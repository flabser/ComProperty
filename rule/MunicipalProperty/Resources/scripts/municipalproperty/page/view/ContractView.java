package municipalproperty.page.view;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._Session;

import municipalproperty.dao.ContractDAO;

public class ContractView extends AbstractMunicipalPropertyView {

	@Override
	public void doGET(_Session session, WebFormData formData) {
		try {
			addContent(getViewPage(new ContractDAO(session), formData));
		} catch (DAOException e) {
			logError(e);
			setBadRequest();
		}
	}
}
