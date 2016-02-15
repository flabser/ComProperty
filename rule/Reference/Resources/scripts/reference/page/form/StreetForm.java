package reference.page.form;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.LocalityDAO;
import reference.dao.StreetDAO;
import reference.model.Street;

import java.util.UUID;

public class StreetForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Street entity;
        if (!id.isEmpty()) {
            StreetDAO dao = new StreetDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Street();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity, lang));
        setContent(getSimpleActionBar(session, lang));
        startSaveFormTransact(entity);
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {
        try {
            _Validation ve = validate(formData, lang);
            if (ve.hasError()) {
                setBadRequest();
                setValidation(ve);
                return;
            }

            boolean isNew = false;
            String id = formData.getValueSilently("docid");
            StreetDAO dao = new StreetDAO(session);
            LocalityDAO localityDAO = new LocalityDAO(session);
            Street entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Street();
            } else {
                entity = dao.findById(UUID.fromString(id));
            }

            entity.setName(formData.getValue("name"));
            entity.setLocality(localityDAO.findByName(formData.getValue("locality")));

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
}
