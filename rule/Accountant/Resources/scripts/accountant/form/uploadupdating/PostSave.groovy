package accountant.form.uploadupdating

import jxl.*
import kz.flabs.runtimeobj.document.Document
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.events._FormPostSave

import java.text.SimpleDateFormat

class PostSave extends _FormPostSave {

    public void doPostSave(_Session ses, _Document doc) {
        doc.getAttachments("rtfcontent", "InventLoad")
        File dir = new File("InventLoad" + File.separator + "1");

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
                return false;
            }
        };


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
                    for (int j = 0; j < sheet.getColumns(); j++) {
                        Cell cell = sheet.getCell(j, i);
                        if (cell.type != CellType.EMPTY && cell.cellFormat != null) {
                            switch (j) {
                                case 0:
                                    if (cell.getType() != CellType.EMPTY) {
                                        println cell.getContents()
                                        String kof = sheet.getCell(j + 1, i).getContents();
                                        if (kof) {
                                            //String kof = ((LabelCell) cell).getString();
                                            //String invnumber = ((LabelCell) sheet.getCell(j + 1, i)).getString();
                                            String invnumber = sheet.getCell(j + 2, i).getContents();
                                            String name = sheet.getCell(j + 3, i).getContents();
                                            //String name = ((LabelCell) sheet.getCell(j + 2, i)).getString();
                                            //String propertycode = ((LabelCell) sheet.getCell(j + 3, i)).getString();
                                            //Date acceptancedate = ((DateCell) sheet.getCell(j + 4, i)).date;

                                            Date acceptancedate
                                            Cell acceptancedateCell = sheet.getCell(j + 4, i)
                                            String acceptancedateStr = sheet.getCell(j + 4, i).getContents()
                                            if (acceptancedateStr.length() == 4) {
                                                acceptancedate = new SimpleDateFormat('yyyy').parse(acceptancedateCell.getContents());
                                            } else if (acceptancedateStr.length() == 8) {
                                                acceptancedate = new SimpleDateFormat('dd.MM.yy').parse(acceptancedateCell.getContents());
                                            } else if (acceptancedateStr.length() > 8) {
                                                acceptancedate = new SimpleDateFormat('dd.MM.yyyy').parse(acceptancedateCell.getContents());
                                            }
                                  /*          switch (cell.cellFormat) {
                                                case DateCell:
                                                    acceptancedate = ((DateCell) acceptancedateCell).date;
                                                    break;
                                                case LabelCell:
                                                    acceptancedate = new SimpleDateFormat('yyyy').parse(acceptancedateCell.getContents());
                                                    break;
                                                case NumberCell:
                                                    acceptancedate = new SimpleDateFormat('yyyy').parse(acceptancedateCell.getContents());
                                                    break;
                                            }*/

                                            //String region = ((LabelCell) sheet.getCell(j + 6, i)).getString();
                                            //String district = ((LabelCell) sheet.getCell(j + 7, i)).getString();
                                            //String address = ((LabelCell) sheet.getCell(j + 8, i)).getString();
                                            String originalcost = ((NumberCell) sheet.getCell(j + 6, i)).value.longValue();
                                            //String balancecost = ((NumberCell) sheet.getCell(j + 12, i)).value.longValue();
                                            //String receiptbasisingproperty = ((LabelCell) sheet.getCell(j + 15, i)).getString();

                                            Document document = new Document(ses.getCurrentDatabase().baseObject, "[supervisor]");
                                            _doc = new _Document(document)

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

                                            //_doc.setValueString("address", address);
                                            _doc.setValueString("invnumber", invnumber);
                                            _doc.setValueString("description", name);
                                            _doc.setValueString("objectname", name);
                                            _doc.setValueString("originalcost", originalcost);
                                            //_doc.setValueString("balancecost", balancecost);
                                            //_doc.setValueString("receiptbasisingproperty", receiptbasisingproperty);
                                            _doc.addEditor("[operator]")
                                            _doc.addEditor("[supervisor]")
                                            _doc.addReader("[operator]")
                                            _doc.addReader("[supervisor]")
                                            _doc.addReader("[observer]")
                                            _doc.addEditor("[observer]")

                                            //_doc.setValueString("region", region);
                                            //_doc.setValueString("district", district);

                                            _doc.addDateField("acceptancedate", acceptancedate)

                                            _doc.setViewText(name)
                                            _doc.addViewText(name)
                                            _doc.addViewText(name)
                                            _doc.addViewText(doc.getValueString("originalcost"))

                                            /*  def col_prop_code = ses.currentDatabase.getCollectionOfGlossaries("name = '${propertycode}'", 0, 0)
                                              if (col_prop_code && col_prop_code.count > 0) {
                                                  def prop_code_glos = ((_ViewEntry)col_prop_code.getEntries()[0])?.document
                                                  _doc.addViewText(ses.currentDatabase.getGlossaryCustomFieldValueByDOCID(prop_code_glos?.getDocID(), "code") )
                                                  _doc.setValueString("propertycode", prop_code_glos?.getDocID());
                                              }*/
                                            _doc.setViewDate(acceptancedate)
                                            _doc.save("[supervisor]")
                                        }
                                    }
                                    break;
                                default:
                                    break
                            }
                        }
                    }
                }
            }

        }
    }
}