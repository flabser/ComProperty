package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

import java.util.List;

/**
 * @author Kayra created 06-01-2016
 */

public class VehicleView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        int kuf = formData.getNumberValueSilently("kuf", -1);
        KufType kufType = KufType.getType(kuf);
        List<KufType> kufList;

        if (kuf == 401 || kuf == 4016) {
            kufList = KufType.getListByGroupId(kuf);
            addContent(getViewPage(session, formData, kufList, lang));
        } else {
            kufList = KufType.getListByGroupId(400);
            if (kufList.contains(kufType)) {
                addContent(getViewPage(session, formData, kufType, lang));
            } else {
                addContent(getViewPage(session, formData, kufList, lang));
            }
        }

        KufType kufParam;
        if (kufType == KufType.UNKNOWN) {
            kufParam = kufList.get(0);
        } else {
            kufParam = kufType;
        }

        addContent(getSimpleActionBar(session, "vehicle-form", kufParam, lang));
    }
}
