package tender.form.userprofile

import java.util.Map
import kz.nextbase.script.*
import kz.nextbase.script.actions.*
import kz.nextbase.script.events.*;
import kz.nextbase.script.constants.*

class QueryOpen extends _FormQueryOpen {

	
	@Override
	public void doQueryOpen(_Session session, _WebFormData webFormData, String lang) {
				
	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
	
		def emp = ses.getCurrentAppUser()
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = new _ActionBar(ses)
		
		if(doc.getEditMode() == _DocumentModeType.EDIT){
			actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		}
		
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)

		publishValue("title",getLocalizedWord("Сотрудник ", lang))
        // -1 физ -2 юр
        if(emp.getRank() == -1){
            publishValue("title",getLocalizedWord("Физическое лицо ", lang));
            publishValue("fullname_caption",getLocalizedWord("Ф.И.О.", lang));
            publishValue("shortname_caption",getLocalizedWord("Полное наименование учреждения (если ИП)", lang));
        }
        else if(emp.getRank() == -2){
            publishValue("title",getLocalizedWord("Юридическое лицо ", lang));
            publishValue("fullname_caption",getLocalizedWord("Наименование", lang));
            publishValue("shortname_caption",getLocalizedWord("Ф.И.О руководителя", lang));
        }else{
            publishValue("fullname_caption",getLocalizedWord("Полное имя", lang));
            publishValue("shortname_caption",getLocalizedWord("Сокращенное имя", lang));
        }

        publishValue("shortname",emp.getValueString("shortname"))
        publishEmployer("fullname", emp.getUserID())

		publishValue("userid",emp.getValueString("userid"))
		publishValue("countdocinview",emp.getValueString("countdocinview"))
		publishValue("lang",emp.getValueString("lang"))
		publishEmployer("skin",doc.getValueString("skin"))
		publishValue("email",doc.getValueString("email"))
		publishValue("group", emp.getListOfGroups())
		publishValue("role", emp.getListOfRoles())
	}

}