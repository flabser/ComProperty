package form.typeofactions

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
		glos.setForm("typeofactions")
		glos.setName(webFormData.getValue("name"))
		glos.addStringField("kazname",webFormData.getValueSilently("kazname"))
		glos.setCode(webFormData.getValue("code"))

		glos.setViewText(glos.getName())
		glos.addViewText(glos.getCode())
		if(webFormData.getValue("parentdocid") !='' && webFormData.getValue("parentdocid") !='0' ){
			glos.addViewText(glos.parentDocument.getViewText())
		}
		def returnURL = session.getURLOfLastPage()
		if (doc.isNewDoc) {
			returnURL.changeParameter("page", "0");
		}
		setRedirectURL(returnURL)
	}

	def validate(_WebFormData webFormData){

		if (webFormData.getValueSilently("name") == ""){
			localizedMsgBox("Поле \"Наименование вида акций\" не заполнено")
			return false
		}else if (webFormData.getValueSilently("code") == ""){
			localizedMsgBox("Поле \"Код\" не заполнено")
			return false
		}


		return true;
	}
}
