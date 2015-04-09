package form.userprofile

import java.util.Map
import kz.nextbase.script.*
import kz.nextbase.script.actions.*
import kz.nextbase.script.events.*;
import kz.nextbase.script.constants.*

class QueryOpenStructure extends _FormQueryOpen {

	
	@Override
	public void doQueryOpen(_Session session, _WebFormData webFormData, String lang) {
		
		def user = session.getCurrentAppUser()
		
		def nav = session.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = session.createActionBar();
		actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)

		
		publishValue("post", session.getCurrentAppUser().getPost())
		publishValue("department",  session.getCurrentAppUser().getDepartment())
		publishValue("userid",  session.getCurrentAppUser().getUserID())
		publishValue("countdocinview", session.getCurrentAppUser().getCountDocInView())
		publishValue("lang",lang)
		publishValue("skin", session.getCurrentAppUser().getSkin())
		publishValue("email", session.getCurrentAppUser().getEmail())
		publishValue("shortname", session.getCurrentAppUser().getShortName())
		
	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		def emp = ses.getCurrentAppUser()
		
		//def nav = ses.getPage("outline", webFormData)
		//publishElement(nav)
		
		def actionBar = new _ActionBar(ses)
		
		if(doc.getEditMode() == _DocumentModeType.EDIT){
			actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		}
		
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)
		
		try{
			publishValue("title",getLocalizedWord("Сотрудник ", lang))
			publishValue("post", emp.getPostID(), emp.getPost())  
			def dep = emp.getMainDepartment();
			publishValue("department", dep.getViewText())
			publishValue("userid",emp.getValueString("userid"))
			publishValue("countdocinview",emp.getValueString("countdocinview"))
			publishValue("lang",emp.getValueString("lang"))
			publishEmployer("skin",doc.getValueString("skin"))
			publishValue("email",doc.getValueString("email"))
			publishValue("shortname",emp.getValueString("shortname"))
			publishEmployer("fullname", emp.getUserID())
			publishValue("instmsgaddress",doc.getValueString("instmsgaddress"))
		
	
		}catch(_Exception e){

		}
	}

}