package municipalproperty.page.view;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.model.constants.KufType;

public class FurnitureView extends MunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		addContent(getSimpleActionBar(session, "furniture-form", lang));
		addContent(getViewPage(session, formData, KufType.FURNITURE, lang));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {

	}
}
