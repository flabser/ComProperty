package accountant.form.uploadupdating

import java.util.Map
import kz.nextbase.script.*
import kz.nextbase.script.actions.*
import kz.nextbase.script.events.*;
import kz.nextbase.script.task._Control
import kz.nextbase.script.task._Task

class QueryOpen extends _FormQueryOpen{

	@Override
	public void doQueryOpen(_Session session, _WebFormData webFormData, String lang) {
		publishValue("title",getLocalizedWord("Страница загрузки обновлений", lang))

		def actionBar = session.createActionBar();
		actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		//actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)

	}


	@Override
	public void doQueryOpen(_Session session, _Document doc, _WebFormData webFormData, String lang) {
		publishValue("title",getLocalizedWord("Страница загрузки обновлений", lang) + ":" + doc.getViewText())
		
	}

	private getActionBar(_Session session){
		return true

	}

}