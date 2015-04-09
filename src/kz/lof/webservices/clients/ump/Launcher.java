package kz.lof.webservices.clients.ump;

import java.io.*;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import kz.flabs.users.User;
import kz.lof.webservices.store.ump.*;

import kz.pchelka.env.Environment;

public class Launcher {
	private static final String logFileDate = DateFormat.getDateTimeInstance().format(new Date()).replace('.', '-').replace(' ', '_').replace(':', '-');
	private static HumansSearchServiceProxy  hSSProxy;
		private static final String logFileName = "";
		private static final String logFilePath = "logs\\";
	
	public static void main(String[] args) {
		Environment.addExtHost("ump", "localhost:15045", "УМП");
//		Environment.addExtHost("FRNS", "127.0.0.1:15045", "Иностранцы");
//		Environment.addExtHost("UKI", "127.0.0.1:15045", "УКИ");
		hSSProxy = new HumansSearchServiceProxy(new User("temp_user"));
//		fSSProxy = new ForeignersSearchServiceProxy(new User("temp_user"));
//		qSProxy = new QuestServiceProxy(new User("temp_user"));
		try {			
//			testHSSServiceGetHumanByFIO("","������","",0,0, "RUS");//++
			Street street = new Street();
			street.setId(403);
			street.setName("");
			Address[] address = {new kz.lof.webservices.store.ump.Address(null, null, null, street, "", "")};
			testHSSServiceGetHumanByAddr(street, "23", "3", 0, 0, "RUS");
			testHSSServiceGetHumanByDoc("111", 0, 0, "RUS");
			testHSSServiceGetCitizenByIIN("750507402103", "RUS");//++
			testHSSServiceGetFullData((long)645870, "RUS");//++
			testHSSServiceGetAllCountries("RUS");
			testHSSServiceGetAllDistricts("RUS");
			testHSSServiceGetAllStreets("RUS");
			testHSSServiceGetStreets("мак","RUS");
			testHSSServiceGetDistricts("тур","RUS");
			testHSSServiceGetCountry("алм","RUS");
//			
//			testFSSServiceGetPersonByFIO("H*A","M*S","",0,0,"");
//			testFSSServiceGetPersonByDoc("149��","0678383","");
//			testFSSServiceGetFullData(10000005, "");
		    
			Calendar startDate = new GregorianCalendar(1990, 3, 17);
			Calendar endDate = new GregorianCalendar(2013, 3, 17);
			
			testHSSServiceGetInfoMigrationByAdr(address, startDate, endDate, 1, 1, "");
			testHSSServiceGetInfoMigrationNationByAdr(address, startDate, endDate, 1, 1, "");
			
//			testHSSServiceGetLog(h);
			
		} catch (RemoteException re) {
			System.out.println("Ошибка");
			re.printStackTrace();
		}
		
//		public MigrationData[] getInfoMigrationByAdr(Address[] address,Date startDate, Date endDate,  int pageNum, int resultsOnPage, String lang) {
	}
	
	public static void testHSSServiceGetInfoMigrationByAdr(Address[] address,Calendar startDate, Calendar endDate,  int pageNum, int resultsOnPage, String lang) throws RemoteException {
        outln("  Method: HumansSearchService -> getInfoMigrationNationByAdr");
        outln("    Result:");
//        for(int i=0; i< hSSProxy.getInfoMigrationNationByAdr(address, startDate, endDate, pageNum, resultsOnPage, lang).length; i++){
        MigrationData iData = hSSProxy.getInfoMigrationByAdr(address, startDate, endDate, pageNum, resultsOnPage, lang)[0];
            outln("      Record data number: "+(1));
            outln("      adr = " + iData.getCountByAge()[1].getAge());
//            outln("      adr = " + iData.getAddress().getStreet().getName());
            
            
            outln("----------------------------------");
//        }
        outln("-------------------------------------------------------------------------");
    }
	
