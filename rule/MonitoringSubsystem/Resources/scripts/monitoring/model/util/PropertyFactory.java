package monitoring.model.util;

import kz.flabs.util.Util;
import monitoring.model.Equipment;
import monitoring.model.PersonalEstate;
import monitoring.model.Property;
import monitoring.model.constants.KufType;

public class PropertyFactory {
	public static Property getProperty(String kuf) {
		KufType kt = KufType.getType(Util.convertStringToInt(kuf));
		switch (kt) {
		case FURNITURE:
			return new PersonalEstate();
		case OTHERS:
			return new PersonalEstate();
		case SCHOOL_EQUIPMENT:
			return new Equipment();
		case COMPUTER_EQUIPMENT:
			return new Equipment();
		case COOK_EQUIPMENT:
			return new Equipment();
		case EQUIPMENT_OF_CIVIL_DEFENSE:
			return new Equipment();
		case OTHERS_EQUIPMENTS:
			return new Equipment();
		default:
			return new Property();
		}

	}
}
