package accountant.page.action;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;

import com.exponentus.env.EnvConst;
import com.exponentus.env.Environment;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.user.IUser;
import com.exponentus.util.Util;

import accountant.page.form.ImportFileEntry;
import accountant.page.form.UpdateWizardForm;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class ImportFileChecker extends _DoPage {
	
	@Override
	public void doGET(_Session session, _WebFormData formData) {
		devPrint(formData);
		boolean stopIfWrong = false;
		ImportFileEntry uf = null;
		try {
			OrganizationDAO oDao = new OrganizationDAO(session);
			
			String sie = formData.getValueSilently("stopiferror");
			if (sie.equals("1")) {
				stopIfWrong = true;
			}
			
			String uo = formData.getValueSilently("uploadtype");

			LanguageCode lang = session.getLang();
			
			String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
			if (!fsid.isEmpty()) {
				String fn = formData.getValueSilently("fileid");
				String fileAttr = UpdateWizardForm.getSesAttrName();
				uf = (ImportFileEntry) session.getAttribute(fileAttr);
				
				IUser<Long> user = session.getUser();
				File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
				
				String excelFile = userTmpDir + File.separator + fn;
				String addFileName = excelFile;
				
				String ext = FilenameUtils.getExtension(excelFile);
				if (ext.equalsIgnoreCase("xls")) {
					File xlsFile = new File(excelFile);
					
					XLImporter id = new XLImporter(XLImporter.CHECK);
					Workbook workbook = null;
					try {
						workbook = Workbook.getWorkbook(xlsFile);
					} catch (BiffException e) {
						uf.setStatus(ImportFileEntry.CHECKING_ERROR);
						uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
						addContent(uf);
						return;
					}
					Sheet sheet = workbook.getSheet(0);
					Organization randomOrg = null;
					
					List<Organization> oList = oDao.findAll().getResult();
					randomOrg = (Organization) Util.getRndListElement(oList);
					
					String[] readers = formData.getListOfValuesSilently("readers");
					
					Outcome result = id.process(sheet, session, stopIfWrong, randomOrg, readers, uo, addFileName);
					
					if (result.sheetErr.size() > 0) {
						uf.setStatus(ImportFileEntry.CHECKING_ERROR);
						uf.setLocalizedMsg(getLocalizedWord("file_data_is_incorrect", lang));
						uf.setSheetErrs(result.sheetErr);
						addContent(uf);
					} else {
						uf.setStatus(ImportFileEntry.CHECKED);
						uf.setSheetErrs(null);
						uf.setLocalizedMsg(" Ok, проверено записей: " + Integer.toString(result.processed));
						addContent(uf);
					}
				} else {
					uf.setStatus(ImportFileEntry.CHECKING_ERROR);
					uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
					addContent(uf);
					return;
				}
			}
		} catch (Exception e) {
			if (uf != null) {
				uf.setStatus(ImportFileEntry.CHECKING_ERROR);
				uf.setLocalizedMsg(e.toString());
				addContent(uf);
			}
			setBadRequest();
			logError(e);
		}
	}
}