	public static void testHSSServiceGetInfoMigrationNationByAdr(Address[] address,Calendar startDate, Calendar endDate,  int pageNum, int resultsOnPage, String lang) throws RemoteException {
        outln("  Method: HumansSearchService -> getInfoMigrationNationByAdr");
        outln("    Result:");
//        for(int i=0; i< hSSProxy.getInfoMigrationNationByAdr(address, startDate, endDate, pageNum, resultsOnPage, lang).length; i++){
            MigrationNatData iData = hSSProxy.getInfoMigrationNationByAdr(address, startDate, endDate, pageNum, resultsOnPage, lang)[0];
            outln("      Record data number: "+(1));
            outln("      adr = " + iData.getAddress().getStreet().getId());
            outln("      nat = " + iData.getCountMigByNat()[0].getNat().getIdNat());
            outln("      nat = " + iData.getCountMigByNat()[0].getNat().getFemaleName());
            outln("      nat = " + iData.getCountMigByNat()[0].getNat().getMaleName());
            outln("      nat = " + iData.getCountMigByNat()[0].getPribylCount());
            outln("      nat = " + iData.getCountMigByNat()[0].getUbylCount());
            
            outln("----------------------------------");
//        }
        outln("-------------------------------------------------------------------------");
    }
	
	
//	public static void testQServiceSearchPeople(WantedData[] peopleList, int pageNum, int resultsOnPage, String lang) throws RemoteException {
//		outln("  Method: QuestService -> searchPeople");
//		
//			WantedResult iData = qSProxy.searchPeople(peopleList, pageNum, resultsOnPage, lang);
//			outln("      Records found: " + iData.getTotalFound());
//			outln("      First record data: ");
//			for(int i = 0; i< iData.getTotalFound(); i++){
//				QuestData iShortData = iData.getQuestData()[i];
//				outln("        First Name = " + iShortData.getWanted().getFirstName());
//				outln("        Last Name = " + iShortData.getWanted().getLastName());
//				outln("        MiddleName = " + iShortData.getWanted().getMiddleName());
//				outln("        Birth Date = "  + DateFormat.getDateInstance().format(iShortData.getWanted().getBirthDate().getTime()));
//				outln("        category = " + iShortData.getCategory());
//				outln("        initaiator = " + iShortData.getInitiator());	
//				outln("-------------------------------------------------------------------------");
//			}
//		
//	}
//	
//	public static void testFSSServiceGetLog( Date date) throws RemoteException {
//        outln("  Method: ForeignersSearchService -> getLog");
//        outln("    Result:");
//        String[] d = fSSProxy.getLog(date);
//        System.out.println(d.length);
//        for(int i=0; i< d.length; i++){
//            String iData = d[i];
//            outln("      Record data number: "+(i+1));
//            outln("      id = " + iData);
//            outln("----------------------------------");
//        }
//        outln("-------------------------------------------------------------------------");
//    }
//	
//	public static void testHSSServiceGetLog( Date date) throws RemoteException {
//        outln("  Method: HumansSearchService -> getLog");
//        outln("    Result:");
//        String[] d = hSSProxy.getLog(date);
//        System.out.println(d.length);
//        for(int i=0; i< d.length; i++){
//            String iData = d[i];
//            outln("      Record data number: "+(i+1));
//            outln("      id = " + iData);
//            outln("----------------------------------");
//        }
//        outln("-------------------------------------------------------------------------");
//    }
//	
//	public static void testFSSServiceGetPersonByFIO(String firstName, String lastName, String middleName, int pageNum, int resultsOnPage, String lang) throws RemoteException {
//		outln("  Method: ForeignersSearchService -> getPersonByFIO");
//		outln("    Parameters:");
//		outln("      lastName = " + lastName);
//		outln("      firstName = " + firstName);
//		outln("      middleName = " + middleName);
//		outln("      pageNum = " + pageNum);
//		outln("      resultsOnPage = " + resultsOnPage);
//		outln("      lang = " + lang);
//		outln("    Result:");
//		PersonSearchResult iData = fSSProxy.getPersonByFIO(firstName, lastName, middleName, pageNum, resultsOnPage, lang);
//		outln("      Records found: " + iData.getTotalFound());
//		outln("      First record data: ");
//		PersonShortData iShortData = iData.getShortData()[0];
//		outln("        id = " + iShortData.getId());
//		outln("        First Name = " + iShortData.getFirstName());
//		outln("        Last Name = " + iShortData.getLastName());
//		outln("        MiddleName = " + iShortData.getMiddleName());
//		outln("        gender = " + iShortData.getGender());
//		outln("        Birth Date = "  + DateFormat.getDateInstance().format(iShortData.getBirthDate().getTime()));;
//		outln("        Country (citizen):");
//		outln("          id = " + iShortData.getCitizenship().getId());
//		outln("          name = " + iShortData.getCitizenship().getName());
//		outln("        Country (origin):");
//		outln("          id = " + iShortData.getOrigin().getId());
//		outln("          name = " + iShortData.getOrigin().getName());
//		outln("        Nationality:");
//		outln("          id = " + iShortData.getNationality().getIdNat());
//		outln("          maleName = " + iShortData.getNationality().getMaleName());
//		outln("          femaleName = " + iShortData.getNationality().getFemaleName());
//		outln("        Document:");
//		outln("          id = " + iShortData.getIdDocument().getId());
//		outln("        	 DocumentType:");
//		outln("          	id = " + iShortData.getIdDocument().getType().getId());
//		outln("             point = " + iShortData.getIdDocument().getType().getPoint());
//		outln("          serial = " + iShortData.getIdDocument().getSerial());
//		outln("          number = " + iShortData.getIdDocument().getNumber());
//		outln("          expirationDate = "  + DateFormat.getDateInstance().format(iShortData.getIdDocument().getExpirationDate().getTime()));
//	
//		outln("-------------------------------------------------------------------------");
//	}
//	
//	public static void testFSSServiceGetPersonByDoc(String serial, String number,  String lang) throws RemoteException {
//		outln("  Method: ForeignersSearchService -> getPersonByDoc");
//		outln("    Parameters:");
//		outln("      serial = " + serial);
//		outln("      number = " + number);
//		outln("      lang = " + lang);
//		outln("    Result:");
//		PersonSearchResult iData = fSSProxy.getPersonByDoc(serial, number, lang);
//		outln("      Records found: " + iData.getTotalFound());
//		outln("      First record data: ");
//		PersonShortData iShortData = iData.getShortData()[0];
//		outln("        id = " + iShortData.getId());
//		outln("        First Name = " + iShortData.getFirstName());
//		outln("        Last Name = " + iShortData.getLastName());
//		outln("        MiddleName = " + iShortData.getMiddleName());
//		outln("        gender = " + iShortData.getGender());
//		outln("        Birth Date = "  + DateFormat.getDateInstance().format(iShortData.getBirthDate().getTime()));;
//		outln("        Country (citizen):");
//		outln("          id = " + iShortData.getCitizenship().getId());
//		outln("          name = " + iShortData.getCitizenship().getName());
//		outln("        Country (origin):");
//		outln("          id = " + iShortData.getOrigin().getId());
//		outln("          name = " + iShortData.getOrigin().getName());
//		outln("        Nationality:");
//		outln("          id = " + iShortData.getNationality().getIdNat());
//		outln("          maleName = " + iShortData.getNationality().getMaleName());
//		outln("          femaleName = " + iShortData.getNationality().getFemaleName());
//		outln("        Document:");
//		outln("          id = " + iShortData.getIdDocument().getId());
//		outln("        	 DocumentType:");
//		outln("          	id = " + iShortData.getIdDocument().getType().getId());
//		outln("             point = " + iShortData.getIdDocument().getType().getPoint());
//		outln("          number = " + iShortData.getIdDocument().getNumber());
//		outln("          serial = " + iShortData.getIdDocument().getSerial());
//		outln("          expirationDate = "  + DateFormat.getDateInstance().format(iShortData.getIdDocument().getExpirationDate().getTime()));
//		
//		
//		
//		outln("-------------------------------------------------------------------------");
//	}
//	
//	public static void testFSSServiceGetFullData(long id, String lang) throws RemoteException {
//		outln("  Method: ForeignersSearchService -> getFullData");
//		outln("    Parameters:");
//		outln("      id = " + id);
//		outln("      lang = " + lang);
//		outln("    Result:");
//		PersonFullData iData = fSSProxy.getFullData(id, lang);
//		outln("      First record data: ");
//		PersonShortData iShortData = iData.getBasicData();
//		outln("        id = " + iShortData.getId());
//		outln("        First Name = " + iShortData.getFirstName());
//		outln("        Last Name = " + iShortData.getLastName());
//		outln("        MiddleName = " + iShortData.getMiddleName());
//		outln("        gender = " + iShortData.getGender());
//		outln("        Birth Date = "  + DateFormat.getDateInstance().format(iShortData.getBirthDate().getTime()));
//		outln("        Country (citizen):");
//		outln("          id = " + iShortData.getCitizenship().getId());
//		outln("          name = " + iShortData.getCitizenship().getName());
//		outln("        Country (origin):");
//		outln("          id = " + iShortData.getOrigin().getId());
//		outln("          name = " + iShortData.getOrigin().getName());
//		outln("        Nationality:");
//		outln("          id = " + iShortData.getNationality().getIdNat());
//		outln("          maleName = " + iShortData.getNationality().getMaleName());
//		outln("          femaleName = " + iShortData.getNationality().getFemaleName());
//		outln("        Document:");
//		outln("          id = " + iShortData.getIdDocument().getId());
//		outln("        	 DocumentType:");
//		outln("          	id = " + iShortData.getIdDocument().getType().getId());
//		outln("             point = " + iShortData.getIdDocument().getType().getPoint());
//		outln("          serial = " + iShortData.getIdDocument().getSerial());
//		outln("          number = " + iShortData.getIdDocument().getNumber());
//		outln("          expirationDate = "  + DateFormat.getDateInstance().format(iShortData.getIdDocument().getExpirationDate().getTime()));
//		
//		for(int i=0; i< iData.getVisitData().length; i++){
//			VisitData visit = iData.getVisitData()[i];
//			outln("        VisitData:");
//			outln("      Record data number: "+(i+1));
//			outln("          regLicenseNumber = " + visit.getRegLicenseNumber() );
//			outln("          regStartDate = " +DateFormat.getDateInstance().format( visit.getRegStartDate().getTime()) );
//			outln("          regEndDate = " + DateFormat.getDateInstance().format(visit.getRegEndDate().getTime()) );
//			outln("          visaSerial = " + visit.getVisaSerial() );
//			outln("          visaNumber = " + visit.getVisaNumber() );
//			outln("          visaRatio = " + visit.getVisaRatio() );
//			outln("          visaGetDate = " + DateFormat.getDateInstance().format(visit.getVisaGetDate().getTime()) );
//			outln("          visaStartDate = " + DateFormat.getDateInstance().format(visit.getVisaStartDate().getTime()) );
//			outln("          visaEndDate = " + DateFormat.getDateInstance().format(visit.getVisaEndDate().getTime()) );
//			outln("          visaAuthority = " + visit.getVisaAuthority() );
//			outln("          livingPlace = " + visit.getLivingPlace() );
//			outln("          street = " + visit.getAddress().getStreet().getName());
//			outln("          house = " + visit.getAddress().getHouse());
//			outln("          flat = " + visit.getAddress().getFlat());
//			
//		}
//		
//		outln("-------------------------------------------------------------------------");
//	}
	public static void testHSSServiceGetStreets(String name, String lang) throws RemoteException {
		outln("  Method: HumansSearchService -> getStreets");
		outln("    Result:");
		for(int i=0; i< hSSProxy.getStreets(name, lang).length; i++){
			Street iData = hSSProxy.getStreets(name, lang)[i];
			outln("      Record data number: "+(i+1));
			outln("      id = " + iData.getId());
			outln("      name rus = " + iData.getName());
			outln("      Country");
			outln("      	id = " + iData.getCity().getDistrict().getRegion().getCountry().getId());
			outln("      	name rus = " + iData.getCity().getDistrict().getRegion().getCountry().getName());
			outln("      Region");
			outln("      	id = " + iData.getCity().getDistrict().getRegion().getId());
			outln("      	name rus = " + iData.getCity().getDistrict().getRegion().getName());
			outln("      District");
			outln("      	id = " + iData.getCity().getDistrict().getId());
			outln("      	name rus = " + iData.getCity().getDistrict().getName());
			outln("      City");
			outln("      	id = " + iData.getCity().getId());
			outln("      	name rus = " + iData.getCity().getName());
			outln("----------------------------------");
		}
		outln("-------------------------------------------------------------------------");
	}
	
