package staff.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kz.flabs.localization.LanguageType;
import reference.model.OrgCategory;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@Column(name = "is_primary")
	private boolean isPrimary;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "org_org_label")
	private List<OrganizationLabel> labels;

	@Column(length = 12)
	private String bin;

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

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
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

	@Override
	public String getShortXMLChunk(LanguageType lang) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<name>" + getName() + "</name>");
		chunk.append("<bin>" + bin + "</bin>");
		return chunk.toString();
	}

}
