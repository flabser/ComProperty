package staff.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kz.nextbase.script._IPOJOObject;
import kz.nextbase.script._URL;

@Entity
@Table(name = "orgs")
@NamedQuery(name = "Organization.findAll", query = "SELECT m FROM Organization AS m ORDER BY m.regDate")
public class Organization<T extends _IPOJOObject> extends Staff {
	@OneToMany(mappedBy = "organization")
	private List<Department> departments;

	@OneToMany(mappedBy = "organization")
	private List<Employer> employers;

	@Column(name = "is_primary")
	private boolean isPrimary;

	public List<Department> getDepartments() {
		return departments;
	}

	public boolean isPrimary() {
		return isPrimary;
	}

	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}

	public List<Employer> getEmployers() {
		return employers;
	}

	public void setEmployers(List<Employer> employers) {
		this.employers = employers;
	}

	@Override
	public _URL getURL() {
		return new _URL("Provider?id=organization_form&amp;docid=" + getId());
	}

}
