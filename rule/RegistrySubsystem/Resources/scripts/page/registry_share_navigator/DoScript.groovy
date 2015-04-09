package page.registry_share_navigator

import java.util.Map;
import kz.nextbase.script.*;
import kz.nextbase.script.events._DoScript
import kz.nextbase.script.outline.*;

class DoScript extends _DoScript {

	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {
		//println(lang)
		def list = []
		def user = session. getCurrentAppUser()
		def outline = new _Outline("", "", "outline")


		if (user.hasRole("registrator")){
			def registermunicipal_outline = new _Outline(getLocalizedWord("Реестр Коммунальных Государственных предприятий и учреждений",lang), getLocalizedWord("Реестр Коммунальных Государственных предприятий и учреждений",lang), "registermunicipal")
				registermunicipal_outline.addEntry(new _OutlineEntry(getLocalizedWord("Коммунальные Государственные Учреждения",lang), getLocalizedWord("Коммунальные Государственные Учреждения",lang), "kgu", "Provider?type=page&id=kgu"))
				registermunicipal_outline.addEntry(new _OutlineEntry(getLocalizedWord("Предприятия на праве Хозяйственного Ведения",lang), getLocalizedWord("Коммунальные Государственные Предприятия на праве Хозяйственного Ведения",lang), "kgp", "Provider?type=page&id=kgp"))
				registermunicipal_outline.addEntry(new _OutlineEntry(getLocalizedWord("Казённые Предприятия",lang), getLocalizedWord("Государственные Коммунальные Казённые Предприятия",lang), "gkkp", "Provider?type=page&id=gkkp"))
				registermunicipal_outline.addEntry(new _OutlineEntry(getLocalizedWord("Акционерные Общества",lang), getLocalizedWord("Акционерные Общества с участием государства в уставном капитале",lang), "ao", "Provider?type=page&id=ao"))
				registermunicipal_outline.addEntry(new _OutlineEntry(getLocalizedWord("Товарищества с Ограниченной Ответственностью",lang), getLocalizedWord("Товарищества с Ограниченной Ответственностью с участием государства в уставном капитале",lang), "too", "Provider?type=page&id=too"))
				registermunicipal_outline.addEntry(new _OutlineEntry(getLocalizedWord("Дочерние и зависимые организации",lang), getLocalizedWord("Дочерние и зависимые организации национальных холдингов и компаний",lang), "subsidiaries", "Provider?type=page&id=subsidiaries"))
			def citydbentities_outline = new _Outline(getLocalizedWord("Городская БД физических и юридических лиц",lang), getLocalizedWord("Городская БД физических и юридических лиц",lang), "citydbentities")
				citydbentities_outline.addEntry(new _OutlineEntry(getLocalizedWord("Физические лица",lang), getLocalizedWord("Физические лица",lang), "individuals", "Provider?type=page&id=individuals"))
				citydbentities_outline.addEntry(new _OutlineEntry(getLocalizedWord("Юридические лица",lang), getLocalizedWord("Юридические лица",lang), "legalentities", "Provider?type=page&id=legalentities"))
			def exempts_outline = new _Outline(getLocalizedWord("Льготники",lang), getLocalizedWord("Льготники",lang), "exempts")
				exempts_outline.addEntry(new _OutlineEntry(getLocalizedWord("Льготники",lang), getLocalizedWord("Льготники",lang), "exempt", "Provider?type=page&id=exempt"))
			outline.addOutline(registermunicipal_outline)
			list.add(registermunicipal_outline)
			outline.addOutline(citydbentities_outline)
			list.add(citydbentities_outline)
			outline.addOutline(exempts_outline)
			list.add(exempts_outline)
		}
		
		def add_outline = new _Outline(getLocalizedWord("Прочее",lang), getLocalizedWord("Прочее",lang), "add")
		add_outline.addEntry(new _OutlineEntry(getLocalizedWord("Корзина",lang), getLocalizedWord("Корзина",lang), "recyclebin", "Provider?type=page&id=recyclebin"))
		outline.addOutline(add_outline)
		list.add(add_outline)
		
		setContent(list)
	}
}
