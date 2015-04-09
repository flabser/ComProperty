package tender.form.rooms

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

		publishValue("title",getLocalizedWord("Недвижимое имущество - Помещение", lang))
		publishEmployer("author", ses.getCurrentAppUser().getUserID())
		def parent_document = ses.db.getDocumentByComplexID(896, webFormData.getNumberValueSilently("parentdocid", 0))
		publishGlossaryValue("limitdepreciation",parent_document.getValueString("limitdepreciation").toInteger())
		publishGlossaryValue("kt",parent_document.getValueString("kt").toInteger())
		publishGlossaryValue("region",parent_document.getValueGlossary("region"))
		publishGlossaryValue("city",parent_document.getValueGlossary("city"))
		publishGlossaryValue("district",parent_document.getValueGlossary("district"))
		publishGlossaryValue("street",parent_document.getValueGlossary("street"))
		publishValue("home",parent_document.getValueString("home"))
		publishValue("appartament",parent_document.getValueString("appartament"))
		publishValue("yearconstruction",parent_document.getValueString("yearconstruction"))
		publishValue("buildingsyears",doc.getValueString("buildingsyears"))
		publishGlossaryValue("material",parent_document.getValueGlossary("material"))
	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		def user = ses.getCurrentAppUser()
		
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = new _ActionBar(ses)
		
		if (ses.currentAppUser.hasRole("rent")){
			actionBar.addAction(new _Action("Подать заявление на аренду", "Подать заявление на аренду", "ADD_RENT_REQUEST"))
		}
		if (ses.currentAppUser.hasRole("tender")){
			actionBar.addAction(new _Action("Подать заявку на участие в тендере", "Подать заявку на участие в тендере", "ADD_TENDER_REQUEST"))
		}
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)
		
		publishValue("title",getLocalizedWord("Недвижимое имущество - Помещение", lang) + " ")
		publishEmployer("author",doc.getAuthorID())
		if(doc.getValueNumber("balanceholder") != 0){
			def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getParentDocument().getValueNumber("balanceholder"));
			publishValue("balanceholder",doc.getParentDocument().getValueNumber("balanceholder"))
			publishValue("balanceholdername",balanceholder.getValueString("orgfullname"))
			publishValue("balanceholdernamekaz",balanceholder.getValueString("orgfullnamekaz"))
			publishValue("bin",balanceholder.getValueString("bin"))
		}
		publishValue("note",doc.getValueString("note"))
		publishValue("address",doc.getValueString("address"))
		publishValue("objectname",doc.getValueString("objectname"))
		publishValue("invnumber",doc.getValueString("invnumber"))
		publishValue("limitdepreciation",doc.getValueString("limitdepreciation"))
		publishValue("description",doc.getValueString("description"))
		publishGlossaryValue("propertycode",doc.getValueString("propertycode").toInteger())
		publishGlossaryValue("kt",doc.getParentDocument().getValueString("kt").toInteger())
		publishValue("acceptancedate",doc.getValueString("acceptancedate"))
		publishGlossaryValue("region",doc.getParentDocument().getValueGlossary("region"))
		publishGlossaryValue("city",doc.getParentDocument().getValueGlossary("city"))
		publishGlossaryValue("district",doc.getParentDocument().getValueGlossary("district"))
		publishGlossaryValue("street",doc.getParentDocument().getValueGlossary("street"))
		publishValue("home",doc.getParentDocument().getValueString("home"))
		publishValue("appartament",doc.getParentDocument().getValueString("appartament"))
		publishValue("regdoc",doc.getValueString("regdoc"))
		publishValue("originalcost",doc.getValueString("originalcost"))
		publishValue("balancecost",doc.getValueString("balancecost"))
		publishValue("estimatedcost",doc.getValueString("estimatedcost"))
		publishValue("residualcost",doc.getValueString("residualcost"))
		publishValue("receiptbasisingproperty",doc.getValueString("receiptbasisingproperty"))
		publishValue("receiptbasisinbalance",doc.getValueString("receiptbasisinbalance"))
		publishValue("cumulativedepreciation",doc.getValueString("cumulativedepreciation"))
		publishValue("deterioration",doc.getValueString("deterioration"))
		publishValue("repair",doc.getValueString("repair"))
		publishValue("depreciating",doc.getValueString("depreciating"))
		publishValue("pledge",doc.getValueString("pledge"))
		publishValue("arrestingbyacourtdecision",doc.getValueString("arrestingbyacourtdecision"))
		publishValue("legalclaim",doc.getValueString("legalclaim"))
		publishValue("orderofremovalfrombalance",doc.getValueString("orderofremovalfrombalance"))
		publishValue("assignment",doc.getValueString("assignment"))
		publishValue("ktr",doc.getValueString("ktr"))
		
		publishValue("countfloors",doc.getValueString("countfloors"))
		publishValue("totalarea",doc.getValueString("totalarea"))
		publishValue("extratotalarea",doc.getValueString("extratotalarea"))
		publishValue("usefularea",doc.getValueString("usefularea"))
		publishValue("landarea",doc.getValueString("landarea"))
		publishValue("extralandarea",doc.getValueString("extralandarea"))
		publishValue("yearconstruction",doc.getParentDocument().getValueString("yearconstruction"))
		publishValue("сellarexistence",doc.getValueString("сellarexistence"))
		publishGlossaryValue("material",doc.getParentDocument().getValueGlossary("material"))
		
		
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}