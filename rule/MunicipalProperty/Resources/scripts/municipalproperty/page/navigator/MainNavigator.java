package municipalproperty.page.navigator;

import java.util.LinkedList;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.env.Environment;
import com.exponentus.localization.constants.LanguageCode;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._Session;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.scripting.outline._Outline;
import com.exponentus.scripting.outline._OutlineEntry;
import com.exponentus.scriptprocessor.page.IOutcomeObject;
import com.exponentus.server.Server;

import municipalproperty.model.constants.NotificationType;
import reference.dao.PropertyCodeDAO;
import reference.model.PropertyCode;

/**
 * @author Kayra created 24-12-2015
 */

public class MainNavigator extends _DoPage {
	
	@Override
	public void doGET(_Session session, WebFormData formData) {
		LanguageCode lang = session.getLang();
		LinkedList<IOutcomeObject> list = new LinkedList<IOutcomeObject>();
		
		_Outline munPropOutline = new _Outline(getLocalizedWord("municipal_property", lang), "municipal_property");
		
		_OutlineEntry allPropertyEntry = new _OutlineEntry(getLocalizedWord("all_municipal_property", lang),
				"allproperty-view");
		
		// -----
		// if(cuser.hasRole(["struct_keeper", "supervisor"])) {
		String peUrl = "p?id=personalestate-view&kuf=";
		_OutlineEntry personalEstateEntry = new _OutlineEntry(getLocalizedWord("personal_estates", lang),
				"personalestate-view");
		personalEstateEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("furniture", lang), "", "personalestate-view101", peUrl + "101")); // FURNITURE(101)
		personalEstateEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("animals", lang), "", "personalestate-view102", peUrl + "102")); // ANIMALS(102)
		personalEstateEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("inventory", lang), "", "personalestate-view103", peUrl + "103")); // SPORT_EQUIPMENT(103)
		personalEstateEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("others", lang), "", "personalestate-view106", peUrl + "106")); // OTHERS(106)
		
		// -----
		String iaUrl = "p?id=intangibleasset-view&kuf=";
		_OutlineEntry intangibleAssetsEntry = new _OutlineEntry(getLocalizedWord("intangible_assets", lang),
				"intangibleasset-view");
		intangibleAssetsEntry.addEntry(new _OutlineEntry(getLocalizedWord("share_blocks", lang), "",
				"intangibleasset-view104", iaUrl + "104")); // SHARE_BLOCK(104)
		intangibleAssetsEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("equities", lang), "", "intangibleasset-view105", iaUrl + "105")); // EQUITY(105)
		
		// -----
		String eqUrl = "p?id=equipment-view&kuf=";
		_OutlineEntry equipmentEntry = new _OutlineEntry(getLocalizedWord("equipment", lang), "equipment-view");
		equipmentEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("school_equipment", lang), "", "equipment-view201", eqUrl + "201")); // SCHOOL_EQUIPMENT(201)
		equipmentEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("office_equipment", lang), "", "equipment-view202", eqUrl + "202")); // OFFICE_EQUIPMENT(202)
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("computer_equipment", lang), "", "equipment-view203",
				eqUrl + "203")); // COMPUTER_EQUIPMENT(203)
		equipmentEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("medical_equipment", lang), "", "equipment-view204", eqUrl + "204")); // MEDICAL_EQUIPMENT(204)
		equipmentEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("cook_equipment", lang), "", "equipment-view205", eqUrl + "205")); // COOK_EQUIPMENT(205)
		equipmentEntry.addEntry(new _OutlineEntry(getLocalizedWord("equipment_of_civil_defence", lang), "",
				"equipment-view206", eqUrl + "206")); // EQUIPMENT_OF_CIVIL_DEFENCE(206)
		equipmentEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("others_equipment", lang), "", "equipment-view207", eqUrl + "207")); // OTHERS_EQUIPMENT(207)
		
		// -----
		String reUrl = "p?id=realestate-view&kuf=";
		_OutlineEntry realEstateEntry = new _OutlineEntry(getLocalizedWord("real_estates", lang), "realestate-view");
		realEstateEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("buildings", lang), "", "realestate-view301", reUrl + "301")); // BUILDINGS(301)
		// TODO ROOMS(302) ?
		realEstateEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("structures", lang), "", "realestate-view303", reUrl + "303")); // STRUCTURES(303)
		realEstateEntry.addEntry(new _OutlineEntry(getLocalizedWord("residential_objects", lang), "",
				"realestate-view304", reUrl + "304")); // RESIDENTIAL_OBJECTS(304)
		realEstateEntry
				.addEntry(new _OutlineEntry(getLocalizedWord("land", lang), "", "realestate-view305", reUrl + "305")); // LAND(305)
		realEstateEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("monuments", lang), "", "realestate-view306", reUrl + "306")); // MONUMENT(306)
		
		// -----
		String trUrl = "p?id=vehicle-view&kuf=";
		_OutlineEntry transportEntry = new _OutlineEntry(getLocalizedWord("transport", lang), "vehicle-view");
		
		_OutlineEntry autoEntry = new _OutlineEntry(getLocalizedWord("automobiles", lang), "", "vehicle-view401",
				trUrl + "401"); // AUTOMOBILE(401)
		autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("cars", lang), "", "vehicle-view4011", trUrl + "4011")); // CAR(4011)
		autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("cargo", lang), "", "vehicle-view4012", trUrl + "4012")); // CARGO(4012)
		autoEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("dej_transport", lang), "", "vehicle-view4013", trUrl + "4013")); // DEJ_TRANSPORT(4013)
		autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("official_transport", lang), "", "vehicle-view4014",
				trUrl + "4014")); // OFFICIAL_TRANSPORT(4014)
		autoEntry.addEntry(new _OutlineEntry(getLocalizedWord("hospital_transport", lang), "", "vehicle-view4015",
				trUrl + "4015")); // HOSPITAL_TRANSPORT(4015)
		transportEntry.addEntry(autoEntry);
		
		_OutlineEntry passengerTransportEntry = new _OutlineEntry(getLocalizedWord("passenger_transport", lang), "",
				"vehicle-view4016", trUrl + "4016"); // passenger transport group
		passengerTransportEntry
				.addEntry(new _OutlineEntry(getLocalizedWord("buses", lang), "", "vehicle-view40161", trUrl + "40161")); // BUS(40161)
		passengerTransportEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("trolleybuses", lang), "", "vehicle-view40162", trUrl + "40162")); // TROLLEYBUS(40162)
		passengerTransportEntry
				.addEntry(new _OutlineEntry(getLocalizedWord("trams", lang), "", "vehicle-view40163", trUrl + "40163")); // TRAM(40163)
		passengerTransportEntry
				.addEntry(new _OutlineEntry(getLocalizedWord("taxi", lang), "", "vehicle-view40164", trUrl + "40164")); // TAXI(40164)
		passengerTransportEntry.addEntry(new _OutlineEntry(getLocalizedWord("water_transports", lang), "",
				"vehicle-view40165", trUrl + "40165")); // WATER_TRANSPORT(40165)
		autoEntry.addEntry(passengerTransportEntry);
		
		transportEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("special_equipment", lang), "", "vehicle-view402", trUrl + "402")); // SPECIAL_EQUIPMENT(402)
		transportEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("motorcycles", lang), "", "vehicle-view403", trUrl + "403")); // MOTORCYCLE(403)
		
		// -----
		String soUrl = "p?id=strategicobject-view&kuf=";
		_OutlineEntry strategicObjectEntry = new _OutlineEntry(getLocalizedWord("strategic_objects", lang),
				"strategicobject-view");
		strategicObjectEntry.addEntry(new _OutlineEntry(getLocalizedWord("object_reserved_fund", lang), "",
				"strategicobject-view501", soUrl + "501"));
		
		_OutlineEntry specialConstructionEntry = new _OutlineEntry(getLocalizedWord("special_constructions", lang), "",
				"strategicobject-view502", soUrl + "502");
		specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("bomb_proofs", lang), "",
				"strategicobject-view5021", soUrl + "5021")); // BOMBPROOF(5021)
		specialConstructionEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("factory", lang), "", "strategicobject-view5022", soUrl + "5022")); // FACTORY(5022)
		specialConstructionEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("combines", lang), "", "strategicobject-view5023", soUrl + "5023")); // COMBINES(5023)
		specialConstructionEntry.addEntry(
				new _OutlineEntry(getLocalizedWord("airports", lang), "", "strategicobject-view5024", soUrl + "5024")); // AIRPORT(5024)
		specialConstructionEntry.addEntry(new _OutlineEntry(getLocalizedWord("transitions", lang), "",
				"strategicobject-view5025", soUrl + "5025")); // TRANSITIONS(5025)
		strategicObjectEntry.addEntry(specialConstructionEntry);
		
		// -----
		String eiUrl = "p?id=engineeringinfrastructure-view&kuf=";
		_OutlineEntry engStructureEntry = new _OutlineEntry(getLocalizedWord("eng_infrastructure", lang),
				"engineeringinfrastructure-view");
		engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("billboards", lang), "",
				"engineeringinfrastructure-view601", eiUrl + "601")); // BILLBOARD(601)
		engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("columns", lang), "",
				"engineeringinfrastructure-view602", eiUrl + "602")); // COLUMNS(602)
		
		_OutlineEntry networkEntry = new _OutlineEntry(getLocalizedWord("networks", lang), "",
				"engineeringinfrastructure-view603", eiUrl + "603");
		networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("electric_networks", lang), "",
				"engineeringinfrastructure-view6031", eiUrl + "6031")); // ELECTRIC_NETWORKS(6031)
		networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("thermal_networks", lang), "",
				"engineeringinfrastructure-view6032", eiUrl + "6032")); // THERMAL_NETWORKS(6032)
		networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("gas", lang), "", "engineeringinfrastructure-view6033",
				eiUrl + "6033")); // GAS(6033)
		networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("water_systems", lang), "",
				"engineeringinfrastructure-view6034", eiUrl + "6034")); // WATER_SYSTEM(6034)
		networkEntry.addEntry(new _OutlineEntry(getLocalizedWord("drain", lang), "",
				"engineeringinfrastructure-view6035", eiUrl + "6035")); // DRAIN(6035)
		engStructureEntry.addEntry(networkEntry);
		engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("roads", lang), "",
				"engineeringinfrastructure-view604", eiUrl + "604")); // ROAD(604)
		engStructureEntry.addEntry(new _OutlineEntry(getLocalizedWord("parkings", lang), "",
				"engineeringinfrastructure-view605", eiUrl + "605")); // PARKING(605)
		
		munPropOutline.addEntry(allPropertyEntry);
		allPropertyEntry.addEntry(personalEstateEntry);
		allPropertyEntry.addEntry(intangibleAssetsEntry);
		allPropertyEntry.addEntry(equipmentEntry);
		allPropertyEntry.addEntry(realEstateEntry);
		allPropertyEntry.addEntry(transportEntry);
		allPropertyEntry.addEntry(strategicObjectEntry);
		allPropertyEntry.addEntry(engStructureEntry);
		
		_Outline orderOutline = new _Outline(getLocalizedWord("orders", lang), "order");
		orderOutline.addEntry(new _OutlineEntry(getLocalizedWord("orders", lang), "order-view"));
		
		_Outline contractOutline = new _Outline(getLocalizedWord("contracts", lang), "contract");
		contractOutline.addEntry(new _OutlineEntry(getLocalizedWord("contracts", lang), "contract-view"));
		
		_Outline reportOutline = new _Outline(getLocalizedWord("reports", lang), "report");
		reportOutline.addEntry(new _OutlineEntry(getLocalizedWord("reports", lang), "report-template-view"));
		
		_Outline mpByPropertyCodeOutline = new _Outline(getLocalizedWord("municipal_property_by_propertycode", lang),
				"municipal_property_by_propertycode");
		try {
			for (PropertyCode propCode : new PropertyCodeDAO(session).findAll().getResult()) {
				mpByPropertyCodeOutline.addEntry(new _OutlineEntry(propCode.getLocName(lang),
						getLocalizedWord("labeled", lang) + " : " + propCode.getLocName(lang),
						"mpbypropertycode-view" + propCode.getId(),
						"p?id=mpbypropertycode-view&categoryid=" + propCode.getId()));
			}
		} catch (DAOException e) {
			Server.logger.errorLogEntry(e);
			setBadRequest();
		}
		_Outline notificationOutline = new _Outline(getLocalizedWord("notifications", lang), "notification");
		String nUrl = "p?id=notification-view&type=";
		for (NotificationType type : NotificationType.values()) {
			if (type != NotificationType.UNKNOWN) {
				String stringType = type.name();
				notificationOutline.addEntry(new _OutlineEntry(getLocalizedWord(stringType.toLowerCase(), lang), "",
						"notification-view" + stringType, nUrl + stringType));
			}
		}
		
		list.add(reportOutline);
		list.add(munPropOutline);
		list.add(mpByPropertyCodeOutline);
		list.add(notificationOutline);
		list.add(orderOutline);
		list.add(contractOutline);
		
		String kuf = formData.getValueSilently("kuf");
		String type = formData.getValueSilently("type");
		String categoryId = formData.getValueSilently("categoryid");
		String pageId = formData.getValueSilently("id").replace("-form", "-view") + kuf + categoryId + type;
		
		addValue("workspaceUrl", Environment.getWorkspaceURL());
		addValue("outline_current", pageId);
		
		if (!kuf.isEmpty()) {
			addValue("request_param", "kuf=" + kuf);
		}
		if (!categoryId.isEmpty()) {
			addValue("request_param", "categoryid=" + categoryId);
		}
		if (!type.isEmpty()) {
			addValue("request_param", "type=" + type);
		}
		addContent(list);
	}
}
