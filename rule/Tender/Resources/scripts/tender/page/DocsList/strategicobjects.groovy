package tender.page.DocsList
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class strategicobjects extends _DoScript {
	
	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		println(formData)
		def page = 1;
		if (formData.containsField("page") && formData.getValue("page")){
			page = Integer.parseInt(formData.getValue("page"))
		}
		def formula = "form='billboard' and viewtext4 ='014' or form='columns' and viewtext4 ='014' or form='electricnetworks' and viewtext4 ='014' or form='thermalnetworks' and viewtext4 ='014' or form='gas' and viewtext4 ='014' or form='watersystem' and viewtext4 ='014' or form='drain' and viewtext4 ='014' or form='road' and viewtext4 ='014' or form='parking' and viewtext4 ='014'";
		def db = session.getCurrentDatabase()
		def filters = []
		def sorting = []
		def col = db.getCollectionOfDocuments(formula, page, true, true, "form != 'notification' and  form != 'contract' and form != 'order'")
		setContent(col)
	}
}