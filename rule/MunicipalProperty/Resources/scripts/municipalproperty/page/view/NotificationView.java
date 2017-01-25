package municipalproperty.page.view;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._Session;

import municipalproperty.dao.NotificationDAO;
import municipalproperty.model.Notification;
import municipalproperty.model.constants.NotificationType;

public class NotificationView extends AbstractMunicipalPropertyView {

	@Override
	public void doGET(_Session session, WebFormData formData) {
		try {
			int pageNum = 1;
			int pageSize = session.pageSize;
			if (formData.containsField("page")) {
				pageNum = formData.getNumberValueSilently("page", pageNum);
			}
			String cat = formData.getValueSilently("type");
			NotificationDAO nDao = new NotificationDAO(session);
			ViewPage<Notification> vp = nDao.findAllequal("type", NotificationType.valueOf(cat), pageNum, pageSize);
			addContent(vp.getResult(), vp.getMaxPage(), vp.getCount(), vp.getPageNum());
		} catch (DAOException e) {
			logError(e);
			setBadRequest();
		}
	}
}
