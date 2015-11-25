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
import kz.pchelka.server.Server;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ConsolidatedReport extends _DoScript {
	private String lang;
	String[] personalstateCat = { "furniture", "animals", "sportsequipment", "others", "shareblocks", "equity" };
	String[] equipmentCat = { "schoolequipment", "officeequipment", "computerequipment", "medicalequipment",
			"cookequipment", "equipmentofcivildefense" };
	String[] realestateCat = { "buildings", "rooms", "structures", "residentialobjects", "land", "monument" };
	String[] transportCat = { "automobile", "cargo", "bus", "trolleybus", "tram", "watertransport", "hospitaltransport",
			"specialequipment", "motorcycle" };
	String[] strategicobjectsCat = { "billboard", "columns", "electricnetworks", "thermalnetworks", "gas",
			"watersystem", "drain", "road", "parking" };
	String[] specialconstructionsCat = { "bombproof", "factory", "combines", "airport", "land", "transitions" };
	String[] engineeringInfrastructureCat = { "shareblocks", "equity" };

	@Override
	public void doProcess(_Session ses, _WebFormData formData, String lang) {
		this.lang = lang;
		println(formData);
		String reportName = "consolidated_report";
		HashMap<String, String[]> categories = new HashMap<String, String[]>();
		categories.put("personalstateCat", personalstateCat);
		categories.put("equipmentCat", equipmentCat);
		categories.put("realestateCat", realestateCat);
		categories.put("transportCat", transportCat);
		categories.put("strategicobjectsCat", strategicobjectsCat);
		categories.put("specialconstructionsCat", specialconstructionsCat);
		categories.put("engineeringInfrastructureCat", engineeringInfrastructureCat);

		// formData.getValue("typefilereport").equals("1")
		int type = 2;

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		try {
			log("Filling report...");
			String repPath = new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports";
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(10, repPath);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

			JRBeanCollectionDataSource dSource = new JRBeanCollectionDataSource(fetchData(categories));

			JasperPrint print = JasperFillManager.fillReport(
					JasperCompileManager.compileReportToFile(repPath + "\\templates\\" + reportName + ".jrxml"),
					parameters, dSource);

			if (type == 1) {
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("C:\\tmp\\" + reportName + ".pdf"));
				exporter.exportReport();
			} else {
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("C:\\tmp\\" + reportName + ".xls"));
				exporter.exportReport();
			}

		} catch (JRException e) {
			Server.logger.errorLogEntry(e);
		}
	}

	private ArrayList<ReportRowEntity> fetchData(HashMap<String, String[]> categories) {
		ArrayList<ReportRowEntity> data = new ArrayList<ReportRowEntity>();
		IDatabase db = DatabaseFactory.getDatabase("MonitoringSubsystem");
		for (String key : categories.keySet()) {
			String[] toReport = categories.get(key);
			for (int ci = 0; ci < toReport.length; ci++) {
				FormulaBlocks queryFormulaBlocks = new FormulaBlocks("form=\"" + toReport[ci] + "\"",
						QueryType.DOCUMENT);
				ISelectFormula sf = new SelectFormula(queryFormulaBlocks);
				_ViewEntryCollection vec = db.getCollectionByCondition(sf, ses.getUser(), 0, 0, new TreeSet<DocID>(),
						new RunTimeParameters(), false);
				ReportRowEntity object = new ReportRowEntity();
				object.setCategory(getLocalizedWord(key, lang));
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
		}

		/*
		 * int iteration = 10; for (int i = 0; i < iteration; i++) {
		 * data.add(generateMock()); }
		 */

		return data;

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
