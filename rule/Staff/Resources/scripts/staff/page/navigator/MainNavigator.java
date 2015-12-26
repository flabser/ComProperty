package staff.page.navigator;

import java.util.ArrayList;
import java.util.List;

import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoPage;
import kz.nextbase.script.outline._Outline;
import kz.nextbase.script.outline._OutlineEntry;

/**
 * Created by Kaira on 21/12/15.
 */

public class MainNavigator extends _DoPage {

	@Override
	public void doGET(_Session session, _WebFormData formData, String lang) {
		// User cuser = session.getCurrentAppUser();
		List list = new ArrayList();

		_Outline docs_outline = new _Outline(getLocalizedWord("Структура", lang), getLocalizedWord("Структура", lang),
				"str");

		// if(cuser.hasRole(["struct_keeper", "supervisor"])) {
		docs_outline.addEntry(new _OutlineEntry(getLocalizedWord("Структура организации", lang),
				getLocalizedWord("Структура организации", lang), "structure", "Provider?id=structure"));
		docs_outline
				.addEntry(new _OutlineEntry(getLocalizedWord("Должность", lang), getLocalizedWord("Должность", lang),
						"post", "Provider?type=page&id=post&sortfield=VIEWTEXT2&order=ASC"));
		docs_outline.addEntry(new _OutlineEntry(getLocalizedWord("Тип подразделения", lang),
				getLocalizedWord("Тип подразделения", lang), "subdivisionlist", "Provider?id=subdivisionlist")); // }

		// if(cuser.hasRole(["org_keeper", "supervisor"])){
		docs_outline.addEntry(new _OutlineEntry(getLocalizedWord("Организации", lang),
				getLocalizedWord("Организации", lang), "organizations", "Provider?id=organizations")); // }
																										// list.add(docs_outline);
		_Outline contractor_outline = new _Outline(getLocalizedWord("Контрагенты", lang),
				getLocalizedWord("Контрагенты", lang), "contr");

		contractor_outline.addEntry(new _OutlineEntry(getLocalizedWord("Физические лица", lang),
				getLocalizedWord("Физические лица", lang), "naturalperson", "Provider?type=page&id=naturalperson"));
		contractor_outline.addEntry(new _OutlineEntry(getLocalizedWord("Юридические лица", lang),
				getLocalizedWord("Юридические лица", lang), "legalentity", "Provider?type=page&id=legalentity"));

		_OutlineEntry resp_person = new _OutlineEntry(getLocalizedWord("Ответственные лица по загрузке объектов", lang),
				getLocalizedWord("Ответственные лица по загрузке объектов", lang), "responsibleperson",
				"Provider?type=page&id=responsibleperson&page=0");
		resp_person.addEntry(new _OutlineEntry(getLocalizedWord("На регистрацию", lang),
				getLocalizedWord("На регистрацию", lang), "responsibleperson_inactive",
				"Provider?type=page&id=responsibleperson&status=inactive&page=0"));
		resp_person.addEntry(new _OutlineEntry(getLocalizedWord("Зарегистрированные", lang),
				getLocalizedWord("Зарегистрированные", lang), "responsibleperson_active",
				"Provider?type=page&id=responsibleperson&status=active&page=0"));

		contractor_outline.addEntry(resp_person);
		list.add(docs_outline);
		list.add(contractor_outline);

		setContent(list);

	}

	@Override
	public void doPOST(_Session session, _WebFormData formData, String lang) {

	}
}
