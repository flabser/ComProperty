package reference.page.form;

import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.CountryDAO;
import reference.dao.RegionDAO;
import reference.model.Country;
import reference.model.Region;
import reference.model.constants.RegionType;

import java.util.UUID;

/**
 * @author Kayra created 03-01-2016
 */

public class RegionForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Region entity;
        if (!id.isEmpty()) {
            RegionDAO dao = new RegionDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Region();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity));
        setContent(new _EnumWrapper<>(RegionType.class.getEnumConstants(), getLocalizedWord(RegionType.class.getEnumConstants(), lang)));
        setContent(new _POJOListWrapper<>(new CountryDAO(session).findAll()));
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
            RegionDAO dao = new RegionDAO(session);
            Region entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Region();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new Region();
                }
            }

            entity.setName(formData.getValue("name"));
            entity.setType(RegionType.valueOf(formData.getValueSilently("region_type", "UNKNOWN")));
            CountryDAO countryDao = new CountryDAO(session);
            Country country = countryDao.findById(UUID.fromString(formData.getValueSilently("country_id")));
            entity.setCountry(country);

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

    @Override
    protected boolean validate(_WebFormData formData) {
        if (super.validate(formData)) {
            return false;
        } else if (formData.getValueSilently("region_type").isEmpty() || formData.getValueSilently("region_type").equals("UNKNOWN")) {
            localizedMsgBox("field_region_type_is_empty");
            return false;
        } else if (formData.getValueSilently("country_id").isEmpty()) {
            localizedMsgBox("field_country_type_is_empty");
            return false;
        }

        return true;
    }
}
