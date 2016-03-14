package staff.page.form;

import administrator.dao.LanguageDAO;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._Exception;
import staff.dao.RoleDAO;
import staff.model.Role;

import java.util.Date;
import java.util.UUID;

/**
 * @author Kayra created 10-01-2016
 */

public class RoleForm extends StaffForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");
        IUser user = session.getUser();
        Role entity;
        if (!id.isEmpty()) {
            RoleDAO dao = new RoleDAO(session);
            entity = dao.findById(UUID.fromString(id));
        } else {
            entity = new Role();
            entity.setRegDate(new Date());
            entity.setAuthor(user);
            entity.setName("");
        }
        addContent(entity);
        addContent(new _POJOListWrapper(new LanguageDAO(session).findAll(), session));
        addContent(getSimpleActionBar(session, session.getLang()));
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
            RoleDAO dao = new RoleDAO(session);
            Role entity;
            boolean isNew = id.isEmpty();

            if (isNew) {
                entity = new Role();
            } else {
                entity = dao.findById(UUID.fromString(id));
            }

            entity.setName(formData.getValue("name"));
            entity.setDescription(formData.getValue("description"));
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
