package reference.page.form;

import java.util.UUID;

import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._Exception;
import reference.dao.TagDAO;
import reference.model.Tag;
import administrator.dao.LanguageDAO;

/**
 * @author Kayra created 28-01-2016
 */

public class TagForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser<Long> user = session.getUser();
		Tag entity;
		if (!id.isEmpty()) {
			TagDAO dao = new TagDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = (Tag) getDefaultEntity(user, new Tag());
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

			boolean isNew = false;
			String id = formData.getValueSilently("docid");
			TagDAO dao = new TagDAO(session);
			Tag entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new Tag();
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
