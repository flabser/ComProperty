package monitoring.page.report;

import java.io.File;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoScript;
import kz.pchelka.server.Server;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class TestJasper extends _DoScript {

	@Override
	public void doProcess(_Session ses, _WebFormData formData, String lang) {
		println(formData);
		int type = 1;
		String reportName = "firstreport";
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		String host = ses.getGlobalSettings().dbURL;
		String uName = ses.getGlobalSettings().getDbUserName();
		String uPass = ses.getGlobalSettings().getDbPassword();
		String dName = ses.getGlobalSettings().driver;
		Driver driver;

		try {

			System.out.println("Filling report...");
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(10,
					new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports");
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

			driver = (Driver) Class.forName(dName).newInstance();

			DriverManager.registerDriver(driver);
			Connection conn = DriverManager.getConnection(host, uName, uPass);

			JasperPrint print = JasperFillManager.fillReport(
					JasperCompileManager.compileReportToFile(
							"C:\\Users\\k-zon_000\\JaspersoftWorkspace\\MyReports\\" + reportName + ".jrxml"),
					parameters, conn);

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

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			Server.logger.errorLogEntry(e);
		} catch (JRException e) {
			Server.logger.errorLogEntry(e);
		}
	}
}
