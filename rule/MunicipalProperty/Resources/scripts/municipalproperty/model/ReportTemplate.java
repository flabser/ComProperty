package municipalproperty.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import kz.flabs.dataengine.jpa.AppEntity;
import kz.nextbase.script._URL;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

@Entity
@Table(name = "reports_template")
@NamedQuery(name = "ReportTemplate.findAll", query = "SELECT m FROM ReportTemplate AS m ORDER BY m.regDate")
public class ReportTemplate extends AppEntity {
	@Column(length = 32)
	private String type;
	@Column(name = "property_type")
	private List<KufType> propertyType;
	@Column(length = 128)
	private String name;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<KufType> getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(List<KufType> propertyType) {
		this.propertyType = propertyType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public _URL getURL() {
		return new _URL("Provider?id=report-template-form&amp;docid=" + getId());
	}

}
