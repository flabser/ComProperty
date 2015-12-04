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
import kz.nextbase.script.events._DoScript;
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

public class RealEstateReport extends _DoScript {
	private String lang;
	private _Session ses;
	private long grandTotal;

	@Override
	public void doProcess(_Session ses, _WebFormData formData, String lang) {
		long start_time = System.currentTimeMillis();
		boolean checkAcceptanceDate = false, checkBalanceHolder = false, checkPropertyType = false;
		this.ses = ses;
		this.lang = lang;
		println(formData);
		String reportName = formData.getValueSilently("id");
		String[] propertyType = formData.getListOfValuesSilently("propertytype");
		if (propertyType.length > 0) {
			checkPropertyType = true;
		}
		Date from = formData.getDateSilently("acceptancedatefrom");
		Date to = formData.getDateSilently("acceptancedateto");
		if (from != null && to != null) {
			checkAcceptanceDate = true;
		}
		int bc = formData.getNumberValueSilently("balanceholder", 0);
		if (bc != 0) {
			checkBalanceHolder = true;
		}

		try {

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

			String[] cat = ReportUtil.getCategories().get("realestateCat");

			if (checkPropertyType) {
				cat = propertyType;
			}

			ArrayList<RealEstateBean> result = fetchReportData(cat, checkAcceptanceDate, checkBalanceHolder, bc, from,
					to);
			parameters.put("grandtotal", Long.toString(grandTotal));
			if (checkBalanceHolder) {
				parameters.put("balanceholder", ReportUtil.getOrgName(ses, bc));
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

	private ArrayList<RealEstateBean> fetchReportData(String[] toReport, boolean checkAcceptanceDate,
			boolean checkBalanceHolder, int bc, Date from, Date to) {
		ArrayList<RealEstateBean> data = new ArrayList<RealEstateBean>();
		IDatabase db = ses.getCurrentDatabase().getBaseObject();

		IDBConnectionPool dbPool = db.getConnectionPool();

		if (toReport != null) {
			for (int ci = 0; ci < toReport.length; ci++) {
				Connection conn = dbPool.getConnection();
				try {

					conn.setAutoCommit(false);
					Statement s = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

					String wherePart = "";
					if (checkBalanceHolder) {
						wherePart = " and m.docid in (select  cf.docid from maindocs as m, custom_fields as cf"
								+ " where cf.name='balanceholder' and cf.docid = m.docid and cf.valueasnumber=" + bc
								+ ")";
					}

					String sql1 = "select m.docid, m.viewtext, cf.value, cf.valueasdate, cf.name from maindocs as m, "
							+ "custom_fields as cf where m.form='" + toReport[ci] + "'" + " and " + "cf.docid = m.docid"
							+ wherePart;
					Server.logger.verboseLogEntry(sql1);
					ResultSet rs = s.executeQuery(sql1);
					while (rs.next()) {
						data.add(getDocument(rs));
					}

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

		}

		return data;
	}

	private RealEstateBean getDocument(ResultSet rs) {
		RealEstateBean object = new RealEstateBean();
		try {
			boolean nextOk = !rs.isAfterLast();
			int docId = rs.getInt(1);
			while (nextOk && rs.getInt(1) == docId) {
				String name = rs.getString("name");
				if (name.equals("invnumber")) {
					object.setInvnumber(rs.getString("value"));
					object.setViewtext(rs.getString("viewtext"));
				} else if (name.equals("originalcost")) {
					object.setOriginalcost(ReportUtil.getLongValue(rs, "value"));
				} else if (name.equals("description")) {
					object.setAssignment(rs.getString("value"));
				} else if (name.equals("propertycode_name")) {
					object.setPropertycodename(rs.getString("value"));
				} else if (name.equals("material")) {
					object.setMaterial(rs.getString("value"));
				} else if (name.equals("yearconstruction")) {
					object.setYearrealise(Integer.toString(ReportUtil.getIntValue(rs, "value")));
				}
				System.out.println(rs.getString("value") + " " + docId);
				nextOk = rs.next();
			}

		} catch (SQLException e) {
			DatabaseUtil.errorPrint(e);
		} catch (Exception e) {
			Database.logger.errorLogEntry(e);
		}
		return object;

	}

}
