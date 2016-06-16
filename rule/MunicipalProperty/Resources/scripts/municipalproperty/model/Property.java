package municipalproperty.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.exponentus.common.model.Attachment;
import com.exponentus.dataengine.jpa.SecureAppEntity;
import com.exponentus.dataengine.system.IEmployee;
import com.exponentus.dataengine.system.IExtUserDAO;
import com.exponentus.env.Environment;
import com.exponentus.scripting._Session;
import com.exponentus.util.NumberUtil;
import com.exponentus.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;

import municipalproperty.model.constants.PropertyStatusType;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import reference.model.Tag;
import reference.model.constants.KufType;
import staff.model.Organization;

@Entity
@Table(name = "properties", uniqueConstraints = @UniqueConstraint(columnNames = { "inv_number", "object_name" }) )
@NamedQuery(name = "Property.findAll", query = "SELECT m FROM Property AS m ORDER BY m.regDate")
public class Property extends SecureAppEntity<UUID> {
	@Column(length = 16)
	private String kof;

	@Enumerated(EnumType.STRING)
	@Column(name = "kuf", nullable = false, length = 32)
	private KufType kuf;

	@Enumerated(EnumType.STRING)
	@Column(name = "property_status", nullable = false, length = 32)
	private PropertyStatusType propertyStatusType = PropertyStatusType.ON_BALANCE;

	@Column(name = "balance_cost")
	private float balanceCost;

	@Column(name = "revaluation_amount")
	private float revaluationAmount;

	@Column(name = "residual_cost")
	private float residualCost;

	@Column(name = "after_revaluation_amount")
	private float afterRevaluationAmount;

	@ManyToOne
	@JoinColumn
	private ReceivingReason receivingReason;

	@Column(name = "commissioning_year")
	private int commissioningYear;

	@Column(name = "acquisition_year")
	private int acquisitionYear;

	@OneToMany(mappedBy = "property")
	private List<PrevBalanceHolder> prevBalanceHolders;

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

	@Column(name = "tech_cert")
	private String techCert = "";

	@Column(name = "reg_cert")
	private String regCert = "";

	@Column(name = "decrees_acts")
	private String decreesActs = "";

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinTable(name = "property_attachments", joinColumns = { @JoinColumn(name = "parent_id", referencedColumnName = "id") }, inverseJoinColumns = {
	        @JoinColumn(name = "attachment_id", referencedColumnName = "id") })
	private List<Attachment> attachments;

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

	public PropertyStatusType getPropertyStatusType() {
		return propertyStatusType;
	}

