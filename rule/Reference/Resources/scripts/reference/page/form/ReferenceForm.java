package reference.page.form;

import java.util.Date;

import kz.lof.common.model.page.form.Form;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.scriptprocessor.page.IOutcomeObject;
import kz.lof.user.IUser;
import kz.lof.user.SuperUser;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import reference.model.Reference;

/**
 * @author Kayra created 03-01-2016
 */

public abstract class ReferenceForm extends Form {

	protected _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();
		if (formData.getValueSilently("name").isEmpty()) {
			ve.addError("name", "required", getLocalizedWord("field_is_empty", lang));
		}

		return ve;
	}

	protected IOutcomeObject getSimpleActionBar(_Session ses) {
		_ActionBar actionBar = new _ActionBar(ses);
		LanguageCode lang = ses.getLang();
		IUser<Long> user = ses.getUser();
		if (user.getId() == SuperUser.ID || user.getRoles().contains("reference_admin")) {
			actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), "", _ActionType.SAVE_AND_CLOSE));
		}
		actionBar.addAction(new _Action(getLocalizedWord("close", lang), "", _ActionType.CLOSE));
		return actionBar;

	}

	protected Reference getDefaultEntity(IUser<Long> user, Reference entity) {
		entity.setAuthor(user);
		entity.setRegDate(new Date());
		entity.setName("");
		return entity;
	}

	@Override
	public abstract void doGET(_Session session, _WebFormData formData);

	@Override
	public abstract void doPOST(_Session session, _WebFormData formData);
}
