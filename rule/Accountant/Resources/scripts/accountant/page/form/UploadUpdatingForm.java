package accountant.page.form;

import kz.flabs.localization.LanguageType;
import kz.flabs.util.Util;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;

/**
 * @author Kayra created 30-01-2016
 */

public class UploadUpdatingForm extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		addContent("formsesid", Util.generateRandomAsText());
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}
}
