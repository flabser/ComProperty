package municipalproperty.model;

import kz.flabs.util.Util;
import kz.lof.dataengine.jpa.SecureAppEntity;
import kz.lof.scripting._Session;
import kz.lof.util.NumberUtil;
import municipalproperty.model.constants.KufType;
import reference.model.PropertyCode;
import reference.model.ReceivingReason;
import reference.model.Tag;
import staff.dao.EmployeeDAO;
import staff.model.Employee;
import staff.model.Organization;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "properties", uniqueConstraints = @UniqueConstraint(columnNames = {"inv_number", "object_name"}))
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

    @Lob
    protected byte[] attachment;

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

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
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
        chunk.append("<originalcost>" + NumberUtil.formatFloat(originalCost) + "</originalcost>");
        chunk.append("<balanceholder>" + balanceHolder + "</balanceholder>");
        chunk.append("<invnumber>" + invNumber + "</invnumber>");
        chunk.append("<objectname>" + objectName + "</objectname>");
        return chunk.toString();
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append("<regdate>" + Util.simpleDateTimeFormat.format(regDate) + "</regdate>");
        EmployeeDAO eDao = new EmployeeDAO(ses);
        Employee user = eDao.findByUserId(author);
        if (user != null) {
            chunk.append("<author>" + user.getName() + "</author>");
        } else {
            chunk.append("<author></author>");
        }
        try {
            chunk.append("<acceptancedate>" + Util.simpleDateFormat.format(acceptanceDate) + "</acceptancedate>");
        } catch (NullPointerException e) {
            chunk.append("<acceptancedate></acceptancedate>");
        }
        chunk.append("<acquisitionyear>" + acquisitionYear + "</acquisitionyear>");
        chunk.append("<assignment>" + assignment + "</assignment>");
        chunk.append("<balancecost>" + String.format("%.02f", balanceCost) + "</balancecost>");

        if (balanceHolder.getId() != null) {
            chunk.append("<balanceholder id=\"" + balanceHolder.getId() + "\">" + balanceHolder.getLocalizedName(ses.getLang()) + "</balanceholder>");
            chunk.append("<balanceholderbin>" + balanceHolder.getBin() + "</balanceholderbin>");
        }

        chunk.append("<commissioningyear>" + commissioningYear + "</commissioningyear>");
        chunk.append("<cumulativedepreciation>" + String.format("%.02f", cumulativeDepreciation) + "</cumulativedepreciation>");
        chunk.append("<description>" + description + "</description>");
        chunk.append("<impairmentloss>" + String.format("%.02f", impairmentLoss) + "</impairmentloss>");
        chunk.append("<invnumber>" + invNumber + "</invnumber>");
        chunk.append("<kof>" + kof + "</kof>");
        try {
            chunk.append("<kuf>" + kuf.getCode() + "</kuf>");
        } catch (NullPointerException e) {
            chunk.append("<kuf></kuf>");
        }
        chunk.append("<objectname>" + objectName + "</objectname>");
        chunk.append("<originalcost>" + String.format("%.02f", originalCost) + "</originalcost>");

        if (propertyCode.getId() != null) {
            chunk.append("<propertycode id=\"" + propertyCode.getId() + "\">" + propertyCode.getLocalizedName(ses.getLang()) + "</propertycode>");
        }
        if (receivingReason.getId() != null) {
            chunk.append("<receivingreason id=\"" + receivingReason.getId() + "\">" + receivingReason.getLocalizedName(ses.getLang()) + "</receivingreason>");
        }

        chunk.append("<isreadytouse>" + readyToUse + "</isreadytouse>");
        chunk.append("<residualcost>" + String.format("%.02f", residualCost) + "</residualcost>");
        chunk.append("<revaluationamount>" + String.format("%.02f", revaluationAmount) + "</revaluationamount>");
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
        chunk.append("<notes>" + notes + "</notes>");
        return chunk.toString();
    }
}
