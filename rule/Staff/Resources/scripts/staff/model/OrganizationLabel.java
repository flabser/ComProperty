package staff.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.lof.scripting._Session;

@Entity
@Table(name = "org_labels")
@NamedQuery(name = "OrganizationLabel.findAll", query = "SELECT m FROM OrganizationLabel AS m ORDER BY m.regDate")
public class OrganizationLabel extends Staff {

	@ManyToMany(mappedBy = "labels")
	private List<Organization> labels;

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public List<Organization> getLabels() {
		return labels;
	}

	@Override
	public String getShortXMLChunk(_Session ses) {
		return "<name>" + getLocalizedName(ses.getLang()) + "</name><description>" + description + "</description>";
	}

	@Override
	public String getURL() {
		return "Provider?id=organization-label-form&amp;docid=" + getId();
	}

}
