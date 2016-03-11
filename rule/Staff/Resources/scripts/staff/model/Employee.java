package staff.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kz.flabs.util.Util;
import kz.lof.dataengine.system.IEmployee;
import kz.lof.scripting._Session;
import reference.model.Position;
import administrator.model.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "employees")
@NamedQuery(name = "Employee.findAll", query = "SELECT m FROM Employee AS m ORDER BY m.regDate")
public class Employee extends Staff implements IEmployee {

	@OneToOne(cascade = CascadeType.MERGE, optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = true)
	private User user;

	@Column(name = "birth_date")
	private String birthDate;

	private String iin = "";

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Organization organization;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Department department;

	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Position position;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "employee_role")
	private List<Role> roles;

	@Lob
	protected byte[] avatar;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getIin() {
		return iin;
	}

	public void setIin(String iin) {
		this.iin = iin;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@JsonIgnore
	public User getUser() {
		return user;

	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void addRole(Role r) {
		if (roles == null) {
			roles = new ArrayList<Role>();
		}
		roles.add(r);
	}

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<regdate>" + Util.simpleDateTimeFormat.format(regDate) + "</regdate>");
		chunk.append("<name>" + getName() + "</name>");
		if (user != null) {
			chunk.append("<reguser>on</reguser>");
			chunk.append("<email>" + user.getEmail() + "</email>");
			chunk.append("<login>" + user.getLogin() + "</login>");
		} else {
			chunk.append("<reguser></reguser><email></email><login></login>");
		}
		if (birthDate != null) {
			chunk.append("<birthdate>" + Util.simpleDateTimeFormat.format(birthDate) + "</birthdate>");
		} else {
			chunk.append("<birthdate></birthdate>");
		}
		if (department != null) {
			chunk.append("<department>" + department + "</department>");
			chunk.append("<departmentid>" + department.getIdentifier() + "</departmentid>");
		} else {
			chunk.append("<department></department><departmentid></departmentid>");
		}
		chunk.append("<iin>" + iin + "</iin>");
		chunk.append("<organization>" + organization + "</organization>");
		chunk.append("<organizationid>" + organization.getId() + "</organizationid>");
		chunk.append("<position>" + position + "</position>");
		chunk.append("<positionid>" + position.getId() + "</positionid>");
		chunk.append("<roles>");
		for (Role l : roles) {
			chunk.append("<entry id=\"" + l.getId() + "\">" + l.getLocalizedName(ses.getLang()) + "</entry>");
		}
		chunk.append("</roles>");
		return chunk.toString();
	}
}
