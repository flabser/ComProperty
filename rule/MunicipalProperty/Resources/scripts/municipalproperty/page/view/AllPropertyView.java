package municipalproperty.page.view;

import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._Session;

import municipalproperty.dao.filter.PropertyFilter;

/**
 * @author Kayra created 12-04-2016
 */

public class AllPropertyView extends AbstractMunicipalPropertyView {
	
	@Override
	public void doGET(_Session session, WebFormData formData) {
		super.doGET(session, formData);
		LanguageCode lang = session.getLang();
		PropertyFilter propertyFilter = new PropertyFilter();
		addContent(getViewPage(session, formData, propertyFilter, lang));
		
	}
}
