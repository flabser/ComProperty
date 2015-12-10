package monitoring.page.report;

public class PropertyBean implements IPropertyBean {
	private long originalcost;
	private String invnumber;
	private String assignment;
	private String propertycodename;
	private String description;
	private String yearrelease;

	@Override
	public long getOriginalcost() {
		return originalcost;
	}

	@Override
	public void setOriginalcost(long originalcost) {
		this.originalcost = originalcost;
	}

	@Override
	public String getInvnumber() {
		if (invnumber != null) {
			return invnumber;
		} else {
			return "";
		}
	}

	@Override
	public void setInvnumber(String invnumber) {
		this.invnumber = invnumber;
	}

	@Override
	public String getAssignment() {
		if (assignment != null) {
			return assignment;
		} else {
			return "";
		}
	}

	@Override
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	@Override
	public String getPropertycodename() {
		if (propertycodename != null) {
			return propertycodename;
		} else {
			return "";
		}
	}

	@Override
	public void setPropertycodename(String propertycodename) {
		this.propertycodename = propertycodename;
	}

	public String getDescription() {
		if (description != null) {
			return description;
		} else {
			return "";
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getYearrelease() {
		if (yearrelease == null) {
			return "";
		} else {
			return yearrelease;
		}
	}

	public void setYearrelease(String string) {
		this.yearrelease = string;
	}
}
