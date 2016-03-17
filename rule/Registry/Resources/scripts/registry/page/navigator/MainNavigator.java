package registry.page.navigator;

import java.util.ArrayList;
import java.util.List;

import kz.lof.localization.LanguageCode;
import kz.lof.scripting._Session;
import kz.lof.scripting._WebFormData;
import kz.lof.scripting.event._DoPage;
import kz.lof.webserver.servlet.IOutcomeObject;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;
import reference.dao.OrgCategoryDAO;
import reference.model.OrgCategory;

public class MainNavigator extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		List<IOutcomeObject> list = new ArrayList<IOutcomeObject>();

		_Outline common_outline = new _Outline(getLocalizedWord("common_staff_data", lang), "common");
		_OutlineEntry orgEntry = new _OutlineEntry(getLocalizedWord("organizations", lang), "organization-view");
		for (OrgCategory cat : new OrgCategoryDAO(session).findAll()) {
			orgEntry.addEntry(new _OutlineEntry(getLocalizedWord(cat.getName(), lang), getLocalizedWord("labeled", lang) + " : "
			        + getLocalizedWord(cat.getName(), lang), "organization-view" + cat.getId(), "Provider?id=organization-view&categoryid="
			        + cat.getId()));
		}
		common_outline.addEntry(orgEntry);
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("contractors", lang), "contractor-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("individuals", lang), "individual-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("legal_entities", lang), "legal-entity-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("responsible_persons", lang), "responsible-person-view"));

		list.add(common_outline);

		addContent("outline_current", formData.getValueSilently("id").replace("-form", "-view") + formData.getValueSilently("docid"));
		addContent(list);
	}

	@Override
	public void doPOST(_Session session, _WebFormData formData) {

	}

}
