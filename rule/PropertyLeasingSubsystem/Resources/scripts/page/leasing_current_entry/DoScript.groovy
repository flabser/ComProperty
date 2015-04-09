package page.leasing_current_entry

import java.util.ArrayList;
import java.util.Map;
import kz.nextbase.script.*;
import kz.nextbase.script.events._DoScript

class DoScript extends _DoScript {

	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		def rootTag = new _Tag()
		def entryTag = new _Tag("entry",getLocalizedWord(formData.getEncodedValueSilently("title"), lang))
		entryTag.setAttr("entryid",formData.getValueSilently("entryid"))
		entryTag.setAttr("id",formData.getValueSilently("id"))
		if( formData.getEncodedValueSilently("id") == "search"){
			def searchTag = new _Tag("search",formData.getEncodedValueSilently("keyword"));
			searchTag.setAttr("search",formData.getEncodedValueSilently("keyword"))
			rootTag.addTag(searchTag)
			def customParam = new _Tag("customparam","&keyword="+formData.getEncodedValueSilently("keyword"))
			rootTag.addTag(customParam)
		}
        if(formData.containsField("status")){
            entryTag.setAttr("id", formData.getValueSilently("id") + "_"+ formData.getValue("status"))
        }
		rootTag.addTag(entryTag)
		def xml = new _XMLDocument(rootTag)
		setContent(xml);
	}
}




