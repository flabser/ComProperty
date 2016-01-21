package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.PropertyCodeDAO;
import reference.model.PropertyCode;

import java.util.UUID;


public class PropertyCodeForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        PropertyCode entity;
        if (!id.isEmpty()) {
            PropertyCodeDAO dao = new PropertyCodeDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new PropertyCode();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity));
        setContent(getSimpleActionBar(session, lang));
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
            PropertyCodeDAO dao = new PropertyCodeDAO(session);
            PropertyCode entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new PropertyCode();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new PropertyCode();
                }
            }

            entity.setName(formData.getValue("name"));

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
