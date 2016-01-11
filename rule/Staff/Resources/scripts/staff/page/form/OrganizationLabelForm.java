package staff.page.form;

import java.util.UUID;

import kz.flabs.users.User;
import kz.nextbase.script._Exception;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._URL;
import kz.nextbase.script._WebFormData;
import staff.dao.EmployeeDAO;
import staff.dao.OrganizationLabelDAO;
import staff.model.Employee;
import staff.model.OrganizationLabel;

/**
 * 
 * 
 * @author Kayra created 10-01-2016
 */

public class OrganizationLabelForm extends StaffForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		OrganizationLabel entity = null;
		if (!id.equals("")) {
			OrganizationLabelDAO dao = new OrganizationLabelDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new OrganizationLabel();
			entity.setAuthor(user);
		}
		setContent(getSimpleActionBar(session, lang));
		setContent(new _POJOObjectWrapper(entity));
	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData, String lang) {
		println(webFormData);
		try {
			boolean v = validate(webFormData);
			if (v == false) {
				setBadRequest();
				return;
			}
			boolean isNew = false;
			String id = webFormData.getValueSilently("docid");
			EmployeeDAO dao = new EmployeeDAO(session);
			Employee entity = dao.findById(UUID.fromString(id));
			if (entity == null) {
				isNew = true;
				entity = new Employee();
			}

			entity.setName(webFormData.getValueSilently("name"));
			_URL returnURL = session.getURLOfLastPage();
			localizedMsgBox(getLocalizedWord("document_was_saved_succesfully", lang));
			setRedirectURL(returnURL);

			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}
		} catch (_Exception e) {
			log(e);
		}
	}

}
