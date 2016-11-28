package municipalproperty.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.AppEntity;
import com.exponentus.scripting._Session;
import com.exponentus.server.Server;

import reference.dao.KufDAO;
import reference.model.Kuf;
import reference.model.constants.KufType;

/**
 *
 *
 * @author Kayra created 06-01-2016
 */

@Entity
@Table(name = "reports_template")
@NamedQuery(name = "ReportTemplate.findAll", query = "SELECT m FROM ReportTemplate AS m ORDER BY m.regDate")
public class ReportTemplate extends AppEntity<UUID> {

	@Column(length = 32)
	private String type;

	@Column(name = "property_type")
	private List<KufType> propertyType;

	@Column(length = 128)
	private String name;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<KufType> getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(List<KufType> propertyType) {
		this.propertyType = propertyType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDefaultFormName() {
		return "report-template-form";
	}

	@Override
	public String getURL() {
		return "Provider?id=report-template-form&amp;docid=" + getId();
	}

	@Override
	public String getFullXMLChunk(_Session ses) {
		StringBuilder value = new StringBuilder(1000);
		value.append("<name>" + name + "</name>");
		try {
			KufDAO kDao = new KufDAO(ses);
			
			String enumValue = "";
			
			if (propertyType != null) {
				for (KufType val : propertyType) {
					Kuf kuf = kDao.findByCode(val);
					if (kuf != null) {
						enumValue += "<entry id=\"" + val + "\">" + kuf.getLocalizedName().get(ses.getLang())
								+ "</entry>";
					} else {
						enumValue += "<entry id=\"" + val + "\">" + val + "</entry>";
					}
				}
			}
			value.append("<propertytype>" + enumValue + "</propertytype>");
		} catch (DAOException e) {
			Server.logger.errorLogEntry(e);
		}
		
		value.append("<type>" + type + "</type>");
		return value.toString();
	}
}
