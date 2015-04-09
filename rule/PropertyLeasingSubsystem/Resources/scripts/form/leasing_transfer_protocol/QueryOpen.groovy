package form.leasing_transfer_protocol

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

		publishValue("title",getLocalizedWord("Акт приема-передачи объекта в аренду", lang))
		publishEmployer("author", ses.getCurrentAppUser().getUserID())
		def parentDocId = webFormData.getValue("parentdocid").toInteger();
		def parentDocType = webFormData.getValue("parentdoctype").toInteger()
		def pdoc = ses.currentDatabase.getDocumentByComplexID(parentDocType, parentDocId);
		publishValue("orderleasingurl",pdoc.getURL())
		def leasingholder = ses.getCurrentDatabase().getDocumentByComplexID(896, pdoc.getValueNumber("leasingholder"));
		publishValue("leasingholder",pdoc.getValueNumber("leasingholder"))
		publishValue("regnumber",pdoc.getValueString("regnumber"))
		publishValue("leasingholdername",leasingholder.getValueString("orgfullname"))
		publishValue("leasingholdernamekaz",leasingholder.getValueString("orgfullnamekaz"))
		publishValue("bin",leasingholder.getValueString("bin"))
		publishValue("objectname",pdoc.getGrandParentDocument().getViewText())
		publishValue("objecturl",pdoc.getGrandParentDocument().getURL())
		publishGlossaryValue("region",pdoc.getGrandParentDocument().getValueGlossary("region"))
		publishGlossaryValue("city",pdoc.getGrandParentDocument().getValueGlossary("city"))
		publishGlossaryValue("district",pdoc.getGrandParentDocument().getValueGlossary("district"))
		publishGlossaryValue("street",pdoc.getGrandParentDocument().getValueGlossary("street"))
		publishValue("home",pdoc.getGrandParentDocument().getValueString("home"))
		publishValue("totalarea",pdoc.getGrandParentDocument().getValueString("totalarea"))
	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		publishValue("title",getLocalizedWord("Акт приема-передачи объекта в аренду", lang) + " ")
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
		if(doc.getParentDocument().getValueNumber("leasingholder") != 0){
			def leasingholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getParentDocument().getValueNumber("leasingholder"));
			publishValue("leasingholder",doc.getParentDocument().getValueNumber("leasingholder"))
			publishValue("leasingholdername",leasingholder.getValueString("orgfullname"))
			publishValue("leasingholdernamekaz",leasingholder.getValueString("orgfullnamekaz"))
			publishValue("bin",leasingholder.getValueString("bin"))
		}
		publishValue("objectname",doc.getGrandParentDocument().getViewText())
		publishValue("objecturl",doc.getGrandParentDocument().getURL())
		publishValue("regnumber",doc.getParentDocument().getValueString("regnumber"))
		publishValue("orderleasingurl",doc.getParentDocument().getURL())
		publishGlossaryValue("region",doc.getGrandParentDocument().getValueGlossary("region"))
		publishGlossaryValue("city",doc.getGrandParentDocument().getValueGlossary("city"))
		publishGlossaryValue("district",doc.getGrandParentDocument().getValueGlossary("district"))
		publishGlossaryValue("street",doc.getGrandParentDocument().getValueGlossary("street"))
		publishValue("home",doc.getGrandParentDocument().getValueString("home"))
		publishValue("totalarea",doc.getGrandParentDocument().getValueString("totalarea"))
		publishValue("techstate",doc.getValueString("techstate"))
		publishValue("turnover",doc.getValueString("turnover"))
		publishValue("accept",doc.getValueString("accept"))
		publishValue("regdate",doc.getValueString("regdate"))
		
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}