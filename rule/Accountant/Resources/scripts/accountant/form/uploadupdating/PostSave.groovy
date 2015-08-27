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
//        String fileDir = "tmp"
        doc.getAttachments("rtfcontent", fileDir)

        File dir = new File(fileDir + File.separator + "1");
//        File dir = new File(fileDir);

        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                return name.endsWith(".xls");
            }
        };

        int saved_docs_counter = 0;
        StringBuilder defectRows = new StringBuilder();
        def s = File.separator;
        def path = (new File("")).getAbsolutePath() +
                "${s}rule${s}Accountant${s}Resources${s}scripts${s}accountant${s}resources${s}kuf.properties"
        def input = new FileInputStream(path);
        Properties kufProp = new Properties();
        kufProp.load(input);

        if (dir && dir.isDirectory()) {
            def files = dir.listFiles(fileNameFilter)

            for (File xlsFile : files) {
                Workbook workbook = Workbook.getWorkbook(xlsFile);
                Sheet sheet = workbook.getSheet(0);

                for (int i = 1; i < sheet.getRows(); i++) {
                    String kuf = sheet.getCell(1, i).getContents();
                    Cell cell = sheet.getCell(1, i);
                    if (cell.type == CellType.EMPTY || cell.cellFormat == null || !kuf ) {
                        continue;
                    }

                    try{
                        String invnumber = sheet.getCell(2, i).getContents();
                        String name = sheet.getCell(3, i).getContents();
                        String propertycode_name = sheet.getCell(4, i).getContents();

                        Date acceptancedate =  null;
                        String acceptancedateStr = sheet.getCell(5, i).getContents().replace("/", ".")
                        switch (acceptancedateStr.length()) {
                            case 4: acceptancedate = new SimpleDateFormat('yyyy').parse(acceptancedateStr); break;
                            case 8: acceptancedate = new SimpleDateFormat('dd.MM.yy').parse(acceptancedateStr); break;
                            case 10: acceptancedate = new SimpleDateFormat('dd.MM.yyyy').parse(acceptancedateStr); break;
                        }

                        String region = ((LabelCell) sheet.getCell(8, i)).getString();
                        String address = ((LabelCell) sheet.getCell(9, i)).getString();
                        String originalcost = sheet.getCell(10, i).getContents();
                        String balancecost =  sheet.getCell(13, i).getContents();

                        Document document = new Document(ses.getCurrentDatabase().baseObject, "[supervisor]");
                        def _doc = new _Document(document);

                        String kufVal = kufProp.getProperty(kuf.trim());
                        if(kufVal == null){
                            defectRows.append("${i} ");   //throw new IllegalArgumentException("kuf value " + kuf + " not found. row = " + i);
                            continue;
                        };

                        _doc.setForm(kufVal)
                        _doc.setValueString("form", kufVal);

                        _doc.setValueString("invnumber", invnumber);
                        _doc.setValueString("description", name);
                        _doc.setValueString("objectname", name);
                        _doc.setValueString("address", region + ", " + address);
                        _doc.setValueString("propertycode_name", propertycode_name);
                        _doc.addDateField("acceptancedate", acceptancedate)
                        _doc.setValueString("originalcost", originalcost);
                        _doc.setValueString("balancecost", balancecost);
                        _doc.addEditor("[operator]")
                        _doc.addEditor("[supervisor]")
                        _doc.addEditor(ses.getCurrentAppUser().getUserID());
                        _doc.setAuthor(ses.getCurrentAppUser().getUserID());

                        _doc.setViewText(name)
                        _doc.addViewText(originalcost)
                        _doc.setViewDate(new Date());

                        _doc.save("[supervisor]")
                        saved_docs_counter++;

                    }catch (Exception e){
                        defectRows.append(i + " ");
                        log("Accountant: doc wasn't saved. Row:$i \n" + e.toString())
                    }
                }
            }
        }
        log("Accountant: import by user ${ses.getCurrentUserID()} finished. Total imported docs number = $saved_docs_counter") ;
    }
}