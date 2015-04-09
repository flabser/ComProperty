package legalentity.form.street

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
		glos.setForm("street")
		glos.setName(webFormData.getValue("name"))
		glos.addStringField("kazname",webFormData.getValueSilently("kazname"))
		doc.addGlossaryField("district", webFormData.getValue("district").toInteger())
		glos.setCode(webFormData.getValue("code"))

		glos.setViewText(glos.getName())
		glos.addViewText(glos.getCode())
		glos.addViewText(glos.getValueString("district"))
		def returnURL = session.getURLOfLastPage()
		if (doc.isNewDoc) {
			returnURL.changeParameter("page", "0");
		}
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		if (webFormData.getValueSilently("name") == ""){
			localizedMsgBox("Поле \"Улица\" не заполнено")
			return false
		}else if (webFormData.getValueSilently("code") == ""){
			localizedMsgBox("Поле \"Код\" не заполнено")
			return false
		}else if (webFormData.getValueSilently("district") == ""){
			localizedMsgBox("Поле \"Район\" не заполнено")
			return false
		}


		return true;
	}
}
