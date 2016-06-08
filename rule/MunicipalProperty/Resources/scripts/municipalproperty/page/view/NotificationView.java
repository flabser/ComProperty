package municipalproperty.page.view;

import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;

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
		addContent(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum());
	}
}
