package page.glossary_search
import kz.nextbase.script._Session
import kz.nextbase.script._Tag
import kz.nextbase.script._WebFormData
import kz.nextbase.script._XMLDocument
import kz.nextbase.script.actions._Action
import kz.nextbase.script.actions._ActionBar
import kz.nextbase.script.actions._ActionType
import kz.nextbase.script.events._DoScript
import nextbase.groovy.*

class DoScript extends _DoScript {

    @Override
    public void doProcess(_Session session, _WebFormData formData, String lang) {
        int page = formData.getNumberValueSilently("page", 1)
        def db = session.getCurrentDatabase()
        String keyword = formData.getEncodedValueSilently("keyword")
        def col = db.getCollectionOfGlossaries("viewtext1 ~* '$keyword'" +
                " and (form='region' or form='city' or form='district' or form ='street')", page, 15);
        getActionBar(session, lang);
        setContent(col)
        def keywordTag = new _Tag("keyword", keyword)
        setContent(new _XMLDocument(keywordTag));
    }

    def getActionBar(_Session session, String lang){
        def actionBar = new _ActionBar(session)
        actionBar.addAction(new _Action(getLocalizedWord("Вернуться к списку документов", lang),
                getLocalizedWord("Вернуться к списку документов", lang), _ActionType.CLOSE))
        setContent(actionBar);
    }
}