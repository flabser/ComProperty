package tender.page.opentender
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class DoScript extends _DoScript {
	
	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
        def cdb = ses.getCurrentDatabase();
        // проверка парент документа
        def pdoc = cdb.getDocumentByID(formData.getSign());
        println("!!!!!!!!!!!" + formData.getSign())
       // def rentApplication = cdb.getCollectionOfDocuments("")
	}
}