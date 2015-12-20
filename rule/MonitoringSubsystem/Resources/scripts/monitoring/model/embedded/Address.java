package monitoring.model.embedded;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import kz.flabs.util.Util;
import monitoring.model.common.Locality;
import monitoring.model.common.Street;

@Embeddable
public class Address {

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "locality_id", nullable = false)
	private Locality locality;

	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(name = "street_id", nullable = false)
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

}
