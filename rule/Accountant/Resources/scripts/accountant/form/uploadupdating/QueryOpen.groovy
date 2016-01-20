package accountant.form.uploadupdating

import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.actions._Action
import kz.nextbase.script.actions._ActionType
import kz.nextbase.script.events._FormQueryOpen


class QueryOpen extends _FormQueryOpen {

    @Override
    public void doQueryOpen(_Session session, _WebFormData webFormData, String lang) {
        publishValue("title", getLocalizedWord("Страница загрузки обновлений", lang))

        def actionBar = session.createActionBar()
        actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть", lang), "", _ActionType.SAVE_AND_CLOSE))
        actionBar.addAction(new _Action(getLocalizedWord("Подписать", lang), "", "sign_upload"))
        //actionBar.addAction(new _Action(getLocalizedWord("Закрыть", lang), "", _ActionType.CLOSE))
        publishElement(actionBar)
    }

    @Override
    public void doQueryOpen(_Session session, _Document doc, _WebFormData webFormData, String lang) {
        publishValue("title", getLocalizedWord("Страница загрузки обновлений", lang) + ":" + doc.getViewText())

        def actionBar = session.createActionBar()
        actionBar.addAction(new _Action(getLocalizedWord("Сохранить и закрыть", lang), "", _ActionType.SAVE_AND_CLOSE))
        actionBar.addAction(new _Action(getLocalizedWord("Подписать", lang), "", "sign_upload"))
        publishElement(actionBar)
    }
}
