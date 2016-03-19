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

public class PersonalEstateView extends MunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();

        List<KufType> params = new ArrayList<KufType>();
        params.add(KufType.FURNITURE);
        params.add(KufType.ANIMALS);
        params.add(KufType.SPORT_EQUIPMENT);
        params.add(KufType.OTHERS);
        addContent(getViewPage(session, formData, params, lang));
        addContent(getSimpleActionBar(session, "personalestate-form", lang));
    }
}
