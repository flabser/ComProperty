package staff.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import kz.flabs.dataengine.DatabaseFactory;
import kz.flabs.dataengine.ISystemDatabase;
import kz.flabs.localization.LanguageCode;
import kz.flabs.users.User;
import kz.flabs.util.Util;
import kz.lof.dataengine.jpa.AppEntity;
import kz.lof.scripting._Session;
import administrator.dao.LanguageDAO;
import administrator.model.Language;

@MappedSuperclass
public class Staff extends AppEntity {
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

	public String getLocalizedName(LanguageCode lang) {
		try {
			return localizedName.get(lang);
		} catch (Exception e) {
			return name;
		}
	}

	public void setLocalizedName(Map<LanguageCode, String> name) {
		this.localizedName = name;
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder chunk = new StringBuilder(1000);
		chunk.append("<regdate>" + Util.simpleDateFormat.format(regDate) + "</regdate>");
		ISystemDatabase sysDb = DatabaseFactory.getSysDatabase();
		User user = sysDb.getUser(author);
		chunk.append("<author>" + user.getUserID() + "</author>");
		chunk.append("<name>" + getName() + "</name>");
		// TODO it should be initialized anyway
		if (localizedName != null) {
			chunk.append("<localizednames>");
			LanguageDAO lDao = new LanguageDAO(ses);
			List<Language> list = lDao.findAll();
			for (Language l : list) {
				String name = localizedName.get(l.getCode());
				if (name != null) {
					chunk.append("<entry id=\"" + l.getCode() + "\">" + name + "</entry>");
				}
			}
			chunk.append("</localizednames>");
		}
		return chunk.toString();
	}

	@Override
	public String getShortXMLChunk(_Session ses) {
		return "<name>" + getLocalizedName(ses.getLang()) + "</name>";
	}
}
