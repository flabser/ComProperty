package municipalproperty.page.report;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.flabs.util.Util;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import kz.pchelka.env.Environment;
import kz.pchelka.server.Server;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Property;
import municipalproperty.model.constants.KufType;
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
import staff.dao.OrganizationDAO;
import staff.model.Organization;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class RegistryReport extends _DoPage {
	protected long grandTotal;

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		long start_time = System.currentTimeMillis();
		boolean checkAcceptanceDate = false, checkBalanceHolder = false;
		println(formData);
		String reportName = formData.getValueSilently("id");

		List<KufType> cat = ReportUtil.getCat().get(reportName);
		String[] propertyType = formData.getListOfValuesSilently("propertytype");
		if (!propertyType[0].equals("")) {
			// cat = propertyType;
		}

		Date from = formData.getDateSilently("acceptancedatefrom");
		Date to = formData.getDateSilently("acceptancedateto");
		if (from != null && to != null) {
			checkAcceptanceDate = true;
		}
		UUID bhId = UUID.fromString(formData.getValueSilently("balanceholder"));
		if (bhId != null) {
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
			String repPath = new File("").getAbsolutePath() + File.separator + "webapps" + File.separator + ses.getGlobalSettings().id
			        + File.separator + "reports";

			JRFileVirtualizer virtualizer = new JRFileVirtualizer(10, Environment.trash);
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

			PropertyDAO dao = new PropertyDAO(ses);
			List<Property> result = dao.find(cat, bhId, from, to);

			// ArrayList<IPropertyBean> result = fetchReportData(cat,
			// checkAcceptanceDate, checkBalanceHolder, bc, from, to);

			parameters.put("grandtotal", "");
			if (checkBalanceHolder) {
				OrganizationDAO orgDao = new OrganizationDAO(ses);
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

			// showFile(filePath, fileName);
			Environment.fileToDelete.add(filePath);
			log("Report \"" + reportName + "\" is ready, estimated time is " + Util.getTimeDiffInMilSec(start_time));
		} catch (JRException e) {
			Server.logger.errorLogEntry(e);
		} catch (_Exception e) {
			Server.logger.errorLogEntry(e);
		}
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}

}
