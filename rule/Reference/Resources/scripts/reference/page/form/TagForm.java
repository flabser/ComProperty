package reference.page.form;

import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._URL;
import kz.nextbase.script._WebFormData;
import municipalproperty.dao.TagDAO;
import reference.model.Tag;

/**
 * @author Kayra created 28-01-2016
 */

public class TagForm extends ReferenceForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Tag entity;
		if (!id.equals("")) {
			TagDAO dao = new TagDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Tag();
			entity.setAuthor(user);
		}
		setContent(new _POJOObjectWrapper(entity));
		setContent(new _EnumWrapper<>(LanguageType.class.getEnumConstants()));
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
			TagDAO dao = new TagDAO(session);
			Tag entity;

			if (id.equals("")) {
				isNew = true;
				entity = new Tag();
			} else {
				entity = dao.findById(UUID.fromString(id));
				if (entity == null) {
					isNew = true;
					entity = new Tag();
				}
			}

			// entity.setName(formData.getValue("name"));
			// entity.setCode(CountryCode.valueOf(formData.getValueSilently("code",
			// "UNKNOWN")));

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
