package page.registry_search
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class DoScript extends _DoScript {
	

	public void doProcess(_Session session, _WebFormData formData, String lang) {
		def page = formData.getNumberValueSilently("page", 1);
		def db = session.getCurrentDatabase()
        def condition = "form in ('kgu', 'kgp', 'gkkp', 'ao', 'too', 'ao', 'individuals', 'legalentities')";
		//def col = db.search(formData.getValue("keyword"), page as int)
        def col = db.getCollectionOfDocuments("($condition) and viewtext ~* '${formData.getEncodedValueSilently("keyword")}'",
                page, false);
		setContent(col)
       // setContent(new _XMLDocument(new _Tag("keyword", formData.getEncodedValueSilently("keyword"))))
	}
}