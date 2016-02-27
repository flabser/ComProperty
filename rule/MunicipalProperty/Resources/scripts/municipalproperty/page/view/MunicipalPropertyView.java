package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kz.flabs.localization.LanguageType;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
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

	protected _POJOListWrapper<Property> getViewPage(_Session session, _WebFormData formData, KufType kuf, LanguageType lang) {
		List<KufType> params = new ArrayList<KufType>();
		params.add(kuf);
		return getViewPage(session, formData, params, lang);
	}

	protected _POJOListWrapper<Property> getViewPage(_Session session, _WebFormData formData, List<KufType> set, LanguageType lang) {
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
	public abstract void doGET(_Session session, _WebFormData formData, LanguageType lang);

	@Override
	public abstract void doPOST(_Session session, _WebFormData formData, LanguageType lang);

	@Override
	public void doDELETE(_Session session, _WebFormData formData, LanguageType lang) {
		println(formData);

		PropertyDAO dao = new PropertyDAO(session);
		for (String id : formData.getListOfValuesSilently("docid")) {
			Property c = dao.findById(UUID.fromString(id));
			dao.delete(c);
		}
	}
}
