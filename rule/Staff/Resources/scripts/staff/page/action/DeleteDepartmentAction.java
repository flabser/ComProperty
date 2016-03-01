package staff.page.action;

import java.util.UUID;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.DepartmentDAO;

/**
 * 
 * 
 * @author Kayra created 09-01-2016
 */

public class DeleteDepartmentAction extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData) {
		LanguageCode lang = session.getLang();
		String id = webFormData.getValueSilently("docid");
		DepartmentDAO dao = new DepartmentDAO(session);
		dao.delete(dao.findById(UUID.fromString(id)));
		addContent("msg", getLocalizedWord("document_was_deleted_succesfully", lang));
	}

}
