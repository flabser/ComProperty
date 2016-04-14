package municipalproperty.dao;

import java.util.UUID;

import com.exponentus.dataengine.jpa.DAO;
import com.exponentus.scripting._Session;
import municipalproperty.model.ReportTemplate;

public class ReportTemplateDAO extends DAO<ReportTemplate, UUID> {

	public ReportTemplateDAO(_Session session) {
		super(ReportTemplate.class, session);
	}

}
