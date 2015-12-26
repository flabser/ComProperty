package monitoring.form.shareblocks

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
		publishValue("isrented","0")

		publishValue("title",getLocalizedWord("Движимое имущество - Государственные пакеты акций", lang))
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
		
		publishValue("title",getLocalizedWord("Движимое имущество - Государственные пакеты акций", lang) + " ")
		publishEmployer("author",doc.getAuthorID())
		def registryholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getValueNumber("registryholder"));
		publishValue("registryholder",doc.getValueNumber("registryholder"))
		publishValue("registryholdername",registryholder.getValueString("orgfullname"))
		publishGlossaryValue("typeofactions",doc.getValueNumber("typeofactions"))
		publishGlossaryValue("formofactions",doc.getValueNumber("formofactions"))
		publishValue("note",doc.getValueString("note"))
		publishValue("parvalue",doc.getValueString("parvalue"))
		if(doc.getField('balanceholder') && doc.getValueNumber("balanceholder") != 0){
			def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getValueNumber("balanceholder"));
			publishValue("balanceholder",doc.getValueNumber("balanceholder"))
			publishValue("balanceholdername",balanceholder.getValueString("orgfullname"))
			publishValue("balanceholdernamekaz",balanceholder.getValueString("orgfullnamekaz"))
			publishValue("bin",balanceholder.getValueString("bin"))
		}
		publishValue("kof",doc.getValueString("kof"))
		publishValue("kuf",doc.getValueString("kuf"))
		publishValue("objectname",doc.getValueString("objectname"))
		publishValue("invnumber",doc.getValueString("invnumber"))
		publishValue("description",doc.getValueString("description"))
		publishValue("limitdepreciation",doc.getValueString("limitdepreciation"))
		if(doc.getField('propertycode')){
			publishGlossaryValue("propertycode",doc.getValueString("propertycode"))
		}
		publishValue("acceptancedate",doc.getValueString("acceptancedate"))
		publishGlossaryValue("region",doc.getValueNumber("region"))
		publishGlossaryValue("city",doc.getValueNumber("city"))
		publishGlossaryValue("district",doc.getValueNumber("district"))
		publishGlossaryValue("street",doc.getValueNumber("street"))
		publishValue("address",doc.getValueString("address"))
		publishValue("home",doc.getValueString("home"))
		publishValue("appartament",doc.getValueString("appartament"))
		publishValue("isrented",doc.getValueString("isrented"))
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
		publishValue("amount",doc.getValueString("amount"))
		publishValue("propertyarticlein",doc.getValueString("propertyarticlein"))
		publishValue("restransferacceptance",doc.getValueString("restransferacceptance"))
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}