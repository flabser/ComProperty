package form.leasing_tenderapplication

import kz.nextbase.script._Document
import kz.nextbase.script._Exception
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.actions._Action
import kz.nextbase.script.actions._ActionBar
import kz.nextbase.script.actions._ActionType
import kz.nextbase.script.events._FormQueryOpen

class QueryOpen extends _FormQueryOpen {

	
	@Override
	public void doQueryOpen(_Session ses, _WebFormData webFormData, String lang) {
		def user = ses.getCurrentAppUser()
		
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = ses.createActionBar();
		actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)

		publishValue("title",getLocalizedWord("Заявление на аренду", lang))
		publishEmployer("author", ses.getCurrentAppUser().getUserID())

		def pdoc = ses.currentDatabase.getDocumentByID(webFormData.getValue("parentdocid").toInteger());
		publishValue("objectname",pdoc.getViewText())
		publishValue("objecturl",pdoc.getURL())
		publishValue("totalarea",pdoc.getValueString("totalarea"))
        def address = pdoc.getValueString("address") /* "обл. " + pdoc.getGlossaryValue(" docid#number=${pdoc.getValueGlossary("region")[0]}", "name") +
                " г. " + pdoc.getGlossaryValue(" docid#number=${pdoc.getValueGlossary("city")[0]}", "name") +
                " р. " + pdoc.getGlossaryValue(" docid#number=${pdoc.getValueGlossary("district")[0]}", "name") +
                " ул. "  + pdoc.getValueGlossary("street")[0] +
                " д. " + pdoc.getValueString("home");   */

        publishValue("address", address);

        // арендатор
        def currentEmp = ses.getCurrentAppUser();
        publishValue("leasingholder_id", currentEmp.getUserID());
        publishValue("leasingholder", currentEmp.getShortName())
        publishValue("leasingholder_head", currentEmp.getFullName())
        publishValue("leasingholder_bin", currentEmp.getViewNumber());
        publishValue("leasingholder_phone", currentEmp.getPhone());
        publishValue("leasingholder_regdate", currentEmp.getBirthDate().format("dd.MM.yyyy"));

	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		publishValue("title",getLocalizedWord("Заявление на аренду", lang) + " ")
		def user = ses.getCurrentAppUser()
        def str = ses.getStructure();
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = new _ActionBar(ses)
        if(doc.getValueString("status") == "new" && user.hasRole("rent_subsystem_administrator")) {
            actionBar.addAction(new _Action(getLocalizedWord("Подтвердить",lang),getLocalizedWord("Подтвердить",lang), "approve"))
            actionBar.addAction(new _Action(getLocalizedWord("Отклонить",lang),getLocalizedWord("Отклонить",lang), "reject"))
        }
        //if(doc.getEditMode() == _DocumentModeType.EDIT){
            //actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
       // }
        actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
        publishElement(actionBar)

        def leasingholder = str.getEmployer(doc.getValueString("leasingholder_id")) ;
        publishValue("leasingholder_id", leasingholder.getUserID());
        publishValue("leasingholder", leasingholder.getShortName())
        publishValue("leasingholder_head", leasingholder.getFullName())
        publishValue("leasingholder_bin", leasingholder.getViewNumber());
        publishValue("leasingholder_phone", leasingholder.getPhone());
        publishValue("leasingholder_regdate", leasingholder.getBirthDate().format("dd.MM.yyyy"));

		publishValue("startdate", doc.getValueDate("startdate").format("dd.MM.yyyy"))
		publishValue("enddate", doc.getValueDate("startdate").format("dd.MM.yyyy"))

        def tender = doc.getParentDocument()
        publishValue("tendername",tender.getViewText())
        publishValue("tenderurl",tender.getURL())

        def pdoc = doc.getParentDocument().getParentDocument();
        publishValue("objectname",pdoc.getViewText())
        publishValue("objecturl",pdoc.getURL())
        publishValue("totalarea",pdoc.getValueString("totalarea"))
        def address =  pdoc.getValueString("address") /* "обл. " + pdoc.getGlossaryValue(" docid#number=${pdoc.getValueGlossary("region")[0]}", "name") +
                " г. " + pdoc.getGlossaryValue(" docid#number=${pdoc.getValueGlossary("city")[0]}", "name") +
                " р. " + pdoc.getGlossaryValue(" docid#number=${pdoc.getValueGlossary("district")[0]}", "name") +
                " ул. "  + pdoc.getValueGlossary("street")[0] +
                " д. " + pdoc.getValueString("home");  */

        publishValue("address", address);
		publishValue("status", doc.getValueString("status"))
//        publishValue("cost", doc.getValueString("cost"));
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}