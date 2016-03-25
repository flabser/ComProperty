package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.filter.PropertyFilter;
import municipalproperty.model.constants.KufType;

/**
 * @author Kayra created 05-01-2016
 */

public class IntangibleAssetView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
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
