package reference.page.action;

import java.util.List;

import kz.flabs.runtimeobj.RuntimeObjUtil;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import reference.dao.DistrictDAO;
import reference.model.District;
import reference.model.Locality;
import reference.model.Region;

/**
 * @author Kayra created 02-03-2016
 */

public class GetLocalityAction extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData) {
		int pageNum = formData.getNumberValueSilently("page", 1);
		int pageSize = ses.pageSize;

		DistrictDAO dDao = new DistrictDAO(ses);
		District district = dDao.findById(formData.getValueSilently("districtid"));
		if (district != null) {
			List<Locality> list = district.getLocalities();
			long count = list.size();
			int maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
			if (pageNum == 0) {
				pageNum = maxPage;
			}
			ViewPage<Region> vp = new ViewPage(list, count, maxPage, pageNum);
			addContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), ses));
		} else {
			setValidation(getLocalizedWord("district_has_not_found", ses.getLang()));
		}

	}
}
