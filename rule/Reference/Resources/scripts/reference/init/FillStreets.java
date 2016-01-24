package reference.init;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.dataengine.jpa.deploying.InitialDataAdapter;
import kz.flabs.localization.Language;
import kz.flabs.localization.Vocabulary;
import kz.nextbase.script._Session;
import reference.dao.LocalityDAO;
import reference.dao.StreetDAO;
import reference.model.Locality;
import reference.model.Street;

/**
 * Created by Kayra on 24/01/16.
 */

public class FillStreets extends InitialDataAdapter<Street, StreetDAO> {

	@Override
	public List<Street> getData(_Session ses, Language lang, Vocabulary vocabulary) {

		List<Street> entities = new ArrayList<Street>();
		String[] data = { "Unknown", "Достык", "Толе Би", "Фурманова" };

		LocalityDAO cDao = new LocalityDAO(ses);
		Locality d = cDao.findByName("Алматы");
		if (d != null) {
			for (String val : data) {
				Street entity = new Street();
				entity.setLocality(d);
				entity.setName(val);
				entities.add(entity);
			}
		}
		return entities;

	}

	@Override
	public Class<StreetDAO> getDAO() {
		return StreetDAO.class;
	}

}
