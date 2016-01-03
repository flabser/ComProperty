package staff.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import staff.model.constants.DepartmentType;

@Entity
@Table(name = "departments")
@NamedQuery(name = "Department.findAll", query = "SELECT m FROM Department AS m ORDER BY m.regDate")
public class Department extends Staff {
	private DepartmentType type;
	private Organization organization;
	private List<Employee> employers;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	public Organization getOrganization() {
		return organization;
	}

	@OneToMany(mappedBy = "department")
	public List<Employee> getRegions() {
		return employers;
	}

	public DepartmentType getType() {
		return type;
	}

	public void setType(DepartmentType type) {
		this.type = type;
	}

}