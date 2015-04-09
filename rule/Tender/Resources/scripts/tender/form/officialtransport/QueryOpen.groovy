package tender.form.officialtransport

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
		//actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)

		publishValue("title",getLocalizedWord("Транспорт - Служебный транспорт", lang))
		publishEmployer("author", ses.getCurrentAppUser().getUserID())
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
		
		publishValue("title",getLocalizedWord("Транспорт - Служебный транспорт", lang) + " ")
		publishEmployer("author",doc.getAuthorID())
		publishValue("limitdepreciation",doc.getValueString("limitdepreciation"))
		if(doc.getValueNumber("balanceholder") != 0){
			def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getValueNumber("balanceholder"));
			publishValue("balanceholder",doc.getValueNumber("balanceholder"))
			publishValue("balanceholdername",balanceholder.getValueString("orgfullname"))
			publishValue("balanceholdernamekaz",balanceholder.getValueString("orgfullnamekaz"))
			publishValue("bin",balanceholder.getValueString("bin"))
		}
		publishValue("note",doc.getValueString("note"))
		publishValue("address",doc.getValueString("address"))
		publishValue("objectname",doc.getValueString("objectname"))
		publishValue("invnumber",doc.getValueString("invnumber"))
		publishValue("description",doc.getValueString("description"))
		publishGlossaryValue("propertycode",doc.getValueString("propertycode").toInteger())
		publishValue("acceptancedate",doc.getValueString("acceptancedate"))
		publishGlossaryValue("region",doc.getValueGlossary("region"))
		publishGlossaryValue("city",doc.getValueGlossary("city"))
		publishGlossaryValue("district",doc.getValueGlossary("district"))
		publishGlossaryValue("street",doc.getValueGlossary("street"))
		publishValue("home",doc.getValueString("home"))
		publishValue("appartament",doc.getValueString("appartament"))
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
		publishValue("grnz",doc.getValueString("grnz"))
		publishValue("srts",doc.getValueString("srts"))
		publishValue("pledge",doc.getValueString("pledge"))
		publishValue("arrestingbyacourtdecision",doc.getValueString("arrestingbyacourtdecision"))
		publishValue("legalclaim",doc.getValueString("legalclaim"))
		publishValue("orderofremovalfrombalance",doc.getValueString("orderofremovalfrombalance"))
		
		publishValue("enginenumber",doc.getValueString("enginenumber"))
		publishValue("bodynumber",doc.getValueString("bodynumber"))
		publishValue("model",doc.getValueString("model"))
		publishValue("yearrelease",doc.getValueString("yearrelease"))
		publishValue("category",doc.getValueString("category"))
		publishValue("color",doc.getValueString("color"))
		publishValue("powerengine",doc.getValueString("powerengine"))
		publishValue("weightwithoutload",doc.getValueString("weightwithoutload"))
		publishValue("maxload",doc.getValueString("maxload"))
		
		publishValue("volumeengine",doc.getValueString("volumeengine"))
		publishValue("condition",doc.getValueString("condition"))
		publishValue("fueltype",doc.getValueString("fueltype"))
		publishValue("expenditurefuel",doc.getValueString("expenditurefuel"))
		publishValue("carrun",doc.getValueString("carrun"))
		publishValue("normperiodmaintenance",doc.getValueString("normperiodmaintenance"))
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}