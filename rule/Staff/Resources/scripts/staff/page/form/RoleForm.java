package staff.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import staff.dao.RoleDAO;
import staff.model.Role;

import java.util.UUID;

/**
 * @author Kayra created 10-01-2016
 */

public class RoleForm extends StaffForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Role entity;
        if (!id.isEmpty()) {
            RoleDAO dao = new RoleDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Role();
            entity.setAuthor(user);
        }
        setContent(getSimpleActionBar(session, lang));
        setContent(new _POJOObjectWrapper(entity));
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
            RoleDAO dao = new RoleDAO(session);
            Role entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Role();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new Role();
                }
            }

            entity.setName(formData.getValueSilently("name"));
            entity.setDescription(formData.getValueSilently("description"));

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
