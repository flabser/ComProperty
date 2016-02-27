package municipalproperty.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import kz.flabs.dataengine.DatabaseFactory;
import kz.flabs.dataengine.ISystemDatabase;
import kz.flabs.users.User;
import kz.flabs.util.Util;
import kz.lof.dataengine.jpa.SecureAppEntity;
import kz.lof.scripting._Session;
import municipalproperty.model.constants.KufType;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import reference.model.Tag;
import staff.model.Organization;

@Entity
@Table(name = "properties", uniqueConstraints = @UniqueConstraint(columnNames = { "inv_number", "object_name" }))
@NamedQuery(name = "Property.findAll", query = "SELECT m FROM Property AS m ORDER BY m.regDate")
public class Property extends SecureAppEntity {
	@Column(length = 16)
	private String kof;

	@Enumerated(EnumType.STRING)
	@Column(name = "kuf", nullable = false, length = 32)
	private KufType kuf;

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

	private String description = "";

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

	private String assignment = "";

	@Column(name = "year_release")
	private int yearRelease;

	@Column(name = "acceptance_date")
	private Date acceptanceDate;

	@Column(name = "ready_to_use")
	private boolean readyToUse;

	private String notes = "";

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "property_tags")
	private List<Tag> tags;

	public boolean isReadyToUse() {
		return readyToUse;
	}

	public void setReadyToUse(boolean readyToUse) {
		this.readyToUse = readyToUse;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void addTag(Tag tag) {
		if (tags == null) {
			tags = new ArrayList<Tag>();
		}
		tags.add(tag);
	}

	// TODO to improve
	public boolean isTagged(String name) {
		if (getTags() != null) {
			for (Tag tag : tags) {
				if (tag.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String getDefaultFormName() {
		return getKuf().name().replace("_", "-").toLowerCase() + "-form";
	}

	@Override
	public String getDefaultViewName() {
		return getKuf().name().replace("_", "-").toLowerCase() + "-view";
	}

	@Override
	public String getURL() {
		return "Provider?id=" + getForm() + "&amp;docid=" + getId();
	}

	@Override
	public String getShortXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<originalcost>" + originalCost + "</originalcost>");
		chunk.append("<balanceholder>" + balanceHolder + "</balanceholder>");
		chunk.append("<invnumber>" + invNumber + "</invnumber>");
		chunk.append("<objectname>" + objectName + "</objectname>");
		return chunk.toString();
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<regdate>" + Util.simpleDateFormat.format(regDate) + "</regdate>");
		ISystemDatabase sysDb = DatabaseFactory.getSysDatabase();
		User user = sysDb.getUser(author);
		chunk.append("<author>" + user.getUserID() + "</author>");
		try {
			chunk.append("<acceptancedate>" + Util.simpleDateFormat.format(acceptanceDate) + "</acceptancedate>");
		} catch (NullPointerException e) {
			chunk.append("<acceptancedate></acceptancedate>");
		}
		chunk.append("<acquisitionyear>" + acquisitionYear + "</acquisitionyear>");
		chunk.append("<assignment>" + assignment + "</assignment>");
		chunk.append("<balancecost>" + balanceCost + "</balancecost>");
		chunk.append("<balanceholder>" + balanceHolder.getName() + "</balanceholder>");
		chunk.append("<balanceholderbin>" + balanceHolder.getBin() + "</balanceholderbin>");
		chunk.append("<balanceholderid>" + balanceHolder.getId() + "</balanceholderid>");
		chunk.append("<commissioningyear>" + commissioningYear + "</commissioningyear>");
		chunk.append("<cumulativedepreciation>" + cumulativeDepreciation + "</cumulativedepreciation>");
		chunk.append("<description>" + description + "</description>");
		chunk.append("<impairmentloss>" + impairmentLoss + "</impairmentloss>");
		chunk.append("<invnumber>" + invNumber + "</invnumber>");
		chunk.append("<kof>" + kof + "</kof>");
		try {
			chunk.append("<kuf>" + kuf.getCode() + "</kuf>");
		} catch (NullPointerException e) {
			chunk.append("<acceptancedate></acceptancedate>");
		}
		chunk.append("<objectname>" + objectName + "</objectname>");
		chunk.append("<originalcost>" + originalCost + "</originalcost>");
		chunk.append("<propertycode>" + propertyCode.getId() + "</propertycode>");
		chunk.append("<isreadytouse>" + readyToUse + "</isreadytouse>");
		chunk.append("<receivingreason>" + receivingReason.getId() + "</receivingreason>");
		chunk.append("<residualcost>" + residualCost + "</residualcost>");
		chunk.append("<revaluationamount>" + revaluationAmount + "</revaluationamount>");
		try {
			String tagsAsText = "";
			for (Tag t : tags) {
				tagsAsText += "<entry id=\"" + t.getName() + "\">" + t.getLocalizedName().get(ses.getLang()) + "</entry>";
			}
			chunk.append("<tags>" + tagsAsText + "</tags>");
		} catch (NullPointerException e) {
			chunk.append("<tags></tags>");
		}
		chunk.append("<yearrelease>" + yearRelease + "</yearrelease>");
		chunk.append("<notes>" + notes + "</notes>");
		return chunk.toString();
	}

}
