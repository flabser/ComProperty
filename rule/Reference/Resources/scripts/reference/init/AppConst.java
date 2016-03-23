package reference.init;

import kz.lof.dataengine.jpa.constants.AppCode;

public class AppConst {
	public static String NAME = "Reference";
	public static String NAME_ENG = "Reference";
	public static String NAME_RUS = "Справочники";
	public static String NAME_KZ = "Анықтамалар";
	public static AppCode CODE = AppCode.REFERENCE;
	public static String DEFAULT_URL = "p?id=country-view";
	public static String FT_INDEX_SCOPE = "[{\"tableName\":\"countries\",\"fieldNames\":"
	        + "[\"name\",\"localized_name\"],\"daoImpl\":\"reference.dao.CountryDAO\"},{\"tableName\":\"regions\",\"fieldNames\":"
	        + "[\"name\"],\"daoImpl\":\"reference.dao.RegionDAO\"},{\"tableName\":\"districts\",\"fieldNames\":"
	        + "[\"name\"],\"daoImpl\":\"reference.dao.DistrictDAO\"}]";
}
