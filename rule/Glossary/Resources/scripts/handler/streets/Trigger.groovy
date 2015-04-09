package handler.streets

import jxl.*
import kz.flabs.runtimeobj.document.glossary.Glossary
import kz.nextbase.script._Database
import kz.nextbase.script._Glossary
import kz.nextbase.script._Session
import kz.nextbase.script.events._DoScheduledHandler

class Trigger extends _DoScheduledHandler {
	
    @Override
    public int doHandler(_Session session) {
        def db = (_Database) session.getCurrentDatabase();

        File xlsFile = new File("export-street.xls");

        Workbook workbook = Workbook.getWorkbook(xlsFile);

        Sheet sheet = workbook.getSheet(0);

        println(sheet.getRows());
        println(sheet.getColumns());

        def street = null;
        for (int i = 1; i < sheet.getRows(); i++) {
            for (int j = 0; j < sheet.getColumns(); j++) {
                Cell cell = sheet.getCell(j, i);
                if (cell.type != CellType.EMPTY && cell.cellFormat != null) {
                    switch (j) {
                        case 0:
                            if (cell.getType() != CellType.EMPTY) {
                                String id = ((LabelCell) cell).getString().trim();
                                String name = ((LabelCell) sheet.getCell(j + 1, i)).getString().trim();
                                Glossary base_glos = new Glossary(db.baseObject);
                                street = new _Glossary(base_glos, session)
                                street.setForm("street");
                                street.setValueString("form", "street");
                                street.setValueString("name", name)
                                street.setViewText(name)
                                street.addViewText(name)
                                street.setValueString("code", id)
                                street.setValueString("is_actual", "true")
                                street.setAuthor("[supervisor]")
                                street.addReader("[observer]")
                                street.addReader("[supervisor]")
                                street.addEditor("[supervisor]")
                                street.save("[supervisor]");
                            }
                            break;
                        default:
                            break
                    }
                }
            }
        }
        return 0;
    }
}
