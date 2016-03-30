package municipalproperty.page.view;

import java.util.List;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.filter.PropertyFilter;
import reference.model.constants.KufType;

public class StrategicObject extends AbstractMunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
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
