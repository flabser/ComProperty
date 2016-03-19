package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kayra created 06-01-2016
 */

public class TransportView extends MunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        List<KufType> params = new ArrayList<KufType>();
        params.add(KufType.AUTOMOBILE);
        params.add(KufType.CAR);
        params.add(KufType.CARGO);
        params.add(KufType.DEJ_TRANSPORT);
        params.add(KufType.OFFICIAL_TRANSPORT);
        params.add(KufType.HOSPITAL_TRANSPORT);
        params.add(KufType.BUS);
        params.add(KufType.TROLLEYBUS);
        params.add(KufType.TRAM);
        params.add(KufType.TAXI);
        params.add(KufType.WATER_TRANSPORT);
        params.add(KufType.SPECIAL_EQUIPMENT);
        params.add(KufType.MOTORCYCLE);
        addContent(getViewPage(session, formData, params, lang));
        addContent(getSimpleActionBar(session, "vehicle-form", lang));
    }
}
