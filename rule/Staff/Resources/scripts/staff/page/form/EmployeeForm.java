package staff.page.form;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kz.flabs.localization.LanguageCode;
import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.nextbase.script._Exception;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import staff.dao.DepartmentDAO;
import staff.dao.EmployeeDAO;
import staff.dao.OrganizationDAO;
import staff.dao.RoleDAO;
import staff.exception.EmployeeException;
import staff.model.Employee;
import staff.model.Role;

/**
 * @author Kayra created 07-01-2016
 */

public class EmployeeForm extends StaffForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
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
		addContent(entity);
		addContent(new _POJOListWrapper(new RoleDAO(session).findAll(), session));
		addContent(getSimpleActionBar(session, lang));
		startSaveFormTransact(entity);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {
		try {
			_Validation ve = validate(formData, lang);
			if (ve.hasError()) {
				setBadRequest();
				setValidation(ve);
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
			}

			entity.setName(formData.getValue("name"));
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

			finishSaveFormTransact(entity);
		} catch (_Exception e) {
			setBadRequest();
			error(e);
		} catch (EmployeeException e) {
			setBadRequest();
			error(e);
		}
	}
}
