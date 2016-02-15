package staff.page.form;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.nextbase.script.*;
import staff.dao.OrganizationDAO;
import staff.dao.OrganizationLabelDAO;
import staff.model.Organization;

import java.util.UUID;

/**
 * @author Kayra created 09-01-2016
 */

public class OrganizationForm extends StaffForm {

    @Override
    public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        Organization entity;
        if (!id.isEmpty()) {
            OrganizationDAO dao = new OrganizationDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Organization();
            entity.setAuthor(user);
        }
        setContent(new _POJOObjectWrapper(entity, lang));
        setContent(new _POJOListWrapper(new OrganizationLabelDAO(session).findAll(), lang));
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
            OrganizationDAO dao = new OrganizationDAO(session);
            Organization entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new Organization();
            } else {
                entity = dao.findById(UUID.fromString(id));
            }

            entity.setName(formData.getValue("name"));
            entity.setBin(formData.getValue("bin"));
            entity.setPrimary("1".equals(formData.getValueSilently("is_primary")));

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

    @Override
    protected _Validation validate(_WebFormData formData, LanguageType lang) {
        _Validation ve = new _Validation();
        if (formData.getValueSilently("name").isEmpty()) {
            ve.addError("name", "empty", getLocalizedWord("required", lang));
        }
        if (formData.getValueSilently("bin").isEmpty()) {
            ve.addError("bin", "empty", getLocalizedWord("required", lang));
        }

        return ve;
    }
}
