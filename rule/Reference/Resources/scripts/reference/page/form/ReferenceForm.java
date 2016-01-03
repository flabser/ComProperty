package reference.page.form;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;

/**
 * 
 * 
 * @author Kayra created 03-01-2016
 */

public abstract class ReferenceForm extends _DoPage {

	protected boolean validate(_WebFormData webFormData) {
		if (webFormData.getValueSilently("name").equals("")) {
			localizedMsgBox("field_name_is_empty");
			return false;
		}

		return true;
	}

	@Override
	public abstract void doGET(_Session session, _WebFormData formData, String lang);

	@Override
	public abstract void doPOST(_Session session, _WebFormData formData, String lang);
}
