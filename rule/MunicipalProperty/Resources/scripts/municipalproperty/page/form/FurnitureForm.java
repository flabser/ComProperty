package municipalproperty.page.form;

import java.util.Date;
import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.flabs.util.Util;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Helper;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.dao.PersonalEstateDAO;
import municipalproperty.model.PersonalEstate;
import municipalproperty.model.constants.KufType;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import staff.model.Organization;

public class FurnitureForm extends MunicipalPropertyForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		PersonalEstate entity;
		if (!id.equals("")) {
			PersonalEstateDAO dao = new PersonalEstateDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new PersonalEstate();
			entity.setAuthor(user);
			entity.setRegDate(new Date());
			Organization tempEmptyOrg = new Organization();
			tempEmptyOrg.setName("");
			tempEmptyOrg.setBin("");
			entity.setBalanceHolder(tempEmptyOrg);
			entity.setKuf(KufType.FURNITURE);
			entity.setKof("");
			entity.setInvNumber("");
			entity.setObjectName("");
			PropertyCodeDAO pcDao = new PropertyCodeDAO(ses);
			entity.setPropertyCode(pcDao.findByName("Собственность"));
			entity.setModel("");
			ReceivingReasonDAO rrDao = new ReceivingReasonDAO(ses);
			entity.setReceivingReason(rrDao.findByName("Приобретено"));
			entity.setReadyToUse(true);

		}
		setContent(new _POJOObjectWrapper(entity, lang));
		setContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll(), lang));
		setContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll(), lang));
		setContent(getActionBar(session, lang, entity));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {
		println(formData);
		try {

			if (!validate(formData, lang)) {
				setBadRequest();
				return;
			}

			boolean isNew = false;
			String id = formData.getValueSilently("docid");
			PersonalEstateDAO dao = new PersonalEstateDAO(session);
			PersonalEstate entity;

			if (id.equals("")) {
				isNew = true;
				entity = new PersonalEstate();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setInvNumber(formData.getValueSilently("invnumber"));
			entity.setDescription(formData.getValueSilently("description"));
			entity.setObjectName(formData.getValueSilently("objectname"));
			entity.setKof(formData.getValueSilently("kof"));
			entity.setKuf(KufType.getType(formData.getNumberValueSilently("kuf", 0)));
			entity.setDescription(formData.getValueSilently("description"));
			PropertyCodeDAO pcDao = new PropertyCodeDAO(ses);
			PropertyCode pcEntity = pcDao.findByName(formData.getValueSilently("propertycode"));
			if (pcEntity != null && !entity.getPropertyCode().equals(pcEntity)) {
				entity.setPropertyCode(pcEntity);
			}
			entity.setOriginalCost(Util.convertStringToFloat(formData.getValueSilently("originalcost")));
			entity.setCumulativeDepreciation(Util.convertStringToFloat(formData.getValueSilently("cumulativedepreciation")));
			entity.setImpairmentLoss(Util.convertStringToFloat(formData.getValueSilently("impairmentloss")));
			entity.setBalanceCost(Util.convertStringToFloat(formData.getValueSilently("balancecost")));
			entity.setRevaluationAmount(Util.convertStringToFloat(formData.getValueSilently("afterrevaluationamount")));

			ReceivingReasonDAO rrDao = new ReceivingReasonDAO(ses);
			ReceivingReason rrEntity = rrDao.findByName(formData.getValueSilently("receivingreason"));
			if (rrEntity != null && !entity.getPropertyCode().equals(rrEntity)) {
				entity.setReceivingReason(rrEntity);
			}
			entity.setModel(formData.getValueSilently("model"));
			entity.setCommissioningYear(formData.getNumberValueSilently("commissioningyear", 0));
			entity.setAcquisitionYear(formData.getNumberValueSilently("acquisitionyear", 0));
			entity.setYearRelease(formData.getNumberValueSilently("yearrelease", 0));
			entity.setAcceptanceDate(_Helper.convertStringToDate(formData.getValue("acceptancedate")));
			User user = session.getUser();
			entity.addReaderEditor(user);

			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}
			addMsg(getLocalizedWord("document_was_saved_succesfully", lang));
		} catch (_Exception e) {
			log(e);
			setBadRequest();
		}
	}

	private boolean validate(_WebFormData webFormData, LanguageType lang) {
		boolean validationState = true;
		if (webFormData.getValueSilently("objectname").isEmpty()) {
			addValidationError("Поле \"Наименование\" не заполнено.");
			validationState = false;
		}
		if (webFormData.getValueSilently("originalcost").isEmpty()) {
			addValidationError("Поле \"Первоначальная стоимость\" не заполнено.");
			validationState = false;
		}
		if (webFormData.getValueSilently("description").isEmpty()) {
			addValidationError("Поле \"Описание объекта\" не заполнено.");
			validationState = false;
		}
		if (webFormData.getValueSilently("propertycode").isEmpty()) {
			addValidationError("Поле \"Код права на имущество\" не заполнено.");
			validationState = false;
		}
		return validationState;

	}
}
