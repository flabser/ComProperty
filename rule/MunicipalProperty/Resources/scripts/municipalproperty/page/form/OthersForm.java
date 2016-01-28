package municipalproperty.page.form;

import java.util.UUID;

import kz.flabs.users.User;
import kz.flabs.util.Util;
import kz.nextbase.script._Exception;
import kz.nextbase.script._Helper;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._POJOObjectWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._URL;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import municipalproperty.dao.PersonalEstateDAO;
import municipalproperty.model.PersonalEstate;
import reference.dao.PropertyCodeDAO;
import reference.dao.ReceivingReasonDAO;

public class OthersForm extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
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
		setContent(new _POJOObjectWrapper(entity));
		setContent(new _POJOListWrapper(new PropertyCodeDAO(session).findAll()));
		setContent(new _POJOListWrapper(new ReceivingReasonDAO(session).findAll()));
	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData, String lang) {
		println(webFormData);
		try {
			boolean v = validate(webFormData);
			if (v == false) {
				return;
			}

			boolean isNew = false;
			String id = webFormData.getValueSilently("docid");
			PersonalEstateDAO dao = new PersonalEstateDAO(session);
			PersonalEstate entity = dao.findById(UUID.fromString(id));
			if (entity == null) {
				isNew = true;
				entity = new PersonalEstate();
			}
			// doc.addStringField("note", webFormData.getValueSilently("note"));
			// entity.setBalanceHolder(webFormData.getNumberValueSilently("balanceholder",
			// 0));
			// doc.addStringField("address",
			// webFormData.getValueSilently("address"));
			entity.setInvNumber(webFormData.getValueSilently("invnumber"));
			entity.setDescription(webFormData.getValueSilently("description"));
			entity.setObjectName(webFormData.getValueSilently("objectname"));
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
			entity.setOriginalCost(Util.convertStringToFloat(webFormData.getValueSilently("originalcost")));
			entity.setBalanceCost(Util.convertStringToFloat(webFormData.getValueSilently("balancecost")));
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
			entity.setYearRelease(webFormData.getNumberValueSilently("yearrelease", 0));
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
			entity.setAcceptanceDate(_Helper.convertStringToDate(webFormData.getValue("acceptancedate")));
			User user = session.getUser();
			entity.addReaderEditor(user);
			entity.addEditor("[operator]");
			// doc.addFile("rtfcontent", webFormData);

			_URL returnURL = session.getURLOfLastPage();
			localizedMsgBox(getLocalizedWord("Документ сохранен", lang));
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
			setRedirectURL(returnURL);

			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}
		} catch (_Exception e) {
			log(e);
		}
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
