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

public class PassengerTransportView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.BUS);
		params.add(KufType.TROLLEYBUS);
		params.add(KufType.TRAM);
		params.add(KufType.WATER_TRANSPORT);
		params.add(KufType.TAXI);
		addContent(getPropertyViewPage(session, formData, params, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}

}
