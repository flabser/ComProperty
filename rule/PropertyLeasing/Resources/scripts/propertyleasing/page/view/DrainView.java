package propertyleasing.page.view;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 09-01-2016
 */

public class DrainView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		addContent(getSimpleActionBar(session, "drain-form", lang));
		addContent(getViewPage(session, formData, KufType.DRAIN, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}