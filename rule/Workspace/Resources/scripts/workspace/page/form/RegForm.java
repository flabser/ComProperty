package workspace.page.form;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import reference.model.constants.CountryCode;

public class RegForm extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {
		try {
			_Validation ve = validate(formData, session.getLang());
			if (ve.hasError()) {
				setBadRequest();
				setValidation(ve);
				return;
			}

			String id = formData.getValueSilently("fio");
			String org = formData.getValueSilently("org");
			String orgbin = formData.getValueSilently("orgbin");
			String login = formData.getValueSilently("login");
			String comment = formData.getValueSilently("comment");

		} catch (Exception e) {
			error(e);
		}
	}

	protected _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();
		if (formData.getValueSilently("name").isEmpty()) {
			ve.addError("name", "required", getLocalizedWord("field_is_empty", lang));
		}

		if (formData.getValueSilently("code").isEmpty()) {
			ve.addError("code", "required", getLocalizedWord("field_is_empty", lang));
		} else if (formData.getValueSilently("code").equalsIgnoreCase(CountryCode.UNKNOWN.name())) {
			ve.addError("code", "ne_unknown", getLocalizedWord("field_cannot_be_unknown", lang));
		}

		return ve;
	}
}
