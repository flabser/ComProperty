package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.filter.PropertyFilter;

/**
 * @author Kayra created 12-04-2016
 */

public class AllPropertyView extends AbstractMunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		super.doGET(session, formData);
		LanguageCode lang = session.getLang();
		PropertyFilter propertyFilter = new PropertyFilter();
		addContent(getViewPage(session, formData, propertyFilter, lang));

	}
}
