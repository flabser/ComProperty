package registry.page.navigator;

import java.util.LinkedList;

import com.exponentus.dataengine.exception.DAOException;
import com.exponentus.env.Environment;
import com.exponentus.localization.LanguageCode;
import com.exponentus.scripting._Session;
import com.exponentus.scripting._WebFormData;
import com.exponentus.scripting.event._DoPage;
import com.exponentus.scripting.outline._Outline;
import com.exponentus.scripting.outline._OutlineEntry;
import com.exponentus.scriptprocessor.page.IOutcomeObject;
import com.exponentus.server.Server;

import reference.dao.OrgCategoryDAO;
import reference.model.OrgCategory;

public class MainNavigator extends _DoPage {
	
	@Override
	public void doGET(_Session session, _WebFormData formData) {
		LanguageCode lang = session.getLang();
		LinkedList<IOutcomeObject> list = new LinkedList<IOutcomeObject>();
		
		_Outline common_outline = new _Outline(getLocalizedWord("common_staff_data", lang), "common");
		_OutlineEntry orgEntry = new _OutlineEntry(getLocalizedWord("organizations", lang), "organization-view");
		try {
			for (OrgCategory cat : new OrgCategoryDAO(session).findAll()) {
				orgEntry.addEntry(new _OutlineEntry(cat.getLocalizedName(lang),
						getLocalizedWord("labeled", lang) + " : " + cat.getLocalizedName(lang),
						"organization-view" + cat.getId(), "p?id=organization-view&categoryid=" + cat.getId()));
			}
		} catch (DAOException e) {
			setBadRequest();
			Server.logger.errorLogEntry(e);
		}
		common_outline.addEntry(orgEntry);
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("contractors", lang), "contractor-view"));
		common_outline.addEntry(new _OutlineEntry(getLocalizedWord("individuals", lang), "individual-view"));
		// common_outline.addEntry(new
		// _OutlineEntry(getLocalizedWord("legal_entities", lang),
		// "legal-entity-view"));
		common_outline
				.addEntry(new _OutlineEntry(getLocalizedWord("responsible_persons", lang), "responsible-person-view"));
		
		list.add(common_outline);
		
		addValue("workspaceUrl", Environment.getWorkspaceURL());
		addValue("outline_current",
				formData.getValueSilently("id").replace("-form", "-view") + formData.getValueSilently("categoryid"));
		addContent(list);
	}
}
