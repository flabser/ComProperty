package municipalproperty.page.view;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import kz.flabs.dataengine.jpa.DAO;
import kz.flabs.users.User;
import kz.nextbase.script._IXMLContent;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Property;
import municipalproperty.model.constants.KufType;

/**
 * Share abstract View for Municipal Property application Use it to code some
 * shared methods for all views of the application
 * 
 * @author Kayra created 05-01-2016
 */

public abstract class MunicipalPropertyView extends _DoPage {

	protected _IXMLContent getPropertyViewPage(_Session session, _WebFormData formData, KufType kuf) {
		List<KufType> params = new ArrayList<KufType>();
		params.add(kuf);
		return getPropertyViewContent(session, formData, params);
	}

	protected _IXMLContent getPropertyViewContent(_Session session, _WebFormData formData, List<KufType> set) {
		User user = session.getUser();
		int pageNum = 1;
		int pageSize = user.getSession().pageSize;
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}
		PropertyDAO dao = new PropertyDAO(session);
		DAO<Property, UUID>.ViewPage<Property> result = dao.findAllIN("kuf", set, pageNum, pageSize);
		return new _POJOListWrapper<Property>(result.getResult(), result.getMaxPage(), result.getCount(), result.getPageNum());
	}

	@Override
	public abstract void doGET(_Session session, _WebFormData formData, String lang);

	@Override
	public abstract void doPOST(_Session session, _WebFormData formData, String lang);
}
