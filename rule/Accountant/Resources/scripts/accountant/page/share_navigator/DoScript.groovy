package accountant.page.share_navigator

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
			def glossary_outline = new _Outline(getLocalizedWord("Справочники",lang), getLocalizedWord("Справочники",lang), "glossary")
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Область",lang), getLocalizedWord("Область",lang), "region", "Provider?type=page&id=region"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Город",lang), getLocalizedWord("Город",lang), "city", "Provider?type=page&id=city"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Район",lang), getLocalizedWord("Район",lang), "district", "Provider?type=page&id=district"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Улица",lang), getLocalizedWord("Улица",lang), "street", "Provider?type=page&id=street"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Тип (Класс по назначению)",lang), getLocalizedWord("Тип (Класс по назначению)",lang), "type", "Provider?type=page&id=type"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Вид спортивного инв.",lang), getLocalizedWord("Вид спортивного инв.",lang), "typesportsequipment", "Provider?type=page&id=typesportsequipment"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Материал постройки",lang), getLocalizedWord("Материал постройки",lang), "material", "Provider?type=page&id=material"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Тип строения",lang), getLocalizedWord("Коэффициент учитывающий тип строения",lang), "kt", "Provider?type=page&id=kt"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Вид деятельности нанимателя",lang), getLocalizedWord("Коэффициенты учитывающие вид деятельности нанимателя",lang), "kvd", "Provider?type=page&id=kvd"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Предельные нормы амортизации",lang), getLocalizedWord("Предельные нормы амортизации",lang), "limitdepreciation", "Provider?type=page&id=limitdepreciation"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Понижающий коэффициент",lang), getLocalizedWord("Понижающий коэффициент",lang), "stepdowncoefficient", "Provider?type=page&id=stepdowncoefficient"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("МРП",lang), getLocalizedWord("МРП",lang), "mrp", "Provider?type=page&id=mrp"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Код права на имущество",lang), getLocalizedWord("Код права на имущество",lang), "propertycode", "Provider?type=page&id=propertycode"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("ОКЭД",lang), getLocalizedWord("ОКЭД",lang), "oked", "Provider?type=page&id=oked"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Вид акций",lang), getLocalizedWord("Вид акций",lang), "typeofactions", "Provider?type=page&id=typeofactions"))
			glossary_outline.addEntry(new _OutlineEntry(getLocalizedWord("Форма акций",lang), getLocalizedWord("Форма акций",lang), "formofactions", "Provider?type=page&id=formofactions"))
			outline.addOutline(glossary_outline)
			list.add(glossary_outline)
		}
		
		def add_outline = new _Outline(getLocalizedWord("Прочее",lang), getLocalizedWord("Прочее",lang), "add")
		add_outline.addEntry(new _OutlineEntry(getLocalizedWord("Корзина",lang), getLocalizedWord("Корзина",lang), "recyclebin", "Provider?type=page&id=recyclebin"))
		outline.addOutline(add_outline)
		list.add(add_outline)
		
		setContent(list)
	}
}
