package accountant.page.action;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.lof.env.Environment;
import kz.nextbase.script._Exception;
import kz.lof.scripting._Session;
import kz.nextbase.script._Validation;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;

import org.apache.commons.io.FilenameUtils;

import staff.dao.OrganizationDAO;
import staff.model.Organization;
import accountant.page.action.MPXLImporter.ErrorDescription;

public class LoadFileData extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {
		println(formData);
		User user = session.getUser();
		File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
		try {
			// String fileName = userTmpDir + File.separator +
			// formData.getEncodedValueSilently("fileid");
			String fileName = userTmpDir + File.separator + formData.getValueSilently("fileid");
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
						setValidation(getLocalizedWord("incorrect_xls_file", lang));
						setBadRequest();
						error(e);
						return;
					}
					Sheet sheet = workbook.getSheet(0);
					Map<Integer, List<List<ErrorDescription>>> sheetErrs = id.process(sheet, ses, true, org, readers);
					if (sheetErrs == null) {
						setError(getLocalizedWord("internal_error", lang));
					}
				} catch (IllegalArgumentException e) {
					_Validation ve = new _Validation();
					ve.addError("balanceholderid", "empty", getLocalizedWord("incorrect_balanceholder_org_field", lang));
					setValidation(ve);
				} catch (_Exception e) {
					_Validation ve = new _Validation();
					ve.addError("reader", "empty", getLocalizedWord("readers_has_not_been_pointed", lang));
					setValidation(ve);
				}
			} else {
				setValidation(getLocalizedWord("incorrect_xls_file", lang));
			}

		} catch (Exception e) {
			error(e);
			setBadRequest();
		}
	}
}
