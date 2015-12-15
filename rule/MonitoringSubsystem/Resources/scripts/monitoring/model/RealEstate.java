package monitoring.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import monitoring.model.constants.PropertyType;
import monitoring.model.embedded.Address;

@Entity
@Table(name = "realestates")
@NamedQuery(name = "RealEstate.findAll", query = "SELECT m FROM RealEstate AS m ORDER BY m.regDate")
public class RealEstate extends Property implements IProperty {
	@Embedded
	private Address address;

	@Column(name = "count_floors")
	private int countFloors;

	private String material;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getCountFloors() {
		return countFloors;
	}

	public void setCountFloors(int countFloors) {
		this.countFloors = countFloors;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Override
	public PropertyType getType() {
		return PropertyType.REAL_ESTATE;
	}

}
