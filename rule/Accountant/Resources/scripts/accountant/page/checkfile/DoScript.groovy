package accountant.page.checkfile

import jxl.Cell
import jxl.CellType
import jxl.LabelCell
import jxl.Sheet
import jxl.Workbook
import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script._Tag
import kz.nextbase.script._WebFormData
import kz.nextbase.script._XMLDocument
import kz.nextbase.script.events._DoScript

import java.text.SimpleDateFormat

class DoScript extends _DoScript {

	@Override
	public void doProcess(_Session session, _WebFormData formData, String lang) {

        String fileDir = "tmp" + File.separator + "InventLoad" + File.separator + session.getCurrentUserID() + File.separator + (new Date().time)
        def ids = [formData.getValue("fileid")]
        def doc = new _Document(session.getCurrentDatabase())
        doc.getAttachments("rtfcontent", fileDir, ids);

        File dir = new File(fileDir + File.separator + "1");
        def result_message = "";
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
                result_message = "file_extension_error"
                return false;
            }
        };

        def rootTag = new _Tag("result");

        if(dir && dir.isDirectory()){
            def files = dir.listFiles(fileNameFilter);

            // read kuf from properties file
            Properties kufProp = new Properties();
            def s = File.separator;
            def path = (new File("")).getAbsolutePath() +
                    "${s}rule${s}Accountant${s}Resources${s}scripts${s}accountant${s}resources${s}kuf.properties"
            def input = new FileInputStream(path);
            kufProp.load(input);

            for(def xlsFile in files){
                Workbook workbook = Workbook.getWorkbook(xlsFile);
                Sheet sheet = workbook.getSheet(0);
                // проверка первой строки
                for (int i = 1; i < 2; i++) {
                    int j = 0;
                    Cell cell = sheet.getCell(j + 1, i);
                    if (cell.type != CellType.EMPTY && cell.cellFormat != null) {
                        String kuf = sheet.getCell(j + 1, i).getContents();
                        try{
                            if (kuf.isNumber()) {
                                rootTag.setAttr("invnumber", sheet.getCell(j + 2, i).getContents())
                                rootTag.setAttr("name", sheet.getCell(j + 3, i).getContents())
                                rootTag.setAttr("propertycode_name", sheet.getCell(j + 4, i).getContents())
                                rootTag.setAttr("acceptancedate", sheet.getCell(j + 5, i).getContents())
                                rootTag.setAttr("region", ((LabelCell) sheet.getCell(j + 8, i)).getString())
                                rootTag.setAttr("address", ((LabelCell) sheet.getCell(j + 9, i)).getString())
                                rootTag.setAttr("originalcost", sheet.getCell(j + 10, i).getContents())
                                rootTag.setAttr("balancecost", sheet.getCell(j + 13, i).getContents())
                                def kufName = kufProp.getProperty(kuf.trim())
                                if(kufName && !kufName.isEmpty())
                                    rootTag.setAttr("kuf", kufName)
                                else
                                    result_message = "wrong_kuf"

                            }else{
                                result_message = "wrong_kuf"
                            }
                        }catch (FileNotFoundException e){
                            result_message = "parsing_file_error"
                        }
                    }else{
                        result_message = "empty_kuf";
                    }
                }
            }
        }
        rootTag.setAttr("status", result_message.isEmpty() ? "success" : result_message)
		def xml = new _XMLDocument(rootTag)
		setContent(xml);
	}
}




