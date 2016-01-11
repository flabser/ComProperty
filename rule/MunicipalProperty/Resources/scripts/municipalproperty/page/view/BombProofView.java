package municipalproperty.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 09-01-2016
 */

public class BombProofView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		setContent(getSimpleActionBar(session, "boombproof", lang));
		setContent(getPropertyViewPage(session, formData, KufType.BOMBPROOF));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
