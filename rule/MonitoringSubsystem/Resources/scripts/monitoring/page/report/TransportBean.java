package monitoring.page.report;

public class TransportBean extends PropertyBean {
	private String enginenumber;
	private String model;
	private String category;

	public String getEnginenumber() {
		if (enginenumber != null) {
			return enginenumber;
		} else {
			return "";
		}
	}

	public void setEnginenumber(String enginenumber) {
		this.enginenumber = enginenumber;
	}

	public String getModel() {
		if (model != null) {
			return model;
		} else {
			return "";
		}
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCategory() {
		if (category == null) {
			return "";
		} else {
			return category;
		}
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
