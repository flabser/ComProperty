package municipalproperty.page.view;

import java.util.List;

import com.exponentus.localization.constants.LanguageCode;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._Session;

import municipalproperty.dao.filter.PropertyFilter;
import reference.model.constants.KufType;

/**
 * @author Kayra created 05-01-2016
 */

public class EquipmentView extends AbstractMunicipalPropertyView {
	
	@Override
	public void doGET(_Session session, WebFormData formData) {
		super.doGET(session, formData);
		LanguageCode lang = session.getLang();
		int kuf = formData.getNumberValueSilently("kuf", -1);
		KufType kufType = KufType.getType(kuf);
		List<KufType> kufList = KufType.getListByGroupId(200);
		PropertyFilter propertyFilter = new PropertyFilter();
		
		if (kufList.contains(kufType)) {
			propertyFilter.setKufType(kufType);
		} else {
			propertyFilter.setKufTypes(kufList);
		}
		
		KufType kufParam;
		if (kufType == KufType.UNKNOWN) {
			kufParam = kufList.get(0);
		} else {
			kufParam = kufType;
		}
		
		addContent(getViewPage(session, formData, propertyFilter, lang));
		addContent(getSimpleActionBar(session, "equipment-form", kufParam, lang));
	}
}
