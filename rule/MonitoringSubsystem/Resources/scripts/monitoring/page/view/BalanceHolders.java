package monitoring.page.view;

import java.util.List;

import kz.flabs.runtimeobj.RuntimeObjUtil;
import kz.flabs.users.User;
import kz.nextbase.script._IPOJOObject;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import staff.dao.OrganizationDAO;

public class BalanceHolders extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		println(formData);
		User user = session.getUser();
		int pageSize = user.getSession().pageSize;
		int pageNum = 1, maxPage = 1;
		long count = 0;
		List<? extends _IPOJOObject> list = null;
		String keyword = "";

		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}

		if (formData.containsField("keyword")) {
			keyword = formData.getEncodedValueSilently("keyword");
		}

		OrganizationDAO dao = new OrganizationDAO(session);
		if (keyword.equals("")) {
			count = dao.getCount();
			maxPage = RuntimeObjUtil.countMaxPage((int) count, pageSize);
			if (pageNum == 0) {
				pageNum = maxPage;
			}
			list = dao.findAll(RuntimeObjUtil.calcStartEntry(pageNum, pageSize), pageSize);
		} else {
			count = dao.getCountByKeyword(keyword);
			maxPage = RuntimeObjUtil.countMaxPage((int) count, pageSize);
			if (pageNum == 0) {
				pageNum = maxPage;
			}
			list = dao.findAllByKeyword(keyword, RuntimeObjUtil.calcStartEntry(pageNum, pageSize), pageSize);
		}
		_POJOListWrapper<? extends _IPOJOObject> data = new _POJOListWrapper(list, maxPage, (int) count, pageNum);
		setContent(data);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
