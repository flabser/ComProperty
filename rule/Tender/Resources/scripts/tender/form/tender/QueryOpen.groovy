package tender.form.tender

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
		//actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
		actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
		publishElement(actionBar)
	}


	@Override
	public void doQueryOpen(_Session ses, _Document doc, _WebFormData webFormData, String lang) {
		publishValue("title", doc.getViewText());
		def user = ses.getCurrentAppUser()
        def str = ses.getStructure();
		def nav = ses.getPage("outline", webFormData)
		publishElement(nav)
		
		def actionBar = new _ActionBar(ses)
        //if(doc.getEditMode() == _DocumentModeType.EDIT){
            //actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть",lang),getLocalizedWord("Сохранить и закрыть",lang),_ActionType.SAVE_AND_CLOSE))
       // }
        actionBar.addAction(new _Action(getLocalizedWord("Закрыть",lang),getLocalizedWord("Закрыть без сохранения",lang),_ActionType.CLOSE))
        publishElement(actionBar)

      	publishValue("regDate", doc.getRegDate().format("dd.MM.yyyy"))
		publishValue("closeDate", doc.doc.getViewTextList()[3])
        publishValue("status", doc.doc.getViewTextList()[1])
        publishValue("rent_cost", doc.doc.getViewTextList()[2])

        def pdoc = doc.getParentDocument();
        publishValue("objectname",pdoc.getViewText())
        publishValue("objecturl",pdoc.getURL())
        publishValue("totalarea",pdoc.getValueString("totalarea"))
        def address =  pdoc.getValueString("address") /* "обл. " + pdoc.getGlossaryValue(" docid#number=${pdoc.getValueGlossary("region")[0]}", "name") +
                " г. " + pdoc.getGlossaryValue(" docid#number=${pdoc.getValueGlossary("city")[0]}", "name") +
                " р. " + pdoc.getGlossaryValue(" docid#number=${pdoc.getValueGlossary("district")[0]}", "name") +
                " ул. "  + pdoc.getValueGlossary("street")[0] +
                " д. " + pdoc.getValueString("home");  */

        publishValue("address", address);
		
		try{
			publishAttachment("rtfcontent","rtfcontent")
		}catch(_Exception e){

		}
	}
}