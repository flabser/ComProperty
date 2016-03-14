package staff.model;

import administrator.dao.LanguageDAO;
import administrator.model.Language;
import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.flabs.util.Util;
import kz.lof.scripting._Session;
import staff.model.constants.DepartmentType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
@Table(name = "departments")
@NamedQuery(name = "Department.findAll", query = "SELECT m FROM Department AS m ORDER BY m.regDate")
public class Department extends Staff {
    private DepartmentType type;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private Organization organization;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @JsonIgnore
    public List<Employee> getEmployees() {
        return employees;
    }

    public DepartmentType getType() {
        return type;
    }

    public void setType(DepartmentType type) {
        this.type = type;
    }

    @Override
    public String getFullXMLChunk(_Session ses) {
        StringBuilder chunk = new StringBuilder(1000);
        chunk.append("<regdate>" + Util.simpleDateFormat.format(regDate) + "</regdate>");
        chunk.append("<name>" + getName() + "</name>");
        chunk.append("<type>" + getType() + "</type>");
        if (organization != null) {
            chunk.append("<organizationid>" + organization.getId() + "</organizationid>");
        }
        chunk.append("<localizednames>");
        LanguageDAO lDao = new LanguageDAO(ses);
        List<Language> list = lDao.findAll();
        for (Language l : list) {
            chunk.append("<entry id=\"" + l.getCode() + "\">" + getLocalizedName(l.getCode()) + "</entry>");
        }
        chunk.append("</localizednames>");
        return chunk.toString();
    }
}
