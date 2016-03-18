package municipalproperty.page.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import kz.lof.env.Environment;
import kz.lof.exception.SecureException;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Helper;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import municipalproperty.dao.OrderDAO;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Order;
import municipalproperty.model.Property;

import org.apache.commons.io.IOUtils;
import org.eclipse.persistence.exceptions.DatabaseException;

public class OrderForm extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser<Long> user = session.getUser();
		Order entity;
		if (!id.isEmpty()) {
			OrderDAO dao = new OrderDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = getDefaultEntity(user);
			String propertyId = formData.getValueSilently("propertyid");
			PropertyDAO propertyDAO = new PropertyDAO(session);
			Property property = propertyDAO.findById(propertyId);
			entity.setProperty(property);
		}
		addContent(entity);
		addContent(new _EnumWrapper<>(Order.OrderStatus.class.getEnumConstants()));
		_ActionBar actionBar = new _ActionBar(session);
		actionBar.addAction(new _Action(getLocalizedWord("save_close", session.getLang()), "", _ActionType.SAVE_AND_CLOSE));
		actionBar.addAction(new _Action(getLocalizedWord("close", session.getLang()), "", _ActionType.CLOSE));
		addContent(actionBar);
		startSaveFormTransact(entity);
	}

	protected Order getDefaultEntity(IUser<Long> user) {
		Order entity = new Order();
		entity.setAuthor(user);
		entity.setRegDate(new Date());
		return entity;
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

			String id = formData.getValueSilently("docid");
			OrderDAO dao = new OrderDAO(session);
			Order entity;
			boolean isNew = id.isEmpty();

			if (isNew) {
				entity = new Order();
				String propertyId = formData.getValueSilently("propertyid");
				PropertyDAO propertyDAO = new PropertyDAO(session);
				Property property = propertyDAO.findById(propertyId);
				entity.setProperty(property);
			} else {
				entity = dao.findById(id);
			}

			entity.setDescription(formData.getValue("description"));
			entity.setRegDate(_Helper.convertStringToDate(formData.getValueSilently("regdate")));
			entity.setOrderStatus(Order.OrderStatus.valueOf(formData.getValue("orderstatus")));
			String fn = formData.getValueSilently("fileid");
			if (!fn.isEmpty()) {
				File userTmpDir = new File(Environment.tmpDir + File.separator + session.getUser().getUserID());
				File file = new File(userTmpDir + File.separator + fn);
				InputStream is = new FileInputStream(file);
				entity.setAttachment(IOUtils.toByteArray(is));
			}
			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}

			finishSaveFormTransact(entity);
		} catch (SecureException e) {
			setError(e);
		} catch (_Exception | DatabaseException | IOException e) {
			error(e);
			setBadRequest();
		}
	}

	private _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();

		if (formData.getValueSilently("description").isEmpty()) {
			ve.addError("description", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("orderstatus").isEmpty()) {
			ve.addError("orderstatus", "required", getLocalizedWord("field_is_empty", lang));
		}
		try {
			Date d = _Helper.convertStringToDate(formData.getValueSilently("regdate"));
		} catch (_Exception e) {
			ve.addError("regdate", "date", getLocalizedWord("date_format_does_not_match_to", lang) + " dd.MM.YYYY");
		}

		return ve;
	}
}
