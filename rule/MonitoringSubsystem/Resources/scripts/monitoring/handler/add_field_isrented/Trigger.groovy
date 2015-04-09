package monitoring.handler.add_field_isrented

import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoHandler

class Trigger extends _DoHandler{

    @Override
    void doHandler(_Session ses, Map<String, String[]> formData, String lang) {
        def db = ses.currentDatabase;
        def coll = db.getCollectionOfDocuments("viewtext4 = '014'", false);
        def doc = null;
        coll.entries.each {
            doc = it.getDocument();
            String rent_value = doc.getValueString("isrented");
            if (rent_value == "") {
                doc.replaceStringField("isrented", '0');
                doc.save("[supervisor]");
            }
        }
    }

	@Override
	public void doHandler(_Session session, _WebFormData formData) {
		// TODO Auto-generated method stub
		
	}

}
