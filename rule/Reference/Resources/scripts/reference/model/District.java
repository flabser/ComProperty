package reference.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kz.lof.scripting._Session;

@Entity
@Table(name = "districts")
@NamedQuery(name = "District.findAll", query = "SELECT m FROM District AS m ORDER BY m.regDate")
public class District extends Reference {
	private List<Locality> localities;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Region region;

	@OneToMany(mappedBy = "district")
	public List<Locality> getLocalities() {
		return localities;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append(super.getFullXMLChunk(ses));
		chunk.append("<region>" + region.getId() + "</region>");
		return chunk.toString();
	}

}
