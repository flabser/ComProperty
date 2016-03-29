package municipalproperty.page.view;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.ContractDAO;


public class ContractView extends AbstractMunicipalPropertyView {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        // LanguageCode lang = session.getLang();
        // addContent(getSimpleActionBar(session, "contract-form", lang));
        addContent(getViewPage(new ContractDAO(session), formData));
    }
}
