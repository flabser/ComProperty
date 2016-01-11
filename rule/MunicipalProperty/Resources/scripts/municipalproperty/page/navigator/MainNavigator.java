package municipalproperty.page.navigator;

import java.util.ArrayList;
import java.util.List;

import kz.nextbase.script._IXMLContent;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;

/**
 * 
 * 
 * @author Kayra created 24-12-2015
 */

public class MainNavigator extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		List<_IXMLContent> list = new ArrayList<_IXMLContent>();

		_Outline munPropOutline = new _Outline(getLocalizedWord("municipal_property", lang), "municipal_property");

		// if(cuser.hasRole(["struct_keeper", "supervisor"])) {
		_OutlineEntry personalEstateEntry = new _OutlineEntry(getLocalizedWord("personal_estates", lang), "personal_estate_view");
		personalEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("furnitures", lang), "furniture_view"));
		personalEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("animals", lang), "animal_view"));
		personalEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("inventory", lang), "inventory_view"));
		personalEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("others", lang), "other_view"));

		_OutlineEntry intangibleAssetsEntry = new _OutlineEntry(getLocalizedWord("intangible_assets", lang), "intangible_asset_view");
		intangibleAssetsEntry.addEntry(new _OutlineEntry(getLocalizedWord("share_blocks", lang), "share_block_view"));
		intangibleAssetsEntry.addEntry(new _OutlineEntry(getLocalizedWord("equities", lang), "equity_view"));

		_OutlineEntry equipmentEntry = new _OutlineEntry(getLocalizedWord("equipment", lang), "equipment_view");
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("school_equipment", lang), "school_equipment_view"));
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("office_equipment", lang), "office_equipment_view"));
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("computer_equipment", lang), "computer_equipment_view"));
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("medical_equipment", lang), "medical_equipment_view"));
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("cook_equipment", lang), "cook_equipment_view"));
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("equipment_of_civildefence", lang), "equipment_of_civildefence_view"));
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("others", lang), "other_equipment_view"));

		_OutlineEntry realEstateEntry = new _OutlineEntry(getLocalizedWord("real_estate", lang), "real_estate_view");
		realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("buildings", lang), "building_view"));
		realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("structures", lang), "structure_view"));
		realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("residential_objects", lang), "residential_object_view"));
		realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("land", lang), "land_view"));
		realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("monuments", lang), "monument_view"));

		_OutlineEntry transportEntry = new _OutlineEntry(getLocalizedWord("transport", lang), "transport_view");
		_OutlineEntry autoEntry = new _OutlineEntry(getLocalizedWord("autmobiles", lang), "autmobile_view");
		transportEntry.addEntry(autoEntry);
		autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("cars", lang), "car_view"));
		autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("cargo", lang), "cargo_view"));
		autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("dej_transport", lang), "dej_transport_view"));
		autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("official_transport", lang), "official_transport_view"));
		_OutlineEntry passengerTransportEntry = new _OutlineEntry(getLocalizedWord("passenger_transport", lang), "passenger_transport_view");
		passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("buses", lang), "bus_view"));
		passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("trolleybuses", lang), "trolleybus_view"));
		passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("trams", lang), "tram_view"));
		passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("water_transports", lang), "water_transport_view"));
		passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("taxi", lang), "taxi_view"));
		autoEntry.addEntry(passengerTransportEntry);
		transportEntry.addEntry(new _OutlineEntry(getLocalizedWord("special_equipment", lang), "special_equipment_view"));
		transportEntry.addEntry(new _OutlineEntry(getLocalizedWord("motorcycles", lang), "motorcycle_view"));

		_OutlineEntry strategicObjectEntry = new _OutlineEntry(getLocalizedWord("strategic_objects", lang), "strategicobject_view");
		strategicObjectEntry.addEntry(new _OutlineEntry(getLocalizedWord("object_reserved_fund", lang), "object_reserved_fund_view"));
		_OutlineEntry specialConstructionEntry = new _OutlineEntry(getLocalizedWord("special_constructions", lang), "special_construction_view");
		specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("bombproofs", lang), "bombproof_view"));
		specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("factory", lang), "factory_view"));
		specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("combines", lang), "combine_view"));
		specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("airports", lang), "airport_view"));
		specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("transitions", lang), "transition_view"));
		strategicObjectEntry.addEntry(specialConstructionEntry);

		_OutlineEntry engStructureEntry = new _OutlineEntry(getLocalizedWord("eng_infrastructure", lang), "eng_infrastructure_view");
		engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("billboards", lang), "billboard_view"));
		engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("columns", lang), "column_view"));
		_OutlineEntry networkEntry = new _OutlineEntry(getLocalizedWord("networks", lang), "network_view");
		engStructureEntry.addEntry(networkEntry);
		networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("electric_networks", lang), "electric_network_view"));
		networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("thermal_networks", lang), "thermal_network_view"));
		networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("gas", lang), "gas_view"));
		networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("water_systems", lang), "water_system_view"));
		networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("drain", lang), "drain_view"));
		engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("roads", lang), "road_view"));
		engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("parkings", lang), "parking_view"));

		munPropOutline.addEntry(personalEstateEntry);
		munPropOutline.addEntry(intangibleAssetsEntry);
		munPropOutline.addEntry(equipmentEntry);
		munPropOutline.addEntry(realEstateEntry);
		munPropOutline.addEntry(transportEntry);
		munPropOutline.addEntry(strategicObjectEntry);
		munPropOutline.addEntry(engStructureEntry);

		_Outline reportOutline = new _Outline(getLocalizedWord("reports", lang), "report");
		reportOutline.addEntry(new _OutlineEntry(getLocalizedWord("reports", lang), "report_template_view"));

		list.add(munPropOutline);
		list.add(reportOutline);

		setContent(list);

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
