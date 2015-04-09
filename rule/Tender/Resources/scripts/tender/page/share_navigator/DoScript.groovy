package tender.page.share_navigator

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

		//if (user.hasRole("user") || user.hasRole("guest")){
			
			def rent = new _Outline(getLocalizedWord("Объекты объявленые в аренду",lang), getLocalizedWord("Объекты объявленые в аренду",lang), "rent")
            rent.addEntry(new _OutlineEntry(getLocalizedWord("Мои заявки",lang), getLocalizedWord("Мои заявки",lang), "rent_application", "Provider?type=page&id=rent_application&page=0"))
			rent.addEntry(new _OutlineEntry(getLocalizedWord("Объявлены в аренду",lang), getLocalizedWord("Объявлены в аренду",lang), "rent", "Provider?type=page&id=rent&page=0"))
			outline.addOutline(rent)
			list.add(rent)
			
			def tenders = new _Outline(getLocalizedWord("Тендера",lang), getLocalizedWord("Тендера",lang), "tenders")
            tenders.addEntry(new _OutlineEntry(getLocalizedWord("Мои заявки",lang), getLocalizedWord("Мои заявки",lang), "tender_application", "Provider?type=page&id=tender_application&page=0"))
			tenders.addEntry(new _OutlineEntry(getLocalizedWord("Все тендеры",lang), getLocalizedWord("Все тендеры",lang), "all_tenders", "Provider?type=page&id=all_tenders&page=0"))
			tenders.addEntry(new _OutlineEntry(getLocalizedWord("Активные тендеры",lang), getLocalizedWord("Активные тендеры",lang), "active_tenders", "Provider?type=page&id=active_tenders&page=0"))
			tenders.addEntry(new _OutlineEntry(getLocalizedWord("Закрытые тендеры",lang), getLocalizedWord("Закрытые тендеры",lang), "close_tenders", "Provider?type=page&id=close_tenders&page=0"))
			outline.addOutline(tenders)
			list.add(tenders)
			
			
		//}
		//if (user.hasRole("user")){
			def notifications_outline = new _Outline(getLocalizedWord("Уведомления",lang), getLocalizedWord("Уведомления",lang), "notifications_outline")
			notifications_outline.addEntry(new _OutlineEntry(getLocalizedWord("Уведомления",lang), getLocalizedWord("Уведомления",lang), "notification", "Provider?type=page&id=notification&page=0"))
			outline.addOutline(notifications_outline)
			list.add(notifications_outline)
		//}
		
		if (user.hasRole("registrator")){
			
			def reg = new _Outline(getLocalizedWord("На регистрацию",lang), getLocalizedWord("На регистрацию",lang), "tenders")
			reg.addEntry(new _OutlineEntry(getLocalizedWord("Аренда",lang), getLocalizedWord("Аренда",lang), "reg_rent", "Provider?type=page&id=reg_rent&page=0"))
			reg.addEntry(new _OutlineEntry(getLocalizedWord("Тендеры",lang), getLocalizedWord("Тендеры",lang), "reg_tenders", "Provider?type=page&id=reg_tenders&page=0"))
			outline.addOutline(reg)
			list.add(reg)
		}
		
		/*def add_outline = new _Outline(getLocalizedWord("Прочее",lang), getLocalizedWord("Прочее",lang), "add")
		add_outline.addEntry(new _OutlineEntry(getLocalizedWord("Корзина",lang), getLocalizedWord("Корзина",lang), "recyclebin", "Provider?type=page&id=recyclebin"))
		outline.addOutline(add_outline)
		list.add(add_outline)
		
		def archive_outline = new _Outline(getLocalizedWord("Архив",lang), getLocalizedWord("Архив",lang), "archive_outline")
		archive_outline.addEntry(new _OutlineEntry(getLocalizedWord("Архив",lang), getLocalizedWord("Архив",lang), "archive", "Provider?type=page&id=archive"))
		outline.addOutline(archive_outline)
		list.add(archive_outline)*/
		
		setContent(list)
	}
}
