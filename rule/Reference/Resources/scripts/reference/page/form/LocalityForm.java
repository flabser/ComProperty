package reference.page.form;

import java.util.Date;
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

import reference.dao.DistrictDAO;
import reference.dao.LocalityDAO;
import reference.dao.LocalityTypeDAO;
import reference.model.Locality;
import reference.model.LocalityType;
import reference.model.constants.LocalityCode;

/**
 * @author Kayra created 03-01-2016
 */

public class LocalityForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser<Long> user = session.getUser();
		Locality entity;
		if (!id.isEmpty()) {
			LocalityDAO dao = new LocalityDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Locality();
			entity.setAuthor(user);
			entity.setRegDate(new Date());
			entity.setName("");
			LocalityType regionType = new LocalityTypeDAO(session).findByCode(LocalityCode.CITY);
			entity.setType(regionType);
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

			String id = formData.getValueSilently("docid");
			LocalityDAO dao = new LocalityDAO(session);
			DistrictDAO districtDAO = new DistrictDAO(session);
			Locality entity;
			boolean isNew = id.isEmpty();

			if (isNew) {
				entity = new Locality();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			LocalityTypeDAO localityTypeDAO = new LocalityTypeDAO(session);
			entity.setType(localityTypeDAO.findById(formData.getValue("localitytypeid")));
			entity.setDistrict(districtDAO.findById(UUID.fromString(formData.getValue("districtid"))));
			entity.setLocalizedName(getLocalizedNames(session, formData));

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

		if (formData.getValueSilently("districtidid").isEmpty()) {
			ve.addError("code", "required", getLocalizedWord("field_is_empty", lang));
		}

		if (formData.getValueSilently("localitytypeid").isEmpty()) {
			ve.addError("code", "required", getLocalizedWord("field_is_empty", lang));
		}

		return ve;
	}
}
