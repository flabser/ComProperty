package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.PositionDAO;
import reference.model.Position;

import java.util.UUID;


public class PositionForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Position entity = null;
        if (!id.isEmpty()) {
            PositionDAO dao = new PositionDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Position();
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
            PositionDAO dao = new PositionDAO(session);
            Position entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Position();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new Position();
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
