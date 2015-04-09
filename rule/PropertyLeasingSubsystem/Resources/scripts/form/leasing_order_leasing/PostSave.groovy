package form.leasing_order_leasing
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.events._FormPostSave

class PostSave extends _FormPostSave {

	public void doPostSave(_Session ses, _Document doc) {	
		def cdb = ses.getCurrentDatabase();
		def _doc = new _Document(cdb);
		def __doc = new _Document(cdb);
		def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getValueNumber("leasingholder"));
		_doc.setForm("notification")
		_doc.addStringField("balanceholdername", balanceholder.getValueString("orgfullname"))
		_doc.addStringField("balanceholderbin", balanceholder.getValueString("bin"))
		_doc.addStringField("balanceholderemail", balanceholder.getValueString("email"))
		_doc.addStringField("sentdate", new Date().toString())
		_doc.addStringField("regdate", doc.getValueString("regdate"))
		_doc.addStringField("content", doc.getValueString("content"))
		def year =(new Date().getYear() + 1900).toString().stripIndent(2)
		def regnumber =cdb.getGlossaryDocument(doc.parentDocument.getValueGlossary("district")[0]).getValueString("code")+"/"+ year +"-"+ doc.getValueString("counter");
		//doc.addStringField("regnumber", webFormData.getValueSilently("regnumber"))
		doc.addStringField("regnumber", regnumber);
		doc.setViewText(regnumber + ' от  ' + doc.getValueString("regdate") +' '+  doc.getValueString('content'),0)
		doc.setViewText(regnumber, 1)
		doc.save("[supervisor]")
		_doc.addStringField("regnumber",regnumber)
		_doc.addStringField("notificationtype", "regorder_leasing")
		_doc.addStringField("object", doc.getViewText())
		_doc.addStringField("comobjectname",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getViewText())
		_doc.addStringField("comobjecturl",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getURL())
		_doc.addStringField("objectlink", doc.getURL())
		//_doc.addStringField("propertycode", ses.getCurrentDatabase().getGlossaryCustomFieldValueByDOCID(doc.getValueString("propertycode").toInteger(), "name"))
		_doc.setParentDoc(doc)
		_doc.setViewText("Уведомление о регистрации договора аренды: №"+ doc.getValueString("regnumber") + " от " + doc.getValueString("regdate"))
		_doc.addViewText("regorder_leasing")
		_doc.setViewDate(new Date())
		_doc.save("[supervisor]")
		_doc.addReader(doc.getAuthorID())
		
		__doc.setForm("paycalendar")
		__doc.addStringField("regnumber",regnumber)
		__doc.addStringField("payperiod",doc.getValueString("payperiod"))
		__doc.addStringField("rent_days",doc.getValueString("rent_days"))
		__doc.addStringField("rent_months",doc.getValueString("rent_months"))
		__doc.addStringField("rent_hours",doc.getValueString("sa"))
		__doc.addStringField("starttarif",  doc.getValueString("starttarif"))
		__doc.addStringField("lgottarif",  doc.getValueString("lgottarif"))
		__doc.addStringField("currenttarif",   doc.getValueString("currenttarif"))
		__doc.addStringField("rentobjtype",   doc.getValueString("rentobjtype"))
		__doc.setAuthor(doc.getAuthorID())
		__doc.setViewText("Платежный календарь к договору аренды: №"+ doc.getValueString("regnumber") + " от " + doc.getValueString("regdate"))
		__doc.setViewDate(new Date())
		__doc.setParentDoc(doc)
		__doc.save("[supervisor]")
		__doc.addReader(doc.getAuthorID())
		def objectdoc = doc.getParentDocument()
		objectdoc.addStringField("isrented", "1")
		objectdoc.save("[supervisor]");
		
		doc.addStringField("sentnotification", "1")
		doc.save("[supervisor]")
	}
}