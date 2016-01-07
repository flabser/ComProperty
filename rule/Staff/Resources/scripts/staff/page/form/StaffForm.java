package staff.page.form;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.struct._Employer;

/**
 * 
 * 
 * @author Kayra created 07-01-2016
 */

public abstract class StaffForm extends _DoPage {

	protected boolean validate(_WebFormData webFormData) {
		if (webFormData.getValueSilently("name").equals("")) {
			localizedMsgBox("field_name_is_empty");
			return false;
		}

		return true;
	}

	protected _ActionBar getSimpleActionBar(_Session ses, String lang) {
		_ActionBar actionBar = new _ActionBar(ses);
		_Employer user = ses.getCurrentAppUser();
		// if (user.hasRole("supervisor")) {
		actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), getLocalizedWord("save_close", lang), _ActionType.SAVE_AND_CLOSE));
		// }

		actionBar.addAction(new _Action(getLocalizedWord("close", lang), getLocalizedWord("just_close", lang), _ActionType.CLOSE));
		return actionBar;

	}

	@Override
	public abstract void doGET(_Session session, _WebFormData formData, String lang);

	@Override
	public abstract void doPOST(_Session session, _WebFormData formData, String lang);
}
