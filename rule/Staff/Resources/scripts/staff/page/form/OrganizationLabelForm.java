package staff.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import staff.dao.OrganizationLabelDAO;
import staff.model.OrganizationLabel;

import java.util.UUID;

/**
 * @author Kayra created 10-01-2016
 */

public class OrganizationLabelForm extends StaffForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        OrganizationLabel entity;
        if (!id.isEmpty()) {
            OrganizationLabelDAO dao = new OrganizationLabelDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new OrganizationLabel();
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
            OrganizationLabelDAO dao = new OrganizationLabelDAO(session);
            OrganizationLabel entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new OrganizationLabel();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new OrganizationLabel();
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
