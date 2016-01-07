package reference.page.view;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import reference.dao.ReceivingReasonDAO;

/**
 * 
 * 
 * @author Kayra created 07-01-2016
 */

public class ReceivingReasonView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		setContent(getSimpleActionBar(session, "receiving_reason", lang));
		setContent(getViewPage(new ReceivingReasonDAO(session), formData));
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
