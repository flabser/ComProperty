package monitoring.handler.data_parser

import kz.nextbase.script._Document
import kz.nextbase.script._Session
import kz.nextbase.script.events._DoScheduledHandler

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern


public class Trigger extends _DoScheduledHandler {

    @Override
    public int doHandler(_Session session){
        String fileName = "path to file";
        File file = new File(fileName);
        if (!file.exists()) {
            log("file " + fileName + " not found!");
            return 0;
        }

        Reader reader = null;
        BufferedReader br = null;

        try {
            reader = new FileReader(file);
            br = new BufferedReader(reader);
            Pattern linePattern = Pattern.compile("^" +
                    "'([0-9]{12})'" + "," +   //ИНН налогоплательщика
                    "'([.&[^']]+)'" + "," +   //Номер платежного поручения
                    "'([A-Za-z]{3}\\s[0-9]{1,2}\\s[0-9]{4}\\s[0-9]{1,2}:[0-9]{1,2}[APM]{2})'" + "," +   //Дата платежного поручения
                    "'([A-Z0-9]{8}|\\s)'" + "," +   //Код банка плательщика
                    "'([A-Z0-9]{20}|\\s)'" + "," +   //Номер счета плательщика
                    "'([0-9]{6})'" + "," +   //КБК
                    "'([0-9]+)'" + "," +   //КНП
                    "([\\-0-9\\.]+)" + "," +   //Сумма
                    "'([0-9]+)'\$"   //Район города
            );

            String line;
            DateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy hh:mmaa");
            while ((line = br.readLine()) != null) {
                Matcher matcher = linePattern.matcher(line);

                if (matcher.matches() /*&& Arrays.asList(kbkList).contains(matcher.group(5))*/) {
                    try {
                        createDocument(
                                matcher.group(1),
                                matcher.group(2),
                                dateFormat.parse(matcher.group(3)),
                                matcher.group(4), //can be white space
                                matcher.group(5), //can be white space
                                matcher.group(6),
                                matcher.group(7),
                                Double.parseDouble(matcher.group(8)), //can be negative
                                matcher.group(9),
                                session
                        );
                    } catch (ParseException e) {
                        log(e.getMessage());
                    }
                }
            }

        } catch (IOException e) {
            log(e.getMessage());
        } finally{
            try {
                if (reader != null)
                    reader.close();
                if (br !=null)
                    br.close();
            } catch (Exception ignored) {}
        }

        return 0;
    }

    private static void createDocument(String iin, String paymentNumber, Date paymentDate, String payerBankCode, String payerInvoiceNumber, String kbk, String knp, double summ, String region, _Session ses) {
        def doc = new _Document(ses);
        doc.setForm();
        doc.setValueString();
        doc.save("[supervisor]");
    }
}
