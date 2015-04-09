package form.leasing_tram

import java.text.SimpleDateFormat
import java.util.Map
import kz.nextbase.script.*
import kz.nextbase.script.events.*
import kz.nextbase.script._Helper
import kz.flabs.servlets.sitefiles.UploadedFile

class QuerySave extends _FormQuerySave {

	@Override
	public void doQuerySave(_Session session, _Document doc, _WebFormData webFormData, String lang) {

		println(webFormData)
		
		boolean v = validate(webFormData)
		if(v == false){
			stopSave()
			return
		}
		doc.setForm("tram");
		doc.addStringField("note",webFormData.getValueSilently("note"))
		doc.addStringField("address", webFormData.getValueSilently("address"))
		doc.addNumberField("balanceholder", webFormData.getNumberValueSilently("balanceholder", 0))
		doc.addStringField("invnumber", webFormData.getValueSilently("invnumber"))
		doc.addStringField("description", webFormData.getValueSilently("description"))
		doc.addStringField("objectname",webFormData.getValueSilently("objectname"))
		doc.addStringField("propertycode", webFormData.getValueSilently("propertycode"))
		doc.addGlossaryField("region", webFormData.getValueSilently("region").toInteger())
		doc.addGlossaryField("city", webFormData.getValueSilently("city").toInteger())
		doc.addGlossaryField("district", webFormData.getValueSilently("district").toInteger())
		doc.addGlossaryField("street", webFormData.getValue("street").toInteger())
		doc.addStringField("home", webFormData.getValueSilently("home"))
		doc.addStringField("limitdepreciation", webFormData.getValueSilently("limitdepreciation").replace(",","."))
		doc.addStringField("appartament", webFormData.getValueSilently("appartament"))
		doc.addStringField("regdoc", webFormData.getValueSilently("regdoc"))
		doc.addStringField("originalcost", webFormData.getValueSilently("originalcost"))
		doc.addStringField("balancecost", webFormData.getValueSilently("balancecost"))
		doc.addStringField("estimatedcost", webFormData.getValueSilently("estimatedcost"))
		doc.addStringField("residualcost", webFormData.getValueSilently("residualcost"))
		doc.addStringField("receiptbasisingproperty", webFormData.getValueSilently("receiptbasisingproperty"))
		doc.addStringField("receiptbasisinbalance", webFormData.getValueSilently("receiptbasisinbalance"))
		doc.addStringField("cumulativedepreciation", webFormData.getValueSilently("cumulativedepreciation"))
		doc.addStringField("deterioration", webFormData.getValueSilently("deterioration"))
		doc.addStringField("repair", webFormData.getValueSilently("repair"))
		doc.addEditor(webFormData.getValue("author"))
		doc.addEditor("[operator]")
		doc.addStringField("depreciating",webFormData.getValueSilently("depreciating"))
		doc.addStringField("pledge",webFormData.getValueSilently("pledge"))
		doc.addStringField("arrestingbyacourtdecision",webFormData.getValueSilently("arrestingbyacourtdecision"))
		doc.addStringField("legalclaim",webFormData.getValueSilently("legalclaim"))
		doc.addStringField("orderofremovalfrombalance",webFormData.getValueSilently("orderofremovalfrombalance"))

		doc.addStringField("enginenumber", webFormData.getValueSilently("enginenumber"))
		doc.addStringField("bodynumber", webFormData.getValueSilently("bodynumber"))
		doc.addStringField("model", webFormData.getValueSilently("model"))
		doc.addStringField("srts", webFormData.getValueSilently("srts"))
		doc.addStringField("grnz", webFormData.getValueSilently("grnz"))
		doc.addStringField("yearrelease", webFormData.getValueSilently("yearrelease"))
		doc.addStringField("category", webFormData.getValueSilently("category"))
		doc.addStringField("color", webFormData.getValueSilently("color"))
		doc.addStringField("powerengine", webFormData.getValueSilently("powerengine"))
		doc.addStringField("weightwithoutload", webFormData.getValueSilently("weightwithoutload"))
		doc.addStringField("maxload", webFormData.getValueSilently("maxload"))
		
		doc.addStringField("volumeengine",webFormData.getValueSilently("volumeengine"))
		doc.addStringField("condition",webFormData.getValueSilently("condition"))
		doc.addStringField("fueltype",webFormData.getValueSilently("fueltype"))
		doc.addStringField("expenditurefuel",webFormData.getValueSilently("expenditurefuel"))
		doc.addStringField("carrun",webFormData.getValueSilently("carrun"))
		doc.addStringField("normperiodmaintenance",webFormData.getValueSilently("normperiodmaintenance"))
		doc.addStringField("quantitypassengerseats",webFormData.getValueSilently("quantitypassengerseats"))
		doc.addStringField("quantitypassengerseats_rh",webFormData.getValueSilently("quantitypassengerseats_rh"))

		Date cDate = null
		String acceptancedate = webFormData.getValueSilently("acceptancedate")
		if(acceptancedate != ""){
			cDate = _Helper.convertStringToDate(acceptancedate)
		}
		doc.addDateField("acceptancedate", cDate)
		doc.addStringField("author", webFormData.getValue("author"))
		doc.addFile("rtfcontent", webFormData)

		def returnURL = session.getURLOfLastPage()
        Date tDate = new Date()
		localizedMsgBox(getLocalizedWord("Документ сохранен",lang))
		if(doc.isNewDoc){
			returnURL.changeParameter("page", "0");
		}

        doc.setViewText(doc.getValueString('objectname') + '  ' +  doc.getValueString('description'))
        doc.addViewText(doc.getValueString('objectname'))
        doc.addViewText(doc.getValueString('description'))
        doc.addViewText(doc.getValueString("estimatedcost"))
		doc.addViewText(session.currentDatabase.getGlossaryCustomFieldValueByDOCID(doc.getValueString("propertycode").toInteger(), "code") )
		doc.setViewDate(doc.getValueDate("acceptancedate"))
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){
		if (webFormData.getValueSilently("objectname") == ""){
			localizedMsgBox("Поле \"Наименование\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("description") == ""){
			localizedMsgBox("Поле \"Описание объекта\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("propertycode") == ""){
			localizedMsgBox("Поле \"Код права на имущество\" не заполнено.")
			return false
		}
		return true
	}
}
