package propertyleasing.page.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.exception.SecureException;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Property;
import municipalproperty.model.constants.KufType;

/**
 * Share abstract View for Municipal Property application Use it to code some
 * shared methods for all views of the application
 *
 * @author Kayra created 05-01-2016
 */

public abstract class MunicipalPropertyView extends _DoPage {

	protected _POJOListWrapper<Property> getViewPage(_Session session, _WebFormData formData, KufType kuf, LanguageCode lang) {
		List<KufType> params = new ArrayList<KufType>();
		params.add(kuf);
		return getViewPage(session, formData, params, lang);
	}

	protected _POJOListWrapper<Property> getViewPage(_Session session, _WebFormData formData, List<KufType> set, LanguageCode lang) {
		int pageNum = 1;
		int pageSize = session.getPageSize();
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}
		PropertyDAO dao = new PropertyDAO(session);
		ViewPage<Property> result = dao.findAllin("kuf", set, pageNum, pageSize);
		return new _POJOListWrapper<Property>(result.getResult(), result.getMaxPage(), result.getCount(), result.getPageNum(), session);
	}

	@Override
	public void doDELETE(_Session session, _WebFormData formData) {
		// println(formData);

		PropertyDAO dao = new PropertyDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			Property c = dao.findById(UUID.fromString(id));
			try {
				dao.delete(c);
			} catch (SecureException e) {
				setError(e);
			}
		}
	}
}
