package staff.page.form;

import java.util.UUID;

import kz.flabs.users.User;
import kz.nextbase.script._Exception;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._URL;
import kz.nextbase.script._WebFormData;
import staff.dao.OrganizationDAO;
import staff.dao.OrganizationLabelDAO;
import staff.model.Organization;

/**
 * @author Kayra created 09-01-2016
 */

public class OrganizationForm extends StaffForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
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
		setContent(new _POJOObjectWrapper(entity));
		setContent(new _POJOListWrapper(new OrganizationLabelDAO(session).findAll()));
		setContent(getSimpleActionBar(session, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {
		try {
			boolean v = validate(formData);
			if (v == false) {
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
				if (entity == null) {
					isNew = true;
					entity = new Organization();
				}
			}

			entity.setName(formData.getValue("name"));
			entity.setPrimary("1".equals(formData.getValueSilently("is_primary")));

			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}

			_URL returnURL = session.getURLOfLastPage();
			localizedMsgBox(getLocalizedWord("document_was_saved_succesfully", lang));
			setRedirectURL(returnURL);
		} catch (_Exception e) {
			log(e);
		}
	}
}
