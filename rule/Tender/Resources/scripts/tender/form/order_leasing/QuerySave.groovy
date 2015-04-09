package tender.form.order_leasing

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
		def cdb = session.getCurrentDatabase();
		doc.setForm("orderleasing");
		doc.addNumberField("leasingholder", webFormData.getNumberValueSilently("leasingholder", 0))
		doc.addStringField("regnumber", webFormData.getValueSilently("regnumber"))
		doc.addStringField("content", webFormData.getValueSilently("content"))
		doc.addStringField("regdate", webFormData.getValueSilently("regdate"))
		doc.addStringField("contractdate", webFormData.getValueSilently("contractdate"))
		doc.addStringField("startcontractdate", webFormData.getValueSilently("startcontractdate"))
		doc.addStringField("endrentdate", webFormData.getValueSilently("endrentdate"))
		doc.addStringField("status", webFormData.getValueSilently("status"))
		doc.addStringField("sa", webFormData.getValueSilently("sa"))
		doc.addStringField("tarif", webFormData.getValueSilently("tarif"))
		doc.addStringField("payperiod", webFormData.getValueSilently("payperiod"))
		doc.addStringField("rent_days", webFormData.getValueSilently("rent_days"))
		doc.addStringField("rent_months", webFormData.getValueSilently("rent_months"))
		doc.addStringField("counter",  cdb.getRegNumber("order_leasing").toString())
		doc.addStringField("starttarif",   webFormData.getValueSilently("starttarif"))
		doc.addStringField("lgottarif",   webFormData.getValueSilently("lgottarif"))
		doc.addStringField("currenttarif",   webFormData.getValueSilently("currenttarif"))
		doc.addStringField("rentobjtype",   webFormData.getValueSilently("rentobjtype"))
		//doc.addStringField("nanimatel", webFormData.getValueSilently("nanimatel"))
		doc.addEditor(webFormData.getValue("author"))
		doc.addEditor("[operator]")
	
		doc.addStringField("author", webFormData.getValue("author"))
		doc.addFile("rtfcontent", webFormData)

		def returnURL = session.getURLOfLastPage()
        Date tDate = new Date()
		localizedMsgBox(getLocalizedWord("Документ сохранен",lang))
		if(doc.isNewDoc){
			returnURL.changeParameter("page", "0");
		}

        doc.setViewText(doc.getValueString('regnumber') + ' от  ' + webFormData.getValueSilently("regdate") +' '+  doc.getValueString('content'))
        doc.addViewText(doc.getValueString('regnumber'))
        doc.addViewText(doc.getValueString('content'))
        doc.addViewText(doc.getValueString("regdate"))
        doc.addViewText(doc.getValueString("contractdate"))
        doc.addViewText(doc.getValueString("status"))
		//doc.setViewNumber(doc.getValueNumber("vnnumber"))
		//doc.setViewDate(doc.getValueDate("contractdate"))
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){
		if (webFormData.getValueSilently("regdate") == ""){
			localizedMsgBox("Поле \"Дата регистрации\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("status") == ""){
			localizedMsgBox("Поле \"Статус\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("payperiod") == ""){
			localizedMsgBox("Поле \"Период оплаты\" не выбрано.")
			return false
		}
		if (webFormData.getValueSilently("startcontractdate") == ""){
			localizedMsgBox("Поле \"Дата начала действия договора\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("endrentdate") == ""){
			localizedMsgBox("Поле \"Дата окончания аренды\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("leasingholder") == ""){
			localizedMsgBox("Поле \"Арендатор\" не заполнено.")
			return false
		}
		return true
	}
}
