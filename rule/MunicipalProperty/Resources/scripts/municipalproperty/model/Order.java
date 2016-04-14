package municipalproperty.model;

import kz.flabs.util.Util;
import com.exponentus.common.model.Attachment;
import com.exponentus.dataengine.jpa.SecureAppEntity;
import com.exponentus.scripting._Session;
import municipalproperty.dao.ContractDAO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.findAll", query = "SELECT m FROM Order AS m ORDER BY m.regDate")
public class Order extends SecureAppEntity {

    public enum OrderStatus {
        INACTIVE, ACTIVE
    }

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "applied_reg_date")
    private Date appliedRegDate;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Property property;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "order_attachments", joinColumns = {@JoinColumn(name = "parent_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "attachment_id", referencedColumnName = "id")})
    private List<Attachment> attachments = new ArrayList<>();

    private String description = "";

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_status")
    private OrderStatus orderStatus = OrderStatus.ACTIVE;

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Date getAppliedRegDate() {
        return appliedRegDate;
    }

    public void setAppliedRegDate(Date appliedRegDate) {
        this.appliedRegDate = appliedRegDate;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String getShortXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append("<regdate>" + Util.convertDateToStringSilently(regDate) + "</regdate>");
        chunk.append("<regnumber>" + regNumber + "</regnumber>");
        chunk.append("<appliedregdate>" + Util.convertDateToStringSilently(appliedRegDate) + "</appliedregdate>");
        chunk.append("<description>" + description + "</description>");
        chunk.append("<orderstatus>" + ses.getAppEnv().vocabulary.getWord(getOrderStatus().name().toLowerCase(), ses.getLang()) + "</orderstatus>");
        chunk.append("<property docid=\"" + property.getId() + "\">");
        chunk.append("<url>" + property.getURL() + "</url>");
        chunk.append("<objectname>" + property.getObjectName() + "</objectname>");
        chunk.append("<balanceholder>" + property.getBalanceHolder().getLocalizedName(ses.getLang()) + "</balanceholder>");
        chunk.append("<propertycode>" + property.getPropertyCode().getLocalizedName(ses.getLang()) + "</propertycode>");
        chunk.append("</property>");

        if (!getAttachments().isEmpty()) {
            chunk.append("<attachments>" + getAttachments().size() + "</attachments>");
        }
        return chunk.toString();
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append("<regdate>" + Util.convertDateToStringSilently(regDate) + "</regdate>");
        chunk.append("<regnumber>" + regNumber + "</regnumber>");
        chunk.append("<appliedregdate>" + Util.convertDateToStringSilently(appliedRegDate) + "</appliedregdate>");
        chunk.append("<description>" + description + "</description>");
        chunk.append("<orderstatus>" + getOrderStatus() + "</orderstatus>");
        chunk.append("<property docid=\"" + property.getId() + "\">");
        chunk.append("<url>" + property.getURL() + "</url>");
        chunk.append("<objectname>" + property.getObjectName() + "</objectname>");
        chunk.append("<balanceholder>" + property.getBalanceHolder().getLocalizedName(ses.getLang()) + "</balanceholder>");
        chunk.append("<propertycode>" + property.getPropertyCode().getLocalizedName(ses.getLang()) + "</propertycode>");
        chunk.append("</property>");

        if (getAttachments() != null && !attachments.isEmpty()) {
            chunk.append("<attachments>");
            for (Attachment att : attachments) {
                String downloadUrl = this.getURL() + "&amp;attachment=" + att.getId() + "&amp;att-name=" + att.getRealFileName();
                chunk.append("<attachment id=\"" + att.getId() + "\">");
                chunk.append("<url>" + downloadUrl + "</url>");
                chunk.append(att.getShortXMLChunk(ses));
                chunk.append("</attachment>");
            }
            chunk.append("</attachments>");
        }

        ContractDAO contractDAO = new ContractDAO(ses);
        if (this.getId() != null) {
            List<Contract> contracts = contractDAO.findAllContractsByOrder(this);
            if (!contracts.isEmpty()) {
                chunk.append("<contracts>");
                for (Contract contract : contracts) {
                    chunk.append("<contract>");
                    chunk.append("<url>" + contract.getURL() + "</url>");
                    chunk.append("<description>" + contract.getDescription() + "</description>");
                    chunk.append("</contract>");
                }
                chunk.append("</contracts>");
            }
        }

        return chunk.toString();
    }
}
