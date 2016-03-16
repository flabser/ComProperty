package municipalproperty.page.form;

import kz.flabs.util.Util;
import kz.lof.env.Environment;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.server.Server;
import kz.nextbase.script._Exception;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.dao.ReportTemplateDAO;
import municipalproperty.model.Property;
import municipalproperty.model.ReportTemplate;
import municipalproperty.model.constants.KufType;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

import java.io.File;
import java.util.*;


public class ReportTemplateForm extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");
        ReportTemplate entity;
        if (!id.isEmpty()) {
            ReportTemplateDAO dao = new ReportTemplateDAO(session);
            entity = dao.findById(UUID.fromString(id));
            addContent(entity);
            _Action back = new _Action(_ActionType.CLOSE);
            back.setURL("Provider?id=report-template-view");
            addContent(new _ActionBar(session).addAction(back));
        } else {
            setBadRequest();
        }
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData) {
        long start_time = System.currentTimeMillis();

        String id = formData.getValueSilently("docid");
        ReportTemplate entity = null;
        if (!id.isEmpty()) {
            ReportTemplateDAO dao = new ReportTemplateDAO(session);
            entity = dao.findById(UUID.fromString(id));
        }

        String reportName = formData.getValueSilently("id");

        if (entity != null) {
            reportName = entity.getType();
        }

        boolean checkAcceptanceDate = false, checkBalanceHolder = false;
        println(formData);
        // String reportName = formData.getValueSilently("id");

        List<KufType> cat = new ArrayList<>();// ReportUtil.getCat().get(reportName);
        if (formData.containsField("propertycode")) {
            String[] propertyType = formData.getListOfValuesSilently("propertycode");
            for (String value : propertyType) {
                if (!"".equalsIgnoreCase(value)) {
                    cat.add(KufType.valueOf(value));
                }
            }
        } else {
            cat = entity.getPropertyType();
        }

        Date from = formData.getDateSilently("acceptancedatefrom");
        Date to = formData.getDateSilently("acceptancedateto");
        if (from != null && to != null) {
            checkAcceptanceDate = true;
        }

        UUID bhId = null;
        if (formData.containsField("balanceholderid")) {
            bhId = UUID.fromString(formData.getValueSilently("balanceholderid"));
            if (bhId != null) {
                checkBalanceHolder = true;
            }
        }

        try {
            String type = ".xlsx";
            String rType = formData.getValue("typefilereport");
            if (rType.equals("1")) {
                type = ".pdf";
            }

            HashMap<String, Object> parameters = new HashMap<String, Object>();
            log("Filling report \"" + reportName + "\"...");
            String repPath = new File("").getAbsolutePath() + File.separator + "webapps" + File.separator + session.getAppEnv().appName
                    + File.separator + "reports";

            JRFileVirtualizer virtualizer = new JRFileVirtualizer(10, Environment.trash);
            parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

            PropertyDAO dao = new PropertyDAO(session);
            List<Property> result = dao.find(cat, bhId, from, to);

            // ArrayList<IPropertyBean> result = fetchReportData(cat,
            // checkAcceptanceDate, checkBalanceHolder, bc, from, to);

            parameters.put("grandtotal", "");
            if (checkBalanceHolder) {
                OrganizationDAO orgDao = new OrganizationDAO(session);
                Organization org = orgDao.findById(bhId);
                parameters.put("balanceholder", org.getName());
            } else {
                parameters.put("balanceholder", "");

            }

            JRBeanCollectionDataSource dSource = new JRBeanCollectionDataSource(result);

            JasperPrint print = JasperFillManager.fillReport(
                    JasperCompileManager.compileReportToFile(repPath + File.separator + "templates" + File.separator + reportName + ".jrxml"),
                    parameters, dSource);

            String fileName = reportName + type;
            String filePath = getTmpDirPath() + File.separator + Util.generateRandomAsText("qwertyuiopasdfghjklzxcvbnm", 10) + type;
            if (type.equalsIgnoreCase(".pdf")) {
                JRStyle style = new JRDesignStyle();
                style.setPdfFontName(repPath + File.separator + "templates" + File.separator + "fonts" + File.separator + "tahoma.ttf");
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
}
