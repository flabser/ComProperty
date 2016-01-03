package staff.page.contractorlist

import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript

/**
 * Created by Bekzat on 2/6/14.
 */
class ResponsiblePerson extends _DoScript{

	@Override
	void doProcess(_Session session, _WebFormData formData, String lang) {
		def cdb = session.getCurrentDatabase()
		def page = formData.getValueSilently("page").toInteger();
		def struct = session.getStructure()
		def condition = "form = 'responsibleperson'";
		if(formData.containsField("status"))
			condition += " and viewtext2='${formData.getValue("status")}'";
		def emp = struct.getStructureEntries(condition, page, false)
		setContent(emp);
	}
}