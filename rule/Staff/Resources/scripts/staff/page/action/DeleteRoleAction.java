package staff.page.action;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.EmployeeDAO;

/**
 * 
 * 
 * @author Kayra created 09-01-2016
 */

public class DeleteRoleAction extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData, LanguageType lang) {
		String id = webFormData.getValueSilently("docid");
		EmployeeDAO dao = new EmployeeDAO(session);
		dao.delete(dao.findById(UUID.fromString(id)));
		addContent("msg", getLocalizedWord("document_was_deleted_succesfully", lang));
	}

}
