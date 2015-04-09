package monitoring.form.act

import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.constants._TaskType
import kz.nextbase.script.events._FormPostSave
import kz.nextbase.script.mail._Memo
import kz.nextbase.script.task._Task

class PostSave extends _FormPostSave {

	public void doPostSave(_Session ses, _Document doc) {	

		def parentdoc = doc.getParentDocument()
		parentdoc.setValueString("contractdate", doc.getValueString("regdate"))
		parentdoc.save("[supervisor]")
		
		def cdb = ses.getCurrentDatabase();
		def _doc = new _Document(cdb);
		def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getParentDocument().getValueNumber("organization"));
		_doc.setForm("notification")
		_doc.addStringField("balanceholdername", balanceholder.getValueString("orgfullname"))
		_doc.addStringField("balanceholderbin", balanceholder.getValueString("bin"))
		_doc.addStringField("balanceholderemail", balanceholder.getValueString("email"))
		_doc.addStringField("sentdate", new Date().toString())
		_doc.addStringField("regdate", doc.getValueString("regdate"))
		_doc.addStringField("regnumber", doc.getValueString("regnumber"))
		_doc.addStringField("content", doc.getValueString("content"))
		_doc.addStringField("endcontractdate", doc.getValueString("endcontractdate"))
		_doc.addStringField("notificationtype", "regcontract")
		_doc.addStringField("object", doc.getViewText())
		_doc.addStringField("comobjectname",ses.dataBase.getDocumentByComplexID(doc.getParentDocument().getParentDocType() ,doc.getParentDocument().getParentDocID()).getViewText())
		_doc.addStringField("comobjecturl",ses.dataBase.getDocumentByComplexID(doc.getParentDocument().getParentDocType() ,doc.getParentDocument().getParentDocID()).getURL())
		_doc.addStringField("ordername",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getViewText())
		_doc.addStringField("orderurl",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getURL())
		_doc.addStringField("objectlink", doc.getURL())
		_doc.addStringField("propertycode", ses.getCurrentDatabase().getGlossaryCustomFieldValueByDOCID(doc.getParentDocument().getValueString("propertycode").toInteger(), "name"))
		_doc.setParentDoc(doc)
		_doc.setViewText("Уведомление о регистрации акта приема-передачи: №"+ doc.getValueString("regnumber") + " от " + doc.getValueString("regdate"))
		_doc.addViewText("regcontract")
		_doc.setViewDate(new Date())
		_doc.addReader(doc.getAuthorID())
		_doc.save("[supervisor]")
		doc.addStringField("sentnotification", "1")
		doc.save("[supervisor]")
	}
}