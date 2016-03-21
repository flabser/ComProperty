package municipalproperty.page.form;

import java.util.List;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.exception.SecureException;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Property;

import org.eclipse.persistence.exceptions.DatabaseException;

/**
 * Common abstract class for form of the Municipal Property application. Use it
 * to code some shared methods for all forms of the application
 *
 * @author Kayra created 27-01-2016
 */

public abstract class AbstractMunicipalPropertyForm extends _DoPage {

	protected _ActionBar getActionBar(_Session ses, Property entity) {
		LanguageCode lang = ses.getLang();
		_ActionBar actionBar = new _ActionBar(ses);
		if (entity.isEditable()) {
			actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), "", _ActionType.SAVE_AND_CLOSE));
		}
		actionBar.addAction(new _Action(getLocalizedWord("close", lang), "", _ActionType.CLOSE));

		if (entity.getId() != null) {
			_Action newOrderAction = new _Action(getLocalizedWord("new_order", lang), "", "new_order");
			newOrderAction.setURL("Provider?id=order-form&propertyid=" + entity.getId());
			actionBar.addAction(newOrderAction);
		}

		return actionBar;
	}

	protected void save(Property entity, DAO dao, boolean isNew) throws DatabaseException, SecureException {
		_Session ses = dao.getSession();
		PropertyDAO pDao = new PropertyDAO(ses);
		List<Property> list = pDao.findByInvNumAndName(entity.getInvNumber(), entity.getObjectName());
		for (Property e : list) {
			if (!e.equals(entity)) {
				_Validation ve = new _Validation();
				ve.addError("invnumber", "unique", getLocalizedWord("inv_number_is_not_unique", ses.getLang()));
				setBadRequest();
				setValidation(ve);
				return;
			}
		}

		if (isNew) {
			IUser<Long> user = ses.getUser();
			entity.addReaderEditor(user);
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