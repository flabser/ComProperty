package municipalproperty.init;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.dataengine.jpa.deploying.InitialDataAdapter;
import kz.flabs.localization.Language;
import kz.flabs.localization.Vocabulary;
import kz.nextbase.script._Session;
import municipalproperty.dao.ReportTemplateDAO;
import municipalproperty.model.ReportTemplate;
import municipalproperty.model.constants.KufType;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class FillReportTemplates extends InitialDataAdapter<ReportTemplate, ReportTemplateDAO> {

	@Override
	public List<ReportTemplate> getData(_Session ses, Language lang, Vocabulary vocabulary) {
		List<ReportTemplate> entities = new ArrayList<ReportTemplate>();

		ReportTemplate entity = new ReportTemplate();
		entity.setType("registry");
		entity.setName("Движимое имущество");
		List<KufType> params = new ArrayList<KufType>();
		params.add(KufType.FURNITURE);
		params.add(KufType.ANIMALS);
		params.add(KufType.SPORT_EQUIPMENT);
		params.add(KufType.OTHERS);
		entity.setPropertyType(params);
		entities.add(entity);

		entity = new ReportTemplate();
		entity.setType("registry");
		entity.setName("Оборудование");
		params = new ArrayList<KufType>();
		params.add(KufType.SCHOOL_EQUIPMENT);
		params.add(KufType.COMPUTER_EQUIPMENT);
		params.add(KufType.COOK_EQUIPMENT);
		params.add(KufType.EQUIPMENT_OF_CIVIL_DEFENSE);
		params.add(KufType.OTHERS_EQUIPMENT);
		entity.setPropertyType(params);
		entities.add(entity);

		entity = new ReportTemplate();
		entity.setType("consolidated");
		entity.setName("Консолидированный");
		entities.add(entity);

		return entities;
	}

	@Override
	public Class<ReportTemplateDAO> getDAO() {
		return ReportTemplateDAO.class;
	}

}
