package reference.page.form;

import administrator.dao.LanguageDAO;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import reference.dao.RegionTypeDAO;
import reference.model.RegionType;
import reference.model.constants.LocalityCode;
import reference.model.constants.RegionCode;

import java.util.UUID;

/**
 * @author Kayra created 03-01-2016
 */

public class RegionTypeForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");
        IUser<Long> user = session.getUser();
        RegionType entity;
        if (!id.isEmpty()) {
            RegionTypeDAO dao = new RegionTypeDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = (RegionType) getDefaultEntity(user, new RegionType());
        }
        addContent(entity);
        addContent(new _EnumWrapper<>(LocalityCode.class.getEnumConstants()));
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
            RegionTypeDAO dao = new RegionTypeDAO(session);
            RegionType entity;
            boolean isNew = id.isEmpty();

            if (isNew) {
                entity = new RegionType();
            } else {
                entity = dao.findById(UUID.fromString(id));
            }

            entity.setName(formData.getValue("name"));
            entity.setCode(RegionCode.valueOf(formData.getValueSilently("code", "UNKNOWN")));
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
