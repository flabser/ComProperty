package municipalproperty.page.view;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.scripting.WebFormData;
import com.exponentus.scripting._Session;
import com.exponentus.server.Server;

import municipalproperty.dao.filter.PropertyFilter;
import reference.dao.PropertyCodeDAO;
import reference.model.PropertyCode;

public class MPByPropertyCodeView extends AbstractMunicipalPropertyView {

	@Override
	public void doGET(_Session session, WebFormData formData) {
		super.doGET(session, formData);
		try {
			String cat = formData.getValueSilently("categoryid");
			PropertyCodeDAO pcDao = new PropertyCodeDAO(session);
			PropertyCode propertyCode = pcDao.findById(cat);
			PropertyFilter propertyFilter = new PropertyFilter();

			propertyFilter.setPropertyCode(propertyCode);

			addContent(getViewPage(session, formData, propertyFilter, session.getLang()));
		} catch (DAOException e) {
			Server.logger.errorLogEntry(e);
			setBadRequest();
		}
	}
}
