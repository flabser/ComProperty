package accountant.form.uploadupdating
import jxl.*
import kz.flabs.runtimeobj.document.Document
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.events._FormPostSave
import kz.nextbase.script.mail._Memo

import java.text.SimpleDateFormat

class PostSave extends _FormPostSave {

    private static int getMaxCount(int docid, int fieldLength){
        int initialDocId = docid;
        int docIdLen = String.valueOf(docid).length();
        int count = 0;

        int nCount;
        int fullLength = 0;

        while((nCount = ((int) Math.pow(10, docIdLen) - initialDocId)) * (docIdLen + /**colon symbol*/1) + fullLength < fieldLength){
            fullLength += nCount * (docIdLen + 1);
            count += nCount;
            initialDocId =(int) Math.pow(10, docIdLen);
            docIdLen++;
        }

        return count + (fieldLength - fullLength)/(docIdLen + 1);
    }

    public void doPostSave(_Session ses, _Document doc) {
        String fileDir = "tmp" + File.separator + "InventLoad" + File.separator + ses.getCurrentUserID() + File.separator + (new Date().time)
        doc.getAttachments("rtfcontent", fileDir)

        File dir = new File(fileDir + File.separator + "1");

        FilenameFilter fileNameFilter = new FilenameFilter() {
            @Override
            public boolean accept(File directory, String name) {
                return name.endsWith(".xls");
            }
        };

        int saved_docs_counter = 0;
        StringBuilder defectRows = new StringBuilder();
        StringBuilder savedRows = new StringBuilder();
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

                int maxCount = 100;
                for (int i = 1; i < sheet.getRows(); i++) {
                    String kuf = sheet.getCell(1, i).getContents();
                    Cell cell = sheet.getCell(1, i);
                    if (cell.type == CellType.EMPTY || cell.cellFormat == null || !kuf ) {
                        defectRows.append(i).append(",");
                        continue;
                    }

                    try{
                        String invnumber = sheet.getCell(2, i).getContents();
                        def docs = ses.getCurrentDatabase().getCollectionOfDocuments("invnumber='" + invnumber + "'", false);
                        if(docs.getEntries().size() > 0) {
                            continue;
                        }

                        String kufVal = kufProp.getProperty(kuf.trim());
                        if(kufVal == null){
                            defectRows.append("${i} ");   //throw new IllegalArgumentException("kuf value " + kuf + " not found. row = " + i);
                            continue;
                        };

                        Document document = new Document(ses.getCurrentDatabase().baseObject, "[supervisor]");
                        def _doc = new _Document(document);
                        String name = sheet.getCell(3, i).getContents();
                        String propertycode_name = sheet.getCell(4, i).getContents();

                        Date acceptancedate =  null;
                        String acceptancedateStr = sheet.getCell(5, i).getContents().replace("/", ".")
                        switch (acceptancedateStr.length()) {
                            case 4:  acceptancedate = new SimpleDateFormat('yyyy').parse(acceptancedateStr); break;
                            case 8:  acceptancedate = new SimpleDateFormat('dd.MM.yy').parse(acceptancedateStr); break;
                            case 10: acceptancedate = new SimpleDateFormat('dd.MM.yyyy').parse(acceptancedateStr); break;
                        }

                        String region = ((LabelCell) sheet.getCell(8, i)).getString();
                        String address = ((LabelCell) sheet.getCell(9, i)).getString();
                        String originalcost = sheet.getCell(10, i).getContents();
                        String balancecost =  sheet.getCell(13, i).getContents();

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
                        _doc.addViewText(originalcost);
                        _doc.addViewText(ses.getCurrentAppUser().fullName);
                        _doc.addViewText("not_confirmed");
                        _doc.setViewDate(new Date());

                        _doc.save(ses.getCurrentAppUser().getUserID())
                        if(i < maxCount)
                            savedRows.append(_doc.docID).append(",");
                        else{
                            maxCount += getMaxCount(_doc.docID, 2048);
                            insertDocument(ses, "saveddocslist", savedRows, xlsFile.getName());

                            savedRows.delete(0, savedRows.length());
                            savedRows.append(_doc.docID).append(",");
                        }
                        saved_docs_counter++;

                    }catch (Exception e){
                        defectRows.append(i).append(",");
                        log("Accountant: doc wasn't saved. Row:$i \n" + e.toString())
                    }
                }

                if (savedRows.length() > 0) {
                    insertDocument(ses, "saveddocslist", savedRows, xlsFile.getName());
                    savedRows.delete(0, savedRows.length());
                }

                if (defectRows.length() > 0) {
                    insertDocument(ses, "defectdocslist", defectRows, xlsFile.getName());
                    defectRows.delete(0, defectRows.length());
                }

                StringBuilder msg = new StringBuilder();
                msg.append("На данный момент было загружено: ")
                        .append(sheet.getRows() - 1)
                        .append(" объектов. Успешно загрузилось ")
                        .append(saved_docs_counter)
                        .append(". С ошибками загрузилось: ")
                        .append(sheet.getRows() - 1 - saved_docs_counter)
                        .append(". Номера excel ячеек с ошибками: ")
                        .append(defectRows)
                        .append(". Необходимо испавить ошибки и загрузить исправленные данные повторно")


                def notifDoc = new _Document(new Document(ses.getCurrentDatabase().baseObject, ses.getCurrentAppUser().getUserID()));
                notifDoc.setViewText("uploadobj", 1);
                notifDoc.setForm("notigication");
                notifDoc.setViewText(msg.toString());
                notifDoc.setViewDate(new Date());

                notifDoc.addEditor(ses.getCurrentAppUser().getUserID());
                notifDoc.setAuthor(ses.getCurrentAppUser().getUserID());
                notifDoc.addEditor("[operator]")
                notifDoc.addEditor("[supervisor]")
                notifDoc.save(ses.getCurrentAppUser().getUserID());

                def ma = ses.getMailAgent()
                try{
                    def memo = new _Memo("Информация о загрузке", "", msg.toString(), null, true)
                    ma.sendMail([ses.getCurrentAppUser().getEmail()], memo)
                } catch (Exception e) {
                    e.printStackTrace()
                }

            }
        }

        log("Accountant: import by user ${ses.getCurrentUserID()} finished. Total imported docs number = $saved_docs_counter") ;



    }

    private static void insertDocument(_Session ses, String form, StringBuilder value, String fileName) {
        value.deleteCharAt(value.length() - 1);

        def doc = new _Document(
                new Document(ses.getCurrentDatabase().baseObject, ses.getCurrentAppUser().getUserID())
        );
        doc.setForm(form);
        doc.setViewText(value.toString());
        doc.setViewDate(new Date());
        doc.addEditor(ses.getCurrentAppUser().getUserID());
        doc.setAuthor(ses.getCurrentAppUser().getUserID());
        doc.addEditor("[operator]")
        doc.addEditor("[supervisor]")
        doc.setValueString("filename", fileName);
        doc.save(ses.getCurrentAppUser().getUserID());
    }
}