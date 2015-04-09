package monitoring.handler.engineeringInfrastrcuture_report

import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoHandler
import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.design.JRDesignStyle
import net.sf.jasperreports.engine.export.JExcelApiExporter
import net.sf.jasperreports.engine.export.JRHtmlExporter
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter
import net.sf.jasperreports.engine.fill.JRFileVirtualizer

import java.sql.Connection
import java.sql.Driver
import java.sql.DriverManager
import java.text.SimpleDateFormat

class Trigger extends _DoHandler {

    @Override
    void doHandler(_Session ses, Map<String, String[]> formData, String lang) {
        try {
            /*
    region
    isresol
    street
    typefilereport 1
    type handler
    city
    id transport_report
    author
    acceptancedatefrom
    acceptancedateto
    district
    doctype 896
    key
    balanceholder */
            formData.each {
                println(it.key + " " + it.value[0]);
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            SimpleDateFormat sqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            def cdb = ses.getCurrentDatabase();
            String reportName = formData.get("id")[0];
            String datefrom = "";
            String dateto = "";
            String formatdatefrom = "";
            String formatdateto = "";
            String rangePropType = "";
            String region = "";
            String street = "";
            String city = "";
            String district = "";
            String balanceholder = "";
            String[] propertytype = [];
                region = formData.get("region")[0];
                street = formData.get("street")[0];
                city = formData.get("city")[0];
                district = formData.get("district")[0];
                balanceholder = formData.get("balanceholder")[0];
                propertytype = formData.get("propertytype");
                datefrom = formData.get("acceptancedatefrom")[0];
                dateto = formData.get("acceptancedateto")[0];
                if (propertytype) {
                    propertytype.each {
                        rangePropType += "'" + it + "',";
                    }
                    if (rangePropType.length() > 0) rangePropType = rangePropType.substring(0, rangePropType.length() - 1);
                }
                println(rangePropType);
                if (datefrom) {
                    java.sql.Date tDateFrom = new java.sql.Date(dateFormat.parse(datefrom).getTime());
                    datefrom = sqlDateFormat.format(tDateFrom);
                    formatdatefrom = dateFormat.format(tDateFrom);
                }
                if (dateto) {
                    java.sql.Date tDateTo = new java.sql.Date(dateFormat.parse(dateto).getTime());
                    dateto = sqlDateFormat.format(tDateTo);
                    formatdateto = dateFormat.format(tDateTo);
                }
            //
            //Определяем формат будущего отчета и способ его отображения
            String typeReportFile = "";
            String openReportWith = "";
            typeReportFile = formData.get("typefilereport")[0];
            //openReportWith = formData.get("openreportwith")[0];

            System.out.println("Filling report...");
            JRFileVirtualizer virtualizer = new JRFileVirtualizer(10, new File("").absolutePath + "\\webapps\\MonitoringSubsystem\\reports");
            Map parameters = new HashMap();
            parameters.put("REGION", region);
            parameters.put("CITY", city);
            parameters.put("DISTRICT", district);
            parameters.put("STREET", street);
            parameters.put("PROPERTYTYPE", rangePropType);
            parameters.put("BALANCEHOLDER", balanceholder);

            parameters.put("DATETO", dateto);
            parameters.put("FORMAT_DATETO", formatdateto);
            parameters.put("DATEFROM", datefrom);
            parameters.put("FORMAT_DATEFROM", formatdatefrom);
            parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
            println(parameters.each { it.key + " " + it.value });


            String host = ses.getGlobalSettings().dbURL;
            String uName = ses.getGlobalSettings().dbUserName;
            String uPass = ses.getGlobalSettings().dbPassword;
            String dName = ses.getGlobalSettings().driver;
            Driver driver = (Driver) Class.forName(dName).newInstance();
            DriverManager.registerDriver(driver);
            Connection conn = DriverManager.getConnection(host, uName, uPass);

            JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReportToFile(new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\templates\\" + reportName + ".jrxml"), parameters, conn);

            JRStyle style = new JRDesignStyle();
            style.setPdfFontName(new File("").absolutePath + "\\webapps\\MonitoringSubsystem\\reports\\templates\\fonts\\tahoma.ttf");
            style.setPdfEncoding("Cp1251");
            style.setPdfEmbedded(true);
            print.setDefaultStyle(style);
            System.out.println("Done!");

            String format = "";
            String app = "";

            switch (typeReportFile) {
                case "1":
                    JasperExportManager.exportReportToPdfFile(print, new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\" + reportName + ".pdf");
                    format = "pdf";
                    app = "AcroRd32.exe";
                    break;
                case "2":
                    JExcelApiExporter xlsExporter = new JExcelApiExporter();
                    xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\" + reportName + ".xls");
                    xlsExporter.exportReport();
                    format = "xls";
                    app = "excel.exe";
                    break;
                case "3":
                    JRHtmlExporter exporter = new JRHtmlExporter();
                    exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\" + reportName + ".html_files\\");
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\" + reportName + ".html");
                    //JasperExportManager.exportReportToHtmlFile(print, new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\"+ reportName +".html");
                    exporter.exportReport();
                    format = "html";
                    app = "iexplore.exe";
                    break;
                default:
                    JasperExportManager.exportReportToPdfFile(print, new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\" + reportName + ".pdf");
                    format = "pdf";
                    app = "AcroRd32.exe";
                    break;
            }
            //return "${new File("").absolutePath}\\webapps\\MonitoringSubsystem\\reports\\$reportName.$format";
            virtualizer.cleanup();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

	@Override
	public void doHandler(_Session session, _WebFormData formData) {
		// TODO Auto-generated method stub
		
	}
}