package reference.page.form;

import java.util.Date;
import java.util.UUID;

import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import reference.dao.LocalityTypeDAO;
import reference.model.LocalityType;
import reference.model.constants.LocalityCode;
import administrator.dao.LanguageDAO;

/**
 * @author Kayra created 03-01-2016
 */

public class LocalityTypeForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		LocalityType entity;
		if (!id.isEmpty()) {
			LocalityTypeDAO dao = new LocalityTypeDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new LocalityType();
			entity.setAuthor(user);
			entity.setRegDate(new Date());
			entity.setName("");
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

			boolean isNew = false;
			String id = formData.getValueSilently("docid");
			LocalityTypeDAO dao = new LocalityTypeDAO(session);
			LocalityType entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new LocalityType();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setCode(LocalityCode.valueOf(formData.getValueSilently("code", "UNKNOWN")));

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