package municipalproperty.page.view;

import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import municipalproperty.dao.ContractDAO;

public class ContractView extends AbstractMunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		addContent(getViewPage(new ContractDAO(session), formData));
	}
}
