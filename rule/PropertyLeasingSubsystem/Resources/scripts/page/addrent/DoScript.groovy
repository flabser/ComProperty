package page.addrent
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
		def glosDocID = ses.db.getGlossaryDocs("propertycode", "code = '014'")[0].getDocID()
		doc.addNumberField("propertycode", glosDocID);
		doc.addStringField("isrented", "0");
		doc.setViewText("014", 4);
		doc.addReader("[rent_viewer]")
        doc.addStringField("rent_cost", formData.getEncodedValueSilently("cost"));
        doc.addStringField("rent_comment", formData.getEncodedValueSilently("comment"));
		doc.save("[supervisor]")
	}
}