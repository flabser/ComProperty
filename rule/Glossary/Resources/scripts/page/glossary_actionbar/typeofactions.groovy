package page.glossary_actionbar

import java.util.ArrayList;
import java.util.Map;
import kz.nextbase.script.*;
import kz.nextbase.script.actions.*
import kz.nextbase.script.events._DoScript

class typeofactions extends _DoScript { 

	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		def actionBar = new _ActionBar(session);
		def user = session.getCurrentAppUser();
		if (user.hasRole(["registrator"])){
			def newDocAction = new _Action(getLocalizedWord("Добавить", lang),getLocalizedWord("Добавить вид акций",lang), "new_glossary")
			newDocAction.setURL("Provider?type=edit&id=typeofactions&key=")
			actionBar.addAction(newDocAction);
		}
		if(user.hasRole(["system_administrator"])){
			actionBar.addAction(new _Action(getLocalizedWord("Удалить документ", lang),getLocalizedWord("Удалить документ", lang),_ActionType.DELETE_DOCUMENT));
		}
		setContent(actionBar);
	}
}

