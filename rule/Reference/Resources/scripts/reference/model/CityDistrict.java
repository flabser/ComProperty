package reference.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kz.lof.scripting._Session;

@Entity
@Table(name = "city_districts")
@NamedQuery(name = "CityDistrict.findAll", query = "SELECT m FROM CityDistrict AS m ORDER BY m.regDate")
public class CityDistrict extends Reference {

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Locality locality;

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality locality) {
		this.locality = locality;
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append(super.getFullXMLChunk(ses));
		if (locality != null) {
			chunk.append("<locality id=\"" + locality.getId() + "\">" + locality.getLocalizedName(ses.getLang()) + "</locality>");
		}
		return chunk.toString();
	}
}
