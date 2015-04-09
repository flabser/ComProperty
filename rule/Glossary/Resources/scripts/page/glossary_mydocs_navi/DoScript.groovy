package page.glossary_mydocs_navi

import java.util.Map;
import kz.nextbase.script.*
import kz.nextbase.script.events._DoScript
import kz.nextbase.script.outline.*
import kz.nextbase.script.constants.*

class DoScript extends _DoScript {
	
	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		//println(lang)
		def db = session.getCurrentDatabase()		
		def outline = new _Outline(getLocalizedWord("Мои документы",lang), getLocalizedWord("Мои документы",lang), "mydocs")
		
		def e1 = new _OutlineEntry(getLocalizedWord("На рассмотрение",lang), getLocalizedWord("На рассмотрение",lang), "toconsider", "Provider?type=page&id=toconsider&page=0")
		e1.setValue(db.getCount(_QueryMacroType.TO_CONSIDER))
		outline.addEntry(e1)
		
		def e2 = new _OutlineEntry(getLocalizedWord("Поручено мне",lang), getLocalizedWord("Поручено мне",lang), "taskforme", "Provider?type=page&id=taskforme&page=0")
		e2.setValue(db.getCount(_QueryMacroType.TASKS_FOR_ME))
		outline.addEntry(e2)
		
		def e3 = new _OutlineEntry(getLocalizedWord("Мои задания",lang), getLocalizedWord("Мои задания",lang), "mytasks", "Provider?type=page&id=mytasks&page=0")
		e3.setValue(db.getCount(_QueryMacroType.MY_TASKS))
		outline.addEntry(e3)
		
		def e4 = new _OutlineEntry(getLocalizedWord("�?сполненные",lang), getLocalizedWord("�?сполненные",lang), "completetask", "Provider?type=page&id=completetask&page=0")
		e4.setValue(db.getCount(_QueryMacroType.COMPLETE_TASKS))
		outline.addEntry(e4)
		
		def e5 = new _OutlineEntry(getLocalizedWord("На согласование",lang), getLocalizedWord("На согласование",lang), "waitforcoord", "Provider?type=page&id=waitforcoord&page=0")
		e5.setValue(db.getCount(_QueryMacroType.WAIT_FOR_COORD))
		outline.addEntry(e5)
		
		def e6 = new _OutlineEntry(getLocalizedWord("На подпись",lang), getLocalizedWord("На подпись",lang), "waitforsign", "Provider?type=page&id=waitforsign&page=0")
		e6.setValue(db.getCount(_QueryMacroType.WAIT_FOR_SIGN))
		outline.addEntry(e6)
		
		def e7 = new _OutlineEntry(getLocalizedWord("�?збранные",lang), getLocalizedWord("�?збранные",lang), "favdocs", "Provider?type=page&id=favdocs&page=0")
		e7.setValue(db.getCount(_QueryMacroType.FAVOURITES))
		outline.addEntry(e7)
		
		setContent(outline)
	}
	

}
