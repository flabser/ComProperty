package municipalproperty.page.view;

import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Property;
import reference.dao.PropertyCodeDAO;
import reference.model.PropertyCode;


public class MPByPropertyCodeView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        String cat = formData.getValueSilently("categoryid");
        PropertyCodeDAO pcDao = new PropertyCodeDAO(session);
        PropertyCode pc = pcDao.findById(cat);
        PropertyDAO pDao = new PropertyDAO(session);

        int pageNum = 1;
        int pageSize = session.pageSize;
        if (formData.containsField("page")) {
            pageNum = formData.getNumberValueSilently("page", pageNum);
        }

        String[] orgIds = formData.getListOfValuesSilently("balanceholder");
        for (String oid : orgIds) {
            if (!oid.isEmpty()) {
                addValue("request_param", "balanceholder=" + oid);
            }
        }

        ViewPage<Property> page = pDao.findAllByPropertyCode(pc, pageNum, pageSize);
        addContent(new _POJOListWrapper(page.getResult(), page.getMaxPage(), page.getCount(), page.getPageNum(), session));
    }
}
