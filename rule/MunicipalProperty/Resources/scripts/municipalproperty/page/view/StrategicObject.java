package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

import java.util.ArrayList;
import java.util.List;


public class StrategicObject extends MunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        List<KufType> params = new ArrayList<KufType>();
        params.add(KufType.OBJECT_RESERVED_FUND);
        params.add(KufType.BOMBPROOF);
        params.add(KufType.FACTORY);
        params.add(KufType.COMBINES);
        params.add(KufType.AIRPORT);
        params.add(KufType.TRANSITIONS);
        addContent(getViewPage(session, formData, params, lang));
        addContent(getSimpleActionBar(session, "strategicobject-form", lang));
    }
}
