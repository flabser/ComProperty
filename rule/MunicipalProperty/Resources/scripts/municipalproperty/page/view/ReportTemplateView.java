package municipalproperty.page.view;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;

import municipalproperty.dao.ReportTemplateDAO;

/**
 * @author Kayra created 06-01-2016
 */

public class ReportTemplateView extends AbstractMunicipalPropertyView {
	
	@Override
	public void doGET(_Session session, _WebFormData formData) {
		try {
			addContent(getViewPage(new ReportTemplateDAO(session), formData));
		} catch (DAOException e) {
			logError(e);
			setBadRequest();
			return;
		}
	}
}
