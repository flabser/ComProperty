package workspace.page.form;

import java.util.ArrayList;
import java.util.List;

import kz.lof.administrator.dao.LanguageDAO;
import kz.lof.env.Environment;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.nextbase.script._AppEntourage;
import kz.pchelka.reminder.MailAgent;

public class RegForm extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		_AppEntourage ent = session.getAppEntourage();
		addValue("serverversion", ent.getServerVersion());
		addValue("build", ent.getBuildTime());
		addValue("org", Environment.orgName);
		addValue("appname", ent.getAppName());
		addContent(new _POJOListWrapper(new LanguageDAO(session).findAll(), session));
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

			String email = formData.getValueSilently("email");
			String fio = formData.getValueSilently("fio");
			String org = formData.getValueSilently("org");
			String orgbin = formData.getValueSilently("orgbin");
			String login = formData.getValueSilently("login");
			String comment = formData.getValueSilently("comment");

			List<String> recipients = new ArrayList<String>();
			recipients.add("biosubj@gmail.com");

			MailAgent ma = new MailAgent();
			ma.sendMail("k-zone@ya.ru", recipients, "subject", fio + " " + org + "  " + orgbin + "  " + login + " " + comment);

		} catch (Exception e) {
			error(e);
		}
	}

	protected _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();

		if (formData.getValueSilently("email").isEmpty()) {
			ve.addError("email", "required", getLocalizedWord("field_is_empty", lang));
		}

		if (formData.getValueSilently("fio").isEmpty()) {
			ve.addError("fio", "required", getLocalizedWord("field_is_empty", lang));
		}

		if (formData.getValueSilently("org").isEmpty()) {
			ve.addError("org", "required", getLocalizedWord("field_is_empty", lang));
		}

		if (formData.getValueSilently("orgbin").isEmpty()) {
			ve.addError("orgbin", "required", getLocalizedWord("field_is_empty", lang));
		} else if (formData.getValueSilently("orgbin").length() != 12) {
			ve.addError("orgbin", "eq_12", getLocalizedWord("bin_value_should_be_consist_from_12_symbols", lang));
		}

		return ve;
	}
}
