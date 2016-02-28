package reference.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageCode;
import kz.flabs.users.User;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script._Exception;
import reference.dao.BuildingMaterialDAO;
import reference.model.BuildingMaterial;

public class BuildingMaterialForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		BuildingMaterial entity;
		if (!id.isEmpty()) {
			BuildingMaterialDAO dao = new BuildingMaterialDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new BuildingMaterial();
			entity.setAuthor(user);
		}
		addContent(entity);
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

			boolean isNew = false;
			String id = formData.getValueSilently("docid");
			BuildingMaterialDAO dao = new BuildingMaterialDAO(session);
			BuildingMaterial entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new BuildingMaterial();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));

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
