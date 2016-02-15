package reference.page.form;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.nextbase.script.*;
import reference.dao.StructureTypeDAO;
import reference.model.StructureType;

import java.util.UUID;

public class StructureTypeForm extends ReferenceForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        StructureType entity;
        if (!id.isEmpty()) {
            StructureTypeDAO dao = new StructureTypeDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new StructureType();
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
            StructureTypeDAO dao = new StructureTypeDAO(session);
            StructureType entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new StructureType();
            } else {
                entity = dao.findById(UUID.fromString(id));
            }

            entity.setName(formData.getValue("name"));

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
