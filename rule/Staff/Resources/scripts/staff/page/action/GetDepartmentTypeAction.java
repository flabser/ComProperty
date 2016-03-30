package staff.page.action;

import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import reference.dao.DepartmentTypeDAO;
import reference.model.DepartmentType;

import java.util.List;


public class GetDepartmentTypeAction extends _DoPage {

    @Override
    public void doGET(_Session ses, _WebFormData formData) {
        DepartmentTypeDAO dao = new DepartmentTypeDAO(ses);
        List<DepartmentType> list = dao.findAll();
        addContent(new _POJOListWrapper(list, ses));
    }
}
