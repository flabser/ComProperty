package staff.model;

import administrator.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.flabs.util.Util;
import kz.lof.dataengine.system.IEmployee;
import kz.lof.scripting._Session;
import reference.model.Position;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "employees")
@NamedQuery(name = "Employee.findAll", query = "SELECT m FROM Employee AS m ORDER BY m.regDate")
public class Employee extends Staff implements IEmployee {

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, optional = false, fetch = FetchType.EAGER)
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
        chunk.append("<iin>" + iin + "</iin>");
        if (user != null) {
            chunk.append("<reguser>on</reguser>");
            chunk.append("<email>" + user.getEmail() + "</email>");
            chunk.append("<login>" + user.getLogin() + "</login>");
        }
        if (birthDate != null) {
            chunk.append("<birthdate>" + Util.simpleDateTimeFormat.format(birthDate) + "</birthdate>");
        }
        if (organization != null && organization.getId() != null) {
            chunk.append("<organization id=\"" + organization.getId() + "\">" + organization.getLocalizedName(ses.getLang()) + "</organization>");
        }
        if (department != null && department.getId() != null) {
            chunk.append("<department id=\"" + department.getId() + "\">" + department.getLocalizedName(ses.getLang()) + "</department>");
        }
        if (position != null && position.getId() != null) {
            chunk.append("<position id=\"" + position.getId() + "\">" + position.getLocalizedName(ses.getLang()) + "</position>");
        }
        chunk.append("<roles>");
        for (Role l : roles) {
            chunk.append("<entry id=\"" + l.getId() + "\">" + l.getLocalizedName(ses.getLang()) + "</entry>");
        }
        chunk.append("</roles>");
        return chunk.toString();
    }
}
