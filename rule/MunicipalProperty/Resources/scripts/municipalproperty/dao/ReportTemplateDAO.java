package municipalproperty.dao;

import java.util.UUID;

import kz.lof.dataengine.jpa.DAO;
import kz.lof.scripting._Session;
import municipalproperty.model.ReportTemplate;

public class ReportTemplateDAO extends DAO<ReportTemplate, UUID> {

	public ReportTemplateDAO(_Session session) {
		super(ReportTemplate.class, session);
	}

}
