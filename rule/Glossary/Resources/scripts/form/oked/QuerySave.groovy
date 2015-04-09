package form.oked

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
		glos.setForm("oked")
		glos.setName(webFormData.getValue("namerus"))
		doc.addStringField("namekaz", webFormData.getValueSilently("namekaz"))
		doc.addStringField("code", webFormData.getValueSilently("code"))

		glos.setViewText(glos.getName())
		glos.addViewText(webFormData.getValueSilently("namekaz"))
		glos.addViewText(webFormData.getValueSilently("code"))
		def returnURL = session.getURLOfLastPage()
		if (doc.isNewDoc) {
			returnURL.changeParameter("page", "0");
		}
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		if (webFormData.getValueSilently("namerus") == ""){
			localizedMsgBox("Поле \"Наименование на русском языке\" не заполнено")
			return false
		}else if (webFormData.getValueSilently("code") == ""){
			localizedMsgBox("Поле \"Код\" не заполнено")
			return false
		}


		return true;
	}
}
