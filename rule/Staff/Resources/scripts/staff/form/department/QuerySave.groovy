package staff.form.department

import kz.nextbase.script.*
import kz.nextbase.script.events.*
import kz.nextbase.script.struct.*


class QuerySave extends _FormQuerySave {

	@Override
	public void doQuerySave(_Session ses, _Document doc, _WebFormData webFormData, String lang) {

		println(webFormData)

		boolean v = validate(webFormData)
		if(v == false){
			stopSave()
			return;
		}

		def dep  = (_Department)doc;
		dep.setParent(webFormData.getListOfValues("parentsubkey"), webFormData.getValue("parentdocid"), webFormData.getValue("parentdoctype"))
		dep.setFullName(webFormData.getValueSilently("fullname"));
		dep.setShortName(webFormData.getValueSilently("shortname"));
		dep.setComment(webFormData.getValueSilently("comment"));
		dep.setRank(webFormData.getNumberValueSilently("rank",999));
		dep.setIndex(webFormData.getValueSilently("index"));
		dep.setType(webFormData.getNumberValueSilently("subdivision",-1))
		dep.setViewText(dep.getFullName());
	}

	def validate(_WebFormData formData){

		if (formData.getValueSilently("fullname") == ""){
			localizedMsgBox("Поле\"Полное название\" не заполнено");
			return false;
		}else if (formData.getValueSilently("shortname") == ""){
			localizedMsgBox("Поле \"Сокращенное название\" не заполнено");
			return false;
		}
		return true;
	}
}
