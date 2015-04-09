package page.leasing_DocsList
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class personalestate extends _DoScript {
	
	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		println(formData)
		def page = 1;
		if (formData.containsField("page") && formData.getValue("page")){
			page = Integer.parseInt(formData.getValue("page"))
		}
		def formula = "form='furniture' and viewtext4 ='014' or form='animals' and viewtext4 ='014' or form='sportsequipment' and viewtext4 ='014' or form='others' and viewtext4 ='014' or form='shareblocks' and viewtext4 ='014' or form='equity' and viewtext4 ='014'";
		def db = session.getCurrentDatabase()
		def filters = []
		def sorting = []
		def col = db.getCollectionOfDocuments(formula, page, true, true, "form != 'notification' and  form != 'contract' and form != 'order'")
		setContent(col)
	}
}