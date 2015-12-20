package monitoring.model.constants;

public enum KufType {
	UNKNOWN(0), FURNITURE(101), OTHERS(106), SCHOOL_EQUIPMENT(201), COMPUTER_EQUIPMENT(203), COOK_EQUIPMENT(
			205), EQUIPMENT_OF_CIVIL_DEFENSE(206), OTHERS_EQUIPMENTS(207);

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
 *
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