	public static void testHSSServiceGetDistricts(String name, String lang) throws RemoteException {
		outln("  Method: HumansSearchService -> getDistricts");
		outln("    Result:");
		for(int i=0; i< hSSProxy.getDistricts(name, lang).length; i++){
			District iData = hSSProxy.getDistricts(name, lang)[i];
			outln("      Record data number: "+(i+1));
			outln("      id = " + iData.getId());
			outln("      name rus = " + iData.getName());
			outln("      Country");
			outln("      	id = " + iData.getRegion().getCountry().getId());
			outln("      	name rus = " + iData.getRegion().getCountry().getName());
			outln("      Region");
			outln("      	id = " + iData.getRegion().getId());
			outln("      	name rus = " + iData.getRegion().getName());
			outln("----------------------------------");
		}
		outln("-------------------------------------------------------------------------");
	}
	public static void testHSSServiceGetCountry(String name, String lang) throws RemoteException {
		outln("  Method: HumansSearchService -> getCountry");
		outln("    Result:");
		for(int i=0; i< hSSProxy.getCountry(name, lang).length; i++){
			Country iData = hSSProxy.getCountry(name, lang)[i];
			outln("      Record data number: "+(i+1));
			outln("      id = " + iData.getId());
			outln("      name rus = " + iData.getName());
			outln("----------------------------------");
		}
		outln("-------------------------------------------------------------------------");
	}
	public static void testHSSServiceGetAllStreets(String lang) throws RemoteException {
		outln("  Method: HumansSearchService -> getAllStreets");
		outln("    Result:");
		for(int i=0; i< hSSProxy.getAllStreets(lang).length; i++){
			Street iData = hSSProxy.getAllStreets(lang)[i];
			outln("      Record data number: "+(i+1));
			outln("      id = " + iData.getId());
			outln("      name rus = " + iData.getName());
			outln("      Country");
			outln("      	id = " + iData.getCity().getDistrict().getRegion().getCountry().getId());
			outln("      	name rus = " + iData.getCity().getDistrict().getRegion().getCountry().getName());
			outln("      Region");
			outln("      	id = " + iData.getCity().getDistrict().getRegion().getId());
			outln("      	name rus = " + iData.getCity().getDistrict().getRegion().getName());
			outln("      District");
			outln("      	id = " + iData.getCity().getDistrict().getId());
			outln("      	name rus = " + iData.getCity().getDistrict().getName());
			outln("      City");
			outln("      	id = " + iData.getCity().getId());
			outln("      	name rus = " + iData.getCity().getName());
			outln("----------------------------------");
		}
		outln("-------------------------------------------------------------------------");
	}
	
