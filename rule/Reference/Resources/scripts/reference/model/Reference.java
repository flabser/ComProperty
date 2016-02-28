package reference.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import kz.flabs.localization.LanguageCode;
import kz.lof.dataengine.jpa.AppEntity;
import kz.lof.scripting._Session;

@MappedSuperclass
public class Reference extends AppEntity {
	@Column(length = 128, unique = true)
	private String name;

	@Column(name = "localized_name")
	private Map<LanguageCode, String> localizedName;

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

	public Map<LanguageCode, String> getLocalizedName() {
		return localizedName;
	}

	public void setLocalizedName(Map<LanguageCode, String> name) {
		this.localizedName = name;
	}

	@Override
	public String getShortXMLChunk(_Session ses) {
		return "<name>" + getName() + "</name>";
	}

}
