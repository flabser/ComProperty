package monitoring.form.transfer_protocol

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

		publishValue("title",getLocalizedWord("Акт приема-передачи объекта", lang))
		publishEmployer("author", ses.getCurrentAppUser().getUserID())
		def parentDocId = webFormData.getValue("parentdocid").toInteger();
		def parentDocType = webFormData.getValue("parentdoctype").toInteger()
		def pdoc = ses.currentDatabase.getDocumentByComplexID(parentDocType, parentDocId);
		publishValue("contracturl",pdoc.getURL())
		publishValue("regnumber",pdoc.getValueString("regnumber"))
		publishValue("contractregdate",pdoc.getValueString("regdate"))
		def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, pdoc.getGrandParentDocument().getValueNumber("balanceholder"));
		publishValue("balanceholder",pdoc.getGrandParentDocument().getValueNumber("balanceholder"))
		publishValue("balanceholdername",balanceholder.getValueString("orgfullname"))
		publishValue("balanceholdernamekaz",balanceholder.getValueString("orgfullnamekaz"))
		publishValue("bin",balanceholder.getValueString("bin"))
		publishValue("objectname",pdoc.getGrandParentDocument().getViewText())
		publishValue("objecturl",pdoc.getGrandParentDocument().getURL())
		publishGlossaryValue("region",pdoc.getGrandParentDocument().getValueGlossary("region"))
		publishGlossaryValue("city",pdoc.getGrandParentDocument().getValueGlossary("city"))
		publishGlossaryValue("district",pdoc.getGrandParentDocument().getValueGlossary("district"))
		publishGlossaryValue("street",pdoc.getGrandParentDocument().getValueGlossary("street"))
		publishValue("home",pdoc.getGrandParentDocument().getValueString("home"))
		publishValue("totalarea",pdoc.getGrandParentDocument().getValueString("totalarea"))
		def propertycode = pdoc.getGrandParentDocument().getValueString("propertycode").toInteger();
		publishValue("propertycode",ses.currentDatabase.getGlossaryDocument(propertycode).getValueString("name"))
	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		publishValue("title",getLocalizedWord("Акт приема-передачи объекта", lang) + " ")
		def user = ses.getCurrentAppUser()
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = new _ActionBar(ses)
		
		if(doc.getEditMode() == _DocumentModeType.EDIT){
			//actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		}
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)
		
		publishEmployer("author",doc.getAuthorID())
		if(doc.getGrandParentDocument().getValueNumber("balanceholder") != 0){
			def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getGrandParentDocument().getValueNumber("balanceholder"));
			publishValue("balanceholder",doc.getGrandParentDocument().getValueNumber("balanceholder"))
			publishValue("balanceholdername",balanceholder.getValueString("orgfullname"))
			publishValue("balanceholdernamekaz",balanceholder.getValueString("orgfullnamekaz"))
			publishValue("bin",balanceholder.getValueString("bin"))
		}
		publishValue("objectname",doc.getGrandParentDocument().getViewText())
		publishValue("objecturl",doc.getGrandParentDocument().getURL())
		publishValue("regnumber",doc.getParentDocument().getValueString("regnumber"))
		publishValue("orderleasingurl",doc.getParentDocument().getURL())
		if(doc.getField('region')){
			publishGlossaryValue("region",doc.getGrandParentDocument().getValueGlossary("region"))
		}
		if(doc.getField('city')){
			publishGlossaryValue("city",doc.getGrandParentDocument().getValueGlossary("city"))
		}
		if(doc.getField('district')){
			publishGlossaryValue("district",doc.getGrandParentDocument().getValueGlossary("district"))
		}
		if(doc.getField('street')){
			publishGlossaryValue("street",doc.getGrandParentDocument().getValueGlossary("street"))
		}
		publishValue("home",doc.getGrandParentDocument().getValueString("home"))
		publishValue("totalarea",doc.getGrandParentDocument().getValueString("totalarea"))
		publishValue("techstate",doc.getValueString("techstate"))
		publishValue("turnover",doc.getValueString("turnover"))
		publishValue("accept",doc.getValueString("accept"))
		publishValue("regdate",doc.getValueString("regdate"))
		def propertycode = doc.getGrandParentDocument().getValueString("propertycode").toInteger();
		publishValue("propertycode",ses.currentDatabase.getGlossaryDocument(propertycode).getValueString("name"))
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}