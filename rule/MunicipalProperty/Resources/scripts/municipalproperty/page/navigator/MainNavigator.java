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
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("equipment_of_civildefense", lang), "equipment_of_civildefense_view"));
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("other_equipment", lang), "other_equipment_view"));
		munPropOutline.addEntry(personalEstateEntry);
		munPropOutline.addEntry(intangibleAssetsEntry);
		munPropOutline.addEntry(equipmentEntry);
		_Outline objPropTypeOutline = new _Outline(getLocalizedWord("obj_property_type", lang), "obj_property_type");
		objPropTypeOutline.addEntry(new _OutlineEntry(getLocalizedWord("propertycodes", lang), "propertycode_view"));
		objPropTypeOutline.addEntry(new _OutlineEntry(getLocalizedWord("structure_types", lang), "structure_type_view"));
		objPropTypeOutline.addEntry(new _OutlineEntry(getLocalizedWord("building_materials", lang), "building_material_view"));

		list.add(munPropOutline);
		list.add(objPropTypeOutline);

		setContent(list);

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
