package municipalproperty.model;

import kz.flabs.util.Util;
import kz.lof.dataengine.jpa.SecureAppEntity;
import kz.lof.scripting._Session;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "orders")
@NamedQuery(name = "Order.findAll", query = "SELECT m FROM Order AS m Order BY m.regDate")
public class Order extends SecureAppEntity {

    public enum OrderStatus {INACTIVE, ACTIVE}

    @Column(name = "reg_number")
    private String regNumber;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Property property;

    @Lob
    protected byte[] attachment;

    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "order_status")
    private OrderStatus orderStatus = OrderStatus.ACTIVE;

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getShortXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append("<regdate>" + Util.simpleDateTimeFormat.format(regDate) + "</regdate>");
        if (regNumber != null) {
            chunk.append("<regnumber>" + regNumber + "</regnumber>");
        }
        if (description != null) {
            chunk.append("<description>" + description + "</description>");
        }
        chunk.append("<orderstatus>" + orderStatus + "</orderstatus>");
        if (getProperty() != null && getProperty().getId() != null) {
            chunk.append("<property>");
            chunk.append("<url>" + property.getURL() + "</url>");
            chunk.append("<objectname>" + property.getObjectName() + "</objectname>");
            chunk.append("<balanceholder>" + property.getBalanceHolder().getLocalizedName(ses.getLang()) + "</balanceholder>");
            chunk.append("<propertycode>" + property.getPropertyCode().getLocalizedName(ses.getLang()) + "</propertycode>");
            chunk.append("</property>");
        }
        return chunk.toString();
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        return getShortXMLChunk(ses);
    }
}
