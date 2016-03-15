package staff.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.lof.scripting._Session;
import reference.model.OrgCategory;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "orgs")
@NamedQuery(name = "Organization.findAll", query = "SELECT m FROM Organization AS m ORDER BY m.regDate")
public class Organization extends Staff {

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private OrgCategory orgCategory;

    @OneToMany(mappedBy = "organization")
    private List<Department> departments;

    @OneToMany(mappedBy = "organization")
    private List<Employee> employers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "orgs_labels", joinColumns = @JoinColumn(name = "org_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "label_id", referencedColumnName = "id"))
    private List<OrganizationLabel> labels;

    @Column(length = 12)
    private String bin = "";

    private int rank = 999;

    @JsonIgnore
    public OrgCategory getOrgCategory() {
        return orgCategory;
    }

    public void setOrgCategory(OrgCategory orgCategory) {
        this.orgCategory = orgCategory;
    }

    @JsonIgnore
    public List<Department> getDepartments() {
        return departments;
    }

    @JsonIgnore
    public List<Employee> getEmployers() {
        return employers;
    }

    public void setEmployers(List<Employee> employers) {
        this.employers = employers;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public List<OrganizationLabel> getLabels() {
        return labels;
    }

    public void setLabels(List<OrganizationLabel> labels) {
        this.labels = labels;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append(super.getFullXMLChunk(ses));
        chunk.append("<bin>" + bin + "</bin>");
        if (orgCategory.getId() != null) {
            chunk.append("<orgcategory id=\"" + orgCategory.getId() + "\">" + orgCategory.getLocalizedName(ses.getLang()) + "</orgcategory>");
        }
        chunk.append("<labels>");
        for (OrganizationLabel l : labels) {
            chunk.append("<entry id=\"" + l.getId() + "\">" + l.getLocalizedName(ses.getLang()) + "</entry>");
        }
        chunk.append("</labels>");
        return chunk.toString();
    }

    @Override
    public String getShortXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append("<name>" + getName() + "</name>");
        chunk.append("<bin>" + bin + "</bin>");
        return chunk.toString();
    }
}
