package municipalproperty.page.form;

import kz.flabs.util.Util;
import kz.lof.common.model.Attachment;
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
import municipalproperty.dao.ContractDAO;
import municipalproperty.dao.OrderDAO;
import municipalproperty.model.Contract;
import municipalproperty.model.Order;
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


public class ContractForm extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        IUser<Long> user = session.getUser();
        Contract entity;
        String id = formData.getValueSilently("docid");
        if (!id.isEmpty()) {
            ContractDAO dao = new ContractDAO(session);
            entity = dao.findById(UUID.fromString(id));
            addValue("formsesid", Util.generateRandomAsText());
        } else {
            entity = new Contract();
            entity.setAuthor(user);
            entity.setRegDate(new Date());
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
            entity.setRegDate(_Helper.convertStringToDate(formData.getValue("regdate")));
            entity.setExpired(_Helper.convertStringToDate(formData.getValue("expired")));

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

        if (formData.getValueSilently("orderid").isEmpty()) {
            ve.addError("orderid", "required", getLocalizedWord("field_is_empty", lang));
        }
        if (formData.getValueSilently("regnumber").isEmpty()) {
            ve.addError("regnumber", "required", getLocalizedWord("field_is_empty", lang));
        }
        if (formData.getValueSilently("regdate").isEmpty()) {
            ve.addError("regdate", "required", getLocalizedWord("field_is_empty", lang));
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
}