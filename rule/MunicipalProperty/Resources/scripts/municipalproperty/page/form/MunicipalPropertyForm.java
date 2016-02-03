package municipalproperty.page.form;

import kz.flabs.localization.LanguageType;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import municipalproperty.model.Property;

/**
 * Common abstract class for form of the Municipal Property application. Use it
 * to code some shared methods for all forms of the application
 * 
 * @author Kayra created 27-01-2016
 */

public abstract class MunicipalPropertyForm extends _DoPage {

	protected _ActionBar getActionBar(_Session ses, LanguageType lang, Property entity) {
		_ActionBar actionBar = new _ActionBar(ses);
		actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), "", _ActionType.SAVE_AND_CLOSE));
		actionBar.addAction(new _Action(getLocalizedWord("close", lang), "", _ActionType.CLOSE));
		_Action transferAction = new _Action(getLocalizedWord("transfer", lang), getLocalizedWord("transfer", lang), "transfer");
		transferAction.setURL("Provider?id=transfer");
		// actionBar.addAction(transferAction);
		if (entity.isTagged("on_balance")) {
			_Action writtenOffAction = new _Action(getLocalizedWord("written_off", lang), getLocalizedWord("written_off", lang), "written_off");
			writtenOffAction.setURL("Provider?id=writtenoff");
			// actionBar.addAction(writtenOffAction);
		}
		return actionBar;
	}

	@Override
	public abstract void doGET(_Session session, _WebFormData formData, LanguageType lang);

	@Override
	public abstract void doPOST(_Session session, _WebFormData webFormData, LanguageType lang);

}
