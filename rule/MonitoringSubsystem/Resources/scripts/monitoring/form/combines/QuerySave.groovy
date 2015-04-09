package monitoring.form.combines

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

		doc.setForm("combines");
		doc.addStringField("note",webFormData.getValueSilently("note"))
		doc.addNumberField("balanceholder", webFormData.getNumberValueSilently("balanceholder", 0))
		doc.addStringField("address", webFormData.getValueSilently("address"))
		doc.addStringField("invnumber", webFormData.getValueSilently("invnumber"))
		doc.addStringField("description", webFormData.getValueSilently("description"))
		doc.addStringField("objectname",webFormData.getValueSilently("objectname"))
		doc.addStringField("propertycode", webFormData.getValueSilently("propertycode"))
		
		doc.addNumberField("region", webFormData.getNumberValueSilently("region", 0))
		doc.addNumberField("city", webFormData.getNumberValueSilently("city", 0))
		doc.addNumberField("district", webFormData.getNumberValueSilently("district", 0))
		doc.addNumberField("street", webFormData.getNumberValueSilently("street", 0))
		doc.addNumberField("material", webFormData.getNumberValueSilently("material", 0))
		
		doc.addStringField("home", webFormData.getValueSilently("home"))
		doc.addStringField("appartament", webFormData.getValueSilently("appartament"))
		doc.addStringField("limitdepreciation", webFormData.getValueSilently("limitdepreciation").replace(",","."))
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
		doc.addStringField("ktr", webFormData.getValueSilently("ktr"))
		doc.addStringField("isrented", webFormData.getValueSilently("isrented"))
		doc.addEditor(webFormData.getValue("author"))
		doc.addEditor("[operator]")
		doc.addStringField("countfloors", webFormData.getValueSilently("countfloors"))
		doc.addStringField("totalarea", webFormData.getValueSilently("totalarea"))
		doc.addStringField("extratotalarea", webFormData.getValueSilently("extratotalarea"))
		doc.addStringField("usefularea", webFormData.getValueSilently("usefularea"))
		doc.addStringField("landarea", webFormData.getValueSilently("landarea"))
		doc.addStringField("extralandarea", webFormData.getValueSilently("extralandarea"))
		doc.addStringField("yearconstruction", webFormData.getValueSilently("yearconstruction"))
		doc.addStringField("buildingsyears", webFormData.getValueSilently("buildingsyears"))
		doc.addStringField("сellarexistence", webFormData.getValueSilently("сellarexistence"))
		doc.addStringField("depreciating",webFormData.getValueSilently("depreciating"))
		doc.addStringField("pledge",webFormData.getValueSilently("pledge"))
		doc.addStringField("arrestingbyacourtdecision",webFormData.getValueSilently("arrestingbyacourtdecision"))
		doc.addStringField("legalclaim",webFormData.getValueSilently("legalclaim"))
		doc.addStringField("orderofremovalfrombalance",webFormData.getValueSilently("orderofremovalfrombalance"))
		doc.addStringField("restransferacceptance",webFormData.getValueSilently("restransferacceptance"))
		doc.addStringField("propertyarticlein",webFormData.getValueSilently("propertyarticlein"))
		doc.addStringField("stateactearth",webFormData.getValueSilently("stateactearth"))
		doc.addStringField("technicalpassport",webFormData.getValueSilently("technicalpassport"))
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
		if (webFormData.getValueSilently("originalcost") == ""){
			localizedMsgBox("Поле \"Первоначальная стоимость\" не заполнено.")
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
