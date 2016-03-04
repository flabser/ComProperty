package staff.page;

import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import staff.dao.EmployeeDAO;
import staff.model.Employee;
import administrator.dao.LanguageDAO;

/**
 * @author Kayra created 05-01-2016
 */

public class UserProfile extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData webFormData) {
		IUser user = ses.getUser();
		EmployeeDAO dao = new EmployeeDAO(ses);
		Employee emp = dao.findByLogin(user.getUserID());
		addContent(new _ActionBar(ses).addAction(new _Action(_ActionType.CLOSE)));
		addContent(emp);
		addContent(new _POJOListWrapper(new LanguageDAO(ses).findAll(), ses));
		String pagesize = String.valueOf(ses.getPageSize());
		addContent("pagesize", pagesize);

	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData) {

	}
}
