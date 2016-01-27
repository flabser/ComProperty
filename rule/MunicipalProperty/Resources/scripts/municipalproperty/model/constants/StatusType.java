package municipalproperty.model.constants;

/**
 * 
 * 
 * @author Kayra created 27-01-2016
 */

public enum StatusType {
	UNKNOWN(0), ON_BALANCE(825), WRITTENOFF(826);

	private int code;

	StatusType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static StatusType getType(int code) {
		for (StatusType type : values()) {
			if (type.code == code) {
				return type;
			}
		}
		return UNKNOWN;
	}
}
