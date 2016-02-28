package reference.page.action;

import java.util.List;

import kz.flabs.localization.LanguageCode;
import kz.flabs.runtimeobj.RuntimeObjUtil;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.events._DoPage;
import reference.dao.CountryDAO;
import reference.model.Country;
import reference.model.Region;
import reference.model.constants.CountryCode;

/**
 * @author Kayra created 17-02-2016
 */

public class GetRegionsAction extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData, LanguageCode lang) {
		int pageNum = formData.getNumberValueSilently("page", 1);
		int pageSize = ses.pageSize;

		CountryDAO cDao = new CountryDAO(ses);
		Country country = cDao.findByCode(CountryCode.KZ);

		if (country != null) {
			List<Region> list = country.getRegions();
			long count = list.size();
			int maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
			if (pageNum == 0) {
				pageNum = maxPage;
			}
			ViewPage<Region> vp = new ViewPage(list, count, maxPage, pageNum);
			addContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), ses));
		} else {
			setValidation(getLocalizedWord("country_has_not_found", lang));
		}
	}
}