	public void setPropertyStatusType(PropertyStatusType propertyStatusType) {
		this.propertyStatusType = propertyStatusType;
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

	public float getAfterRevaluationAmount() {
		return afterRevaluationAmount;
	}

	public void setAfterRevaluationAmount(float afterRevaluationAmount) {
		this.afterRevaluationAmount = afterRevaluationAmount;
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

	public List<PrevBalanceHolder> getPrevBalanceHolders() {
		return prevBalanceHolders;
	}

	public void setPrevBalanceHolders(List<PrevBalanceHolder> prevBalanceHolders) {
		this.prevBalanceHolders = prevBalanceHolders;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTechCert() {
		return techCert;
	}

	public void setTechCert(String techCert) {
		this.techCert = techCert;
	}

	public String getRegCert() {
		return regCert;
	}

	public void setRegCert(String regCert) {
		this.regCert = regCert;
	}

	public String getDecreesActs() {
		return decreesActs;
	}

	public void setDecreesActs(String decreesActs) {
		this.decreesActs = decreesActs;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String getDefaultFormName() {
		return getKuf().name().replace("_", "-").toLowerCase() + "-form";
	}

	@Override
	public String getDefaultViewName() {
		return getClass().getSimpleName().toLowerCase() + "-view&kuf=" + getKuf().getCode();
	}

	@Override
	public String getShortXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<originalcost>" + NumberUtil.formatFloat(originalCost) + "</originalcost>");
		chunk.append("<balanceholder>" + balanceHolder + "</balanceholder>");
		chunk.append("<invnumber>" + invNumber + "</invnumber>");
		chunk.append("<objectname>" + objectName + "</objectname>");
		chunk.append("<propertystatus>" + propertyStatusType + "</propertystatus>");
		if (getTags() != null && getTags().size() > 0) {
			chunk.append("<tags>");
			for (Tag tag : getTags()) {
				chunk.append("<tag>");
				chunk.append("<name>" + tag.getLocalizedName(ses.getLang()) + "</name>");
				chunk.append("<color>" + tag.getColor() + "</color>");
				chunk.append("</tag>");
			}
			chunk.append("</tags>");
		}
		if (!getAttachments().isEmpty()) {
			chunk.append("<attachments>" + getAttachments().size() + "</attachments>");
		}
		return chunk.toString();
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<regdate>" + Util.convertDataTimeToString(regDate) + "</regdate>");
		IExtUserDAO eDao = Environment.getExtUserDAO();
		IEmployee user = eDao.getEmployee(author);
		if (user != null) {
			chunk.append("<author>" + user.getName() + "</author>");
		} else {
			chunk.append("<author>" + author + "</author>");
		}

		chunk.append("<acceptancedate>" + Util.convertDateToStringSilently(acceptanceDate) + "</acceptancedate>");
		chunk.append("<acquisitionyear>" + acquisitionYear + "</acquisitionyear>");
		chunk.append("<assignment>" + assignment + "</assignment>");
		chunk.append("<balancecost>" + String.format("%.02f", balanceCost) + "</balancecost>");
		chunk.append("<balanceholder id=\"" + balanceHolder.getId() + "\">" + balanceHolder.getLocalizedName(ses.getLang()) + "</balanceholder>");
		chunk.append("<balanceholderbin>" + balanceHolder.getBin() + "</balanceholderbin>");
		try {
			String prevBhAsText = "";
			for (PrevBalanceHolder t : prevBalanceHolders) {
				prevBhAsText += "<entry id=\"" + t.getId() + "\">" + t.getFullXMLChunk(ses) + "</entry>";
			}
			chunk.append("<prevbalanceholders>" + prevBhAsText + "</prevbalanceholders>");
		} catch (NullPointerException e) {
			chunk.append("<prevbalanceholders></prevbalanceholders>");
		}
		chunk.append("<commissioningyear>" + commissioningYear + "</commissioningyear>");
		chunk.append("<cumulativedepreciation>" + String.format("%.02f", cumulativeDepreciation) + "</cumulativedepreciation>");
		chunk.append("<description>" + description + "</description>");
		chunk.append("<impairmentloss>" + String.format("%.02f", impairmentLoss) + "</impairmentloss>");
		chunk.append("<invnumber>" + invNumber + "</invnumber>");
		chunk.append("<kof>" + kof + "</kof>");
		chunk.append("<kuf name=\"" + kuf.name() + "\">" + kuf.getCode() + "</kuf>");
		chunk.append("<propertystatus>" + propertyStatusType + "</propertystatus>");
		chunk.append("<objectname>" + objectName + "</objectname>");
		chunk.append("<originalcost>" + String.format("%.02f", originalCost) + "</originalcost>");
		chunk.append("<propertycode id=\"" + propertyCode.getId() + "\">" + propertyCode.getLocalizedName(ses.getLang()) + "</propertycode>");
		chunk.append(
		        "<receivingreason id=\"" + receivingReason.getId() + "\">" + receivingReason.getLocalizedName(ses.getLang()) + "</receivingreason>");
		chunk.append("<isreadytouse>" + readyToUse + "</isreadytouse>");
		chunk.append("<residualcost>" + String.format("%.02f", residualCost) + "</residualcost>");
		chunk.append("<revaluationamount>" + String.format("%.02f", revaluationAmount) + "</revaluationamount>");
		chunk.append("<afterrevaluationamount>" + String.format("%.02f", afterRevaluationAmount) + "</afterrevaluationamount>");
		try {
			String tagsAsText = "";
			for (Tag t : tags) {
				tagsAsText += "<entry id=\"" + t.getId() + "\">" + t.getLocalizedName().get(ses.getLang()) + "</entry>";
			}
			chunk.append("<tags>" + tagsAsText + "</tags>");
		} catch (NullPointerException e) {
			chunk.append("<tags></tags>");
		}
		chunk.append("<yearrelease>" + yearRelease + "</yearrelease>");
		chunk.append("<techcert>" + techCert + "</techcert>");
		chunk.append("<regcert>" + regCert + "</regcert>");
		chunk.append("<decreesacts>" + decreesActs + "</decreesacts>");
		chunk.append("<notes>" + notes + "</notes>");
		if (getAttachments() != null && !attachments.isEmpty()) {
			chunk.append("<attachments>");
			for (Attachment att : attachments) {
				String downloadUrl = this.getURL() + "&amp;attachment=" + att.getId() + "&amp;fileid=" + att.getRealFileName();
				chunk.append("<attachment id=\"" + att.getId() + "\">");
				chunk.append("<url>" + downloadUrl + "</url>");
				chunk.append(att.getShortXMLChunk(ses));
				chunk.append("</attachment>");
			}
			chunk.append("</attachments>");
		}
		return chunk.toString();
	}

}
