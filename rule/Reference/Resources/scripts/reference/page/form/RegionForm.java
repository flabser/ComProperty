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
        Region entity = null;
        if (!id.equals("")) {
            RegionDAO dao = new RegionDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Region();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity));
        setContent(new _EnumWrapper<RegionType>(RegionType.class.getEnumConstants(), getLocalizedWord(RegionType.class.getEnumConstants(), lang)));
        setContent(new _POJOListWrapper<Country>(new CountryDAO(session).findAll()));
        setContent(getSimpleActionBar(session, lang));
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
            RegionDAO dao = new RegionDAO(session);
            Region entity = dao.findById(UUID.fromString(id));
            if (entity == null) {
                isNew = true;
                entity = new Region();
            }

            entity.setName(webFormData.getValueSilently("name"));
            entity.setType(RegionType.valueOf(webFormData.getValueSilently("region_type", "UNKNOWN")));
            CountryDAO cDao = new CountryDAO(session);
            Country country = cDao.findById(UUID.fromString(webFormData.getValueSilently("country_id")));
            entity.setCountry(country);
            _URL returnURL = session.getURLOfLastPage();
            localizedMsgBox(getLocalizedWord("document_was_saved_succesfully", lang));
            setRedirectURL(returnURL);

            if (isNew) {
                dao.add(entity);
            } else {
                dao.update(entity);
            }
        } catch (_Exception e) {
            log(e);
        }
    }

    @Override
    protected boolean validate(_WebFormData webFormData) {
        if (super.validate(webFormData)) {
            return false;
        } else if (webFormData.getValueSilently("region_type").equals("") || webFormData.getValueSilently("region_type").equals("UNKNOWN")) {
            localizedMsgBox("field_region_type_is_empty");
            return false;
        } else if (webFormData.getValueSilently("country_id").equals("")) {
            localizedMsgBox("field_country_type_is_empty");
            return false;
        }

        return true;
    }
}