	public static void testHSSServiceGetAllDistricts(String lang) throws RemoteException {
		outln("  Method: HumansSearchService -> getAllDistricts");
		outln("    Result:");
		for(int i=0; i< hSSProxy.getAllDistricts(lang).length; i++){
			District iData = hSSProxy.getAllDistricts(lang)[i];
			outln("      Record data number: "+(i+1));
			outln("      id = " + iData.getId());
			outln("      name rus = " + iData.getName());
			outln("      Country");
			outln("      	id = " + iData.getRegion().getCountry().getId());
			outln("      	name rus = " + iData.getRegion().getCountry().getName());
			outln("      Region");
			outln("      	id = " + iData.getRegion().getId());
			outln("      	name rus = " + iData.getRegion().getName());
			outln("----------------------------------");
		}
		outln("-------------------------------------------------------------------------");
	}
	public static void testHSSServiceGetAllCountries( String lang) throws RemoteException {
		outln("  Method: HumansSearchService -> getAllCountries");
		outln("    Result:");
		for(int i=0; i< hSSProxy.getAllCountries(lang).length; i++){
			Country iData = hSSProxy.getAllCountries(lang)[i];
			outln("      Record data number: "+(i+1));
			outln("      id = " + iData.getId());
			outln("      name rus = " + iData.getName());
			outln("----------------------------------");
		}
		outln("-------------------------------------------------------------------------");
	}
	public static void testHSSServiceGetFullData(Long id, String lang) throws RemoteException {
		outln("  Method: HumansSearchService -> getFullData");
		outln("    Parameters:");
		outln("      iin = " + id);
		outln("      lang = " + lang);
		outln("    Result:");
		HumanFullData iFullData = hSSProxy.getFullData(id, lang);
		outln("      First record data: ");
		outln("        id = " + iFullData.getId());
		outln("        First Name = " + iFullData.getFirstName());
		outln("        Last Name = " + iFullData.getLastName());
		outln("        MiddleName = " + iFullData.getMiddleName());
		outln("        gender = " + iFullData.getGender());
		outln("        IIN = " + iFullData.getIin());
		outln("        Birth Date = "  + DateFormat.getDateInstance().format(iFullData.getBirthDate().getTime()));
		
		outln("        Nationality:");
		outln("          id = " + iFullData.getNationality().getIdNat());
		outln("          maleName = " + iFullData.getNationality().getMaleName());
		outln("          femaleName = " + iFullData.getNationality().getFemaleName());
			
		outln("        Country (getOrigin):");
		outln("          id = " + iFullData.getOrigin().getId());
		outln("          name = " + iFullData.getOrigin().getName());
		
		outln("        Country (getCitizen):");
		outln("          id = " + iFullData.getCitizenship().getId());
		outln("          name = " + iFullData.getCitizenship().getName());
		
		outln("        VisitPurpose (camePurpose):");
		outln("          id = " + iFullData.getCamePurpose().getId());
		outln("          name = " + iFullData.getCamePurpose().getName());
		
		
		outln("        VisitPurpose (gonePurpose):");
		outln("          id = " + iFullData.getGonePurpose().getId());
		outln("          name = " + iFullData.getGonePurpose().getName());
		
		outln("        VisitReason:");
		outln("          id = " + iFullData.getGoneReason().getId());
		outln("          name = " + iFullData.getGoneReason().getName());
		
		outln("        Document:");
		outln("          id = " + iFullData.getIdDocument()[0].getId());
		outln("        	 DocumentType:");
		outln("          	id = " + iFullData.getIdDocument()[0].getType().getId());
		outln("             point = " + iFullData.getIdDocument()[0].getType().getPoint());
		outln("          serial = " + iFullData.getIdDocument()[0].getSerial());
		outln("          number = " + iFullData.getIdDocument()[0].getNumber());
		outln("          authority = " + iFullData.getIdDocument()[0].getAuthority());
		outln("          creationDate = "  + DateFormat.getDateInstance().format(iFullData.getIdDocument()[0].getCreationDate().getTime()));
		outln("          expirationDate = "  + DateFormat.getDateInstance().format(iFullData.getIdDocument()[0].getExpirationDate().getTime()));
		outln("          comments = " + iFullData.getIdDocument()[0].getComments());
//		
		outln("          rnn = " + iFullData.getRnn());
		
		outln("        Relation:");
		outln("          id = " + iFullData.getRelationship().getId());
		outln("          name = " + iFullData.getRelationship().getName());
		
		outln("        RegType:");
		outln("          id = " + iFullData.getRegType().getId());
		outln("          name = " + iFullData.getRegType().getName());
		
		outln("        tmpRegNumber = " + iFullData.getTmpRegNumber());
		outln("        regStartDate = "  + DateFormat.getDateInstance().format(iFullData.getRegStartDate().getTime()));
		outln("        regEndDate = "  + DateFormat.getDateInstance().format(iFullData.getRegEndDate().getTime()));
		
		
		outln("        Address(address):");
		outln("        		Region:");
		outln("          		id = " + iFullData.getAddress().getRegion().getId());
		outln("          		name = " + iFullData.getAddress().getRegion().getName());
		outln("        		District:");
		outln("          		id = " + iFullData.getAddress().getDistrict().getId());
		outln("          		name = " + iFullData.getAddress().getDistrict().getName());
		outln("        		City:");
		outln("          		id = " + iFullData.getAddress().getCity().getId());
		outln("          		name = " + iFullData.getAddress().getCity().getName());
		outln("        		Street:");
		outln("          		id = " + iFullData.getAddress().getStreet().getId());
		outln("          		name = " + iFullData.getAddress().getStreet().getName());
		
		outln("        Address(cameFrom):");
		outln("        		Region:");
		outln("          		id = " + iFullData.getCameFrom().getRegion().getId());
		outln("          		name = " + iFullData.getCameFrom().getRegion().getName());
		outln("        		District:");
		outln("          		id = " + iFullData.getCameFrom().getDistrict().getId());
		outln("          		name = " + iFullData.getCameFrom().getDistrict().getName());
		outln("        		City:");
		outln("          		id = " + iFullData.getCameFrom().getCity().getId());
		outln("          		name = " + iFullData.getCameFrom().getCity().getName());
		outln("        		Street:");
		outln("          		id = " + iFullData.getCameFrom().getStreet().getId());
		outln("          		name = " + iFullData.getCameFrom().getStreet().getName());
		
		outln("        Address(goneTo):");
		outln("        		Region:");
		outln("          		id = " + iFullData.getGoneTo().getRegion().getId());
		outln("          		name = " + iFullData.getGoneTo().getRegion().getName());
		outln("        		District:");
		outln("          		id = " + iFullData.getGoneTo().getDistrict().getId());
		outln("          		name = " + iFullData.getGoneTo().getDistrict().getName());
		outln("        		City:");
		outln("          		id = " + iFullData.getGoneTo().getCity().getId());
		outln("          		name = " + iFullData.getGoneTo().getCity().getName());
		outln("        		Street:");
		outln("          		id = " + iFullData.getGoneTo().getStreet().getId());
		outln("          		name = " + iFullData.getGoneTo().getStreet().getName());
		outln("-------------------------------------------------------------------------");
	}
	public static void testHSSServiceGetCitizenByIIN(String iin, String lang) throws RemoteException {
		outln("  Method: HumansSearchService -> getCitizenByIIN");
		outln("    Parameters:");
		outln("      iin = " + iin);
		outln("      lang = " + lang);
		outln("    Result:");
		HumansSearchResult iData = hSSProxy.getCitizenByIIN(iin, lang);
		outln("      Records found: " + iData.getTotalFound());
		outln("      First record data: ");
		HumanShortData iShortData = iData.getShortData()[0];
		outln("        id = " + iShortData.getId());
		outln("        First Name = " + iShortData.getFirstName());
		outln("        Last Name = " + iShortData.getLastName());
		outln("        MiddleName = " + iShortData.getMiddleName());
		outln("        gender = " + iShortData.getGender());
		outln("        IIN = " + iShortData.getIin());
		outln("        Birth Date = "  + DateFormat.getDateInstance().format(iShortData.getBirthDate().getTime()));;
		
		outln("        Nationality:");
		outln("          id = " + iShortData.getNationality().getIdNat());
		outln("          maleName = " + iShortData.getNationality().getMaleName());
		outln("          femaleName = " + iShortData.getNationality().getFemaleName());
		
		outln("        Country (getCitizen):");
		outln("          id = " + iShortData.getCitizenship().getId());
		outln("          name = " + iShortData.getCitizenship().getName());
		
		outln("        Document:");
		outln("          id = " + iShortData.getIdDocument()[0].getId());
		outln("        	 DocumentType:");
		outln("          	id = " + iShortData.getIdDocument()[0].getType().getId());
		outln("             point = " + iShortData.getIdDocument()[0].getType().getPoint());
		outln("          serial = " + iShortData.getIdDocument()[0].getSerial());
		outln("          number = " + iShortData.getIdDocument()[0].getNumber());
		outln("          authority = " + iShortData.getIdDocument()[0].getAuthority());
		outln("          creationDate = "  + DateFormat.getDateInstance().format(iShortData.getIdDocument()[0].getCreationDate().getTime()));
		outln("          expirationDate = "  + DateFormat.getDateInstance().format(iShortData.getIdDocument()[0].getExpirationDate().getTime()));
		outln("          comments = " + iShortData.getIdDocument()[0].getComments());
	
		outln("-------------------------------------------------------------------------");
	}
	
