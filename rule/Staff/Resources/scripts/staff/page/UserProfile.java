package staff.page;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Validator;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import staff.dao.EmployeeDAO;
import staff.model.Employee;
import administrator.dao.LanguageDAO;
import administrator.dao.UserDAO;
import administrator.model.User;

/**
 * @author Kayra created 05-01-2016
 */

public class UserProfile extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		IUser<Long> user = session.getUser();
		EmployeeDAO dao = new EmployeeDAO(session);
		Employee emp = dao.findByUserId(user.getId());
		_ActionBar actionBar = new _ActionBar(session);
		actionBar.addAction(new _Action(getLocalizedWord("save_close", session.getLang()), "", _ActionType.SAVE_AND_CLOSE));
		actionBar.addAction(new _Action(getLocalizedWord("close", session.getLang()), "", _ActionType.CLOSE));
		addContent(emp);
		addContent(actionBar);
		addContent(new _POJOListWrapper(new LanguageDAO(session).findAll(), session));
		addContent("pagesize", String.valueOf(session.getPageSize()));
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

			IUser user = session.getUser();
			UserDAO dao = new UserDAO(session);
			User entity = dao.findById((long) user.getId());

			entity.setLogin(formData.getValue("login"));
			entity.setEmail(formData.getValue("email"));
			entity.setPwd(formData.getValue("pwd"));

			setRedirect("_back");
		} catch (_Exception e) {
			error(e);
		}
	}

	private _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();

		if (formData.getValueSilently("login").isEmpty()) {
			ve.addError("login", "required", getLocalizedWord("required", lang));
		}
		if (formData.getValueSilently("email").isEmpty() || !_Validator.checkEmail(formData.getValueSilently("email"))) {
			ve.addError("email", "email", getLocalizedWord("email_invalid", lang));
		}
		if (!formData.getValueSilently("pwd").isEmpty()) {
			if (formData.getValueSilently("pwd_confirm").isEmpty()) {
				ve.addError("pwd_confirm", "required", getLocalizedWord("required", lang));
			} else if (!formData.getValueSilently("pwd").equals(formData.getValueSilently("pwd_confirm"))) {
				ve.addError("pwd_confirm", "required", getLocalizedWord("password_confirm_not_equals", lang));
			}
		}

		return ve;
	}
}
