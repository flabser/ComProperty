package reference.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageCode;
import kz.flabs.users.User;
import kz.lof.scripting._Session;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Validation;
import kz.nextbase.script._WebFormData;
import reference.dao.PositionDAO;
import reference.model.Position;

public class PositionForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Position entity;
		if (!id.isEmpty()) {
			PositionDAO dao = new PositionDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Position();
			entity.setAuthor(user);
		}
		addContent(entity);
		addContent(getSimpleActionBar(session, lang));
		startSaveFormTransact(entity);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {
		try {
			_Validation ve = validate(formData, lang);
			if (ve.hasError()) {
				setBadRequest();
				setValidation(ve);
				return;
			}

			boolean isNew = false;
			String id = formData.getValueSilently("docid");
			PositionDAO dao = new PositionDAO(session);
			Position entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new Position();
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
