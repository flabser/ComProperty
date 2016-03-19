package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

import java.util.List;


public class StrategicObject extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        int kuf = formData.getNumberValueSilently("kuf", -1);
        KufType kufType = KufType.getType(kuf);
        List<KufType> kufList = KufType.getListByGroupId(500);

        if (kufList.contains(kufType)) {
            addContent(getViewPage(session, formData, kufType, lang));
        } else {
            addContent(getViewPage(session, formData, kufList, lang));
        }

        addContent(getSimpleActionBar(session, "strategicobject-form", lang));
    }
}
