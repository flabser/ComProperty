package staff.form.responsibleperson

import kz.nextbase.script.*
import kz.nextbase.script.actions.*
import kz.nextbase.script.constants.*
import kz.nextbase.script.events.*
import kz.nextbase.script.struct.*

class QueryOpen extends _FormQueryOpen {


	@Override
	public void doQueryOpen(_Session ses, _WebFormData webFormData, String lang) {
	}

	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		def user = ses.getCurrentAppUser()

		def emp  = (_Employer)doc;
		publishValue("title",getLocalizedWord("Ответственные лица по загрузке объектов", lang) + " - " + emp.getFullName())
		publishValue("form", emp.getDocumentForm());
		publishValue("fullname", emp.getFullName())
		publishValue("userid", emp.getUserID())
		publishValue("email", emp.getEmail())
		publishValue("instmsgaddress", emp.getInstMessengerAddr())
		def org = emp.getOrganization();
		publishValue("organization", org.getShortName())
		def dep = emp.getMainDepartment();
		publishValue("depid", dep.getValueString("shortName"))
		publishValue("role", emp.getListOfRoles())
		if(user.hasRole("supervisor")){
			publishValue("issupervisor", "1")
		}
		publishValue("phone", emp.getPhone())
		publishValue("comment", emp.getComment())
		publishValue("group", emp.getListOfGroups())
		def apps = new HashSet();
		for(def userprof : emp.getEnabledApps()){
			apps.add(userprof.appName);
		}
		publishValue("apps", apps)
		publishValue("post", emp.getPostID(), emp.getPost())
		def birthDate = emp.getBirthDate();
		if (birthDate) {
			def bd = _Helper.getDateAsStringShort(birthDate)
			if (bd) publishValue("birthdate", bd)
		}

		if (emp.getStatus() == _EmployerStatusType.FIRED){
			publishValue("fired", "1")
		}
		try{
			publishAttachment("rtfcontent", "rtfcontent")
		}catch(Exception e){}
		publishValue("institutionname", emp.getShortName());
		publishValue("institution", emp.getObl());


		def actionBar = ses.createActionBar();
		if (user.hasRole(["supervisor", "struct_keeper"]) ){
			//  actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
			if(emp.getStatus() == _EmployerStatusType.FIRED)
				actionBar.addAction(new _Action(getLocalizedWord("Подтвердить",lang),getLocalizedWord("Подтвердить",lang), "activate"))
			actionBar.addAction(new _Action(getLocalizedWord("Отправить уведомления",lang),getLocalizedWord("Подтвердить",lang), "send_notification"))
		}

		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)
	}
}