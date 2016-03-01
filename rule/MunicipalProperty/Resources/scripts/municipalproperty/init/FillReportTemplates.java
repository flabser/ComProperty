package municipalproperty.init;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.localization.Vocabulary;
import kz.lof.dataengine.jpa.deploying.InitialDataAdapter;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
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
	public List<ReportTemplate> getData(_Session ses, LanguageCode lang, Vocabulary vocabulary) {
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
		entity.setName("Нематериальные активы");
		params = new ArrayList<>();
		params.add(KufType.SHARE_BLOCK);
		params.add(KufType.EQUITY);
		entity.setPropertyType(params);
		entities.add(entity);

		entity = new ReportTemplate();
		entity.setType("registry");
		entity.setName("Оборудование");
		params = new ArrayList<KufType>();
		params.add(KufType.SCHOOL_EQUIPMENT);
		params.add(KufType.COMPUTER_EQUIPMENT);
		params.add(KufType.COOK_EQUIPMENT);
		params.add(KufType.EQUIPMENT_OF_CIVIL_DEFENCE);
		params.add(KufType.OTHERS_EQUIPMENT);
		entity.setPropertyType(params);
		entities.add(entity);

		entity = new ReportTemplate();
		entity.setType("registry");
		entity.setName("Недвижимое имущество");
		params = new ArrayList<>();
		params.add(KufType.BUILDINGS);
		params.add(KufType.ROOMS);
		params.add(KufType.STRUCTURES);
		params.add(KufType.RESIDENTIAL_OBJECTS);
		params.add(KufType.LAND);
		params.add(KufType.MONUMENT);
		entity.setPropertyType(params);
		entities.add(entity);

		entity = new ReportTemplate();
		entity.setType("registry");
		entity.setName("Транспорт");
		params = new ArrayList<>();
		params.add(KufType.AUTOMOBILE);
		params.add(KufType.CAR);
		params.add(KufType.CARGO);
		params.add(KufType.DEJ_TRANSPORT);
		params.add(KufType.OFFICIAL_TRANSPORT);
		params.add(KufType.HOSPITAL_TRANSPORT);
		params.add(KufType.BUS);
		params.add(KufType.TROLLEYBUS);
		params.add(KufType.TRAM);
		params.add(KufType.TAXI);
		params.add(KufType.WATER_TRANSPORT);
		params.add(KufType.SPECIAL_EQUIPMENT);
		params.add(KufType.MOTORCYCLE);
		entity.setPropertyType(params);
		entities.add(entity);

		entity = new ReportTemplate();
		entity.setType("registry");
		entity.setName("Стратегические объекты");
		params = new ArrayList<>();
		params.add(KufType.OBJECT_RESERVED_FUND);
		params.add(KufType.BOMBPROOF);
		params.add(KufType.FACTORY);
		params.add(KufType.COMBINES);
		params.add(KufType.AIRPORT);
		params.add(KufType.TRANSITIONS);
		entity.setPropertyType(params);
		entities.add(entity);

		entity = new ReportTemplate();
		entity.setType("registry");
		entity.setName("Объекты инженерной инфраструктуры");
		params = new ArrayList<>();
		params.add(KufType.BILLBOARD);
		params.add(KufType.COLUMNS);
		params.add(KufType.ELECTRIC_NETWORKS);
		params.add(KufType.THERMAL_NETWORKS);
		params.add(KufType.GAS);
		params.add(KufType.WATER_SYSTEM);
		params.add(KufType.DRAIN);
		params.add(KufType.ROAD);
		params.add(KufType.PARKING);
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
