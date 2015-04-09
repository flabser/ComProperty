/**
 * HumanFullData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package kz.lof.webservices.store.ump;

public class HumanFullData  implements java.io.Serializable {
    private kz.lof.webservices.store.ump.Document[] idDocument;

    private long id;

    private java.lang.String firstName;

    private java.lang.String lastName;

    private java.lang.String middleName;

    private int pribyl;

    private int ubyl;

    private int total;

    private int type_address;

    private java.util.Calendar birthDate;

    private short gender;

    private java.lang.String iin;

    private kz.lof.webservices.store.ump.Nationality nationality;

    private boolean isCitizen;

    private kz.lof.webservices.store.ump.Country origin;

    private kz.lof.webservices.store.ump.Country citizenship;

    private java.lang.String rnn;

    private kz.lof.webservices.store.ump.Relation relationship;

    private kz.lof.webservices.store.ump.RegType regType;

    private java.lang.String tmpRegNumber;

    private java.util.Calendar regStartDate;

    private java.util.Calendar regEndDate;

    private java.lang.String visaSerial;

    private java.lang.String visaNumber;

    private int visaRecurrence;

    private java.util.Calendar visaDate;

    private java.util.Calendar visaStartDate;

    private java.util.Calendar visaEndaDate;

    private java.lang.String visaIssuer;

    private kz.lof.webservices.store.ump.VisitPurpose camePurpose;

    private kz.lof.webservices.store.ump.VisitReason cameReason;

    private kz.lof.webservices.store.ump.VisitPurpose gonePurpose;

    private kz.lof.webservices.store.ump.VisitReason goneReason;

    private java.lang.String livingPlace;

    private int numOfChildren;

    private kz.lof.webservices.store.ump.Address address;

    private kz.lof.webservices.store.ump.Address cameFrom;

    private kz.lof.webservices.store.ump.Address goneTo;

    private kz.lof.webservices.store.ump.Education edu;

    private java.lang.String regOffice;

    public HumanFullData() {
    }

    public HumanFullData(
           kz.lof.webservices.store.ump.Document[] idDocument,
           long id,
           java.lang.String firstName,
           java.lang.String lastName,
           java.lang.String middleName,
           int pribyl,
           int ubyl,
           int total,
           int type_address,
           java.util.Calendar birthDate,
           short gender,
           java.lang.String iin,
           kz.lof.webservices.store.ump.Nationality nationality,
           boolean isCitizen,
           kz.lof.webservices.store.ump.Country origin,
           kz.lof.webservices.store.ump.Country citizenship,
           java.lang.String rnn,
           kz.lof.webservices.store.ump.Relation relationship,
           kz.lof.webservices.store.ump.RegType regType,
           java.lang.String tmpRegNumber,
           java.util.Calendar regStartDate,
           java.util.Calendar regEndDate,
           java.lang.String visaSerial,
           java.lang.String visaNumber,
           int visaRecurrence,
           java.util.Calendar visaDate,
           java.util.Calendar visaStartDate,
           java.util.Calendar visaEndaDate,
           java.lang.String visaIssuer,
           kz.lof.webservices.store.ump.VisitPurpose camePurpose,
           kz.lof.webservices.store.ump.VisitReason cameReason,
           kz.lof.webservices.store.ump.VisitPurpose gonePurpose,
           kz.lof.webservices.store.ump.VisitReason goneReason,
           java.lang.String livingPlace,
           int numOfChildren,
           kz.lof.webservices.store.ump.Address address,
           kz.lof.webservices.store.ump.Address cameFrom,
           kz.lof.webservices.store.ump.Address goneTo,
           kz.lof.webservices.store.ump.Education edu,
           java.lang.String regOffice) {
           this.idDocument = idDocument;
           this.id = id;
           this.firstName = firstName;
           this.lastName = lastName;
           this.middleName = middleName;
           this.pribyl = pribyl;
           this.ubyl = ubyl;
           this.total = total;
           this.type_address = type_address;
           this.birthDate = birthDate;
           this.gender = gender;
           this.iin = iin;
           this.nationality = nationality;
           this.isCitizen = isCitizen;
           this.origin = origin;
           this.citizenship = citizenship;
           this.rnn = rnn;
           this.relationship = relationship;
           this.regType = regType;
           this.tmpRegNumber = tmpRegNumber;
           this.regStartDate = regStartDate;
           this.regEndDate = regEndDate;
           this.visaSerial = visaSerial;
           this.visaNumber = visaNumber;
           this.visaRecurrence = visaRecurrence;
           this.visaDate = visaDate;
           this.visaStartDate = visaStartDate;
           this.visaEndaDate = visaEndaDate;
           this.visaIssuer = visaIssuer;
           this.camePurpose = camePurpose;
           this.cameReason = cameReason;
           this.gonePurpose = gonePurpose;
           this.goneReason = goneReason;
           this.livingPlace = livingPlace;
           this.numOfChildren = numOfChildren;
           this.address = address;
           this.cameFrom = cameFrom;
           this.goneTo = goneTo;
           this.edu = edu;
           this.regOffice = regOffice;
    }


    /**
     * Gets the idDocument value for this HumanFullData.
     * 
     * @return idDocument
     */
    public kz.lof.webservices.store.ump.Document[] getIdDocument() {
        return idDocument;
    }


    /**
     * Sets the idDocument value for this HumanFullData.
     * 
     * @param idDocument
     */
    public void setIdDocument(kz.lof.webservices.store.ump.Document[] idDocument) {
        this.idDocument = idDocument;
    }


    /**
     * Gets the id value for this HumanFullData.
     * 
     * @return id
     */
    public long getId() {
        return id;
    }


    /**
     * Sets the id value for this HumanFullData.
     * 
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }


    /**
     * Gets the firstName value for this HumanFullData.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this HumanFullData.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the lastName value for this HumanFullData.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this HumanFullData.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the middleName value for this HumanFullData.
     * 
     * @return middleName
     */
    public java.lang.String getMiddleName() {
        return middleName;
    }


    /**
     * Sets the middleName value for this HumanFullData.
     * 
     * @param middleName
     */
    public void setMiddleName(java.lang.String middleName) {
        this.middleName = middleName;
    }


    /**
     * Gets the pribyl value for this HumanFullData.
     * 
     * @return pribyl
     */
    public int getPribyl() {
        return pribyl;
    }


    /**
     * Sets the pribyl value for this HumanFullData.
     * 
     * @param pribyl
     */
    public void setPribyl(int pribyl) {
        this.pribyl = pribyl;
    }


    /**
     * Gets the ubyl value for this HumanFullData.
     * 
     * @return ubyl
     */
    public int getUbyl() {
        return ubyl;
    }


    /**
     * Sets the ubyl value for this HumanFullData.
     * 
     * @param ubyl
     */
    public void setUbyl(int ubyl) {
        this.ubyl = ubyl;
    }


    /**
     * Gets the total value for this HumanFullData.
     * 
     * @return total
     */
    public int getTotal() {
        return total;
    }


    /**
     * Sets the total value for this HumanFullData.
     * 
     * @param total
     */
    public void setTotal(int total) {
        this.total = total;
    }


    /**
     * Gets the type_address value for this HumanFullData.
     * 
     * @return type_address
     */
    public int getType_address() {
        return type_address;
    }


    /**
     * Sets the type_address value for this HumanFullData.
     * 
     * @param type_address
     */
    public void setType_address(int type_address) {
        this.type_address = type_address;
    }


    /**
     * Gets the birthDate value for this HumanFullData.
     * 
     * @return birthDate
     */
    public java.util.Calendar getBirthDate() {
        return birthDate;
    }


    /**
     * Sets the birthDate value for this HumanFullData.
     * 
     * @param birthDate
     */
    public void setBirthDate(java.util.Calendar birthDate) {
        this.birthDate = birthDate;
    }


    /**
     * Gets the gender value for this HumanFullData.
     * 
     * @return gender
     */
    public short getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this HumanFullData.
     * 
     * @param gender
     */
    public void setGender(short gender) {
        this.gender = gender;
    }


    /**
     * Gets the iin value for this HumanFullData.
     * 
     * @return iin
     */
    public java.lang.String getIin() {
        return iin;
    }


    /**
     * Sets the iin value for this HumanFullData.
     * 
     * @param iin
     */
    public void setIin(java.lang.String iin) {
        this.iin = iin;
    }


    /**
     * Gets the nationality value for this HumanFullData.
     * 
     * @return nationality
     */
    public kz.lof.webservices.store.ump.Nationality getNationality() {
        return nationality;
    }


    /**
     * Sets the nationality value for this HumanFullData.
     * 
     * @param nationality
     */
    public void setNationality(kz.lof.webservices.store.ump.Nationality nationality) {
        this.nationality = nationality;
    }


    /**
     * Gets the isCitizen value for this HumanFullData.
     * 
     * @return isCitizen
     */
    public boolean isIsCitizen() {
        return isCitizen;
    }


    /**
     * Sets the isCitizen value for this HumanFullData.
     * 
     * @param isCitizen
     */
    public void setIsCitizen(boolean isCitizen) {
        this.isCitizen = isCitizen;
    }


    /**
     * Gets the origin value for this HumanFullData.
     * 
     * @return origin
     */
    public kz.lof.webservices.store.ump.Country getOrigin() {
        return origin;
    }


    /**
     * Sets the origin value for this HumanFullData.
     * 
     * @param origin
     */
    public void setOrigin(kz.lof.webservices.store.ump.Country origin) {
        this.origin = origin;
    }


    /**
     * Gets the citizenship value for this HumanFullData.
     * 
     * @return citizenship
     */
    public kz.lof.webservices.store.ump.Country getCitizenship() {
        return citizenship;
    }


    /**
     * Sets the citizenship value for this HumanFullData.
     * 
     * @param citizenship
     */
    public void setCitizenship(kz.lof.webservices.store.ump.Country citizenship) {
        this.citizenship = citizenship;
    }


    /**
     * Gets the rnn value for this HumanFullData.
     * 
     * @return rnn
     */
    public java.lang.String getRnn() {
        return rnn;
    }


    /**
     * Sets the rnn value for this HumanFullData.
     * 
     * @param rnn
     */
    public void setRnn(java.lang.String rnn) {
        this.rnn = rnn;
    }


    /**
     * Gets the relationship value for this HumanFullData.
     * 
     * @return relationship
     */
    public kz.lof.webservices.store.ump.Relation getRelationship() {
        return relationship;
    }


    /**
     * Sets the relationship value for this HumanFullData.
     * 
     * @param relationship
     */
    public void setRelationship(kz.lof.webservices.store.ump.Relation relationship) {
        this.relationship = relationship;
    }


    /**
     * Gets the regType value for this HumanFullData.
     * 
     * @return regType
     */
    public kz.lof.webservices.store.ump.RegType getRegType() {
        return regType;
    }


    /**
     * Sets the regType value for this HumanFullData.
     * 
     * @param regType
     */
    public void setRegType(kz.lof.webservices.store.ump.RegType regType) {
        this.regType = regType;
    }


    /**
     * Gets the tmpRegNumber value for this HumanFullData.
     * 
     * @return tmpRegNumber
     */
    public java.lang.String getTmpRegNumber() {
        return tmpRegNumber;
    }


    /**
     * Sets the tmpRegNumber value for this HumanFullData.
     * 
     * @param tmpRegNumber
     */
    public void setTmpRegNumber(java.lang.String tmpRegNumber) {
        this.tmpRegNumber = tmpRegNumber;
    }


    /**
     * Gets the regStartDate value for this HumanFullData.
     * 
     * @return regStartDate
     */
    public java.util.Calendar getRegStartDate() {
        return regStartDate;
    }


    /**
     * Sets the regStartDate value for this HumanFullData.
     * 
     * @param regStartDate
     */
    public void setRegStartDate(java.util.Calendar regStartDate) {
        this.regStartDate = regStartDate;
    }


    /**
     * Gets the regEndDate value for this HumanFullData.
     * 
     * @return regEndDate
     */
    public java.util.Calendar getRegEndDate() {
        return regEndDate;
    }


    /**
     * Sets the regEndDate value for this HumanFullData.
     * 
     * @param regEndDate
     */
    public void setRegEndDate(java.util.Calendar regEndDate) {
        this.regEndDate = regEndDate;
    }


    /**
     * Gets the visaSerial value for this HumanFullData.
     * 
     * @return visaSerial
     */
    public java.lang.String getVisaSerial() {
        return visaSerial;
    }


    /**
     * Sets the visaSerial value for this HumanFullData.
     * 
     * @param visaSerial
     */
    public void setVisaSerial(java.lang.String visaSerial) {
        this.visaSerial = visaSerial;
    }


    /**
     * Gets the visaNumber value for this HumanFullData.
     * 
     * @return visaNumber
     */
    public java.lang.String getVisaNumber() {
        return visaNumber;
    }


    /**
     * Sets the visaNumber value for this HumanFullData.
     * 
     * @param visaNumber
     */
    public void setVisaNumber(java.lang.String visaNumber) {
        this.visaNumber = visaNumber;
    }


    /**
     * Gets the visaRecurrence value for this HumanFullData.
     * 
     * @return visaRecurrence
     */
    public int getVisaRecurrence() {
        return visaRecurrence;
    }


    /**
     * Sets the visaRecurrence value for this HumanFullData.
     * 
     * @param visaRecurrence
     */
    public void setVisaRecurrence(int visaRecurrence) {
        this.visaRecurrence = visaRecurrence;
    }


    /**
     * Gets the visaDate value for this HumanFullData.
     * 
     * @return visaDate
     */
    public java.util.Calendar getVisaDate() {
        return visaDate;
    }


    /**
     * Sets the visaDate value for this HumanFullData.
     * 
     * @param visaDate
     */
    public void setVisaDate(java.util.Calendar visaDate) {
        this.visaDate = visaDate;
    }


    /**
     * Gets the visaStartDate value for this HumanFullData.
     * 
     * @return visaStartDate
     */
    public java.util.Calendar getVisaStartDate() {
        return visaStartDate;
    }


    /**
     * Sets the visaStartDate value for this HumanFullData.
     * 
     * @param visaStartDate
     */
    public void setVisaStartDate(java.util.Calendar visaStartDate) {
        this.visaStartDate = visaStartDate;
    }


    /**
     * Gets the visaEndaDate value for this HumanFullData.
     * 
     * @return visaEndaDate
     */
    public java.util.Calendar getVisaEndaDate() {
        return visaEndaDate;
    }


    /**
     * Sets the visaEndaDate value for this HumanFullData.
     * 
     * @param visaEndaDate
     */
    public void setVisaEndaDate(java.util.Calendar visaEndaDate) {
        this.visaEndaDate = visaEndaDate;
    }


    /**
     * Gets the visaIssuer value for this HumanFullData.
     * 
     * @return visaIssuer
     */
    public java.lang.String getVisaIssuer() {
        return visaIssuer;
    }


    /**
     * Sets the visaIssuer value for this HumanFullData.
     * 
     * @param visaIssuer
     */
    public void setVisaIssuer(java.lang.String visaIssuer) {
        this.visaIssuer = visaIssuer;
    }


    /**
     * Gets the camePurpose value for this HumanFullData.
     * 
     * @return camePurpose
     */
    public kz.lof.webservices.store.ump.VisitPurpose getCamePurpose() {
        return camePurpose;
    }


    /**
     * Sets the camePurpose value for this HumanFullData.
     * 
     * @param camePurpose
     */
    public void setCamePurpose(kz.lof.webservices.store.ump.VisitPurpose camePurpose) {
        this.camePurpose = camePurpose;
    }


    /**
     * Gets the cameReason value for this HumanFullData.
     * 
     * @return cameReason
     */
    public kz.lof.webservices.store.ump.VisitReason getCameReason() {
        return cameReason;
    }


    /**
     * Sets the cameReason value for this HumanFullData.
     * 
     * @param cameReason
     */
    public void setCameReason(kz.lof.webservices.store.ump.VisitReason cameReason) {
        this.cameReason = cameReason;
    }


    /**
     * Gets the gonePurpose value for this HumanFullData.
     * 
     * @return gonePurpose
     */
    public kz.lof.webservices.store.ump.VisitPurpose getGonePurpose() {
        return gonePurpose;
    }


    /**
     * Sets the gonePurpose value for this HumanFullData.
     * 
     * @param gonePurpose
     */
    public void setGonePurpose(kz.lof.webservices.store.ump.VisitPurpose gonePurpose) {
        this.gonePurpose = gonePurpose;
    }


    /**
     * Gets the goneReason value for this HumanFullData.
     * 
     * @return goneReason
     */
    public kz.lof.webservices.store.ump.VisitReason getGoneReason() {
        return goneReason;
    }


    /**
     * Sets the goneReason value for this HumanFullData.
     * 
     * @param goneReason
     */
    public void setGoneReason(kz.lof.webservices.store.ump.VisitReason goneReason) {
        this.goneReason = goneReason;
    }


    /**
     * Gets the livingPlace value for this HumanFullData.
     * 
     * @return livingPlace
     */
    public java.lang.String getLivingPlace() {
        return livingPlace;
    }


    /**
     * Sets the livingPlace value for this HumanFullData.
     * 
     * @param livingPlace
     */
    public void setLivingPlace(java.lang.String livingPlace) {
        this.livingPlace = livingPlace;
    }


    /**
     * Gets the numOfChildren value for this HumanFullData.
     * 
     * @return numOfChildren
     */
    public int getNumOfChildren() {
        return numOfChildren;
    }


    /**
     * Sets the numOfChildren value for this HumanFullData.
     * 
     * @param numOfChildren
     */
    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }


    /**
     * Gets the address value for this HumanFullData.
     * 
     * @return address
     */
    public kz.lof.webservices.store.ump.Address getAddress() {
        return address;
    }


    /**
     * Sets the address value for this HumanFullData.
     * 
     * @param address
     */
    public void setAddress(kz.lof.webservices.store.ump.Address address) {
        this.address = address;
    }


    /**
     * Gets the cameFrom value for this HumanFullData.
     * 
     * @return cameFrom
     */
    public kz.lof.webservices.store.ump.Address getCameFrom() {
        return cameFrom;
    }


    /**
     * Sets the cameFrom value for this HumanFullData.
     * 
     * @param cameFrom
     */
    public void setCameFrom(kz.lof.webservices.store.ump.Address cameFrom) {
        this.cameFrom = cameFrom;
    }


    /**
     * Gets the goneTo value for this HumanFullData.
     * 
     * @return goneTo
     */
    public kz.lof.webservices.store.ump.Address getGoneTo() {
        return goneTo;
    }


    /**
     * Sets the goneTo value for this HumanFullData.
     * 
     * @param goneTo
     */
    public void setGoneTo(kz.lof.webservices.store.ump.Address goneTo) {
        this.goneTo = goneTo;
    }


    /**
     * Gets the edu value for this HumanFullData.
     * 
     * @return edu
     */
    public kz.lof.webservices.store.ump.Education getEdu() {
        return edu;
    }


    /**
     * Sets the edu value for this HumanFullData.
     * 
     * @param edu
     */
    public void setEdu(kz.lof.webservices.store.ump.Education edu) {
        this.edu = edu;
    }


    /**
     * Gets the regOffice value for this HumanFullData.
     * 
     * @return regOffice
     */
    public java.lang.String getRegOffice() {
        return regOffice;
    }


    /**
     * Sets the regOffice value for this HumanFullData.
     * 
     * @param regOffice
     */
    public void setRegOffice(java.lang.String regOffice) {
        this.regOffice = regOffice;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HumanFullData)) return false;
        HumanFullData other = (HumanFullData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.idDocument==null && other.getIdDocument()==null) || 
             (this.idDocument!=null &&
              java.util.Arrays.equals(this.idDocument, other.getIdDocument()))) &&
            this.id == other.getId() &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.middleName==null && other.getMiddleName()==null) || 
             (this.middleName!=null &&
              this.middleName.equals(other.getMiddleName()))) &&
            this.pribyl == other.getPribyl() &&
            this.ubyl == other.getUbyl() &&
            this.total == other.getTotal() &&
            this.type_address == other.getType_address() &&
            ((this.birthDate==null && other.getBirthDate()==null) || 
             (this.birthDate!=null &&
              this.birthDate.equals(other.getBirthDate()))) &&
            this.gender == other.getGender() &&
            ((this.iin==null && other.getIin()==null) || 
             (this.iin!=null &&
              this.iin.equals(other.getIin()))) &&
            ((this.nationality==null && other.getNationality()==null) || 
             (this.nationality!=null &&
              this.nationality.equals(other.getNationality()))) &&
            this.isCitizen == other.isIsCitizen() &&
            ((this.origin==null && other.getOrigin()==null) || 
             (this.origin!=null &&
              this.origin.equals(other.getOrigin()))) &&
            ((this.citizenship==null && other.getCitizenship()==null) || 
             (this.citizenship!=null &&
              this.citizenship.equals(other.getCitizenship()))) &&
            ((this.rnn==null && other.getRnn()==null) || 
             (this.rnn!=null &&
              this.rnn.equals(other.getRnn()))) &&
            ((this.relationship==null && other.getRelationship()==null) || 
             (this.relationship!=null &&
              this.relationship.equals(other.getRelationship()))) &&
            ((this.regType==null && other.getRegType()==null) || 
             (this.regType!=null &&
              this.regType.equals(other.getRegType()))) &&
            ((this.tmpRegNumber==null && other.getTmpRegNumber()==null) || 
             (this.tmpRegNumber!=null &&
              this.tmpRegNumber.equals(other.getTmpRegNumber()))) &&
            ((this.regStartDate==null && other.getRegStartDate()==null) || 
             (this.regStartDate!=null &&
              this.regStartDate.equals(other.getRegStartDate()))) &&
            ((this.regEndDate==null && other.getRegEndDate()==null) || 
             (this.regEndDate!=null &&
              this.regEndDate.equals(other.getRegEndDate()))) &&
            ((this.visaSerial==null && other.getVisaSerial()==null) || 
             (this.visaSerial!=null &&
              this.visaSerial.equals(other.getVisaSerial()))) &&
            ((this.visaNumber==null && other.getVisaNumber()==null) || 
             (this.visaNumber!=null &&
              this.visaNumber.equals(other.getVisaNumber()))) &&
            this.visaRecurrence == other.getVisaRecurrence() &&
            ((this.visaDate==null && other.getVisaDate()==null) || 
             (this.visaDate!=null &&
              this.visaDate.equals(other.getVisaDate()))) &&
            ((this.visaStartDate==null && other.getVisaStartDate()==null) || 
             (this.visaStartDate!=null &&
              this.visaStartDate.equals(other.getVisaStartDate()))) &&
            ((this.visaEndaDate==null && other.getVisaEndaDate()==null) || 
             (this.visaEndaDate!=null &&
              this.visaEndaDate.equals(other.getVisaEndaDate()))) &&
            ((this.visaIssuer==null && other.getVisaIssuer()==null) || 
             (this.visaIssuer!=null &&
              this.visaIssuer.equals(other.getVisaIssuer()))) &&
            ((this.camePurpose==null && other.getCamePurpose()==null) || 
             (this.camePurpose!=null &&
              this.camePurpose.equals(other.getCamePurpose()))) &&
            ((this.cameReason==null && other.getCameReason()==null) || 
             (this.cameReason!=null &&
              this.cameReason.equals(other.getCameReason()))) &&
            ((this.gonePurpose==null && other.getGonePurpose()==null) || 
             (this.gonePurpose!=null &&
              this.gonePurpose.equals(other.getGonePurpose()))) &&
            ((this.goneReason==null && other.getGoneReason()==null) || 
             (this.goneReason!=null &&
              this.goneReason.equals(other.getGoneReason()))) &&
            ((this.livingPlace==null && other.getLivingPlace()==null) || 
             (this.livingPlace!=null &&
              this.livingPlace.equals(other.getLivingPlace()))) &&
            this.numOfChildren == other.getNumOfChildren() &&
            ((this.address==null && other.getAddress()==null) || 
             (this.address!=null &&
              this.address.equals(other.getAddress()))) &&
            ((this.cameFrom==null && other.getCameFrom()==null) || 
             (this.cameFrom!=null &&
              this.cameFrom.equals(other.getCameFrom()))) &&
            ((this.goneTo==null && other.getGoneTo()==null) || 
             (this.goneTo!=null &&
              this.goneTo.equals(other.getGoneTo()))) &&
            ((this.edu==null && other.getEdu()==null) || 
             (this.edu!=null &&
              this.edu.equals(other.getEdu()))) &&
            ((this.regOffice==null && other.getRegOffice()==null) || 
             (this.regOffice!=null &&
              this.regOffice.equals(other.getRegOffice())));
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
        if (getIdDocument() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIdDocument());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIdDocument(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        _hashCode += new Long(getId()).hashCode();
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getMiddleName() != null) {
            _hashCode += getMiddleName().hashCode();
        }
        _hashCode += getPribyl();
        _hashCode += getUbyl();
        _hashCode += getTotal();
        _hashCode += getType_address();
        if (getBirthDate() != null) {
            _hashCode += getBirthDate().hashCode();
        }
        _hashCode += getGender();
        if (getIin() != null) {
            _hashCode += getIin().hashCode();
        }
        if (getNationality() != null) {
            _hashCode += getNationality().hashCode();
        }
        _hashCode += (isIsCitizen() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getOrigin() != null) {
            _hashCode += getOrigin().hashCode();
        }
        if (getCitizenship() != null) {
            _hashCode += getCitizenship().hashCode();
        }
        if (getRnn() != null) {
            _hashCode += getRnn().hashCode();
        }
        if (getRelationship() != null) {
            _hashCode += getRelationship().hashCode();
        }
        if (getRegType() != null) {
            _hashCode += getRegType().hashCode();
        }
        if (getTmpRegNumber() != null) {
            _hashCode += getTmpRegNumber().hashCode();
        }
        if (getRegStartDate() != null) {
            _hashCode += getRegStartDate().hashCode();
        }
        if (getRegEndDate() != null) {
            _hashCode += getRegEndDate().hashCode();
        }
        if (getVisaSerial() != null) {
            _hashCode += getVisaSerial().hashCode();
        }
        if (getVisaNumber() != null) {
            _hashCode += getVisaNumber().hashCode();
        }
        _hashCode += getVisaRecurrence();
        if (getVisaDate() != null) {
            _hashCode += getVisaDate().hashCode();
        }
        if (getVisaStartDate() != null) {
            _hashCode += getVisaStartDate().hashCode();
        }
        if (getVisaEndaDate() != null) {
            _hashCode += getVisaEndaDate().hashCode();
        }
        if (getVisaIssuer() != null) {
            _hashCode += getVisaIssuer().hashCode();
        }
        if (getCamePurpose() != null) {
            _hashCode += getCamePurpose().hashCode();
        }
        if (getCameReason() != null) {
            _hashCode += getCameReason().hashCode();
        }
        if (getGonePurpose() != null) {
            _hashCode += getGonePurpose().hashCode();
        }
        if (getGoneReason() != null) {
            _hashCode += getGoneReason().hashCode();
        }
        if (getLivingPlace() != null) {
            _hashCode += getLivingPlace().hashCode();
        }
        _hashCode += getNumOfChildren();
        if (getAddress() != null) {
            _hashCode += getAddress().hashCode();
        }
        if (getCameFrom() != null) {
            _hashCode += getCameFrom().hashCode();
        }
        if (getGoneTo() != null) {
            _hashCode += getGoneTo().hashCode();
        }
        if (getEdu() != null) {
            _hashCode += getEdu().hashCode();
        }
        if (getRegOffice() != null) {
            _hashCode += getRegOffice().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HumanFullData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "HumanFullData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idDocument");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "idDocument"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Document"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://ump.webservices.lof.kz", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "firstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "lastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("middleName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "middleName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pribyl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "pribyl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ubyl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "ubyl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("total");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "total"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("type_address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "type_address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("birthDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "birthDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "iin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nationality");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "nationality"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Nationality"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isCitizen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "isCitizen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "origin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Country"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("citizenship");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "citizenship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Country"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rnn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "rnn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relationship");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "relationship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Relation"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "regType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "RegType"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tmpRegNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "tmpRegNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regStartDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "regStartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "regEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visaSerial");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "visaSerial"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visaNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "visaNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visaRecurrence");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "visaRecurrence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visaDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "visaDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visaStartDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "visaStartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visaEndaDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "visaEndaDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("visaIssuer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "visaIssuer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("camePurpose");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "camePurpose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "VisitPurpose"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cameReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "cameReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "VisitReason"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gonePurpose");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "gonePurpose"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "VisitPurpose"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("goneReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "goneReason"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "VisitReason"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("livingPlace");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "livingPlace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numOfChildren");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "numOfChildren"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("address");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "address"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cameFrom");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "cameFrom"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("goneTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "goneTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Address"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edu");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "edu"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "Education"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("regOffice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://store.ump.webservices.lof.kz", "regOffice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
