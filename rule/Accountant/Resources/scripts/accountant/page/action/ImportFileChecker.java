package accountant.page.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import com.exponentus.env.EnvConst;
import com.exponentus.env.Environment;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.user.IUser;
import com.exponentus.util.Util;

import accountant.page.action.MPXLImporter.ErrorDescription;
import accountant.page.form.ImportFileEntry;
import accountant.page.form.UploadUpdatingForm;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class ImportFileChecker extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		devPrint(formData);
		boolean stopIfWrong = true, writeOff = false, isTransfer = false;
		Organization recipiеnt = null;
		OrganizationDAO oDao = new OrganizationDAO(session);

		String sie = formData.getValueSilently("stopiferror");
		if (sie.equals("1")) {
			stopIfWrong = false;
		}
		String wo = formData.getValueSilently("writeoff");
		if (wo.equals("1")) {
			writeOff = true;
		}

		String uo = formData.getValueSilently("uporder");
		if (uo.equals("1")) {
			isTransfer = true;
			List<Organization> oList = oDao.findAll();
			recipiеnt = (Organization) Util.getRndListElement(oList);
		}

		LanguageCode lang = session.getLang();
		try {
			String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
			if (!fsid.isEmpty()) {
				String fn = formData.getValueSilently("fileid");

				String fileAttr = UploadUpdatingForm.getSesAttrName(fsid, fn);
				ImportFileEntry uf = (ImportFileEntry) session.getAttribute(fileAttr);

				IUser<Long> user = session.getUser();
				File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());

				String excelFile = userTmpDir + File.separator + fn;
				String ext = FilenameUtils.getExtension(excelFile);
				if (ext.equalsIgnoreCase("xls")) {
					File xlsFile = new File(excelFile);

					MPXLImporter id = new MPXLImporter(MPXLImporter.CHECK);
					Workbook workbook = null;
					try {
						workbook = Workbook.getWorkbook(xlsFile);
					} catch (BiffException e) {
						uf.setStatus(ImportFileEntry.CHECKING_ERROR);
						uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
						return;
					}
					Sheet sheet = workbook.getSheet(0);

					List<Organization> oList = oDao.findAll();
					Organization org = (Organization) Util.getRndListElement(oList);
					String[] readers = formData.getListOfValuesSilently("reader");

					Map<Integer, List<List<ErrorDescription>>> sheetErrs = id.process(sheet, session, stopIfWrong, writeOff, org, readers, isTransfer,
					        recipiеnt);

					if (sheetErrs.size() > 0) {
						uf.setStatus(ImportFileEntry.CHECKING_ERROR);
						uf.setLocalizedMsg(getLocalizedWord("file_data_is_incorrect", lang));
						uf.setSheetErrs(sheetErrs);
					} else {
						uf.setStatus(ImportFileEntry.CHECKED);
					}
				} else {
					uf.setStatus(ImportFileEntry.CHECKING_ERROR);
					uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
					return;
				}
				System.out.println(uf.getFullXMLChunk(session));
				session.setAttribute(fileAttr, uf);
			}
		} catch (Exception e) {
			setBadRequest();
			error(e);
		}
	}
}
