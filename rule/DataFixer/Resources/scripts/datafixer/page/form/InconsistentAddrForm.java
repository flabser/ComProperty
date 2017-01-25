package datafixer.page.form;

import java.util.UUID;

import org.eclipse.persistence.exceptions.DatabaseException;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._EnumWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._Validation;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.user.IUser;

import municipalproperty.dao.RealEstateDAO;
import municipalproperty.model.RealEstate;
import municipalproperty.model.constants.PropertyStatusType;
import municipalproperty.page.form.AbstractMunicipalPropertyForm;
import reference.dao.CityDistrictDAO;
import reference.dao.LocalityDAO;
import reference.dao.RegionDAO;
import reference.dao.StreetDAO;
import reference.model.constants.KufType;
import reference.model.embedded.Address;

public class InconsistentAddrForm extends AbstractMunicipalPropertyForm {
	
	@Override
	public void doGET(_Session session, WebFormData formData) {
		String id = formData.getValueSilently("docid");
		LanguageCode lang = session.getLang();
		RealEstate entity = null;
		try {
			if (!id.isEmpty()) {
				entity = getEntity(id, session);
				addContent(entity);
			}
			_ActionBar actionBar = new _ActionBar(session);
			if (entity.isEditable()) {
				actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), "", _ActionType.SAVE_AND_CLOSE));
			}
			addContent(actionBar);
			addContent(new _EnumWrapper(PropertyStatusType.class.getEnumConstants()));
			addContent(new _EnumWrapper(KufType.class.getEnumConstants()));
		} catch (DAOException e) {
			logError(e);
			setBadRequest();
			return;
		}
	}
	
	@Override
	public void doPOST(_Session session, WebFormData formData) {
		devPrint(formData);
		try {
			String id = formData.getValueSilently("docid");
			
			_Validation ve = validate(formData, session.getLang(), false);
			if (ve.hasError()) {
				setBadRequest();
				setValidation(ve);
				return;
			}
			
			RealEstateDAO dao = new RealEstateDAO(session);
			RealEstate entity;
			
			entity = dao.findById(id);
			
			entity.getAddress().setRegion(new RegionDAO(session).findById(formData.getValueSilently("region")));
			entity.getAddress().setLocality(new LocalityDAO(session).findById(formData.getValueSilently("locality")));
			entity.getAddress()
					.setCityDistrict(new CityDistrictDAO(session).findById(formData.getValueSilently("district")));
			entity.getAddress().setStreet(new StreetDAO(session).findById(formData.getValueSilently("street")));
			entity.getAddress().setHouseNumber(formData.getValueSilently("housenumber"));
			entity.getAddress().setAdditionalInfo(formData.getValueSilently("additionalinfo"));
			
			IUser<Long> user = session.getUser();
			entity.addReaderEditor(user);
			
			save(entity, dao, false);
		} catch (DatabaseException | SecureException | DAOException e) {
			logError(e);
			setBadRequest();
		}
	}
	
	private _Validation validate(WebFormData formData, LanguageCode lang, boolean isNew) {
		_Validation ve = new _Validation();
		
		if (formData.getValueSilently("street").isEmpty()) {
			ve.addError("street", "required", getLocalizedWord("street_is_empty", lang));
		}
		
		return ve;
	}
	
	protected RealEstate getEntity(String id, _Session session) throws DAOException {
		RealEstateDAO dao = new RealEstateDAO(session);
		RealEstate entity = dao.findById(UUID.fromString(id));
		Address addr = entity.getAddress();
		if (addr == null) {
			entity.setAddress(Address.getStub(session));
		} else if (addr.getStreet().getName().equalsIgnoreCase("unknown")) {
			entity.getAddress().getStreet().setName("");
		}
		return entity;
	}
}
