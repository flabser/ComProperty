package reference.page.form;

import java.util.UUID;

import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script._Exception;
import reference.dao.LocalityDAO;
import reference.dao.StreetDAO;
import reference.model.Street;
import administrator.dao.LanguageDAO;

public class StreetForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Street entity;
		if (!id.isEmpty()) {
			StreetDAO dao = new StreetDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Street();
			entity.setAuthor(user);
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
			StreetDAO dao = new StreetDAO(session);
			LocalityDAO localityDAO = new LocalityDAO(session);
			Street entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new Street();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setLocality(localityDAO.findByName(formData.getValue("locality")));

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
