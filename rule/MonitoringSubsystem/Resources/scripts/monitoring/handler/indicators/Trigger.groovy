package monitoring.handler.indicators
import kz.nextbase.script._Session
import kz.nextbase.script.events._DoScheduledHandler
import kz.pchelka.env.Environment

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Timestamp
import java.text.SimpleDateFormat

class Trigger extends _DoScheduledHandler {

    def indeces = [animals                : ['084CMPCMP', '027'],
                   billboard              : ['08CMP0028'],
                   columns                : ['08CMP0028'],
                   computerequipment      : ['08CMP0029'],
                   cookequipment          : ['08CMP0029'],
                   drain                  : ['08CMP0028'],
                   electricnetworks       : ['08CMP0028'],
                   equipmentofcivildefense: ['08CMP0029'],
                   equity                 : ['084CMPCMP', '027'],
                   furniture              : ['084CMPCMP', '027'],
                   gas                    : ['08CMP0028'],
                   medicalequipment       : ['08CMP0029'],
                   officeequipment        : ['08CMP0029'],
                   others                 : ['084CMPCMP', '027'],
                   parking                : ['08CMP0028'],
                   road                   : ['08CMP0028'],
                   schoolequipment        : ['08CMP0029'],
                   shareblocks            : ['084CMPCMP', '027'],
                   sportsequipment        : ['084CMPCMP', '027'],
                   thermalnetworks        : ['08CMP0028'],
                   watersystem            : ['08CMP0028']]

    def districts = [Алматы:'002:751000000',
                     алмал:'002:751110000',
                     алатау:'002:751210000',
                     ауэз:'002:751310000',
                     бостан:'002:751410000',
                     жетыс:'002:751510000',
                     меде:'002:751710000',
                     наурыз:'002:751810000',
                     турксиб:'002:751910000']

    def add_gloss = [animals        : '036:0360002',
                     furniture      : '036:0360001',
                     sportsequipment: '036:0360003']


