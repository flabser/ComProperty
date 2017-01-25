package municipalproperty.page.view;

import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._Session;

import municipalproperty.dao.filter.PropertyFilter;
import reference.model.constants.KufType;

/**
 * @author Kayra created 05-01-2016
 */

public class IntangibleAssetView extends AbstractMunicipalPropertyView {
	
	@Override
	public void doGET(_Session session, WebFormData formData) {
		super.doGET(session, formData);
		LanguageCode lang = session.getLang();
		int kuf = formData.getNumberValueSilently("kuf", -1);
		KufType kufType = KufType.getType(kuf);
		KufType kufParam;
		PropertyFilter propertyFilter = new PropertyFilter();
		
		switch (kufType) {
		case SHARE_BLOCK:
		case EQUITY:
			kufParam = kufType;
			propertyFilter.addKufType(kufType);
			break;
		default:
			kufParam = KufType.SHARE_BLOCK;
			propertyFilter.addKufType(KufType.SHARE_BLOCK);
			propertyFilter.addKufType(KufType.EQUITY);
			break;
		}
		
		addContent(getViewPage(session, formData, propertyFilter, lang));
		addContent(getSimpleActionBar(session, "intangibleasset-form", kufParam, lang));
	}
}
