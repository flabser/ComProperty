package staff.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.lof.scripting._POJOObjectWrapper;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Session;
import kz.nextbase.script._Validation;
import kz.nextbase.script._WebFormData;
import staff.dao.RoleDAO;
import staff.model.Role;

/**
 * @author Kayra created 10-01-2016
 */

public class RoleForm extends StaffForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Role entity;
		if (!id.isEmpty()) {
			RoleDAO dao = new RoleDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Role();
			entity.setAuthor(user);
		}
		addContent(new _POJOObjectWrapper(entity, lang));
		addContent(getSimpleActionBar(session, lang));
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
			RoleDAO dao = new RoleDAO(session);
			Role entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new Role();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setDescription(formData.getValue("description"));

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
