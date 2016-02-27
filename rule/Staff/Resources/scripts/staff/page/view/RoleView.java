package staff.page.view;

import java.util.List;
import java.util.UUID;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import staff.dao.RoleDAO;
import staff.model.Employee;
import staff.model.Role;

/**
 * @author Kayra created 08-01-2016
 */

public class RoleView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		RoleDAO dao = new RoleDAO(session);
		String id = formData.getValueSilently("docid");
		if (!id.isEmpty()) {
			Role role = dao.findById(UUID.fromString(id));
			List<Employee> emps = role.getEmployees();
			addContent(new _POJOListWrapper(emps, session));
		} else {
			_ActionBar actionBar = new _ActionBar(session);
			_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_role");
			newDocAction.setURL("Provider?id=role-form");
			actionBar.addAction(newDocAction);
			actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));

			addContent(actionBar);
			addContent(getViewPage(dao, formData));
		}
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData, LanguageCode lang) {
		println(formData);

		RoleDAO dao = new RoleDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			Role m = dao.findById(UUID.fromString(id));
			dao.delete(m);
		}
	}
}
