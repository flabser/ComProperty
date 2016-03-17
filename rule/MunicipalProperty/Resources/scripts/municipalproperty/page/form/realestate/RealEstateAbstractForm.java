package municipalproperty.page.form.realestate;

import kz.flabs.util.Util;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.user.IUser;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Helper;
import municipalproperty.dao.RealEstateDAO;
import municipalproperty.model.RealEstate;
import municipalproperty.model.constants.KufType;
import municipalproperty.model.constants.PropertyStatusType;
import municipalproperty.page.form.MunicipalPropertyForm;
import reference.dao.*;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import reference.model.Tag;
import reference.model.embedded.Address;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public abstract class RealEstateAbstractForm extends MunicipalPropertyForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        addContent(new _EnumWrapper<>(PropertyStatusType.class.getEnumConstants()));
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData) {
        println(formData);
        try {
            _Validation ve = validate(formData, session.getLang());
            if (ve.hasError()) {
                setBadRequest();
                setValidation(ve);
                return;
            }

            String id = formData.getValueSilently("docid");
            RealEstateDAO dao = new RealEstateDAO(session);
            RealEstate entity;
            boolean isNew = id.isEmpty();

            if (isNew) {
                entity = new RealEstate();
            } else {
                entity = dao.findById(id);
            }

            OrganizationDAO oDao = new OrganizationDAO(session);
            Organization org = oDao.findById(formData.getValueSilently("balanceholder"));
            entity.setBalanceHolder(org);
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
            entity.setAcceptanceDate(_Helper.convertStringToDate(formData.getValue("acceptancedate")));

            int rtu = formData.getNumberValueSilently("isreadytouse", 0);
            if (rtu == 1) {
                entity.setReadyToUse(true);
            } else {
                entity.setReadyToUse(false);
            }
            entity.setNotes(formData.getValueSilently("notes"));

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

            entity.getAddress().setRegion(new RegionDAO(session).findById(formData.getValueSilently("region")));
            entity.getAddress().setLocality(new LocalityDAO(session).findById(formData.getValueSilently("locality")));
            entity.getAddress().setCityDistrict(new CityDistrictDAO(session).findById(formData.getValueSilently("district")));
            entity.getAddress().setStreet(new StreetDAO(session).findById(formData.getValueSilently("street")));
            entity.getAddress().setHouseNumber(formData.getValueSilently("housenumber"));
            entity.getAddress().setAdditionalInfo(formData.getValueSilently("additionalinfo"));

            IUser<Long> user = session.getUser();
            entity.addReaderEditor(user);

            save(entity, dao, isNew);

            finishSaveFormTransact(entity);
        } catch (_Exception e) {
            error(e);
            setBadRequest();
        }
    }

    private _Validation validate(_WebFormData formData, LanguageCode lang) {
        _Validation ve = new _Validation();

        if (formData.getValueSilently("balanceholder").isEmpty()) {
            ve.addError("balanceholder", "required", getLocalizedWord("field_is_empty", lang));
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
        if (formData.getValueSilently("description").isEmpty()) {
            ve.addError("description", "required", getLocalizedWord("field_is_empty", lang));
        }
        if (formData.getValueSilently("acceptancedate").isEmpty()) {
            ve.addError("acceptancedate", "required", getLocalizedWord("field_is_empty", lang));
        } else {
            try {
                Date d = _Helper.convertStringToDate(formData.getValueSilently("acceptancedate"));
            } catch (_Exception e) {
                ve.addError("acceptancedate", "date", getLocalizedWord("date_format_does_not_match_to", lang) + " dd.MM.YYYY");
            }
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

        return ve;
    }

    protected RealEstate getEntity(String id, _Session session) {
        RealEstateDAO dao = new RealEstateDAO(session);
        RealEstate entity = dao.findById(UUID.fromString(id));
        Address addr = entity.getAddress();
        if (addr == null) {
            entity.setAddress(Address.getStub(session));
        } else if (addr.getStreet().getName().equalsIgnoreCase("unknown")) {
            entity.getAddress().getStreet().setName("");
        }
        return entity;
    }

    protected RealEstate getDefaultEntity(IUser user, KufType type, _Session session) {
        RealEstate entity = new RealEstate();
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
