package staff.page.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._Exception;
import reference.dao.PositionDAO;
import reference.model.Position;
import staff.dao.DepartmentDAO;
import staff.dao.EmployeeDAO;
import staff.dao.OrganizationDAO;
import staff.dao.RoleDAO;
import staff.model.Employee;
import staff.model.Organization;
import staff.model.Role;
import administrator.model.User;

/**
 * @author Kayra created 07-01-2016
 */

public class EmployeeForm extends StaffForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser user = session.getUser();
		Employee entity;
		if (!id.isEmpty()) {
			EmployeeDAO dao = new EmployeeDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Employee();
			entity.setRegDate(new Date());
			entity.setAuthor(user);
			entity.setName("");
			Organization tmpOrg = new Organization();
			tmpOrg.setName("");
			entity.setOrganization(tmpOrg);
			Position tmpPos = new Position();
			tmpPos.setName("");
			entity.setPosition(tmpPos);
			entity.setRoles(new ArrayList<Role>());
		}
		addContent(entity);
		addContent(new _POJOListWrapper(new OrganizationDAO(session).findAll(), session));
		addContent(new _POJOListWrapper(new DepartmentDAO(session).findAll(), session));
		addContent(new _POJOListWrapper(new PositionDAO(session).findAll(), session));
		addContent(new _POJOListWrapper(new RoleDAO(session).findAll(), session));
		addContent(getSimpleActionBar(session, session.getLang()));
		startSaveFormTransact(entity);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {
		println(formData);
		try {
			_Validation ve = validate(formData, session.getLang());
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
			entity.setOrganization(orgDAO.findById(UUID.fromString(formData.getValue("organizationid"))));
			String di = formData.getValue("departmentid");
			if (!di.isEmpty()) {
				entity.setDepartment(depDAO.findById(UUID.fromString(di)));
			}

			String[] roles = formData.getListOfValuesSilently("role");
			if (roles != null) {
				List<Role> roleList = new ArrayList<>();
				for (String roleId : roles) {
					Role role = roleDAO.findById(UUID.fromString(roleId));
					roleList.add(role);
				}
				if (!roleList.isEmpty()) {
					entity.setRoles(roleList);
				}
			}

			String reguser = formData.getValueSilently("reguser");
			if (reguser.isEmpty() && reguser.equals("1")) {
				User user = new User();
				user.setEmail(formData.getValueSilently("email"));
				user.setLogin(formData.getValueSilently("login"));
				user.setPwd(formData.getValueSilently("pwd"));
				entity.setUser(user);
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
		}
	}

	@Override
	protected _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();

		if (formData.getValueSilently("organizationid").isEmpty()) {
			ve.addError("organizationid", "empty", getLocalizedWord("required", lang));
		}

		if (formData.getValueSilently("name").isEmpty()) {
			ve.addError("name", "empty", getLocalizedWord("required", lang));
		}

		String reguser = formData.getValueSilently("reguser");
		if (reguser.isEmpty() && reguser.equals("1")) {
			if (formData.getValueSilently("email").isEmpty()) {
				ve.addError("email", "empty", getLocalizedWord("required", lang));
			}
			if (formData.getValueSilently("login").isEmpty()) {
				ve.addError("login", "empty", getLocalizedWord("required", lang));
			}
			if (formData.getValueSilently("pwd").isEmpty()) {
				ve.addError("pwd", "empty", getLocalizedWord("required", lang));
			}
		}

		return ve;
	}
}
