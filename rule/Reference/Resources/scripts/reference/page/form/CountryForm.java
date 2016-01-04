package reference.page.form;

import java.util.UUID;

import kz.flabs.users.User;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._URL;
import kz.nextbase.script._WebFormData;
import reference.dao.CountryDAO;
import reference.model.Country;
import reference.model.constants.CountryCode;

/**
 * 
 * 
 * @author Kayra created 03-01-2016
 */

public class CountryForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Country entity = null;
		if (!id.equals("")) {
			CountryDAO dao = new CountryDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Country();
			entity.setAuthor(user);
		}
		setContent(getSimpleActionBar(session, lang));
		setContent(new _POJOObjectWrapper(entity));
		setContent(new _EnumWrapper<CountryCode>(CountryCode.class.getEnumConstants()));
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
			CountryDAO dao = new CountryDAO(session);
			Country entity = dao.findById(UUID.fromString(id));
			if (entity == null) {
				isNew = true;
				entity = new Country();
			}

			entity.setName(webFormData.getValueSilently("name"));
			entity.setCode(CountryCode.valueOf(webFormData.getValueSilently("code", "UNKNOWN")));
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
