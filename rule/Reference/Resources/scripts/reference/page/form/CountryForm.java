package reference.page.form;

import kz.flabs.localization.LanguageType;
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
    public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Country entity;
        if (!id.isEmpty()) {
            CountryDAO dao = new CountryDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Country();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity, lang));
        setContent(new _EnumWrapper<>(CountryCode.class.getEnumConstants()));
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
            CountryDAO dao = new CountryDAO(session);
            Country entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Country();
            } else {
                entity = dao.findById(UUID.fromString(id));
            }

            entity.setName(formData.getValue("name"));
            entity.setCode(CountryCode.valueOf(formData.getValueSilently("code", "UNKNOWN")));

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
