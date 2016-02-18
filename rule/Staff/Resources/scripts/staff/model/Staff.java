package staff.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import kz.flabs.localization.LanguageType;
import kz.lof.dataengine.jpa.AppEntity;

import com.google.gson.annotations.Expose;

@MappedSuperclass
public class Staff extends AppEntity {
	@Expose
	@Column(length = 128, unique = true)
	private String name;

	@Column(name = "localized_name")
	private Map<LanguageType, String> localizedName;

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

	public Map<LanguageType, String> getLocalizedName() {
		return localizedName;
	}

	public void setLocalizedName(Map<LanguageType, String> name) {
		this.localizedName = name;
	}
}
