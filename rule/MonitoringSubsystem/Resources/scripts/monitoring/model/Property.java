package monitoring.model;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import kz.flabs.dataengine.jpa.AppEntity;

@Entity
@Table(name = "properties")
@MappedSuperclass
public class Property extends AppEntity {
	private String address;
	private long balanceCost;
	private String description;
	private String invNumber;
	private String objectName;
	private long originalCost;
	private String propertyCode;
	private String assignment;
	private int yearRelease;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getBalanceCost() {
		return balanceCost;
	}

	public void setBalanceCost(long balanceCost) {
		this.balanceCost = balanceCost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInvNumber() {
		return invNumber;
	}

	public void setInvNumber(String invNumber) {
		this.invNumber = invNumber;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public long getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(long originalCost) {
		this.originalCost = originalCost;
	}

	public String getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(String propertyCode) {
		this.propertyCode = propertyCode;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public int getYearRelease() {
		return yearRelease;
	}

	public void setYearRelease(int yearRelease) {
		this.yearRelease = yearRelease;
	}

}
