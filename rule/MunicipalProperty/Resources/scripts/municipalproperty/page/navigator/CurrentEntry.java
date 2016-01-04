package municipalproperty.page.navigator;

import kz.nextbase.script._Session;
import kz.nextbase.script._Tag;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script._XMLDocument;
import kz.nextbase.script.events._DoPage;

/**
 * 
 * 
 * @author Kayra created 24-12-2015
 */
public class CurrentEntry extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		_Tag rootTag = new _Tag();
		_Tag entryTag = new _Tag("entry", getLocalizedWord(formData.getEncodedValueSilently("title"), lang));
		entryTag.setAttr("entryid", formData.getValueSilently("entryid"));
		entryTag.setAttr("id", formData.getValueSilently("id"));
		if (formData.getEncodedValueSilently("id") == "search") {
			_Tag searchTag = new _Tag("search", formData.getEncodedValueSilently("keyword"));
			searchTag.setAttr("search", formData.getEncodedValueSilently("keyword"));
			rootTag.addTag(searchTag);
			_Tag customParam = new _Tag("customparam", "&keyword=" + formData.getEncodedValueSilently("keyword"));
			rootTag.addTag(customParam);
		}
		rootTag.addTag(entryTag);
		_XMLDocument xml = new _XMLDocument(rootTag);
		setContent(xml);

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
