package municipalproperty.model.util;

import kz.flabs.util.Util;
import municipalproperty.model.EngineeringInfrastructure;
import municipalproperty.model.Equipment;
import municipalproperty.model.IntangibleAsset;
import municipalproperty.model.PersonalEstate;
import municipalproperty.model.Property;
import municipalproperty.model.RealEstate;
import municipalproperty.model.StrategicObject;
import municipalproperty.model.Vehicle;
import municipalproperty.model.constants.KufType;

public class PropertyFactory {
	public static Property getProperty(String kuf) {
		KufType kt = KufType.getType(Util.convertStringToInt(kuf));
		switch (kt) {
		case FURNITURE:
			return new PersonalEstate();
		case ANIMALS:
			return new PersonalEstate();
		case SPORT_EQUIPMENT:
			return new PersonalEstate();
		case OTHERS:
			return new PersonalEstate();
		case SHARE_BLOCK:
			return new IntangibleAsset();
		case EQUITY:
			return new IntangibleAsset();
		case SCHOOL_EQUIPMENT:
			return new Equipment();
		case COMPUTER_EQUIPMENT:
			return new Equipment();
		case OFFICE_EQUIPMENT:
			return new Equipment();
		case MEDICAL_EQUIPMENT:
			return new Equipment();
		case COOK_EQUIPMENT:
			return new Equipment();
		case EQUIPMENT_OF_CIVIL_DEFENSE:
			return new Equipment();
		case OTHERS_EQUIPMENT:
			return new Equipment();
		case BUILDINGS:
			return new RealEstate();
		case ROOMS:
			return new RealEstate();
		case STRUCTURES:
			return new RealEstate();
		case RESIDENTIAL_OBJECTS:
			return new RealEstate();
		case LAND:
			return new RealEstate();
		case MONUMENT:
			return new RealEstate();
		case AUTOMOBILE:
			return new Vehicle();
		case CAR:
			return new Vehicle();
		case CARGO:
			return new Vehicle();
		case DEJ_TRANSPORT:
			return new Vehicle();
		case OFFICIAL_TRANSPORT:
			return new Vehicle();
		case HOSPITAL_TRANSPORT:
			return new Vehicle();
		case BUS:
			return new Vehicle();
		case TROLLEYBUS:
			return new Vehicle();
		case TRAM:
			return new Vehicle();
		case TAXI:
			return new Vehicle();
		case WATER_TRANSPORT:
			return new Vehicle();
		case SPECIAL_EQUIPMENT:
			return new Vehicle();
		case MOTORCYCLE:
			return new Vehicle();
		case OBJECT_RESERVED_FUND:
			return new StrategicObject();
		case BOMBPROOF:
			return new StrategicObject();
		case FACTORY:
			return new StrategicObject();
		case COMBINES:
			return new StrategicObject();
		case AIRPORT:
			return new StrategicObject();
		case TRANSITIONS:
			return new StrategicObject();
		case BILLBOARD:
			return new EngineeringInfrastructure();
		case COLUMNS:
			return new EngineeringInfrastructure();
		case ELECTRIC_NETWORKS:
			return new EngineeringInfrastructure();
		case THERMAL_NETWORKS:
			return new EngineeringInfrastructure();
		case GAS:
			return new EngineeringInfrastructure();
		case WATER_SYSTEM:
			return new EngineeringInfrastructure();
		case DRAIN:
			return new EngineeringInfrastructure();
		case ROAD:
			return new EngineeringInfrastructure();
		case PARKING:
			return new EngineeringInfrastructure();
		default:
			return new Property();
		}

	}
}
