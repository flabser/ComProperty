package staff.page.action;

import java.util.UUID;

import kz.nextbase.script._Exception;
import kz.nextbase.script._Session;
import kz.nextbase.script._URL;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.EmployeeDAO;

/**
 * 
 * 
 * @author Kayra created 09-01-2016
 */

public class DeleteEmployeeAction extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData, String lang) {
		try {
			String id = webFormData.getValueSilently("docid");
			EmployeeDAO dao = new EmployeeDAO(session);
			dao.delete(dao.findById(UUID.fromString(id)));
			_URL returnURL = session.getURLOfLastPage();
			localizedMsgBox(getLocalizedWord("document_was_deleted_succesfully", lang));
			setRedirectURL(returnURL);
		}catch (_Exception e){
			log(e);
		}
	}

}