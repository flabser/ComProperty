package monitoring.page.report;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

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

	@Override
	public void doProcess(_Session ses, _WebFormData formData, String lang) {
		println(formData);
		String reportName = "firstreport";
		// formData.getValue("typefilereport").equals("1")
		int type = 1;

		HashMap<String, Object> parameters = new HashMap<String, Object>();

		try {
			log("Filling report...");
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(10,
					new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports");
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

			JRBeanCollectionDataSource dSource = new JRBeanCollectionDataSource(fetchData());

			JasperPrint print = JasperFillManager.fillReport(
					JasperCompileManager.compileReportToFile(
							"C:\\Users\\k-zon_000\\JaspersoftWorkspace\\MyReports\\" + reportName + ".jrxml"),
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

	private ArrayList<ReportRowEntity> fetchData() {
		ArrayList<ReportRowEntity> data = new ArrayList<ReportRowEntity>();

		int iteration = 10;
		for (int i = 0; i < iteration; i++) {
			data.add(generateMock());
		}

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
