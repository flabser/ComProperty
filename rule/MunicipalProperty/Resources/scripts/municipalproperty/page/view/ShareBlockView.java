package municipalproperty.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class ShareBlockView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "share_block", lang));
		setContent(getPropertyViewPage(session, formData, KufType.SHARE_BLOCK));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
