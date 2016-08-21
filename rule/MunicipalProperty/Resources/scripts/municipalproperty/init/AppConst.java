package municipalproperty.init;

public class AppConst {
	public static String MODULE_VERSION = "1.0";
	public static String NAME = "MunicipalProperty";
	public static String NAME_ENG = "MunicipalProperty";
	public static String NAME_RUS = "Коммунальное имущество";
	public static String NAME_KAZ = "Коммуналды меншік объектілерін бақылау";
	public static String DEFAULT_PAGE = "personalestate-view";
	public static String FT_INDEX_SCOPE = "[{\"tableName\":\"properties\",\"fieldNames\":"
	        + "[\"object_name\",\"description\",\"notes\",\"inv_number\"],\"daoImpl\":\"municipalproperty.dao.PropertyDAO\"}]";
}
