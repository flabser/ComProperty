package legalentity.form.district

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
		glos.setForm("district")
		glos.setName(webFormData.getValue("name"))
		glos.addStringField("kazname",webFormData.getValueSilently("kazname"))
		doc.addNumberField("city", webFormData.getNumberValueSilently("city", 0))
		glos.setCode(webFormData.getValue("code"))

		glos.setViewText(glos.getName())
		glos.addViewText(glos.getCode())
		glos.addViewText(glos.getValueString("city"))
		def returnURL = session.getURLOfLastPage()
		if (doc.isNewDoc) {
			returnURL.changeParameter("page", "0");
		}
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		if (webFormData.getValueSilently("name") == ""){
			localizedMsgBox("Поле \"Район\" не заполнено")
			return false
		}else if (webFormData.getValueSilently("code") == ""){
			localizedMsgBox("Поле \"Код\" не заполнено")
			return false
		}else if (webFormData.getValueSilently("city") == ""){
			localizedMsgBox("Поле \"Город\" не заполнено")
			return false
		}


		return true;
	}
}
