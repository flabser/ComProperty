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
import reference.dao.RegionDAO;
import reference.model.Locality;
import reference.model.LocalityType;
import reference.model.Region;
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
			Region region = new Region();
			region.setName("");
			entity.setRegion(region);
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

			Locality entity;
			boolean isNew = id.isEmpty();

			if (isNew) {
				entity = new Locality();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			LocalityTypeDAO localityTypeDAO = new LocalityTypeDAO(session);
			entity.setType(localityTypeDAO.findById(formData.getValue("localitytype")));
			RegionDAO regionDAO = new RegionDAO(session);
			entity.setRegion(regionDAO.findById(formData.getValueSilently("region")));

			String districtId = formData.getValueSilently("district");
			if (!districtId.isEmpty()) {
				DistrictDAO districtDAO = new DistrictDAO(session);
				entity.setDistrict(districtDAO.findById(districtId));
			} else {

			}

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

		if (formData.getValueSilently("region").isEmpty()) {
			ve.addError("region", "required", getLocalizedWord("field_is_empty", lang));
		}

		if (formData.getValueSilently("localitytype").isEmpty()) {
			ve.addError("localitytype", "required", getLocalizedWord("field_is_empty", lang));
		}

		return ve;
	}
}
