package administrator.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import kz.flabs.util.Util;
import kz.lof.dataengine.jpa.AppEntity;
import kz.lof.dataengine.jpa.constants.AppCode;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;

@Entity
@Table(name = "_apps")
@NamedQuery(name = "Application.findAll", query = "SELECT m FROM Application AS m ORDER BY m.regDate")
public class Application extends AppEntity {
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 16)
	private AppCode code = AppCode.UNKNOWN;

	@Column(length = 128, unique = true)
	private String name;

	private List<AppCode> dependencies;

	@Column(name = "localized_name")
	private Map<LanguageCode, String> localizedName;

	private String defaultURL;

	private int position;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AppCode> getDependencies() {
		return dependencies;
	}

	public void setDependencies(List<AppCode> dependencies) {
		this.dependencies = dependencies;
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

	public String getDefaultURL() {
		return defaultURL;
	}

	public void setDefaultURL(String defaultURL) {
		this.defaultURL = defaultURL;
	}

	public AppCode getCode() {
		return code;
	}

	public void setCode(AppCode code) {
		this.code = code;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String getShortXMLChunk(_Session ses) {
		return "<app id=\"" + name + "\">" + localizedName.get(ses.getLang()) + "</app>" + "<pos>" + position + "</pos><url>"
		        + Util.getAsTagValue(defaultURL) + "</url>";
	}

}
