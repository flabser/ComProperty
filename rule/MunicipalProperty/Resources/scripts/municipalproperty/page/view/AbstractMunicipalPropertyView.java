package municipalproperty.page.view;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.exception.SecureException;
import com.exponentus.localization.constants.LanguageCode;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._POJOListWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;
import com.exponentus.scripting.event._DoPage;

import municipalproperty.dao.PropertyDAO;
import municipalproperty.dao.filter.PropertyFilter;
import municipalproperty.model.Property;
import municipalproperty.model.constants.PropertyStatusType;
import reference.model.constants.KufType;
import staff.model.Organization;

/**
 * Share abstract View for Municipal Property application Use it to code some
 * shared methods for all views of the application
 *
 * @author Kayra created 05-01-2016
 */

public abstract class AbstractMunicipalPropertyView extends _DoPage {

	protected _POJOListWrapper<Property> getViewPage(_Session session, WebFormData formData, PropertyFilter filter,
			LanguageCode lang) {
		int pageNum = formData.getNumberValueSilently("page", 1);
		int pageSize = session.getPageSize();

		String[] orgIds = formData.getListOfValuesSilently("balanceholder");
		for (String oid : orgIds) {
			if (!oid.isEmpty()) {
				Organization org = new Organization();
				org.setId(UUID.fromString(oid));
				filter.addBalanceHolder(org);
			}
		}

		String status = formData.getValueSilently("status");
		if (!status.isEmpty()) {
			filter.setStatus(PropertyStatusType.valueOf(status.toUpperCase()));
		}

		try {
			PropertyDAO dao = new PropertyDAO(session);
			ViewPage<Property> result = dao.findAll(filter, pageNum, pageSize);
			return new _POJOListWrapper<>(result.getResult(), result.getMaxPage(), result.getCount(),
					result.getPageNum(), session);
		} catch (DAOException e) {
			logError(e);
			setBadRequest();
			return null;
		}
	}

	protected _ActionBar getSimpleActionBar(_Session session, String type, KufType kufType, LanguageCode lang) {
		_ActionBar actionBar = new _ActionBar(session);
		_Action newDocAction = new _Action(getLocalizedWord("new_", lang), "", "new_" + type);
		newDocAction.setURL("p?id=" + type + "&kuf=" + kufType.getCode());
		actionBar.addAction(newDocAction);
		actionBar.addAction(new _Action(getLocalizedWord("del_document", lang), "", _ActionType.DELETE_DOCUMENT));
		return actionBar;
	}

	@Override
	public void doGET(_Session session, WebFormData formData) {
		String[] orgIds = formData.getListOfValuesSilently("balanceholder");
		for (String oid : orgIds) {
			if (!oid.isEmpty()) {
				addValue("request_param", "balanceholder=" + oid);
			}
		}
		String propertyStatus = formData.getValueSilently("status");
		if (!propertyStatus.isEmpty()) {
			addValue("request_param", "status=" + propertyStatus);
		}
	}

	@Override
	public void doDELETE(_Session session, WebFormData formData) {
		// println(formData);

		try {
			PropertyDAO dao = new PropertyDAO(session);
			for (String id : formData.getListOfValuesSilently("docid")) {
				Property c = dao.findById(UUID.fromString(id));

				dao.delete(c);

			}
		} catch (DAOException | SecureException e) {
			logError(e);
			setBadRequest();
			return;
		}
	}
}
