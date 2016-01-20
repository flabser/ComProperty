package staff.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import staff.dao.DepartmentDAO;
import staff.dao.EmployeeDAO;
import staff.dao.OrganizationDAO;
import staff.dao.RoleDAO;
import staff.exception.EmployеeException;
import staff.model.Employee;
import staff.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Kayra created 07-01-2016
 */

public class EmployeeForm extends StaffForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Employee entity;
        if (!id.isEmpty()) {
            EmployeeDAO dao = new EmployeeDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Employee();
            entity.setAuthor(user);
        }
        setContent(getSimpleActionBar(session, lang));
        setContent(new _POJOObjectWrapper(entity));
        setContent(new _POJOListWrapper(new RoleDAO(session).findAll()));
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {
        try {
            boolean v = validate(formData);
            if (v == false) {
                setBadRequest();
                return;
            }

            boolean isNew = false;
            String id = formData.getValueSilently("docid");
            OrganizationDAO orgDAO = new OrganizationDAO(session);
            DepartmentDAO depDAO = new DepartmentDAO(session);
            RoleDAO roleDAO = new RoleDAO(session);
            EmployeeDAO dao = new EmployeeDAO(session);
            Employee entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Employee();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new Employee();
                }
            }

            entity.setName(formData.getValueSilently("name"));
            entity.setLogin(formData.getValueSilently("login"));
            entity.setOrganization(orgDAO.findById(UUID.fromString(formData.getValue("organization_id"))));
            entity.setDepartment(depDAO.findById(UUID.fromString(formData.getValue("department_id"))));

            String[] roles = formData.getListOfValuesSilently("role");
            List<Role> roleList = new ArrayList<>();
            for (String roleId : roles) {
                Role role = roleDAO.findById(UUID.fromString(roleId));
                roleList.add(role);
            }
            if (!roleList.isEmpty()) {
                entity.setRoles(roleList);
            }

            if (isNew) {
                dao.add(entity);
            } else {
                dao.update(entity);
            }

            _URL returnURL = session.getURLOfLastPage();
            localizedMsgBox(getLocalizedWord("document_was_saved_succesfully", lang));
            setRedirectURL(returnURL);
        } catch (_Exception e) {
            setBadRequest();
            log(e);
        } catch (EmployеeException e) {
            setBadRequest();
            log(e);
        }
    }
}
