package reference.page.navigator;

import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.webserver.servlet.IOutcomeObject;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;

import java.util.ArrayList;
import java.util.List;


public class MainNavigator extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        List<IOutcomeObject> list = new ArrayList<IOutcomeObject>();

        _Outline common_outline = new _Outline(getLocalizedWord("common_reference_data", session.getLang()), "common");

        // if(cuser.hasRole(["struct_keeper", "supervisor"])) {
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("countries", session.getLang()), "country-view"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("regions", session.getLang()), "region-view"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("districts", session.getLang()), "district-view"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("localities", session.getLang()), "locality-view"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("city_districts", session.getLang()), "citydistrict-view"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("streets", session.getLang()), "street-view"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("org_categories", session.getLang()), "orgcategory-view"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("region_types", session.getLang()), "regiontype-view"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("locality_types", session.getLang()), "localitytype-view"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("positions", session.getLang()), "position-view"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("tags", session.getLang()), "tag-view"));

        _Outline specific_outline = new _Outline(getLocalizedWord("specific_reference_data", session.getLang()), "specific");
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("kuf", session.getLang()), "kuf-view"));
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("property_codes", session.getLang()), "propertycode-view"));
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("receiving_reason", session.getLang()), "receivingreason-view"));
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("structure_type", session.getLang()), "structuretype-view"));
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("building_materials", session.getLang()), "buildingmaterial-view"));

        list.add(common_outline);
        list.add(specific_outline);

        addContent("outline_current", formData.getValueSilently("id").replace("-form", "-view"));
        addContent(list);

    }
}
