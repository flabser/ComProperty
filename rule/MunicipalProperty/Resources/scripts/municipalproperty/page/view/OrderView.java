package municipalproperty.page.view;

import java.util.UUID;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.dataengine.jpa.ViewPage;
import com.exponentus.exception.SecureException;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._POJOListWrapper;
import com.exponentus.scripting._Session;
import com.exponentus.scripting.actions._Action;
import com.exponentus.scripting.actions._ActionBar;
import com.exponentus.scripting.actions._ActionType;

import municipalproperty.dao.OrderDAO;
import municipalproperty.dao.PropertyDAO;
import municipalproperty.model.Order;
import municipalproperty.model.Property;

/**
 * @author Kayra created 27-01-2016
 */

public class OrderView extends AbstractMunicipalPropertyView {

	@Override
	public void doGET(_Session session, WebFormData formData) {
		try {
			String propertyId = formData.getValueSilently("propertyid");
			session.getLang();
			OrderDAO orderDAO = new OrderDAO(session);

			if (!propertyId.isEmpty() && !propertyId.equals("undefined")) {
				PropertyDAO propertyDAO = new PropertyDAO(session);
				Property property = propertyDAO.findByIdentefier(propertyId);
				if (property != null) {
					ViewPage<Order> result = orderDAO.findAllByProperty(property);
					addContent(new _POJOListWrapper<>(result.getResult(), result.getMaxPage(), result.getCount(),
							result.getPageNum(), session));
				}
			} else {
				_ActionBar actionBar = new _ActionBar(session);
				actionBar.addAction(new _Action(getLocalizedWord("del_document", session.getLang()), "",
						_ActionType.DELETE_DOCUMENT));
				addContent(actionBar);
				addContent(getViewPage(orderDAO, formData));
			}
		} catch (DAOException e) {
			logError(e);
			setBadRequest();
		}
	}

	@Override
	public void doDELETE(_Session session, WebFormData formData) {
		try {
			OrderDAO dao = new OrderDAO(session);
			for (String id : formData.getListOfValuesSilently("docid")) {
				Order c = dao.findById(UUID.fromString(id));

				dao.delete(c);
			}
		} catch (SecureException | DAOException e) {
			setError(e);
		}

	}
}
