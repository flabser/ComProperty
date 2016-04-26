package accountant.page.action;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

import accountant.page.form.UploadedFile;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.exponentus.env.EnvConst;
import com.exponentus.env.Environment;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._Exception;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.user.IUser;

import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class UpdateFile extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {

		String fn = formData.getValueSilently("fileid");
		if (!validateFileName(fn)) {
			return;
		}

		IUser<Long> user = session.getUser();
		File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
		try {
			File xlsFile = new File(userTmpDir + File.separator + formData.getValue("fileid"));
			if (xlsFile.exists()) {
				showFile(xlsFile.getAbsolutePath(), fn);
			}
		} catch (_Exception e) {
			error(e);
		}
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {

		String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
		String fn = formData.getValueSilently("fileid");
		if (fsid.isEmpty() || !validateFileName(fn)) {
			return;
		}

		try {
			session.removeAttribute(fsid + "_file" + fn);
			session.removeAttribute(fsid);
			IUser<Long> user = session.getUser();
			File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());

			File xlsFile = new File(userTmpDir + File.separator + fn);
			if (xlsFile.exists()) {
				xlsFile.delete();
			}
		} catch (Exception e) {
			setBadRequest();
			error(e);
		}
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {
		boolean writeOff = false;
		devPrint(formData);
		LanguageCode lang = session.getLang();

		String wo = formData.getValueSilently("writeoff");
		if (wo.equals("1")) {
			writeOff = true;
		}

		try {
			String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
			if (!fsid.isEmpty()) {
				String fn = formData.getValueSilently("fileid");
				UploadedFile uf = (UploadedFile) session.getAttribute(fsid + "_file" + fn);
				if (uf.geSheetErrs() != null && uf.geSheetErrs().size() > 0) {
					return;
				}
				if (uf != null) {
					IUser<Long> user = session.getUser();
					File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
					String fileName = userTmpDir + File.separator + fn;
					String ext = FilenameUtils.getExtension(fileName);
					Organization org = null;
					if (ext.equalsIgnoreCase("xls")) {
						try {
							UUID bhId = UUID.fromString(formData.getValueSilently("balanceholder"));
							OrganizationDAO dao = new OrganizationDAO(session);
							org = dao.findById(bhId);
							String[] readers = formData.getListOfValues("reader");
							File xlsFile = new File(fileName);
							MPXLImporter id = new MPXLImporter(MPXLImporter.LOAD);
							Workbook workbook = null;
							try {
								workbook = Workbook.getWorkbook(xlsFile);
							} catch (BiffException e) {
								uf.setStatus(UploadedFile.LOADING_ERROR);
								uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
								return;
							}
							Sheet sheet = workbook.getSheet(0);
							Map<Integer, List<List<MPXLImporter.ErrorDescription>>> sheetErrs = id.process(sheet, session, true, writeOff, org,
							        readers);

							if (sheetErrs.size() > 0) {
								uf.setStatus(UploadedFile.LOADING_ERROR);
								uf.setLocalizedMsg(getLocalizedWord("file_has_been_not_loaded", lang));
								uf.setSheetErrs(sheetErrs);
							} else {
								uf.setStatus(UploadedFile.LOADED);
								uf.setLocalizedMsg(getLocalizedWord("data_has_been_loaded_succesfully", lang));
							}
						} catch (IllegalArgumentException e) {
							uf.setStatus(UploadedFile.LOADING_ERROR);
							uf.setLocalizedMsg(getLocalizedWord("incorrect_balanceholder_org_field", lang));
						} catch (_Exception e) {
							uf.setStatus(UploadedFile.LOADING_ERROR);
							uf.setLocalizedMsg(getLocalizedWord("readers_has_not_been_pointed", lang));
						}
					} else {
						uf.setStatus(UploadedFile.LOADING_ERROR);
						uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
					}
				}
			}
		} catch (Exception e) {
			error(e);
			setBadRequest();
		}
	}

	private boolean validateFileName(String fn) {
		if (fn.isEmpty()) {
			return false;
		} else if (fn.indexOf('/') > -1 || fn.indexOf('\\') > -1) {
			return false;
		}

		return true;
	}
}
