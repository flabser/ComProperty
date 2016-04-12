package municipalproperty.page.view;

import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import municipalproperty.dao.NotificationDAO;
import municipalproperty.model.Notification;
import municipalproperty.model.constants.NotificationType;

public class NotificationView extends AbstractMunicipalPropertyView {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		int pageNum = 1;
		int pageSize = session.pageSize;
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}
		String cat = formData.getValueSilently("type");
		NotificationDAO nDao = new NotificationDAO(session);
		ViewPage<Notification> vp = nDao.findAllequal("type", NotificationType.valueOf(cat), pageNum, pageSize);
		addContent(new _POJOListWrapper(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum(), session));
	}
}
