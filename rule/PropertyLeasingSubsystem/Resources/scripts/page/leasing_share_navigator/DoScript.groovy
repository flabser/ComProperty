package page.leasing_share_navigator

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
			def objectsmunicipalproperty = new _Outline(getLocalizedWord("Объекты коммунальной собственности",lang), getLocalizedWord("Объекты коммунальной собственности",lang), "objectsmunicipalproperty")
			
			/*def personalestate_outline = new _OutlineEntry(getLocalizedWord("Движимое имущество",lang), getLocalizedWord("Движимое имущество",lang), "personalestate", "Provider?type=page&id=personalestate&page=0")
			personalestate_outline.addEntry(new _OutlineEntry(getLocalizedWord("Мебель",lang), getLocalizedWord("Мебель",lang), "furniture", "Provider?type=page&id=furniture&page=0"))
			personalestate_outline.addEntry(new _OutlineEntry(getLocalizedWord("Животные",lang), getLocalizedWord("Животные",lang), "animals", "Provider?type=page&id=animals&page=0"))
			personalestate_outline.addEntry(new _OutlineEntry(getLocalizedWord("Инвентарь",lang), getLocalizedWord("Инвентарь",lang), "sportsequipment", "Provider?type=page&id=sportsequipment&page=0"))
			personalestate_outline.addEntry(new _OutlineEntry(getLocalizedWord("Прочее",lang), getLocalizedWord("Прочее",lang), "others", "Provider?type=page&id=others&page=0"))
			def intangibleassets = new _OutlineEntry(getLocalizedWord("Нематериальные активы",lang), getLocalizedWord("Нематериальные активы",lang), "intangibleassets", "Provider?type=page&id=intangibleassets&page=0")
			intangibleassets.addEntry(new _OutlineEntry(getLocalizedWord("Государственные пакеты акций",lang), getLocalizedWord("Государственные пакеты акций",lang), "shareblocks", "Provider?type=page&id=shareblocks&page=0"))
			intangibleassets.addEntry(new _OutlineEntry(getLocalizedWord("Долевое участие в ТОО",lang), getLocalizedWord("Долевое участие в ТОО",lang), "equity", "Provider?type=page&id=equity&page=0"))
			personalestate_outline.addEntry(intangibleassets)
			objectsmunicipalproperty.addEntry(personalestate_outline)*/
			
			
			def equipment_outline = new _OutlineEntry(getLocalizedWord("Оборудование",lang), getLocalizedWord("Оборудование",lang), "equipment", "Provider?type=page&id=equipment&page=0")
			equipment_outline.addEntry(new _OutlineEntry(getLocalizedWord("Школьное оборудование",lang), getLocalizedWord("Школьное оборудование",lang), "schoolequipment", "Provider?type=page&id=schoolequipment&page=0"))
			equipment_outline.addEntry(new _OutlineEntry(getLocalizedWord("Офисное оборудование",lang), getLocalizedWord("Офисное оборудование",lang), "officeequipment", "Provider?type=page&id=officeequipment&page=0"))
			equipment_outline.addEntry(new _OutlineEntry(getLocalizedWord("Компьютерное оборудование и орг. техника",lang), getLocalizedWord("Компьютерное оборудование и орг. техника",lang), "computerequipment", "Provider?type=page&id=computerequipment&page=0"))
			equipment_outline.addEntry(new _OutlineEntry(getLocalizedWord("Медицинское оборудование",lang), getLocalizedWord("Медицинское оборудование",lang), "medicalequipment", "Provider?type=page&id=medicalequipment&page=0"))
			equipment_outline.addEntry(new _OutlineEntry(getLocalizedWord("Кухонное оборудование",lang), getLocalizedWord("Кухонное оборудование",lang), "cookequipment", "Provider?type=page&id=cookequipment&page=0"))
			equipment_outline.addEntry(new _OutlineEntry(getLocalizedWord("Оборудование гражданской обороны",lang), getLocalizedWord("Оборудование гражданской обороны",lang), "equipmentofcivildefense", "Provider?type=page&id=equipmentofcivildefense&page=0"))
			objectsmunicipalproperty.addEntry(equipment_outline)
			
			def realestate_outline = new _OutlineEntry(getLocalizedWord("Недвижимое имущество",lang), getLocalizedWord("Недвижимое имущество",lang), "realestate", "Provider?type=page&id=realestate&page=0")
			realestate_outline.addEntry(new _OutlineEntry(getLocalizedWord("Здания(Отдельностоящие)",lang), getLocalizedWord("Здания(Отдельностоящие)",lang), "buildings", "Provider?type=page&id=buildings&page=0"))
			realestate_outline.addEntry(new _OutlineEntry(getLocalizedWord("Помещения",lang), getLocalizedWord("Помещения",lang), "rooms", "Provider?type=page&id=rooms&page=0"))
			realestate_outline.addEntry(new _OutlineEntry(getLocalizedWord("Строения",lang), getLocalizedWord("Строения",lang), "structures", "Provider?type=page&id=structures&page=0"))
			realestate_outline.addEntry(new _OutlineEntry(getLocalizedWord("Жилые объекты",lang), getLocalizedWord("Жилые объекты",lang), "residentialobjects", "Provider?type=page&id=residentialobjects&page=0"))
			realestate_outline.addEntry(new _OutlineEntry(getLocalizedWord("Земля",lang), getLocalizedWord("Земля",lang), "land", "Provider?type=page&id=land&page=0"))
			realestate_outline.addEntry(new _OutlineEntry(getLocalizedWord("Памятники истории и культуры",lang), getLocalizedWord("Памятники истории и культуры",lang), "monument", "Provider?type=page&id=monument&page=0"))
			objectsmunicipalproperty.addEntry(realestate_outline)
			
			/*def transport_outline = new _OutlineEntry(getLocalizedWord("Транспорт",lang), getLocalizedWord("Транспорт",lang), "transport", "Provider?type=page&id=transport&page=0")
				def automobile = new _OutlineEntry(getLocalizedWord("Автомашины",lang), getLocalizedWord("Автомашины",lang), "cars", "Provider?type=page&id=cars&page=0")
					automobile.addEntry(new _OutlineEntry(getLocalizedWord("Легковые",lang), getLocalizedWord("Легковые",lang), "automobile", "Provider?type=page&id=automobile&page=0"))
					automobile.addEntry(new _OutlineEntry(getLocalizedWord("Грузовые",lang), getLocalizedWord("Грузовые",lang), "cargo", "Provider?type=page&id=cargo&page=0"))
					automobile.addEntry(new _OutlineEntry(getLocalizedWord("Дежурный",lang), getLocalizedWord("Дежурный",lang), "dejtransport", "Provider?type=page&id=dejtransport&page=0"))
					automobile.addEntry(new _OutlineEntry(getLocalizedWord("Служебный",lang), getLocalizedWord("Служебный",lang), "officialtransport", "Provider?type=page&id=officialtransport&page=0"))
					automobile.addEntry(new _OutlineEntry(getLocalizedWord("Санитарный транспорт",lang), getLocalizedWord("Санитарный транспорт",lang), "hospitaltransport", "Provider?type=page&id=hospitaltransport&page=0"))
					def passengertransport = new _OutlineEntry(getLocalizedWord("Пассажирский транспорт",lang), getLocalizedWord("Пассажирский транспорт",lang), "passengertransport", "Provider?type=page&id=passengertransport&page=0")
						passengertransport.addEntry(new _OutlineEntry(getLocalizedWord("Автобусы",lang), getLocalizedWord("Автобусы",lang), "bus", "Provider?type=page&id=bus&page=0"))
						passengertransport.addEntry(new _OutlineEntry(getLocalizedWord("Троллейбусы",lang), getLocalizedWord("Троллейбусы",lang), "trolleybus", "Provider?type=page&id=trolleybus&page=0"))
						passengertransport.addEntry(new _OutlineEntry(getLocalizedWord("Трамваи",lang), getLocalizedWord("Трамваи",lang), "tram", "Provider?type=page&id=tram&page=0"))
						passengertransport.addEntry(new _OutlineEntry(getLocalizedWord("Водный транспорт",lang), getLocalizedWord("Водный транспорт",lang), "watertransport", "Provider?type=page&id=watertransport&page=0"))
						passengertransport.addEntry(new _OutlineEntry(getLocalizedWord("Таксопарк",lang), getLocalizedWord("Таксопарк",lang), "taxi", "Provider?type=page&id=taxi&page=0"))
				automobile.addEntry(passengertransport)
			transport_outline.addEntry(automobile)
			transport_outline.addEntry(new _OutlineEntry(getLocalizedWord("Специальная техника",lang), getLocalizedWord("Специальная техника",lang), "specialequipment", "Provider?type=page&id=specialequipment&page=0"))
			transport_outline.addEntry(new _OutlineEntry(getLocalizedWord("Мотоциклы",lang), getLocalizedWord("Мотоциклы",lang), "Other", "Provider?type=page&id=motorcycle&page=0"))
			objectsmunicipalproperty.addEntry(transport_outline)
			
			def strategicobjects_outline = new _OutlineEntry(getLocalizedWord("Стратегические объекты",lang), getLocalizedWord("Стратегические объекты",lang), "strategicobjects", "Provider?type=page&id=strategicobjects&page=0")
			strategicobjects_outline.addEntry(new _OutlineEntry(getLocalizedWord("Объекты государственного природно-заповедного фонда",lang), getLocalizedWord("Объекты государственного природно-заповедного фонда",lang), "objectreservedfund", "Provider?type=page&id=objectreservedfund&page=0"))
			def specialsconstructions = new _OutlineEntry(getLocalizedWord("Специальные сооружения",lang), getLocalizedWord("Специальные сооружения",lang), "specialconstructions", "Provider?type=page&id=specialconstructions&page=0")
				specialsconstructions.addEntry(new _OutlineEntry(getLocalizedWord("Бомбоубежища",lang), getLocalizedWord("Бомбоубежища",lang), "bombproof", "Provider?type=page&id=bombproof&page=0"))
				specialsconstructions.addEntry(new _OutlineEntry(getLocalizedWord("Заводы",lang), getLocalizedWord("Заводы",lang), "factory", "Provider?type=page&id=factory&page=0"))
				specialsconstructions.addEntry(new _OutlineEntry(getLocalizedWord("Комбинаты",lang), getLocalizedWord("Комбинаты",lang), "combines", "Provider?type=page&id=combines&page=0"))
				specialsconstructions.addEntry(new _OutlineEntry(getLocalizedWord("Аэропорты",lang), getLocalizedWord("Аэропорты",lang), "airport", "Provider?type=page&id=airport&page=0"))
				specialsconstructions.addEntry(new _OutlineEntry(getLocalizedWord("Подземные и надземные переходы",lang), getLocalizedWord("Подземные и надземные переходы",lang), "transitions", "Provider?type=page&id=transitions&page=0"))
			strategicobjects_outline.addEntry(specialsconstructions)
			objectsmunicipalproperty.addEntry(strategicobjects_outline)
			
			def engineeringInfrastructure_outline = new _OutlineEntry(getLocalizedWord("Объекты инженерной инфраструктуры",lang), getLocalizedWord("Объекты инженерной инфраструктуры",lang), "engineeringInfrastructure", "Provider?type=page&id=engineeringInfrastructure&page=0")
			engineeringInfrastructure_outline.addEntry(new _OutlineEntry(getLocalizedWord("Биллборды",lang), getLocalizedWord("Биллборды",lang), "billboard", "Provider?type=page&id=billboard&page=0"))
			engineeringInfrastructure_outline.addEntry(new _OutlineEntry(getLocalizedWord("Электрические столбы",lang), getLocalizedWord("Электрические столбы",lang), "columns", "Provider?type=page&id=columns&page=0"))
			def networks = new _OutlineEntry(getLocalizedWord("Сети",lang), getLocalizedWord("Сети",lang), "networks", "Provider?type=page&id=networks&page=0")
				networks.addEntry(new _OutlineEntry(getLocalizedWord("Электрические",lang), getLocalizedWord("Электрические",lang), "electricnetworks", "Provider?type=page&id=electricnetworks&page=0"))
				networks.addEntry(new _OutlineEntry(getLocalizedWord("Тепловые",lang), getLocalizedWord("Тепловые",lang), "thermalnetworks", "Provider?type=page&id=thermalnetworks&page=0"))
				networks.addEntry(new _OutlineEntry(getLocalizedWord("Газовые",lang), getLocalizedWord("Газовые",lang), "gas", "Provider?type=page&id=gas&page=0"))
				networks.addEntry(new _OutlineEntry(getLocalizedWord("Водопровод и канализация",lang), getLocalizedWord("Водопровод и канализация",lang), "watersystem", "Provider?type=page&id=watersystem&page=0"))
				networks.addEntry(new _OutlineEntry(getLocalizedWord("Арычные",lang), getLocalizedWord("Арычные",lang), "drain", "Provider?type=page&id=drain&page=0"))
			engineeringInfrastructure_outline.addEntry(networks)
			engineeringInfrastructure_outline.addEntry(new _OutlineEntry(getLocalizedWord("Дороги",lang), getLocalizedWord("Дороги",lang), "road", "Provider?type=page&id=road&page=0"))
			engineeringInfrastructure_outline.addEntry(new _OutlineEntry(getLocalizedWord("Парковки",lang), getLocalizedWord("Парковки",lang), "parking", "Provider?type=page&id=parking&page=0"))
			objectsmunicipalproperty.addEntry(engineeringInfrastructure_outline)*/
	
	
			outline.addOutline(objectsmunicipalproperty)
			list.add(objectsmunicipalproperty)
			
			def rent = new _Outline(getLocalizedWord("Объявлены в аренду",lang), getLocalizedWord("Объявлены в аренду",lang), "rent")
            rent.addEntry(new _OutlineEntry(getLocalizedWord("Объявлены в аренду",lang), getLocalizedWord("Объявлены в аренду",lang), "rent", "Provider?type=page&id=rent&page=0"))
            //def rentAppl = new _OutlineEntry(getLocalizedWord("Заявление на аренду",lang), getLocalizedWord("Заяление на аренду",lang), "rentApplDoc")
            def rentAppl = new _OutlineEntry(getLocalizedWord("Заявление на аренду",lang), getLocalizedWord("Все",lang), "rent_application", "Provider?type=page&id=rent_application&status=all&page=0");
            rentAppl.addEntry(new _OutlineEntry(getLocalizedWord("Новые",lang), getLocalizedWord("Новые",lang), "rent_application_new", "Provider?type=page&id=rent_application&status=new&page=0"))
            rentAppl.addEntry(new _OutlineEntry(getLocalizedWord("Обработанные",lang), getLocalizedWord("Обработанные",lang), "rent_application_reviewed", "Provider?type=page&id=rent_application&status=reviewed&page=0"));

            def tenderAppl = new _OutlineEntry(getLocalizedWord("Заявление на тендер",lang), getLocalizedWord("Все",lang), "tender_application", "Provider?type=page&id=tender_application&status=all&page=0");
            tenderAppl.addEntry(new _OutlineEntry(getLocalizedWord("Новые",lang), getLocalizedWord("Новые",lang), "tender_application_new", "Provider?type=page&id=tender_application&status=new&page=0"))
            tenderAppl.addEntry(new _OutlineEntry(getLocalizedWord("Обработанные",lang), getLocalizedWord("Обработанные",lang), "tender_application_reviewed", "Provider?type=page&id=tender_application&status=reviewed&page=0"));

            def tenders = new _OutlineEntry(getLocalizedWord("Все тендеры",lang), getLocalizedWord("Все тендеры",lang), "all_tenders", "Provider?type=page&id=all_tenders&page=0");
            tenders.addEntry(new _OutlineEntry(getLocalizedWord("Активные тендеры",lang), getLocalizedWord("Активные тендеры",lang), "active_tenders", "Provider?type=page&id=active_tenders&page=0"))
            tenders.addEntry(new _OutlineEntry(getLocalizedWord("Закрытые тендеры",lang), getLocalizedWord("Закрытые тендеры",lang), "close_tenders", "Provider?type=page&id=close_tenders&page=0"))


            rent.addEntry(rentAppl);
            rent.addEntry(tenderAppl);
            rent.addEntry(tenders);
			outline.addOutline(rent)
			list.add(rent)
			
			def contractsleasing_outline = new _Outline(getLocalizedWord("Договора и доп. соглашения",lang), getLocalizedWord("Договора и доп. соглашения",lang), "contractsleasing")
			contractsleasing_outline.addEntry(new _OutlineEntry(getLocalizedWord("Договора и доп. соглашения",lang), getLocalizedWord("Договора и доп. соглашения",lang), "contractleasing", "Provider?type=page&id=contractleasing"))
			outline.addOutline(contractsleasing_outline)
			list.add(contractsleasing_outline)
			
			def paycalendar_outline = new _Outline(getLocalizedWord("Платежные календари",lang), getLocalizedWord("Платежные календари",lang), "paycalendars")
			paycalendar_outline.addEntry(new _OutlineEntry(getLocalizedWord("Платежный календарь",lang), getLocalizedWord("Платежный календарь",lang), "paycalendar", "Provider?type=page&id=paycalendar"))
			outline.addOutline(paycalendar_outline)
			list.add(paycalendar_outline)
			
			def notifications_outline = new _Outline(getLocalizedWord("Уведомления",lang), getLocalizedWord("Уведомления",lang), "notifications_outline")
			notifications_outline.addEntry(new _OutlineEntry(getLocalizedWord("Внесение арендной платы",lang), getLocalizedWord("Уведомления об необходимости внесения арендной платы",lang), "rentpayment_notification", "Provider?type=page&id=rentpayment_notification&page=0"))
			notifications_outline.addEntry(new _OutlineEntry(getLocalizedWord("Расторжение договора",lang), getLocalizedWord("Уведомление о досрочном прекращении действия договора",lang), "endcontract_notification", "Provider?type=page&id=endcontract_notification&page=0"))
			notifications_outline.addEntry(new _OutlineEntry(getLocalizedWord("Регистрация договора",lang), getLocalizedWord("Уведомление о регистрации договора аренды",lang), "newcontract_notification", "Provider?type=page&id=newcontract_notification&page=0"))
			notifications_outline.addEntry(new _OutlineEntry(getLocalizedWord("окончание срока действия договора",lang), getLocalizedWord("Уведомление об окончании срока действия договора аренды",lang), "enddatecontract_notification", "Provider?type=page&id=enddatecontract_notification&page=0"))
			outline.addOutline(notifications_outline)
			list.add(notifications_outline)
			
			def report_outline = new _Outline(getLocalizedWord("Отчеты",lang), getLocalizedWord("Отчеты",lang), "report_outline")
			report_outline.addEntry(new _OutlineEntry(getLocalizedWord("Отчеты",lang), getLocalizedWord("Отчеты",lang), "report", "Provider?type=page&id=report&page=0"))
			outline.addOutline(report_outline)
			list.add(report_outline)
		}
		
		def add_outline = new _Outline(getLocalizedWord("Прочее",lang), getLocalizedWord("Прочее",lang), "add")
		add_outline.addEntry(new _OutlineEntry(getLocalizedWord("Корзина",lang), getLocalizedWord("Корзина",lang), "recyclebin", "Provider?type=page&id=recyclebin"))
		outline.addOutline(add_outline)
		list.add(add_outline)
		
		def archive_outline = new _Outline(getLocalizedWord("Архив",lang), getLocalizedWord("Архив",lang), "archive_outline")
		archive_outline.addEntry(new _OutlineEntry(getLocalizedWord("Архив",lang), getLocalizedWord("Архив",lang), "archive", "Provider?type=page&id=archive"))
		outline.addOutline(archive_outline)
		list.add(archive_outline)
		
		setContent(list)
	}
}
