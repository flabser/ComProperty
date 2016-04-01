package municipalproperty.page.view;

import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.OrderDAO;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Order;
import municipalproperty.model.Property;

/**
 * @author Kayra created 27-01-2016
 */

public class OrderView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String propertyId = formData.getValueSilently("propertyid");
        LanguageCode lang = session.getLang();
        OrderDAO orderDAO = new OrderDAO(session);

        if (!propertyId.isEmpty()) {
            PropertyDAO propertyDAO = new PropertyDAO(session);
            Property property = propertyDAO.findById(propertyId);
            if (property != null) {
                ViewPage<Order> result = orderDAO.findAllByProperty(property);
                addContent(new _POJOListWrapper<>(result.getResult(), result.getMaxPage(), result.getCount(), result.getPageNum(), session));
            }
        } else {
            addContent(getSimpleActionBar(session, "order-form", lang));
            addContent(getViewPage(orderDAO, formData));
        }
    }
}
