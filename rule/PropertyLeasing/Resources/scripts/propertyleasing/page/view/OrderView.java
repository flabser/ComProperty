package propertyleasing.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.OrderDAO;

/**
 * @author Kayra created 27-01-2016
 */

public class OrderView extends MunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        addContent(getSimpleActionBar(session, "order-form", lang));
        addContent(getViewPage(new OrderDAO(session), formData));
    }
}
