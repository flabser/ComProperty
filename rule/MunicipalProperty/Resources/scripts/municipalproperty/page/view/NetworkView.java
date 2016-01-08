package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;

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
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "networks", lang));
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.ELECTRIC_NETWORKS);
		params.add(KufType.THERMAL_NETWORKS);
		params.add(KufType.GAS);
		params.add(KufType.WATER_SYSTEM);
		params.add(KufType.DRAIN);
		setContent(getPropertyViewContent(session, formData, params));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
