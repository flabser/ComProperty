package accountant.form.confirmation

import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script._ViewEntry
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._FormQuerySave

class QuerySave extends _FormQuerySave{

    @Override
    void doQuerySave(_Session ses, _Document doc, _WebFormData webFormData, String lang) {

        String op = webFormData.getValue("allowsave");
        if(op == null || "true".equals(op.trim()) || !"false".equals(op.trim())) {
            return;
        }

        def fileName = webFormData.getValue("filename");

        def savedDocs = ses.getCurrentDatabase().getCollectionOfDocuments(
                "form = 'saveddocslist' " +
                "and author = '${ses.getCurrentAppUser().getUserID()}' " +
                "and filename = '${fileName}'", false);

        Iterator<_ViewEntry> savedDocsIt = savedDocs.getEntries().iterator();
        while(savedDocsIt.hasNext()){
            def listDoc = savedDocsIt.next().document;
            def docs = ses.getCurrentDatabase().getCollectionOfDocuments("docid in (" + listDoc.viewText + ")", false);
            Iterator<_ViewEntry> it = docs.getEntries().iterator();

            while(it.hasNext()){
                ses.getCurrentDatabase().deleteDocument(String.valueOf(it.next().document.docID), true);
            }

            ses.getCurrentDatabase().deleteDocument(String.valueOf(listDoc.docID), true);
        }

    }
}
