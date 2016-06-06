package municipalproperty.page.form;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.persistence.exceptions.DatabaseException;

import com.exponentus.common.model.Attachment;
import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.dataengine.jpa.SecureAppEntity;
import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting.IPOJOObject;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._Validation;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.user.IUser;

import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Property;

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

	protected IPOJOObject getACL(_Session ses, SecureAppEntity<UUID> entity) {
		return entity.getACL(ses);
	}

	protected void save(Property entity, DAO dao, boolean isNew) throws DatabaseException, SecureException {
		_Session ses = dao.getSession();
		PropertyDAO pDao = new PropertyDAO(ses);
		List<Property> list = pDao.findAllByInvNumAndName(entity.getInvNumber(), entity.getObjectName());
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
	public void doDELETE(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		String attachmentId = formData.getValueSilently("attachment");
		String attachmentName = formData.getValueSilently("att-name");

		if (id.isEmpty() || attachmentId.isEmpty() || attachmentName.isEmpty()) {
			return;
		}

		PropertyDAO dao = new PropertyDAO(session);
		Property entity = dao.findById(id);

		List<Attachment> atts = entity.getAttachments();
		List<Attachment> forRemove = atts.stream()
		        .filter(it -> attachmentId.equals(it.getIdentifier()) && it.getRealFileName().equals(attachmentName)).collect(Collectors.toList());
		atts.removeAll(forRemove);

		try {
			dao.update(entity);
		} catch (SecureException e) {
			setError(e);
		}
	}
}
