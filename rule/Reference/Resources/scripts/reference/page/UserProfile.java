package reference.page;

import kz.flabs.users.User;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import staff.dao.EmployeeDAO;
import staff.model.Employee;


public class UserProfile extends _DoPage {

    @Override
    public void doGET(_Session ses, _WebFormData webFormData, String lang) {
        User user = ses.getUser();
        EmployeeDAO dao = new EmployeeDAO(ses);
        Employee emp = dao.findByLogin(user.getUserID());
        setContent(new _ActionBar(ses).addAction(new _Action(_ActionType.CLOSE)));
        setContent(emp);
    }

    @Override
    public void doPOST(_Session session, _WebFormData webFormData, String lang) {

    }
}