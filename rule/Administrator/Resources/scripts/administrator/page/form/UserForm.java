package administrator.page.form;

import java.util.Date;
import java.util.UUID;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import staff.dao.DepartmentDAO;
import staff.model.Department;
import staff.model.constants.DepartmentType;
import administrator.dao.LanguageDAO;
import administrator.dao.UserDAO;
import administrator.model.User;

/**
 * @author Kayra created 05-03-2016
 */

public class UserForm extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		String id = formData.getValueSilently("docid");
		IUser user = session.getUser();
		User entity;
		if (!id.isEmpty()) {
			UserDAO dao = new UserDAO(session);
			entity = dao.findById(Long.parseLong(id));
		} else {
			entity = new User();
			entity.setRegDate(new Date());
			entity.setLogin("");
		}
		// addContent(entity);
		addContent(new _EnumWrapper<>(DepartmentType.class.getEnumConstants()));
		addContent(new _POJOListWrapper(new LanguageDAO(session).findAll(), session));
		_ActionBar actionBar = new _ActionBar(session);
		actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), "", _ActionType.SAVE_AND_CLOSE));
		actionBar.addAction(new _Action(getLocalizedWord("close", lang), "", _ActionType.CLOSE));
		addContent(actionBar);
		// startSaveFormTransact(entity);
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

			boolean isNew = false;
			String id = formData.getValueSilently("docid");
			DepartmentDAO dao = new DepartmentDAO(session);
			Department entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new Department();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			entity.setType(DepartmentType.valueOf(formData.getValueSilently("type", "UNKNOWN")));

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

	protected _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();

		if (formData.getValueSilently("name").isEmpty()) {
			ve.addError("name", "empty", getLocalizedWord("required", lang));
		}

		return ve;
	}
}
