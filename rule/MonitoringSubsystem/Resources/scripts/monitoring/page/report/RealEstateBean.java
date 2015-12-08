package monitoring.page.report;

public class RealEstateBean {
	private long originalcost;
	private String yearrelease;
	private String countfloors;
	private String viewtext;
	private String invnumber;
	private String assignment;
	private String propertycodename;
	private String material;

	public long getOriginalcost() {
		return originalcost;
	}

	public void setOriginalcost(long originalCost) {
		this.originalcost = originalCost;
	}

	public String getYearrelease() {
		if (yearrelease == null) {
			return "";
		} else {
			return yearrelease;
		}
	}

	public void setYearrealise(String string) {
		this.yearrelease = string;
	}

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

	public String getViewtext() {
		if (viewtext == null) {
			return "";
		} else {
			return viewtext;
		}
	}

	public void setViewtext(String viewText) {
		this.viewtext = viewText;
	}

	public String getInvnumber() {
		if (invnumber == null) {
			return "";
		} else {
			return invnumber;
		}
	}

	public void setInvnumber(String invnumber) {
		this.invnumber = invnumber;
	}

	public String getAssignment() {
		if (assignment == null) {
			return "";
		} else {
			return assignment;
		}

	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public String getPropertycodename() {
		if (propertycodename == null) {
			return "";
		} else {
			return propertycodename;
		}

	}

	public void setPropertycodename(String propertycodename) {
		this.propertycodename = propertycodename;
	}

	public void setMaterial(String material) {
		this.material = material;

	}

	public String getMaterial() {
		if (material == null) {
			return "";
		} else {
			return material;
		}

	}

}
