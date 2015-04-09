package form.reg_naturalperson

import kz.nextbase.script.*
import kz.nextbase.script.events._FormPostSave
import kz.nextbase.script.events._FormQuerySave
import kz.nextbase.script.struct._Employer
import kz.nextbase.script.struct._EmployerStatusType
import kz.nextbase.script.struct._UserApplicationProfile

class PostSave extends _FormPostSave {


    @Override
    void doPostSave(_Session ses, _Document doc) {
        //def emp  = (_Employer)doc;

        def g = ses.getStructure().getGroup(ses, "[rent_viewer]", ["[supervisor]"] as Set, "[supervisor]");
        def l = g.getListOfMembers();
        def emp = ses.getStructure().getUserByCondition("empid=${doc.getDocID()}")
        l.add(emp.getUserID());
        g.setListOfMembers(l as String[]);
        g.save("[supervisor]");

    }
}
