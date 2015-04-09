package tender.form.rentapplication

import kz.nextbase.script._Document
import kz.nextbase.script._Helper
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._FormPostSave
import kz.nextbase.script.events._FormQuerySave
import kz.nextbase.script.mail._Memo

class PostSave extends _FormPostSave {
    @Override
    void doPostSave(_Session ses, _Document doc) {
        def cdb = ses.getCurrentDatabase();
        def srtuct = ses.getStructure();


       /*
        def pdoc = doc.getParentDocument()

        // если уже есть тендер
        if(pdoc.doc.getViewTextList()[7] == "tender" || pdoc.doc.hasField("tender")){ println("tender exists"); return};

        def complexUserID = ["[supervisor]", "[observer]"] as Set;
        def appl = cdb.getAllDocsByReader("parentdocid#number = ${pdoc.getDocID()} and form='rentapplication'", complexUserID, "[supervisor]")

        if(appl.size() >= 2){
            // открываем тендер
            def tender = new _Document(cdb);
            def emails = [] ;
            for(def it in appl){
                def emp = srtuct.getEmployer(it.getValueString("leasingholder_id"));
                if(emp.getEmail() != "" )  emails.add(emp.getEmail());
                tender.addReader(emp.getUserID());
            }

            tender.setForm("tender")
            tender.setParentDoc(pdoc);
            tender.setValueString("object_description", pdoc.getViewText());
            tender.setViewText(pdoc.getViewText())
            tender.addViewText("1") // status тендера  активный 0 - закрытый
            tender.setViewDate(new Date());

            tender.setAuthor("system");
            tender.addReader("[tender_reader]");
            tender.addEditor("[tender_editor]");
            tender.save("[supervisor]");

            pdoc.setViewText("tender", 7);
            pdoc.addStringField("tender", tender.getID());
            println pdoc.save("[supervisor]");
            println("tender created");

            // уведомление
            String body = "<b>Извещение о проведении тендера</b> на аренду объекта: ${pdoc.getViewText()}" +
                    "<table><tr><td>Краткая характеристика объекта тендера</td><td>${pdoc.getValueString("description")}</td></tr>" +
                    "<tr><td>Сроки принятия заявки на участие в тендере до</td><td>${ses.getDatePlusDays(15).format("dd.MM.yyyy")}</td></tr>"
                          "</table>";
            def memo = new _Memo("Извещение о проведении тендера","Извещение о проведении тендера", body, tender, false);
            def ma = ses.getMailAgent();
            ma.sendMail(emails,memo)
        }   */
    }
}
