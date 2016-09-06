package municipalproperty.page.form;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.eclipse.persistence.exceptions.DatabaseException;

import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scheduler._EnumWrapper;
import com.exponentus.scripting._Exception;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._Validation;
import com.exponentus.scripting._WebFormData;
import com.exponentus.user.IUser;
import com.exponentus.util.TimeUtil;
import com.exponentus.util.Util;

import municipalproperty.dao.RealEstateDAO;
import municipalproperty.model.RealEstate;
import municipalproperty.model.constants.PropertyStatusType;
import reference.dao.CityDistrictDAO;
import reference.dao.LocalityDAO;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;
import reference.dao.RegionDAO;
import reference.dao.StreetDAO;
import reference.dao.TagDAO;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import reference.model.Tag;
import reference.model.constants.KufType;
import reference.model.embedded.Address;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

public class RealEstateForm extends AbstractMunicipalPropertyForm {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser<Long> user = session.getUser();
		RealEstate entity;
		if (!id.isEmpty()) {
			entity = getEntity(id, session);

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
		addContent(getActionBar(session, entity));
		addContent(new _EnumWrapper<>(PropertyStatusType.class.getEnumConstants()));
		addContent(new _EnumWrapper<>(KufType.class.getEnumConstants()));
		startSaveFormTransact(entity);
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

			RealEstateDAO dao = new RealEstateDAO(session);
			RealEstate entity;

			if (isNew) {
				entity = new RealEstate();
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
			entity.setOriginalCost(Util.convertStringToFloat(formData.getValueSilently("originalcost")));
			entity.setCumulativeDepreciation(Util.convertStringToFloat(formData.getValueSilently("cumulativedepreciation")));
			entity.setImpairmentLoss(Util.convertStringToFloat(formData.getValueSilently("impairmentloss")));
			entity.setBalanceCost(Util.convertStringToFloat(formData.getValueSilently("balancecost")));
			entity.setRevaluationAmount(Util.convertStringToFloat(formData.getValueSilently("revaluationamount")));

			ReceivingReasonDAO rrDao = new ReceivingReasonDAO(session);
			ReceivingReason rrEntity = rrDao.findById(formData.getValueSilently("receivingreason"));
			entity.setReceivingReason(rrEntity);

			entity.setCommissioningYear(formData.getNumberValueSilently("commissioningyear", 0));
			entity.setAcquisitionYear(formData.getNumberValueSilently("acquisitionyear", 0));
			entity.setYearRelease(formData.getNumberValueSilently("yearrelease", 0));
			entity.setAcceptanceDate(TimeUtil.stringToDate(formData.getValue("acceptancedate")));

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

			Address addr = entity.getAddress();
			addr.setRegion(new RegionDAO(session).findById(formData.getValueSilently("region")));
			addr.setLocality(new LocalityDAO(session).findById(formData.getValueSilently("locality")));
			addr.setCityDistrict(new CityDistrictDAO(session).findById(formData.getValueSilently("district")));
			addr.setStreet(new StreetDAO(session).findById(formData.getValueSilently("street")));
			addr.setHouseNumber(formData.getValueSilently("housenumber"));
			addr.setAdditionalInfo(formData.getValueSilently("additionalinfo"));
			addr.setCoordinates(formData.getValueSilently("coordinates"));

			IUser<Long> user = session.getUser();
			entity.addReaderEditor(user);

			save(entity, dao, isNew);

			finishSaveFormTransact(entity);
		} catch (_Exception | DatabaseException | SecureException e) {
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
		} else {

			TimeUtil.stringToDate(formData.getValueSilently("acceptancedate"));

		}

		if (formData.getValueSilently("originalcost").isEmpty()) {
			ve.addError("originalcost", "required", getLocalizedWord("field_is_empty", lang));
		} else if (formData.getFloatValueSilently("originalcost", 0) <= 0) {
			ve.addError("originalcost", "gt_0", getLocalizedWord("should_be_contain_value_more_than_zero", lang));
		}

		if (formData.getValueSilently("balancecost").isEmpty()) {
			ve.addError("balancecost", "required", getLocalizedWord("required", lang));
		} else if (formData.getFloatValueSilently("balancecost", 0) <= 0) {
			ve.addError("balancecost", "gt_0", getLocalizedWord("should_be_contain_value_more_than_zero", lang));
		}

		if (formData.getValueSilently("region").isEmpty()) {
			ve.addError("region", "required", getLocalizedWord("field_is_empty", lang));
		}

		return ve;
	}

	protected RealEstate getEntity(String id, _Session session) {
		RealEstateDAO dao = new RealEstateDAO(session);
		RealEstate entity = dao.findById(UUID.fromString(id));
		Address addr = entity.getAddress();
		if (addr == null) {
			entity.setAddress(Address.getStub(session));
		}
		return entity;
	}

	protected RealEstate getDefaultEntity(IUser<Long> user, KufType type, _Session session) {
		RealEstate entity = new RealEstate();
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
		entity.setPropertyCode(pcDao.findByName("Собственность"));
		// entity.setModel("");
		ReceivingReasonDAO rrDao = new ReceivingReasonDAO(session);
		entity.setReceivingReason(rrDao.findByName("Приобретено"));
		entity.setReadyToUse(true);
		entity.setAddress(Address.getStub(session));
		return entity;
	}
}
