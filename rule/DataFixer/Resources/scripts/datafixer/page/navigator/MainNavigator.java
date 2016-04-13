package datafixer.page.navigator;

import java.util.ArrayList;
import java.util.List;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.scriptprocessor.page.IOutcomeObject;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;

/**
 * @author Kayra created 13-04-2016
 */

public class MainNavigator extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		List<IOutcomeObject> list = new ArrayList<IOutcomeObject>();

		_Outline outline = new _Outline(getLocalizedWord("inconsistent_data", lang), "inconsistent_data");
		outline.addEntry(new _OutlineEntry(getLocalizedWord("inconsistent_address", lang), "inconsistentaddr-view"));

		list.add(outline);

		addValue("outline_current", formData.getValueSilently("id").replace("-form", "-view") + formData.getValueSilently("kuf"));

		addContent(list);
	}
}
