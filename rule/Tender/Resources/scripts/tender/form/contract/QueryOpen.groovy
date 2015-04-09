package tender.form.contract

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

		publishValue("title",getLocalizedWord("Новый договор", lang))
		publishEmployer("author", ses.getCurrentAppUser().getUserID())
		def parentDocId = webFormData.getValue("parentdocid").toInteger();
		def parentDocType = webFormData.getValue("parentdoctype").toInteger()
		publishValue("objectname",ses.dataBase.getDocumentByComplexID(parentDocType ,parentDocId).getViewText())
		publishValue("objecturl",ses.dataBase.getDocumentByComplexID(parentDocType ,parentDocId).getURL())
		publishValue("regnumber",ses.dataBase.getDocumentByComplexID(parentDocType ,parentDocId).getViewTextList()[1].toString())
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
		
		publishValue("title",getLocalizedWord("Договор", lang) + " ")
		publishEmployer("author",doc.getAuthorID())
		publishValue("regnumber",doc.getValueString("regnumber"))
		publishValue("content",doc.getValueString("content"))
		publishValue("regdate",doc.getValueString("regdate"))
		publishValue("endcontractdate",doc.getValueString("endcontractdate"))
		publishValue("status",doc.getValueString("status"))
		publishValue("objectname",doc.getParentDocument().getViewText())
		publishValue("objecturl",doc.getParentDocument().getURL())
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}