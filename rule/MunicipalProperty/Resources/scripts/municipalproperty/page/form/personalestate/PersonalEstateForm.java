package municipalproperty.page.form.personalestate;

import java.util.Date;

import kz.flabs.localization.LanguageType;
import kz.flabs.users.User;
import kz.flabs.util.Util;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Helper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.dao.PersonalEstateDAO;
import municipalproperty.model.PersonalEstate;
import municipalproperty.model.constants.KufType;
import municipalproperty.page.form.MunicipalPropertyForm;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

public abstract class PersonalEstateForm extends MunicipalPropertyForm {

	@Override
	public abstract void doGET(_Session session, _WebFormData formData, LanguageType lang);

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
				entity = dao.findById(id);
			}

			OrganizationDAO oDao = new OrganizationDAO(ses);
			Organization org = oDao.findById(formData.getValueSilently("balanceholderid"));
			entity.setBalanceHolder(org);
			entity.setInvNumber(formData.getValueSilently("invnumber"));
			entity.setDescription(formData.getValueSilently("description"));
			entity.setObjectName(formData.getValueSilently("objectname"));
			entity.setKof(formData.getValueSilently("kof"));
			entity.setKuf(KufType.getType(formData.getNumberValueSilently("kuf", 0)));
			entity.setDescription(formData.getValueSilently("description"));
			PropertyCodeDAO pcDao = new PropertyCodeDAO(ses);
			PropertyCode pcEntity = pcDao.findById(formData.getValueSilently("propertycode"));
			entity.setPropertyCode(pcEntity);
			entity.setOriginalCost(Util.convertStringToFloat(formData.getValueSilently("originalcost")));
			entity.setCumulativeDepreciation(Util.convertStringToFloat(formData.getValueSilently("cumulativedepreciation")));
			entity.setImpairmentLoss(Util.convertStringToFloat(formData.getValueSilently("impairmentloss")));
			entity.setBalanceCost(Util.convertStringToFloat(formData.getValueSilently("balancecost")));
			entity.setRevaluationAmount(Util.convertStringToFloat(formData.getValueSilently("afterrevaluationamount")));

			ReceivingReasonDAO rrDao = new ReceivingReasonDAO(ses);
			ReceivingReason rrEntity = rrDao.findById(formData.getValueSilently("receivingreason"));
			entity.setReceivingReason(rrEntity);
			entity.setModel(formData.getValueSilently("model"));
			entity.setCommissioningYear(formData.getNumberValueSilently("commissioningyear", 0));
			entity.setAcquisitionYear(formData.getNumberValueSilently("acquisitionyear", 0));
			entity.setYearRelease(formData.getNumberValueSilently("yearrelease", 0));
			entity.setAcceptanceDate(_Helper.convertStringToDate(formData.getValue("acceptancedate")));
			int rtu = formData.getNumberValueSilently("isreadytouse", 0);
			if (rtu == 1) {
				entity.setReadyToUse(true);
			} else {
				entity.setReadyToUse(false);
			}
			entity.setNotes(formData.getValueSilently("notes"));

			User user = session.getUser();
			entity.addReaderEditor(user);

			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}

			finishSaveFormTransact(entity);
		} catch (_Exception e) {
			error(e);
			setBadRequest();
		}
	}

	private boolean validate(_WebFormData webFormData, LanguageType lang) {
		boolean validationState = true;

		if (webFormData.getValueSilently("balanceholderid").isEmpty()) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("balance_holder", lang) + "\""
			        + getLocalizedWord("has_been_not_filled", lang));
			validationState = false;
		}
		if (webFormData.getValueSilently("kof").isEmpty()) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("kof", lang) + "\""
			        + getLocalizedWord("has_been_not_filled", lang));
			validationState = false;
		}
		if (webFormData.getValueSilently("kuf").isEmpty()) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("kuf", lang) + "\""
			        + getLocalizedWord("has_been_not_filled", lang));
			validationState = false;
		}
		if (webFormData.getValueSilently("invnumber").isEmpty()) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("inv_number", lang) + "\""
			        + getLocalizedWord("has_been_not_filled", lang));
			validationState = false;
		}
		if (webFormData.getValueSilently("objectname").isEmpty()) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("name", lang) + "\""
			        + getLocalizedWord("has_been_not_filled", lang));
			validationState = false;
		}
		if (webFormData.getValueSilently("description").isEmpty()) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("description", lang) + "\""
			        + getLocalizedWord("has_been_not_filled", lang));
			validationState = false;
		}
		if (webFormData.getValueSilently("acceptancedate").isEmpty()) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("acceptance_date", lang) + "\""
			        + getLocalizedWord("has_been_not_filled", lang));
			validationState = false;
		}
		if (webFormData.getValueSilently("originalcost").isEmpty()) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("original_cost", lang) + "\""
			        + getLocalizedWord("has_been_not_filled", lang));
			validationState = false;
		} else if (webFormData.getFloatValueSilently("originalcost", 0) <= 0) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("original_cost", lang) + "\""
			        + getLocalizedWord("should_be_contain_value_more_than_zero", lang));
			validationState = false;
		}

		if (webFormData.getValueSilently("balancecost").isEmpty()) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("balance_cost", lang) + "\""
			        + getLocalizedWord("has_been_not_filled", lang));
			validationState = false;
		} else if (webFormData.getFloatValueSilently("balancecost", 0) <= 0) {
			addValidationError(getLocalizedWord("the_field", lang) + "\"" + getLocalizedWord("balance_cost", lang) + "\""
			        + getLocalizedWord("should_be_contain_value_more_than_zero", lang));
			validationState = false;
		}

		return validationState;

	}

	protected PersonalEstate getDefaultEntity(User user, KufType type) {
		PersonalEstate entity = new PersonalEstate();
		entity.setAuthor(user);
		entity.setRegDate(new Date());
		Organization tempEmptyOrg = new Organization();
		tempEmptyOrg.setName("");
		tempEmptyOrg.setBin("");
		entity.setBalanceHolder(tempEmptyOrg);
		entity.setKuf(type);
		entity.setKof("");
		entity.setInvNumber("");
		entity.setObjectName("");
		PropertyCodeDAO pcDao = new PropertyCodeDAO(ses);
		entity.setPropertyCode(pcDao.findByName("Собственность"));
		entity.setModel("");
		ReceivingReasonDAO rrDao = new ReceivingReasonDAO(ses);
		entity.setReceivingReason(rrDao.findByName("Приобретено"));
		entity.setReadyToUse(true);
		return entity;
	}
}
