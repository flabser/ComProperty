package legalentity.form.mrp

import java.text.SimpleDateFormat
import java.util.Map
import kz.nextbase.script.*
import kz.nextbase.script.events._FormQuerySave
class QuerySave extends _FormQuerySave {

	@Override
	public void doQuerySave(_Session session, _Document doc, _WebFormData webFormData, String lang) {
		
		println(webFormData)
		
		boolean v = validate(webFormData);
		if(v == false){
			stopSave()
			return;
		}
		
		def glos = (_Glossary)doc;
		glos.setForm("mrp")
		glos.setName(webFormData.getValue("year"))
		doc.addStringField("value", webFormData.getValueSilently("value"))
		doc.addStringField("year", webFormData.getValueSilently("year"))

		glos.setViewText(webFormData.getValue("year")+" : "+ webFormData.getValueSilently("value") )
		glos.addViewText(webFormData.getValue("year"))
		glos.addViewText(webFormData.getValueSilently("value"))
		def returnURL = session.getURLOfLastPage()
		if (doc.isNewDoc) {
			returnURL.changeParameter("page", "0");
		}
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		if (webFormData.getValueSilently("year") == ""){
			localizedMsgBox("Поле \"Год\" не заполнено")
			return false
		}else if (webFormData.getValueSilently("value") == ""){
			localizedMsgBox("Поле \"Значение\" не заполнено")
			return false
		}


		return true;
	}
}
