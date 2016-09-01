package municipalproperty.page.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.eclipse.persistence.exceptions.DatabaseException;

import com.exponentus.common.dao.AttachmentDAO;
import com.exponentus.common.model.Attachment;
import com.exponentus.env.EnvConst;
import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scheduler._EnumWrapper;
import com.exponentus.scripting.IPOJOObject;
import com.exponentus.scripting._Exception;
import com.exponentus.scripting._POJOListWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._Validation;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.scripting.event._DoForm;
import com.exponentus.user.IUser;
import com.exponentus.util.TimeUtil;
import com.exponentus.webserver.servlet.UploadedFile;

import municipalproperty.dao.ContractDAO;
import municipalproperty.dao.OrderDAO;
import municipalproperty.model.Contract;
import municipalproperty.model.Order;

public class ContractForm extends _DoForm {

	@SuppressWarnings("unchecked")
	@Override
	public void doGET(_Session session, _WebFormData formData) {
		IUser<Long> user = session.getUser();
		Contract entity;
		String id = formData.getValueSilently("docid");
		if (!id.isEmpty()) {
			ContractDAO dao = new ContractDAO(session);
			entity = dao.findById(UUID.fromString(id));

			if (formData.containsField("attachment")) {
				if (showAttachment(formData.getValueSilently("attachment"), entity)) {
					return;
				} else {
					setBadRequest();
				}
			}
		} else {
			entity = new Contract();
			entity.setAuthor(user);
			entity.setAppliedRegDate(new Date());
			entity.setExpired(new Date());
			entity.setRegNumber("");
			String orderId = formData.getValueSilently("orderid");
			OrderDAO orderDAO = new OrderDAO(session);
			Order order = orderDAO.findById(orderId);
			if (order == null) {
				order = new Order();
				order.setDescription("");
			}
			entity.setOrder(order);
			String fsId = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
			addValue("formsesid", fsId);
			List<String> formFiles = null;
			Object obj = session.getAttribute(fsId);
			if (obj == null) {
				formFiles = new ArrayList<String>();
			} else {
				formFiles = (List<String>) obj;
			}

			List<IPOJOObject> filesToPublish = new ArrayList<IPOJOObject>();

			for (String fn : formFiles) {
				UploadedFile uf = (UploadedFile) session.getAttribute(fsId + "_file" + fn);
				if (uf == null) {
					uf = new UploadedFile();
					uf.setName(fn);
					session.setAttribute(fsId + "_file" + fn, uf);
				}
				filesToPublish.add(uf);
			}
			addContent(new _POJOListWrapper<IPOJOObject>(filesToPublish, session));
		}

		addContent(entity);
		addContent(new _EnumWrapper<>(Contract.ContractStatus.class.getEnumConstants()));
		_ActionBar actionBar = new _ActionBar(session);
		actionBar.addAction(new _Action(getLocalizedWord("save_close", session.getLang()), "", _ActionType.SAVE_AND_CLOSE));
		actionBar.addAction(new _Action(getLocalizedWord("close", session.getLang()), "", _ActionType.CLOSE));
		addContent(actionBar);
		startSaveFormTransact(entity);
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

			ContractDAO dao = new ContractDAO(session);
			Contract entity;
			String id = formData.getValueSilently("docid");
			boolean isNew = id.isEmpty();

			if (isNew) {
				entity = new Contract();
				String orderId = formData.getValueSilently("orderid");
				OrderDAO orderDAO = new OrderDAO(session);
				Order order = orderDAO.findById(orderId);
				entity.setOrder(order);
			} else {
				entity = dao.findById(id);
			}

			entity.setDescription(formData.getValue("description"));
			entity.setRegNumber(formData.getValue("regnumber"));
			entity.setAppliedRegDate(TimeUtil.convertStringToDate(formData.getValue("appliedregdate")));
			entity.setExpired(TimeUtil.convertStringToDate(formData.getValue("expired")));

			entity.setAttachments(getActualAttachments(entity.getAttachments()));

			if (isNew) {
				IUser<Long> user = session.getUser();
				entity.addReaderEditor(user);
				entity = dao.add(entity);
			} else {
				entity = dao.update(entity);
			}

			finishSaveFormTransact(entity);
		} catch (SecureException e) {
			setError(e);
		} catch (_Exception | DatabaseException e) {
			logError(e);
			setBadRequest();
		}
	}

	private _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();

		if (formData.getValueSilently("orderid").isEmpty()) {
			ve.addError("orderid", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("regnumber").isEmpty()) {
			ve.addError("regnumber", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("appliedregdate").isEmpty()) {
			ve.addError("appliedregdate", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("expired").isEmpty()) {
			ve.addError("expired", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("description").isEmpty()) {
			ve.addError("description", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("contractstatus").isEmpty()) {
			ve.addError("contractstatus", "required", getLocalizedWord("field_is_empty", lang));
		}

		return ve;
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		String attachmentId = formData.getValueSilently("attachment");
		// String attachmentName = formData.getValueSilently("att-name");

		if (id.isEmpty()
		        || attachmentId.isEmpty()/* || attachmentName.isEmpty() */) {
			return;
		}

		ContractDAO dao = new ContractDAO(session);
		Contract entity = dao.findById(id);

		AttachmentDAO attachmentDAO = new AttachmentDAO(session);
		Attachment att = attachmentDAO.findById(attachmentId);
		entity.getAttachments().remove(att);

		try {
			dao.update(entity);
		} catch (SecureException e) {
			setError(e);
		}
	}
}
