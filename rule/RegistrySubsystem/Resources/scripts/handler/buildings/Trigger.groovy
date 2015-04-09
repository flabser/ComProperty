package handler.buildings

import jxl.*
import kz.nextbase.script._Database
import kz.nextbase.script._Document
import kz.nextbase.script._Glossary
import kz.nextbase.script._Session
import kz.nextbase.script.events._DoScheduledHandler

class Trigger extends _DoScheduledHandler {


    @Override
    public int doHandler(_Session session) {
        def db = (_Database) session.getCurrentDatabase();

        File xlsFile = new File("Нежилые_здания.xls");

        Workbook workbook = Workbook.getWorkbook(xlsFile);

        Sheet sheet = workbook.getSheet(0);

        println(sheet.getRows());
        println(sheet.getColumns());

        def doc = null;
        def balanceholder = null;
        for (int i = 1; i < sheet.getRows(); i++) {
            doc = new _Document(db);
            doc.setForm("buildings");
            for (int j = 0; j < sheet.getColumns(); j++) {
                 Cell cell = sheet.getCell(j, i);
                 if (cell.type != CellType.EMPTY && cell.cellFormat != null) {
                     switch (j) {
                         case 0:
                             if (cell.getType() != CellType.EMPTY) {
                                 String bin = ((LabelCell) cell).getString();
                                 def col = db.getCollectionOfDocuments("bin='" + bin + "'", false);
                                 if (col && col.entries.size() > 0) {
                                     balanceholder = col.entries.get(0).document
                                 } else {
                                     balanceholder = new _Document(db);
                                     balanceholder.setValueString("bin", bin);
                                     String name = ((LabelCell) sheet.getCell(j + 1, i)).getString();
                                     balanceholder.setValueString("orgfullname", name)
                                     balanceholder.setValueString("orgfullnamekaz", name)
                                     if (name.toLowerCase().contains("коммун")) {
                                         balanceholder.setForm("kgu");
                                         balanceholder.setValueString("form", "kgu")
                                     }
                                     if (name.toLowerCase().contains("акционер")) {
                                         balanceholder.setForm("ao");
                                         balanceholder.setValueString("form", "ao")
                                     }
                                     balanceholder.setViewText(name)
                                     balanceholder.addViewText("")
                                     balanceholder.addViewText(bin)
                                     balanceholder.addViewText("")
                                     balanceholder.addViewText("")
                                     balanceholder.addViewText("")
                                     balanceholder.addViewText("")
                                     if (name.length() > 128) {
                                        balanceholder.addViewText(name.substring(0, 127));
                                     }  else {
                                         balanceholder.addViewText(name);
                                     }
                                     balanceholder.save("[supervisor]")
                                 }
                                 doc.setValueNumber("balanceholder", balanceholder.getDocID())
                             }
                             break;
                         case 3:
                             String invnumber = ((LabelCell) cell).getString()
                             doc.setValueString("invnumber", invnumber)
                             break;
                         case 4:
                             String assignment = ((LabelCell) cell).getString()
                             doc.setValueString("assignment", assignment)
                             break;
                         case 5:
                             String propertycode = ((LabelCell) cell).getString().trim()
                             def propertycode_glos = db.getGlossaryDocument("propertycode", "viewtext='" + propertycode + "'");
                             if (!propertycode_glos) {
                                 propertycode_glos = new _Glossary(db)
                                 propertycode_glos.setForm("propertycode");
                                 propertycode_glos.setValueString("form", "propertycode");
                                 propertycode_glos.setValueString("name", propertycode)
                                 propertycode_glos.setViewText(propertycode)
                                 propertycode_glos.addViewText(propertycode)
                                 propertycode_glos.save("[supervisor]");
                             }
                             doc.addGlossaryField("propertycode", propertycode_glos.docID)
                             break;
                         case 6:
                             Date acceptancedate = ((DateCell) cell).getDate()
                             doc.setValueDate("acceptancedate", acceptancedate)
                             break;
                         case 8:
                             String region = ((LabelCell) cell).getString().trim()
                             def region_glos = db.getGlossaryDocument("region", "viewtext='" + region + "'");
                             if (!region_glos) {
                                 region_glos = new _Glossary(db)
                                 region_glos.setForm("region");
                                 region_glos.setValueString("form", "region");
                                 region_glos.setValueString("name", region)
                                 region_glos.setViewText(region)
                                 region_glos.addViewText(region)
                                 region_glos.save("[supervisor]");
                             }
                             doc.addGlossaryField("region", region_glos.docID)
                             break;
                         case 9:
                             String district = ((LabelCell) cell).getString().trim()
                             def district_glos = db.getGlossaryDocument("district", "viewtext='" + district + "'");
                             if (!district_glos) {
                                 district_glos = new _Glossary(db)
                                 district_glos.setForm("district");
                                 district_glos.setValueString("form", "district");
                                 district_glos.setValueString("name", district)
                                 district_glos.setViewText(district)
                                 district_glos.addViewText(district)
                                 district_glos.save("[supervisor]");
                             }
                             doc.addGlossaryField("district", district_glos.docID)
                             break;
                         case 10:
                             String address = ((LabelCell) cell).getString()
                             doc.setValueString("address", address)
                             break;
                         case 11:
                             //тип документа
                             String regdoc = ((LabelCell) cell).getString() + "\n"
                             //номер
                             cell = (LabelCell) sheet.getCell(j + 1, i)
                             regdoc += cell.getString() + "\n"
                             //дата выдачи
                             cell = sheet.getCell(j + 2, i)
                             regdoc += cell.getContents() + "\n"
                             //кем выдан
                             cell = (LabelCell) sheet.getCell(j + 3, i)
                             regdoc += cell.getString() + "\n"
                             //сведения о техпаспорте
                             cell = (LabelCell) sheet.getCell(j + 4, i)
                             regdoc += cell.getString()
                             doc.setValueString("regdoc", regdoc)
                             break;
                         case 16:
                            /* if (cell.type == CellType.NUMBER) {

                             } else {

                             }*/
                             String originalcost
                             if (cell.type == CellType.NUMBER) {
                                 originalcost  = ((NumberCell) cell).getValue().intValue().toString()
                             } else {
                                 originalcost = ((LabelCell) cell).getString()
                             }
                             doc.setValueString("originalcost", originalcost)
                             break
                         case 17:
                             String cumulativedepreciation
                             if (cell.type == CellType.NUMBER) {
                                 cumulativedepreciation  = ((NumberCell) cell).getValue().intValue().toString()
                             } else {
                                 cumulativedepreciation = ((LabelCell) cell).getString()
                             }
                             doc.setValueString("cumulativedepreciation", cumulativedepreciation)
                             break
                         case 18:
                             String depreciating
                             if (cell.type == CellType.NUMBER) {
                                 depreciating  = ((NumberCell) cell).getValue().intValue().toString()
                             } else {
                                 depreciating = ((LabelCell) cell).getString()
                             }
                             doc.setValueString("depreciating", depreciating)
                             break
                         case 22:
                             String receiptbasisingproperty = ((LabelCell) cell).getString()
                             doc.setValueString("receiptbasisingproperty", receiptbasisingproperty)
                             break
                         case 24:
                             String receiptbasisinbalance = ((LabelCell) cell).getString()
                             doc.setValueString("receiptbasisinbalance", receiptbasisinbalance)
                             break
                         case 25:
                             String comment = ((LabelCell) cell).getString()
                             doc.setValueString("comment", comment)
                             break
                         case 29:
                             String countfloors
                             if (cell.type == CellType.NUMBER) {
                                 countfloors  = ((NumberCell) cell).getValue().intValue().toString()
                             } else {
                                 countfloors = ((LabelCell) cell).getString()
                             }
                             doc.setValueString("countfloors", countfloors)
                             break
                         case 31:
                             String totalarea
                             if (cell.type == CellType.NUMBER) {
                                 totalarea  = ((NumberCell) cell).getValue().intValue().toString()
                             } else {
                                 totalarea = ((LabelCell) cell).getString()
                             }
                             doc.setValueString("totalarea", totalarea)
                             break
                         case 33:
                             String usefularea
                             if (cell.type == CellType.NUMBER) {
                                 usefularea  = ((NumberCell) cell).getValue().intValue().toString()
                             } else {
                                 usefularea = ((LabelCell) cell).getString()
                             }
                             doc.setValueString("usefularea", usefularea)
                             break
                         case 40:
                             String material = ((LabelCell) cell).getString().trim()
                             def material_glos = db.getGlossaryDocument("material", "viewtext='" + material + "'");
                             if (!material_glos) {
                                 material_glos = new _Glossary(db)
                                 material_glos.setForm("material");
                                 material_glos.setValueString("form", "material");
                                 material_glos.setValueString("name", material)
                                 material_glos.setViewText(material)
                                 material_glos.addViewText(material)
                                 material_glos.save("[supervisor]");
                             }
                             doc.addGlossaryField("material", material_glos.docID)
                             break;
                         case 41:
                             String yearconstruction
                             if (cell.type == CellType.NUMBER) {
                                 yearconstruction  = ((NumberCell) cell).getValue().intValue().toString()
                             } else {
                                 yearconstruction = ((LabelCell) cell).getString()
                             }
                             doc.setValueString("yearconstruction", yearconstruction)
                             break
                         case 42:
                             String deterioration
                             if (cell.type == CellType.NUMBER) {
                                 deterioration  = ((NumberCell) cell).getValue().intValue().toString()
                             } else {
                                 deterioration = ((LabelCell) cell).getString()
                             }
                             doc.setValueString("deterioration", deterioration)
                             break
                         default:
                             break
                     }
                 }
                /*if (cell.getType() != CellType.EMPTY) {
                    LabelCell labCell = (LabelCell) cell;
                    String value = labCell.getString().trim();

                }*/
            }
            doc.setValueString("limitdepreciation", "0")
            doc.addGlossaryField("kt", 0)
            doc.addGlossaryField("city", 0)
            doc.addGlossaryField("street", 0)

            doc.setViewText(doc.getValueString('invnumber') + '  ' +  doc.getValueString('description'))
            doc.addViewText(doc.getValueString('invnumber'))
            doc.addViewText(doc.getValueString('description'))
            doc.addViewText(doc.getValueString("totalarea"))
            // doc.setViewDate(doc.getValueDate("acceptancedate"))
            doc.addReader("wish");
            doc.save("[supervisor]")
        }
        return 0;
    }
}
