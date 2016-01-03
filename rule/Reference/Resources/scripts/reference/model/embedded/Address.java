package reference.model.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kz.flabs.util.Util;
import reference.model.Locality;
import reference.model.Region;
import reference.model.Street;

@Embeddable
public class Address {

	@ManyToOne
	@JoinColumn
	private Region region;

	@ManyToOne
	@JoinColumn
	private Locality locality;

	@ManyToOne
	@JoinColumn
	private Street street;

	@Column(name = "house_number", length = 10)
	private String houseNumber;

	@Column(name = "additional_info")
	private String additionalInfo;

	public Locality getLocality() {
		return locality;
	}

	public void setLocality(Locality l) {
		this.locality = l;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@Override
	public String toString() {
		return Util.toStringGettersVal(this);
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public void setRegion(String r) {
		region = new Region();

	}

}