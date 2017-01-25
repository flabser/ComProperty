package registry.page.form;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.exception.SecureException;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting.WebFormException;
import com.exponentus.scripting._POJOListWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._Validation;
import com.exponentus.server.Server;
import com.exponentus.user.IUser;

import reference.dao.OrgCategoryDAO;
import reference.model.OrgCategory;
import staff.dao.OrganizationDAO;
import staff.dao.OrganizationLabelDAO;
import staff.model.Organization;
import staff.model.OrganizationLabel;
import staff.page.form.StaffForm;

/**
 * @author Kayra created 09-01-2016
 */

public class IndividualForm extends StaffForm {

	@Override
	public void doGET(_Session session, WebFormData formData) {
		String id = formData.getValueSilently("docid");
		IUser<Long> user = session.getUser();
		try {
			Organization entity;
			if (!id.isEmpty()) {
				OrganizationDAO dao = new OrganizationDAO(session);
				entity = dao.findById(UUID.fromString(id));
			} else {
				entity = new Organization();
				entity.setAuthor(user);
				entity.setName("");
				entity.setBin("");
				OrgCategoryDAO ocDao = new OrgCategoryDAO(session);
				OrgCategory oc = null;
				try {
					oc = ocDao.findByName("Частный предприниматель");
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				entity.setOrgCategory(oc);
				entity.setLabels(new ArrayList<>());

			}
			addContent(entity);
			addContent(new _POJOListWrapper<>(new OrganizationLabelDAO(session).findAll().getResult(), session));
			addContent(getSimpleActionBar(session, session.getLang()));
		} catch (DAOException e) {
			setBadRequest();
			Server.logger.errorLogEntry(e);
		}
	}

	@Override
	public void doPOST(_Session session, WebFormData formData) {
		try {
			_Validation ve = validate(formData, session.getLang());
			if (ve.hasError()) {
				setBadRequest();
				setValidation(ve);
				return;
			}

			String id = formData.getValueSilently("docid");
			OrganizationDAO dao = new OrganizationDAO(session);
			Organization entity;
			boolean isNew = id.isEmpty();

			if (isNew) {
				entity = new Organization();
			} else {
				entity = dao.findById(UUID.fromString(id));
			}

			entity.setName(formData.getValue("name"));
			OrgCategoryDAO ocDao = new OrgCategoryDAO(session);
			OrgCategory oc = ocDao.findByName("Частный предприниматель");
			entity.setOrgCategory(oc);
			String bin = formData.getValueSilently("bin");
			if (!bin.isEmpty()) {
				entity.setBin(formData.getValue("bin"));
			}
			OrganizationLabelDAO olDao = new OrganizationLabelDAO(session);
			List<OrganizationLabel> labels = new ArrayList<>();
			for (String labelId : formData.getListOfValuesSilently("labels")) {
				if (!labelId.isEmpty()) {
					OrganizationLabel prgLabel = olDao.findById(labelId);
					if (prgLabel != null) {
						labels.add(prgLabel);
					}
				}
			}
			entity.setLabels(labels);

			if (isNew) {
				dao.add(entity);
			} else {
				dao.update(entity);
			}

			setRedirect("p?id=individual-view");
		} catch (WebFormException | SecureException | DAOException e) {
			logError(e);
		}
	}

	@Override
	protected _Validation validate(WebFormData formData, LanguageCode lang) {
		_Validation ve = new _Validation();

		if (formData.getValueSilently("name").isEmpty()) {
			ve.addError("name", "required", getLocalizedWord("field_is_empty", lang));
		}
		/*
		 * if (formData.getValueSilently("orgcategory").isEmpty()) {
		 * ve.addError("orgcategory", "required",
		 * getLocalizedWord("field_is_empty", lang)); }
		 */

		if (!formData.getValueSilently("bin").isEmpty() && formData.getValueSilently("bin").length() != 12) {
			ve.addError("bin", "eq_12", getLocalizedWord("bin_value_should_be_consist_from_12_symbols", lang));
		}

		return ve;
	}
}
