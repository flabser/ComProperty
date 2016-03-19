package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kayra created 08-01-2016
 */

public class PassengerTransportView extends MunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        List<KufType> params = new ArrayList<KufType>();
        params.add(KufType.BUS);
        params.add(KufType.TROLLEYBUS);
        params.add(KufType.TRAM);
        params.add(KufType.WATER_TRANSPORT);
        params.add(KufType.TAXI);
        addContent(getViewPage(session, formData, params, lang));
        addContent(getSimpleActionBar(session, "vehicle-form", lang));
    }
}
