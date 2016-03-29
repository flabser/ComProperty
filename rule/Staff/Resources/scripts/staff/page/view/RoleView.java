package staff.page.view;

import java.util.List;
import java.util.UUID;

import kz.lof.exception.SecureException;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.lof.user.SuperUser;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import staff.dao.RoleDAO;
import staff.model.Employee;
import staff.model.Role;

/**
 * @author Kayra created 08-01-2016
 */

public class RoleView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		RoleDAO dao = new RoleDAO(session);
		String id = formData.getValueSilently("categoryid");
		if (!id.isEmpty()) {
			Role role = dao.findById(UUID.fromString(id));
			List<Employee> emps = role.getEmployees();
			addContent(new _POJOListWrapper(emps, session));
		} else {
			IUser<Long> user = session.getUser();
			if (user.getId() == SuperUser.ID || user.getRoles().contains("staff_admin")) {
				_ActionBar actionBar = new _ActionBar(session);
				_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_role");
				newDocAction.setURL("Provider?id=role-form");
				actionBar.addAction(newDocAction);
				actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));
				addContent(actionBar);
			}
			addContent(getViewPage(dao, formData));
		}
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		println(formData);

		RoleDAO dao = new RoleDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			Role m = dao.findById(UUID.fromString(id));
			try {
				dao.delete(m);
			} catch (SecureException e) {
				setError(e);
			}
		}
	}
}
