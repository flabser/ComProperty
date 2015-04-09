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
 * Комментарии не убирать, ничего не трогать! Одноразовый код
 * 5- 14 лист
 */
class DoScript extends _DoScript {

	@Override
	public void doProcess(_Session session, _WebFormData webFormData, String lang) {
		// OpenFile
		File file = new File("rule/MonitoringSubsystem/Resources/scripts/page/importFromXls/Транспорт по учреждениям.xls");
		Workbook excelFile = Workbook.getWorkbook(file); 
		// Листы
		Sheet sheet1 = excelFile.getSheet(5);
		
		// 13, for(int i=9; i<=10; i++){ automobile
		// 13, for(int i=13; i<=13; i++){ 
		// 12, for(int i=9; i<=9; i++){ automobile
		// 12, for(int i=12; i<=13; i++){ cargo
		// 12, for(int i=16; i<=16; i++){ bus
		// 11, for(int i=10; i<=11; i++){ automobile
		// 10, for(int i=11; i<=11; i++){ automobile
		// 9, for(int i=10; i<=10; i++){ automobile
		// 8, for(int i=17; i<=17; i++){  officialtransport
		// 6, for(int i=10; i<=11; i++){  automobile
		// 6, for(int i=17; i<=17; i++){ officialtransport
		// 5, for(int i=9; i<=9; i++){ automobile
		// 5, 
		
		// specialequipment
		for(int i=14; i<=14; i++){ 
			// col - column
			 
			Cell model = sheet1.getCell(1, i);
			println(sheet1.getCell(8, i).getContents())
			println(sheet1.getCell(11, i).getContents())
						
			def doc = new _Document(session.getCurrentDatabase());
			doc.addStringField("model", model.getContents())
			doc.addStringField("yearrelease", sheet1.getCell(2, i).getContents())
			doc.addStringField("bodynumber", sheet1.getCell(5, i).getContents())
			doc.addStringField("enginenumber", sheet1.getCell(8, i).getContents())
			doc.addStringField("originalcost", sheet1.getCell(9, i).getContents())
			doc.addStringField("residualcost", sheet1.getCell(10, i).getContents())
			doc.addStringField("namerus", "КГУ Гимназия № 46" )
			doc.addStringField("invnumber", sheet1.getCell(4, i).getContents())
			
			if(sheet1.getCell(3, i).getType() == CellType.DATE){
				Date cDate = ((DateCell)sheet1.getCell(3, i)).getDate()
				doc.addDateField("acceptancedate", cDate)
			} 
			
			doc.setForm("bus");
			doc.addStringField("bin", webFormData.getValueSilently("bin"))
			
			doc.addStringField("namekaz", webFormData.getValueSilently("namekaz"))
			
			doc.addStringField("description", "0")
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
		 
	}
}
