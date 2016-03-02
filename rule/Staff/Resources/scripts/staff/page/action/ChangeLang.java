package staff.page.action;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.events._DoPage;

/**
 * 
 * 
 * @author Kayra created 12-02-2016
 */

public class ChangeLang extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {
		String anotherLang = formData.getValueSilently("type");
		session.setLang(LanguageCode.valueOf(anotherLang));
	}

}
