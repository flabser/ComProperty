package accountant.page.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import kz.flabs.users.User;
import kz.flabs.util.Util;
import kz.nextbase.script._Session;
import kz.nextbase.script._Tag;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script._XMLDocument;
import kz.nextbase.script.events._DoPage;
import kz.pchelka.env.Environment;
import staff.dao.OrganizationDAO;
import staff.model.Organization;
import accountant.page.action.MPXLImporter.ErrorDescription;

public class ImportFileChecker extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		User user = session.getUser();
		File userTmpDir = new File(Environment.tmpDir + File.separator + user.getUserID());
		try {
			File xlsFile = new File(userTmpDir + File.separator + formData.getEncodedValueSilently("fileid"));

			_Tag rootTag = new _Tag("result");

			MPXLImporter id = new MPXLImporter(MPXLImporter.CHECK);

			Workbook workbook = Workbook.getWorkbook(xlsFile);
			Sheet sheet = workbook.getSheet(0);

			OrganizationDAO oDao = new OrganizationDAO(ses);
			List<Organization> oList = oDao.findAll();
			Organization org = (Organization) Util.getRndListElement(oList);

			Map<Integer, List<List<ErrorDescription>>> sheetErrs = id.process(sheet, ses, false, org);
			for (Entry<Integer, List<List<ErrorDescription>>> row : sheetErrs.entrySet()) {
				_Tag entry = rootTag.addTag("entry");
				entry.setAttr("row", row.getKey());
				for (List<ErrorDescription> colList : row.getValue()) {
					for (ErrorDescription val : colList) {
						entry.addTag("column", val.toString());
					}
				}
			}

			_XMLDocument xml = new _XMLDocument(rootTag);
			setContent(xml);
		} catch (BiffException | IOException e) {
			setBadRequest();
			error(e);
		}
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
