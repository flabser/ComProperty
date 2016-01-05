package municipalproperty.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import municipalproperty.model.constants.FuelType;

@Entity
@Table(name = "vehicles")
@Inheritance(strategy = InheritanceType.JOINED)
public class Vehicle extends Property {
	@Column(name = "body_number", length = 30)
	private String bodyNumber;

	private String category;

	@Column(length = 20)
	private String color;

	@Enumerated(EnumType.STRING)
	@Column(name = "fuel_type", nullable = true, length = 16)
	private FuelType fuelType = FuelType.UNKNOWN;

	@Column(length = 10)
	private String grnz;

	@Column(name = "max_load")
	private int maxLoad;

	@Column(name = "engine_number", length = 30)
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

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
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
