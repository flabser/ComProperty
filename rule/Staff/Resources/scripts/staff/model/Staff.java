package staff.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import kz.flabs.dataengine.jpa.AppEntity;

@MappedSuperclass
public class Staff extends AppEntity {
	@Column(length = 128)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
