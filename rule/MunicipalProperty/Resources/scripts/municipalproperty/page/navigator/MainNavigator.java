package municipalproperty.page.navigator;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.webserver.servlet.IOutcomeObject;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kayra created 24-12-2015
 */

public class MainNavigator extends _DoPage {

    @Override
    public void doGET(_Session session, _WebFormData formData) {
        LanguageCode lang = session.getLang();
        List<IOutcomeObject> list = new ArrayList<IOutcomeObject>();

        _Outline munPropOutline = new _Outline(getLocalizedWord("municipal_property", lang), "municipal_property");

        // if(cuser.hasRole(["struct_keeper", "supervisor"])) {
        _OutlineEntry personalEstateEntry = new _OutlineEntry(getLocalizedWord("personal_estates", lang), "personalestate-view");
        personalEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("furnitures", lang), "furniture-view"));
        personalEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("animals", lang), "animals-view"));
        personalEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("inventory", lang), "sport-equipment-view"));
        personalEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("others", lang), "others-view"));

        _OutlineEntry intangibleAssetsEntry = new _OutlineEntry(getLocalizedWord("intangible_assets", lang), "intangibleasset-view");
        intangibleAssetsEntry.addEntry(new _OutlineEntry(getLocalizedWord("share_blocks", lang), "share-block-view"));
        intangibleAssetsEntry.addEntry(new _OutlineEntry(getLocalizedWord("equities", lang), "equity-view"));

        _OutlineEntry equipmentEntry = new _OutlineEntry(getLocalizedWord("equipment", lang), "equipment-view");
        equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("school_equipment", lang), "school-equipment-view"));
        equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("office_equipment", lang), "office-equipment-view"));
        equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("computer_equipment", lang), "computer-equipment-view"));
        equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("medical_equipment", lang), "medical-equipment-view"));
        equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("cook_equipment", lang), "cook-equipment-view"));
        equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("equipment_of_civildefence", lang), "equipment-of-civil-defence-view"));
        equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("others", lang), "others-equipment-view"));

        _OutlineEntry realEstateEntry = new _OutlineEntry(getLocalizedWord("real_estates", lang), "realestate-view");
        realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("buildings", lang), "buildings-view"));
        realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("structures", lang), "structures-view"));
        realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("residential_objects", lang), "residential-objects-view"));
        realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("land", lang), "land-view"));
        realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("monuments", lang), "monument-view"));

        _OutlineEntry transportEntry = new _OutlineEntry(getLocalizedWord("transport", lang), "vehicle-view");
        _OutlineEntry autoEntry = new _OutlineEntry(getLocalizedWord("automobiles", lang), "automobile-view");
        transportEntry.addEntry(autoEntry);
        autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("cars", lang), "car-view"));
        autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("cargo", lang), "cargo-view"));
        autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("dej_transport", lang), "dej-transport-view"));
        autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("official_transport", lang), "official-transport-view"));
        _OutlineEntry passengerTransportEntry = new _OutlineEntry(getLocalizedWord("passenger_transport", lang), "passenger-transport-view");
        passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("buses", lang), "bus-view"));
        passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("trolleybuses", lang), "trolleybus-view"));
        passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("trams", lang), "tram-view"));
        passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("water_transports", lang), "water-transport-view"));
        passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("taxi", lang), "taxi-view"));
        autoEntry.addEntry(passengerTransportEntry);
        transportEntry.addEntry(new _OutlineEntry(getLocalizedWord("special_equipment", lang), "special-equipment-view"));
        transportEntry.addEntry(new _OutlineEntry(getLocalizedWord("motorcycles", lang), "motorcycle-view"));

        _OutlineEntry strategicObjectEntry = new _OutlineEntry(getLocalizedWord("strategic_objects", lang), "strategicobject-view");
        strategicObjectEntry.addEntry(new _OutlineEntry(getLocalizedWord("object_reserved_fund", lang), "object-reserved-fund-view"));
        _OutlineEntry specialConstructionEntry = new _OutlineEntry(getLocalizedWord("special_constructions", lang), "special-construction-view");
        specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("bombproofs", lang), "bombproof-view"));
        specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("factory", lang), "factory-view"));
        specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("combines", lang), "combines-view"));
        specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("airports", lang), "airport-view"));
        specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("transitions", lang), "transitions-view"));
        strategicObjectEntry.addEntry(specialConstructionEntry);

        _OutlineEntry engStructureEntry = new _OutlineEntry(getLocalizedWord("eng_infrastructure", lang), "engineeringinfrastructure-view");
        engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("billboards", lang), "billboard-view"));
        engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("columns", lang), "columns-view"));
        _OutlineEntry networkEntry = new _OutlineEntry(getLocalizedWord("networks", lang), "network-view");
        engStructureEntry.addEntry(networkEntry);
        networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("electric_networks", lang), "electric-networks-view"));
        networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("thermal_networks", lang), "thermal-networks-view"));
        networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("gas", lang), "gas-view"));
        networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("water_systems", lang), "water-system-view"));
        networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("drain", lang), "drain-view"));
        engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("roads", lang), "road-view"));
        engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("parkings", lang), "parking-view"));

        munPropOutline.addEntry(personalEstateEntry);
        munPropOutline.addEntry(intangibleAssetsEntry);
        munPropOutline.addEntry(equipmentEntry);
        munPropOutline.addEntry(realEstateEntry);
        munPropOutline.addEntry(transportEntry);
        munPropOutline.addEntry(strategicObjectEntry);
        munPropOutline.addEntry(engStructureEntry);

        _Outline orderOutline = new _Outline(getLocalizedWord("orders", lang), "order");
        orderOutline.addEntry(new _OutlineEntry(getLocalizedWord("orders", lang), "order-view"));

        _Outline reportOutline = new _Outline(getLocalizedWord("reports", lang), "report");
        reportOutline.addEntry(new _OutlineEntry(getLocalizedWord("reports", lang), "report-template-view"));

        list.add(reportOutline);
        list.add(munPropOutline);
        list.add(orderOutline);

        addContent("outline_current", formData.getValueSilently("id").replace("-form", "-view"));
        addContent(list);
    }
}
