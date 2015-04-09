package monitoring.handler.from_bti_wl_v2
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import kz.flabs.util.Util
import kz.nextbase.script._Session
import kz.nextbase.script.events._DoScheduledHandler

class Trigger extends _DoScheduledHandler {
    @Override
    int doHandler(_Session session) {
        def bti_sql = Sql.newInstance("jdbc:postgresql://192.168.0.13:5433/BTI_FULL", "postgres", "smartdoc", "org.postgresql.Driver")
        def cp_sql = Sql.newInstance("jdbc:postgresql://localhost:5432/PropertyObjects", "postgres", "admin", "org.postgresql.Driver")

        def list_ids;

        def row_material;
        def row_building;
        def row_building_part;
        def row_street;
        def row_sign_build;
        def row_part_house;
        def row_type_house;
        def row_sign;
        def desc_row;
        def desc_type_row;
        def desc_part_row;
        def desc = "";
        def address;
        def i = 0;

        bti_sql.eachRow("SELECT * FROM s_wall_material WHERE is_actual = TRUE ") { row ->

            row_material = cp_sql.firstRow("SELECT * FROM custom_fields_glossary where NAME = 'name' and value = ${row.name_wall_material}")
            if (!row_material && row.name_wall_material) {
                list_ids = cp_sql.executeInsert("INSERT into glossary (author, parentdocid, parentdoctype, regdate, doctype, lastupdate, viewtext, form, viewtext1, viewtext2, viewtext3, viewnumber, viewdate, ddbid, viewtext4, viewtext5, viewtext6, viewtext7)" +
                        "values ('[supervisor]', 0, 890, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', 894, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', '${row.name_wall_material}', 'material', '${row.name_wall_material}', '${row.name_wall_material}', '-', -1, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', '${Util.generateRandomAsText('ComProperty')}', '-', '-', '-', '-')")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'form', 'material', 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'name', ${row.name_wall_material}, 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, valueasnumber, type) VALUES (${list_ids[0][0]}, 'bti_id', ${row.id_wall_material}, 3)")
            }
        }

        bti_sql.eachRow("SELECT * FROM s_street WHERE is_actual = TRUE ") { row ->
            row_street = cp_sql.firstRow("SELECT * FROM custom_fields_glossary where NAME = 'name' and value = ${row.name_street}")
            if (!row_street && row.name_street) {
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

        bti_sql.eachRow("SELECT * FROM s_sign_building WHERE is_actual = TRUE ") { row ->
            row_sign_build = cp_sql.firstRow("select * from custom_fields_glossary where name = 'name' and value = ${row.name_sign_building}")
            if (!row_sign_build && row.name_sign_building) {
                list_ids = cp_sql.executeInsert("INSERT into glossary (author, parentdocid, parentdoctype, regdate, doctype, lastupdate, viewtext, form, viewtext1, viewtext2, viewtext3, viewnumber, viewdate, ddbid, viewtext4, viewtext5, viewtext6, viewtext7)" +
                        "values" +
                        " ('[supervisor]', 0, 890, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', 894, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', " +
                        "'${row.name_sign_building}', 'sign_build', '${row.name_sign_building}', '${row.name_sign_building}', '-', -1, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', " +
                        "'${Util.generateRandomAsText('ComProperty')}', '-', '-', '-', '-')")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'form', 'sign_build', 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'name', ${row.name_sign_building}, 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'kazname', ${row.name_sign_building_kaz}, 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, valueasnumber, type) VALUES (${list_ids[0][0]}, 'bti_id', ${row.id_sign_building}, 3)")

            }

        }

        bti_sql.eachRow("SELECT * FROM s_part_house WHERE is_actual = TRUE ") { row ->
            row_part_house = cp_sql.firstRow("select * from custom_fields_glossary where name = 'name' and value = ${row.name_part_house}")
            if (!row_part_house && row.name_part_house) {
                list_ids = cp_sql.executeInsert("INSERT into glossary (author, parentdocid, parentdoctype, regdate, doctype, lastupdate, viewtext, form, viewtext1, viewtext2, viewtext3, viewnumber, viewdate, ddbid, viewtext4, viewtext5, viewtext6, viewtext7)" +
                        "values" +
                        " ('[supervisor]', 0, 890, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', 894, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', " +
                        "'${row.name_part_house}', 'part_house', '${row.name_part_house}', '${row.name_part_house}', '-', -1, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', " +
                        "'${Util.generateRandomAsText('ComProperty')}', '-', '-', '-', '-')")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'form', 'part_house', 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'name', ${row.name_part_house}, 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'kazname', ${row.name_part_house}, 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, valueasnumber, type) VALUES (${list_ids[0][0]}, 'bti_id', ${row.id_part_house}, 3)")

            }

        }

