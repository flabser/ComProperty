package reference.page.form;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.DistrictDAO;
import reference.dao.RegionDAO;
import reference.model.District;

import java.util.UUID;

/**
 * @author Kayra created 03-01-2016
 */

public class DistrictForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        District entity;
        if (!id.isEmpty()) {
            DistrictDAO dao = new DistrictDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new District();
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
            DistrictDAO dao = new DistrictDAO(session);
            RegionDAO regionDAO = new RegionDAO(session);
            District entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new District();
            } else {
                entity = dao.findById(UUID.fromString(id));
            }

            entity.setName(formData.getValue("name"));
            entity.setRegion(regionDAO.findById(UUID.fromString(formData.getValue("region"))));

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
