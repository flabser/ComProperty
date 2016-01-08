package reference.page.navigator;

import kz.nextbase.script.*;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;

import java.util.ArrayList;
import java.util.List;

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
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("countries", lang), "countries"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("regions", lang), "regions"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("districts", lang), "districts"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("localities", lang), "localities"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("streets", lang), "streets"));
        common_outline.addEntry(new _OutlineEntry(getLocalizedWord("positions", lang), "positions"));

        _Outline specific_outline = new _Outline(getLocalizedWord("specific_reference_data", lang), "specific");
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("property_codes", lang), "property-codes"));
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("receiving_reasons", lang), "receiving-reasons"));
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("structure_types", lang), "structure-types"));
        specific_outline.addEntry(new _OutlineEntry(getLocalizedWord("building_materials", lang), "building-materials"));

        list.add(common_outline);
        list.add(specific_outline);

        setContent(new _XMLDocument(currentTag));
        setContent(list);
    }

    @Override
    public void doPOST(_Session session, _WebFormData formData, String lang) {

    }
}
