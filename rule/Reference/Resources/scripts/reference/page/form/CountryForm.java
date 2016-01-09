package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.CountryDAO;
import reference.model.Country;
import reference.model.constants.CountryCode;

import java.util.UUID;

/**
 * @author Kayra created 03-01-2016
 */

public class CountryForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Country entity = null;
        if (!id.isEmpty()) {
            CountryDAO dao = new CountryDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Country();
            entity.setAuthor(user);
        }
        setContent(getSimpleActionBar(session, lang));
        setContent(new _POJOObjectWrapper(entity));
        setContent(new _EnumWrapper<>(CountryCode.class.getEnumConstants()));
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
            CountryDAO dao = new CountryDAO(session);
            Country entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Country();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new Country();
                }
            }

            entity.setName(webFormData.getValueSilently("name"));
            entity.setCode(CountryCode.valueOf(webFormData.getValueSilently("code", "UNKNOWN")));

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
