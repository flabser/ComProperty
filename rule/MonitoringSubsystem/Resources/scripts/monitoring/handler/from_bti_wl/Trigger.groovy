package monitoring.handler.from_bti_wl

import groovy.sql.Sql
import kz.flabs.util.Util
import kz.nextbase.script._Session
import kz.nextbase.script._WebFormData
import kz.nextbase.script.events._DoScheduledHandler


class Trigger extends _DoScheduledHandler {
    @Override
    int doHandler(_Session session) {
        def bti_sql = Sql.newInstance("jdbc:postgresql://192.168.0.13:5433/BTI_FULL", "postgres", "smartdoc", "org.postgresql.Driver")
        def cp_sql = Sql.newInstance("jdbc:postgresql://localhost:5432/PropertyObjects", "postgres", "admin", "org.postgresql.Driver")

        def list_ids;

        def row_material;
        def row_building;
        def row_street;
        def row_sign;
        def desc_row;
        def desc;
        def address;
        def i = 0;

        bti_sql.eachRow("SELECT * from s_wall_material where is_actual = TRUE ") { row ->

            row_material = cp_sql.firstRow("SELECT * FROM custom_fields_glossary where NAME = 'name' and value = ${row.name_wall_material}")
            if (!row_material) {
                list_ids = cp_sql.executeInsert("INSERT into glossary (author, parentdocid, parentdoctype, regdate, doctype, lastupdate, viewtext, form, viewtext1, viewtext2, viewtext3, viewnumber, viewdate, ddbid, viewtext4, viewtext5, viewtext6, viewtext7)" +
                        "values ('[supervisor]', 0, 890, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', 894, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', '${row.name_wall_material}', 'material', '${row.name_wall_material}', '${row.name_wall_material}', '-', -1, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', '${Util.generateRandomAsText('ComProperty')}', '-', '-', '-', '-')")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'form', 'material', 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'name', ${row.name_wall_material}, 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, valueasnumber, type) VALUES (${list_ids[0][0]}, 'bti_id', ${row.id_wall_material}, 3)")
            }
        }

        bti_sql.eachRow("SELECT * from s_street where is_actual = TRUE ") { row ->
            row_street = cp_sql.firstRow("SELECT * FROM custom_fields_glossary where NAME = 'name' and value = ${row.name_street}")
            if (!row_street) {
                list_ids = cp_sql.executeInsert("INSERT into glossary (author, parentdocid, parentdoctype, regdate, doctype, lastupdate, viewtext, form, viewtext1, viewtext2, viewtext3, viewnumber, viewdate, ddbid, viewtext4, viewtext5, viewtext6, viewtext7)" +
                        "values" +
                        " ('[supervisor]', 0, 890, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', 894, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', " +
                        "'${row.name_street}', 'street', '${row.name_street}', '${row.name_street}', '-', -1, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', " +
                        "'${Util.generateRandomAsText('ComProperty')}', '-', '-', '-', '-')")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'form', 'street', 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'name', ${row.name_street}, 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'kazname', ${row.name_street_kaz}, 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'district', 1, 8)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, valueasnumber, type) VALUES (${list_ids[0][0]}, 'bti_id', ${row.id_street}, 3)")
            }
        }

        bti_sql.eachRow("select * from building where is_actual = 'true'"){row ->
            println("r:" + i++ + " ");
            row_building = cp_sql.firstRow("select docid from custom_fields where name = 'bti_id' and value = ${row.id_building}::varchar")
            if (!row_building) {
                desc_row = bti_sql.firstRow("select name_sign_building from s_sign_building where id_sign_building = ${row.sign_building}")
                if (desc_row){
                    desc = desc_row[0] + ", " + address + ", количество этажей: " + (row.count_floor ?: "")
                } else {
                    desc = address + ", количество этажей: " + (row.count_floor ?: "")
                }


                list_ids = cp_sql.executeInsert("insert into maindocs (author, parentdocid, parentdoctype, regdate, doctype, lastupdate, viewtext, form, viewtext1, viewtext2, viewnumber, ddbid) " +
                        "values " +
                        "('[supervisor]', 0, 890, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', 896, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', '${desc}', 'buildings', '${desc}', '${desc}', -1,'${Util.generateRandomAsText('ComProperty')}')")

                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]} , 'form', 'buildings', 1)")
                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'home', ${row.house}, 1)")
                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'yearconstruction', ${row.year_build}, 1)")
                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'countfloors', ${row.count_floor}, 1)")
                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'usefularea', ${row.s_live}, 1)")
                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'totalarea', ${row.s_all}, 1)")
                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'invnumber', ${row.invent_number}, 1)")
                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'bti_id', ${row.id_building}, 1)")


                cp_sql.execute("insert into readers_maindocs (docid, username) values (${list_ids[0][0]}, '[supervisor]')")
                cp_sql.execute("insert into authors_maindocs (docid, username) values (${list_ids[0][0]}, '[supervisor]')")
                cp_sql.execute("insert into authors_maindocs (docid, username) values (${list_ids[0][0]}, '[operator]')")
                cp_sql.execute("insert into readers_maindocs (docid, username) values (${list_ids[0][0]}, '[operator]')")
                cp_sql.execute("insert into readers_maindocs (docid, username) values (${list_ids[0][0]}, '[observer]')")

                row_material = cp_sql.firstRow("select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${row.id_wall_material}")
                if (row_material) {
                    cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'material', ${row_material[0]}, 8)")
                }

                row_street = cp_sql.firstRow("select * from custom_fields_glossary where docid in (select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${row.id_street} ) and name = 'name'")
                if (row_street) {
                    cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'street', ${row_street.docid}, 8)")
                    address = row_street[3] ?: ""
                    address +=' ' + row.house
                    cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'address', ${address}, 1)")
                }


                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'description', ${desc} , 1)")
                //println(list_ids[0][0]);
            }

        }
        return 0
    }
}
