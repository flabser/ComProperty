package municipalproperty.page.form;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.exponentus.common.model.ACL;
import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._EnumWrapper;
import com.exponentus.scripting._Exception;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._Validation;
import com.exponentus.scripting._WebFormData;
import com.exponentus.user.IUser;
import com.exponentus.util.TimeUtil;

import municipalproperty.dao.PersonalEstateDAO;
import municipalproperty.model.PersonalEstate;
import municipalproperty.model.constants.PropertyStatusType;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;
import reference.dao.TagDAO;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import reference.model.Tag;
import reference.model.constants.KufType;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class PersonalEstateForm extends AbstractMunicipalPropertyForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser<Long> user = session.getUser();
		PersonalEstate entity;
		if (!id.isEmpty()) {
			PersonalEstateDAO dao = new PersonalEstateDAO(session);
			entity = dao.findById(UUID.fromString(id));

			if (formData.containsField("attachment")) {
				if (showAttachment(formData.getValueSilently("attachment"), entity)) {
					return;
				} else {
					setBadRequest();
				}
			}
		} else {
			int kuf = formData.getNumberValueSilently("kuf", -1);
			KufType kufType = KufType.getType(kuf);
			entity = getDefaultEntity(user, kufType, session);
		}
		addContent(entity);
		addContent(new ACL(entity));
		addContent(getActionBar(session, entity));
		addContent(new _EnumWrapper<>(PropertyStatusType.class.getEnumConstants()));
		addContent(new _EnumWrapper<>(KufType.class.getEnumConstants()));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {
		devPrint(formData);
		try {
			String id = formData.getValueSilently("docid");
			boolean isNew = id.isEmpty();

			_Validation ve = validate(formData, session.getLang(), isNew);
			if (ve.hasError()) {
				setBadRequest();
				setValidation(ve);
				return;
			}

			PersonalEstateDAO dao = new PersonalEstateDAO(session);
			PersonalEstate entity;

			if (isNew) {
				entity = new PersonalEstate();
			} else {
				entity = dao.findById(id);
			}

			if (formData.containsField("balanceholder")) {
				OrganizationDAO oDao = new OrganizationDAO(session);
				Organization org = oDao.findById(formData.getValueSilently("balanceholder"));
				entity.setBalanceHolder(org);
			}
			entity.setInvNumber(formData.getValueSilently("invnumber"));
			entity.setDescription(formData.getValueSilently("description"));
			entity.setObjectName(formData.getValueSilently("objectname"));
			entity.setKof(formData.getValueSilently("kof"));
			entity.setKuf(KufType.getType(formData.getNumberValueSilently("kuf", 0)));
			entity.setPropertyStatusType(PropertyStatusType.valueOf(formData.getValue("propertystatus")));
			entity.setDescription(formData.getValueSilently("description"));
			PropertyCodeDAO pcDao = new PropertyCodeDAO(session);
			PropertyCode pcEntity = pcDao.findById(formData.getValueSilently("propertycode"));
			entity.setPropertyCode(pcEntity);
			entity.setOriginalCost(formData.getFloatValueSilently("originalcost", 0));
			entity.setCumulativeDepreciation(formData.getFloatValueSilently("cumulativedepreciation", 0));
			entity.setImpairmentLoss(formData.getFloatValueSilently("impairmentloss", 0));
			entity.setBalanceCost(formData.getFloatValueSilently("balancecost", 0));
			entity.setRevaluationAmount(formData.getFloatValueSilently("revaluationamount", 0));
			entity.setAfterRevaluationAmount(formData.getFloatValueSilently("afterrevaluationamount", 0));

			ReceivingReasonDAO rrDao = new ReceivingReasonDAO(session);
			ReceivingReason rrEntity = rrDao.findById(formData.getValueSilently("receivingreason"));
			entity.setReceivingReason(rrEntity);
			entity.setModel(formData.getValueSilently("model"));
			entity.setCommissioningYear(formData.getNumberValueSilently("commissioningyear", 0));
			entity.setAcquisitionYear(formData.getNumberValueSilently("acquisitionyear", 0));
			entity.setYearRelease(formData.getNumberValueSilently("yearrelease", 0));
			entity.setAcceptanceDate(TimeUtil.stringToDateWithPassion(formData.getValue("acceptancedate")));

			int rtu = formData.getNumberValueSilently("isreadytouse", 0);
			if (rtu == 1) {
				entity.setReadyToUse(true);
			} else {
				entity.setReadyToUse(false);
			}
			entity.setNotes(formData.getValueSilently("notes"));
			entity.setTechCert(formData.getValueSilently("techcert"));
			entity.setRegCert(formData.getValueSilently("regcert"));
			entity.setDecreesActs(formData.getValueSilently("decreesacts"));

			if (formData.containsField("tags")) {
				String[] tagIds = formData.getListOfValuesSilently("tags");
				if (tagIds.length > 0) {
					List<Tag> tags = new ArrayList<>();
					TagDAO tagDAO = new TagDAO(session);
					for (String tagId : tagIds) {
						if (!tagId.isEmpty()) {
							Tag tag = tagDAO.findById(UUID.fromString(tagId));
							if (tag != null) {
								tags.add(tag);
							}
						}
					}
					entity.setTags(tags);
				}
			}

			entity.setAttachments(getActualAttachments(entity.getAttachments()));

			save(entity, dao, isNew);

		} catch (SecureException e) {
			setError(e);
		} catch (_Exception e) {
			logError(e);
			setBadRequest();
		}
	}

	private _Validation validate(_WebFormData formData, LanguageCode lang, boolean isNew) {
		_Validation ve = new _Validation();

		if (isNew || formData.containsField("balanceholder")) {
			if (formData.getValueSilently("balanceholder").isEmpty()) {
				ve.addError("balanceholder", "required", getLocalizedWord("field_is_empty", lang));
			}
		}
		if (formData.getValueSilently("kof").isEmpty()) {
			ve.addError("kof", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("kuf").isEmpty()) {
			ve.addError("kuf", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("invnumber").isEmpty()) {
			ve.addError("invnumber", "required", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("objectname").isEmpty()) {
			ve.addError("objectname", "required", getLocalizedWord("field_is_empty", lang));
		}
		/*
		 * if (formData.getValueSilently("description").isEmpty()) {
		 * ve.addError("description", "required",
		 * getLocalizedWord("field_is_empty", lang)); }
		 */
		if (formData.getValueSilently("acceptancedate").isEmpty()) {
			ve.addError("acceptancedate", "required", getLocalizedWord("field_is_empty", lang));
		} else if (TimeUtil.stringToDateWithPassion(formData.getValueSilently("acceptancedate")) == null) {
			ve.addError("acceptancedate", "date_format_does_not_match_to", getLocalizedWord("date_format_does_not_match_to", lang));
		}

		if (formData.getValueSilently("originalcost").isEmpty()) {
			ve.addError("originalcost", "required", getLocalizedWord("field_is_empty", lang));
		} else if (formData.getFloatValueSilently("originalcost", 0) <= 0) {
			ve.addError("originalcost", "gt_0", getLocalizedWord("should_be_contain_value_more_than_zero", lang));
		}

		if (formData.getValueSilently("balancecost").isEmpty()) {
			ve.addError("balancecost", "required", getLocalizedWord("field_is_empty", lang));
		} else if (formData.getFloatValueSilently("balancecost", 0) <= 0) {
			ve.addError("balancecost", "gt_0", getLocalizedWord("should_be_contain_value_more_than_zero", lang));
		}

		return ve;
	}

	private PersonalEstate getDefaultEntity(IUser<Long> user, KufType type, _Session session) {
		PersonalEstate entity = new PersonalEstate();
		entity.setAuthor(user);
		Organization tempEmptyOrg = new Organization();
		tempEmptyOrg.setName("");
		tempEmptyOrg.setBin("");
		entity.setBalanceHolder(tempEmptyOrg);
		entity.setKuf(type);
		entity.setKof("");
		entity.setInvNumber("");
		entity.setObjectName("");
		PropertyCodeDAO pcDao = new PropertyCodeDAO(session);
		entity.setPropertyCode(pcDao.findAll().get(0));
		entity.setModel("");
		ReceivingReasonDAO rrDao = new ReceivingReasonDAO(session);
		entity.setReceivingReason(rrDao.findAll().get(0));
		entity.setReadyToUse(true);
		return entity;
	}
}
