package municipalproperty.page.view;

import java.util.List;

import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._Session;

import municipalproperty.dao.filter.PropertyFilter;
import reference.model.constants.KufType;

public class StrategicObject extends AbstractMunicipalPropertyView {
	
	@Override
	public void doGET(_Session session, WebFormData formData) {
		super.doGET(session, formData);
		LanguageCode lang = session.getLang();
		int kuf = formData.getNumberValueSilently("kuf", -1);
		KufType kufType = KufType.getType(kuf);
		List<KufType> kufList;
		PropertyFilter propertyFilter = new PropertyFilter();
		
		if (kuf == 502) {
			kufList = KufType.getListByGroupId(kuf);
			propertyFilter.setKufTypes(kufList);
		} else {
			kufList = KufType.getListByGroupId(500);
			if (kufList.contains(kufType)) {
				propertyFilter.setKufType(kufType);
			} else {
				propertyFilter.setKufTypes(kufList);
			}
		}
		
		KufType kufParam;
		if (kufType == KufType.UNKNOWN) {
			kufParam = kufList.get(0);
		} else {
			kufParam = kufType;
		}
		
		addContent(getViewPage(session, formData, propertyFilter, lang));
		addContent(getSimpleActionBar(session, "strategicobject-form", kufParam, lang));
	}
}
