package staff.page.action;

import kz.flabs.localization.LanguageCode;
import kz.flabs.util.Util;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.events._DoPage;

/**
 * 
 * 
 * @author Kayra created 01-03-2016
 */

public class ChangePageSize extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData, LanguageCode lang) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {
		String anotherPageSize = formData.getValueSilently("pagesize");
		session.pageSize = Util.convertStringToInt(anotherPageSize, 20);
	}

}
