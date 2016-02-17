package reference.page.action;

import java.util.List;

import kz.flabs.localization.LanguageType;
import kz.flabs.runtimeobj.RuntimeObjUtil;
import kz.flabs.servlets.PublishAsType;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._POJOListWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import reference.dao.CountryDAO;
import reference.model.Country;
import reference.model.Region;

/**
 * 
 * 
 * @author Kayra created 17-02-2016
 */

public class GetRegionsAction extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData, LanguageType lang) {
		String parentId = formData.getValueSilently("countryid");
		int pageNum = 1;
		int pageSize = ses.pageSize;
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}
		CountryDAO cDao = new CountryDAO(ses);
		Country country = cDao.findById(parentId);
		if (country != null) {
			List<Region> list = country.getRegions();
			long count = list.size();
			int maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
			if (pageNum == 0) {
				pageNum = maxPage;
			}
			ViewPage<Region> vp = new ViewPage(list, count, maxPage, pageNum);
			setContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), lang));
		} else {
			setValidation(getLocalizedWord("country_has_not_found", lang));
		}
		setPublishAsType(PublishAsType.JSON);
	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData, LanguageType lang) {

	}

}
