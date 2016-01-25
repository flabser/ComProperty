package reference.page.form;

import java.util.UUID;

import kz.flabs.users.User;
import kz.nextbase.script._Exception;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._URL;
import kz.nextbase.script._WebFormData;
import reference.dao.PositionDAO;
import reference.model.Position;

public class PositionForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
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
		setContent(new _POJOObjectWrapper(entity));
		setContent(getSimpleActionBar(session, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {
		try {
			boolean v = validate(formData);
			if (v == false) {
				setBadRequest();
				return;
			}

			boolean isNew = false;
			String id = formData.getValueSilently("docid");
			PositionDAO dao = new PositionDAO(session);
			Position entity;

			if (id.equals("")) {
				isNew = true;
				entity = new Position();
			} else {
				entity = dao.findById(UUID.fromString(id));
				if (entity == null) {
					isNew = true;
					entity = new Position();
				}
			}

			entity.setName(formData.getValue("name"));

			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}

			_URL returnURL = session.getURLOfLastPage();
			localizedMsgBox(getLocalizedWord("document_was_saved_succesfully", lang));
			setRedirectURL(returnURL);
		} catch (_Exception e) {
			log(e);
		}
	}
}
