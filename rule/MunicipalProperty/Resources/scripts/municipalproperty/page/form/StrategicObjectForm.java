package municipalproperty.page.form;

import kz.flabs.util.Util;
import com.exponentus.common.model.Attachment;
import com.exponentus.env.Environment;
import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._Validation;
import com.exponentus.scripting._WebFormData;
import com.exponentus.server.Server;
import com.exponentus.user.IUser;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Helper;
import municipalproperty.dao.StrategicObjectDAO;
import municipalproperty.model.StrategicObject;
import municipalproperty.model.constants.PropertyStatusType;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.eclipse.persistence.exceptions.DatabaseException;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;
import reference.dao.TagDAO;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import reference.model.Tag;
import reference.model.constants.KufType;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class StrategicObjectForm extends AbstractMunicipalPropertyForm {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String id = formData.getValueSilently("docid");
        IUser<Long> user = session.getUser();
        StrategicObject entity;
        if (!id.isEmpty()) {
            StrategicObjectDAO dao = new StrategicObjectDAO(session);
            entity = dao.findById(UUID.fromString(id));

            String attachmentId = formData.getValueSilently("attachment");
            if (!attachmentId.isEmpty() && entity.getAttachments() != null) {
                Attachment att = entity.getAttachments().stream().filter(it -> it.getIdentifier().equals(attachmentId)).findFirst().get();

                try {
                    String filePath = getTmpDirPath() + File.separator + Util.generateRandomAsText("qwertyuiopasdfghjklzxcvbnm", 10) + att.getRealFileName();
                    File attFile = new File(filePath);
                    FileUtils.writeByteArrayToFile(attFile, att.getFile());
                    showFile(filePath, att.getRealFileName());
                    Environment.fileToDelete.add(filePath);
                } catch (IOException ioe) {
                    Server.logger.errorLogEntry(ioe);
                }
                return;
            } else {
                setBadRequest();
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
        try {
            String id = formData.getValueSilently("docid");
            boolean isNew = id.isEmpty();

            _Validation ve = validate(formData, session.getLang(), isNew);
            if (ve.hasError()) {
                setBadRequest();
                setValidation(ve);
                return;
            }

            StrategicObjectDAO dao = new StrategicObjectDAO(session);
            StrategicObject entity;

            if (isNew) {
                entity = new StrategicObject();
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
            // entity.setModel(formData.getValueSilently("model"));
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

            String[] fileNames = formData.getListOfValuesSilently("fileid");
            if (fileNames.length > 0) {
                File userTmpDir = new File(Environment.tmpDir + File.separator + session.getUser().getUserID());
                for (String fn : fileNames) {
                    File file = new File(userTmpDir + File.separator + fn);
                    InputStream is = new FileInputStream(file);
                    Attachment att = new Attachment();
                    att.setRealFileName(fn);
                    att.setFile(IOUtils.toByteArray(is));
                    att.setAuthor(session.getUser());
                    att.setForm("attachment");
                    entity.getAttachments().add(att);
                }
            }

            IUser user = session.getUser();
            entity.addReaderEditor(user);

            save(entity, dao, isNew);

            finishSaveFormTransact(entity);
        } catch (_Exception | DatabaseException | SecureException | IOException e) {
            error(e);
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
            ve.addError("balancecost", "required", getLocalizedWord("field_is_empty", lang));
        } else if (formData.getFloatValueSilently("balancecost", 0) <= 0) {
            ve.addError("balancecost", "gt_0", getLocalizedWord("should_be_contain_value_more_than_zero", lang));
        }

        return ve;
    }

    protected StrategicObject getDefaultEntity(IUser user, KufType type, _Session session) {
        StrategicObject entity = new StrategicObject();
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
        return entity;
    }
}
