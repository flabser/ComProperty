package municipalproperty.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import kz.flabs.dataengine.jpa.SecureAppEntity;
import kz.nextbase.script._URL;
import municipalproperty.model.constants.KufType;

import org.eclipse.persistence.annotations.Convert;

@Entity
@Table(name = "properties")
@NamedQuery(name = "Property.findAll", query = "SELECT m FROM Property AS m ORDER BY m.regDate")
public class Property extends SecureAppEntity {
	@Column(length = 16)
	private String kof;

	@Enumerated(EnumType.STRING)
	@Column(name = "kuf", nullable = false, length = 32)
	private KufType kuf;

	@Column(name = "balance_cost")
	private float balanceCost;

	@Convert("uuidConverter")
	@Column(name = "balance_holder")
	private UUID balanceHolder;

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

	@Column(name = "acceptance_date")
	private Date acceptanceDate;

	@Column(name = "year_release")
	private String getKof() {
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

	public UUID getBalanceHolder() {
		return balanceHolder;
	}

	public void setBalanceHolder(UUID balanceHolder) {
		this.balanceHolder = balanceHolder;
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

	public Date getAcceptanceDate() {
		return acceptanceDate;
	}

	public void setAcceptanceDate(Date acceptanceDate) {
		this.acceptanceDate = acceptanceDate;
	}

	@Override
	public _URL getURL() {
		return new _URL("Provider?id=" + getKuf().name().toLowerCase() + "_form&amp;docid=" + getId());
	}

}
