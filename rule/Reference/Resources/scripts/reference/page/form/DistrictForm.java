package reference.page.form;

import java.util.UUID;

import kz.lof.exception.SecureException;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._Exception;

import org.eclipse.persistence.exceptions.DatabaseException;

import reference.dao.DistrictDAO;
import reference.dao.RegionDAO;
import reference.model.District;
import reference.model.Region;
import administrator.dao.LanguageDAO;

/**
 * @author Kayra created 03-01-2016
 */

public class DistrictForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser<Long> user = session.getUser();
		District entity;
		if (!id.isEmpty()) {
			DistrictDAO dao = new DistrictDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = (District) getDefaultEntity(user, new District());
			RegionDAO cDao = new RegionDAO(session);
			Region region = cDao.findByName("Алматы");
			entity.setRegion(region);
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
			DistrictDAO dao = new DistrictDAO(session);
			RegionDAO regionDAO = new RegionDAO(session);
			District entity;
			boolean isNew = id.isEmpty();

			if (isNew) {
				entity = new District();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setRegion(regionDAO.findById(UUID.fromString(formData.getValue("region"))));
			entity.setLocalizedName(getLocalizedNames(session, formData));

			District foundEntity = dao.findByName(entity.getName());
			if (foundEntity != null && !foundEntity.equals(entity)) {
				ve = new _Validation();
				ve.addError("name", "unique", getLocalizedWord("name_is_not_unique", session.getLang()));
				setBadRequest();
				setValidation(ve);
				return;
			}

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
}
