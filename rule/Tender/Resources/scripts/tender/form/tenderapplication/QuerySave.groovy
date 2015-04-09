package tender.form.tenderapplication

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
		doc.setForm("tenderapplication");

		doc.addDateField("startdate", _Helper.convertStringToDate(webFormData.getValueSilently("startdate")));
		doc.addDateField("enddate",_Helper.convertStringToDate(webFormData.getValueSilently("enddate")));
        doc.addStringField("leasingholder_id", webFormData.getValueSilently("leasingholder_id"));
        doc.addStringField("cost", webFormData.getValue("cost"))
		doc.addFile("rtfcontent", webFormData)

        def regNum = 0;
        if(doc.isNewDoc){
            regNum = session.getCurrentDatabase().getRegNumber("tenderapplication");
            doc.addNumberField("regNum", regNum)
            doc.addStringField("status", "new");
            doc.setViewText("new", 2);
        }else
            regNum = doc.getValueNumber("regNum")
        doc.setViewNumber(regNum);

		def pdoc = session.currentDatabase.getDocumentByID(webFormData.getValueSilently("parentdocid").toInteger()).getParentDocument();

		doc.setViewText(" Заявление №$regNum от ${new Date().format("dd.MM.yyyy")} на участие в тендере: " +  pdoc.getViewText() + " заявитель: " + webFormData.getValueSilently("leasingholder"))
        doc.addViewText(webFormData.getValueSilently("leasingholder"));
        doc.addViewText("new");
		doc.setViewDate(new Date())


        doc.addEditor(webFormData.getValue("author"))
        doc.addEditor("[rent_editor]")
        doc.addReader("[rent_reader]")

        localizedMsgBox(getLocalizedWord("Документ сохранен",lang))
        def returnURL = session.getURLOfLastPage()
        if(doc.isNewDoc){
			returnURL.changeParameter("page", "0");
		}
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		if (webFormData.getValueSilently("leasingholder_id") == ""){
			localizedMsgBox("Поле \"Арендатор\" не заполнено.")
			return false
		}

		if (webFormData.getValueSilently("startdate") == ""){
			localizedMsgBox("Поле \"Начало аренды\" не заполнено.")
			return false
		}
		if (webFormData.getValueSilently("enddate") == ""){
			localizedMsgBox("Поле \"Конец аренды\" не заполнено.")
			return false
		}
        if (webFormData.getValueSilently("cost") == ""){
            localizedMsgBox("Поле \"Предлагаемая сумма\" не заполнено.")
            return false
        }
		return true
	}
}