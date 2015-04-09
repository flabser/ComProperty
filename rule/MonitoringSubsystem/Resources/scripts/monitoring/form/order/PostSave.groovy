package monitoring.form.order

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
		def parentdoc = doc.getParentDocument();
		parentdoc.addStringField("propertycode", doc.getValueString("propertycode"))
		parentdoc.save("[supervisor]")
		def balanceholder = ses.getCurrentDatabase().getDocumentByComplexID(896, doc.getValueNumber("organization"));
		_doc.setForm("notification")
		_doc.addStringField("balanceholdername", balanceholder.getValueString("orgfullname"))
		_doc.addStringField("balanceholderbin", balanceholder.getValueString("bin"))
		_doc.addStringField("balanceholderemail", balanceholder.getValueString("email"))
		_doc.addStringField("sentdate", new Date().toString())
		_doc.addStringField("regdate", doc.getValueString("regdate"))
		_doc.addStringField("content", doc.getValueString("content"))
		def year =(new Date().getYear() + 1900).toString().stripIndent(2)
		def quarter = 4;
	
			quarter =(int)(new Date().getMonth()/ 3) + 1;
		
		def regnumber =quarter+"/"+ doc.getValueString("counter");
		//doc.addStringField("regnumber", webFormData.getValueSilently("regnumber"))
		doc.addStringField("regnumber", regnumber);
		doc.setViewText(regnumber + ' от  ' + doc.getValueString("regdate") +' '+  doc.getValueString('content'),0)
		doc.setViewText(regnumber, 1)
		doc.save("[supervisor]")
		_doc.addStringField("regnumber",regnumber)
		_doc.addStringField("notificationtype", "regorder")
		_doc.addStringField("object", doc.getViewText())
		_doc.addStringField("comobjectname",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getViewText())
		_doc.addStringField("comobjecturl",ses.dataBase.getDocumentByComplexID(doc.getParentDocType() ,doc.getParentDocID()).getURL())
		_doc.addStringField("objectlink", doc.getURL())
		_doc.addStringField("propertycode", ses.getCurrentDatabase().getGlossaryCustomFieldValueByDOCID(doc.getValueString("propertycode").toInteger(), "name"))
		def objectdoc = doc.getParentDocument();
		objectdoc.addNumberField("balanceholder", doc.getValueNumber("organization"))
		objectdoc.save("[supervisor]")
			
		_doc.setParentDoc(doc)
		_doc.setViewText("Уведомление о регистрации постановления: №"+ doc.getValueString("regnumber") + " от " + doc.getValueString("regdate"))
		_doc.addViewText("regorder")
		_doc.setViewDate(new Date())
		_doc.addReader(doc.getAuthorID())
		_doc.save("[supervisor]")
		doc.addStringField("sentnotification", "1")
		doc.save("[supervisor]")
	}
}