package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.LocalityDAO;
import reference.dao.StreetDAO;
import reference.model.Street;

import java.util.UUID;


public class StreetForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Street entity = null;
        if (!id.isEmpty()) {
            StreetDAO dao = new StreetDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Street();
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
            StreetDAO dao = new StreetDAO(session);
            LocalityDAO localityDAO = new LocalityDAO(session);
            Street entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Street();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new Street();
                }
            }

            entity.setName(webFormData.getValueSilently("name"));
            entity.setLocality(localityDAO.findById(UUID.fromString(webFormData.getValue("locality"))));

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
