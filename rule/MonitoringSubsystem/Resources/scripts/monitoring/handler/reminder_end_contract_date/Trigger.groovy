package monitoring.handler.reminder_end_contract_date
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.events._DoScheduledHandler
import kz.nextbase.script.mail._Memo

class Trigger extends _DoScheduledHandler {

    @Override
    public int doHandler(_Session session) {
        try {
            def recipients_data = [:];
            def db = session.getCurrentDatabase()
            def coll = db.getCollectionOfDocuments("form='contract' and dbd#number <= 10 and status = '1'", false).getEntries()
            def ma = session.getMailAgent()
            def doc = null;
            def pdocs = [];
            int org_docid;
            def org_doc;
            String org_name = "";
            String org_email = "";
            int holder_docid;
            def holder_doc;
            String holder_email = "";

            String ruk_fio = "";


            ArrayList<String> recipients = new ArrayList<>();

            coll.each {
                doc = (_Document) it.getDocument();
                if (doc) {
                    pdocs = doc.getParents(doc.getBaseObject());
                    pdocs.each {
                        if (it.getDocumentForm() == "order") {
                            org_docid = it.getValueNumber("organization");
                            org_doc = db.getDocumentByComplexID(896, org_docid);
                            org_email = org_doc.getValueString("email");
                            org_name = org_doc.getValueString("orgfullname");
                            ruk_fio = org_doc.getValueString("rukfullname");
                        } else {
                            holder_docid = it.getValueNumber("balanceholder");
                            holder_doc = db.getDocumentByComplexID(896, holder_docid);
                            holder_email = holder_doc.getValueString("email");
                        }
                    }
                    if (!"".equalsIgnoreCase(org_email)) {
                        String body_mail = "Уважаемый " + ruk_fio + " <br>" +
                                " Настоящим уведомлением ставим Вас в известность, что " + ((_Document)doc).getValueString("endcontractdate") + " истекает срок  договора №" + ((_Document)doc).getValueString("regnumber") + ", " + ((_Document)doc).getValueString("content") + ", заключенного между  ГУ «Управление финансов г.Алматы» и Вами " + ((_Document)doc).getValueString("regdate") + "." +
                                " Для продления срока Договора (заключения доп. соглашения) необходимо связаться с работниками отдела мониторинга объектов коммунальной собственности. " + "<br>"
                        if (recipients_data.containsKey(org_email)) {
                            String full_mail_body = recipients_data.get(org_email) + body_mail;
                            recipients_data.put(org_email, full_mail_body);
                        } else {
                            recipients_data.put(org_email, body_mail);
                        }
                        org_email = "";
                    }
                    if (org_email != holder_email && !"".equalsIgnoreCase(holder_email)) {
                        String body_mail = "Истекает срок действия договора №" + ((_Document)doc).getValueString("regnumber") + "," + ((_Document)doc).getValueString("content") + ", заключенного между ГУ «Управление финансов г.Алматы» и " + org_name + ". До истечения срока осталось " + doc.getValueNumber("dbd") + ". " + "<br>"
                        if (recipients_data.containsKey(holder_email)) {
                            String full_mail_body = recipients_data.get(holder_email) + body_mail;
                            recipients_data.put(holder_email, full_mail_body);
                        } else {
                            recipients_data.put(holder_email, body_mail);
                        }
                        holder_email = "";
                    }
                }
            }
            for (String r in recipients_data.keySet()) {
                recipients.clear();
                recipients.add(r)
                def memo = new _Memo("Уведомление", "Уведомление о скором истечении срока договора", recipients_data.get(r), doc, false);
                ma.sendMail(recipients, memo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0
    }

}