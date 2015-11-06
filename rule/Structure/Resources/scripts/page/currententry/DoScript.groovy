package page.currententry

import kz.nextbase.script._Session
import kz.nextbase.script._Tag
import kz.nextbase.script._WebFormData
import kz.nextbase.script._XMLDocument
import kz.nextbase.script.events._DoScript

class DoScript extends _DoScript {

	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		def rootTag = new _Tag()
		def entryTag = new _Tag("entry",getLocalizedWord(formData.getEncodedValueSilently("title"), lang))
		entryTag.setAttr("entryid",formData.getValueSilently("entryid"))
		def id = formData.getValueSilently("id");
		if( formData.containsField("status")){
			id += "_" + formData.getValue("status")
			def customParam = new _Tag("customparam","&status="+formData.getValue("status"))
			rootTag.addTag(customParam)
		}
        entryTag.setAttr("id",id)
		rootTag.addTag(entryTag)
		def xml = new _XMLDocument(rootTag)
		setContent(xml);
	}
}




