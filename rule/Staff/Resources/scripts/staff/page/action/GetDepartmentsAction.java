package staff.page.action;

import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import staff.dao.OrganizationDAO;
import staff.model.Department;
import staff.model.Organization;

import java.util.List;
import java.util.UUID;


public class GetDepartmentsAction extends _DoPage {

    @Override
    public void doGET(_Session ses, _WebFormData formData) {
        String orgId = formData.getValueSilently("organization");
        OrganizationDAO orgDao = new OrganizationDAO(ses);
        Organization org = orgDao.findById(UUID.fromString(orgId));
        List<Department> deps = org.getDepartments();
        addContent(new _POJOListWrapper(deps, ses));
    }
}
