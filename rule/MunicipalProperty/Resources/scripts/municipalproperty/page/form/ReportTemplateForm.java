package municipalproperty.page.form;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.env.Environment;
import com.exponentus.scheduler.tasks.TempFileCleaner;
import com.exponentus.scripting._Exception;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.server.Server;
import com.exponentus.util.StringUtil;
import com.exponentus.util.TimeUtil;

import municipalproperty.dao.PropertyDAO;
import municipalproperty.dao.ReportTemplateDAO;
import municipalproperty.model.Property;
import municipalproperty.model.ReportTemplate;
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
import reference.dao.OrgCategoryDAO;
import reference.model.OrgCategory;
import reference.model.constants.KufType;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class ReportTemplateForm extends _DoPage {
	
	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		ReportTemplate entity;
		try {
			if (!id.isEmpty()) {
				ReportTemplateDAO dao = new ReportTemplateDAO(session);
				entity = dao.findById(UUID.fromString(id));
				addContent(entity);
				_Action back = new _Action(_ActionType.CLOSE);
				back.setURL("Provider?id=report-template-view");
				addContent(new _ActionBar(session, getCurrentAppEnv()).addAction(back));
			} else {
				setBadRequest();
			}
		} catch (DAOException e) {
			logError(e);
			setBadRequest();
			return;
		}
	}
	
	@Override
	public void doPOST(_Session session, _WebFormData formData) {
		long start_time = System.currentTimeMillis();
		
		String id = formData.getValueSilently("docid");
		ReportTemplate entity = null;
		try {
			if (!id.isEmpty()) {
				ReportTemplateDAO dao = new ReportTemplateDAO(session);
				entity = dao.findById(UUID.fromString(id));
			}

			String reportName = formData.getValueSilently("id");

			if (entity != null) {
				reportName = entity.getType();
			}

			String addInfo = "";
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
				// checkAcceptanceDate = true;
			}

			UUID bhCat = null;
			UUID bhId = null;
			String bh = formData.getValueSilently("balanceholder");

			if (!bh.isEmpty()) {
				bhId = UUID.fromString(bh);
				OrganizationDAO orgDao = new OrganizationDAO(session);
				Organization org = orgDao.findById(bhId);
				addInfo = org.getName();
			} else {
				String oc = formData.getValueSilently("orgcategory");
				if (!oc.isEmpty()) {
					bhCat = UUID.fromString(oc);

					OrgCategoryDAO ocDao = new OrgCategoryDAO(session);
					OrgCategory orgCatEntity = ocDao.findById(oc);
					addInfo = orgCatEntity.getName();

				}
			}
			
			String type = ".xlsx";
			String rType = formData.getValue("typefilereport");
			if (rType.equals("1")) {
				type = ".pdf";
			}
			
			HashMap<String, Object> parameters = new HashMap<>();
			log("Filling report \"" + reportName + "\"...");
			String repPath = new File("").getAbsolutePath() + File.separator + "webapps" + File.separator
					+ getCurrentAppEnv().appName + File.separator + "reports";
			
			JRFileVirtualizer virtualizer = new JRFileVirtualizer(10, Environment.trash);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			
			PropertyDAO dao = new PropertyDAO(session);
			List<Property> result = dao.findAllForReport(cat, bhId, bhCat, from, to);
			
			// ArrayList<IPropertyBean> result = fetchReportData(cat,
			// checkAcceptanceDate, checkBalanceHolder, bc, from, to);
			
			parameters.put("grandtotal", "");
			parameters.put("balanceholder", addInfo);
			
			JRBeanCollectionDataSource dSource = new JRBeanCollectionDataSource(result);
			
			JasperPrint print = JasperFillManager.fillReport(
					JasperCompileManager.compileReportToFile(
							repPath + File.separator + "templates" + File.separator + reportName + ".jrxml"),
					parameters, dSource);
			
			String fileName = reportName + type;
			String filePath = getTmpDirPath() + File.separator
					+ StringUtil.generateRandomAsText("qwertyuiopasdfghjklzxcvbnm", 10) + type;
			if (type.equals(".pdf")) {
				JRStyle style = new JRDesignStyle();
				style.setPdfFontName(repPath + File.separator + "templates" + File.separator + "fonts" + File.separator
						+ "tahoma.ttf");
				style.setPdfEncoding("Cp1251");
				style.setPdfEmbedded(true);
				print.setDefaultStyle(style);
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filePath));
				exporter.exportReport();
			} else if (type.equals(".xlsx")) {
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(print));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filePath));
				exporter.exportReport();
			}
			
			showFile(filePath, fileName);
			TempFileCleaner.addFileToDelete(filePath);
			log("Report \"" + reportName + "\" is ready, estimated time is "
					+ TimeUtil.getTimeDiffInMilSec(start_time));
		} catch (DAOException e) {
			logError(e);
			setBadRequest();
		} catch (JRException e) {
			Server.logger.errorLogEntry(e);
		} catch (_Exception e) {
			Server.logger.errorLogEntry(e);
		}
	}
}
