package reference.init;

import java.util.ArrayList;
import java.util.List;

import kz.flabs.localization.LanguageCode;
import kz.flabs.localization.Vocabulary;
import kz.lof.dataengine.jpa.deploying.InitialDataAdapter;
import kz.lof.scripting._Session;
import reference.dao.CountryDAO;
import reference.dao.RegionDAO;
import reference.model.Country;
import reference.model.Region;
import reference.model.constants.RegionType;

/**
 * Created by Kayra on 30/12/15.
 */

public class FillRegions extends InitialDataAdapter<Region, RegionDAO> {

	@Override
	public List<Region> getData(_Session ses, LanguageCode lang, Vocabulary vocabulary) {
		List<Region> entities = new ArrayList<Region>();
		String[] data = { "Алматы", "Астана", "Алматинская", "Акмолинская", "Джамбульская", "Мангистауская", "ЮКО", "ВКО" };

		CountryDAO cDao = new CountryDAO(ses);
		Country country = cDao.findByName("Казахстан");

		for (int i = 0; i < data.length; i++) {
			Region entity = new Region();
			entity.setCountry(country);
			entity.setName(data[i]);
			if (data[i].equals("Алматы") || data[i].equals("Астана")) {
				entity.setType(RegionType.URBAN_AGGLOMERATION);
			} else {
				entity.setType(RegionType.REGION);
			}
			entities.add(entity);
		}

		String[] data1 = { "Москва", "Санкт-Петербург", "Рязаньская", "Ростовская" };
		Country country1 = cDao.findByName("Россия");

		for (int i = 0; i < data1.length; i++) {
			Region entity = new Region();
			entity.setCountry(country1);
			entity.setName(data1[i]);
			if (data1[i].equals("Москва") || data1[i].equals("Санкт-Петербург")) {
				entity.setType(RegionType.URBAN_AGGLOMERATION);
			} else {
				entity.setType(RegionType.REGION);
			}
			entities.add(entity);
		}

		String[] data2 = { "Lisbon", "Leiria" };
		Country country2 = cDao.findByName("Португалия");

		for (int i = 0; i < data2.length; i++) {
			Region entity = new Region();
			entity.setCountry(country2);
			entity.setName(data2[i]);
			if (data2[i].equals("Lisbon")) {
				entity.setType(RegionType.URBAN_AGGLOMERATION);
			} else {
				entity.setType(RegionType.REGION);
			}
			entities.add(entity);
		}

		return entities;

	}

	@Override
	public Class<RegionDAO> getDAO() {
		return RegionDAO.class;
	}

}
