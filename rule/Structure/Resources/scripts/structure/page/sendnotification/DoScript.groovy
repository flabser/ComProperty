package structure.page.sendnotification

import kz.nextbase.script._Session
import kz.nextbase.script._Tag
import kz.nextbase.script._WebFormData
import kz.nextbase.script._XMLDocument
import kz.nextbase.script.events._DoScript
import kz.nextbase.script.mail._Memo
import kz.nextbase.script.struct._EmployerStatusType
import kz.nextbase.script.struct._UserApplicationProfile

/**
 * Created by Bekzat on 2/6/14.
 */
class DoScript extends _DoScript{

    @Override
    void doProcess(_Session session, _WebFormData formData, String lang) {

        def cdb = ses.getCurrentDatabase();
        def ma = ses.getMailAgent();
        def structure = ses.getStructure();

        def emp = structure.getEmployer(formData.getValue("userid"))

        try{
            def recipients = emp.getEmail()
            def url = ses.getCurrentHost()
            def body = formData.getEncodedValueSilently("message") + " <br/> Перейдите по <a href='$url'>ссылке</a> для работы с системой.";
            def memo = new _Memo("Уведомление", "Уведомление ответственному лицу по загрузке объектов", body, null, false);
            ma.sendMail(recipients, memo);
            setContent(new _XMLDocument(new _Tag("result", "ok")))
            log("Уведомление на ${formData.getValue("userid")} отправлено. Текст: " + body)
        }catch (Exception e){
            log("Уведомление на ${formData.getValue("userid")} не отправлено")
            e.printStackTrace()
            setContent(new _XMLDocument(new _Tag("result", "error")))
        }
    }
}
