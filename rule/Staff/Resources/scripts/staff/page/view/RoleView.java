package staff.page.view;

import java.util.List;
import java.util.UUID;

import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.RoleDAO;
import staff.model.Employee;
import staff.model.Role;

/**
 * @author Kayra created 08-01-2016
 */

public class RoleView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		RoleDAO dao = new RoleDAO(session);
		String id = formData.getValueSilently("docid");
		if (!id.equals("")) {
			Role role = dao.findById(UUID.fromString(id));
			List<Employee> emps = role.getEmployees();
			setContent(new _POJOListWrapper(emps));
		} else {
			setContent(getSimpleActionBar(session, "role", lang));
			setContent(getViewPage(dao, formData));
		}
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
