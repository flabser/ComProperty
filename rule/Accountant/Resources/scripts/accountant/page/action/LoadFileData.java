package accountant.page.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import kz.pchelka.env.Environment;

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
		User user = session.getUser();
		File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
		try {
			String fileName = userTmpDir + File.separator + formData.getEncodedValueSilently("fileid");
			String ext = FilenameUtils.getExtension(fileName);
			if (ext.equalsIgnoreCase("xls")) {
				try {
					UUID bhId = UUID.fromString(formData.getValueSilently("balanceholder"));
					OrganizationDAO dao = new OrganizationDAO(session);
					Organization org = dao.findById(bhId);
					File xlsFile = new File(fileName);
					MPXLImporter id = new MPXLImporter(MPXLImporter.LOAD);
					Workbook workbook = Workbook.getWorkbook(xlsFile);
					Sheet sheet = workbook.getSheet(0);
					Map<Integer, List<List<ErrorDescription>>> sheetErrs = id.process(sheet, ses, true, org);
					if (sheetErrs == null) {
						setBadRequest();
					}
				} catch (IllegalArgumentException e) {
					setBadRequest();
				}
			} else {
				setBadRequest();
			}

		} catch (BiffException | IOException e) {
			error(e);
			setBadRequest();
		}
	}
}
