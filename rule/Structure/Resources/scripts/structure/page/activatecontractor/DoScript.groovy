package structure.page.activatecontractor

import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScript
import kz.nextbase.script.mail._Memo
import kz.nextbase.script.struct._Employer
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

        def emp = structure.getUser(formData.getValue("userid"))

        emp.setStatus(_EmployerStatusType.HIRED);
        emp.clearEnabledAppsList();
        emp.addEnabledApp(new _UserApplicationProfile("Accountant","0"));
        emp.addEnabledApp(new _UserApplicationProfile("MonitoringSubsystem","0"));
        emp.addEnabledApp(new _UserApplicationProfile("RegistrySubsystem","0"));
        emp.setListOfRoles(["contractor#Accountant"] as String[]);
        emp.setListOfRoles(["contractor#RegistrySubsystem"] as String[]);

        emp.setViewText("active",2)
        emp.save()
        setRedirectURL(session.getURLOfLastPage())

        try{
            def org = emp.getObl()
            def doc = cdb.getDocumentByID(org);
            doc.addEditor(emp.getUserID());
            doc.save("[supervisor]");
        }catch(Exception e){
            log("не удалось открыть доступ на редактирование учреждения id=${emp.getObl()} для userid=${emp.getUserID()}")
        }
        try{

            def recipients = emp.getEmail()

            def url = ses.getCurrentHost()
            def body = "Ваши учетные данные были подтверждены администратором, перейдите по <a href='$url'>ссылке</a> для работы с системой.";
            def memo = new _Memo("Уведомление", "Уведомление о подтверждении регистрации ответственного лица по загрузке объектов", body, null, false);
            ma.sendMail(recipients, memo);
        }catch (Exception e){}
    }
}
