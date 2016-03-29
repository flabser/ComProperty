package municipalproperty.model;

import kz.flabs.util.Util;
import kz.lof.common.model.Attachment;
import kz.lof.dataengine.jpa.SecureAppEntity;
import kz.lof.scripting._Session;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "contracts")
@NamedQuery(name = "Contract.findAll", query = "SELECT m FROM Contract AS m ORDER BY m.regDate")
public class Contract extends SecureAppEntity {

    public enum ContractStatus {
        INACTIVE, ACTIVE
    }

    @Column(name = "reg_number")
    private String regNumber;

    @Column(name = "reg_date")
    private Date regDate;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Order order;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "contract_attachments", joinColumns = {@JoinColumn(name = "parent_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "attachment_id", referencedColumnName = "id")})
    private List<Attachment> attachments = new ArrayList<>();

    private String description = "";

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "contract_status")
    private ContractStatus contractStatus = ContractStatus.ACTIVE;

    private Date expired;

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    @Override
    public String getShortXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append("<regdate>" + Util.simpleDateTimeFormat.format(regDate) + "</regdate>");
        chunk.append("<regnumber>" + regNumber + "</regnumber>");
        chunk.append("<description>" + description + "</description>");
        chunk.append("<contractstatus>" + ses.getAppEnv().vocabulary.getWord(getContractStatus().name().toLowerCase(), ses.getLang()) + "</contractstatus>");
        chunk.append("<order docid=\"" + order.getId() + "\">");
        chunk.append("<url>" + order.getURL() + "</url>");
        chunk.append("<ordername>" + order.getDescription() + "</ordername>");
        chunk.append("</order>");

        if (!getAttachments().isEmpty()) {
            chunk.append("<attachments>" + getAttachments().size() + "</attachments>");
        }
        return chunk.toString();
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append("<regdate>" + Util.simpleDateTimeFormat.format(regDate) + "</regdate>");
        chunk.append("<regnumber>" + regNumber + "</regnumber>");
        chunk.append("<description>" + description + "</description>");
        chunk.append("<contractstatus>" + getContractStatus() + "</contractstatus>");
        chunk.append("<order docid=\"" + order.getId() + "\">");
        chunk.append("<url>" + order.getURL() + "</url>");
        chunk.append("<ordername>" + order.getDescription() + "</ordername>");
        chunk.append("</order>");

        if (!getAttachments().isEmpty()) {
            chunk.append("<attachments>" + getAttachments().stream().map(it -> it.getShortXMLChunk(ses)).collect(Collectors.joining(""))
                    + "</attachments>");
        }
        return chunk.toString();
    }
}
