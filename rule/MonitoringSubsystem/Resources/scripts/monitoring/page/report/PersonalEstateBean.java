package monitoring.page.report;

public class PersonalEstateBean extends PropertyBean {
	private String countfloors;
	private String receivingbasis;

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

	public String getReceivingbasis() {
		if (receivingbasis == null) {
			return "";
		} else {
			return receivingbasis;
		}
	}

	public void setReceivingbasis(String receivingbasis) {
		this.receivingbasis = receivingbasis;
	}

}