        bti_sql.eachRow("SELECT * FROM s_type_house WHERE is_actual = TRUE ") { row ->
            row_type_house = cp_sql.firstRow("select * from custom_fields_glossary where name = 'name' and value = ${row.name_type_house}")
            if (!row_type_house && row.name_type_house) {
                list_ids = cp_sql.executeInsert("INSERT into glossary (author, parentdocid, parentdoctype, regdate, doctype, lastupdate, viewtext, form, viewtext1, viewtext2, viewtext3, viewnumber, viewdate, ddbid, viewtext4, viewtext5, viewtext6, viewtext7)" +
                        "values" +
                        " ('[supervisor]', 0, 890, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', 894, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', " +
                        "'${row.name_type_house}', 'type_house', '${row.name_type_house}', '${row.name_type_house}', '-', -1, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', " +
                        "'${Util.generateRandomAsText('ComProperty')}', '-', '-', '-', '-')")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'form', 'type_house', 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'name', ${row.name_type_house}, 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, value, type) VALUES (${list_ids[0][0]}, 'kazname', ${row.name_type_house}, 1)")
                cp_sql.execute("insert into custom_fields_glossary (docid, name, valueasnumber, type) VALUES (${list_ids[0][0]}, 'bti_id', ${row.id_type_house}, 3)")
            }
        }


        bti_sql.eachRow("SELECT * FROM building WHERE is_actual = 'true' AND (is_deleted IS NULL OR is_deleted = 'false') ") { row ->
            println("r:" + i++ + " ")
            row_building = cp_sql.firstRow("SELECT docid from custom_fields where name = 'bti_id' and value = ${row.id_building}::varchar")
            if (!row_building) {

                row_street = cp_sql.firstRow("select * from custom_fields_glossary where docid in (select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${row.id_street} ) and name = 'name'")
                if (row_street) {
                    address = row_street[3] ?: ""
                    address += ' ' + row.house
                }

                if (bti_sql.firstRow("select count(*) from apartment where id_building = ${row.id_building}")[0] != 0) {
                    bti_sql.eachRow("select * from apartment where is_actual = 'true' and id_building = ${row.id_building} and s_live <> '0.00' and s_all <> '0.00'") { bp_row ->
                        row_building_part = cp_sql.firstRow("SELECT docid from custom_fields where name = 'bti_apartment_id' and value = ${bp_row.id_record}::varchar")
                        if (!row_building_part) {
                            desc_row = bti_sql.firstRow("select name_sign_building from s_sign_building where id_sign_building = ${bp_row.sign_building}")
                            desc_type_row = bti_sql.firstRow("select name_type_house from s_type_house where id_type_house = ${bp_row.id_type_house}")
                            desc_part_row = bti_sql.firstRow("select name_part_house from s_part_house where id_part_house = ${bp_row.id_part_house}")

                            if (desc_type_row && desc_type_row[0] && desc_type_row[0] != 'null') {
                                desc += desc_type_row[0]
                            }

                            if (desc_part_row && desc_part_row[0] && desc_part_row[0] != 'null') {
                                desc += desc_part_row[0]
                            }

                            if (desc_row && desc_row[0] && desc_row[0] != 'null') {
                                desc += desc_row[0] + ", " + address + ", количество помещений: " + (bp_row.count_premises ?: "") + ", количество комнат: " + (bp_row.count_room ?: "")
                            } else {
                                desc += address + ", количество помещений: " + (bp_row.count_premises ?: "") + ", количество комнат: " + (bp_row.count_room ?: "")
                            }

                            list_ids = cp_sql.executeInsert("insert into maindocs (author, parentdocid, parentdoctype, regdate, doctype, lastupdate, viewtext, form, viewtext1, viewtext2, viewnumber, ddbid) " +
                                    "values " +
                                    "('[supervisor]', 0, 890, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', 896, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', '${desc}', 'buildings', '${desc}', '${desc}', -1,'${Util.generateRandomAsText('ComProperty')}')")

                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]} , 'form', 'buildings', 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'home', ${row.house}, 1)")
                            //cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'yearconstruction', ${row.year_build}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'countfloors', ${row.count_floor}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'countrooms', ${bp_row.count_room}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'countpremises', ${bp_row.count_premises}, 1)")

                            if (bp_row.year_build && bp_row.year_build != "0") {
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'yearconstruction', ${bp_row.year_build}, 1)")
                            } else {
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'yearconstruction', ${row.year_build}, 1)")
                            }

                            if (bp_row.s_live && bp_row.s_live != '0.00') {
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'usefularea', ${bp_row.s_live}, 1)")
                            } else if (row.s_live && row.s_live != '0.00') {
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'usefularea', ${row.s_live}, 1)")
                            }

                            if (bp_row.s_all && bp_row.s_all != '0.00') {
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'totalarea', ${bp_row.s_all}, 1)")
                            } else if (row.s_all && row.s_all != '0.00') {
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'totalarea', ${row.s_all}, 1)")
                            }

                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'invnumber', ${row.invent_number}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'floor', ${bp_row.floor}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'flatnumber', ${bp_row.flat_number}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'bti_id', ${row.id_building}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'bti_part_id', ${bp_row.id_building}, 1)")


                            cp_sql.execute("insert into readers_maindocs (docid, username) values (${list_ids[0][0]}, '[supervisor]')")
                            cp_sql.execute("insert into authors_maindocs (docid, username) values (${list_ids[0][0]}, '[supervisor]')")
                            cp_sql.execute("insert into authors_maindocs (docid, username) values (${list_ids[0][0]}, '[operator]')")
                            cp_sql.execute("insert into readers_maindocs (docid, username) values (${list_ids[0][0]}, '[operator]')")
                            cp_sql.execute("insert into readers_maindocs (docid, username) values (${list_ids[0][0]}, '[observer]')")

                            if (row.id_wall_material && row.id_wall_material != 0) {
                                row_material = cp_sql.firstRow("select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${row.id_wall_material}")
                                if (row_material) {
                                    cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'material', ${row_material[0]}, 8)")
                                }
                            } else {
                                row_material = cp_sql.firstRow("select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${bp_row.id_wall_material}")
                                if (row_material) {
                                    cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'material', ${row_material[0]}, 8)")
                                }
                            }

                            row_sign_build = cp_sql.firstRow("select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${bp_row.sign_building}")
                            if (row_sign_build) {
                                cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'sign_build', ${row_sign_build[0]}, 8)")
                            }

                            row_type_house = cp_sql.firstRow("select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${bp_row.id_type_house}")
                            if (row_type_house) {
                                cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'type_house', ${row_type_house[0]}, 8)")
                            }

                            row_part_house = cp_sql.firstRow("select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${bp_row.id_part_house}")
                            if (row_part_house) {
                                cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'part_house', ${row_part_house[0]}, 8)")
                            }

                            if (row_street) {
                                cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'street', ${row_street.docid}, 8)")
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'address', ${address}, 1)")
                            }

                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'description', ${desc} , 1)")
                            desc = ""

                        }
                    }
                }

                if (bti_sql.firstRow("select count(*) from building_part where id_building = ${row.id_building}")[0] != 0) {
                    bti_sql.eachRow("select * from building_part where is_actual = 'true' and is_deleted = 'false' and id_building = ${row.id_building} and s_live <> '0.00' and s_all <> '0.00'") { bp_row ->
                        row_building_part = cp_sql.firstRow("SELECT docid from custom_fields where name = 'bti_part_id' and value = ${bp_row.id_record}::varchar")
                        if (!row_building_part) {
                            desc_row = (GroovyRowResult) bti_sql.firstRow("select name_sign_building::varchar from s_sign_building where id_sign_building::int = ${bp_row.sign_building_part}::int")
                            if (desc_row) {
                                desc = desc_row[0] + ", " + address + ", количество помещений: " + (bp_row.count_premises ?: "") + ", количество комнат: " + (bp_row.count_room ?: "")
                            } else {
                                desc = address + ", количество помещений: " + (bp_row.count_premises ?: "") + ", количество комнат: " + (bp_row.count_room ?: "")
                            }

                            list_ids = cp_sql.executeInsert("insert into maindocs (author, parentdocid, parentdoctype, regdate, doctype, lastupdate, viewtext, form, viewtext1, viewtext2, viewnumber, ddbid) " +
                                    "values " +
                                    "('[supervisor]', 0, 890, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', 896, '${new Date().format("yyyy-MM-dd hh:mm:ss")}', '${desc}', 'buildings', '${desc}', '${desc}', -1,'${Util.generateRandomAsText('ComProperty')}')")

                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]} , 'form', 'buildings', 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'home', ${row.house}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'yearconstruction', ${row.year_build}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'countfloors', ${row.count_floor}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'countrooms', ${bp_row.count_room}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'countpremises', ${bp_row.count_premises}, 1)")

                            if (bp_row.s_live && bp_row.s_live != '0.00') {
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'usefularea', ${bp_row.s_live}, 1)")
                            } else if (row.s_live && row.s_live != '0.00') {
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'usefularea', ${row.s_live}, 1)")
                            }

                            if (bp_row.s_all && bp_row.s_all != '0.00') {
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'totalarea', ${bp_row.s_all}, 1)")
                            } else if (row.s_all && row.s_all != '0.00') {
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'totalarea', ${row.s_all}, 1)")
                            }

                            //cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'usefularea', ${row.s_live}, 1)")
                            //cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'totalarea', ${row.s_all}, 1)")

                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'invnumber', ${row.invent_number}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'bti_id', ${row.id_building}, 1)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'bti_part_id', ${bp_row.id_building}, 1)")


                            cp_sql.execute("insert into readers_maindocs (docid, username) values (${list_ids[0][0]}, '[supervisor]')")
                            cp_sql.execute("insert into authors_maindocs (docid, username) values (${list_ids[0][0]}, '[supervisor]')")
                            cp_sql.execute("insert into authors_maindocs (docid, username) values (${list_ids[0][0]}, '[operator]')")
                            cp_sql.execute("insert into readers_maindocs (docid, username) values (${list_ids[0][0]}, '[operator]')")
                            cp_sql.execute("insert into readers_maindocs (docid, username) values (${list_ids[0][0]}, '[observer]')")

                            row_material = cp_sql.firstRow("select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${row.id_wall_material}")
                            if (row_material) {
                                cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'material', ${row_material[0]}, 8)")
                            }

                            row_sign_build = cp_sql.firstRow("select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${bp_row.sign_building_part}")
                            if (row_sign_build) {
                                cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'sign_build', ${row_sign_build[0]}, 8)")
                            }

                            if (row_street) {
                                cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'street', ${row_street.docid}, 8)")
                                cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'address', ${address}, 1)")
                            }

                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'description', ${desc} , 1)")
                            desc = ""

                        }
                    }
                } else {

                    row_building = cp_sql.firstRow("select docid from custom_fields where name = 'bti_id' and value = ${row.id_building}::varchar")
                    if (!row_building) {
                        desc_row = bti_sql.firstRow("select name_sign_building from s_sign_building where id_sign_building = ${row.sign_building}")
                        if (desc_row) {
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
                        cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'countflat', ${row.count_flat}, 1)")
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

                        row_sign_build = cp_sql.firstRow("select docid from custom_fields_glossary where name = 'bti_id' and valueasnumber = ${row.sign_building}")
                        if (row_sign_build) {
                            cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'sign_build', ${row_sign_build[0]}, 8)")
                        }

                        if (row_street) {
                            cp_sql.execute("INSERT into custom_fields (docid, name, valueasglossary, type) VALUES (${list_ids[0][0]}, 'street', ${row_street.docid}, 8)")
                            cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'address', ${address}, 1)")
                        }


                        cp_sql.execute("insert into custom_fields (docid, name, value, type) values (${list_ids[0][0]}, 'description', ${desc} , 1)")
                        desc = ""
                        //println(list_ids[0][0]);
                    }


                }
                address = ""
            }
        }

        /*bti_sql.eachRow("select * from building where is_actual = 'true'"){row ->
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

        }*/
        return 0
    }

}
