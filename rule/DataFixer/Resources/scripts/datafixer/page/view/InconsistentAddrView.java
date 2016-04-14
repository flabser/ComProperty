package datafixer.page.view;

import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.event._DoPage;
import municipalproperty.dao.filter.PropertyFilter;
import reference.model.constants.KufType;

/**
 * @author Kayra created 13-04-2016
 */

public class InconsistentAddrView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		int kuf = formData.getNumberValueSilently("kuf", -1);
		KufType kufType = KufType.getType(kuf);
		KufType kufParam;
		PropertyFilter propertyFilter = new PropertyFilter();

		switch (kufType) {
		case FURNITURE:
		case ANIMALS:
		case SPORT_EQUIPMENT:
		case OTHERS:
			kufParam = kufType;
			propertyFilter.addKufType(kufType);
			break;
		default:
			kufParam = KufType.FURNITURE;
			propertyFilter.addKufType(KufType.FURNITURE);
			propertyFilter.addKufType(KufType.ANIMALS);
			propertyFilter.addKufType(KufType.SPORT_EQUIPMENT);
			propertyFilter.addKufType(KufType.OTHERS);
			break;
		}

		// addContent(getViewPage(session, formData, propertyFilter, lang));
		// addContent(getSimpleActionBar(session, "personalestate-form",
		// kufParam, lang));
	}
}
