package reference.page.navigator;

import java.util.ArrayList;
import java.util.List;

import kz.nextbase.script._IXMLContent;
import kz.nextbase.script._Session;
import kz.nextbase.script._Tag;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script._XMLDocument;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;

/**
 * @author Kayra created 24-12-2015
 */

public class MainNavigator extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		List<_IXMLContent> list = new ArrayList<>();

		_Tag currentTag = new _Tag("current");
		currentTag.setAttr("entryid", formData.getValueSilently("entryid"));
		currentTag.setAttr("id", formData.getValueSilently("id"));

		_Outline common_outline = new _Outline(getLocalizedWord("common_reference_data", lang), "common");

		// if(cuser.hasRole(["struct_keeper", "supervisor"])) {
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("countries", lang), "country_view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("regions", lang), "region_view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("districts", lang), "district_view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("localities", lang), "locality_view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("streets", lang), "street_view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("positions", lang), "position_view"));

		_Outline specific_outline = new _Outline(getLocalizedWord("specific_reference_data", lang), "specific");
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("property_codes", lang), "property_code_view"));
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("receiving_reasons", lang), "receiving_reason_view"));
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("structure_types", lang), "structure_type_view"));
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("building_materials", lang), "building_material_view"));

		list.add(common_outline);
		list.add(specific_outline);

		setContent(new _XMLDocument(currentTag));
		setContent(list);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
