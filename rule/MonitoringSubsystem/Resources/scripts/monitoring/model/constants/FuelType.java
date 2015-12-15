package monitoring.model.constants;

public enum FuelType {
	UNKNOWN(0), PETROL(1001), DIESEL_FUEL(1002), GAS(1003);

	private int code;

	FuelType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static FuelType getType(int code) {
		for (FuelType type : values()) {
			if (type.code == code) {
				return type;
			}
		}
		return UNKNOWN;
	}

}
