package accountant.form.uploadupdating

import jxl.*
import kz.flabs.runtimeobj.document.Document
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.events._FormPostSave

import java.text.SimpleDateFormat

class PostSave extends _FormPostSave {

    public void doPostSave(_Session ses, _Document doc) {
        String fileDir = "tmp" + File.separator + "InventLoad" + File.separator + ses.getCurrentUserID() + File.separator + (new Date().time)
        doc.getAttachments("rtfcontent", fileDir)

        File dir = new File(fileDir + File.separator + "1");

        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                if (name.lastIndexOf('.') > 0) {
                    int lastIndex = name.lastIndexOf('.');
                    String str = name.substring(lastIndex);
                    if (str.equals(".xls") || str.equals(".xls")) {
                        return true;
                    }
                }
                log("Accountant: import failed, file extension have to be .xls")
                return false;
            }
        };

        int saved_docs_counter = 0;

        if (dir && dir.isDirectory()) {
            def files = dir.listFiles(fileNameFilter)

            for (File xlsFile : files) {
                Workbook workbook = Workbook.getWorkbook(xlsFile);

                Sheet sheet = workbook.getSheet(0);

                println(sheet.getRows());
                println(sheet.getColumns());

                def docs = null;
                def _doc = null;
                for (int i = 1; i < sheet.getRows(); i++) {
                   // for (int j = 0; j < sheet.getColumns(); j++) {
                    int j = 0;
                    Cell cell = sheet.getCell(j + 1, i);
                    if (cell.type != CellType.EMPTY && cell.cellFormat != null) {
                        println cell.getContents()
                      //  String kof = sheet.getCell(j + 1, i).getContents();
                        String kuf = sheet.getCell(j + 1, i).getContents();
                        try{
                            if (kuf) {
                                String invnumber = sheet.getCell(j + 2, i).getContents();
                                String name = sheet.getCell(j + 3, i).getContents();
                                String propertycode_name = sheet.getCell(j + 4, i).getContents();

                                Date acceptancedate
                                Cell acceptancedateCell = sheet.getCell(j + 5, i)
                                String acceptancedateStr = sheet.getCell(j + 5, i).getContents().replace("/", ".")
                                if (acceptancedateStr.length() == 4) {
                                    acceptancedate = new SimpleDateFormat('yyyy').parse(acceptancedateStr);
                                } else if (acceptancedateStr.length() == 8) {
                                    acceptancedate = new SimpleDateFormat('dd.MM.yy').parse(acceptancedateStr);
                                } else if (acceptancedateStr.length() > 8) {
                                    acceptancedate = new SimpleDateFormat('dd.MM.yyyy').parse(acceptancedateStr);
                                }

                               // String country = ((LabelCell) sheet.getCell(j + 6, i)).getString();
                               // String district = ((LabelCell) sheet.getCell(j + 7, i)).getString();
                                String region = ((LabelCell) sheet.getCell(j + 8, i)).getString();
                                String address = ((LabelCell) sheet.getCell(j + 9, i)).getString();
                                String originalcost = sheet.getCell(j + 10, i).getContents();
                                String balancecost =  sheet.getCell(j + 13, i).getContents();

                                //String receiptbasisingproperty = ((LabelCell) sheet.getCell(j + 15, i)).getString();

                                Document document = new Document(ses.getCurrentDatabase().baseObject, "[supervisor]");
                                _doc = new _Document(document)
                                int kuf_num = kuf.trim().isNumber() ? kuf.trim().toInteger() : 0;
                                if(kuf_num == 101) {
                                    _doc.setForm("furniture")
                                    _doc.setValueString("form", "furniture");
                                }else if(kuf_num == 102){
                                    _doc.setForm("animals")
                                    _doc.setValueString("form", "animals");
                                }else if(kuf_num == 103){
                                    _doc.setForm("sportsequipment")
                                    _doc.setValueString("form", "sportsequipment");
                                } else if(kuf_num == 104){
                                    _doc.setForm("shareblocks")
                                    _doc.setValueString("form", "shareblocks");
                                }else if(kuf_num == 105){
                                    _doc.setForm("equity")
                                    _doc.setValueString("form", "equity");
                                }else if(kuf_num == 106){
                                    _doc.setForm("others")
                                    _doc.setValueString("form", "others");
                                }else if(kuf_num == 201){
                                    _doc.setForm("schoolequipment")
                                    _doc.setValueString("form", "schoolequipment");
                                }else if(kuf_num == 202){
                                    _doc.setForm("officeequipment")
                                    _doc.setValueString("form", "officeequipment");
                                }else if(kuf_num == 203){
                                    _doc.setForm("computerequipment")
                                    _doc.setValueString("form", "computerequipment");
                                }else if(kuf_num == 204){
                                    _doc.setForm("medicalequipment")
                                    _doc.setValueString("form", "medicalequipment");
                                }else if(kuf_num == 205){
                                    _doc.setForm("cookequipment")
                                    _doc.setValueString("form", "cookequipment");
                                }else if(kuf_num == 206){
                                    _doc.setForm("equipmentofcivildefense")
                                    _doc.setValueString("form", "equipmentofcivildefense");
                                }else if(kuf_num == 207){
                                    _doc.setForm("others_equipment")
                                    _doc.setValueString("form", "others_equipment");
                                }else if(kuf_num == 301){
                                    _doc.setForm("buildings")
                                    _doc.setValueString("form", "buildings");
                                }else if(kuf_num == 302){
                                    _doc.setForm("rooms")
                                    _doc.setValueString("form", "rooms");
                                }else if(kuf_num == 303){
                                    _doc.setForm("structures")
                                    _doc.setValueString("form", "structures");
                                }else if(kuf_num == 304){
                                    _doc.setForm("residentialobjects")
                                    _doc.setValueString("form", "residentialobjects");
                                }else if(kuf_num == 305){
                                    _doc.setForm("land")
                                    _doc.setValueString("form", "land");
                                }else if(kuf_num == 306){
                                    _doc.setForm("monument")
                                    _doc.setValueString("form", "monument");
                                }else if(kuf_num == 401){
                                    _doc.setForm("automobile")
                                    _doc.setValueString("form", "automobile");
                                }else if(kuf_num == 4011){
                                    _doc.setForm("automobile")
                                    _doc.setValueString("form", "automobile");
                                }else if(kuf_num == 4012){
                                        _doc.setForm("cargo")
                                        _doc.setValueString("form", "cargo");
                                }else if(kuf_num == 4013){
                                    _doc.setForm("dejtransport")
                                    _doc.setValueString("form", "dejtransport");
                                }else if(kuf_num == 4014){
                                    _doc.setForm("officialtransport")
                                    _doc.setValueString("form", "officialtransport");
                                }else if(kuf_num == 4015){
                                    _doc.setForm("hospitaltransport")
                                    _doc.setValueString("form", "hospitaltransport");
                                }else if(kuf_num == 40161){
                                    _doc.setForm("bus")
                                    _doc.setValueString("form", "bus");
                                }else if(kuf_num == 40162){
                                    _doc.setForm("trolleybus")
                                    _doc.setValueString("form", "trolleybus");
                                }else if(kuf_num == 40163){
                                    _doc.setForm("tram")
                                    _doc.setValueString("form", "tram");
                                }else if(kuf_num == 40164){
                                    _doc.setForm("taxi")
                                    _doc.setValueString("form", "taxi");
                                }else if(kuf_num == 40165){
                                    _doc.setForm("watertransport")
                                    _doc.setValueString("form", "watertransport");
                                }else if(kuf_num == 402){
                                    _doc.setForm("specialequipment")
                                    _doc.setValueString("form", "specialequipment");
                                }else if(kuf_num == 403){
                                    _doc.setForm("motorcycle")
                                    _doc.setValueString("form", "motorcycle");
                                }else if(kuf_num == 501){
                                    _doc.setForm("objectreservedfund")
                                    _doc.setValueString("form", "objectreservedfund");
                                }else if(kuf_num == 5021){
                                    _doc.setForm("bombproof")
                                    _doc.setValueString("form", "bombproof");
                                }else if(kuf_num == 5022){
                                    _doc.setForm("factory")
                                    _doc.setValueString("form", "factory");
                                }else if(kuf_num == 5023){
                                    _doc.setForm("combines")
                                    _doc.setValueString("form", "combines");
                                }else if(kuf_num == 5024){
                                    _doc.setForm("airport")
                                    _doc.setValueString("form", "airport");
                                }else if(kuf_num == 5025){
                                    _doc.setForm("transitions")
                                    _doc.setValueString("form", "transitions");
                                }else if(kuf_num == 601){
                                    _doc.setForm("billboard")
                                    _doc.setValueString("form", "billboard");
                                }else if(kuf_num == 602){
                                    _doc.setForm("columns")
                                    _doc.setValueString("form", "columns");
                                }else if(kuf_num == 6031){
                                    _doc.setForm("electricnetworks")
                                    _doc.setValueString("form", "electricnetworks");
                                }else if(kuf_num == 6032){
                                    _doc.setForm("thermalnetworks")
                                    _doc.setValueString("form", "thermalnetworks");
                                }else if(kuf_num == 6033){
                                    _doc.setForm("gas")
                                    _doc.setValueString("form", "gas");
                                } else if(kuf_num == 6034){
                                    _doc.setForm("watersystem")
                                    _doc.setValueString("form", "watersystem");
                                }else if(kuf_num == 6035){
                                    _doc.setForm("drain")
                                    _doc.setValueString("form", "drain");
                                }else if(kuf_num == 604){
                                    _doc.setForm("road")
                                    _doc.setValueString("form", "road");
                                }else if(kuf_num == 605){
                                    _doc.setForm("parking")
                                    _doc.setValueString("form", "parking");
                                }else continue;

                                 /*
                                String part_kof = kof.substring(0, 7)

                                if (part_kof.matches("150\\.310.*")) {
                                    _doc.setForm("furniture")
                                    _doc.setValueString("form", "furniture");
                                } else if (part_kof.matches("(161\\.0(14|30))|(180\\.000).*")) {
                                    _doc.setForm("animals")
                                    _doc.setValueString("form", "animals");
                                } else if (part_kof.matches("150\\.323.*")) {
                                    _doc.setForm("sportsequipment")
                                    _doc.setValueString("form", "sportsequipment");
                                } else if (part_kof.matches("(150)|(180)|(2[0-6]0).*")) {
                                    _doc.setForm("others")
                                    _doc.setValueString("form", "others");
                                } else if (part_kof.matches("142\\.282.*")) {
                                    _doc.setForm("officeequipment")
                                    _doc.setValueString("form", "officeequipment");
                                } else if (part_kof.matches("142\\.26[2-5].*")) {
                                    _doc.setForm("computerequipment")
                                    _doc.setValueString("form", "computerequipment");
                                } else if (part_kof.matches("142\\.((2[0,5-9])|(32)).*")) {
                                    _doc.setForm("others_equipment")
                                    _doc.setValueString("form", "others_equipment");
                                } else if (part_kof.matches("122\\.*")) {
                                    _doc.setForm("buildings")
                                    _doc.setValueString("form", "buildings");
                                } else if (part_kof.matches("121\\.*")) {
                                    _doc.setForm("residentialobjects")
                                    _doc.setValueString("form", "residentialobjects");
                                } else if (part_kof.matches("11[0-5]\\.*")) {
                                    _doc.setForm("land")
                                    _doc.setValueString("form", "land");
                                } else if (kof.matches("14[0,1]\\.((00)|(29))[0,1]0[0, 2][0-4].*")) {
                                    _doc.setForm("automobile")
                                    _doc.setValueString("form", "automobile");
                                } else if (part_kof.matches("141\\.2910[3,4][0-4].*")) {
                                    _doc.setForm("cargo")
                                    _doc.setValueString("form", "cargo");
                                } else if (kof.matches("141\\.(29105[0-9]).*|(2920).*|(30[0-8]).*|(28).*")) {
                                    _doc.setForm("specialequipment")
                                    _doc.setValueString("form", "specialequipment");
                                } else if (kof.matches("141\\.309[1-9].*")) {
                                    _doc.setForm("motorcycle")
                                    _doc.setValueString("form", "motorcycle");
                                }else if (kof.matches(" 131\\.(12|2[2-3]).*")) {
                                    _doc.setForm("watersystem")
                                    _doc.setValueString("form", "watersystem");
                                }else if (kof.matches("131\\.[1-2]4.*")) {
                                    _doc.setForm("columns")
                                    _doc.setValueString("form", "columns");
                                }else if (kof.matches("131\\.21.*")) {
                                    _doc.setForm("gas")
                                    _doc.setValueString("form", "gas");
                                }else if (kof.matches("132\\.[1-2][0-3].*")) {
                                    _doc.setForm("road")
                                    _doc.setValueString("form", "road");
                                }
                                 */

                                //_doc.setValueString("address", address);
                                _doc.setValueString("invnumber", invnumber);
                                _doc.setValueString("description", name);
                                _doc.setValueString("objectname", name);
                                _doc.setValueString("address", region + ", " + address);
                                _doc.setValueString("propertycode_name", propertycode_name);
                                _doc.addDateField("acceptancedate", acceptancedate)
                                _doc.setValueString("originalcost", originalcost);
                                _doc.setValueString("balancecost", balancecost);
                                //_doc.setValueString("balancecost", balancecost);
                                //_doc.setValueString("receiptbasisingproperty", receiptbasisingproperty);
                                _doc.addEditor("[operator]")
                                _doc.addEditor("[supervisor]")
                                _doc.addReader("[operator]")
                                _doc.addReader("[supervisor]")
                                _doc.addReader("[observer]")
                                _doc.addEditor(ses.getCurrentAppUser().getUserID())
                                _doc.addEditor("[observer]")
                                _doc.setAuthor(ses.getCurrentAppUser().getUserID());

                                //_doc.setValueString("region", region);
                                //_doc.setValueString("district", district);



                                _doc.setViewText(name)
                                _doc.addViewText(name)
                                _doc.addViewText(name)
                                _doc.addViewText(doc.getValueString("originalcost"))
                                _doc.setViewDate(new Date());

                                /*  def col_prop_code = ses.currentDatabase.getCollectionOfGlossaries("name = '${propertycode}'", 0, 0)
                                  if (col_prop_code && col_prop_code.count > 0) {
                                      def prop_code_glos = ((_ViewEntry)col_prop_code.getEntries()[0])?.document
                                      _doc.addViewText(ses.currentDatabase.getGlossaryCustomFieldValueByDOCID(prop_code_glos?.getDocID(), "code") )
                                      _doc.setValueString("propertycode", prop_code_glos?.getDocID());
                                  }*/
                                //_doc.setViewDate(acceptancedate)
                                _doc.save("[supervisor]")
                                saved_docs_counter++;
                            }
                        }catch (Exception e){
                            log("Accountant: doc wasn't saved. Row:$i \n" + e.toString())
                        }
                    }
                }
            }
        }
        log("Accountant: import by user ${ses.getCurrentUserID()} finished. Total imported docs number = $saved_docs_counter") ;
    }
}