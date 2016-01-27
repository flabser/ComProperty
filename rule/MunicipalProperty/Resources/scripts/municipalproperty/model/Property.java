package municipalproperty.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kz.flabs.dataengine.jpa.SecureAppEntity;
import kz.nextbase.script._URL;
import municipalproperty.model.constants.KufType;
import municipalproperty.model.constants.StatusType;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import staff.model.Organization;

@Entity
@Table(name = "properties")
@NamedQuery(name = "Property.findAll", query = "SELECT m FROM Property AS m ORDER BY m.regDate")
public class Property extends SecureAppEntity {
	@Column(length = 16)
	private String kof;

	@Enumerated(EnumType.STRING)
	@Column(name = "kuf", nullable = false, length = 32)
	private KufType kuf;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 32)
	private StatusType status;

	@Column(name = "balance_cost")
	private float balanceCost;

	@Column(name = "revaluation_amount")
	private float revaluationAmount;

	@Column(name = "residual_cost")
	private float residualCost;

	@ManyToOne
	@JoinColumn
	private ReceivingReason receivingReason;

	@Column(name = "commissioning_year")
	private int commissioningYear;

	@Column(name = "acquisition_year")
	private int acquisitionYear;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Organization balanceHolder;

	private String description;

	@Column(name = "inv_number")
	private String invNumber;

	@Column(name = "object_name")
	private String objectName;

	@Column(name = "original_cost")
	private float originalCost;

	@Column(name = "cumulative_depreciation")
	private float cumulativeDepreciation;

	@Column(name = "impairment_loss")
	private float impairmentLoss;

	@ManyToOne
	@JoinColumn
	private PropertyCode propertyCode;

	private String assignment;

	@Column(name = "year_release")
	private int yearRelease;

	@Column(name = "acceptance_date")
	private Date acceptanceDate;

	@Column(name = "ready_to_use")
	private boolean readyToUse;

	public boolean isReadyToUse() {
		return readyToUse;
	}

	public void setReadyToUse(boolean readyToUse) {
		this.readyToUse = readyToUse;
	}

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

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public float getBalanceCost() {
		return balanceCost;
	}

	public void setBalanceCost(float balanceCost) {
		this.balanceCost = balanceCost;
	}

	public float getCumulativeDepreciation() {
		return cumulativeDepreciation;
	}

	public void setCumulativeDepreciation(float cumulativeDepreciation) {
		this.cumulativeDepreciation = cumulativeDepreciation;
	}

	public float getImpairmentLoss() {
		return impairmentLoss;
	}

	public void setImpairmentLoss(float impairmentLoss) {
		this.impairmentLoss = impairmentLoss;
	}

	public Organization getBalanceHolder() {
		return balanceHolder;
	}

	public void setBalanceHolder(Organization balanceHolder) {
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

	public PropertyCode getPropertyCode() {
		return propertyCode;
	}

	public void setPropertyCode(PropertyCode propertyCode) {
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

	public float getRevaluationAmount() {
		return revaluationAmount;
	}

	public void setRevaluationAmount(float revaluationAmount) {
		this.revaluationAmount = revaluationAmount;
	}

	public float getResidualCost() {
		return residualCost;
	}

	public void setResidualCost(float residualCost) {
		this.residualCost = residualCost;
	}

	public ReceivingReason getReceivingReason() {
		return receivingReason;
	}

	public void setReceivingReason(ReceivingReason receivingReason) {
		this.receivingReason = receivingReason;
	}

	public int getCommissioningYear() {
		return commissioningYear;
	}

	public void setCommissioningYear(int commissioningYear) {
		this.commissioningYear = commissioningYear;
	}

	public int getAcquisitionYear() {
		return acquisitionYear;
	}

	public void setAcquisitionYear(int acquisitionYear) {
		this.acquisitionYear = acquisitionYear;
	}

	@Override
	public String getDefaultForm() {
		return getKuf().name().replace("_", "-").toLowerCase() + "-form";
	}

	// TODO need to post the URL during saving
	@Override
	public _URL getURL() {
		return new _URL("Provider?id=" + getKuf().name().replace("_", "-").toLowerCase() + "-form&amp;docid=" + getId());
	}

}
