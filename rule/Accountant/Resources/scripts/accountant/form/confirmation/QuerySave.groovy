package accountant.form.confirmation

import kz.nextbase.script.*
import kz.nextbase.script.events._FormQuerySave

import java.util.prefs.Preferences

class QuerySave extends _FormQuerySave{

    @Override
    void doQuerySave(_Session ses, _Document doc, _WebFormData webFormData, String lang) {

        String op = webFormData.getValue("allowsave");
        if(op == null || "true".equals(op.trim()) || !"false".equals(op.trim())) {
            Preferences.userRoot().node(ses.user.userID).node("saveddocsids").remove("ids");
            return;
        }

        Preferences pref = Preferences.userRoot().node(ses.user.userID).node("saveddocsids");
        String ids = pref.get("ids", null);
        if(ids == null) return;

        def docs = ses.getCurrentDatabase().getCollectionOfDocuments("docid in (" + ids + ")", false);
        Iterator<_ViewEntry> it = docs.getEntries().iterator();

        while(it.hasNext()){
            ses.getCurrentDatabase().deleteDocument(String.valueOf(it.next().document.docID), true);
        }

        pref.remove("ids");
    }
}
