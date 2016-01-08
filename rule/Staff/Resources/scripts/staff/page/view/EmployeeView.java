package staff.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.EmployeeDAO;

/**
 * @author Kayra created 07-01-2016
 */

public class EmployeeView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		EmployeeDAO dao = new EmployeeDAO(session);

		setContent(getSimpleActionBar(session, "employee", lang));
		setContent(getViewPage(dao, formData));

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
