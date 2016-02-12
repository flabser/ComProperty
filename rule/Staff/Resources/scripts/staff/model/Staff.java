package staff.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import kz.lof.dataengine.jpa.AppEntity;

import com.google.gson.annotations.Expose;

@MappedSuperclass
public class Staff extends AppEntity {
	@Expose
	@Column(length = 128, unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}
