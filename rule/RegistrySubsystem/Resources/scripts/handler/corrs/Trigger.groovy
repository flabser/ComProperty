package handler.corrs

import jxl.*
import kz.flabs.runtimeobj.document.Document
import kz.nextbase.script._Database
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.events._DoScheduledHandler

class Trigger extends _DoScheduledHandler {



    @Override
    public int doHandler(_Session session) {
        def db = (_Database) session.getCurrentDatabase();

        File xlsFile = new File("corrs.xls");

        Workbook workbook = Workbook.getWorkbook(xlsFile);
        String sheetName = "";
        String docForm = "";
        for (Sheet sheet : workbook.getSheets()) {

            def street = null;
            for (int i = 1; i < sheet.getRows(); i++) {
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    if (cell.type != CellType.EMPTY && cell.cellFormat != null) {
                        switch (j) {
                            case 0:
                                if (cell.getType() != CellType.EMPTY) {
                                    if (cell instanceof LabelCell) {
                                        String name = ((LabelCell) cell).getString().trim();
                                        if (db.getCollectionOfDocuments("orgfullname ~ '" + name + "'", false).getCount() == 0) {

                                            Document base_glos = new Document(db.baseObject, "[supervisor]");
                                            street = new _Document(base_glos, session)

                                            if (name != null) {
                                                if (name.contains("ТОО")) {
                                                    docForm = "too";
                                                }

                                                if (name.contains("ОГУ")) {
                                                    docForm = "kgu";
                                                }

                                                if (name.contains("АО")) {
                                                    docForm = "ao";
                                                }

                                                if (name.contains("ПХВ")) {
                                                    docForm = "kgp";
                                                }

                                                if (name.contains("ГККП")) {
                                                    docForm = "gkkp";
                                                }
                                            }

                                            street.setForm(docForm);
                                            street.setValueString("form", docForm);

                                            street.setValueString("name", name)
                                            street.setValueString("orgfullname", name)
                                            street.setValueString("orgfullnamekaz", name)
                                            street.setViewText(name)
                                            street.addViewText(name)
                                            street.setAuthor("[supervisor]")
                                            street.addReader("[observer]")
                                            street.addReader("[supervisor]")
                                            street.addEditor("[supervisor]")
                                            street.save("[supervisor]");
                                        }
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
        return 0;
    }
}
