package staff.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageCode;
import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script._Exception;
import staff.dao.OrganizationLabelDAO;
import staff.model.OrganizationLabel;
import administrator.dao.LanguageDAO;

/**
 * @author Kayra created 10-01-2016
 */

public class OrganizationLabelForm extends StaffForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		OrganizationLabel entity;
		if (!id.isEmpty()) {
			OrganizationLabelDAO dao = new OrganizationLabelDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new OrganizationLabel();
			entity.setAuthor(user);
		}
		addContent(entity);
		addContent(new _POJOListWrapper(new LanguageDAO(session).findAll(), session));
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
			OrganizationLabelDAO dao = new OrganizationLabelDAO(session);
			OrganizationLabel entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new OrganizationLabel();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setDescription(formData.getValue("description"));

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
