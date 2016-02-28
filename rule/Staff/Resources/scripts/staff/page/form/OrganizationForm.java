package staff.page.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import kz.flabs.localization.LanguageCode;
import kz.flabs.users.User;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.nextbase.script._Exception;
import kz.lof.scripting._Validation;
import kz.lof.scripting._WebFormData;
import reference.dao.OrgCategoryDAO;
import reference.model.OrgCategory;
import staff.dao.OrganizationDAO;
import staff.dao.OrganizationLabelDAO;
import staff.model.Organization;
import staff.model.OrganizationLabel;

/**
 * @author Kayra created 09-01-2016
 */

public class OrganizationForm extends StaffForm {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		String id = formData.getValueSilently("docid");
		User user = session.getUser();
		Organization entity;
		if (!id.isEmpty()) {
			OrganizationDAO dao = new OrganizationDAO(session);
			entity = dao.findById(UUID.fromString(id));
		} else {
			entity = new Organization();
			entity.setAuthor(user);
			entity.setName("");
			entity.setBin("");
			entity.setRegDate(new Date());
			entity.setOrgCategory(new OrgCategory());
		}
		addContent(entity);
		addContent(new _POJOListWrapper(new OrganizationLabelDAO(session).findAll(), session));
		addContent(new _POJOListWrapper<>(new OrgCategoryDAO(session).findAll(), session));
		addContent(getSimpleActionBar(session, lang));
		startSaveFormTransact(entity);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {
		println(formData);
		try {
			_Validation ve = validate(formData, lang);
			if (ve.hasError()) {
				setBadRequest();
				setValidation(ve);
				return;
			}

			boolean isNew = false;
			String id = formData.getValueSilently("docid");
			OrganizationDAO dao = new OrganizationDAO(session);
			Organization entity;

			if (id.isEmpty()) {
				isNew = true;
				entity = new Organization();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			OrgCategoryDAO ocDao = new OrgCategoryDAO(session);
			entity.setOrgCategory(ocDao.findById(formData.getValue("orgcategory")));
			entity.setBin(formData.getValue("bin"));
			OrganizationLabelDAO olDao = new OrganizationLabelDAO(session);
			List<OrganizationLabel> labels = new ArrayList<OrganizationLabel>();
			for (String labelId : formData.getListOfValuesSilently("labels")) {
				OrganizationLabel prgLabel = olDao.findById(labelId);
				if (prgLabel != null) {
					labels.add(prgLabel);
				}
			}
			entity.setLabels(labels);

			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}

			finishSaveFormTransact(entity);
		} catch (_Exception e) {
			error(e);
		}
	}

	@Override
	protected _Validation validate(_WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();
		if (formData.getValueSilently("name").isEmpty()) {
			ve.addError("name", "empty", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("orgcategory").isEmpty()) {
			ve.addError("orgcategory", "empty", getLocalizedWord("field_is_empty", lang));
		}
		if (formData.getValueSilently("bin").isEmpty()) {
			ve.addError("bin", "empty", getLocalizedWord("required", lang));
		} else if (formData.getValueSilently("bin").length() > 12) {
			ve.addError("bin", "empty", getLocalizedWord("bin_value_should_be_consist_from_12_symbols", lang));
		}

		return ve;
	}
}
