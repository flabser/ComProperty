package form.individuals

import java.util.Collection;
import java.util.Map
import kz.nextbase.script.*
import kz.nextbase.script.actions.*
import kz.nextbase.script.events.*;
import kz.nextbase.script.constants.*

class QueryOpen extends _FormQueryOpen {

	
	@Override
	public void doQueryOpen(_Session ses, _WebFormData webFormData, String lang) {
		def user = ses.getCurrentAppUser()
		
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = ses.createActionBar();
		actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)

		publishValue("title",getLocalizedWord("Физическое лицо", lang))
		publishEmployer("author", ses.getCurrentAppUser().getUserID())
	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		def user = ses.getCurrentAppUser()
		
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = new _ActionBar(ses)
		
		if(doc.getEditMode() == _DocumentModeType.EDIT){
			actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		}
		
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)
		
		publishValue("title",getLocalizedWord("Физическое лицо", lang) + " ")
		
		publishEmployer("author",doc.getAuthorID())
		
		publishValue("orgfullname",doc.getValueString("orgfullname"))
		publishValue("orgfullnamekaz",doc.getValueString("orgfullnamekaz"))
		publishValue("fullactualaddress",doc.getValueString("fullactualaddress"))
		publishValue("phone",doc.getValueString("phone"))
		publishValue("fax",doc.getValueString("fax"))
		publishValue("email",doc.getValueString("email"))
		publishValue("fio",doc.getValueString("fio"))
		publishValue("iin",doc.getValueString("iin"))
		publishGlossaryValue("oked",doc.getValueNumber("oked"))
		publishValue("orgregdate",doc.getValueString("orgregdate"))
		publishValue("orgregnumber",doc.getValueString("orgregnumber"))
		publishValue("regdateorder",doc.getValueString("regdateorder"))
		publishValue("numberorder",doc.getValueString("numberorder"))
		publishGlossaryValue("typeactivity",doc.getValueNumber("typeactivity"))
		publishValue("privilege",doc.getValueString("privilege"))
        publishValue("registration",doc.getValueString("registration"))
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}