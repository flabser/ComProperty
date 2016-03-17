package municipalproperty.model.constants;

/**
 * @author Kayra created 27-01-2016
 */

public enum PropertyStatusType {
    UNKNOWN(0), ON_BALANCE(825), WRITTENOFF(826);

    private int code;

    PropertyStatusType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PropertyStatusType getType(int code) {
        for (PropertyStatusType type : values()) {
            if (type.code == code) {
                return type;
            }
        }
        return UNKNOWN;
    }
}
