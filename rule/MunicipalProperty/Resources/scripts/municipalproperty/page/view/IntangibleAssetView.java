package municipalproperty.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Kayra created 05-01-2016
 */

public class IntangibleAssetView extends MunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        ArrayList<KufType> params = new ArrayList<KufType>(Arrays.asList(KufType.SHARE_BLOCK, KufType.EQUITY));
        addContent(getViewPage(session, formData, params, lang));
        addContent(getSimpleActionBar(session, "intangibleasset-form", lang));
    }
}
