package municipalproperty.model.constants;

public enum KufType {
	UNKNOWN(0), FURNITURE(101), ANIMALS(102), SPORT_EQUIPMENT(103), SHARE_BLOCK(104), EQUITY(105), OTHERS(106), SCHOOL_EQUIPMENT(201), OFFICE_EQUIPMENT(
	        202), COMPUTER_EQUIPMENT(203), MEDICAL_EQUIPMENT(204), COOK_EQUIPMENT(205), EQUIPMENT_OF_CIVIL_DEFENCE(206), OTHERS_EQUIPMENT(207), BUILDINGS(
	        301), ROOMS(302), STRUCTURES(303), RESIDENTIAL_OBJECTS(304), LAND(305), MONUMENT(306), AUTOMOBILE(401), CAR(4011), CARGO(4012), DEJ_TRANSPORT(
	        4013), OFFICIAL_TRANSPORT(4014), HOSPITAL_TRANSPORT(4015), BUS(40161), TROLLEYBUS(40162), TRAM(40163), TAXI(40164), WATER_TRANSPORT(40165), SPECIAL_EQUIPMENT(
	        402), MOTORCYCLE(403), OBJECT_RESERVED_FUND(501), BOMBPROOF(5021), FACTORY(5022), COMBINES(5023), AIRPORT(5024), TRANSITIONS(5025), BILLBOARD(
	        601), COLUMNS(602), ELECTRIC_NETWORKS(6031), THERMAL_NETWORKS(6032), GAS(6033), WATER_SYSTEM(6034), DRAIN(6035), ROAD(604), PARKING(605);

	private int code;

	KufType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static KufType getType(int code) {
		for (KufType type : values()) {
			if (type.code == code) {
				return type;
			}
		}
		return UNKNOWN;
	}

}
/*
 * 101=furniture 102=animals 103=sportsequipment 104=shareblocks 105=equity
 * 106=others 201=schoolequipment 202=officeequipment 203=computerequipment
 * 204=medicalequipment 205=cookequipment 206=equipmentofcivildefense
 * 207=others_equipment 301=buildings 302=rooms 303=structures
 * 304=residentialobjects 305=land 306=monument 401=automobile 4011=automobile
 * 4012=cargo 4013=dejtransport 4014=officialtransport 4015=hospitaltransport
 * 40161=bus 40162=trolleybus 40163=tram 40164=taxi 40165=watertransport
 * 402=specialequipment 403=motorcycle 501=objectreservedfund 5021=bombproof
 * 5022=factory 5023=combines 5024=airport 5025=transitions 601=billboard
 * 602=columns 6031=electricnetworks 6032=thermalnetworks 6033=gas
 * 6034=watersystem 6035=drain 604=road 605=parking
 */