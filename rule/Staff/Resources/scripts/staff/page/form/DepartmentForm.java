package staff.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import staff.dao.DepartmentDAO;
import staff.dao.RoleDAO;
import staff.model.Department;
import staff.model.constants.DepartmentType;

import java.util.UUID;

/**
 * @author Kayra created 07-01-2016
 */

public class DepartmentForm extends StaffForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Department entity;
        if (!id.isEmpty()) {
            DepartmentDAO dao = new DepartmentDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Department();
            entity.setAuthor(user);
        }
        setContent(getSimpleActionBar(session, lang));
        setContent(new _POJOObjectWrapper(entity));
        setContent(new _EnumWrapper<>(DepartmentType.class.getEnumConstants()));
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
            DepartmentDAO dao = new DepartmentDAO(session);
            Department entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Department();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new Department();
                }
            }

            entity.setName(formData.getValueSilently("name"));
            entity.setType(DepartmentType.valueOf(formData.getValueSilently("type", "UNKNOWN")));

            if (isNew) {
                dao.add(entity);
            } else {
                dao.update(entity);
            }

            _URL returnURL = session.getURLOfLastPage();
            localizedMsgBox(getLocalizedWord("document_was_saved_succesfully", lang));
            setRedirectURL(returnURL);
        } catch (_Exception e) {
            log(e);
        }
    }
}
