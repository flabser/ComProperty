package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.localization.LanguageType;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 08-01-2016
 */

public class NetworkView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.ELECTRIC_NETWORKS);
		params.add(KufType.THERMAL_NETWORKS);
		params.add(KufType.GAS);
		params.add(KufType.WATER_SYSTEM);
		params.add(KufType.DRAIN);
		setContent(getPropertyViewPage(session, formData, params, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}

}
