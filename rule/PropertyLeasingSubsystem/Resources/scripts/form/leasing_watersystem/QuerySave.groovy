package form.leasing_watersystem

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
		doc.setForm("watersystem");
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
		doc.addStringField("ktr",webFormData.getValueSilently("ktr"))
	
		doc.addStringField("totalarea", webFormData.getValueSilently("totalarea"))
		doc.addStringField("extratotalarea", webFormData.getValueSilently("extratotalarea"))
		doc.addStringField("landtotalarea", webFormData.getValueSilently("landtotalarea"))
		doc.addStringField("extralandarea", webFormData.getValueSilently("extralandarea"))
		doc.addStringField("yearconstruction", webFormData.getValueSilently("yearconstruction"))
		doc.addStringField("buildingsyears", webFormData.getValueSilently("buildingsyears"))
		doc.addGlossaryField("material", webFormData.getValue("material").toInteger())
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
		return true
	}
}
