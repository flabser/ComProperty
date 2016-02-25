package municipalproperty.page.view;

import kz.flabs.localization.LanguageType;
import kz.lof.scripting._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 08-01-2016
 */

public class AutomobileView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		addContent(getPropertyViewPage(session, formData, KufType.AUTOMOBILE, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}

}
