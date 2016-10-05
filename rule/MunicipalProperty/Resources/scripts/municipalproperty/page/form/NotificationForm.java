package municipalproperty.page.form;

import java.util.UUID;

import com.exponentus.exception.SecureException;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.scripting.event._DoForm;

import municipalproperty.dao.NotificationDAO;
import municipalproperty.model.Notification;

public class NotificationForm extends _DoForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		Notification entity;
		String id = formData.getValueSilently("docid");
		NotificationDAO dao = new NotificationDAO(session);
		entity = dao.findById(UUID.fromString(id));

		addContent(entity);
		_ActionBar actionBar = new _ActionBar(session);
		actionBar.addAction(new _Action(getLocalizedWord("close", session.getLang()), "", _ActionType.CLOSE));
		addContent(actionBar);
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");

		if (id.isEmpty()) {
			return;
		}

		NotificationDAO dao = new NotificationDAO(session);
		Notification entity = dao.findById(id);

		try {
			dao.update(entity);
		} catch (SecureException e) {
			setError(e);
		}
	}
}
