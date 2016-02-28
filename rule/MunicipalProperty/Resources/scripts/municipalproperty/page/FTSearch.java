package municipalproperty.page;

import kz.flabs.dataengine.IDatabase;
import kz.flabs.dataengine.IFTIndexEngine;
import kz.flabs.localization.LanguageCode;
import kz.lof.dataengine.jpa.ViewPage;
import kz.lof.scripting._POJOListWrapper;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.events._DoPage;
import municipalproperty.model.Property;

/**
 * @author Kayra created 06-01-2016
 */

public class FTSearch extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, LanguageCode lang) {
		String keyWord = formData.getValueSilently("keyword");
		if (keyWord.isEmpty()) {
			setBadRequest();
			return;
		}
		int pageNum = formData.getNumberValueSilently("page", 1);
		int pageSize = session.pageSize;

		IDatabase db = session.getCurrentDatabase();
		IFTIndexEngine ftEngine = db.getFTSearchEngine();
		ViewPage result = ftEngine.search(keyWord, session, pageNum, pageSize);

		addContent(new _ActionBar(session).addAction(new _Action(getLocalizedWord("back_to_doc_list", lang), getLocalizedWord("back", lang),
		        "reset_search")));
		if (result != null) {
			addContent(new _POJOListWrapper<Property>(result.getResult(), result.getMaxPage(), result.getCount(), result.getPageNum(), session,
			        keyWord));
		} else {
			addContent(new _POJOListWrapper(getLocalizedWord("ft_search_resturn_null", lang) + ": '" + keyWord + "'", keyWord));
		}
	}
}
