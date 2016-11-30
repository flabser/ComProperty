package municipalproperty.page.report;

import java.io.File;
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
import com.exponentus.scripting.event._DoPage;
import com.exponentus.server.Server;
import com.exponentus.util.StringUtil;
import com.exponentus.util.TimeUtil;

import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Property;
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
import reference.model.constants.KufType;
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
	public void doGET(_Session session, _WebFormData formData) {
		long start_time = System.currentTimeMillis();
		boolean checkBalanceHolder = false;
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
		}

		UUID bhCat = null;
		UUID bhId = UUID.fromString(formData.getValueSilently("balanceholder"));
		if (bhId != null) {
			checkBalanceHolder = true;
		} else {
			bhCat = UUID.fromString(formData.getValueSilently("orgcategory"));
		}

		try {

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
			if (checkBalanceHolder) {
				OrganizationDAO orgDao = new OrganizationDAO(session);
				Organization org = orgDao.findById(bhId);
				parameters.put("balanceholder", org.getName());
			} else {
				parameters.put("balanceholder", "");

			}

			JRBeanCollectionDataSource dSource = new JRBeanCollectionDataSource(result);

			JasperPrint print = JasperFillManager.fillReport(
					JasperCompileManager.compileReportToFile(
							repPath + File.separator + "templates" + File.separator + reportName + ".jrxml"),
					parameters, dSource);

			String fileName = reportName + type;
			String filePath = getTmpDirPath() + File.separator
					+ StringUtil.generateRandomAsText("qwertyuiopasdfghjklzxcvbnm", 10) + type;
			if (type.equalsIgnoreCase(".pdf")) {
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
			} else if (type.equalsIgnoreCase(".xlsx")) {
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

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
