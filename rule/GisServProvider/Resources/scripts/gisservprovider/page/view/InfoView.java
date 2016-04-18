package gisservprovider.page.view;

import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.scripting._POJOListWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.event._DoPage;

import municipalproperty.dao.RealEstateDAO;
import municipalproperty.model.RealEstate;

/**
 * @author Kayra created 18-04-2016
 */

public class InfoView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		int pageNum = 1;
		int pageSize = session.pageSize;
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}
		RealEstateDAO nDao = new RealEstateDAO(session);
		ViewPage<RealEstate> vp = nDao.findAllInconsistAddr(pageNum, pageSize);
		addContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), session));
	}
}
