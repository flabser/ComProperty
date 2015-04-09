package page.leasing_search
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class DoScript extends _DoScript {
	

	public void doProcess(_Session session, _WebFormData formData, String lang) {
		println(formData)
		def page = 1;
		def db = session.getCurrentDatabase()
		def col = db.search(formData.getValue("keyword"), page as int)
		setContent(col)
	}
}