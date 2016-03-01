package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;


import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

public class StrategicObject extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.OBJECT_RESERVED_FUND);
		params.add(KufType.BOMBPROOF);
		params.add(KufType.FACTORY);
		params.add(KufType.COMBINES);
		params.add(KufType.AIRPORT);
		params.add(KufType.TRANSITIONS);
		addContent(getViewPage(session, formData, params, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
