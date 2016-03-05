package staff.page.action;


import kz.flabs.util.Util;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;

/**
 * 
 * 
 * @author Kayra created 01-03-2016
 */

public class ChangePageSize extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {
		String anotherPageSize = formData.getValueSilently("pagesize");
		session.pageSize = Util.convertStringToInt(anotherPageSize);
	}

}
