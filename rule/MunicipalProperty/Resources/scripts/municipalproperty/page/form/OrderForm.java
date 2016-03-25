package municipalproperty.page.form;

import kz.flabs.util.Util;
import kz.lof.env.EnvConst;
import kz.lof.env.Environment;
import kz.lof.exception.SecureException;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.user.IUser;
import kz.lof.webserver.servlet.UploadedFile;
import kz.nextbase.script._EnumWrapper;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Helper;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import municipalproperty.dao.OrderDAO;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Attachment;
import municipalproperty.model.Order;
import municipalproperty.model.Property;
import org.apache.commons.io.IOUtils;
import org.eclipse.persistence.exceptions.DatabaseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class OrderForm extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        IUser<Long> user = session.getUser();
        Order entity;
        String id = formData.getValueSilently("docid");
        if (!id.isEmpty()) {
            OrderDAO dao = new OrderDAO(session);
            entity = dao.findById(UUID.fromString(id));
            addValue("formsesid", Util.generateRandomAsText());
        } else {
            entity = getDefaultEntity(user);
            String propertyId = formData.getValueSilently("propertyid");
            PropertyDAO propertyDAO = new PropertyDAO(session);
            Property property = propertyDAO.findById(propertyId);
            entity.setProperty(property);

            String fsId = formData.getValueSilently(EnvConst.FSID_FIELD_NAME);
            addValue("formsesid", fsId);
            List<String> formFiles = null;
            Object obj = session.getAttribute(fsId);
            if (obj == null) {
                formFiles = new ArrayList<String>();
            } else {
                formFiles = (List<String>) obj;
            }

            List<UploadedFile> filesToPublish = new ArrayList<UploadedFile>();

            for (String fn : formFiles) {
                UploadedFile uf = (UploadedFile) session.getAttribute(fsId + "_file" + fn);
                if (uf == null) {
                    uf = new UploadedFile();
                    uf.setName(fn);
                    session.setAttribute(fsId + "_file" + fn, uf);
                }
                filesToPublish.add(uf);
            }
            addContent(new _POJOListWrapper(filesToPublish, session));
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

            OrderDAO dao = new OrderDAO(session);
            Order entity;
            String id = formData.getValueSilently("docid");
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
            entity.setRegNumber(formData.getValue("regnumber"));
            entity.setRegDate(_Helper.convertStringToDate(formData.getValueSilently("regdate")));
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
                    att.setAuthor(session.getUser());
                    att.setForm("attachment");
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
