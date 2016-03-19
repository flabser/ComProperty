package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kayra created 05-01-2016
 */

public class IntangibleAssetView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        int kuf = formData.getNumberValueSilently("kuf", -1);
        KufType kufType = KufType.getType(kuf);

        switch (kufType) {
            case SHARE_BLOCK:
            case EQUITY:
                addContent(getViewPage(session, formData, kufType, lang));
                break;
            default:
                List<KufType> params = new ArrayList<>();
                params.add(KufType.SHARE_BLOCK);
                params.add(KufType.EQUITY);
                addContent(getViewPage(session, formData, params, lang));
                break;
        }

        addContent(getSimpleActionBar(session, "intangibleasset-form", lang));
    }
}
