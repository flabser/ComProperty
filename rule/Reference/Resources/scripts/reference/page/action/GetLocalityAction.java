package reference.page.action;

import java.util.List;

import kz.flabs.runtimeobj.RuntimeObjUtil;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import reference.dao.LocalityDAO;
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

		LocalityDAO lDao = new LocalityDAO(ses);
		List<Locality> localities = lDao.findAll();

		long count = localities.size();
		int maxPage = RuntimeObjUtil.countMaxPage(count, pageSize);
		if (pageNum == 0) {
			pageNum = maxPage;
		}
		ViewPage<Region> vp = new ViewPage(localities, count, maxPage, pageNum);
		addContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), ses));

	}
}
