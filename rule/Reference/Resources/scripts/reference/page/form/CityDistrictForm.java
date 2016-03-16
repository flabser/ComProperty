package reference.page.form;

import administrator.dao.LanguageDAO;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._Exception;
import reference.dao.CityDistrictDAO;
import reference.model.CityDistrict;

import java.util.UUID;


public class CityDistrictForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");
        IUser<Long> user = session.getUser();
        CityDistrict entity;
        if (!id.isEmpty()) {
            CityDistrictDAO dao = new CityDistrictDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = (CityDistrict) getDefaultEntity(user, new CityDistrict());
        }
        addContent(entity);
        addContent(new _POJOListWrapper(new LanguageDAO(session).findAll(), session));
        addContent(getSimpleActionBar(session));
        startSaveFormTransact(entity);
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData) {
        try {
            _Validation ve = validate(formData, session.getLang());
            if (ve.hasError()) {
                setBadRequest();
                setValidation(ve);
                return;
            }

            String id = formData.getValueSilently("docid");
            CityDistrictDAO dao = new CityDistrictDAO(session);
            CityDistrict entity;
            boolean isNew = id.isEmpty();

            if (isNew) {
                entity = new CityDistrict();
            } else {
                entity = dao.findById(UUID.fromString(id));
            }

            entity.setName(formData.getValue("name"));
            entity.setLocalizedName(getLocalizedNames(session, formData));

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