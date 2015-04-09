package monitoring.form.transfer_protocol
import kz.nextbase.script._Document
import kz.nextbase.script._Helper
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._FormQuerySave

class QuerySave extends _FormQuerySave {

	@Override
	public void doQuerySave(_Session session, _Document doc, _WebFormData webFormData, String lang) {

		println(webFormData)
		
		boolean v = validate(webFormData)
		if(v == false){
			stopSave()
			return
		}
		doc.setForm("monitoring_transfer_protocol");
		doc.addStringField("techstate",webFormData.getValueSilently("techstate"))
		//doc.addStringField("regdate",webFormData.getValueSilently("regdate"))
		doc.addStringField("turnover",webFormData.getValueSilently("turnover"))
		doc.addStringField("accept",webFormData.getValueSilently("accept"))
		
		Date cDate = new Date();
		String regdate = webFormData.getValueSilently("regdate")
		if(regdate != ""){
			cDate = _Helper.convertStringToDate(regdate)
		}
		doc.addDateField("regdate", cDate)
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
		def parentDocID = webFormData.getValueSilently("parentdocid").toInteger();
		def parentDocType = webFormData.getValueSilently("parentdoctype").toInteger();
		def pdoc = session.currentDatabase.getDocumentByComplexID(parentDocType, parentDocID)
		doc.setViewText("Акт приема объекта в аренду "+ pdoc.getGrandParentDocument().getViewText() + '  от ' +  webFormData.getValueSilently("regdate"))
		doc.setViewDate(doc.getValueDate("regdate"))
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		if (webFormData.getValueSilently("techstate") == ""){
			localizedMsgBox("Поле \"Техническое состояние объекта на момент сдачи\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("regdate") == ""){
			localizedMsgBox("Поле \"Дата регистрации акта\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("turnover") == ""){
			localizedMsgBox("Поле \"Сдал\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("accept") == ""){
			localizedMsgBox("Поле \"Принял\" не заполнено.")
			return false
		}
		return true
	}
}
