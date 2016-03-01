package reference.model.constants;

public enum LocalityCode {
	UNKNOWN(0), CITY(1100), VILLAGE(1101);

	private int code;

	LocalityCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static LocalityCode getType(int code) {
		for (LocalityCode type : values()) {
			if (type.code == code) {
				return type;
			}
		}
		return UNKNOWN;
	}

}
