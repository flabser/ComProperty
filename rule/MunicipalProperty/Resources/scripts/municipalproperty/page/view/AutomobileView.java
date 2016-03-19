package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * @author Kayra created 08-01-2016
 */

public class AutomobileView extends MunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        addContent(getViewPage(session, formData, KufType.AUTOMOBILE, lang));
        addContent(getSimpleActionBar(session, "vehicle-form", lang));
    }
}
