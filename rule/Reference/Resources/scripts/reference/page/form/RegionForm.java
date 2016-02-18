package reference.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._POJOObjectWrapper;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import kz.lof.scripting._Session;
import kz.nextbase.script._Validation;
import kz.nextbase.script._WebFormData;
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
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
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
		addContent(new _POJOObjectWrapper(entity, lang));
		addContent(new _EnumWrapper<>(RegionType.class.getEnumConstants(), getLocalizedWord(RegionType.class.getEnumConstants(), lang.toString())));
		addContent(new _POJOListWrapper<>(new CountryDAO(session).findAll(), lang));
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
			RegionDAO dao = new RegionDAO(session);
			Region entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new Region();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setType(RegionType.valueOf(formData.getValueSilently("region_type", "UNKNOWN")));
			CountryDAO countryDao = new CountryDAO(session);
			Country country = countryDao.findById(UUID.fromString(formData.getValueSilently("country_id")));
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
	protected _Validation validate(_WebFormData formData, LanguageType lang) {
		_Validation ve = new _Validation();
		if (formData.getValueSilently("name").isEmpty()) {
			ve.addError("name", "empty", getLocalizedWord("required", lang));
		}
		if (formData.getValueSilently("region_type").isEmpty() || formData.getValueSilently("region_type").equals("UNKNOWN")) {
			ve.addError("region_type", "empty", getLocalizedWord("required", lang));
		}
		if (formData.getValueSilently("country_id").isEmpty()) {
			ve.addError("country_id", "empty", getLocalizedWord("required", lang));
		}

		return ve;
	}
}
