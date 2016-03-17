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

public class ObjectReservedFundView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		addContent(getSimpleActionBar(session, "object-reserved-fund-form", lang));
		addContent(getViewPage(session, formData, KufType.OBJECT_RESERVED_FUND, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
