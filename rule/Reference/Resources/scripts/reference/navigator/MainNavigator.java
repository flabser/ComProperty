package reference.navigator;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.webserver.servlet.IOutcomeObject;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;

public class MainNavigator extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		List<IOutcomeObject> list = new ArrayList<IOutcomeObject>();

		_Outline common_outline = new _Outline(getLocalizedWord("common_reference_data", lang), "common");

		// if(cuser.hasRole(["struct_keeper", "supervisor"])) {
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("countries", lang), "country-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("regions", lang), "region-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("districts", lang), "district-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("localities", lang), "locality-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("streets", lang), "street-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("org_categories", lang), "orgcategory-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("positions", lang), "position-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("tags", lang), "tag-view"));

		_Outline specific_outline = new _Outline(getLocalizedWord("specific_reference_data", lang), "specific");
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("kuf", lang), "kuf-view"));
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("property_codes", lang), "propertycode-view"));
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("receiving_reason", lang), "receivingreason-view"));
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("structure_type", lang), "structuretype-view"));
		specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("building_materials", lang), "buildingmaterial-view"));

		list.add(common_outline);
		list.add(specific_outline);

		addContent("outline_current", formData.getValueSilently("id").replace("-form", "-view"));
		addContent(list);

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageCode lang) {

	}

}
