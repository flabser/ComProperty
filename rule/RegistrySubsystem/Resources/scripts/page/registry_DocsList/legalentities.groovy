package page.registry_DocsList
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class legalentities extends _DoScript {
	
	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		println(formData)
		def page = 1;
		if (formData.containsField("page") && formData.getValue("page")){
			page = Integer.parseInt(formData.getValue("page"))
		}
		def formula = "form='legalentities'";
		def db = session.getCurrentDatabase()
		def filters = []
		def sorting = []
		def col = db.getCollectionOfDocuments(formula, page, true, true)
		setContent(col)
	}
}