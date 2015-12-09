package monitoring.form.buildings

import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.constants._TaskType
import kz.nextbase.script.events._FormPostSave
import kz.nextbase.script.mail._Memo
import kz.nextbase.script.task._ExecsBlocks
import kz.nextbase.script.task._Task
import kz.nextbase.script.task._Control

class PostSave extends _FormPostSave {

	public void doPostSave(_Session ses, _Document doc) {	
		def cdb = ses.getCurrentDatabase();
		def _doc = new _Document(cdb);
		_doc.setForm("notification")
		if(doc.getValueNumber("balanceholder") != 0){
			def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getValueNumber("balanceholder"));
			_doc.addStringField("balanceholdername", balanceholder.getValueString("orgfullname"))
			_doc.addStringField("balanceholderbin", balanceholder.getValueString("bin"))
			_doc.addStringField("balanceholderemail", balanceholder.getValueString("email"))
		}
		_doc.addStringField("invnumber", doc.getValueString("invnumber"))
		_doc.addStringField("description", doc.getValueString("description"))
		_doc.addStringField("acceptancedate", doc.getValueString("acceptancedate") ?: "")
		_doc.addStringField("home", doc.getValueString("home"))
		_doc.addStringField("sentdate", new Date().toString())
		_doc.addStringField("notificationtype", "regobj")
		_doc.addStringField("object", doc.getViewText())
		_doc.addStringField("objectlink", doc.getURL())
		_doc.addStringField("propertycode", ses.getCurrentDatabase().getGlossaryCustomFieldValueByDOCID(doc.getValueString("propertycode").toInteger(), "name"))
		_doc.addStringField("region", ses.getCurrentDatabase().getGlossaryCustomFieldValueByDOCID(doc.getValueString("region").toInteger(), "name"))
		_doc.addStringField("city", ses.getCurrentDatabase().getGlossaryCustomFieldValueByDOCID(doc.getValueString("city").toInteger(), "name"))
		_doc.addStringField("district", ses.getCurrentDatabase().getGlossaryCustomFieldValueByDOCID(doc.getValueString("district").toInteger(), "name"))
		_doc.addStringField("street", ses.getCurrentDatabase().getGlossaryCustomFieldValueByDOCID(doc.getValueString("street").toInteger(), "name"))
		_doc.setParentDoc(doc)
		_doc.setViewText("Уведомление о регистрации объекта: "+ new Date() + " объект: "+ doc.getViewText())
		_doc.addViewText("regobj")
		_doc.setViewDate(new Date())
		_doc.addReader(doc.getAuthorID())
		_doc.save("[supervisor]")
		doc.addStringField("sentnotification", "1")
		doc.save("[supervisor]")
	}
}