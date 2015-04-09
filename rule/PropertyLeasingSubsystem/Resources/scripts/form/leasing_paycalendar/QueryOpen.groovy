package form.leasing_paycalendar

import java.util.Collection;
import java.util.Map
import kz.nextbase.script.*
import kz.nextbase.script.actions.*
import kz.nextbase.script.events.*;
import kz.nextbase.script.constants.*

class QueryOpen extends _FormQueryOpen {

	
	@Override
	public void doQueryOpen(_Session ses, _WebFormData webFormData, String lang) {
		
		
	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		def user = ses.getCurrentAppUser()
		
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = new _ActionBar(ses)
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)
		publishValue("title",getLocalizedWord("Платежный календарь", lang))
		def leasingholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getParentDocument().getValueNumber("leasingholder"));
		publishValue("leasingholder",doc.getParentDocument().getValueNumber("leasingholder"))
		publishValue("leasingholdername",leasingholder.getValueString("orgfullname"))
		publishValue("leasingholdernamekaz",leasingholder.getValueString("orgfullnamekaz"))
		publishValue("bin",leasingholder.getValueString("bin"))
		publishEmployer("author",doc.getAuthorID())
		publishValue("regnumber",doc.getParentDocument().getValueString("regnumber"))
		publishValue("content",doc.getParentDocument().getValueString("content"))
		publishValue("regdate",doc.getParentDocument().getValueString("regdate"))
		publishValue("contractdate",doc.getParentDocument().getValueString("contractdate"))
		publishValue("startcontractdate",doc.getParentDocument().getValueString("startcontractdate"))
		publishValue("endrentdate",doc.getParentDocument().getValueString("endrentdate"))
		publishValue("status",doc.getParentDocument().getValueString("status"))
		publishValue("sa",doc.getParentDocument().getValueString("sa"))
		publishValue("tarif",doc.getParentDocument().getValueString("tarif"))
		doc.addEditor(webFormData.getValue("author"))
		doc.addEditor("[operator]")
		publishValue("objectname",doc.getParentDocument().getViewText())
		publishValue("objecturl",doc.getParentDocument().getURL())
		publishValue("payperiod",doc.getValueString("payperiod"))
		publishValue("rent_days",doc.getValueString("rent_days"))
		publishValue("rent_months",doc.getValueString("rent_months"))
		publishValue("rent_hours",doc.getValueString("rent_hours"))
		publishValue("starttarif",  doc.getValueString("starttarif"))
		publishValue("lgottarif",   doc.getValueString("lgottarif"))
		publishValue("currenttarif",  doc.getValueString("currenttarif"))
		publishValue("rentobjtype",   doc.getValueString("rentobjtype"))
	}
}