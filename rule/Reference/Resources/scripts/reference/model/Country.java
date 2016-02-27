package reference.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import reference.model.constants.CountryCode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

@Entity
@Table(name = "countries")
@NamedQuery(name = "Country.findAll", query = "SELECT m FROM Country AS m ORDER BY m.regDate")
@JsonIgnoreType
public class Country extends Reference {
	@JsonIgnore
	@OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
	private List<Region> regions;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 7, unique = true)
	private CountryCode code = CountryCode.UNKNOWN;

	public CountryCode getCode() {
		return code;
	}

	public void setCode(CountryCode code) {
		this.code = code;
	}

	@JsonIgnore
	public List<Region> getRegions() {
		return regions;
	}

	@JsonIgnore
	public void setRegions(List<Region> regions) {
		this.regions = regions;
	}

}
