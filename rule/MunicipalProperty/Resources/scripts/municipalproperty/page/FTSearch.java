package municipalproperty.page;

import kz.flabs.dataengine.IDatabase;
import kz.flabs.dataengine.IFTIndexEngine;
import kz.flabs.localization.LanguageType;
import kz.lof.dataengine.jpa.ViewPage;
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
	public void doGET(_Session session, _WebFormData formData, LanguageType lang) {
		String keyWord = formData.getValueSilently("keyword");
		if (keyWord.equals("")) {
			setBadRequest();
			return;
		}
		int pageNum = 1;
		int pageSize = session.pageSize;
		if (formData.containsField("page")) {
			pageNum = formData.getNumberValueSilently("page", pageNum);
		}

		IDatabase db = ses.getCurrentDatabase();
		IFTIndexEngine ftEngine = db.getFTSearchEngine();
		ViewPage result = ftEngine.search(keyWord, session, pageNum, pageSize);
		setContent(new _ActionBar(ses).addAction(new _Action(getLocalizedWord("back_to_doc_list", lang), getLocalizedWord("back_to_doc_list", lang),
		        _ActionType.BACK)));
		if (result != null) {
			setContent(new _POJOListWrapper<Property>(result.getResult(), result.getMaxPage(), result.getCount(), result.getPageNum(), lang));
		} else {
			setContent(new _POJOListWrapper(getLocalizedWord("ft_search_resturn_null", lang) + ": " + keyWord));
		}

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, LanguageType lang) {

	}

}
