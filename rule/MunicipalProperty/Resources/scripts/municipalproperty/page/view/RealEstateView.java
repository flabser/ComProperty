package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.filter.PropertyFilter;
import municipalproperty.model.constants.KufType;

import java.util.List;

/**
 * @author Kayra created 06-01-2016
 */

public class RealEstateView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        super.doGET(session, formData);
        LanguageCode lang = session.getLang();
        int kuf = formData.getNumberValueSilently("kuf", -1);
        KufType kufType = KufType.getType(kuf);
        List<KufType> kufList = KufType.getListByGroupId(300);
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
        addContent(getSimpleActionBar(session, "realestate-form", kufParam, lang));
    }
}
