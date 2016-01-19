package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.StructureTypeDAO;
import reference.model.StructureType;

import java.util.UUID;


public class StructureTypeForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        StructureType entity = null;
        if (!id.isEmpty()) {
            StructureTypeDAO dao = new StructureTypeDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new StructureType();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity));
    }

    @Override
    public void doPOST(_Session session, _WebFormData webFormData, String lang) {
        println(webFormData);
        try {
            boolean v = validate(webFormData);
            if (v == false) {
                setBadRequest();
                return;
            }

            boolean isNew = false;
            String id = webFormData.getValueSilently("docid");
            StructureTypeDAO dao = new StructureTypeDAO(session);
            StructureType entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new StructureType();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new StructureType();
                }
            }

            entity.setName(webFormData.getValueSilently("name"));

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
