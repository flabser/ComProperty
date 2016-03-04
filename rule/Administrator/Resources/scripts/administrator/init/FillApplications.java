package administrator.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kz.flabs.localization.Vocabulary;
import kz.lof.dataengine.jpa.constants.AppCode;
import kz.lof.dataengine.jpa.deploying.InitialDataAdapter;
import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import administrator.dao.ApplicationDAO;
import administrator.model.Application;

/**
 * @author Created by Kayra on 04/03/16.
 */

// TODO it is temporary thing
public class FillApplications extends InitialDataAdapter<Application, ApplicationDAO> {

	@Override
	public List<Application> getData(_Session ses, LanguageCode lang, Vocabulary vocabulary) {
		List<Application> entities = new ArrayList<Application>();
		String[] data = { "Staff", "Reference", "Accountant", "MunicipalProperty" };
		String[] dataEng = { "Staff", "Reference", "Accountant", "Municipal property" };
		String[] dataKaz = { "Құрылым", "Анықтамалар", "Нысан импорт", "Коммуналды меншік объектілерін бақылау" };
		String[] dataRus = { "Структура", "Справочник", "Импорт объектов", "Коммунальное имущество" };
		String[] urls = { "Provider?id=structure-view", "Provider?id=country-view", "Provider?id=upload-updating-form", "Provider?id=furniture-view" };

		AppCode[] code = { AppCode.STAFF, AppCode.REFERENCE, AppCode.CUSTOM, AppCode.CUSTOM };

		for (int i = 0; i < code.length; i++) {
			Application entity = new Application();
			entity.setName(data[i].toString());
			Map<LanguageCode, String> name = new HashMap<LanguageCode, String>();
			name.put(LanguageCode.ENG, dataEng[i]);
			name.put(LanguageCode.KAZ, dataKaz[i]);
			name.put(LanguageCode.RUS, dataRus[i]);
			entity.setLocalizedName(name);
			entity.setCode(code[i]);
			entity.setDefaultURL(urls[i]);
			entity.setPosition(i + 1);
			entities.add(entity);
		}

		return entities;
	}

	@Override
	public Class<ApplicationDAO> getDAO() {
		return ApplicationDAO.class;
	}

}
