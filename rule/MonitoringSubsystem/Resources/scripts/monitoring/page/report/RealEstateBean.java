package monitoring.page.report;

public class RealEstateBean extends PropertyBean {
	private String countfloors;
	private int material;

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

	public void setMaterial(int m) {
		this.material = m;

	}

	public int getMaterial() {
		return material;

	}

}
