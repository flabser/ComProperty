package tender.form.contract

import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.constants._TaskType
import kz.nextbase.script.events._FormPostSave
import kz.nextbase.script.mail._Memo
import kz.nextbase.script.task._Task

class PostSave extends _FormPostSave {

	public void doPostSave(_Session ses, _Document doc) {	

		def parentdoc = doc.getParentDocument()
		parentdoc.setValueString("contractdate", doc.getValueString("regdate"))
		parentdoc.save("[supervisor]")
	}
}