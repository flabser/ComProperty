package staff.page.action;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;

/**
 * 
 * 
 * @author Kayra created 12-02-2016
 */

public class ChangeLang extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData, LanguageCode lang) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {
		String anotherLang = formData.getValueSilently("lang");
		session.setLang(LanguageCode.valueOf(anotherLang));
	}

}
