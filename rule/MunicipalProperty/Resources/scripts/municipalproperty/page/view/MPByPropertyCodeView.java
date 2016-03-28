package municipalproperty.page.view;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.filter.PropertyFilter;
import reference.dao.PropertyCodeDAO;
import reference.model.PropertyCode;


public class MPByPropertyCodeView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String cat = formData.getValueSilently("categoryid");
        PropertyCodeDAO pcDao = new PropertyCodeDAO(session);
        PropertyCode propertyCode = pcDao.findById(cat);
        PropertyFilter propertyFilter = new PropertyFilter();

        propertyFilter.setPropertyCode(propertyCode);

        addContent(getViewPage(session, formData, propertyFilter, session.getLang()));
    }
}
