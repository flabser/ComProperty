package municipalproperty.page.view;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.ReportTemplateDAO;

/**
 * @author Kayra created 06-01-2016
 */

public class ReportTemplateView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        addContent(getViewPage(new ReportTemplateDAO(session), formData));
    }
}
