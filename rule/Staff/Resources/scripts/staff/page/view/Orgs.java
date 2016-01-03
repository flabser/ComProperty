package staff.page.view;

import java.util.List;

import kz.flabs.runtimeobj.RuntimeObjUtil;
import kz.flabs.users.User;
import kz.nextbase.script._IPOJOObject;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.OrganizationDAO;

public class Orgs extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		println(formData);
		User user = session.getUser();
		int pageNum = 1;
		int pageSize = user.getSession().pageSize;
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}
		OrganizationDAO dao = new OrganizationDAO(session);
		long count = dao.getCount();
		int maxPage = RuntimeObjUtil.countMaxPage((int) count, pageSize);
		if (pageNum == 0) {
			pageNum = maxPage;
		}
		int startRec = RuntimeObjUtil.calcStartEntry(pageNum, pageSize);
		List<? extends _IPOJOObject> list = dao.findAll(startRec, pageSize);
		_POJOListWrapper<? extends _IPOJOObject> data = new _POJOListWrapper<>(list, maxPage, (int) count, pageNum);
		setContent(data);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
