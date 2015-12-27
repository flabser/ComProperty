package reference.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import kz.flabs.dataengine.jpa.AppEntity;

@MappedSuperclass
public class Reference extends AppEntity {
	@Column(length = 64, unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
