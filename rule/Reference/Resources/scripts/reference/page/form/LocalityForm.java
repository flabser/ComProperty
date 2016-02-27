package reference.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.lof.scripting._Session;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Validation;
import kz.nextbase.script._WebFormData;
import reference.dao.DistrictDAO;
import reference.dao.LocalityDAO;
import reference.model.Locality;
import reference.model.constants.LocalityType;

/**
 * @author Kayra created 03-01-2016
 */

public class LocalityForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Locality entity;
		if (!id.isEmpty()) {
			LocalityDAO dao = new LocalityDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Locality();
			entity.setAuthor(user);
		}
		addContent(entity);
		addContent(new _EnumWrapper<>(LocalityType.class.getEnumConstants()));
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
			LocalityDAO dao = new LocalityDAO(session);
			DistrictDAO districtDAO = new DistrictDAO(session);
			Locality entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new Locality();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setType(LocalityType.valueOf(formData.getValueSilently("type", "UNKNOWN")));
			entity.setDistrict(districtDAO.findById(UUID.fromString(formData.getValue("district"))));

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
