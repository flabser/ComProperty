package staff.page.form;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import staff.dao.DepartmentDAO;
import staff.dao.OrganizationDAO;
import staff.model.Department;
import staff.model.constants.DepartmentType;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * @author Kayra created 07-01-2016
 */

public class DepartmentForm extends StaffForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");
        IUser<Long> user = session.getUser();
        Department entity;
        if (!id.isEmpty()) {
            DepartmentDAO dao = new DepartmentDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Department();
            entity.setRegDate(new Date());
            entity.setAuthor(user);
            entity.setName("");
        }
        addContent(entity);
        addContent(new _EnumWrapper<>(DepartmentType.class.getEnumConstants()));

        if (entity.getOrganization() != null && entity.getOrganization().getId() != null) {
            addContent(new _POJOListWrapper(Arrays.asList(entity.getOrganization()), session));
        }

        addContent(getSimpleActionBar(session, session.getLang()));
        startSaveFormTransact(entity);
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData) {
        try {
            _Validation ve = validate(formData, session.getLang());
            if (ve.hasError()) {
                setBadRequest();
                setValidation(ve);
                return;
            }

            String id = formData.getValueSilently("docid");
            DepartmentDAO dao = new DepartmentDAO(session);
            Department entity;
            boolean isNew = id.isEmpty();

            if (isNew) {
                entity = new Department();
            } else {
                entity = dao.findById(UUID.fromString(id));
            }

            entity.setName(formData.getValue("name"));
            entity.setType(DepartmentType.valueOf(formData.getValueSilently("type", "UNKNOWN")));
            OrganizationDAO orgDAO = new OrganizationDAO(session);
            entity.setOrganization(orgDAO.findById(UUID.fromString(formData.getValue("organizationid"))));

            if (isNew) {
                dao.add(entity);
            } else {
                dao.update(entity);
            }

            finishSaveFormTransact(entity);
        } catch (_Exception e) {
            error(e);
        }
    }

    @Override
    protected _Validation validate(_WebFormData formData, LanguageCode lang) {
        _Validation ve = new _Validation();

        if (formData.getValueSilently("name").isEmpty()) {
            ve.addError("name", "required", getLocalizedWord("required", lang));
        }
        if (formData.getValueSilently("organizationid").isEmpty()) {
            ve.addError("organizationid", "required", getLocalizedWord("required", lang));
        }

        return ve;
    }
}
