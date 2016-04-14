package registry.page.view;

import java.util.UUID;

import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.event._DoPage;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import staff.dao.EmployeeDAO;
import staff.model.Employee;

/**
 * @author Kayra created 07-01-2016
 */

public class ResponsiblePersonView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		_ActionBar actionBar = new _ActionBar(session);
		_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_employee");
		newDocAction.setURL("Provider?id=employee-form");
		actionBar.addAction(newDocAction);
		actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

		addContent(actionBar);
		addContent(getViewPage(new EmployeeDAO(session), formData));
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		println(formData);

		EmployeeDAO dao = new EmployeeDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			Employee m = dao.findById(UUID.fromString(id));
			try {
				dao.delete(m);
			} catch (SecureException e) {
				setError(e);
			}
		}
	}
}