	public static void testHSSServiceGetHumanByDoc(String docNumber, int pageNum, int resultsOnPage, String lang) throws RemoteException {
		outln("  Method: HumansSearchService -> getHumanByDoc");
		outln("    Parameters:");
		outln("      docNumber = " + docNumber);
		outln("      pageNum = " + pageNum);
		outln("      resultsOnPage = " + resultsOnPage);
		outln("      lang = " + lang);
		outln("    Result:");
		HumansSearchResult iData = hSSProxy.getHumanByDoc(docNumber, pageNum, resultsOnPage, lang);
		outln("      Records found: " + iData.getTotalFound());
		outln("      First record data: ");
		HumanShortData iShortData = iData.getShortData()[0];
		outln("        id = " + iShortData.getId());
		outln("        First Name = " + iShortData.getFirstName());
		outln("        Last Name = " + iShortData.getLastName());
		outln("        MiddleName = " + iShortData.getMiddleName());
		outln("        gender = " + iShortData.getGender());
		outln("        IIN = " + iShortData.getIin());
		outln("        Birth Date = "  + DateFormat.getDateInstance().format(iShortData.getBirthDate().getTime()));;
		
		outln("        Nationality:");
		outln("          id = " + iShortData.getNationality().getIdNat());
		outln("          maleName = " + iShortData.getNationality().getMaleName());
		outln("          femaleName = " + iShortData.getNationality().getFemaleName());
	
		
		outln("        Country:");
		outln("          id = " + iShortData.getCitizenship().getId());
		outln("          name = " + iShortData.getCitizenship().getName());
		
		outln("        Document:");
		outln("          id = " + iShortData.getIdDocument()[0].getId());
		outln("        	 DocumentType:");
		outln("          	id = " + iShortData.getIdDocument()[0].getType().getId());
		outln("             point = " + iShortData.getIdDocument()[0].getType().getPoint());
		outln("          serial = " + iShortData.getIdDocument()[0].getSerial());
		outln("          number = " + iShortData.getIdDocument()[0].getNumber());
		outln("          authority = " + iShortData.getIdDocument()[0].getAuthority());
		outln("          creationDate = "  + DateFormat.getDateInstance().format(iShortData.getIdDocument()[0].getCreationDate().getTime()));
		outln("          expirationDate = "  + DateFormat.getDateInstance().format(iShortData.getIdDocument()[0].getExpirationDate().getTime()));
		outln("          comments = " + iShortData.getIdDocument()[0].getComments());
		outln("-------------------------------------------------------------------------");
	}
	
