package staff.page.form;

import administrator.dao.ApplicationDAO;
import administrator.dao.UserDAO;
import administrator.model.Application;
import administrator.model.User;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Validator;
import reference.dao.PositionDAO;
import reference.model.Position;
import staff.dao.DepartmentDAO;
import staff.dao.EmployeeDAO;
import staff.dao.OrganizationDAO;
import staff.dao.RoleDAO;
import staff.model.Employee;
import staff.model.Organization;
import staff.model.Role;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author Kayra created 07-01-2016
 */

public class EmployeeForm extends StaffForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");
        IUser<Long> user = session.getUser();
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
        List<Application> apps = new ApplicationDAO(session).findAll();
        addContent(new _POJOListWrapper(apps, session));
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

            String id = formData.getValueSilently("docid");
            EmployeeDAO dao = new EmployeeDAO(session);
            Employee entity;
            boolean isNew = id.isEmpty();

            if (isNew) {
                entity = new Employee();
            } else {
                entity = dao.findById(UUID.fromString(id));
            }

            entity.setName(formData.getValue("name"));
            entity.setIin(formData.getValue("iin"));
            OrganizationDAO orgDAO = new OrganizationDAO(session);
            entity.setOrganization(orgDAO.findById(UUID.fromString(formData.getValue("organizationid"))));
            String di = formData.getValueSilently("departmentid");
            if (!di.isEmpty()) {
                DepartmentDAO depDAO = new DepartmentDAO(session);
                entity.setDepartment(depDAO.findById(UUID.fromString(di)));
            }
            PositionDAO posDAO = new PositionDAO(session);
            entity.setPosition(posDAO.findById(UUID.fromString(formData.getValue("positionid"))));
            String[] roles = formData.getListOfValuesSilently("role");
            if (roles != null) {
                RoleDAO roleDAO = new RoleDAO(session);
                List<Role> roleList = new ArrayList<>();
                for (String roleId : roles) {
                    if (!roleId.isEmpty()) {
                        Role role = roleDAO.findById(UUID.fromString(roleId));
                        roleList.add(role);
                    }
                }
                if (!roleList.isEmpty()) {
                    entity.setRoles(roleList);
                }
            }

            String reguser = formData.getValueSilently("reguser");
            if ("on".equals(reguser)) {
                String login = formData.getValueSilently("login");
                UserDAO uDao = new UserDAO();
                User user = (User) uDao.findByLogin(login);
                if (user == null) {
                    user = new User();
                }
                user.setEmail(formData.getValueSilently("email"));
                user.setLogin(login);
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

        if (formData.getValueSilently("name").isEmpty()) {
            ve.addError("name", "required", getLocalizedWord("required", lang));
        }
        if (formData.getValueSilently("iin").isEmpty()) {
            ve.addError("iin", "required", getLocalizedWord("required", lang));
        }
        if (formData.getValueSilently("organizationid").isEmpty()) {
            ve.addError("organizationid", "required", getLocalizedWord("required", lang));
        }
        if (formData.getValueSilently("positionid").isEmpty()) {
            ve.addError("positionid", "required", getLocalizedWord("required", lang));
        }

        String regUser = formData.getValueSilently("reguser");
        if ("on".equals(regUser)) {
            if (formData.getValueSilently("login").isEmpty()) {
                ve.addError("login", "required", getLocalizedWord("required", lang));
            }
            if (formData.getValueSilently("email").isEmpty() || !_Validator.checkEmail(formData.getValueSilently("email"))) {
                ve.addError("email", "email", getLocalizedWord("email_invalid", lang));
            }
            if (!formData.getValueSilently("pwd").isEmpty()) {
                if (formData.getValueSilently("pwd_confirm").isEmpty()) {
                    ve.addError("pwd_confirm", "required", getLocalizedWord("required", lang));
                } else if (!formData.getValueSilently("pwd").equals(formData.getValueSilently("pwd_confirm"))) {
                    ve.addError("pwd_confirm", "required", getLocalizedWord("password_confirm_not_equals", lang));
                }
            } else {
                ve.addError("pwd", "required", getLocalizedWord("required", lang));
            }
        }

        return ve;
    }
}
