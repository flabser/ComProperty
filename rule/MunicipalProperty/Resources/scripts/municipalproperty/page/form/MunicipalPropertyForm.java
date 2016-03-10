package municipalproperty.page.form;

import java.util.List;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Property;

/**
 * Common abstract class for form of the Municipal Property application. Use it
 * to code some shared methods for all forms of the application
 * 
 * @author Kayra created 27-01-2016
 */

public abstract class MunicipalPropertyForm extends _DoPage {

	protected _ActionBar getActionBar(_Session ses, Property entity) {
		LanguageCode lang = ses.getLang();
		_ActionBar actionBar = new _ActionBar(ses);
		actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), "", _ActionType.SAVE_AND_CLOSE));
		actionBar.addAction(new _Action(getLocalizedWord("close", lang), "", _ActionType.CLOSE));
		_Action transferAction = new _Action(getLocalizedWord("transfer", lang), getLocalizedWord("transfer", lang), "transfer");
		transferAction.setURL("Provider?id=transfer");
		// actionBar.addAction(transferAction);
		if (entity.isTagged("on_balance")) {
			_Action writtenOffAction = new _Action(getLocalizedWord("written_off", lang), getLocalizedWord("written_off", lang), "written_off");
			writtenOffAction.setURL("Provider?id=writtenoff");
			// actionBar.addAction(writtenOffAction);
		}
		return actionBar;
	}

	protected void save(Property entity, DAO dao, boolean isNew) {
		_Session ses = dao.getSession();
		PropertyDAO pDao = new PropertyDAO(ses);
		List<Property> list = pDao.findByInvNumAndName(entity.getInvNumber(), entity.getObjectName());
		for (Property e : list) {
			if (!e.equals(entity)) {
				_Validation ve = new _Validation();
				ve.addError("invnumber", "unique_error", getLocalizedWord("inv_number_is_not_unique", ses.getLang()));
				setBadRequest();
				setValidation(ve);
				return;
			}
		}

		if (isNew) {
			dao.add(entity);
		} else {
			dao.update(entity);
		}

	}

	@Override
	public abstract void doGET(_Session session, _WebFormData formData);

	@Override
	public abstract void doPOST(_Session session, _WebFormData webFormData);

}
