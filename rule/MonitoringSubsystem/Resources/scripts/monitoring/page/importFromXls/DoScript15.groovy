package monitoring.page.importFromXls

import kz.nextbase.script._Document
import kz.nextbase.script._Session;
import kz.nextbase.script._WebFormData;
import kz.nextbase.script.events._DoScript;
import jxl.*;
import java.io.File.*;
import kz.nextbase.script._Helper
/**
 * 
 * @author Bekzat
 * 15ый лист
 * Комментарии не убирать, ничего не трогать!
 */
class DoScript15 extends _DoScript {

	@Override
	public void doProcess(_Session session, _WebFormData webFormData, String lang) {
		// TODO Auto-generated method stub
		// OpenFile
		File file = new File("rule/MonitoringSubsystem/Resources/scripts/page/importFromXls/Транспорт по учреждениям.xls");
		Workbook excelFile = Workbook.getWorkbook(file); 
		// Листы
		Sheet sheet1 = excelFile.getSheet(15);
		
		/*
		for(int i=13; i<=21; i++){
			// col - column
			 
			Cell model = sheet1.getCell(1, i);
			
			println(model.getContents())
			println(sheet1.getCell(11, i).getContents())
			
			def doc = new _Document(session.getCurrentDatabase());
			doc.addStringField("model", model.getContents())
			doc.addStringField("yearrelease", sheet1.getCell(3, i).getContents())
			doc.addStringField("bodynumber", sheet1.getCell(6, i).getContents())
			doc.addStringField("enginenumber", sheet1.getCell(9, i).getContents())
			doc.addStringField("originalcost", sheet1.getCell(11, i).getContents())
			doc.addStringField("namerus", "ГКП на ПХВ Аппарата акима города Алматы'Специализированный комбинат ритуальных услуг города Алматы'" )
			doc.addStringField("invnumber", "0")
			
			if(sheet1.getCell(4, i).getType() == CellType.DATE){
				Date cDate = ((DateCell)sheet1.getCell(4, i)).getDate()
				doc.addDateField("acceptancedate", cDate)
			}else{
 
			}
			
			doc.setForm("automobile");
			doc.addStringField("bin", webFormData.getValueSilently("bin"))
			
			doc.addStringField("namekaz", webFormData.getValueSilently("namekaz"))
			
			doc.addStringField("description","0")
			doc.addStringField("propertycode", "28")
			doc.addGlossaryField("region", 35)
			doc.addGlossaryField("city", 36)
			doc.addGlossaryField("district", 37)
			doc.addGlossaryField("street", 38)
			doc.addStringField("home", webFormData.getValueSilently("home"))
			doc.addStringField("appartament", webFormData.getValueSilently("appartament"))
			doc.addStringField("regdoc", webFormData.getValueSilently("regdoc"))
			doc.addStringField("estimatedcost", webFormData.getValueSilently("estimatedcost"))
			doc.addStringField("receiptbasisingproperty", webFormData.getValueSilently("receiptbasisingproperty"))
			doc.addStringField("receiptbasisinbalance", webFormData.getValueSilently("receiptbasisinbalance"))
			doc.addStringField("cumulativedepreciation", webFormData.getValueSilently("cumulativedepreciation"))
			doc.addStringField("deterioration", webFormData.getValueSilently("deterioration"))
			doc.addStringField("repair", webFormData.getValueSilently("repair"))
			
			doc.addStringField("category", webFormData.getValueSilently("category"))
			doc.addStringField("color", webFormData.getValueSilently("color"))
			doc.addStringField("powerengine", webFormData.getValueSilently("powerengine"))
			doc.addStringField("weightwithoutload", webFormData.getValueSilently("weightwithoutload"))
			doc.addStringField("maxload", webFormData.getValueSilently("maxload"))
			doc.addStringField("depreciating",webFormData.getValueSilently("depreciating"))
			doc.addStringField("pledge",webFormData.getValueSilently("pledge"))
			doc.addStringField("arrestingbyacourtdecision",webFormData.getValueSilently("arrestingbyacourtdecision"))
			doc.addStringField("legalclaim",webFormData.getValueSilently("legalclaim"))
			doc.addStringField("orderofremovalfrombalance",webFormData.getValueSilently("orderofremovalfrombalance"))
			
			doc.addStringField("volumeengine",webFormData.getValueSilently("volumeengine"))
			doc.addStringField("condition",webFormData.getValueSilently("condition"))
			doc.addStringField("fueltype",webFormData.getValueSilently("fueltype"))
			doc.addStringField("expenditurefuel",webFormData.getValueSilently("expenditurefuel"))
			doc.addStringField("carrun",webFormData.getValueSilently("carrun"))
			doc.addStringField("normperiodmaintenance",webFormData.getValueSilently("normperiodmaintenance"))
	
			
			doc.addStringField("author", "robot")
			//doc.addFile("rtfcontent", webFormData)
	
			doc.setViewText(doc.getValueString('invnumber') + '  ' +  doc.getValueString('description'))
			doc.addViewText(doc.getValueString('invnumber'))
			doc.addViewText(doc.getValueString('description'))
			doc.addViewText(doc.getValueString("estimatedcost"))
			//doc.setViewNumber(doc.getValueNumber("vnnumber"))
			//doc.setViewDate(doc.getValueDate("acceptancedate"))
			 doc.addViewText(doc.getValueString("model"))
			doc.save("[observer]"); 
		}
		 */
		/*
		// грузовые
		for(int i=24; i<=37; i++){
			Cell model = sheet1.getCell(1, i);
			
			println(model.getContents())
			
			def doc = new _Document(session.getCurrentDatabase());
			doc.addStringField("model", model.getContents())
			doc.addStringField("yearrelease", sheet1.getCell(3, i).getContents())
			doc.addStringField("bodynumber", sheet1.getCell(6, i).getContents())
			doc.addStringField("enginenumber", sheet1.getCell(9, i).getContents())
			doc.addStringField("originalcost", sheet1.getCell(11, i).getContents())
			doc.addStringField("namerus", "ГКП на ПХВ Аппарата акима города Алматы'Специализированный комбинат ритуальных услуг города Алматы'" )
			doc.addStringField("invnumber", "0")
			
			if(sheet1.getCell(4, i).getType() == CellType.DATE){
				Date cDate = ((DateCell)sheet1.getCell(4, i)).getDate()
				doc.addDateField("acceptancedate", cDate)
			}else{
 
			}
			
			doc.setForm("cargo");
			doc.addStringField("bin", webFormData.getValueSilently("bin"))
			
			doc.addStringField("namekaz", webFormData.getValueSilently("namekaz"))
			
			doc.addStringField("description","0")
			doc.addStringField("propertycode", "28")
			doc.addGlossaryField("region", 35)
			doc.addGlossaryField("city", 36)
			doc.addGlossaryField("district", 37)
			doc.addGlossaryField("street", 38)
			doc.addStringField("home", webFormData.getValueSilently("home"))
			doc.addStringField("appartament", webFormData.getValueSilently("appartament"))
			doc.addStringField("regdoc", webFormData.getValueSilently("regdoc"))
			doc.addStringField("originalcost", webFormData.getValueSilently("originalcost"))
			doc.addStringField("estimatedcost", webFormData.getValueSilently("estimatedcost"))
			doc.addStringField("residualcost", webFormData.getValueSilently("residualcost"))
			doc.addStringField("receiptbasisingproperty", webFormData.getValueSilently("receiptbasisingproperty"))
			doc.addStringField("receiptbasisinbalance", webFormData.getValueSilently("receiptbasisinbalance"))
			doc.addStringField("cumulativedepreciation", webFormData.getValueSilently("cumulativedepreciation"))
			doc.addStringField("deterioration", webFormData.getValueSilently("deterioration"))
			doc.addStringField("repair", webFormData.getValueSilently("repair"))
			
			doc.addStringField("category", webFormData.getValueSilently("category"))
			doc.addStringField("color", webFormData.getValueSilently("color"))
			doc.addStringField("powerengine", webFormData.getValueSilently("powerengine"))
			doc.addStringField("weightwithoutload", webFormData.getValueSilently("weightwithoutload"))
			doc.addStringField("maxload", webFormData.getValueSilently("maxload"))
			doc.addStringField("depreciating",webFormData.getValueSilently("depreciating"))
			doc.addStringField("pledge",webFormData.getValueSilently("pledge"))
			doc.addStringField("arrestingbyacourtdecision",webFormData.getValueSilently("arrestingbyacourtdecision"))
			doc.addStringField("legalclaim",webFormData.getValueSilently("legalclaim"))
			doc.addStringField("orderofremovalfrombalance",webFormData.getValueSilently("orderofremovalfrombalance"))
			
			doc.addStringField("volumeengine",webFormData.getValueSilently("volumeengine"))
			doc.addStringField("condition",webFormData.getValueSilently("condition"))
			doc.addStringField("fueltype",webFormData.getValueSilently("fueltype"))
			doc.addStringField("expenditurefuel",webFormData.getValueSilently("expenditurefuel"))
			doc.addStringField("carrun",webFormData.getValueSilently("carrun"))
			doc.addStringField("normperiodmaintenance",webFormData.getValueSilently("normperiodmaintenance"))
	
			
			doc.addStringField("author", "robot")
			//doc.addFile("rtfcontent", webFormData)
	
			doc.setViewText(doc.getValueString('invnumber') + '  ' +  doc.getValueString('description'))
			doc.addViewText(doc.getValueString('invnumber'))
			doc.addViewText(doc.getValueString('description'))
			doc.addViewText(doc.getValueString("estimatedcost"))
			doc.addViewText(doc.getValueString("model"))
			doc.save("[observer]"); 
		}
		*/
		/*
		// Пассажирские (автобусы ритуальные)
		for(int i=40; i<=46; i++){
			Cell model = sheet1.getCell(1, i);
			
			println(model.getContents())
			
			def doc = new _Document(session.getCurrentDatabase());
			doc.addStringField("model", model.getContents())
			doc.addStringField("yearrelease", sheet1.getCell(3, i).getContents())
			doc.addStringField("bodynumber", sheet1.getCell(6, i).getContents())
			doc.addStringField("enginenumber", sheet1.getCell(9, i).getContents())
			doc.addStringField("originalcost", sheet1.getCell(11, i).getContents())
			doc.addStringField("namerus", "ГКП на ПХВ Аппарата акима города Алматы'Специализированный комбинат ритуальных услуг города Алматы'" )
			doc.addStringField("invnumber", "0")
			
			if(sheet1.getCell(4, i).getType() == CellType.DATE){
				Date cDate = ((DateCell)sheet1.getCell(4, i)).getDate()
				doc.addDateField("acceptancedate", cDate)
			}else{
 
			}
			
			doc.setForm("bus");
			doc.addStringField("bin", webFormData.getValueSilently("bin"))
			
			doc.addStringField("namekaz", webFormData.getValueSilently("namekaz"))
			
			doc.addStringField("description","0")
			doc.addStringField("propertycode", "28")
			doc.addGlossaryField("region", 35)
			doc.addGlossaryField("city", 36)
			doc.addGlossaryField("district", 37)
			doc.addGlossaryField("street", 38)
			doc.addStringField("home", webFormData.getValueSilently("home"))
			doc.addStringField("appartament", webFormData.getValueSilently("appartament"))
			doc.addStringField("regdoc", webFormData.getValueSilently("regdoc"))
			doc.addStringField("originalcost", webFormData.getValueSilently("originalcost"))
			doc.addStringField("estimatedcost", webFormData.getValueSilently("estimatedcost"))
			doc.addStringField("residualcost", webFormData.getValueSilently("residualcost"))
			doc.addStringField("receiptbasisingproperty", webFormData.getValueSilently("receiptbasisingproperty"))
			doc.addStringField("receiptbasisinbalance", webFormData.getValueSilently("receiptbasisinbalance"))
			doc.addStringField("cumulativedepreciation", webFormData.getValueSilently("cumulativedepreciation"))
			doc.addStringField("deterioration", webFormData.getValueSilently("deterioration"))
			doc.addStringField("repair", webFormData.getValueSilently("repair"))
			
			doc.addStringField("category", webFormData.getValueSilently("category"))
			doc.addStringField("color", webFormData.getValueSilently("color"))
			doc.addStringField("powerengine", webFormData.getValueSilently("powerengine"))
			doc.addStringField("weightwithoutload", webFormData.getValueSilently("weightwithoutload"))
			doc.addStringField("maxload", webFormData.getValueSilently("maxload"))
			doc.addStringField("depreciating",webFormData.getValueSilently("depreciating"))
			doc.addStringField("pledge",webFormData.getValueSilently("pledge"))
			doc.addStringField("arrestingbyacourtdecision",webFormData.getValueSilently("arrestingbyacourtdecision"))
			doc.addStringField("legalclaim",webFormData.getValueSilently("legalclaim"))
			doc.addStringField("orderofremovalfrombalance",webFormData.getValueSilently("orderofremovalfrombalance"))
			
			doc.addStringField("volumeengine",webFormData.getValueSilently("volumeengine"))
			doc.addStringField("condition",webFormData.getValueSilently("condition"))
			doc.addStringField("fueltype",webFormData.getValueSilently("fueltype"))
			doc.addStringField("expenditurefuel",webFormData.getValueSilently("expenditurefuel"))
			doc.addStringField("carrun",webFormData.getValueSilently("carrun"))
			doc.addStringField("normperiodmaintenance",webFormData.getValueSilently("normperiodmaintenance"))
	
			
			doc.addStringField("author", "robot")
			//doc.addFile("rtfcontent", webFormData)
	
			doc.setViewText(doc.getValueString('invnumber') + '  ' +  doc.getValueString('description'))
			doc.addViewText(doc.getValueString('invnumber'))
			doc.addViewText(doc.getValueString('description'))
			doc.addViewText(doc.getValueString("estimatedcost"))
			doc.addViewText(doc.getValueString("model"))
			doc.save("[observer]");
		}
		*/
		//Специальная техника
		for(int i=49; i<=58; i++){
			Cell model = sheet1.getCell(1, i);
			
			println(model.getContents())
			
			def doc = new _Document(session.getCurrentDatabase());
			doc.addStringField("model", model.getContents())
			doc.addStringField("yearrelease", sheet1.getCell(3, i).getContents())
			doc.addStringField("bodynumber", sheet1.getCell(6, i).getContents())
			doc.addStringField("enginenumber", sheet1.getCell(9, i).getContents())
			doc.addStringField("originalcost", sheet1.getCell(11, i).getContents())
			doc.addStringField("namerus", "ГКП на ПХВ Аппарата акима города Алматы'Специализированный комбинат ритуальных услуг города Алматы'" )
			doc.addStringField("invnumber", "0")
			
			if(sheet1.getCell(4, i).getType() == CellType.DATE){
				Date cDate = ((DateCell)sheet1.getCell(4, i)).getDate()
				doc.addDateField("acceptancedate", cDate)
			}else{
 
			}
			
			doc.setForm("specialequipment");
			doc.addStringField("bin", webFormData.getValueSilently("bin"))
			
			doc.addStringField("namekaz", webFormData.getValueSilently("namekaz"))
			
			doc.addStringField("description",model.getContents())
			doc.addStringField("propertycode", "28")
			doc.addGlossaryField("region", 35)
			doc.addGlossaryField("city", 36)
			doc.addGlossaryField("district", 37)
			doc.addGlossaryField("street", 38)
			doc.addStringField("home", webFormData.getValueSilently("home"))
			doc.addStringField("appartament", webFormData.getValueSilently("appartament"))
			doc.addStringField("regdoc", webFormData.getValueSilently("regdoc"))
			doc.addStringField("originalcost", webFormData.getValueSilently("originalcost"))
			doc.addStringField("estimatedcost", webFormData.getValueSilently("estimatedcost"))
			doc.addStringField("residualcost", webFormData.getValueSilently("residualcost"))
			doc.addStringField("receiptbasisingproperty", webFormData.getValueSilently("receiptbasisingproperty"))
			doc.addStringField("receiptbasisinbalance", webFormData.getValueSilently("receiptbasisinbalance"))
			doc.addStringField("cumulativedepreciation", webFormData.getValueSilently("cumulativedepreciation"))
			doc.addStringField("deterioration", webFormData.getValueSilently("deterioration"))
			doc.addStringField("repair", webFormData.getValueSilently("repair"))
			
			doc.addStringField("category", webFormData.getValueSilently("category"))
			doc.addStringField("color", webFormData.getValueSilently("color"))
			doc.addStringField("powerengine", webFormData.getValueSilently("powerengine"))
			doc.addStringField("weightwithoutload", webFormData.getValueSilently("weightwithoutload"))
			doc.addStringField("maxload", webFormData.getValueSilently("maxload"))
			doc.addStringField("depreciating",webFormData.getValueSilently("depreciating"))
			doc.addStringField("pledge",webFormData.getValueSilently("pledge"))
			doc.addStringField("arrestingbyacourtdecision",webFormData.getValueSilently("arrestingbyacourtdecision"))
			doc.addStringField("legalclaim",webFormData.getValueSilently("legalclaim"))
			doc.addStringField("orderofremovalfrombalance",webFormData.getValueSilently("orderofremovalfrombalance"))
			
			doc.addStringField("volumeengine",webFormData.getValueSilently("volumeengine"))
			doc.addStringField("condition",webFormData.getValueSilently("condition"))
			doc.addStringField("fueltype",webFormData.getValueSilently("fueltype"))
			doc.addStringField("expenditurefuel",webFormData.getValueSilently("expenditurefuel"))
			doc.addStringField("carrun",webFormData.getValueSilently("carrun"))
			doc.addStringField("normperiodmaintenance",webFormData.getValueSilently("normperiodmaintenance"))
	
			
			doc.addStringField("author", "robot")
			//doc.addFile("rtfcontent", webFormData)
	
			doc.setViewText(doc.getValueString('invnumber') + '  ' +  doc.getValueString('description'))
			doc.addViewText(doc.getValueString('invnumber'))
			doc.addViewText(doc.getValueString('description'))
			doc.addViewText(doc.getValueString("estimatedcost"))
			doc.addViewText(doc.getValueString("model"))
			doc.save("[observer]");
		}
	}
}
