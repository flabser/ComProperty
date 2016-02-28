package municipalproperty.page.view;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 08-01-2016
 */

public class ElectricNetworkView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		addContent(getSimpleActionBar(session, "electric_network", lang));
		addContent(getViewPage(session, formData, KufType.ELECTRIC_NETWORKS, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {

	}

}
