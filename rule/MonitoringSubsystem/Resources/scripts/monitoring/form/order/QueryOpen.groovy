package monitoring.form.order
import kz.nextbase.script._Document
import kz.nextbase.script._Exception
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

		publishValue("title",getLocalizedWord("Новое постановление", lang))
		publishEmployer("author", ses.getCurrentAppUser().getUserID())
		def parentDocId = webFormData.getValue("parentdocid").toInteger();
		def parentDocType = webFormData.getValue("parentdoctype").toInteger()
		publishValue("objectname",ses.dataBase.getDocumentByComplexID(parentDocType ,parentDocId).getViewText())
		publishValue("objecturl",ses.dataBase.getDocumentByComplexID(parentDocType ,parentDocId).getURL())
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
		
		publishValue("title",getLocalizedWord("Постановление", lang) + " ")
		publishEmployer("author",doc.getAuthorID())
		publishValue("regnumber",doc.getValueString("regnumber"))
		publishValue("content",doc.getValueString("content"))
		publishValue("regdate",doc.getValueString("regdate"))
		publishValue("contractdate",doc.getValueString("contractdate"))
		publishValue("status",doc.getValueString("status"))
        if (doc.getParentDocument()) {
            publishValue("objectname",doc.getParentDocument().getViewText())
            publishValue("objecturl",doc.getParentDocument().getURL())
        }

		if (doc.getValueNumber("organization") != 0) {
            def organization = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getValueNumber("organization"));
            publishValue("organization",doc.getValueNumber("organization"))
            publishValue("organizationname",organization.getValueString("orgfullname"))
            publishValue("organizationnamekaz",organization.getValueString("orgfullnamekaz"))
            publishValue("bin",organization.getValueString("bin"))
        }
		if(doc.getField('propertycode')){
			publishGlossaryValue("propertycode",doc.getValueString("propertycode").toInteger())
		}
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}