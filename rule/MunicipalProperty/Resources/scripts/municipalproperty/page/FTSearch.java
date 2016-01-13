package municipalproperty.page;

import kz.flabs.dataengine.IDatabase;
import kz.flabs.dataengine.IFTIndexEngine;
import kz.flabs.dataengine.jpa.ViewPage;
import kz.flabs.users.User;
import kz.nextbase.script._POJOListWrapper;
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;
import municipalproperty.model.Property;

/**
 * 
 * 
 * @author Kayra created 06-01-2016
 */

public class FTSearch extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		String keyWord = formData.getEncodedValueSilently("keyword");
		if (keyWord.equals("")) {
			setBadRequest();
			return;
		}
		User user = session.getUser();
		int pageNum = 1;
		int pageSize = user.getSession().pageSize;
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}

		IDatabase db = ses.getCurrentDatabase().getBaseObject();
		IFTIndexEngine ftEngine = db.getFTSearchEngine();
		ViewPage result = ftEngine.search(keyWord, session, pageNum, pageSize);
		setContent(new _ActionBar(ses).addAction(new _Action(_ActionType.CLOSE)));
		if (result != null) {
			setContent(new _POJOListWrapper<Property>(result.getResult(), result.getMaxPage(), result.getCount(), result.getPageNum()));
		} else {
			setContent(new _POJOListWrapper(getLocalizedWord("ft_search_resturn_null", lang) + ": " + keyWord));
		}

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}

}