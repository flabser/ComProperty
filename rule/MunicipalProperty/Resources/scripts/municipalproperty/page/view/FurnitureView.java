package municipalproperty.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import municipalproperty.model.constants.KufType;

public class FurnitureView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// println(formData);
		setContent(getSimpleActionBar(session, "furniture", lang));
		setContent(getPropertyViewPage(session, formData, KufType.FURNITURE));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
