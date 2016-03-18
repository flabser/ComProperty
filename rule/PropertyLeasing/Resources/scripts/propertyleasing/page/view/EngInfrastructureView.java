package propertyleasing.page.view;

import java.util.ArrayList;
import java.util.Arrays;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

public class EngInfrastructureView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		ArrayList<KufType> params = new ArrayList<KufType>(Arrays.asList(KufType.BILLBOARD, KufType.COLUMNS, KufType.ELECTRIC_NETWORKS,
		        KufType.THERMAL_NETWORKS, KufType.GAS, KufType.WATER_SYSTEM, KufType.DRAIN, KufType.ROAD, KufType.PARKING));
		addContent(getViewPage(session, formData, params, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}