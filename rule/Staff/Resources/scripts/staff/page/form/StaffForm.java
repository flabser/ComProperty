package staff.page.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kz.lof.administrator.dao.LanguageDAO;
import kz.lof.administrator.model.Language;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.lof.user.SuperUser;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;

/**
 * @author Kayra created 07-01-2016
 */

public abstract class StaffForm extends _DoPage {

	protected _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();

		if (formData.getValueSilently("name").isEmpty()) {
			ve.addError("name", "required", getLocalizedWord("required", lang));
		}

		return ve;
	}

	protected _ActionBar getSimpleActionBar(_Session ses, LanguageCode lang) {
		_ActionBar actionBar = new _ActionBar(ses);
		IUser<Long> user = ses.getUser();
		if (user.getId() == SuperUser.ID || user.getRoles().contains("staff_admin")) {
			actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), "", _ActionType.SAVE_AND_CLOSE));
		}
		actionBar.addAction(new _Action(getLocalizedWord("close", lang), "", _ActionType.CLOSE));
		return actionBar;
	}

	protected Map<LanguageCode, String> getLocalizedNames(_Session session, _WebFormData formData) {
		Map<LanguageCode, String> localizedNames = new HashMap<LanguageCode, String>();
		List<Language> langs = new LanguageDAO(session).findAll();
		for (Language l : langs) {
			String ln = formData.getValueSilently(l.getCode().name().toLowerCase() + "localizedname");
			if (!ln.isEmpty()) {
				localizedNames.put(l.getCode(), ln);
			} else {
				localizedNames.put(l.getCode(), formData.getValueSilently("name"));
			}
		}
		return localizedNames;
	}
}
