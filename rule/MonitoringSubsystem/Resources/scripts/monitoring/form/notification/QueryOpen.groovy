package monitoring.form.notification
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.actions._Action
import kz.nextbase.script.actions._ActionBar
import kz.nextbase.script.actions._ActionType
import kz.nextbase.script.constants._DocumentModeType
import kz.nextbase.script.events._FormQueryOpen

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

		publishValue("title",getLocalizedWord("Стратегические объекты - Аэропорт", lang))
		publishEmployer("author", ses.getCurrentAppUser().getUserID())
	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		def user = ses.getCurrentAppUser()
		
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = new _ActionBar(ses)
		
		if(doc.getEditMode() == _DocumentModeType.EDIT){
			//actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		}
		
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)
		
		publishValue("title",getLocalizedWord("Уведомление", lang) + " ")
		publishValue("balanceholdername",doc.getValueString("balanceholdername"))
		publishValue("balanceholderbin",doc.getValueString("balanceholderbin"))
		publishValue("balanceholderemail",doc.getValueString("balanceholderemail"))
		publishValue("invnumber",doc.getValueString("invnumber"))
		publishValue("description",doc.getValueString("description"))
		publishValue("acceptancedate",doc.getValueString("acceptancedate"))
		publishValue("home",doc.getValueString("home"))
		publishValue("notificationtype",doc.getValueString("notificationtype"))
		publishValue("sentdate",doc.getValueString("sentdate"))
		publishValue("object",doc.getValueString("object"))
		publishValue("objectlink",doc.getValueString("objectlink"))
		publishValue("propertycode",doc.getValueString("propertycode"))
		publishValue("region",doc.getValueString("region"))
		publishValue("city",doc.getValueString("city"))
		publishValue("district",doc.getValueString("district"))
		publishValue("street",doc.getValueString("street"))
		publishValue("regnumber",doc.getValueString("regnumber"))
		publishValue("content",doc.getValueString("content"))
		publishValue("comobjectname",doc.getValueString("comobjectname"))
		publishValue("ordername",doc.getValueString("ordername"))
		publishValue("comobjecturl",doc.getValueString("comobjecturl"))
		publishValue("orderurl",doc.getValueString("orderurl"))
		publishValue("regdate",doc.getValueString("regdate"))
        publishValue("viewtext",doc.getValueString("viewtext"))
		publishValue("endcontractdate",doc.getValueString("endcontractdate"))
	}
}