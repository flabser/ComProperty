package municipalproperty.page.form;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.eclipse.persistence.exceptions.DatabaseException;

import com.exponentus.common.model.Attachment;
import com.exponentus.env.EnvConst;
import com.exponentus.env.Environment;
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
import com.exponentus.scripting.event._DoPage;
import com.exponentus.user.IUser;
import com.exponentus.util.Util;
import com.exponentus.webserver.servlet.UploadedFile;

import municipalproperty.dao.OrderDAO;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Order;
import municipalproperty.model.Property;
import reference.model.PropertyCode;
import staff.model.Organization;

public class OrderForm extends _DoPage {

	@SuppressWarnings("unchecked")
	@Override
	public void doGET(_Session session, _WebFormData formData) {

		IUser<Long> user = session.getUser();
		Order entity;
		String id = formData.getValueSilently("docid");
		if (!id.isEmpty()) {
			OrderDAO dao = new OrderDAO(session);
			entity = dao.findById(UUID.fromString(id));
			addValue("formsesid", Util.generateRandomAsText());

			String attachmentId = formData.getValueSilently("attachment");
			if (!attachmentId.isEmpty() && entity.getAttachments() != null) {
				Attachment att = entity.getAttachments().stream().filter(it -> it.getIdentifier().equals(attachmentId)).findFirst().get();

				if (showAttachment(att)) {
					return;
				} else {
					setBadRequest();
				}
			}
		} else {
			entity = new Order();
			entity.setAuthor(user);
			entity.setRegDate(new Date());
			entity.setRegNumber("");
			String propertyId = formData.getValueSilently("propertyid");
			PropertyDAO propertyDAO = new PropertyDAO(session);
			Property property = propertyDAO.findById(propertyId);
			if (property == null) {
				property = new Property();
				property.setObjectName("");
				Organization org = new Organization();
				org.setName("");
				property.setBalanceHolder(org);
				PropertyCode pc = new PropertyCode();
				pc.setName("");
				property.setPropertyCode(pc);
			}

			List<Property> list = new ArrayList<Property>();
			list.add(property);
			entity.setProperties(list);
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
		addContent(new _EnumWrapper<>(Order.OrderStatus.class.getEnumConstants()));
		_ActionBar actionBar = new _ActionBar(session);
		actionBar.addAction(new _Action(getLocalizedWord("save_close", session.getLang()), "", _ActionType.SAVE_AND_CLOSE));
		actionBar.addAction(new _Action(getLocalizedWord("close", session.getLang()), "", _ActionType.CLOSE));
		if (entity.getId() != null) {
			_Action newOrderAction = new _Action(getLocalizedWord("new_contract", session.getLang()), "", "new_contract");
			newOrderAction.setURL("Provider?id=contract-form&orderid=" + entity.getId());
			actionBar.addAction(newOrderAction);
		}
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

			OrderDAO dao = new OrderDAO(session);
			Order entity;
			String id = formData.getValueSilently("docid");
			boolean isNew = id.isEmpty();

			if (isNew) {
				entity = new Order();
				String propertyId = formData.getValueSilently("propertyid");
				PropertyDAO propertyDAO = new PropertyDAO(session);
				Property property = propertyDAO.findById(propertyId);
				List<Property> list = new ArrayList<Property>();
				list.add(property);
				entity.setProperties(list);
			} else {
				entity = dao.findById(id);
			}

			entity.setDescription(formData.getValue("description"));
			entity.setRegNumber(formData.getValue("regnumber"));
			entity.setAppliedRegDate(new Date());
			entity.setOrderStatus(Order.OrderStatus.valueOf(formData.getValue("orderstatus")));

			String[] fileNames = formData.getListOfValuesSilently("fileid");
			if (fileNames.length > 0) {
				File userTmpDir = new File(Environment.tmpDir + File.separator + session.getUser().getUserID());
				for (String fn : fileNames) {
					File file = new File(userTmpDir + File.separator + fn);
					InputStream is = new FileInputStream(file);
					Attachment att = new Attachment();
					att.setRealFileName(fn);
					att.setFile(IOUtils.toByteArray(is));
					entity.getAttachments().add(att);
				}
			}

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
		} catch (_Exception | DatabaseException | IOException e) {
			error(e);
			setBadRequest();
		}
	}

	private _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();

		if (formData.getValueSilently("regnumber").isEmpty()) {
			ve.addError("regnumber", "required", getLocalizedWord("field_is_empty", lang));
		}

		/*
		 * if (formData.getValueSilently("propertyid").isEmpty()) {
		 * ve.addError("propertyid", "required",
		 * getLocalizedWord("field_is_empty", lang)); }
		 */

		if (formData.getValueSilently("description").isEmpty()) {
			ve.addError("description", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("orderstatus").isEmpty()) {
			ve.addError("orderstatus", "required", getLocalizedWord("field_is_empty", lang));
		}

		return ve;
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		String attachmentId = formData.getValueSilently("attachment");
		String attachmentName = formData.getValueSilently("att-name");

		if (id.isEmpty() || attachmentId.isEmpty() || attachmentName.isEmpty()) {
			return;
		}

		OrderDAO dao = new OrderDAO(session);
		Order entity = dao.findById(id);

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
