package monitoring.handler.buildings

import jxl.*
import kz.nextbase.script._Database
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.events._DoScheduledHandler

class Trigger extends _DoScheduledHandler {


    @Override
    public int doHandler(_Session session) {
        def db = (_Database) session.getCurrentDatabase();

        File xlsFile = new File("buildings.xls");

        Workbook workbook = Workbook.getWorkbook(xlsFile);
/*
        workbook.getNumberOfSheets()

        Sheet sheet = workbook.getSheet(2);

        println(sheet.getRows());
        println(sheet.getColumns());*/

        for (Sheet sheet : workbook.getSheets()) {


            for (int i = 1; i < sheet.getRows(); i++) {
                def build = new _Document(session.getCurrentDatabase())
                build.setForm("buildings");
                build.setValueString("form", "buildings")
                for (int j = 0; j < sheet.getColumns(); j++) {
                    Cell cell = sheet.getCell(j, i);
                    if ((j == 0 || j == 2) || cell.type != CellType.EMPTY) {
                        switch (j) {
                            case 0:
                                String objectName = "";

                                if (cell.getContents() == null || cell.getContents().length() == 0) {
                                    for (int n = i; n--; n >= 0) {
                                        Cell addCell = sheet.getCell(j, n);
                                        if (addCell.getContents() != null && addCell.getContents().length() != 0) {
                                            objectName = ((LabelCell) addCell).getString().trim();
                                            break;
                                        }
                                    }
                                } else {
                                    objectName = ((LabelCell) cell).getString().trim();
                                }

                                build.setValueString("objectname", objectName)
                                Cell assignmentCell = sheet.getCell(j + 1, i);
                                if (assignmentCell.getContents() != null && assignmentCell.getContents().length() != 0) {
                                    String assignment = ((LabelCell) assignmentCell).getString().trim();
                                    build.setViewText(objectName + " " + assignment)
                                } else {
                                    build.setViewText(objectName)
                                }
                                build.addViewText(objectName)
                                break;
                            case 1:
                                String assignment = ((LabelCell) cell).getString().trim();
                                build.setValueString("assignment", assignment)
                                break;
                            case 2:
                                String address = "";

                                if (cell.getContents() == null || cell.getContents().length() == 0) {
                                    for (int n = i; n--; n >= 0) {
                                        Cell addCell = sheet.getCell(j, n);
                                        if (addCell.getContents() != null && addCell.getContents().length() != 0) {
                                            address = ((LabelCell) addCell).getString().trim();
                                            break;
                                        }
                                    }
                                } else {
                                    address = ((LabelCell) cell).getString().trim();
                                }

                                build.setValueString("address", address)
                                break;
                            case 3:
                                String year = ""
                                if (cell.getType() == CellType.NUMBER) {
                                    year = ((NumberCell) cell).value.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    year = ((LabelCell) cell).getString().trim();
                                }
                                build.setValueString("yearconstruction", year)
                                break;
                            case 4:
                                String originalcost = ""
                                if (cell.getType() == CellType.NUMBER) {
                                    originalcost = ((NumberCell) cell).value.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    originalcost = ((LabelCell) cell).getString().trim();
                                }
                                build.setValueString("originalcost", originalcost)
                                break;
                            case 5:
                                String estimatedcost = ""
                                println cell.getType().toString()
                                if (cell.getType() == CellType.NUMBER) {
                                    estimatedcost = ((NumberCell) cell).value.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    estimatedcost = ((LabelCell) cell).getString().trim();
                                } else if (cell.getType() == CellType.NUMBER_FORMULA) {
                                    estimatedcost = ((NumberFormulaCell) cell).getValue().toString();
                                }
                                build.setValueString("estimatedcost", estimatedcost)
                                break;
                            case 6:
                                String residualcost = ""
                                if (cell.getType() == CellType.NUMBER) {
                                    residualcost = ((NumberCell) cell).value.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    residualcost = ((LabelCell) cell).getString().trim();
                                } else if (cell.getType() == CellType.NUMBER_FORMULA) {
                                    residualcost = ((NumberFormulaCell) cell).getValue().toString();
                                }
                                build.setValueString("residualcost", residualcost)
                                break;
                            case 7:
                                String countfloors = ""
                                if (cell.getType() == CellType.NUMBER) {
                                    countfloors = ((NumberCell) cell).value.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    countfloors = ((LabelCell) cell).getString().trim();
                                }
                                build.setValueString("countfloors", countfloors)
                                break;
                            case 8:
                                String totalarea = ""
                                if (cell.getType() == CellType.NUMBER) {
                                    totalarea = ((NumberCell) cell).value.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    totalarea = ((LabelCell) cell).getString().trim();
                                } else if (cell.getType() == CellType.NUMBER_FORMULA) {
                                    totalarea = ((NumberFormulaCell) cell).getValue().toString();
                                }
                                build.setValueString("totalarea", totalarea)
                                build.addViewText(totalarea)
                                break;
                            case 9:
                                String landarea = ""
                                if (cell.getType() == CellType.NUMBER) {
                                    landarea = ((NumberCell) cell).value.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    landarea = ((LabelCell) cell).getString().trim();
                                } else if (cell.getType() == CellType.NUMBER_FORMULA) {
                                    landarea = ((NumberFormulaCell) cell).getValue().toString();
                                }
                                build.setValueString("landarea", landarea)
                                break;
                            case 10:
                                String technicalpassport = ""
                                if (cell.getType() == CellType.DATE) {
                                    technicalpassport = ((DateCell) cell).date.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    technicalpassport = ((LabelCell) cell).getString().trim();
                                }
                                build.setValueString("technicalpassport", technicalpassport)
                                break;
                            case 11:
                                String stateactearth = ""
                                if (cell.getType() == CellType.DATE) {
                                    stateactearth = ((DateCell) cell).date.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    stateactearth = ((LabelCell) cell).getString().trim();
                                }
                                build.setValueString("stateactearth", stateactearth)
                                break;
                            case 12:
                                String propertyarticlein = ""
                                if (cell.getType() == CellType.DATE) {
                                    propertyarticlein = ((DateCell) cell).date.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    propertyarticlein = ((LabelCell) cell).getString().trim();
                                }
                                build.setValueString("propertyarticlein", propertyarticlein)
                                break;
                            case 13:
                                String restransferacceptance = ""
                                if (cell.getType() == CellType.DATE) {
                                    restransferacceptance = ((DateCell) cell).date.toString()
                                } else if (cell.getType() == CellType.LABEL) {
                                    restransferacceptance = ((LabelCell) cell).getString().trim();
                                }
                                build.setValueString("restransferacceptance", restransferacceptance)
                                break;

                            default:
                                break
                        }
                    }
                }
                println "#" + i + "#"
                build.save("[supervisor]")
            }
        }
        return 0;
    }
}
