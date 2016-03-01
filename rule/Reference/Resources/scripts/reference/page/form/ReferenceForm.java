package reference.page.form;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.webserver.servlet.IOutcomeObject;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;

/**
 * @author Kayra created 03-01-2016
 */

public abstract class ReferenceForm extends _DoPage {

	protected _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();
		if (formData.getValueSilently("name").isEmpty()) {
			ve.addError("name", "required", getLocalizedWord("field_is_empty", lang));
		}

		return ve;
	}

	protected IOutcomeObject getSimpleActionBar(_Session ses, LanguageCode lang) {
		_ActionBar actionBar = new _ActionBar(ses);
		// _Employer user = ses.getCurrentAppUser();
		// if (user.hasRole("supervisor")) {
		actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), "", _ActionType.SAVE_AND_CLOSE));
		// }

		actionBar.addAction(new _Action(getLocalizedWord("close", lang), "", _ActionType.CLOSE));
		return actionBar;

	}

	@Override
	public abstract void doGET(_Session session, _WebFormData formData);

	@Override
	public abstract void doPOST(_Session session, _WebFormData formData);
}
