package form.stepdowncoefficient

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
		glos.setForm("stepdowncoefficient")
		glos.setName(webFormData.getValue("name"))
		glos.addStringField("kazname",webFormData.getValueSilently("kazname"))
		doc.addStringField("koef", webFormData.getValueSilently("koef"))

		glos.setViewText(glos.getName())
		glos.addViewText(webFormData.getValueSilently("koef"))
		def returnURL = session.getURLOfLastPage()
		if (doc.isNewDoc) {
			returnURL.changeParameter("page", "0");
		}
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		if (webFormData.getValueSilently("name") == ""){
			localizedMsgBox("Поле \"Описание\" не заполнено")
			return false
		}else if (webFormData.getValueSilently("koef") == ""){
			localizedMsgBox("Поле \"Коэффициент\" не заполнено")
			return false
		}


		return true;
	}
}
