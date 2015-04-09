/**
 * GBDULJurInfoType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package tender.webservices.ul;


/**
 * Информация о ЮЛ
 */
public class GBDULJurInfoType  implements java.io.Serializable {
    /* БИН */
    private String BIN;

    /* Статус регистрации в ГБД ЮЛ */
    private String regStatus;

    /* Дата регистрации статуса в ГБД ЮЛ */
    private java.util.Date regStatusDate;

    /* РНН */
    private String RNN;

    /* ОКПО */
    private String OKPO;

    /* Дата (пере)регистрации */
    private java.util.Date regDate;

    /* Номер свидетельства о гос.регистрации */
    private String certNumber;

    /* Серия свидетельства о гос.регистрации */
    private String certSeries;

    /* Полное наименование */
    private tender.webservices.ul.GBDULNameType fullName;

    /* Сокращенное наименование */
    private tender.webservices.ul.GBDULNameType shortName;

    /* Сокращенное наименование */
    private tender.webservices.ul.GBDULIrrelevantNameType[] irrelevantNameList;

    /* Форма организации */
    private String orgForm;

    /* Организационно-правовая форма */
    private String formOfLaw;

    /* Форма собственности */
    private String ownership;

    /* Коммерческая организация? */
    private String commerceOrg;

    /* Субъект предпринимательства? */
    private String enterpriseSubj;

    /* Тип частного предпринимательства */
    private String privateEnterpriseType;

    /* Дочерняя организация? */
    private String childOrg;

    /* Международная организация? */
    private String internationalOrg;

    /* Участие иностранных инвесторов? */
    private String foreignInvest;

    /* Сведения о руководителе */
    private tender.webservices.ul.GBDULFizItemType headInfo;

    /* Виды деятельности */
    private String activityKinds;

    /* Юридический адрес */
    private tender.webservices.ul.GBDULJurAddressType jurAddress;

    /* Неактуальные юридические адреса */
    private tender.webservices.ul.GBDULJurAddressType[] irrelevantJurAddressList;

    /* Размер уставного капитала, тыс. тенге */
    private double capitalSize;

    /* Сведения о головной организации */
    private tender.webservices.ul.GBDULJurItemType headOrg;

    /* Сведения об учредителях – юридических лицах */
    private tender.webservices.ul.GBDULJurItemType[] foundersUL;

    /* Сведения об учредителях – физических лицах */
    private tender.webservices.ul.GBDULFizItemType[] foundersFL;

    public GBDULJurInfoType() {
    }

    public GBDULJurInfoType(
           String BIN,
           String regStatus,
           java.util.Date regStatusDate,
           String RNN,
           String OKPO,
           java.util.Date regDate,
           String certNumber,
           String certSeries,
           tender.webservices.ul.GBDULNameType fullName,
           tender.webservices.ul.GBDULNameType shortName,
           tender.webservices.ul.GBDULIrrelevantNameType[] irrelevantNameList,
           String orgForm,
           String formOfLaw,
           String ownership,
           String commerceOrg,
           String enterpriseSubj,
           String privateEnterpriseType,
           String childOrg,
           String internationalOrg,
           String foreignInvest,
           tender.webservices.ul.GBDULFizItemType headInfo,
           String activityKinds,
           tender.webservices.ul.GBDULJurAddressType jurAddress,
           tender.webservices.ul.GBDULJurAddressType[] irrelevantJurAddressList,
           double capitalSize,
           tender.webservices.ul.GBDULJurItemType headOrg,
           tender.webservices.ul.GBDULJurItemType[] foundersUL,
           tender.webservices.ul.GBDULFizItemType[] foundersFL) {
           this.BIN = BIN;
           this.regStatus = regStatus;
           this.regStatusDate = regStatusDate;
           this.RNN = RNN;
           this.OKPO = OKPO;
           this.regDate = regDate;
           this.certNumber = certNumber;
           this.certSeries = certSeries;
           this.fullName = fullName;
           this.shortName = shortName;
           this.irrelevantNameList = irrelevantNameList;
           this.orgForm = orgForm;
           this.formOfLaw = formOfLaw;
           this.ownership = ownership;
           this.commerceOrg = commerceOrg;
           this.enterpriseSubj = enterpriseSubj;
           this.privateEnterpriseType = privateEnterpriseType;
           this.childOrg = childOrg;
           this.internationalOrg = internationalOrg;
           this.foreignInvest = foreignInvest;
           this.headInfo = headInfo;
           this.activityKinds = activityKinds;
           this.jurAddress = jurAddress;
           this.irrelevantJurAddressList = irrelevantJurAddressList;
           this.capitalSize = capitalSize;
           this.headOrg = headOrg;
           this.foundersUL = foundersUL;
           this.foundersFL = foundersFL;
    }


