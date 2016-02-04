package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.dataengine.jpa.ViewPage;
import kz.flabs.localization.LanguageType;
import kz.nextbase.script._IPOJOObject;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._Session;
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

	protected _IPOJOObject getPropertyViewPage(_Session session, _WebFormData formData, KufType kuf, LanguageType lang) {
		List<KufType> params = new ArrayList<KufType>();
		params.add(kuf);
		return getPropertyViewPage(session, formData, params, lang);
	}

	protected _IPOJOObject getPropertyViewPage(_Session session, _WebFormData formData, List<KufType> set, LanguageType lang) {
		int pageNum = 1;
		int pageSize = session.getPageSize();
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}
		PropertyDAO dao = new PropertyDAO(session);
		ViewPage<Property> result = dao.findAllin("kuf", set, pageNum, pageSize);
		return new _POJOListWrapper<Property>(result.getResult(), result.getMaxPage(), result.getCount(), result.getPageNum(), lang);
	}

	@Override
	public abstract void doGET(_Session session, _WebFormData formData, LanguageType lang);

	@Override
	public abstract void doPOST(_Session session, _WebFormData formData, LanguageType lang);
}
