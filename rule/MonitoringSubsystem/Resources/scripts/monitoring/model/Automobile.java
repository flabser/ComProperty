package monitoring.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "automobiles")
public class Automobile extends Property {
	private String bodyNumber;
	private String category;
	private String color;
	private String fuelType;
	private String grnz;
	private int maxLoad;
	private String engineNumber;

	public String getBodyNumber() {
		return bodyNumber;
	}

	public void setBodyNumber(String bodyNumber) {
		this.bodyNumber = bodyNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getGrnz() {
		return grnz;
	}

	public void setGrnz(String grnz) {
		this.grnz = grnz;
	}

	public int getMaxLoad() {
		return maxLoad;
	}

	public void setMaxLoad(int maxLoad) {
		this.maxLoad = maxLoad;
	}

	public String getEngineNumber() {
		return engineNumber;
	}

	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}

}
