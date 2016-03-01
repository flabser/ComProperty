package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 08-01-2016
 */

public class NetworkView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.ELECTRIC_NETWORKS);
		params.add(KufType.THERMAL_NETWORKS);
		params.add(KufType.GAS);
		params.add(KufType.WATER_SYSTEM);
		params.add(KufType.DRAIN);
		addContent(getViewPage(session, formData, params, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
