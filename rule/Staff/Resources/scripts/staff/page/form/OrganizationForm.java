package staff.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.nextbase.script._Exception;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import staff.dao.OrganizationDAO;
import staff.dao.OrganizationLabelDAO;
import staff.model.Organization;

/**
 * @author Kayra created 09-01-2016
 */

public class OrganizationForm extends StaffForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Organization entity;
		if (!id.equals("")) {
			OrganizationDAO dao = new OrganizationDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Organization();
			entity.setAuthor(user);
		}
		setContent(new _POJOObjectWrapper(entity, lang));
		setContent(new _POJOListWrapper(new OrganizationLabelDAO(session).findAll(), lang));
		setContent(getSimpleActionBar(session, lang));
		startSaveFormTransact(entity);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {
		try {

			if (!validate(formData, lang)) {
				setBadRequest();
				return;
			}

			boolean isNew = false;
			String id = formData.getValueSilently("docid");
			OrganizationDAO dao = new OrganizationDAO(session);
			Organization entity;

			if (id.equals("")) {
				isNew = true;
				entity = new Organization();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setBin(formData.getValue("bin"));
			entity.setPrimary("1".equals(formData.getValueSilently("is_primary")));

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
	protected boolean validate(_WebFormData formData, LanguageType lang) {
		boolean validationState = true;

		if (!super.validate(formData, lang)) {
			validationState = false;
		}

		if (formData.getValueSilently("bin").equals("")) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("bin", lang) + "\""
			        + getLocalizedWord("has_been_not_filled", lang));
			validationState = false;
		}

		return validationState;
	}
}
