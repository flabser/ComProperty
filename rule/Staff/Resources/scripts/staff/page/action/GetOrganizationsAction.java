package staff.page.action;

import kz.lof.dataengine.jpa.ViewPage;
import kz.flabs.localization.LanguageType;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.OrganizationDAO;
import staff.model.Organization;

/**
 * 
 * 
 * @author Kayra created 09-01-2016
 */

public class GetOrganizationsAction extends _DoPage {

	@Override
	public void doGET(_Session ses, _WebFormData formData, LanguageType lang) {
		String keyword = formData.getEncodedValueSilently("keyword");
		int pageNum = 1;
		// int pageSize = ses.pageSize;
		int pageSize = 100;
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}
		OrganizationDAO dao = new OrganizationDAO(ses);
		ViewPage<Organization> vp = dao.findAllByKeyword(keyword, pageNum, pageSize);
		setContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), lang));
		// setPublishAsType(PublishAsType.JSON);
	}

	@Override
	public void doPOST(_Session session, _WebFormData webFormData, LanguageType lang) {

	}

}
