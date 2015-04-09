package tender.page.sentnotification_rentpayment
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import kz.nextbase.script._Helper
import nextbase.groovy.*
import org.joda.time.DateTime

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
		_doc.addStringField("endcontractdate", doc.getValueString("endcontractdate"))
		_doc.addStringField("notificationtype", "notif_rentpayment")
		_doc.addStringField("object", doc.getViewText())
		_doc.addStringField("tarif", doc.getValueString("tarif"))
		 int days = new DateTime().dayOfMonth().getMaximumValue();
		 int month = new Date().getMonth()+ 1;
		 int year = new Date().getYear()+1900;
		_doc.addStringField("enddatepayment", days.toString()+"."+month.toString()+"." + year );
		_doc.addStringField("comobjectname",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getViewText())
		_doc.addStringField("comobjecturl",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getURL())
		_doc.addStringField("objectlink", doc.getURL())
		_doc.addStringField("propertycode", ses.getCurrentDatabase().getGlossaryCustomFieldValueByDOCID(doc.getParentDocument().getValueString("propertycode").toInteger(), "name"))
		_doc.setParentDoc(doc)
		_doc.setViewText("Уведомление о необходимости внесения арендной платы : договор №"+ doc.getValueString("regnumber") + " от " + doc.getValueString("regdate"))
		_doc.addViewText("notif_rentpayment")
		_doc.setViewDate(new Date())
		_doc.addReader(doc.getAuthorID())
		_doc.save("[supervisor]")
		doc.addStringField("sentnotification", "1")
		doc.save("[supervisor]")

	}
}