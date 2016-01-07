package reference.init;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.dataengine.jpa.deploying.InitialDataAdapter;
import kz.flabs.localization.Language;
import kz.flabs.localization.Vocabulary;
import kz.nextbase.script._Session;
import reference.dao.DistrictDAO;
import reference.dao.LocalityDAO;
import reference.model.District;
import reference.model.Locality;
import reference.model.constants.LocalityType;

/**
 * Created by Kayra on 30/12/15.
 */

public class FillLocalities extends InitialDataAdapter<Locality, LocalityDAO> {

	@Override
	public List<Locality> getData(_Session ses, Language lang, Vocabulary vocabulary) {

		List<Locality> entities = new ArrayList<Locality>();
		String[] data = { "Талды-Курган" };

		DistrictDAO cDao = new DistrictDAO(ses);
		District d = cDao.findByName("Алматинская");
		if (d != null) {

			for (int i = 0; i < data.length; i++) {
				Locality entity = new Locality();
				entity.setDistrict(d);
				entity.setName(data[i]);
				entity.setType(LocalityType.CITY);
				entities.add(entity);
			}
		}
		return entities;

	}

	@Override
	public Class<LocalityDAO> getDAO() {
		return LocalityDAO.class;
	}

}
