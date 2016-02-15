package reference.page.form;

import kz.flabs.localization.LanguageType;
import kz.nextbase.script._Session;
import kz.nextbase.script._Validation;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.actions._Action;
import kz.nextbase.script.actions._ActionBar;
import kz.nextbase.script.actions._ActionType;
import kz.nextbase.script.events._DoPage;

/**
 * @author Kayra created 03-01-2016
 */

public abstract class ReferenceForm extends _DoPage {

    protected _Validation validate(_WebFormData formData, LanguageType lang) {
        _Validation ve = new _Validation();
        if (formData.getValueSilently("name").isEmpty()) {
            ve.addError("name", "empty", getLocalizedWord("required", lang));
        }

        return ve;
    }

    protected _ActionBar getSimpleActionBar(_Session ses, LanguageType lang) {
        _ActionBar actionBar = new _ActionBar(ses);
        // _Employer user = ses.getCurrentAppUser();
        // if (user.hasRole("supervisor")) {
        actionBar.addAction(new _Action(getLocalizedWord("save_close", lang), "", _ActionType.SAVE_AND_CLOSE));
        // }

        actionBar.addAction(new _Action(getLocalizedWord("close", lang), "", _ActionType.CLOSE));
        return actionBar;

    }

    @Override
    public abstract void doGET(_Session session, _WebFormData formData, LanguageType lang);

    @Override
    public abstract void doPOST(_Session session, _WebFormData formData, LanguageType lang);
}
