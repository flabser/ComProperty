package administrator.page.view;

import administrator.dao.ApplicationDAO;
import kz.lof.scripting.IPOJOObject;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;

import java.util.List;


public class ApplicationView extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        ApplicationDAO dao = new ApplicationDAO(session);
        List<? extends IPOJOObject> list = dao.findAll();
        addContent(new _POJOListWrapper(list, 0, dao.getCount(), 0, session));
    }
}
