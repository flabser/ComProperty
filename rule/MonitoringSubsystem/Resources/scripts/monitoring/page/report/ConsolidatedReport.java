package monitoring.page.report;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import kz.flabs.dataengine.DatabaseUtil;
import kz.flabs.dataengine.IDBConnectionPool;
import kz.flabs.dataengine.IDatabase;
import kz.flabs.dataengine.h2.Database;
import kz.flabs.util.Util;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.pchelka.env.Environment;
import kz.pchelka.server.Server;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ConsolidatedReport extends PropertyReport {

	@Override
	public void doProcess(_Session ses, _WebFormData formData, String lang) {
		long start_time = System.currentTimeMillis();
		boolean checkAcceptanceDate = false, checkBalanceHolder = false;
		this.ses = ses;
		this.lang = lang;
		println(formData);
		String reportName = formData.getValueSilently("id");
		Date from = formData.getDateSilently("acceptancedatefrom");
		Date to = formData.getDateSilently("acceptancedateto");
		if (from != null && to != null) {
			checkAcceptanceDate = true;
		}
		Integer[] bc = formData.getNumberValuesSilently("balanceholder", 0);
		if (bc[0] != 0) {
			checkBalanceHolder = true;
		}

		try {
			String pType[] = formData.getListOfValuesSilently("propertytype");
			HashMap<String, String[]> categories = new HashMap<String, String[]>();
			HashMap<String, String[]> allCategories = ReportUtil.getCategories();
			for (String val : pType) {
				categories.put(val, allCategories.get(val));
			}

			String type = ".xlsx";
			String rType = formData.getValue("typefilereport");
			if (rType.equals("1")) {
				type = ".pdf";
			}

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			log("Filling report \"" + reportName + "\"...");
			String repPath = new File("").getAbsolutePath() + File.separator + "webapps" + File.separator
					+ ses.getGlobalSettings().id + File.separator + "reports";
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(10, repPath);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

			ArrayList<ConsolidatedDataBean> result = fetchReportData(categories, checkAcceptanceDate,
					checkBalanceHolder, bc, from, to);
			parameters.put("grandtotal", Long.toString(grandTotal));
			if (checkBalanceHolder) {
				// parameters.put("balanceholder", ReportUtil.getOrgName(ses,
				// bc));
			} else {
				parameters.put("balanceholder", "");
			}

			JRBeanCollectionDataSource dSource = new JRBeanCollectionDataSource(result);

			JasperPrint print = JasperFillManager.fillReport(
					JasperCompileManager.compileReportToFile(repPath + "\\templates\\" + reportName + ".jrxml"),
					parameters, dSource);

			String fileName = reportName + type;
			String filePath = getTmpDirPath() + File.separator
					+ Util.generateRandomAsText("qwertyuiopasdfghjklzxcvbnm", 10) + type;
			if (type.equalsIgnoreCase(".pdf")) {
				JRStyle style = new JRDesignStyle();
				style.setPdfFontName(repPath + "\\templates\\fonts\\tahoma.ttf");
				style.setPdfEncoding("Cp1251");
				style.setPdfEmbedded(true);
				print.setDefaultStyle(style);
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filePath));
				exporter.exportReport();
			} else if (type.equalsIgnoreCase(".xlsx")) {
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filePath));
				exporter.exportReport();
			}

			showFile(filePath, fileName);
			Environment.fileToDelete.add(filePath);
			log("Report \"" + reportName + "\" is ready, estimated time is " + Util.getTimeDiffInMilSec(start_time));
		} catch (JRException e) {
			Server.logger.errorLogEntry(e);
		} catch (_Exception e) {
			Server.logger.errorLogEntry(e);
		}
	}

	private ArrayList<ConsolidatedDataBean> fetchReportData(HashMap<String, String[]> categories,
			boolean checkAcceptanceDate, boolean checkBalanceHolder, Integer[] bc, Date from, Date to) {

		ArrayList<ConsolidatedDataBean> data = new ArrayList<ConsolidatedDataBean>();
		IDatabase db = ses.getCurrentDatabase().getBaseObject();

		IDBConnectionPool dbPool = db.getConnectionPool();
		for (Integer bKey : bc) {
			ConsolidatedDataBean orgObject = new ConsolidatedDataBean();
			if (checkBalanceHolder) {
				orgObject.setOrgName(ReportUtil.getOrgName(ses, bKey));
			} else {
				orgObject.setOrgName("Все организации");
			}
			data.add(orgObject);
			for (String key : categories.keySet()) {

				String[] toReport = categories.get(key);
				if (toReport != null) {
					long countCat = 0, originalCostSumCat = 0, cumulativedepreciationSumCat = 0, balanceCostSumCat = 0;
					ConsolidatedDataBean catObject = new ConsolidatedDataBean();
					catObject.setCategory(getLocalizedWord(key, lang));
					data.add(catObject);
					for (int ci = 0; ci < toReport.length; ci++) {
						ConsolidatedDataBean object = new ConsolidatedDataBean();
						object.setSubCategory(getLocalizedWord(toReport[ci], lang));
						Connection conn = dbPool.getConnection();
						try {

							conn.setAutoCommit(false);
							Statement s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

							long countSum = 0, originalCostSum = 0, cumulativedepreciationSum = 0, balanceCostSum = 0;

							String wherePart = "";
							if (checkBalanceHolder) {
								wherePart = " and m.docid in (select  cf.docid from maindocs as m, custom_fields as cf"
										+ " where cf.name='balanceholder' and cf.docid = m.docid and cf.valueasnumber="
										+ bKey + ")";
							}

							String sql1 = "select count(m.docid) from maindocs as m, custom_fields as cf where m.form='"
									+ toReport[ci] + "'" + " and cf.docid = m.docid" + wherePart;
							// Server.logger.verboseLogEntry(sql1);
							ResultSet rs = s.executeQuery(sql1);
							if (rs.next()) {
								countSum = rs.getLong(1);
								// Server.logger.verboseLogEntry(Long.toString(countSum));
							}

							String sql2 = "select sum(CASE WHEN cf.value~E'^\\\\d+$' THEN cf.value::bigint ELSE 0 END) from maindocs as m, "
									+ "custom_fields as cf where m.form='" + toReport[ci]
									+ "' and cf.docid = m.docid and " + "cf.name = 'originalcost'" + wherePart;
							rs = s.executeQuery(sql2);
							if (rs.next()) {
								originalCostSum = rs.getLong(1);
								// Server.logger.verboseLogEntry(originalCostSum
								// + "
								// " + sql2);
							}

							String sql3 = "select sum(CASE WHEN cf.value~E'^\\\\d+$' THEN cf.value::bigint ELSE 0 END) from maindocs as m, "
									+ "custom_fields as cf where m.form='" + toReport[ci]
									+ "' and cf.docid = m.docid and " + "cf.name = 'cumulativedepreciation'"
									+ wherePart;
							;
							rs = s.executeQuery(sql3);
							if (rs.next()) {
								cumulativedepreciationSum = rs.getLong(1);
								// Server.logger.verboseLogEntry(cumulativedepreciationSum
								// + " " + sql3);
							}

							String sql4 = "select sum(CASE WHEN cf.value~E'^\\\\d+$' THEN cf.value::bigint ELSE 0 END) from maindocs as m, "
									+ "custom_fields as cf where m.form='" + toReport[ci]
									+ "' and cf.docid = m.docid and " + "cf.name = 'balancecost'" + wherePart;
							rs = s.executeQuery(sql4);
							if (rs.next()) {
								balanceCostSum = rs.getLong(1);
								// Server.logger.verboseLogEntry(balanceCostSum
								// + "
								// " + sql4);
							}

							object.setCountNum(countSum);
							object.setPrimaryCostNum(originalCostSum);
							object.setDepreciationNum(cumulativedepreciationSum);
							object.setBookvalueNum(balanceCostSum);
							object.setReassessmentCostNum(0);
							countCat = countCat + countSum;
							grandTotal = grandTotal + countSum;
							originalCostSumCat = originalCostSumCat + originalCostSum;
							cumulativedepreciationSumCat = cumulativedepreciationSumCat + cumulativedepreciationSum;
							balanceCostSumCat = balanceCostSumCat + balanceCostSum;
							data.add(object);

							rs.close();
							s.close();
							conn.commit();

						} catch (SQLException e) {
							DatabaseUtil.errorPrint(db.getDbID(), e);
						} catch (Exception e) {
							Database.logger.errorLogEntry(e);
						} finally {
							dbPool.returnConnection(conn);
						}

					}
					catObject.setCountNum(countCat);
					catObject.setPrimaryCostNum(catObject.getPrimaryCostNum() + originalCostSumCat);
					catObject.setDepreciationNum(catObject.getDepreciationNum() + cumulativedepreciationSumCat);
					catObject.setBookvalueNum(catObject.getBookvalueNum() + balanceCostSumCat);
					catObject.setReassessmentCostNum(0);
				}
			}
		}
		return data;
	}

	@Override
	protected IPropertyBean getDocument(ResultSet rs) {
		return null;
	}

	@Override
	protected IPropertyBean getDocument() {
		return null;
	}

}
