package reference.page.actionbar;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.struct._Employer;

/**
 * Created by Kaira on 24/12/15.
 */

public class Districts extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		_ActionBar actionBar = new _ActionBar(session);
		_Employer user = session.getCurrentAppUser();
		if (user.hasRole("registrator")) {
			_Action newDocAction = new _Action(getLocalizedWord("new_district", lang),
					getLocalizedWord("add_new_district", lang), "new_district");
			newDocAction.setURL("Provider?id=district_form&key=");
			actionBar.addAction(newDocAction);
		}
		if (user.hasRole("system_administrator")) {
			actionBar.addAction(new _Action(getLocalizedWord("del_document", lang),
					getLocalizedWord("del_document", lang), _ActionType.DELETE_DOCUMENT));
		}
		setContent(actionBar);

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
