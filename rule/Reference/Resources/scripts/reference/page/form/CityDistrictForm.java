package reference.page.form;

import java.util.UUID;

import kz.lof.administrator.dao.LanguageDAO;
import kz.lof.exception.SecureException;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._Exception;

import org.eclipse.persistence.exceptions.DatabaseException;

import reference.dao.CityDistrictDAO;
import reference.dao.LocalityDAO;
import reference.model.CityDistrict;

public class CityDistrictForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser<Long> user = session.getUser();
		CityDistrict entity;
		if (!id.isEmpty()) {
			CityDistrictDAO dao = new CityDistrictDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = (CityDistrict) getDefaultEntity(user, new CityDistrict());
		}
		addContent(entity);
		addContent(new _POJOListWrapper(new LocalityDAO(session).findAll(), session));
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

			String id = formData.getValueSilently("docid");
			CityDistrictDAO dao = new CityDistrictDAO(session);
			CityDistrict entity;
			boolean isNew = id.isEmpty();

			if (isNew) {
				entity = new CityDistrict();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setLocalizedName(getLocalizedNames(session, formData));
			LocalityDAO localityDAO = new LocalityDAO(session);
			entity.setLocality(localityDAO.findById(formData.getValue("localityid")));

			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}

			finishSaveFormTransact(entity);
		} catch (_Exception | DatabaseException | SecureException e) {
			error(e);
		}
	}

	@Override
	protected _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();
		if (formData.getValueSilently("name").isEmpty()) {
			ve.addError("name", "required", getLocalizedWord("field_is_empty", lang));
		}

		if (formData.getValueSilently("localityid").isEmpty()) {
			ve.addError("localityid", "required", getLocalizedWord("field_is_empty", lang));
		}

		return ve;
	}

}
