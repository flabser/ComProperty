package legalentity.page.actionbar

import java.util.ArrayList;
import java.util.Map;
import kz.nextbase.script.*;
import kz.nextbase.script.actions.*
import kz.nextbase.script.events._DoScript

class material extends _DoScript { 

	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		def actionBar = new _ActionBar(session);
		def user = session.getCurrentAppUser();
			if (user.hasRole(["registrator","system_administrator"])){
				def newDocAction = new _Action(getLocalizedWord("Новый материал", lang),getLocalizedWord("Добавить новый материал постройки",lang), "new_glossary")
				newDocAction.setURL("Provider?type=edit&id=material&key=")
				actionBar.addAction(newDocAction);
			}
			if (user.hasRole(["registrator","system_administrator"])){
				actionBar.addAction(new _Action(getLocalizedWord("Удалить документ", lang),getLocalizedWord("Удалить документ", lang),_ActionType.DELETE_DOCUMENT));
			}
		setContent(actionBar);
	}
}

