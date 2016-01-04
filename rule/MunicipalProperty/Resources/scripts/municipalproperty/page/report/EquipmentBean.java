package municipalproperty.page.report;

public class EquipmentBean extends PropertyBean {
	private String countfloors;
	private String objectname;
	private String color;

	public String getCountfloors() {
		if (countfloors == null) {
			return "";
		} else {
			return countfloors;
		}

	}

	public void setCountfloors(String countfloors) {
		this.countfloors = countfloors;
	}

	public String getObjectname() {
		if (objectname == null) {
			return "";
		} else {
			return objectname;
		}
	}

	public void setObjectname(String objectName) {
		this.objectname = objectName;
	}

	public String getColor() {
		if (color == null) {
			return "";
		} else {
			return color;
		}
	}

	public void setColor(String color) {
		this.color = color;
	}

}
