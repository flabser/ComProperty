package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 05-01-2016
 */

public class IntangibleAssetView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "intangible_asset", lang));
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.FURNITURE);
		params.add(KufType.ANIMALS);
		params.add(KufType.SPORT_EQUIPMENT);
		params.add(KufType.OTHERS);
		setContent(getPropertyViewContent(session, formData, params));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
