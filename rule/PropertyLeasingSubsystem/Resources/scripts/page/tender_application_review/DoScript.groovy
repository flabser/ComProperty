package page.tender_application_review

import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import kz.nextbase.script.mail._Memo
import nextbase.groovy.*

class DoScript extends _DoScript {
	

	public void doProcess(_Session ses, _WebFormData formData, String lang) {
        def cdb = ses.getCurrentDatabase();
        def srtuct = ses.getStructure();
		def doc = ses.db.getDocumentByID(formData.getValue("docid"))

        if(formData.getValue("decision") != "approved" && formData.getValue("decision") != "rejected") return;

		doc.addStringField("status", formData.getEncodedValueSilently("decision"));
        doc.addDateField("review_date", new Date());
        doc.addStringField("review_comment", formData.getEncodedValueSilently("comment"));
        doc.setViewText(formData.getEncodedValueSilently("decision"), 2)
		doc.save("[supervisor]")
        setRedirectURL(ses.getURLOfLastPage());
	}
}