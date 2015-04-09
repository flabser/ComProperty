/**
 * HumansSearchServiceSoapBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.clients.ump;

public class HumansSearchServiceSoapBindingStub extends org.apache.axis.client.Stub implements kz.lof.webservices.clients.ump.HumansSearchService {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[34];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getLog");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "date"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"), java.util.Date.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        oper.setReturnClass(java.lang.String[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getLogReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getFullData");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"), long.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumanFullData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.HumanFullData.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getFullDataReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllCountries");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Country"));
        oper.setReturnClass(kz.lof.webservices.store.ump.Country[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getAllCountriesReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllDistricts");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "District"));
        oper.setReturnClass(kz.lof.webservices.store.ump.District[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getAllDistrictsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllStreets");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Street"));
        oper.setReturnClass(kz.lof.webservices.store.ump.Street[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getAllStreetsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDistricts");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "District"));
        oper.setReturnClass(kz.lof.webservices.store.ump.District[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getDistrictsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getHumanByFIO");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "firstName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lastName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "middleName"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumansSearchResult"));
        oper.setReturnClass(kz.lof.webservices.store.ump.HumansSearchResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getHumanByFIOReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getHumanByAddr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "street"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Street"), kz.lof.webservices.store.ump.Street.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "house"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "flat"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumansSearchResult"));
        oper.setReturnClass(kz.lof.webservices.store.ump.HumansSearchResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getHumanByAddrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getHumanByDoc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "docNumber"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumansSearchResult"));
        oper.setReturnClass(kz.lof.webservices.store.ump.HumansSearchResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getHumanByDocReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCitizenByIIN");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "iin"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumansSearchResult"));
        oper.setReturnClass(kz.lof.webservices.store.ump.HumansSearchResult.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCitizenByIINReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("testService");
        oper.setReturnType(new javax.xml.namespace.QName("http://common.webservices.lof.kz", "ServiceInfo"));
        oper.setReturnClass(kz.lof.webservices.common.ServiceInfo.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "testServiceReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInfoMigrationByCodeRegion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "region"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region"), kz.lof.webservices.store.ump.Region[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "startDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "endDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.MigrationData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationByCodeRegionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInfoMigrationNationByAdr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "startDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "endDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationNatData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.MigrationNatData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationNationByAdrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInfoMigrationNationByCodeRegion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "region"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region"), kz.lof.webservices.store.ump.Region[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "startDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "endDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationNatData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.MigrationNatData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationNationByCodeRegionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInfoMigrationReasonByAdr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "startDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "endDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationReasonData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.MigrationReasonData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationReasonByAdrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInfoMigrationReasonByCodeRegion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "region"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region"), kz.lof.webservices.store.ump.Region[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "startDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "endDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationReasonData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.MigrationReasonData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationReasonByCodeRegionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMigrationSpecByAdr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "SpecCount"));
        oper.setReturnClass(kz.lof.webservices.store.ump.SpecCount[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationSpecByAdrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMigrationSpecByCodeRegion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "region"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region"), kz.lof.webservices.store.ump.Region[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "SpecCount"));
        oper.setReturnClass(kz.lof.webservices.store.ump.SpecCount[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationSpecByCodeRegionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMigrationNatSpecByAdr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "nacId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "NatEducData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.NatEducData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationNatSpecByAdrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMigrationNatSpecByCodeRegion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "region"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region"), kz.lof.webservices.store.ump.Region[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "nacId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "NatEducData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.NatEducData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationNatSpecByCodeRegionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMigrationNatEducByAdr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "nacId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "NatEducData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.NatEducData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationNatEducByAdrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMigrationNatEducByCodeRegion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "region"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region"), kz.lof.webservices.store.ump.Region[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "nacId"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "NatEducData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.NatEducData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationNatEducByCodeRegionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCountTypeRegByAdr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "TypeRegData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.TypeRegData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCountTypeRegByAdrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCountCriminalsByCodeRegion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "region"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region"), kz.lof.webservices.store.ump.Region[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "idNac"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "sex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "startAge"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "endAge"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CriminalsData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.CriminalsData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCountCriminalsByCodeRegionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCountCriminalsByAdr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "idNac"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "sex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "startAge"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "endAge"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CriminalsData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.CriminalsData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCountCriminalsByAdrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCountTypeRegByCodeRegion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "region"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region"), kz.lof.webservices.store.ump.Region[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "TypeRegData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.TypeRegData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCountTypeRegByCodeRegionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInfoMigrationLiveByCodeRegion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "region"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region"), kz.lof.webservices.store.ump.Region[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationLiveData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.MigrationLiveData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationLiveByCodeRegionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getStreets");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Street"));
        oper.setReturnClass(kz.lof.webservices.store.ump.Street[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getStreetsReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getNationalityByAddr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "NatCount"));
        oper.setReturnClass(kz.lof.webservices.store.ump.NatCount[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getNationalityByAddrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProzhivByCodeRegion");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "region"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region"), kz.lof.webservices.store.ump.Region[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountByAddr"));
        oper.setReturnClass(kz.lof.webservices.store.ump.CountByAddr[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getProzhivByCodeRegionReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProzhivByAddr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountByAddr"));
        oper.setReturnClass(kz.lof.webservices.store.ump.CountByAddr[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getProzhivByAddrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCntAdamByAge");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "startAge"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "endAge"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "sex"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountByAddr"));
        oper.setReturnClass(kz.lof.webservices.store.ump.CountByAddr[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCntAdamByAgeReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getInfoMigrationByAdr");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "address"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"), kz.lof.webservices.store.ump.Address[].class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "startDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "endDate"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"), java.util.Calendar.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "pageNum"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "resultsOnPage"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationData"));
        oper.setReturnClass(kz.lof.webservices.store.ump.MigrationData[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationByAdrReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getCountry");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "name"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "lang"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Country"));
        oper.setReturnClass(kz.lof.webservices.store.ump.Country[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCountryReturn"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[33] = oper;

    }

    public HumansSearchServiceSoapBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public HumansSearchServiceSoapBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public HumansSearchServiceSoapBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.2");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://common.webservices.lof.kz", "InfoEntry");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.common.InfoEntry.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://common.webservices.lof.kz", "ServiceInfo");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.common.ServiceInfo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.Address.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Apartment");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.Apartment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "ApartmentType");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.ApartmentType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "City");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.City.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountByAddr");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.CountByAddr.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountByAge");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.CountByAge.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountByReason");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.CountByReason.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountMigByApartment");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.CountMigByApartment.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountMigByNat");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.CountMigByNat.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Country");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.Country.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CriminalsData");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.CriminalsData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "District");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.District.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "DocType");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.DocType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Document");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.Document.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Education");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.Education.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumanFullData");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.HumanFullData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumanShortData");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.HumanShortData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumansSearchResult");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.HumansSearchResult.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationData");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.MigrationData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationLiveData");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.MigrationLiveData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationNatData");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.MigrationNatData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "MigrationReasonData");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.MigrationReasonData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "NatCount");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.NatCount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "NatEducData");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.NatEducData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Nationality");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.Nationality.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Region");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.Region.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "RegType");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.RegType.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "RegTypeCount");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.RegTypeCount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Relation");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.Relation.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "SpecCount");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.SpecCount.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Street");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.Street.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "TypeRegData");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.TypeRegData.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "VisitPurpose");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.VisitPurpose.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "VisitReason");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.VisitReason.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "ArrayOf_tns1_CountByAge");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.CountByAge[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountByAge");
            qName2 = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "ArrayOf_tns1_CountByReason");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.CountByReason[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountByReason");
            qName2 = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "ArrayOf_tns1_CountMigByApartment");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.CountMigByApartment[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountMigByApartment");
            qName2 = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "ArrayOf_tns1_CountMigByNat");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.CountMigByNat[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "CountMigByNat");
            qName2 = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "ArrayOf_tns1_Document");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.Document[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Document");
            qName2 = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "ArrayOf_tns1_HumanShortData");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.HumanShortData[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumanShortData");
            qName2 = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "ArrayOf_tns1_RegTypeCount");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.RegTypeCount[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "RegTypeCount");
            qName2 = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "ArrayOf_tns1_SpecCount");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.store.ump.SpecCount[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "SpecCount");
            qName2 = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

            qName = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "ArrayOf_tns2_InfoEntry");
            cachedSerQNames.add(qName);
            cls = kz.lof.webservices.common.InfoEntry[].class;
            cachedSerClasses.add(cls);
            qName = new javax.xml.namespace.QName("http://common.webservices.lof.kz", "InfoEntry");
            qName2 = new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item");
            cachedSerFactories.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(qName, qName2));
            cachedDeserFactories.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public java.lang.String[] getLog(java.util.Date date) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getLog"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {date});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (java.lang.String[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (java.lang.String[]) org.apache.axis.utils.JavaUtils.convert(_resp, java.lang.String[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.HumanFullData getFullData(long id, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getFullData"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Long(id), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.HumanFullData) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.HumanFullData) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.HumanFullData.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.Country[] getAllCountries(java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getAllCountries"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.Country[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.Country[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.Country[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.District[] getAllDistricts(java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getAllDistricts"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.District[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.District[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.District[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.Street[] getAllStreets(java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getAllStreets"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.Street[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.Street[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.Street[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.District[] getDistricts(java.lang.String name, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getDistricts"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {name, lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.District[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.District[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.District[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.HumansSearchResult getHumanByFIO(java.lang.String firstName, java.lang.String lastName, java.lang.String middleName, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getHumanByFIO"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {firstName, lastName, middleName, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.HumansSearchResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.HumansSearchResult) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.HumansSearchResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.HumansSearchResult getHumanByAddr(kz.lof.webservices.store.ump.Street street, java.lang.String house, java.lang.String flat, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getHumanByAddr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {street, house, flat, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.HumansSearchResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.HumansSearchResult) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.HumansSearchResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.HumansSearchResult getHumanByDoc(java.lang.String docNumber, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getHumanByDoc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {docNumber, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.HumansSearchResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.HumansSearchResult) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.HumansSearchResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.HumansSearchResult getCitizenByIIN(java.lang.String iin, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCitizenByIIN"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {iin, lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.HumansSearchResult) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.HumansSearchResult) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.HumansSearchResult.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.common.ServiceInfo testService() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "testService"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.common.ServiceInfo) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.common.ServiceInfo) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.common.ServiceInfo.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.MigrationData[] getInfoMigrationByCodeRegion(kz.lof.webservices.store.ump.Region[] region, java.util.Calendar startDate, java.util.Calendar endDate, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationByCodeRegion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {region, startDate, endDate, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.MigrationData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.MigrationData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.MigrationData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.MigrationNatData[] getInfoMigrationNationByAdr(kz.lof.webservices.store.ump.Address[] address, java.util.Calendar startDate, java.util.Calendar endDate, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationNationByAdr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, startDate, endDate, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.MigrationNatData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.MigrationNatData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.MigrationNatData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.MigrationNatData[] getInfoMigrationNationByCodeRegion(kz.lof.webservices.store.ump.Region[] region, java.util.Calendar startDate, java.util.Calendar endDate, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationNationByCodeRegion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {region, startDate, endDate, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.MigrationNatData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.MigrationNatData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.MigrationNatData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.MigrationReasonData[] getInfoMigrationReasonByAdr(kz.lof.webservices.store.ump.Address[] address, java.util.Calendar startDate, java.util.Calendar endDate, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationReasonByAdr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, startDate, endDate, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.MigrationReasonData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.MigrationReasonData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.MigrationReasonData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.MigrationReasonData[] getInfoMigrationReasonByCodeRegion(kz.lof.webservices.store.ump.Region[] region, java.util.Calendar startDate, java.util.Calendar endDate, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationReasonByCodeRegion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {region, startDate, endDate, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.MigrationReasonData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.MigrationReasonData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.MigrationReasonData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.SpecCount[] getMigrationSpecByAdr(kz.lof.webservices.store.ump.Address[] address, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationSpecByAdr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.SpecCount[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.SpecCount[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.SpecCount[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.SpecCount[] getMigrationSpecByCodeRegion(kz.lof.webservices.store.ump.Region[] region, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationSpecByCodeRegion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {region, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.SpecCount[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.SpecCount[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.SpecCount[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.NatEducData[] getMigrationNatSpecByAdr(kz.lof.webservices.store.ump.Address[] address, int nacId, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationNatSpecByAdr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, new java.lang.Integer(nacId), new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.NatEducData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.NatEducData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.NatEducData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.NatEducData[] getMigrationNatSpecByCodeRegion(kz.lof.webservices.store.ump.Region[] region, int nacId, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationNatSpecByCodeRegion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {region, new java.lang.Integer(nacId), new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.NatEducData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.NatEducData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.NatEducData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.NatEducData[] getMigrationNatEducByAdr(kz.lof.webservices.store.ump.Address[] address, int nacId, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationNatEducByAdr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, new java.lang.Integer(nacId), new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.NatEducData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.NatEducData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.NatEducData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.NatEducData[] getMigrationNatEducByCodeRegion(kz.lof.webservices.store.ump.Region[] region, int nacId, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getMigrationNatEducByCodeRegion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {region, new java.lang.Integer(nacId), new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.NatEducData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.NatEducData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.NatEducData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.TypeRegData[] getCountTypeRegByAdr(kz.lof.webservices.store.ump.Address[] address, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCountTypeRegByAdr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.TypeRegData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.TypeRegData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.TypeRegData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.CriminalsData[] getCountCriminalsByCodeRegion(kz.lof.webservices.store.ump.Region[] region, int idNac, int sex, int startAge, int endAge, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCountCriminalsByCodeRegion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {region, new java.lang.Integer(idNac), new java.lang.Integer(sex), new java.lang.Integer(startAge), new java.lang.Integer(endAge), new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.CriminalsData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.CriminalsData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.CriminalsData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.CriminalsData[] getCountCriminalsByAdr(kz.lof.webservices.store.ump.Address[] address, int idNac, int sex, int startAge, int endAge, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCountCriminalsByAdr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, new java.lang.Integer(idNac), new java.lang.Integer(sex), new java.lang.Integer(startAge), new java.lang.Integer(endAge), new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.CriminalsData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.CriminalsData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.CriminalsData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.TypeRegData[] getCountTypeRegByCodeRegion(kz.lof.webservices.store.ump.Region[] region, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCountTypeRegByCodeRegion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {region, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.TypeRegData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.TypeRegData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.TypeRegData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.MigrationLiveData[] getInfoMigrationLiveByCodeRegion(kz.lof.webservices.store.ump.Region[] region, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationLiveByCodeRegion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {region, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.MigrationLiveData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.MigrationLiveData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.MigrationLiveData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.Street[] getStreets(java.lang.String name, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getStreets"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {name, lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.Street[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.Street[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.Street[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.NatCount[] getNationalityByAddr(kz.lof.webservices.store.ump.Address[] address, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getNationalityByAddr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.NatCount[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.NatCount[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.NatCount[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.CountByAddr[] getProzhivByCodeRegion(kz.lof.webservices.store.ump.Region[] region, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getProzhivByCodeRegion"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {region, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.CountByAddr[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.CountByAddr[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.CountByAddr[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.CountByAddr[] getProzhivByAddr(kz.lof.webservices.store.ump.Address[] address, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getProzhivByAddr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.CountByAddr[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.CountByAddr[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.CountByAddr[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.CountByAddr[] getCntAdamByAge(kz.lof.webservices.store.ump.Address[] address, int startAge, int endAge, int sex, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCntAdamByAge"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, new java.lang.Integer(startAge), new java.lang.Integer(endAge), new java.lang.Integer(sex), new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.CountByAddr[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.CountByAddr[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.CountByAddr[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.MigrationData[] getInfoMigrationByAdr(kz.lof.webservices.store.ump.Address[] address, java.util.Calendar startDate, java.util.Calendar endDate, int pageNum, int resultsOnPage, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getInfoMigrationByAdr"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {address, startDate, endDate, new java.lang.Integer(pageNum), new java.lang.Integer(resultsOnPage), lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.MigrationData[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.MigrationData[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.MigrationData[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public kz.lof.webservices.store.ump.Country[] getCountry(java.lang.String name, java.lang.String lang) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "getCountry"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {name, lang});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (kz.lof.webservices.store.ump.Country[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (kz.lof.webservices.store.ump.Country[]) org.apache.axis.utils.JavaUtils.convert(_resp, kz.lof.webservices.store.ump.Country[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
