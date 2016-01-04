package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.struct._Employer;

public abstract class ReferenceView extends _DoPage {

	protected _ActionBar getSimpleActionBar(_Session session, String type, String lang) {
		_ActionBar actionBar = new _ActionBar(session);
		_Employer user = session.getCurrentAppUser();
		// if (user.hasRole("registrator")) {
		_Action newDocAction = new _Action(getLocalizedWord("new_" + type, lang), getLocalizedWord("add_new_" + type, lang), "new_" + type);
		newDocAction.setURL("Provider?id=" + type + "_form&key=");
		actionBar.addAction(newDocAction);
		// }
		// if (user.hasRole("system_administrator")) {
		actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), getLocalizedWord("del_document", lang), _ActionType.DELETE_DOCUMENT));
		// }
		return actionBar;
	}

	@Override
	public abstract void doGET(_Session session, _WebFormData formData, String lang);

	@Override
	public abstract void doPOST(_Session session, _WebFormData formData, String lang);
}
