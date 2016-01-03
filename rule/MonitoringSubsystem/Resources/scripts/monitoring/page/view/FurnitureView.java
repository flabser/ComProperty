package monitoring.page.view;

import java.util.EnumSet;
import java.util.UUID;

import kz.flabs.dataengine.jpa.DAO;
import kz.flabs.users.User;
import kz.nextbase.script._IPOJOObject;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import monitoring.dao.PropertyDAO;
import monitoring.model.Property;
import monitoring.model.constants.KufType;

public class FurnitureView extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		println(formData);
		User user = session.getUser();
		int pageNum = 1;
		int pageSize = user.getSession().pageSize;
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}
		PropertyDAO dao = new PropertyDAO(session);
		DAO<Property, UUID>.ViewResult<Property> result = dao.findAllIN("kuf", EnumSet.of(KufType.FURNITURE), pageNum, pageSize);
		_POJOListWrapper<? extends _IPOJOObject> data = new _POJOListWrapper<Property>(result.getResult(), result.getMaxPage(), result.getCount(),
		        pageNum);
		setContent(data);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}
