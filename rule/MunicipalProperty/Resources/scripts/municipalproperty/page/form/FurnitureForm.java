package municipalproperty.page.form;

import kz.flabs.users.User;
import kz.flabs.util.Util;
import kz.nextbase.script.*;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import municipalproperty.dao.PersonalEstateDAO;
import municipalproperty.model.PersonalEstate;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;

import java.util.UUID;


public class FurnitureForm extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData, String lang) {
        String id = formData.getValueSilently("docid");
        User user = session.getUser();
        PersonalEstate entity;
        if (id.isEmpty()) {
            entity = new PersonalEstate();
            entity.setAuthor(user);
        } else {
            PersonalEstateDAO dao = new PersonalEstateDAO(session);
            entity = dao.findById(UUID.fromString(id));
        }
        setContent(new _POJOObjectWrapper(entity));
        setContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll()));
        setContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll()));
        setContent(getActionBar(session, lang));
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {
        try {
            boolean v = validate(formData);
            if (!v) {
                setBadRequest();
                return;
            }

            boolean isNew = false;
            String id = formData.getValueSilently("docid");
            PersonalEstateDAO dao = new PersonalEstateDAO(session);
            PersonalEstate entity;

            if (id.isEmpty()) {
                isNew = true;
                entity = new PersonalEstate();
            } else {
                entity = dao.findById(UUID.fromString(id));
                if (entity == null) {
                    isNew = true;
                    entity = new PersonalEstate();
                }
            }

            entity.setInvNumber(formData.getValueSilently("invnumber"));
            entity.setDescription(formData.getValueSilently("description"));
            entity.setObjectName(formData.getValueSilently("objectname"));

            // doc.addStringField("note", webFormData.getValueSilently("note"));
            // entity.setBalanceHolder(webFormData.getNumberValueSilently("balanceholder",
            // 0));
            // doc.addStringField("address",
            // webFormData.getValueSilently("address"));

            // entity.setPropertyCode(webFormData.getValueSilently("propertycode"));
            // doc.addNumberField("region",
            // webFormData.getNumberValueSilently("region", 0));
            // doc.addNumberField("city",
            // webFormData.getNumberValueSilently("city",
            // 0));
            // doc.addNumberField("district",
            // webFormData.getNumberValueSilently("district", 0));
            // doc.addNumberField("street",
            // webFormData.getNumberValueSilently("street", 0));
            // doc.addStringField("limitdepreciation",
            // webFormData.getValueSilently("limitdepreciation").replace(",",
            // "."));
            // doc.addStringField("home", webFormData.getValueSilently("home"));
            // doc.addStringField("appartament",
            // webFormData.getValueSilently("appartament"));
            // doc.addStringField("regdoc",
            // webFormData.getValueSilently("regdoc"));
            entity.setOriginalCost(Util.convertStringToFloat(formData.getValueSilently("originalcost")));
            entity.setBalanceCost(Util.convertStringToFloat(formData.getValueSilently("balancecost")));
            // doc.addStringField("estimatedcost",
            // webFormData.getValueSilently("estimatedcost"));
            // doc.addStringField("residualcost",
            // webFormData.getValueSilently("residualcost"));
            // doc.addStringField("receiptbasisingproperty",
            // webFormData.getValueSilently("receiptbasisingproperty"));
            // doc.addStringField("receiptbasisinbalance",
            // webFormData.getValueSilently("receiptbasisinbalance"));
            // doc.addStringField("cumulativedepreciation",
            // webFormData.getValueSilently("cumulativedepreciation"));
            // doc.addStringField("deterioration",
            // webFormData.getValueSilently("deterioration"));
            // doc.addStringField("repair",
            // webFormData.getValueSilently("repair"));
            // doc.addStringField("isrented",
            // webFormData.getValueSilently("isrented"));
            // doc.addStringField("amount",
            // webFormData.getValueSilently("amount"));
            // doc.addStringField("model",
            // webFormData.getValueSilently("model"));
            // doc.addStringField("color",
            // webFormData.getValueSilently("color"));
            // doc.addStringField("width",
            // webFormData.getValueSilently("width"));
            // doc.addStringField("height",
            // webFormData.getValueSilently("height"));
            // doc.addStringField("depth",
            // webFormData.getValueSilently("depth"));
            entity.setYearRelease(formData.getNumberValueSilently("yearrelease", 0));
            // doc.addStringField("depreciating",
            // webFormData.getValueSilently("depreciating"));
            // doc.addStringField("pledge",
            // webFormData.getValueSilently("pledge"));
            // doc.addStringField("arrestingbyacourtdecision",
            // webFormData.getValueSilently("arrestingbyacourtdecision"));
            // doc.addStringField("legalclaim",
            // webFormData.getValueSilently("legalclaim"));
            // doc.addStringField("orderofremovalfrombalance",
            // webFormData.getValueSilently("orderofremovalfrombalance"));
            // doc.addStringField("restransferacceptance",
            // webFormData.getValueSilently("restransferacceptance"));
            // doc.addStringField("propertyarticlein",
            // webFormData.getValueSilently("propertyarticlein"));
            // doc.addStringField("technicalpassport",
            // webFormData.getValueSilently("technicalpassport"));
            entity.setAcceptanceDate(_Helper.convertStringToDate(formData.getValue("acceptancedate")));
            User user = session.getUser();
            entity.addReaderEditor(user);
            entity.addEditor("[operator]");
            // doc.addFile("rtfcontent", webFormData);

            /*
             * if (doc.isNewDoc) { returnURL.changeParameter("page", "0"); }
			 */

            // doc.setViewText(doc.getValueString("objectname") + " " +
            // doc.getValueString("description"));
            // doc.addViewText(doc.getValueString("objectname"));
            // doc.addViewText(doc.getValueString("description"));
            // doc.addViewText(doc.getValueString("estimatedcost"));
            // doc.addViewText(session.currentDatabase
            // .getGlossaryCustomFieldValueByDOCID(doc.getValueString("propertycode").toInteger(),
            // "code"));
            // doc.setViewDate(doc.getValueDate("acceptancedate"));

            if (isNew) {
                dao.add(entity);
            } else {
                dao.update(entity);
            }

            _URL returnURL = session.getURLOfLastPage();
            localizedMsgBox(getLocalizedWord("document_was_saved_succesfully", lang));
            setRedirectURL(returnURL);
        } catch (_Exception e) {
            log(e);
        }
    }

    private _ActionBar getActionBar(_Session ses, String lang) {
        _ActionBar actionBar = new _ActionBar(ses);
        actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), "", _ActionType.SAVE_AND_CLOSE));
        actionBar.addAction(new _Action(getLocalizedWord("close", lang), "", _ActionType.CLOSE));
        return actionBar;
    }

    private boolean validate(_WebFormData webFormData) {
        if (webFormData.getValueSilently("objectname").isEmpty()) {
            localizedMsgBox("Поле \"Наименование\" не заполнено.");
            return false;
        }
        if (webFormData.getValueSilently("originalcost").isEmpty()) {
            localizedMsgBox("Поле \"Первоначальная стоимость\" не заполнено.");
            return false;
        }
        if (webFormData.getValueSilently("description").isEmpty()) {
            localizedMsgBox("Поле \"Описание объекта\" не заполнено.");
            return false;
        }
        if (webFormData.getValueSilently("propertycode").isEmpty()) {
            localizedMsgBox("Поле \"Код права на имущество\" не заполнено.");
            return false;
        }
        return true;
    }
}
