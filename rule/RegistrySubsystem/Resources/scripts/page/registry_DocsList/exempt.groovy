package page.registry_DocsList
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class exempt extends _DoScript {
	
	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		println(formData)
		def page = 1;
		if (formData.containsField("page") && formData.getValue("page")){
			page = Integer.parseInt(formData.getValue("page"))
		}
		def formula = "form='legalentities' and viewtext6 = '1' or form='ao' and viewtext6 = '1' or form='gkkp' and viewtext6 = '1' or form='kgp' and viewtext6 = '1' or form='kgu' and viewtext6 = '1' or form='subsidiaries' and viewtext6 = '1' or form='too' and viewtext6 = '1'";
		def db = session.getCurrentDatabase()
		def filters = []
		def sorting = []
		def col = db.getCollectionOfDocuments(formula, page, true, true)
		setContent(col)
	}
}