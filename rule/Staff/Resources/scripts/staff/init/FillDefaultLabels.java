package staff.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kz.flabs.localization.LanguageType;
import kz.flabs.localization.Vocabulary;
import kz.lof.dataengine.jpa.deploying.InitialDataAdapter;
import kz.lof.scripting._Session;
import staff.dao.OrganizationLabelDAO;
import staff.model.OrganizationLabel;

/**
 * 
 * 
 * @author Kayra created 09-01-2016
 */

public class FillDefaultLabels extends InitialDataAdapter<OrganizationLabel, OrganizationLabelDAO> {

	@Override
	public List<OrganizationLabel> getData(_Session ses, LanguageType lang, Vocabulary vocabulary) {
		List<OrganizationLabel> entities = new ArrayList<OrganizationLabel>();

		OrganizationLabel entity = new OrganizationLabel();
		entity.setName("inactive");
		Map<LanguageType, String> name = new HashMap<LanguageType, String>();
		name.put(LanguageType.ENG, "Inactive organization");
		name.put(LanguageType.RUS, "Не действующая организация");
		entity.setLocalizedName(name);
		entity.setDescription("Inactive organization");
		entities.add(entity);

		entity = new OrganizationLabel();
		entity.setName("primary");
		name = new HashMap<LanguageType, String>();
		name.put(LanguageType.ENG, "Primary organization");
		name.put(LanguageType.RUS, "Первичная организация");
		entity.setDescription("primary organization");
		entities.add(entity);

		entity = new OrganizationLabel();
		entity.setName("branch");
		name = new HashMap<LanguageType, String>();
		name.put(LanguageType.ENG, "Branch");
		name.put(LanguageType.RUS, "Филиал");
		entity.setDescription("organization is a branch of the primary organization");
		entities.add(entity);

		/* ComProperty application specific labels */
		entity = new OrganizationLabel();
		entity.setName("balance_holder");
		name = new HashMap<LanguageType, String>();
		name.put(LanguageType.ENG, "Balance holder");
		name.put(LanguageType.RUS, "Организация-балансодержатель");
		entity.setDescription("Организация-балансодержатель");
		entities.add(entity);

		return entities;
	}

	@Override
	public Class<OrganizationLabelDAO> getDAO() {
		return OrganizationLabelDAO.class;
	}

}
