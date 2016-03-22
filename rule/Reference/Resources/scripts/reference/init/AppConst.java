package reference.init;

public class AppConst {
	public static String DEFAULT_URL = "p?id=country-view";
	public static String FT_INDEX_SCOPE = "[{\"tableName\":\"countries\",\"fieldNames\":"
	        + "[\"name\",\"localized_name\"],\"daoImpl\":\"reference.dao.CountryDAO\"},{\"tableName\":\"regions\",\"fieldNames\":"
	        + "[\"name\"],\"daoImpl\":\"reference.dao.RegionDAO\"},{\"tableName\":\"districts\",\"fieldNames\":"
	        + "[\"name\"],\"daoImpl\":\"reference.dao.DistrictDAO\"}]";
}
