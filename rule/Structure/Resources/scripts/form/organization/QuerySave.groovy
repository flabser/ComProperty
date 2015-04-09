package form.organization

import java.util.ArrayList;
import java.text.SimpleDateFormat
import java.util.Map
import kz.nextbase.script.*
import kz.nextbase.script.events.*;
import kz.nextbase.script.struct.*
import kz.nextbase.script._Helper;

class QuerySave extends _FormQuerySave {

	@Override
	public void doQuerySave(_Session ses, _Document doc, _WebFormData webFormData, String lang) {

		println(webFormData)
		
		boolean v = validate(webFormData)
		if(v == false){
			stopSave()
			return;
		}

		def org  = (_Organization)doc;
		org.setFullName(webFormData.getValueSilently("fullname"));
		org.setShortName(webFormData.getValueSilently("shortname"));
		org.setAddress(webFormData.getValueSilently("address"));
		org.setComment(webFormData.getValueSilently("comment"));
		
		org.setViewText(org.getFullName());

	}

	def validate(_WebFormData formData){

		if (formData.getValueSilently("fullname") == ""){
			localizedMsgBox("Поле \"Название\" не заполнено.");
			return false;
		}else if (formData.getValueSilently("shortname") == ""){
			localizedMsgBox("Поле \"Сокращенное название\" не заполнено.");
			return false;
		}		
		return true;
		
	}
}
