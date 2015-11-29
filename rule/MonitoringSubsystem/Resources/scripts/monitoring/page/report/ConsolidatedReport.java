package monitoring.page.report;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

import kz.flabs.dataengine.DatabaseFactory;
import kz.flabs.dataengine.IDatabase;
import kz.flabs.dataengine.ISelectFormula;
import kz.flabs.dataengine.h2.queryformula.SelectFormula;
import kz.flabs.parser.FormulaBlocks;
import kz.flabs.runtimeobj.document.DocID;
import kz.flabs.users.RunTimeParameters;
import kz.flabs.util.Util;
import kz.flabs.webrule.constants.QueryType;
import kz.nextbase.script._Document;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Session;
import kz.nextbase.script._ViewEntry;
import kz.nextbase.script._ViewEntryCollection;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoScript;
import kz.pchelka.env.Environment;
import kz.pchelka.server.Server;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;

public class ConsolidatedReport extends _DoScript {
	private String lang;

	@Override
	public void doProcess(_Session ses, _WebFormData formData, String lang) {
		this.lang = lang;
		println(formData);
		String reportName = formData.getValueSilently("id");
		try {
			String pType[] = formData.getListOfValuesSilently("propertytype");
			HashMap<String, String[]> categories = new HashMap<String, String[]>();
			HashMap<String, String[]> allCategories = getCategories();
			for (String val : pType) {
				categories.put(val, allCategories.get(val));
			}

			String type = ".xls";
			String rType = formData.getValue("typefilereport");
			if (rType.equals("1")) {
				type = ".pdf";
			}

			HashMap<String, Object> parameters = new HashMap<String, Object>();
			log("Filling report...");
			String repPath = new File("").getAbsolutePath() + File.separator + "webapps" + File.separator
					+ ses.getGlobalSettings().id + File.separator + "reports";
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(10, repPath);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

			JRBeanCollectionDataSource dSource = new JRBeanCollectionDataSource(fetchData(categories));

			JasperPrint print = JasperFillManager.fillReport(
					JasperCompileManager.compileReportToFile(repPath + "\\templates\\" + reportName + ".jrxml"),
					parameters, dSource);

			String fileName = reportName + "." + type;
			String filePath = getTmpDirPath() + File.separator
					+ Util.generateRandomAsText("qwertyuiopasdfghjklzxcvbnm", 10) + type;
			if (type.equalsIgnoreCase(".pdf")) {
				JRStyle style = new JRDesignStyle();
				style.setPdfFontName(repPath + "\\templates\\fonts\\tahoma.ttf");
				style.setPdfEncoding("Cp1251");
				style.setPdfEmbedded(true);
				print.setDefaultStyle(style);
				// JRPdfExporter exporter = new JRPdfExporter();
				// exporter.setExporterInput(new SimpleExporterInput(print));
				// exporter.setExporterOutput(new
				// SimpleOutputStreamExporterOutput(filePath));
				// exporter.exportReport();
				JasperExportManager.exportReportToPdfFile(print, filePath);

			} else if (type.equalsIgnoreCase(".xls")) {
				// JRXlsxExporter exporter = new JRXlsxExporter();
				// exporter.setExporterInput(new SimpleExporterInput(print));
				// exporter.setExporterOutput(new
				// SimpleOutputStreamExporterOutput(filePath));
				// exporter.exportReport();
				JExcelApiExporter xlsExporter = new JExcelApiExporter();
				xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
				xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, filePath);
				xlsExporter.exportReport();
			}

			showFile(filePath, fileName);
			Environment.fileToDelete.add(filePath);
			log("Done");
		} catch (JRException e) {
			Server.logger.errorLogEntry(e);
		} catch (_Exception e) {
			Server.logger.errorLogEntry(e);
		}
	}

	private ArrayList<ReportRowEntity> fetchData(HashMap<String, String[]> categories) {
		ArrayList<ReportRowEntity> data = new ArrayList<ReportRowEntity>();
		IDatabase db = DatabaseFactory.getDatabase(ses.getGlobalSettings().id);
		for (String key : categories.keySet()) {
			String[] toReport = categories.get(key);
			if (toReport != null) {
				ReportRowEntity object = new ReportRowEntity();
				object.setCategory(getLocalizedWord(key, lang));
				object.setSubCategory("");
				data.add(object);
				for (int ci = 0; ci < toReport.length; ci++) {
					object = new ReportRowEntity();
					FormulaBlocks queryFormulaBlocks = new FormulaBlocks("form=\"" + toReport[ci] + "\"",
							QueryType.DOCUMENT);
					ISelectFormula sf = new SelectFormula(queryFormulaBlocks);
					_ViewEntryCollection vec = db.getCollectionByCondition(sf, ses.getUser(), 0, 0,
							new TreeSet<DocID>(), new RunTimeParameters(), false);
					object.setCategory("");
					object.setSubCategory(getLocalizedWord(toReport[ci], lang));
					object.setCount(vec.getCount());
					int originalCostSum = 0, cumulativedepreciationSum = 0, balanceCostSum = 0;
					for (_ViewEntry e : vec.getEntries()) {
						try {
							_Document doc = e.getDocument();
							originalCostSum = originalCostSum + doc.getValueInt("originalcost");
							cumulativedepreciationSum = cumulativedepreciationSum
									+ doc.getValueInt("cumulativedepreciation");
							balanceCostSum = balanceCostSum + doc.getValueInt("balancecost");
						} catch (_Exception e1) {
							Server.logger.errorLogEntry(e1);
						}

					}
					object.setPrimaryCost(originalCostSum);
					object.setDepreciation(cumulativedepreciationSum);
					object.setBookvalue(balanceCostSum);
					object.setReassessmentCost(0);
					data.add(object);
				}
			} else {
				ReportRowEntity object = new ReportRowEntity();
				object.setCategory(getLocalizedWord("no_data", lang));
				object.setSubCategory("");
				data.add(object);
			}
		}

		/*
		 * int iteration = 10; for (int i = 0; i < iteration; i++) {
		 * data.add(generateMock()); }
		 */

		return data;

	}

	private HashMap<String, String[]> getCategories() {
		HashMap<String, String[]> cat = new HashMap<String, String[]>();
		cat.put("personalstateCat",
				new String[] { "furniture", "animals", "sportsequipment", "others", "shareblocks", "equity" });
		cat.put("equipmentCat", new String[] { "schoolequipment", "officeequipment", "computerequipment",
				"medicalequipment", "cookequipment", "equipmentofcivildefense" });
		cat.put("realestateCat",
				new String[] { "buildings", "rooms", "structures", "residentialobjects", "land", "monument" });
		cat.put("transportCat", new String[] { "automobile", "cargo", "bus", "trolleybus", "tram", "watertransport",
				"hospitaltransport", "specialequipment", "motorcycle" });
		cat.put("strategicobjectsCat", new String[] { "billboard", "columns", "electricnetworks", "thermalnetworks",
				"gas", "watersystem", "drain", "road", "parking" });
		cat.put("specialconstructionsCat",
				new String[] { "bombproof", "factory", "combines", "airport", "land", "transitions" });

		cat.put("engineeringInfrastructureCat", new String[] { "shareblocks", "equity" });
		return cat;
	}

	private ReportRowEntity generateMock() {
		ReportRowEntity object = new ReportRowEntity();
		object.setCategory(Util.generateRandomAsText("qwertyuiopasdfghjklzxcvbnm", 10));
		object.setSubCategory(Util.generateRandomAsText("qwertyuiopasdfghjklzxcvbnm", 10));
		object.setCount(Util.generateRandom());
		object.setPrimaryCost(Util.generateRandom());
		object.setDepreciation(Util.generateRandom());
		object.setBookvalue(Util.generateRandom());
		object.setReassessmentCost(Util.generateRandom());
		return object;

	}
}
