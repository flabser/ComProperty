package reference.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "localities")
@NamedQuery(name = "Locality.findAll", query = "SELECT m FROM Locality AS m ORDER BY m.regDate")
public class Locality extends Reference {
	private List<Street> streets;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private LocalityType type;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private District district;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Region region;

	public LocalityType getType() {
		return type;
	}

	public void setType(LocalityType type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "locality")
	public List<Street> getStreets() {
		return streets;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setStreets(List<Street> streets) {
		this.streets = streets;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

}
