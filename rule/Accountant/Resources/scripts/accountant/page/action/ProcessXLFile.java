package accountant.page.action;

import java.io.File;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;

import com.exponentus.env.EnvConst;
import com.exponentus.env.Environment;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._Exception;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.user.IUser;

import accountant.page.form.ImportFileEntry;
import accountant.page.form.UpdateWizardForm;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class ProcessXLFile extends _DoPage {

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
			logError(e);
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
			session.removeAttribute(UpdateWizardForm.getSesAttrName());
			session.removeAttribute(fsid);
			IUser<Long> user = session.getUser();
			File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());

			File xlsFile = new File(userTmpDir + File.separator + fn);
			if (xlsFile.exists()) {
				xlsFile.delete();
			}
		} catch (Exception e) {
			setBadRequest();
			logError(e);
		}
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

		devPrint(formData);
		LanguageCode lang = session.getLang();
		try {
			OrganizationDAO dao = new OrganizationDAO(session);
			
			String uo = formData.getValueSilently("uploadtype");
			
			String fsid = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
			if (!fsid.isEmpty()) {
				String fn = formData.getValueSilently("fileid");
				ImportFileEntry uf = (ImportFileEntry) session.getAttribute(UpdateWizardForm.getSesAttrName());
				uf.setLocalizedMsg("");
				if (uf.geSheetErrs() != null && uf.geSheetErrs().size() > 0) {
					return;
				}
				if (uf != null) {
					IUser<Long> user = session.getUser();
					File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
					String fileName = userTmpDir + File.separator + fn;
					String ext = FilenameUtils.getExtension(fileName);
					Organization org = null;
					String[] readers = null;
					String addFileName = "";
					if (ext.equalsIgnoreCase("xls")) {
						if (uo.equals("transfer")) {
							try {
								UUID bhId = UUID.fromString(formData.getValueSilently("recipient"));
								org = dao.findById(bhId);
								uf.setRecipient(org);
								String ofn = uf.getOrderFileName();
								if (ofn.equals("")) {
									ofn = UpdateWizardForm.getFileNameByType(session, fsid, "uporder", uf);
									uf.setOrderFileName(ofn);
								}
								addFileName = userTmpDir + File.separator + ofn;
							} catch (IllegalArgumentException e) {
								uf.setStatus(ImportFileEntry.LOADING_ERROR);
								uf.setLocalizedMsg(getLocalizedWord("incorrect_balanceholder_org_field", lang));
								addContent(uf);
								return;
							}
							try {
								readers = formData.getListOfValues("readers");
							} catch (_Exception e) {
								uf.setStatus(ImportFileEntry.LOADING_ERROR);
								uf.setLocalizedMsg(getLocalizedWord("readers_has_not_been_pointed", lang));
								addContent(uf);
								return;
							}
						} else if (uo.equals("upload")) {
							try {
								UUID bhId = UUID.fromString(formData.getValueSilently("balanceholder"));
								org = dao.findById(bhId);
								uf.setBalanceHolder(org);
							} catch (IllegalArgumentException e) {
								uf.setStatus(ImportFileEntry.LOADING_ERROR);
								uf.setLocalizedMsg(getLocalizedWord("incorrect_balanceholder_org_field", lang));
								addContent(uf);
								return;
							}
							try {
								readers = formData.getListOfValues("readers");
							} catch (_Exception e) {
								uf.setStatus(ImportFileEntry.LOADING_ERROR);
								uf.setLocalizedMsg(getLocalizedWord("readers_has_not_been_pointed", lang));
								addContent(uf);
								return;
							}
						}

						File xlsFile = new File(fileName);
						XLImporter id = new XLImporter(XLImporter.PROCESS);
						Workbook workbook = null;
						try {
							workbook = Workbook.getWorkbook(xlsFile);
						} catch (BiffException e) {
							uf.setStatus(ImportFileEntry.LOADING_ERROR);
							uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
							return;
						}
						Sheet sheet = workbook.getSheet(0);
						Outcome result = id.process(sheet, session, true, org, readers, uo, addFileName);

						if (result.sheetErr.size() > 0) {
							uf.setStatus(ImportFileEntry.LOADING_ERROR);
							uf.setLocalizedMsg(getLocalizedWord("file_has_been_not_loaded", lang));
							uf.setSheetErrs(result.sheetErr);
						} else {
							uf.setStatus(ImportFileEntry.LOADED);
							uf.setLocalizedMsg(getLocalizedWord("data_has_been_loaded_succesfully", lang)
									+ ", обработано записей: " + Integer.toString(result.processed) + "("
									+ Integer.toString(result.skipped) + ")");
						}
					} else {
						uf.setStatus(ImportFileEntry.LOADING_ERROR);
						uf.setLocalizedMsg(getLocalizedWord("incorrect_xls_file", lang));
					}
				}
			}
		} catch (Exception e) {
			logError(e);
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
