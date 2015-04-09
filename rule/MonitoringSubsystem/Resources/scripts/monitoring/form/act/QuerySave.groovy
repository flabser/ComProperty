package monitoring.form.act

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

		doc.setForm("act");
		def cdb = session.getCurrentDatabase();
		doc.addStringField("regnumber",  cdb.getRegNumber("act").toString())
		doc.addStringField("content", webFormData.getValueSilently("content"))
		doc.addStringField("regdate", webFormData.getValueSilently("regdate"))
		doc.addStringField("endcontractdate", webFormData.getValueSilently("endcontractdate"))
		doc.addStringField("status", webFormData.getValueSilently("status"))
		
	
		doc.addStringField("author", webFormData.getValue("author"))
		doc.addFile("rtfcontent", webFormData)
		doc.addEditor(webFormData.getValue("author"))
		doc.addEditor("[operator]")
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
        doc.addViewText(doc.getValueString("endcontractdate"))
        doc.addViewText(doc.getValueString("status"))
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){
		if (webFormData.getValueSilently("content") == ""){
			localizedMsgBox("Поле \"Наименование акта\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("regdate") == ""){
			localizedMsgBox("Поле \"Дата регистрации\" не заполнено.")
			return false
		}
		return true
	}
}
