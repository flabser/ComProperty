package reference.page.form;

import java.util.UUID;

import javax.persistence.RollbackException;

import kz.flabs.localization.LanguageCode;
import kz.flabs.users.User;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import reference.dao.CountryDAO;
import reference.model.Country;
import reference.model.constants.CountryCode;

/**
 * @author Kayra created 03-01-2016
 */

public class CountryForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Country entity;
		if (!id.isEmpty()) {
			CountryDAO dao = new CountryDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Country();
			entity.setAuthor(user);
		}
		addContent(entity);
		addContent(new _EnumWrapper<>(CountryCode.class.getEnumConstants()));
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

			CountryDAO dao = new CountryDAO(session);
			Country entity;
			String id = formData.getValueSilently("docid");

			boolean isNew = id.isEmpty();
			if (isNew) {
				entity = new Country();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setCode(CountryCode.valueOf(formData.getValueSilently("code", "UNKNOWN")));

			if (isNew) {
				try {
					dao.add(entity);
				} catch (RollbackException e) {
					e.printStackTrace();
				}
			} else {
				dao.update(entity);
			}

			finishSaveFormTransact(entity);
		} catch (_Exception e) {
			error(e);
		}
	}
}
