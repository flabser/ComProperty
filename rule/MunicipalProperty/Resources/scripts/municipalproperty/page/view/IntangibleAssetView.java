package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.Arrays;

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
		ArrayList<KufType> params = new ArrayList<KufType>(Arrays.asList(KufType.SHARE_BLOCK, KufType.EQUITY));
		setContent(getPropertyViewPage(session, formData, params));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
