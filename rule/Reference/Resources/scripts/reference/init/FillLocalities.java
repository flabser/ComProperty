package reference.init;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.localization.Vocabulary;
import kz.lof.dataengine.jpa.deploying.InitialDataAdapter;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import reference.dao.LocalityDAO;
import reference.dao.LocalityTypeDAO;
import reference.dao.RegionDAO;
import reference.model.Locality;
import reference.model.Region;
import reference.model.constants.LocalityCode;

/**
 * Created by Kayra on 30/12/15.
 */

public class FillLocalities extends InitialDataAdapter<Locality, LocalityDAO> {

	@Override
	public List<Locality> getData(_Session ses, LanguageCode lang, Vocabulary vocabulary) {

		List<Locality> entities = new ArrayList<Locality>();
		String[] data = { "Алматы", "Капчагай", "Талды-Курган" };

		RegionDAO cDao = new RegionDAO(ses);
		Region d = cDao.findByName("Алматинская");
		if (d != null) {
			for (String val : data) {
				Locality entity = new Locality();
				entity.setRegion(d);
				entity.setName(val);
				LocalityTypeDAO ltDao = new LocalityTypeDAO(ses);
				entity.setType(ltDao.findByCode(LocalityCode.CITY));
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
