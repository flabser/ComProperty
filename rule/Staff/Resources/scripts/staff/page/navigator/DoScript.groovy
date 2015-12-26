package staff.page.navigator

import kz.nextbase.script.*
import kz.nextbase.script.events._DoScript
import kz.nextbase.script.outline.*

/**
 * Created by Bekzat on 2/10/14.
 */

class DoScript extends _DoScript{
	public void doProcess(_Session session, _WebFormData formData, String lang) {

		def cuser = session.getCurrentAppUser();
		def list = [];
		def docs_outline = new _Outline(getLocalizedWord("Структура",lang), getLocalizedWord("Структура",lang), "str")

		//if(cuser.hasRole(["struct_keeper", "supervisor"])) {
		docs_outline.addEntry(new _OutlineEntry(getLocalizedWord("Структура организации",lang), getLocalizedWord("Структура организации",lang), "structure", "Provider?type=page&id=structure"))
		docs_outline.addEntry(new _OutlineEntry(getLocalizedWord("Группы пользователей",lang), getLocalizedWord("Группы пользователей",lang), "group", "Provider?type=page&id=group"))
		docs_outline.addEntry(new _OutlineEntry(getLocalizedWord("Должность",lang), getLocalizedWord("Должность",lang), "post", "Provider?type=page&id=post&sortfield=VIEWTEXT2&order=ASC"))
		docs_outline.addEntry(new _OutlineEntry(getLocalizedWord("Тип подразделения",lang), getLocalizedWord("Тип подразделения",lang), "subdivisionlist", "Provider?type=page&id=subdivisionlist&sortfield=VIEWTEXT1&order=ASC"))
		// }

		// if(cuser.hasRole(["org_keeper", "supervisor"])){
		docs_outline.addEntry(new _OutlineEntry(getLocalizedWord("Организации",lang), getLocalizedWord("Организации",lang), "organizations", "Provider?type=page&id=organizations"))
		// }
		list.add(docs_outline)
		def contractor_outline = new _Outline(getLocalizedWord("Контрагенты",lang), getLocalizedWord("Контрагенты",lang), "contr")
		/*
		 contractor_outline.addEntry(new _OutlineEntry(getLocalizedWord("Физические лица",lang), getLocalizedWord("Физические лица",lang),
		 "naturalperson", "Provider?type=page&id=naturalperson"));
		 contractor_outline.addEntry(new _OutlineEntry(getLocalizedWord("Юридические лица",lang), getLocalizedWord("Юридические лица",lang),
		 "legalentity", "Provider?type=page&id=legalentity"));
		 */
		def resp_person = new _OutlineEntry(getLocalizedWord("Ответственные лица по загрузке объектов",lang),
				getLocalizedWord("Ответственные лица по загрузке объектов",lang), "responsibleperson", "Provider?type=page&id=responsibleperson&page=0");
		resp_person.addEntry(new _OutlineEntry(getLocalizedWord("На регистрацию",lang),
				getLocalizedWord("На регистрацию",lang), "responsibleperson_inactive", "Provider?type=page&id=responsibleperson&status=inactive&page=0"));
		resp_person.addEntry(new _OutlineEntry(getLocalizedWord("Зарегистрированные",lang),
				getLocalizedWord("Зарегистрированные",lang), "responsibleperson_active", "Provider?type=page&id=responsibleperson&status=active&page=0"));

		contractor_outline.addEntry(resp_person);
		list.add(contractor_outline)
		setContent(list)
	}
}
