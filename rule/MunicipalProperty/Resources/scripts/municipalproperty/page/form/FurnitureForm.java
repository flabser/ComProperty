package municipalproperty.page.form;

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
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;

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
			addMsg(getLocalizedWord("document_was_saved_succesfully", lang));
		} catch (_Exception e) {
			log(e);
			setBadRequest();
		}
	}

	private boolean validate(_WebFormData webFormData, LanguageType lang) {
		boolean validationState = true;
		if (webFormData.getValueSilently("objectname").isEmpty()) {
			addMsg("Поле \"Наименование\" не заполнено.");
			validationState = false;
		}
		if (webFormData.getValueSilently("originalcost").isEmpty()) {
			addMsg("Поле \"Первоначальная стоимость\" не заполнено.");
			validationState = false;
		}
		if (webFormData.getValueSilently("description").isEmpty()) {
			addMsg("Поле \"Описание объекта\" не заполнено.");
			validationState = false;
		}
		if (webFormData.getValueSilently("propertycode").isEmpty()) {
			addMsg("Поле \"Код права на имущество\" не заполнено.");
			validationState = false;
		}
		return validationState;

	}
}
