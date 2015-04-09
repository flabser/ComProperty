package page.rent_application_review

import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import kz.nextbase.script.mail._Memo
import nextbase.groovy.*

class DoScript extends _DoScript {
	

	public void doProcess(_Session ses, _WebFormData formData, String lang) {
        def cdb = ses.getCurrentDatabase();
        def srtuct = ses.getStructure();
		def doc = ses.db.getDocumentByID(formData.getValue("docid"))

        if(formData.getValue("decision") != "approved" && formData.getValue("decision") != "rejected") return;

		doc.addStringField("status", formData.getEncodedValueSilently("decision"));
        doc.addDateField("review_date", new Date());
        doc.addStringField("review_comment", formData.getEncodedValueSilently("comment"));
        doc.setViewText(formData.getEncodedValueSilently("decision"), 2)
		doc.save("[supervisor]")

        def pdoc = doc.getParentDocument()

        // если уже есть тендер
        if(pdoc.doc.getViewTextList()[7] == "tender" || pdoc.doc.hasField("tender")){ println("tender exists"); return};

        def complexUserID = ["[supervisor]", "[observer]"] as Set;
        def appl = cdb.getAllDocsByReader("parentdocid#number = ${pdoc.getDocID()} and form='rentapplication' and status='approved'", complexUserID, "[supervisor]")

        if(appl.size() >= 2){
            // открываем тендер
            def tender = new _Document(cdb);
            def emails = [];
            for(def it in appl){
                def emp = srtuct.getEmployer(it.getValueString("leasingholder_id"));
                if(emp.getEmail() != "" )  emails.add(emp.getEmail());
                tender.addReader(emp.getUserID());
            }

            tender.setForm("tender")
            tender.setParentDoc(pdoc);
            tender.setValueString("object_description", pdoc.getViewText());
            tender.setViewText("Тендер на объект: " + pdoc.getViewText())
            tender.addViewText("active") // status тендера  активный 0 - закрытый
            tender.addViewText(pdoc.getValueString("rent_cost"))
            tender.addViewText(ses.getDatePlusDays(15).format("dd.MM.yyyy"));
            tender.setViewDate(new Date());

            tender.setAuthor("system");

            tender.addReader("[rent_viewer]");
            tender.addReader("[tender_reader]");
            tender.addEditor("[tender_editor]");
            tender.save("[supervisor]");

            pdoc.setViewText("tender", 5);
            pdoc.addStringField("tender", tender.getID());
            pdoc.save("[supervisor]");
            println("tender created");

            // дублируем их в заявку в тендер
            // ? надо добавить уведомление

            for(def it in appl){
                def tenderApp = new _Document(cdb);
                tenderApp.setForm("tenderapplication");
                tenderApp.addDateField("startdate", it.getValueDate("startdate"));
                tenderApp.addDateField("enddate", it.getValueDate("enddate"));
                tenderApp.addStringField("leasingholder_id", it.getValueString("leasingholder_id"));
                tenderApp.addStringField("cost", pdoc.getValueString("rent_cost"));
                // ? tenderApp.addFile("rtfcontent")
                def regNum  = cdb.getRegNumber("tenderapplication");
                tenderApp.addNumberField("regNum", regNum)
                tenderApp.addStringField("status", "approved");
                tenderApp.setViewNumber(regNum);

                tenderApp.setViewText(" Заявление №$regNum от ${new Date().format("dd.MM.yyyy")} на участие в тендере: " +  pdoc.getViewText() + " заявитель: " + it.doc.getViewTextList()[1])
                tenderApp.addViewText(it.doc.getViewTextList()[1]);
                tenderApp.addViewText("approved");
                tenderApp.setViewDate(new Date())
                tenderApp.setParentDoc(tender);

                tenderApp.setAuthor("system");
                tenderApp.addReader("[tender_reader]");
                tenderApp.addEditor(it.getValueString("leasingholder_id"));
                tenderApp.save("[supervisor]");
            }

            // уведомление
            String body = "<b>Извещение о проведении тендера</b> на аренду объекта: ${pdoc.getViewText()}" +
                    "<table><tr><td>Краткая характеристика объекта тендера</td><td>${pdoc.getValueString("description")}</td></tr>" +
                    "<tr><td>Сроки принятия заявки на участие в тендере до</td><td>${ses.getDatePlusDays(15).format("dd.MM.yyyy")}</td></tr>"
            "</table>";
            def memo = new _Memo("Извещение о проведении тендера","Извещение о проведении тендера", body, tender, false);
            def ma = ses.getMailAgent();
            ma.sendMail(emails,memo)
        }
        setRedirectURL(ses.getURLOfLastPage());
	}
}