	public static void testHSSServiceGetHumanByAddr(Street street, String house, String flat,  int pageNum, int resultsOnPage, String lang) throws RemoteException {
		HumansSearchResult iData = hSSProxy.getHumanByAddr(street, house, flat, pageNum, resultsOnPage, lang);
		System.out.println();
	}
	
	public static void testHSSServiceGetHumanByFIO(String firstName, String lastName, String middleName, int pageNum, int resultsOnPage, String lang) throws RemoteException {
		HumansSearchResult iData = hSSProxy.getHumanByFIO(firstName, lastName, middleName, pageNum, resultsOnPage, lang);
		System.out.println();
	}

	public static void out(Object o) {
		String fullFileName = "";
		if (logFileName.length() > 0) {
			fullFileName = logFilePath + logFileDate + "_" + logFileName;
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(fullFileName, true);
		} catch (FileNotFoundException fe) {
			os = System.out;
		} finally {
			try {
				os.write(o.toString().getBytes());
				if (os instanceof FileOutputStream) os.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public static void outln(Object o) {
		String fullFileName = "";
		if (logFileName.length() > 0) {
			fullFileName = logFilePath + logFileDate + "_" + logFileName;
		}
		OutputStream os = null;
		try {
			os = new FileOutputStream(fullFileName, true);
		} catch (FileNotFoundException fe) {
			os = System.out;
		} finally {
			try {
				os.write(o.toString().getBytes());
				os.write(new byte[] {13, 10});
				if (os instanceof FileOutputStream) os.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
}