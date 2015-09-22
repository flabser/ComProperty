package accountant.page.confirm

import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript

class ConfirmDoc extends _DoScript {

    @Override
    public void doProcess(_Session session, _WebFormData formData, String lang) {
        String op = formData.getValue("docid");
        if(op == null || op.trim().isEmpty()) return;
        int docid = op.toInteger();
        def doc = ses.getCurrentDatabase().getDocumentByID(docid);
        if(doc == null) return;
        doc.replaceViewText(doc.doc.getViewTextList()[3].equals("not_confirmed") ? "confirmed" : "not_confirmed", 3);
        doc.save(ses.getCurrentAppUser().getUserID());
        println ""

    }
}





