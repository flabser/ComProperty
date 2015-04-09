package monitoring.form.order

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

		doc.setForm("order");
		//doc.setDefaultRuleID("IN")
		def cdb = session.getCurrentDatabase();
		doc.addStringField("content", webFormData.getValueSilently("content"))
		doc.addStringField("regdate", webFormData.getValueSilently("regdate"))
		doc.addStringField("contractdate", webFormData.getValueSilently("contractdate"))
		doc.addStringField("status", webFormData.getValueSilently("status"))
		doc.addNumberField("organization", webFormData.getNumberValueSilently("organization", 0))
		doc.addStringField("propertycode", webFormData.getValueSilently("propertycode"))
		doc.addStringField("counter",  cdb.getRegNumber("order").toString())
	
		doc.addStringField("author", webFormData.getValue("author"))
		doc.addFile("rtfcontent", webFormData)

		def returnURL = session.getURLOfLastPage()
        Date tDate = new Date()
		localizedMsgBox(getLocalizedWord("Документ сохранен",lang))
		if(doc.isNewDoc){
			returnURL.changeParameter("page", "0");
		}
		doc.addEditor(webFormData.getValue("author"))
		doc.addEditor("[operator]")
        doc.setViewText(doc.getValueString('regnumber') + ' от  ' + webFormData.getValueSilently("regdate") +' '+  doc.getValueString('content'))
        doc.addViewText(doc.getValueString('regnumber'))
        doc.addViewText(doc.getValueString('content'))
        doc.addViewText(doc.getValueString("regdate"))
        doc.addViewText(doc.getValueString("contractdate"))
        doc.addViewText(doc.getValueString("status"))
        doc.addViewText(doc.getValueString("organization"))
        doc.addViewText(doc.getValueString("propertycode"))
	
		//doc.setViewNumber(doc.getValueNumber("vnnumber"))
		//doc.setViewDate(doc.getValueDate("contractdate"))
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		if (webFormData.getValueSilently("content") == ""){
			localizedMsgBox("Поле \"Наименование постановления\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("regdate") == ""){
			localizedMsgBox("Поле \"Дата регистрации\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("status") == ""){
			localizedMsgBox("Поле \"Статус\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("organization") == ""){
			localizedMsgBox("Поле \"Организация\" не заполнено.")
			return false
		}
		return true
	}
}
