package staff.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kz.nextbase.script._URL;

@Entity
@Table(name = "employers")
@NamedQuery(name = "Employer.findAll", query = "SELECT m FROM Employer AS m ORDER BY m.regDate")
public class Employer extends Staff {
	@Column(nullable = false, length = 32)
	private String login;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Organization organization;

	private Department department;

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Organization getOrganization() {
		return organization;
	}

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	public Department getDepartment() {
		return department;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public _URL getURL() {
		return new _URL("Provider?id=employer_form&amp;docid=" + getId());
	}

}
