package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;


import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 08-01-2016
 */

public class PassengerTransportView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.BUS);
		params.add(KufType.TROLLEYBUS);
		params.add(KufType.TRAM);
		params.add(KufType.WATER_TRANSPORT);
		params.add(KufType.TAXI);
		addContent(getViewPage(session, formData, params, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
