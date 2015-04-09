package monitoring.page.printform

import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource
import net.sf.jasperreports.engine.design.JRDesignStyle
import net.sf.jasperreports.engine.fill.JRFileVirtualizer

class DoScript extends _DoScript {
    @Override
    void doProcess(_Session session, _WebFormData formData, String lang) {

/*        JasperPrint printTest = JasperFillManager.fillReport(JasperCompileManager.compileReportToFile(new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\templates\\Flower.jrxml"), new HashMap<String, Object>())
        JasperExportManager.exportReportToPdfFile(printTest, new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\templates\\Flower.pdf")
        return*/

        JRFileVirtualizer virtualizer = new JRFileVirtualizer(10, new File("").absolutePath + "\\webapps\\MonitoringSubsystem\\reports");

        def contract = session.getCurrentDatabase().getDocumentByComplexID(formData.getValueSilently("doctype"), formData.getValueSilently("key"))

        Map parameters = new HashMap();
        //    parameters.put("TITLE", contract.getViewText());
        //    parameters.put("REGNUMBER", contract.getValueString("regnumber"))
/*        parameters.put("CITY", city);
        parameters.put("DISTRICT", district);
        parameters.put("STREET", street);
        parameters.put("PROPERTYTYPE", rangePropType);
        parameters.put("BALANCEHOLDER", balanceholder);

        parameters.put("DATETO", dateto);
        parameters.put("FORMAT_DATETO", formatdateto);
        parameters.put("DATEFROM", datefrom);
        parameters.put("FORMAT_DATEFROM", formatdatefrom);*/
        parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
        println(parameters.each { it.key + " " + it.value });

        String reportName = "printform";
        ContractBeanFactory.fillBeanArray(contract)
        JRBeanArrayDataSource dataSource = ContractBeanFactory.getBeanArray()
        JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReportToFile(new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\templates\\" + reportName + ".jrxml"), parameters, dataSource);

        JRStyle style = new JRDesignStyle();
        style.setPdfFontName(new File("").absolutePath + "\\webapps\\MonitoringSubsystem\\reports\\templates\\fonts\\tahoma.ttf");
        style.setPdfEncoding("Cp1251");
        style.setPdfEmbedded(true);
        print.setDefaultStyle(style);
        System.out.println("Done!");

        String format = "";
        String app = "";

        JasperExportManager.exportReportToPdfFile(print, new File("").getAbsolutePath() + "\\webapps\\MonitoringSubsystem\\reports\\" + reportName + ".pdf");
        //return "${new File("").absolutePath}\\webapps\\MonitoringSubsystem\\reports\\$reportName.$format";
        virtualizer.cleanup();

    }

}

public class ContractBeanFactory {

    private static Contract[] arr = [new Contract()]

    public static void fillBeanArray(_Document contract) {
        arr[0].setViewtext(contract.getViewText())
        arr[0].setRegnumber(contract.getValueString("regnumber"))
    }

    public static Contract[] getBeanArray() {
        return arr;
    }

    public static Contract[] generateBeanArray() {
        return arr;
    }

}

public class Contract {

    public String viewtext;
    public String regnumber;
    public Date regdate = new Date();

    Contract(){
    }

    public Contract(String viewtext, String regnumber, Date regdate) {
        this.viewtext = viewtext
        this.regnumber = regnumber
        this.regdate = regdate
    }

    public String getViewtext() {
        return viewtext
    }

    public void setViewtext(String viewtext) {
        this.viewtext = viewtext
    }

    public Date getRegdate() {
        return regdate
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate
    }

    public String getRegnumber() {
        return regnumber
    }

    public void setRegnumber(String regnumber) {
        this.regnumber = regnumber
    }

}
