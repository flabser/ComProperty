package monitoring.handler.parser

import kz.flabs.runtimeobj.document.Document
import kz.flabs.runtimeobj.document.Field
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoHandler

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.regex.Matcher
import java.util.regex.Pattern

class Trigger extends _DoHandler {

    @Override
    void doHandler(_Session session, _WebFormData formData) {

        String filePath = "paymentDocs/text.txt";
        File file = new File(filePath);

        try {
            Reader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);

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
                        Document doc = new Document(session.currentDatabase.baseObject, session.currentUser);
                        doc.setForm("payment");
                        doc.addField(new Field("iin", matcher.group(1)));
                        doc.addField(new Field("paymentNumber", matcher.group(2)));
                        doc.addField(new Field("paymentDate", dateFormat.parse(matcher.group(3))));
                        doc.addField(new Field("payerBankCode", matcher.group(4)));
                        doc.addField(new Field("payerInvoiceNumber", matcher.group(5)));
                        doc.addField(new Field("kbk", matcher.group(6)));
                        doc.addField(new Field("knp", matcher.group(7)));
                        doc.addField(new Field("sum", Double.parseDouble(matcher.group(8))));
                        doc.addField(new Field("region", matcher.group(9)));
                        doc.save(session.user)

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }

            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
