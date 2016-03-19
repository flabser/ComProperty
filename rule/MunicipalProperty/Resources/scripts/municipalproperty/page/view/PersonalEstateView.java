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

public class PersonalEstateView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        int kuf = formData.getNumberValueSilently("kuf", -1);
        KufType kufType = KufType.getType(kuf);

        switch (kufType) {
            case FURNITURE:
            case ANIMALS:
            case SPORT_EQUIPMENT:
            case OTHERS:
                addContent(getViewPage(session, formData, kufType, lang));
                break;
            default:
                List<KufType> params = new ArrayList<>();
                params.add(KufType.FURNITURE);
                params.add(KufType.ANIMALS);
                params.add(KufType.SPORT_EQUIPMENT);
                params.add(KufType.OTHERS);
                addContent(getViewPage(session, formData, params, lang));
                break;
        }

        addContent(getSimpleActionBar(session, "personalestate-form", lang));
    }
}
