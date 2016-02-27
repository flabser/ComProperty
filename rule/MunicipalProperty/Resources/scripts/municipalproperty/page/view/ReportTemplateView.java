package municipalproperty.page.view;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.dao.ReportTemplateDAO;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class ReportTemplateView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		addContent(getViewPage(new ReportTemplateDAO(session), formData));

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {

	}

}
