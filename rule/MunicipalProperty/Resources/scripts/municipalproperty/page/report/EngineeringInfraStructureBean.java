package municipalproperty.page.report;

public class EngineeringInfraStructureBean extends PropertyBean {
	private int material;
	private String totalarea;

	public void setMaterial(int m) {
		this.material = m;

	}

	public int getMaterial() {
		return material;

	}

	public String getTotalarea() {
		if (totalarea != null) {
			return totalarea;
		} else {
			return "";
		}
	}

	public void setTotalarea(String totalarea) {
		this.totalarea = totalarea;
	}
}
