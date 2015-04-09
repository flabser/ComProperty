package page.sentnotification_enddatecontract
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class DoScript extends _DoScript {
	

	public void doProcess(_Session ses, _WebFormData formData, String lang) {
		println(formData)
		def doc = ses.db.getDocumentByComplexID(896, formData.getValue("docid").toInteger())
		def cdb = ses.getCurrentDatabase();
		def _doc = new _Document(cdb);
		def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getValueNumber("leasingholder"));
		_doc.setForm("notification")
		_doc.addStringField("balanceholdername", balanceholder.getValueString("orgfullname"))
		_doc.addStringField("balanceholderbin", balanceholder.getValueString("bin"))
		_doc.addStringField("balanceholderemail", balanceholder.getValueString("email"))
		_doc.addStringField("sentdate", new Date().toString())
		_doc.addStringField("regdate", doc.getValueString("regdate"))
		_doc.addStringField("regnumber", doc.getValueString("regnumber"))
		_doc.addStringField("content", doc.getValueString("content"))
		_doc.addStringField("endcontractdate", doc.getValueString("enddatecontract"))
		_doc.addStringField("notificationtype", "notif")
		_doc.addStringField("object", doc.getViewText())
		_doc.addStringField("comobjectname",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getViewText())
		_doc.addStringField("comobjecturl",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getURL())
		//_doc.addStringField("ordername",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getViewText())
		//_doc.addStringField("orderurl",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getURL())
		_doc.addStringField("objectlink", doc.getURL())
		//_doc.addStringField("propertycode", ses.getCurrentDatabase().getGlossaryCustomFieldValueByDOCID(doc.getParentDocument().getValueString("propertycode").toInteger(), "name"))
		_doc.setParentDoc(doc)
		_doc.setViewText("Уведомление об окончании срока действия договора: №"+ doc.getValueString("regnumber") + " от " + doc.getValueString("regdate"))
		_doc.addViewText("enddatecontract")
		_doc.setViewDate(new Date())
		_doc.addReader(doc.getAuthorID())
		_doc.save("[supervisor]")
		doc.addStringField("sentnotification", "1")
		doc.save("[supervisor]")
	}
}