    @Override public int doHandler(_Session session) {
        /*   def db = session.getCurrentDatabase()
           SimpleDateFormat dotFormat = new SimpleDateFormat("dd.MM.yyyy")
           SimpleDateFormat wDotFormat = new SimpleDateFormat("ddMMyyyy")
           Calendar curCalDate = Calendar.getInstance()
           Date curDate = curCalDate.getTime()

           Calendar temp = Calendar.getInstance()
           temp.set(Calendar.DAY_OF_MONTH, temp.get(Calendar.DAY_OF_MONTH) - 5)


           def m = curCalDate.get(Calendar.MONTH) - 1
           curCalDate.set(Calendar.MONTH, m)

           println "regdate <= '" +  dotFormat.format(temp.getTime()) + "' and regdate >= '" + dotFormat.format(curCalDate.getTime()) + "'"
           def docs = db.getDocsCollection("form <> 'notification' and regdate <= '" +  dotFormat.format(new Date()) + "' and regdate >= '" + dotFormat.format(temp.getTime()) + "'", 0, 0)
           println docs.count

           def test = 'Алмалинская р.а.'
           def new_test = districts.find {
               it =~ /${test.substring(0,5).toLowerCase()}.*//*
           }
           println "***" + new_test + "***"


           def district
           def district_viewtext
           File indDir = new File("indicators")
           indDir.mkdir()
           if (indDir.directory) {
               File report = new File(indDir.getPath() + File.separator + "0010008_M_F_" + wDotFormat.format(new Date()) + ".txt")
               println ("***" + report.exists())
               println ("***" + report.exists())
               println ("***" + report.exists())
               report.withPrintWriter("UTF-8") {out ->
                   //out.println('"08CMP0028"."006"."002:751000000"."032:0320001"=""')
                   docs.baseCollection.each {doc ->
                       district = doc.getValueAsString('district')[0]
                       if (district.isNumber()) {
                           district_viewtext = db.getGlossaryCustomFieldValueByDOCID(district, 'viewtext')
                       }
                       if (!district || (district && !district_viewtext)) {
                           district_viewtext = 'Алматы'
                       }
                       district_viewtext = districts.find {
                           district_viewtext.toLowerCase() =~ it.key.toLowerCase()
                       }
                       out.println('"' + indeces.get(doc.form)[0] + '"."' + indeces.get(doc.form)[1] + '"."' + district_viewtext?.value + '"."' + add_gloss.get(doc.form) + '"=""')
                   }
                   out.flush()
                   out.close()
               }
               return 0

               select count(custom_fields.docid), (
case when district.viewtext like '%Алмал%' then '002:751110000'
 when district.viewtext like '%Алата%' then '002:751210000'
 when district.viewtext like '%Ауэзо%' then '002:751310000'
 when district.viewtext like '%Боста%' then '002:751410000'
 when district.viewtext like '%Жетыс%' then '002:751510000'
 when district.viewtext like '%Медеу%' then '002:751710000'
 when district.viewtext like '%Науры%' then '002:751810000'
 when district.viewtext like '%Туркс%' then '002:751910000'
 else '002:751000000' end) as name from custom_fields
left join
(select viewtext, docid from glossary where form = 'district') as district
on district.docid = custom_fields.valueasnumber
where custom_fields.docid in
(select docid from maindocs where form = 'furniture' and regdate between '01.10.2014' and '17.11.2014')
and name = 'district' group by district.viewtext

select '084CMPCMP' as indeces, (
case when district.viewtext like '%Алмал%' then '002:751110000'
 when district.viewtext like '%Алата%' then '002:751210000'
 when district.viewtext like '%Ауэзо%' then '002:751310000'
 when district.viewtext like '%Боста%' then '002:751410000'
 when district.viewtext like '%Жетыс%' then '002:751510000'
 when district.viewtext like '%Медеу%' then '002:751710000'
 when district.viewtext like '%Науры%' then '002:751810000'
 when district.viewtext like '%Туркс%' then '002:751910000'
 else '002:751000000' end) as name from custom_fields
left join
(select viewtext, docid from glossary where form = 'district') as district
on district.docid = custom_fields.valueasnumber
where custom_fields.docid in
(select docid from maindocs where form = 'furniture' and regdate between '01.10.2014' and '17.11.2014')
and name = 'district' group by district.viewtext


select '084CMPCMP' as indeces, '027' as measure, (
case when district.viewtext like '%Алмал%' then '002:751110000'
 when district.viewtext like '%Алата%' then '002:751210000'
 when district.viewtext like '%Ауэзо%' then '002:751310000'
 when district.viewtext like '%Боста%' then '002:751410000'
 when district.viewtext like '%Жетыс%' then '002:751510000'
 when district.viewtext like '%Медеу%' then '002:751710000'
 when district.viewtext like '%Науры%' then '002:751810000'
 when district.viewtext like '%Туркс%' then '002:751910000'
 else '002:751000000' end) as name, '036:0360001' as add_gloss, count(custom_fields.docid) from custom_fields
left join
(select viewtext, docid from glossary where form = 'district') as district
on district.docid = custom_fields.valueasnumber
where custom_fields.docid in
(select docid from maindocs where form = 'furniture' and regdate between '01.10.2014' and '17.11.2014')
and name = 'district' group by district.viewtext

           }*/



        def db = Environment.getDatabase("PropertyObjects")
        def dbpool = db.getConnectionPool()
        Connection conn = dbpool.getConnection()
        PreparedStatement preparedStatement = conn.prepareStatement("select ? as indeces, ? as measure, (\n" +
                "case when district.viewtext like '%Алмал%' then '002:751110000'\n" +
                " when district.viewtext like '%Алата%' then '002:751210000'\n" +
                " when district.viewtext like '%Ауэзо%' then '002:751310000'\n" +
                " when district.viewtext like '%Боста%' then '002:751410000'\n" +
                " when district.viewtext like '%Жетыс%' then '002:751510000'\n" +
                " when district.viewtext like '%Медеу%' then '002:751710000'\n" +
                " when district.viewtext like '%Науры%' then '002:751810000'\n" +
                " when district.viewtext like '%Туркс%' then '002:751910000'\n" +
                " else '002:751000000' end) as name, ? as add_gloss, count(custom_fields.docid) from custom_fields\n" +
                "left join\n" +
                "(select viewtext, docid from glossary where form = 'district') as district\n" +
                "on district.docid = custom_fields.valueasnumber\n" +
                "where custom_fields.docid in\n" +
                "(select docid from maindocs where form = ? and regdate between ? and ?)\n" +
                "and name = 'district' group by district.viewtext");


        SimpleDateFormat wDotFormat = new SimpleDateFormat("ddMMyyyy")
        File indDir = new File("indicators")
        indDir.mkdir()
        if (indDir.directory) {
            File report = new File(indDir.getPath() + File.separator + "0010008_M_F_" + wDotFormat.format(new Date()) + ".txt")
            report.withPrintWriter("UTF-8") { out ->

                prepare(preparedStatement, ['084CMPCMP', '027', '036:0360001', 'furniture'], out)
                prepare(preparedStatement, ['084CMPCMP', '027', '036:0360002', 'animals'], out)
                prepare(preparedStatement, ['084CMPCMP', '027', '036:0360003', 'sportsequipment'], out)
                prepare(preparedStatement, ['084CMPCMP', '007', '038:0380001', 'shareblocks'], out)
                prepare(preparedStatement, ['084CMPCMP', '007', '038:0380002', 'equity'], out)

                prepare(preparedStatement, ['08CMP0029', '006', '033:0330001', 'schoolequipment'], out)
                prepare(preparedStatement, ['08CMP0029', '006', '033:0330002', 'officeequipment'], out)
                prepare(preparedStatement, ['08CMP0029', '006', '033:0330003', 'computerequipment'], out)
                prepare(preparedStatement, ['08CMP0029', '006', '033:0330004', 'medicalequipment'], out)
                prepare(preparedStatement, ['08CMP0029', '006', '033:0330005', 'cookequipment'], out)
                prepare(preparedStatement, ['08CMP0029', '006', '033:0330006', 'equipmentofcivildefense'], out)
                prepare(preparedStatement, ['08CMP0029', '006', '033:0330006', 'equipmentofcivildefense'], out)

                out.flush()
                out.close()
            }
            return 0
        }



        return 0
    }

    def prepare = {pst, values, out ->
        pst.setString(1, values[0])//'084CMPCMP')
        pst.setString(2, values[1])//'027')
        pst.setString(3, values[2])//'036:0360001')
        pst.setString(4, values[3])//'furniture')
        pst.setTimestamp(5, new Timestamp(new SimpleDateFormat('dd.MM.yyyy').parse('01.10.2014').time))
        pst.setTimestamp(6, new Timestamp(new SimpleDateFormat('dd.MM.yyyy').parse('20.11.2014').time))
        ResultSet rs = pst.executeQuery()

        while (rs.next()) {
            out.println('"' + rs.getString(1) + '"."' + rs.getString(2) + '"."' + rs.getString(3) + '"."' + rs.getString(4) + '"="' + rs.getInt(5) + '"');
        }
    }

}