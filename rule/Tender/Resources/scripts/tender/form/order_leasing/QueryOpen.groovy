package tender.form.order_leasing

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
		publishValue("objectname",ses.db.getDocumentByComplexID(parentDocType ,parentDocId).getViewText())
		publishValue("objecturl",ses.db.getDocumentByComplexID(parentDocType ,parentDocId).getURL())
		def obj = ses.db.getDocumentByComplexID(parentDocType ,parentDocId);
		def objform = obj.getDocumentForm()
		if(objform == 'buildings' || objform == 'rooms' || objform == 'structures' || objform == 'residentialobjects' || objform == 'land' || objform == 'monument'){
			println("недвижимость")	
			def totalarea =  obj.getValueString("totalarea").toInteger();
			def year =new Date().getYear()+1900;
			def mrp = ses.db.getGlossaryDocument("mrp","year='"+year+"'").getValueString("value").toInteger();
			def kt = ses.db.getGlossaryCustomFieldValueByDOCID(obj.getValueString("kt").toInteger(), "koef").replace(",", ".").toDouble();
			def ktr =obj.getValueString("ktr").replace(",", ".").toDouble();
			def fulltarif = totalarea * mrp * kt * ktr;
			def tarif = fulltarif
			def lgottarif = tarif/2;
			publishValue("fulltarif",fulltarif.toString())
			publishValue("lgottarif",lgottarif.toString())
			publishValue("tarif",tarif.toString())
			publishValue("rentobjtype","n")
		}else{
			def residualcost = obj.getValueString("residualcost").toInteger(); //остаточная стоимость
			def limitdepreciation = obj.getValueString("limitdepreciation").toInteger(); // предел амортизации
			def deterioration = obj.getValueString("deterioration").toInteger(); // износ
			def fulltarif = ((residualcost * limitdepreciation)/100);
			def tarif = fulltarif
			//def lgottarif = ((residualcost * limitdepreciation)/100)/2;
			def detertarif = ((residualcost * limitdepreciation)/100)*0.8;
			if(deterioration > 60){
				//def ponkoef = 0.8;
				tarif = detertarif;
			}
			def lgottarif = tarif/2;
			publishValue("fulltarif",fulltarif.toString())
			publishValue("lgottarif",lgottarif.toString())
			publishValue("detertarif",detertarif.toString())
			publishValue("tarif",tarif.toString())
		}
		
	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		def user = ses.getCurrentAppUser()
		
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = new _ActionBar(ses)
		
		/*if(doc.getEditMode() == _DocumentModeType.EDIT){
			actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		}
		actionBar.addAction(new _Action("Добавить акт приема", "Добавить акт приема", "add_acceptance_protocol"))*/
		actionBar.addAction(new _Action("Добавить акт приема-передачи", "Добавить акт приема-передачи", "add_transfer_protocol"))
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)
		
		publishValue("title",getLocalizedWord("Договор", lang) + " ")
		def leasingholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getValueNumber("leasingholder"));
		publishValue("leasingholder",doc.getValueNumber("leasingholder"))
		publishValue("leasingholdername",leasingholder.getValueString("orgfullname"))
		publishValue("leasingholdernamekaz",leasingholder.getValueString("orgfullnamekaz"))
		publishValue("bin",leasingholder.getValueString("bin"))
		publishEmployer("author",doc.getAuthorID())
		publishValue("regnumber",doc.getValueString("regnumber"))
		publishValue("content",doc.getValueString("content"))
		publishValue("regdate",doc.getValueString("regdate"))
		publishValue("contractdate",doc.getValueString("contractdate"))
		publishValue("startcontractdate",doc.getValueString("startcontractdate"))
		publishValue("endrentdate",doc.getValueString("endrentdate"))
		publishValue("status",doc.getValueString("status"))
		publishValue("sa",doc.getValueString("sa"))
		//publishValue("nanimatel",doc.getValueString("nanimatel"))
		publishValue("objectname",doc.getParentDocument().getViewText())
		publishValue("objecturl",doc.getParentDocument().getURL())
		publishValue("tarif",doc.getValueString("tarif"))
		publishValue("payperiod",doc.getValueString("payperiod"))
		publishValue("rent_days",doc.getValueString("rent_days"))
		publishValue("rent_months",doc.getValueString("rent_months"))
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}