package monitoring.form.watersystem

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

		publishValue("title",getLocalizedWord("Объекты инженерной инфраструктуры - Водопровод и канализация", lang))
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
		
		publishValue("title",getLocalizedWord("Объекты инженерной инфраструктуры - Водопровод и канализация", lang))
		publishEmployer("author",doc.getAuthorID())
		if(doc.getField('balanceholder') && doc.getValueNumber("balanceholder") != 0){
			def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getValueNumber("balanceholder"));
			publishValue("balanceholder",doc.getValueNumber("balanceholder"))
			publishValue("balanceholdername",balanceholder.getValueString("orgfullname"))
			publishValue("balanceholdernamekaz",balanceholder.getValueString("orgfullnamekaz"))
			publishValue("bin",balanceholder.getValueString("bin"))
		}
		publishValue("note",doc.getValueString("note"))
		publishValue("objectname",doc.getValueString("objectname"))
		publishValue("invnumber",doc.getValueString("invnumber"))
		publishValue("description",doc.getValueString("description"))
		publishValue("limitdepreciation",doc.getValueString("limitdepreciation"))
		if(doc.getField('propertycode')){
			publishGlossaryValue("propertycode",doc.getValueString("propertycode").toInteger())
		}
		publishValue("acceptancedate",doc.getValueString("acceptancedate"))
		if(doc.getField('region')){
			publishGlossaryValue("region",doc.getValueGlossary("region"))
		}
		if(doc.getField('city')){
			publishGlossaryValue("city",doc.getValueGlossary("city"))
		}
		if(doc.getField('district')){
			publishGlossaryValue("district",doc.getValueGlossary("district"))
		}
		publishValue("isrented",doc.getValueString("isrented"))
		if(doc.getField('street')){
			publishGlossaryValue("street",doc.getValueGlossary("street"))
		}
		publishValue("address",doc.getValueString("address"))
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
		publishValue("pledge",doc.getValueString("pledge"))
		publishValue("arrestingbyacourtdecision",doc.getValueString("arrestingbyacourtdecision"))
		publishValue("legalclaim",doc.getValueString("legalclaim"))
		publishValue("orderofremovalfrombalance",doc.getValueString("orderofremovalfrombalance"))
		publishValue("ktr",doc.getValueString("ktr"))
		
		publishValue("totalarea",doc.getValueString("totalarea"))
		publishValue("extratotalarea",doc.getValueString("extratotalarea"))
		publishValue("landtotalarea",doc.getValueString("landtotalarea"))
		publishValue("extralandarea",doc.getValueString("extralandarea"))
		publishValue("yearconstruction",doc.getValueString("yearconstruction"))
		publishValue("buildingsyears",doc.getValueString("buildingsyears"))
		if(doc.getField('material')){
			publishGlossaryValue("material",doc.getValueGlossary("material"))
		}
		publishValue("technicalpassport",doc.getValueString("technicalpassport"))
		publishValue("stateactearth",doc.getValueString("stateactearth"))
		publishValue("propertyarticlein",doc.getValueString("propertyarticlein"))
		publishValue("restransferacceptance",doc.getValueString("restransferacceptance"))
		
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}