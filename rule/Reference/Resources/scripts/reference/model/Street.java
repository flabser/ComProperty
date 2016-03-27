package reference.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import kz.lof.scripting._Session;

@Entity
@Table(name = "streets", uniqueConstraints = @UniqueConstraint(columnNames = { "name", "locality_id" }))
@NamedQuery(name = "Street.findAll", query = "SELECT m FROM Street AS m ORDER BY m.regDate")
public class Street extends Reference {
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Locality locality;

	@Column(name = "street_id")
	private int streetId;

	public int getStreetId() {
		return streetId;
	}

	public void setStreetId(int streetId) {
		this.streetId = streetId;
	}

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality city) {
		this.locality = city;
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append(super.getFullXMLChunk(ses));
		chunk.append("<streetid>" + streetId + "</streetid>");
		chunk.append("<locality id=\"" + locality.getId() + "\">" + locality.getLocalizedName(ses.getLang()) + "</locality>");
		return chunk.toString();
	}
}
