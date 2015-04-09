package tender.page.application

import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class RentApplicationList extends _DoScript {
	

	public void doProcess(_Session ses, _WebFormData formData, String lang) {
        def page = formData.getNumberValueSilently("page", 0);
        def cuser = ses.getCurrentAppUser().getUserID();

        def formula = "form='rentapplication' and leasingholder_id='$cuser'";
        def db = ses.getCurrentDatabase()
        if(formData.getValueSilently("status") == "new")
            formula += " and status = 'new'";
        else if (formData.getValueSilently("status") == "reviewed")
            formula += " and status != 'new'";

        def col = db.getCollectionOfDocuments(formula, page, false, true)
        setContent(col)
	}
}