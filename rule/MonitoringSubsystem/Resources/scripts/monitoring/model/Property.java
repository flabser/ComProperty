package monitoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import kz.flabs.dataengine.jpa.AppEntity;
import monitoring.model.constants.KufType;

@Entity
@Table(name = "properties")
public class Property extends AppEntity {
	@Column(length = 10)
	private String kof;

	@Enumerated(EnumType.STRING)
	@Column(name = "kuf_type", nullable = false, length = 32)
	private KufType kuf;

	@Column(name = "balance_cost")
	private float balanceCost;

	private String description;

	@Column(name = "inv_number")
	private String invNumber;

	@Column(name = "object_name")
	private String objectName;

	@Column(name = "original_cost")
	private float originalCost;

	@Column(name = "property_code")
	private String propertyCode;

	private String assignment;

	@Column(name = "year_release")
	private int yearRelease;

	public String getKof() {
		return kof;
	}

	public void setKof(String kof) {
		this.kof = kof;
	}

	public KufType getKuf() {
		return kuf;
	}

	public void setKuf(KufType kuf) {
		this.kuf = kuf;
	}

	public float getBalanceCost() {
		return balanceCost;
	}

	public void setBalanceCost(float balanceCost) {
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

	public float getOriginalCost() {
		return originalCost;
	}

	public void setOriginalCost(float originalCost) {
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