    /**
     * Gets the BIN value for this GBDULJurInfoType.
     *
     * @return BIN   * БИН
     */
    public String getBIN() {
        return BIN;
    }


    /**
     * Sets the BIN value for this GBDULJurInfoType.
     *
     * @param BIN   * БИН
     */
    public void setBIN(String BIN) {
        this.BIN = BIN;
    }


    /**
     * Gets the regStatus value for this GBDULJurInfoType.
     *
     * @return regStatus   * Статус регистрации в ГБД ЮЛ
     */
    public String getRegStatus() {
        return regStatus;
    }


    /**
     * Sets the regStatus value for this GBDULJurInfoType.
     *
     * @param regStatus   * Статус регистрации в ГБД ЮЛ
     */
    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }


    /**
     * Gets the regStatusDate value for this GBDULJurInfoType.
     *
     * @return regStatusDate   * Дата регистрации статуса в ГБД ЮЛ
     */
    public java.util.Date getRegStatusDate() {
        return regStatusDate;
    }


    /**
     * Sets the regStatusDate value for this GBDULJurInfoType.
     *
     * @param regStatusDate   * Дата регистрации статуса в ГБД ЮЛ
     */
    public void setRegStatusDate(java.util.Date regStatusDate) {
        this.regStatusDate = regStatusDate;
    }


    /**
     * Gets the RNN value for this GBDULJurInfoType.
     *
     * @return RNN   * РНН
     */
    public String getRNN() {
        return RNN;
    }


    /**
     * Sets the RNN value for this GBDULJurInfoType.
     *
     * @param RNN   * РНН
     */
    public void setRNN(String RNN) {
        this.RNN = RNN;
    }


    /**
     * Gets the OKPO value for this GBDULJurInfoType.
     *
     * @return OKPO   * ОКПО
     */
    public String getOKPO() {
        return OKPO;
    }


    /**
     * Sets the OKPO value for this GBDULJurInfoType.
     *
     * @param OKPO   * ОКПО
     */
    public void setOKPO(String OKPO) {
        this.OKPO = OKPO;
    }


    /**
     * Gets the regDate value for this GBDULJurInfoType.
     *
     * @return regDate   * Дата (пере)регистрации
     */
    public java.util.Date getRegDate() {
        return regDate;
    }


    /**
     * Sets the regDate value for this GBDULJurInfoType.
     *
     * @param regDate   * Дата (пере)регистрации
     */
    public void setRegDate(java.util.Date regDate) {
        this.regDate = regDate;
    }


    /**
     * Gets the certNumber value for this GBDULJurInfoType.
     *
     * @return certNumber   * Номер свидетельства о гос.регистрации
     */
    public String getCertNumber() {
        return certNumber;
    }


    /**
     * Sets the certNumber value for this GBDULJurInfoType.
     *
     * @param certNumber   * Номер свидетельства о гос.регистрации
     */
    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }


    /**
     * Gets the certSeries value for this GBDULJurInfoType.
     *
     * @return certSeries   * Серия свидетельства о гос.регистрации
     */
    public String getCertSeries() {
        return certSeries;
    }


    /**
     * Sets the certSeries value for this GBDULJurInfoType.
     *
     * @param certSeries   * Серия свидетельства о гос.регистрации
     */
    public void setCertSeries(String certSeries) {
        this.certSeries = certSeries;
    }


    /**
     * Gets the fullName value for this GBDULJurInfoType.
     *
     * @return fullName   * Полное наименование
     */
    public tender.webservices.ul.GBDULNameType getFullName() {
        return fullName;
    }


    /**
     * Sets the fullName value for this GBDULJurInfoType.
     *
     * @param fullName   * Полное наименование
     */
    public void setFullName(tender.webservices.ul.GBDULNameType fullName) {
        this.fullName = fullName;
    }


    /**
     * Gets the shortName value for this GBDULJurInfoType.
     *
     * @return shortName   * Сокращенное наименование
     */
    public tender.webservices.ul.GBDULNameType getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this GBDULJurInfoType.
     *
     * @param shortName   * Сокращенное наименование
     */
    public void setShortName(tender.webservices.ul.GBDULNameType shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the irrelevantNameList value for this GBDULJurInfoType.
     *
     * @return irrelevantNameList   * Сокращенное наименование
     */
    public tender.webservices.ul.GBDULIrrelevantNameType[] getIrrelevantNameList() {
        return irrelevantNameList;
    }


    /**
     * Sets the irrelevantNameList value for this GBDULJurInfoType.
     *
     * @param irrelevantNameList   * Сокращенное наименование
     */
    public void setIrrelevantNameList(tender.webservices.ul.GBDULIrrelevantNameType[] irrelevantNameList) {
        this.irrelevantNameList = irrelevantNameList;
    }


    /**
     * Gets the orgForm value for this GBDULJurInfoType.
     *
     * @return orgForm   * Форма организации
     */
    public String getOrgForm() {
        return orgForm;
    }


    /**
     * Sets the orgForm value for this GBDULJurInfoType.
     *
     * @param orgForm   * Форма организации
     */
    public void setOrgForm(String orgForm) {
        this.orgForm = orgForm;
    }


    /**
     * Gets the formOfLaw value for this GBDULJurInfoType.
     *
     * @return formOfLaw   * Организационно-правовая форма
     */
    public String getFormOfLaw() {
        return formOfLaw;
    }


    /**
     * Sets the formOfLaw value for this GBDULJurInfoType.
     *
     * @param formOfLaw   * Организационно-правовая форма
     */
    public void setFormOfLaw(String formOfLaw) {
        this.formOfLaw = formOfLaw;
    }


    /**
     * Gets the ownership value for this GBDULJurInfoType.
     *
     * @return ownership   * Форма собственности
     */
    public String getOwnership() {
        return ownership;
    }


    /**
     * Sets the ownership value for this GBDULJurInfoType.
     *
     * @param ownership   * Форма собственности
     */
    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }


    /**
     * Gets the commerceOrg value for this GBDULJurInfoType.
     *
     * @return commerceOrg   * Коммерческая организация?
     */
    public String getCommerceOrg() {
        return commerceOrg;
    }


    /**
     * Sets the commerceOrg value for this GBDULJurInfoType.
     *
     * @param commerceOrg   * Коммерческая организация?
     */
    public void setCommerceOrg(String commerceOrg) {
        this.commerceOrg = commerceOrg;
    }


    /**
     * Gets the enterpriseSubj value for this GBDULJurInfoType.
     *
     * @return enterpriseSubj   * Субъект предпринимательства?
     */
    public String getEnterpriseSubj() {
        return enterpriseSubj;
    }


    /**
     * Sets the enterpriseSubj value for this GBDULJurInfoType.
     *
     * @param enterpriseSubj   * Субъект предпринимательства?
     */
    public void setEnterpriseSubj(String enterpriseSubj) {
        this.enterpriseSubj = enterpriseSubj;
    }


    /**
     * Gets the privateEnterpriseType value for this GBDULJurInfoType.
     *
     * @return privateEnterpriseType   * Тип частного предпринимательства
     */
    public String getPrivateEnterpriseType() {
        return privateEnterpriseType;
    }


    /**
     * Sets the privateEnterpriseType value for this GBDULJurInfoType.
     *
     * @param privateEnterpriseType   * Тип частного предпринимательства
     */
    public void setPrivateEnterpriseType(String privateEnterpriseType) {
        this.privateEnterpriseType = privateEnterpriseType;
    }


    /**
     * Gets the childOrg value for this GBDULJurInfoType.
     *
     * @return childOrg   * Дочерняя организация?
     */
    public String getChildOrg() {
        return childOrg;
    }


    /**
     * Sets the childOrg value for this GBDULJurInfoType.
     *
     * @param childOrg   * Дочерняя организация?
     */
    public void setChildOrg(String childOrg) {
        this.childOrg = childOrg;
    }


    /**
     * Gets the internationalOrg value for this GBDULJurInfoType.
     *
     * @return internationalOrg   * Международная организация?
     */
    public String getInternationalOrg() {
        return internationalOrg;
    }


    /**
     * Sets the internationalOrg value for this GBDULJurInfoType.
     *
     * @param internationalOrg   * Международная организация?
     */
    public void setInternationalOrg(String internationalOrg) {
        this.internationalOrg = internationalOrg;
    }


    /**
     * Gets the foreignInvest value for this GBDULJurInfoType.
     *
     * @return foreignInvest   * Участие иностранных инвесторов?
     */
    public String getForeignInvest() {
        return foreignInvest;
    }


    /**
     * Sets the foreignInvest value for this GBDULJurInfoType.
     *
     * @param foreignInvest   * Участие иностранных инвесторов?
     */
    public void setForeignInvest(String foreignInvest) {
        this.foreignInvest = foreignInvest;
    }


    /**
     * Gets the headInfo value for this GBDULJurInfoType.
     *
     * @return headInfo   * Сведения о руководителе
     */
    public tender.webservices.ul.GBDULFizItemType getHeadInfo() {
        return headInfo;
    }


    /**
     * Sets the headInfo value for this GBDULJurInfoType.
     *
     * @param headInfo   * Сведения о руководителе
     */
    public void setHeadInfo(tender.webservices.ul.GBDULFizItemType headInfo) {
        this.headInfo = headInfo;
    }


    /**
     * Gets the activityKinds value for this GBDULJurInfoType.
     *
     * @return activityKinds   * Виды деятельности
     */
    public String getActivityKinds() {
        return activityKinds;
    }


    /**
     * Sets the activityKinds value for this GBDULJurInfoType.
     *
     * @param activityKinds   * Виды деятельности
     */
    public void setActivityKinds(String activityKinds) {
        this.activityKinds = activityKinds;
    }


    /**
     * Gets the jurAddress value for this GBDULJurInfoType.
     *
     * @return jurAddress   * Юридический адрес
     */
    public tender.webservices.ul.GBDULJurAddressType getJurAddress() {
        return jurAddress;
    }


    /**
     * Sets the jurAddress value for this GBDULJurInfoType.
     *
     * @param jurAddress   * Юридический адрес
     */
    public void setJurAddress(tender.webservices.ul.GBDULJurAddressType jurAddress) {
        this.jurAddress = jurAddress;
    }


    /**
     * Gets the irrelevantJurAddressList value for this GBDULJurInfoType.
     *
     * @return irrelevantJurAddressList   * Неактуальные юридические адреса
     */
    public tender.webservices.ul.GBDULJurAddressType[] getIrrelevantJurAddressList() {
        return irrelevantJurAddressList;
    }


    /**
     * Sets the irrelevantJurAddressList value for this GBDULJurInfoType.
     *
     * @param irrelevantJurAddressList   * Неактуальные юридические адреса
     */
    public void setIrrelevantJurAddressList(tender.webservices.ul.GBDULJurAddressType[] irrelevantJurAddressList) {
        this.irrelevantJurAddressList = irrelevantJurAddressList;
    }


    /**
     * Gets the capitalSize value for this GBDULJurInfoType.
     *
     * @return capitalSize   * Размер уставного капитала, тыс. тенге
     */
    public double getCapitalSize() {
        return capitalSize;
    }


    /**
     * Sets the capitalSize value for this GBDULJurInfoType.
     *
     * @param capitalSize   * Размер уставного капитала, тыс. тенге
     */
    public void setCapitalSize(double capitalSize) {
        this.capitalSize = capitalSize;
    }


    /**
     * Gets the headOrg value for this GBDULJurInfoType.
     *
     * @return headOrg   * Сведения о головной организации
     */
    public tender.webservices.ul.GBDULJurItemType getHeadOrg() {
        return headOrg;
    }


    /**
     * Sets the headOrg value for this GBDULJurInfoType.
     *
     * @param headOrg   * Сведения о головной организации
     */
    public void setHeadOrg(tender.webservices.ul.GBDULJurItemType headOrg) {
        this.headOrg = headOrg;
    }


    /**
     * Gets the foundersUL value for this GBDULJurInfoType.
     *
     * @return foundersUL   * Сведения об учредителях – юридических лицах
     */
    public tender.webservices.ul.GBDULJurItemType[] getFoundersUL() {
        return foundersUL;
    }


    /**
     * Sets the foundersUL value for this GBDULJurInfoType.
     *
     * @param foundersUL   * Сведения об учредителях – юридических лицах
     */
    public void setFoundersUL(tender.webservices.ul.GBDULJurItemType[] foundersUL) {
        this.foundersUL = foundersUL;
    }


    /**
     * Gets the foundersFL value for this GBDULJurInfoType.
     *
     * @return foundersFL   * Сведения об учредителях – физических лицах
     */
    public tender.webservices.ul.GBDULFizItemType[] getFoundersFL() {
        return foundersFL;
    }


    /**
     * Sets the foundersFL value for this GBDULJurInfoType.
     *
     * @param foundersFL   * Сведения об учредителях – физических лицах
     */
    public void setFoundersFL(tender.webservices.ul.GBDULFizItemType[] foundersFL) {
        this.foundersFL = foundersFL;
    }

    private Object __equalsCalc = null;
    public synchronized boolean equals(Object obj) {
        if (!(obj instanceof GBDULJurInfoType)) return false;
        GBDULJurInfoType other = (GBDULJurInfoType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
            ((this.BIN==null && other.getBIN()==null) ||
             (this.BIN!=null &&
              this.BIN.equals(other.getBIN()))) &&
            ((this.regStatus==null && other.getRegStatus()==null) ||
             (this.regStatus!=null &&
              this.regStatus.equals(other.getRegStatus()))) &&
            ((this.regStatusDate==null && other.getRegStatusDate()==null) ||
             (this.regStatusDate!=null &&
              this.regStatusDate.equals(other.getRegStatusDate()))) &&
            ((this.RNN==null && other.getRNN()==null) ||
             (this.RNN!=null &&
              this.RNN.equals(other.getRNN()))) &&
            ((this.OKPO==null && other.getOKPO()==null) ||
             (this.OKPO!=null &&
              this.OKPO.equals(other.getOKPO()))) &&
            ((this.regDate==null && other.getRegDate()==null) ||
             (this.regDate!=null &&
              this.regDate.equals(other.getRegDate()))) &&
            ((this.certNumber==null && other.getCertNumber()==null) ||
             (this.certNumber!=null &&
              this.certNumber.equals(other.getCertNumber()))) &&
            ((this.certSeries==null && other.getCertSeries()==null) ||
             (this.certSeries!=null &&
              this.certSeries.equals(other.getCertSeries()))) &&
            ((this.fullName==null && other.getFullName()==null) ||
             (this.fullName!=null &&
              this.fullName.equals(other.getFullName()))) &&
            ((this.shortName==null && other.getShortName()==null) ||
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.irrelevantNameList==null && other.getIrrelevantNameList()==null) ||
             (this.irrelevantNameList!=null &&
              java.util.Arrays.equals(this.irrelevantNameList, other.getIrrelevantNameList()))) &&
            ((this.orgForm==null && other.getOrgForm()==null) ||
             (this.orgForm!=null &&
              this.orgForm.equals(other.getOrgForm()))) &&
            ((this.formOfLaw==null && other.getFormOfLaw()==null) ||
             (this.formOfLaw!=null &&
              this.formOfLaw.equals(other.getFormOfLaw()))) &&
            ((this.ownership==null && other.getOwnership()==null) ||
             (this.ownership!=null &&
              this.ownership.equals(other.getOwnership()))) &&
            ((this.commerceOrg==null && other.getCommerceOrg()==null) ||
             (this.commerceOrg!=null &&
              this.commerceOrg.equals(other.getCommerceOrg()))) &&
            ((this.enterpriseSubj==null && other.getEnterpriseSubj()==null) ||
             (this.enterpriseSubj!=null &&
              this.enterpriseSubj.equals(other.getEnterpriseSubj()))) &&
            ((this.privateEnterpriseType==null && other.getPrivateEnterpriseType()==null) ||
             (this.privateEnterpriseType!=null &&
              this.privateEnterpriseType.equals(other.getPrivateEnterpriseType()))) &&
            ((this.childOrg==null && other.getChildOrg()==null) ||
             (this.childOrg!=null &&
              this.childOrg.equals(other.getChildOrg()))) &&
            ((this.internationalOrg==null && other.getInternationalOrg()==null) ||
             (this.internationalOrg!=null &&
              this.internationalOrg.equals(other.getInternationalOrg()))) &&
            ((this.foreignInvest==null && other.getForeignInvest()==null) ||
             (this.foreignInvest!=null &&
              this.foreignInvest.equals(other.getForeignInvest()))) &&
            ((this.headInfo==null && other.getHeadInfo()==null) ||
             (this.headInfo!=null &&
              this.headInfo.equals(other.getHeadInfo()))) &&
            ((this.activityKinds==null && other.getActivityKinds()==null) ||
             (this.activityKinds!=null &&
              this.activityKinds.equals(other.getActivityKinds()))) &&
            ((this.jurAddress==null && other.getJurAddress()==null) ||
             (this.jurAddress!=null &&
              this.jurAddress.equals(other.getJurAddress()))) &&
            ((this.irrelevantJurAddressList==null && other.getIrrelevantJurAddressList()==null) ||
             (this.irrelevantJurAddressList!=null &&
              java.util.Arrays.equals(this.irrelevantJurAddressList, other.getIrrelevantJurAddressList()))) &&
            this.capitalSize == other.getCapitalSize() &&
            ((this.headOrg==null && other.getHeadOrg()==null) ||
             (this.headOrg!=null &&
              this.headOrg.equals(other.getHeadOrg()))) &&
            ((this.foundersUL==null && other.getFoundersUL()==null) ||
             (this.foundersUL!=null &&
              java.util.Arrays.equals(this.foundersUL, other.getFoundersUL()))) &&
            ((this.foundersFL==null && other.getFoundersFL()==null) ||
             (this.foundersFL!=null &&
              java.util.Arrays.equals(this.foundersFL, other.getFoundersFL())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getBIN() != null) {
            _hashCode += getBIN().hashCode();
        }
        if (getRegStatus() != null) {
            _hashCode += getRegStatus().hashCode();
        }
        if (getRegStatusDate() != null) {
            _hashCode += getRegStatusDate().hashCode();
        }
        if (getRNN() != null) {
            _hashCode += getRNN().hashCode();
        }
        if (getOKPO() != null) {
            _hashCode += getOKPO().hashCode();
        }
        if (getRegDate() != null) {
            _hashCode += getRegDate().hashCode();
        }
        if (getCertNumber() != null) {
            _hashCode += getCertNumber().hashCode();
        }
        if (getCertSeries() != null) {
            _hashCode += getCertSeries().hashCode();
        }
        if (getFullName() != null) {
            _hashCode += getFullName().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getIrrelevantNameList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIrrelevantNameList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getIrrelevantNameList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOrgForm() != null) {
            _hashCode += getOrgForm().hashCode();
        }
        if (getFormOfLaw() != null) {
            _hashCode += getFormOfLaw().hashCode();
        }
        if (getOwnership() != null) {
            _hashCode += getOwnership().hashCode();
        }
        if (getCommerceOrg() != null) {
            _hashCode += getCommerceOrg().hashCode();
        }
        if (getEnterpriseSubj() != null) {
            _hashCode += getEnterpriseSubj().hashCode();
        }
        if (getPrivateEnterpriseType() != null) {
            _hashCode += getPrivateEnterpriseType().hashCode();
        }
        if (getChildOrg() != null) {
            _hashCode += getChildOrg().hashCode();
        }
        if (getInternationalOrg() != null) {
            _hashCode += getInternationalOrg().hashCode();
        }
        if (getForeignInvest() != null) {
            _hashCode += getForeignInvest().hashCode();
        }
        if (getHeadInfo() != null) {
            _hashCode += getHeadInfo().hashCode();
        }
        if (getActivityKinds() != null) {
            _hashCode += getActivityKinds().hashCode();
        }
        if (getJurAddress() != null) {
            _hashCode += getJurAddress().hashCode();
        }
        if (getIrrelevantJurAddressList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIrrelevantJurAddressList());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getIrrelevantJurAddressList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Double(getCapitalSize()).hashCode();
        if (getHeadOrg() != null) {
            _hashCode += getHeadOrg().hashCode();
        }
        if (getFoundersUL() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFoundersUL());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFoundersUL(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFoundersFL() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFoundersFL());
                 i++) {
                Object obj = java.lang.reflect.Array.get(getFoundersFL(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GBDULJurInfoType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurInfoType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "BIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "RegStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regStatusDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "RegStatusDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("RNN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "RNN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OKPO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "OKPO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "RegDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "CertNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certSeries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "CertSeries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fullName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "FullName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULNameType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULNameType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("irrelevantNameList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "IrrelevantNameList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULIrrelevantNameType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("orgForm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "OrgForm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formOfLaw");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "FormOfLaw"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ownership");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Ownership"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("commerceOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "CommerceOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterpriseSubj");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "EnterpriseSubj"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("privateEnterpriseType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "PrivateEnterpriseType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("childOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "ChildOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("internationalOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "InternationalOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foreignInvest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "ForeignInvest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("headInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "HeadInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULFizItemType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityKinds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "ActivityKinds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jurAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "JurAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurAddressType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("irrelevantJurAddressList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "IrrelevantJurAddressList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurAddressType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capitalSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "CapitalSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("headOrg");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "HeadOrg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurItemType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foundersUL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "FoundersUL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULJurItemType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("foundersFL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "FoundersFL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "GBDULFizItemType"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.epam.com/kaz-shp2/gbdul", "Item"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           String mechType,
           Class _javaType,
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
