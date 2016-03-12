package staff.page;

import administrator.dao.LanguageDAO;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import staff.dao.EmployeeDAO;
import staff.model.Employee;

/**
 * @author Kayra created 05-01-2016
 */

public class UserProfile extends _DoPage {

    @Override
    public void doGET(_Session ses, _WebFormData formData) {
        IUser user = ses.getUser();
        EmployeeDAO dao = new EmployeeDAO(ses);
        Employee emp = dao.findByUserId((long) user.getId());
        _ActionBar actionBar = new _ActionBar(ses);
        actionBar.addAction(new _Action(getLocalizedWord("save_close", ses.getLang()), "", _ActionType.SAVE_AND_CLOSE));
        actionBar.addAction(new _Action(getLocalizedWord("close", ses.getLang()), "", _ActionType.CLOSE));
        addContent(emp);
        addContent(actionBar);
        addContent(new _POJOListWrapper(new LanguageDAO(ses).findAll(), ses));
        addContent("pagesize", String.valueOf(ses.getPageSize()));
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData) {
        println(formData);
    }
}
