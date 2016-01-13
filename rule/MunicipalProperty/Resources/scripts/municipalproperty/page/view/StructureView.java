package municipalproperty.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class StructureView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		setContent(getSimpleActionBar(session, "structure", lang));
		setContent(getPropertyViewPage(session, formData, KufType.STRUCTURES));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}