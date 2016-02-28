package reference.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageCode;
import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import reference.dao.CountryDAO;
import reference.dao.RegionDAO;
import reference.model.Country;
import reference.model.Region;
import reference.model.constants.RegionType;

/**
 * @author Kayra created 03-01-2016
 */

public class RegionForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Region entity;
		if (!id.isEmpty()) {
			RegionDAO dao = new RegionDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Region();
			entity.setAuthor(user);
		}
		addContent(entity);
		addContent(new _EnumWrapper<>(RegionType.class.getEnumConstants(), getLocalizedWord(RegionType.class.getEnumConstants(), lang.toString())));
		addContent(new _POJOListWrapper<>(new CountryDAO(session).findAll(), session));
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

			RegionDAO dao = new RegionDAO(session);
			Region entity;
			String id = formData.getValueSilently("docid");

			boolean isNew = id.isEmpty();
			if (isNew) {
				entity = new Region();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setType(RegionType.valueOf(formData.getValue("type")));
			CountryDAO countryDao = new CountryDAO(session);
			Country country = countryDao.findById(UUID.fromString(formData.getValue("country")));
			entity.setCountry(country);

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
	protected _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation v = new _Validation();
		if (formData.getValueSilently("name").isEmpty()) {
			v.addError("name", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("type").isEmpty() || formData.getValueSilently("type").equals("UNKNOWN")) {
			v.addError("type", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("country").isEmpty()) {
			v.addError("country", "required", getLocalizedWord("field_is_empty", lang));
		}

		return v;
	}
}
