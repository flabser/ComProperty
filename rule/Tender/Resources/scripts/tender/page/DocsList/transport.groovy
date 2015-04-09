package tender.page.DocsList
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class transport extends _DoScript {
	
	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		println(formData)
		def page = 1;
		if (formData.containsField("page") && formData.getValue("page")){
			page = Integer.parseInt(formData.getValue("page"))
		}
		def formula = "form='automobile' and viewtext4 ='014' or form='cargo' and viewtext4 ='014' or form='bus' and viewtext4 ='014' or form='trolleybus' and viewtext4 ='014' or form='tram' and viewtext4 ='014' or form='watertransport' and viewtext4 ='014' or form='hospitaltransport' and viewtext4 ='014' or form='specialequipment' and viewtext4 ='014' or form='motorcycle' and viewtext4 ='014' ";
		def db = session.getCurrentDatabase()
		def filters = []
		def sorting = []
		def col = db.getCollectionOfDocuments(formula, page, true, true, "form != 'notification' and  form != 'contract' and form != 'order'")
		setContent(col)
	}
}