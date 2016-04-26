package accountant.page.action;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

import accountant.page.action.MPXLImporter.ErrorDescription;
import accountant.page.form.UploadedFile;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.exponentus.env.EnvConst;
import com.exponentus.env.Environment;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.user.IUser;
import com.exponentus.util.Util;

import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class ImportFileChecker extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		boolean stopIfWrong = true, writeOff = false;
		String sie = formData.getValueSilently("stopiferror");
		if (sie.equals("1")) {
			stopIfWrong = false;
		}
		String wo = formData.getValueSilently("writeoff");
		if (wo.equals("1")) {
			writeOff = true;
		}

		LanguageCode lang = session.getLang();
		try {
			String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
			if (!fsid.isEmpty()) {
				String fn = formData.getValueSilently("fileid");
				UploadedFile uf = (UploadedFile) session.getAttribute(fsid + "_file" + fn);
				if (uf == null) {
					uf = new UploadedFile();
					uf.setName(fn);
					uf.setStatus(UploadedFile.JUST_UPLOADED);
					session.setAttribute(fsid + "_file" + fn, uf);
				}

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
						uf.setStatus(UploadedFile.CHECKING_ERROR);
						uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
						return;
					}
					Sheet sheet = workbook.getSheet(0);

					OrganizationDAO oDao = new OrganizationDAO(session);
					List<Organization> oList = oDao.findAll();
					Organization org = (Organization) Util.getRndListElement(oList);
					String[] readers = formData.getListOfValuesSilently("reader");

					Map<Integer, List<List<ErrorDescription>>> sheetErrs = id.process(sheet, session, stopIfWrong, writeOff, org, readers);

					if (sheetErrs.size() > 0) {
						uf.setStatus(UploadedFile.CHECKING_ERROR);
						uf.setLocalizedMsg(getLocalizedWord("file_data_is_incorrect", lang));
						uf.setSheetErrs(sheetErrs);
					} else {
						uf.setStatus(UploadedFile.CHECKED);
					}
				} else {
					uf.setStatus(UploadedFile.CHECKING_ERROR);
					uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
					return;
				}
			}
		} catch (Exception e) {
			setBadRequest();
			error(e);
		}
	}
}
