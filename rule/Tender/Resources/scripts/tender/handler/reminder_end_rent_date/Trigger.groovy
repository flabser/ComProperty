package tender.handler.reminder_end_rent_date
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
            def coll = db.getCollectionOfDocuments("form='orderleasing' and dbd#number <= 5 and sa = '1'", false).getEntries()
            def ma = session.getMailAgent()
            def doc = null;
            def pdoc = null;
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
                    org_docid = doc.getValueNumber("leasingholder");
                    org_doc = db.getDocumentByComplexID(896, org_docid);
                    org_email = org_doc.getValueString("email");
                    org_name = org_doc.getValueString("orgfullname");
                    ruk_fio = org_doc.getValueString("rukfullname");

                    pdoc = doc.getParentDocument();
                    if (pdoc) {
                        holder_docid = pdoc.getValueNumber("balanceholder");
                        holder_doc = db.getDocumentByComplexID(896, holder_docid);
                        holder_email = holder_doc.getValueString("email");
                    }

                    if (!"".equalsIgnoreCase(org_email)) {
                        String content = ((_Document) doc).getValueString("content");
                        String body_mail = "Уважаемый " + ruk_fio + " <br>" +
                                " Настоящим уведомлением ставим Вас в известность, что " + ((_Document) doc).getValueString("endrentdate") + " истекает срок , в течение  которого  необходимо внести арендную плату за объект, согласно договора №" + ((_Document) doc).getValueString("regnumber") + ", " + (!"".equalsIgnoreCase(content) ? content + ", " : "") + " заключенного между  ГУ «Управление финансов г.Алматы» и Вами " + ((_Document) doc).getValueString("regdate") + ". <br>" +
                                "При нарушении срока внесения арендной платы, Вам будет начислена пеня в размере 0,5% от неуплаченной суммы задолженности за каждый день просрочки.<br>" +
                                "<br>" +
                                "По возникшим вопросам обращаться в отдел Имущественного найма ГУ «Управление финансов г.Алматы»";
                        if (recipients_data.containsKey(org_email)) {
                            String full_mail_body = recipients_data.get(org_email) + body_mail;
                            recipients_data.put(org_email, full_mail_body);
                        } else {
                            recipients_data.put(org_email, body_mail);
                        }
                    }
                    if (org_email != holder_email && !"".equalsIgnoreCase(holder_email)) {
                        String content = ((_Document) doc).getValueString("content");
                        String body_mail = "Истекают сроки внесения арендной платы по Договору №" + ((_Document)doc).getValueString("regnumber") + "," + (!"".equalsIgnoreCase(content) ? content + ", " : "") + " заключенного между ГУ «Управление финансов г.Алматы» и " + org_name + ". До истечения срока осталось " + doc.getValueNumber("dbd") + ". " + "<br>"
                        if (recipients_data.containsKey(holder_email)) {
                            String full_mail_body = recipients_data.get(holder_email) + body_mail;
                            recipients_data.put(holder_email, full_mail_body);
                        } else {
                            recipients_data.put(holder_email, body_mail);
                        }
                    }
                    org_email = "";
                    holder_email = "";
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