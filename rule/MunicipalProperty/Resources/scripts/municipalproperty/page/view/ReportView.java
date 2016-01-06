package municipalproperty.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.dao.ReportTemplateDAO;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class ReportView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		setContent(getViewPage(new ReportTemplateDAO(session), formData));

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
