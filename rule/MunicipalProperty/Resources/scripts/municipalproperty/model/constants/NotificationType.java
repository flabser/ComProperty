package municipalproperty.model.constants;

public enum NotificationType {
	UNKNOWN(0), OBJECT_REGISTERED(650), CONTRACT_EXPIRED(651), ORDER_REGISTERED(652), CONTRACT_REGISTERED(653), OBJECT_LOADED(654);

	private int code;

	NotificationType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static NotificationType getType(int code) {
		for (NotificationType type : values()) {
			if (type.code == code) {
				return type;
			}
		}
		return UNKNOWN;
	}
}
