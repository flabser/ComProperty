package monitoring.model.common;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kz.flabs.dataengine.jpa.AppEntity;

@Entity
@Table(name = "districts")
@NamedQuery(name = "District.findAll", query = "SELECT m FROM Country AS m ORDER BY m.regDate")
public class District extends AppEntity {
	private String name;
	private List<Locality> localities;
	private Region region;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "locality")
	public List<Locality> getLocalities() {
		return localities;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
