package reference.page.form;

import java.util.UUID;

import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import reference.dao.CountryDAO;
import reference.model.Country;
import reference.model.Reference;
import reference.model.constants.CountryCode;
import administrator.dao.LanguageDAO;

/**
 * @author Kayra created 03-01-2016
 */

public class CountryForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Reference entity;
		if (!id.isEmpty()) {
			CountryDAO dao = new CountryDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = getDefaultEntity(user);
		}
		addContent(entity);
		addContent(new _EnumWrapper<>(CountryCode.class.getEnumConstants()));
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

			save(session, entity, dao, isNew);

			finishSaveFormTransact(entity);
		} catch (_Exception e) {
			error(e);
		}
	}

	protected void save(_Session ses, Country entity, CountryDAO dao, boolean isNew) {
		Country foundEntity = dao.findByCode(entity.getCode());
		if (foundEntity != null && !foundEntity.equals(entity)) {
			_Validation ve = new _Validation();
			ve.addError("code", "unique_error", getLocalizedWord("code_is_not_unique", ses.getLang()));
			setBadRequest();
			setValidation(ve);
			return;
		}

		if (isNew) {
			dao.add(entity);
		} else {
			dao.update(entity);
		}

	}
}
