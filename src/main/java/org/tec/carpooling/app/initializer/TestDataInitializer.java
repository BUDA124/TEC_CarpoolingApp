package org.tec.carpooling.app.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.tec.carpooling.bl.services.AuditLogService;
import org.tec.carpooling.common.constants.AppConstants;

// Entities
import org.tec.carpooling.common.utils.HashingUtil;
import org.tec.carpooling.da.entities.*;

// Repositories
import org.tec.carpooling.da.repositories.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

@Component
@Order(2)
public class TestDataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(TestDataInitializer.class);

    // --- Injected Services & Repositories ---
    @Autowired private AuditLogService auditService;

    // Location & Institution Repositories
    @Autowired private CountryRepository countryRepository;
    @Autowired private ProvinceRepository provinceRepository;
    @Autowired private CantonRepository cantonRepository;
    @Autowired private DistrictRepository districtRepository;
    @Autowired private CoordinateLocationRepository coordinateLocationRepository;
    @Autowired private InstitutionRepository institutionRepository;
    @Autowired private ContactPhoneNumberRepository contactPhoneNumberRepository;
    @Autowired private ContactEmailRepository contactEmailRepository;

    // Person & User Repositories
    @Autowired private GenderRepository genderRepository;
    @Autowired private PersonRepository personRepository;
    @Autowired private PhoneNumberRepository phoneNumberRepository; // Person's phone
    @Autowired private EmailRepository emailRepository; // Person's email
    @Autowired private TypeOfCredentialRepository typeOfCredentialRepository;
    @Autowired private CredentialRepository credentialRepository;
    @Autowired private UserStatusRepository userStatusRepository;
    @Autowired private PersonalUserRepository personalUserRepository;
    @Autowired private InstitutionalEmailRepository institutionalEmailRepository;

    // Role Repositories
    @Autowired private AdministratorRepository administratorRepository;
    @Autowired private AccessStatusRepository accessStatusRepository;
    @Autowired private DriverRepository driverRepository;
    @Autowired private PassengerRepository passengerRepository;

    // Vehicle & Trip Repositories
    @Autowired private VehicleRepository vehicleRepository;
    @Autowired private StopRepository stopRepository;
    @Autowired private PaymentMethodRepository paymentMethodRepository;
    @Autowired private PriceStatusRepository priceStatusRepository;
    @Autowired private TripStatusRepository tripStatusRepository;
    @Autowired private TripRepository tripRepository;

    // Reporting & Logging Repositories
    @Autowired private DailyReportRepository dailyReportRepository;
    @Autowired private ParameterRepository parameterRepository;
    @Autowired private LogBookRepository logBookRepository;
    @Autowired private EntityModifiedRepository entityModifiedRepository;
    @Autowired private AttributeModifiedRepository attributeModifiedRepository;

    // N:N Relationship Table Repositories
    @Autowired private CredentialHasTypeOfCredentialRepository credentialHasTypeOfCredentialRepository;
    @Autowired private AdminManageInstitutionRepository adminManageInstitutionRepository;
    @Autowired private AdminReceiveDailyReportRepository adminReceiveDailyReportRepository;
    @Autowired private PassengerQueryTripRepository passengerQueryTripRepository;
    @Autowired private PassengerJoinTripRepository passengerJoinTripRepository;
    @Autowired private TripHasTripStatusRepository tripHasTripStatusRepository;
    @Autowired private StopHasCoordinateLocationRepository stopHasCoordinateLocationRepository;
    @Autowired private TripHasStopHasPaymentMethodRepository tripHasStopHasPaymentMethodRepository;
    @Autowired private TripHasStopRepository tripHasStopRepository;
    @Autowired private TripReportDailyReportRepository tripReportDailyReportRepository;
    @Autowired private LogBookHasEntityModifiedRepository logBookHasEntityModifiedRepository;
    @Autowired private AttrModHasEntModRepository attrModHasEntModRepository;


    // Data Holders
    private final Map<String, CountryEntity> countries = new HashMap<>();
    private final Map<String, ProvinceEntity> provinces = new HashMap<>();
    private final Map<String, CantonEntity> cantons = new HashMap<>();
    private final Map<String, DistrictEntity> districts = new HashMap<>();
    private final Map<String, CoordinateLocationEntity> coordinateLocations = new HashMap<>();
    private final Map<String, InstitutionEntity> institutions = new HashMap<>();
    private final Map<String, GenderEntity> genders = new HashMap<>();
    private final Map<String, PersonEntity> persons = new HashMap<>();
    private final Map<String, TypeOfCredentialEntity> typeOfCredentials = new HashMap<>();
    private final Map<String, CredentialEntity> credentials = new HashMap<>();
    private final Map<String, UserStatusEntity> userStatuses = new HashMap<>();
    private final Map<String, PersonalUserEntity> personalUsers = new HashMap<>();
    private final Map<String, AdministratorEntity> administrators = new HashMap<>();
    private final Map<String, DriverEntity> drivers = new HashMap<>();
    private final Map<String, VehicleEntity> vehicles = new HashMap<>();
    private final Map<String, StopEntity> stops = new HashMap<>();
    private final Map<String, PaymentMethodEntity> paymentMethods = new HashMap<>();
    private final Map<String, PriceStatusEntity> priceStatuses = new HashMap<>();
    private final Map<String, TripStatusEntity> tripStatuses = new HashMap<>();
    private final Map<Long, TripEntity> trips = new HashMap<>();
    private final Map<Long, DailyReportEntity> dailyReports = new HashMap<>();
    private final Map<Long, LogBookEntity> logBooks = new HashMap<>();
    private final Map<Long, EntityModifiedEntity> entityModifiedRecords = new HashMap<>();
    private final Map<Long, AttributeModifiedEntity> attributeModifiedRecords = new HashMap<>();


    public TestDataInitializer() {}

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        if (institutionRepository.findByEmailDomain("ucr.ac.cr").isPresent() &&
            institutionRepository.findByEmailDomain("tec.ac.cr").isPresent() &&
            personalUserRepository.findByUsername("carlos.rodriguez").isPresent()) {
            log.info("Test data from TestDataInitializer seems to be already populated. Skipping initialization.");
            return;
        }

        loadStaticData();
        createGeographicDataExtensions();
        createCoordinateLocations();
        createInstitutionsAndContacts();
        createPersonsAndRelatedInfo();
        createSpecificCredentialTypes();
        createCredentials();
        createUsers();
        createInstitutionalEmails();
        createAdministratorsAndAccess();
        createDriversAndVehicles();
        createPassengers();
        createStops();
        createSpecificPriceStatuses();
        createTrips();
        createDailyReports();
        createSpecificParameters();
        createLogBookEntries();
        createAuditingDetails();

        // N:N Relationships
        createCredentialTypeLinks();
        createAdminInstitutionLinks();
        createPassengerTripLinks();
        createTripStatusLinks();
        createStopCoordinateLinks();
        createTripStopLinks();
        createTripStopPaymentMethodLinks();
        createTripDailyReportLinks();
        createAdminDailyReportLinks();
        createLogBookEntityModifiedLinks();
        createAttributeModifiedLogBookLinks();
    }
    
    private AuditLogEntity newAuditLog() {
        return auditService.createInitialAuditLog(AppConstants.SYSTEM_USER);
    }

    // --- Helper methods to fetch data created by StaticDataInitializer ---
    private CountryEntity fetchCountryOrFail(String name) {
        return countryRepository.findByName(name)
            .orElseThrow(() -> new IllegalStateException("Country '" + name + "' not found. Ensure StaticDataInitializer ran."));
    }

    private ProvinceEntity fetchProvinceOrFail(String name, CountryEntity country) {
        return provinceRepository.findByNameAndCountry(name, country)
            .orElseThrow(() -> new IllegalStateException("Province '" + name + "' in country '" + country.getName() + "' not found."));
    }

    private CantonEntity fetchCantonOrFail(String name, ProvinceEntity province) {
        return cantonRepository.findByNameAndProvince(name, province)
            .orElseThrow(() -> new IllegalStateException("Canton '" + name + "' in province '" + province.getName() + "' not found."));
    }
    
    private DistrictEntity fetchDistrictOrFail(String name, CantonEntity canton) {
        return districtRepository.findByNameAndCanton(name, canton)
            .orElseThrow(() -> new IllegalStateException("District '" + name + "' in canton '" + canton.getName() + "' not found."));
    }

    private GenderEntity fetchGenderOrFail(String name) {
        return genderRepository.findByGenderName(name)
            .orElseThrow(() -> new IllegalStateException("Gender '" + name + "' not found. Ensure StaticDataInitializer ran."));
    }

    private UserStatusEntity fetchUserStatusOrFail(String name) {
        return userStatusRepository.findByStatus(name)
            .orElseThrow(() -> new IllegalStateException("UserStatus '" + name + "' not found. Ensure StaticDataInitializer ran."));
    }

    private TypeOfCredentialEntity fetchTypeOfCredentialOrFail(String name) {
        return typeOfCredentialRepository.findByType(name)
            .orElseThrow(() -> new IllegalStateException("TypeOfCredential '" + name + "' not found. Ensure StaticDataInitializer ran."));
    }
    
    private PaymentMethodEntity fetchPaymentMethodOrFail(String name) {
        return paymentMethodRepository.findByMethod(name)
            .orElseThrow(() -> new IllegalStateException("PaymentMethod '" + name + "' not found. Ensure StaticDataInitializer ran."));
    }

    private PriceStatusEntity fetchPriceStatusOrFail(String name) {
        return priceStatusRepository.findByStatus(name)
            .orElseThrow(() -> new IllegalStateException("PriceStatus '" + name + "' not found. Ensure StaticDataInitializer ran."));
    }

    private TripStatusEntity fetchTripStatusOrFail(String name) {
        return tripStatusRepository.findByStatus(name)
            .orElseThrow(() -> new IllegalStateException("TripStatus '" + name + "' not found. Ensure StaticDataInitializer ran."));
    }

    private void loadStaticData() {
        // Geographic
        countries.put(AppConstants.COUNTRY_COSTA_RICA, fetchCountryOrFail(AppConstants.COUNTRY_COSTA_RICA));
        provinces.put(AppConstants.PROVINCE_SAN_JOSE, fetchProvinceOrFail(AppConstants.PROVINCE_SAN_JOSE, countries.get(AppConstants.COUNTRY_COSTA_RICA)));
        cantons.put(AppConstants.CANTON_SAN_JOSE_CENTRAL, fetchCantonOrFail(AppConstants.CANTON_SAN_JOSE_CENTRAL, provinces.get(AppConstants.PROVINCE_SAN_JOSE)));
        districts.put(AppConstants.DISTRICT_CARMEN_SAN_JOSE, fetchDistrictOrFail(AppConstants.DISTRICT_CARMEN_SAN_JOSE, cantons.get(AppConstants.CANTON_SAN_JOSE_CENTRAL)));

        // Genders
        genders.put(AppConstants.GENDER_MALE, fetchGenderOrFail(AppConstants.GENDER_MALE));
        genders.put(AppConstants.GENDER_FEMALE, fetchGenderOrFail(AppConstants.GENDER_FEMALE));
        genders.put(AppConstants.GENDER_NON_BINARY, fetchGenderOrFail(AppConstants.GENDER_NON_BINARY));
        genders.put(AppConstants.GENDER_PREFER_NOT_TO_SAY, fetchGenderOrFail(AppConstants.GENDER_PREFER_NOT_TO_SAY));

        // User Statuses
        userStatuses.put(AppConstants.USER_STATUS_ACTIVE, fetchUserStatusOrFail(AppConstants.USER_STATUS_ACTIVE));
        userStatuses.put(AppConstants.USER_STATUS_INACTIVE, fetchUserStatusOrFail(AppConstants.USER_STATUS_INACTIVE));
        userStatuses.put(AppConstants.USER_STATUS_BANNED, fetchUserStatusOrFail(AppConstants.USER_STATUS_BANNED));
        userStatuses.put(AppConstants.USER_STATUS_PENDING_VERIFICATION, fetchUserStatusOrFail(AppConstants.USER_STATUS_PENDING_VERIFICATION));

        // Type Of Credentials (Static ones)
        typeOfCredentials.put(AppConstants.CREDENTIAL_TYPE_NATIONAL_ID, fetchTypeOfCredentialOrFail(AppConstants.CREDENTIAL_TYPE_NATIONAL_ID));
        typeOfCredentials.put(AppConstants.CREDENTIAL_TYPE_DIMEX, fetchTypeOfCredentialOrFail(AppConstants.CREDENTIAL_TYPE_DIMEX));
        // ... add NITE, PASSPORT if needed by tests, or rely on specific creation later

        // Payment Methods
        paymentMethods.put(AppConstants.PAYMENT_METHOD_CASH, fetchPaymentMethodOrFail(AppConstants.PAYMENT_METHOD_CASH));
        paymentMethods.put(AppConstants.PAYMENT_METHOD_SINPE, fetchPaymentMethodOrFail(AppConstants.PAYMENT_METHOD_SINPE));
        paymentMethods.put(AppConstants.PAYMENT_METHOD_DEBIT_CARD, fetchPaymentMethodOrFail(AppConstants.PAYMENT_METHOD_DEBIT_CARD));
        paymentMethods.put(AppConstants.PAYMENT_METHOD_CREDIT_CARD, fetchPaymentMethodOrFail(AppConstants.PAYMENT_METHOD_CREDIT_CARD));
        // ... add others from AppConstants if tests use them

        // Price Statuses
        priceStatuses.put(AppConstants.PRICE_STATUS_FREE, fetchPriceStatusOrFail(AppConstants.PRICE_STATUS_FREE));
        // ... add others from AppConstants if tests use them

        // Trip Statuses
        tripStatuses.put(AppConstants.TRIP_STATUS_SCHEDULED, fetchTripStatusOrFail(AppConstants.TRIP_STATUS_SCHEDULED));
        tripStatuses.put(AppConstants.TRIP_STATUS_IN_PROGRESS, fetchTripStatusOrFail(AppConstants.TRIP_STATUS_IN_PROGRESS));
        tripStatuses.put(AppConstants.TRIP_STATUS_COMPLETED, fetchTripStatusOrFail(AppConstants.TRIP_STATUS_COMPLETED));
        tripStatuses.put(AppConstants.TRIP_STATUS_CANCELLED, fetchTripStatusOrFail(AppConstants.TRIP_STATUS_CANCELLED));
    }

    private void createGeographicDataExtensions() {
        CountryEntity costaRica = countries.get(AppConstants.COUNTRY_COSTA_RICA); // Already fetched

        ProvinceEntity cartagoProv = provinceRepository.findByNameAndCountry("Cartago", costaRica).orElseGet(() -> {
            ProvinceEntity p = new ProvinceEntity("Cartago", costaRica, newAuditLog());
            return provinceRepository.save(p);
        });
        provinces.put("Cartago", cartagoProv);

        ProvinceEntity sanJoseProv = provinces.get(AppConstants.PROVINCE_SAN_JOSE);
        CantonEntity montesDeOca = cantonRepository.findByNameAndProvince("Montes de Oca", sanJoseProv).orElseGet(() -> {
            CantonEntity cn = new CantonEntity("Montes de Oca", sanJoseProv, newAuditLog());
            return cantonRepository.save(cn);
        });
        cantons.put("Montes de Oca", montesDeOca);

        CantonEntity cartagoCanton = cantonRepository.findByNameAndProvince("Cartago", cartagoProv).orElseGet(() -> {
            CantonEntity cn = new CantonEntity("Cartago", cartagoProv, newAuditLog());
            return cantonRepository.save(cn);
        });
        cantons.put("Cartago Canton", cartagoCanton);

        DistrictEntity sanPedro = districtRepository.findByNameAndCanton("San Pedro", montesDeOca).orElseGet(() -> {
            DistrictEntity d = new DistrictEntity("San Pedro", montesDeOca, newAuditLog());
            return districtRepository.save(d);
        });
        districts.put("San Pedro", sanPedro);

        DistrictEntity oriental = districtRepository.findByNameAndCanton("Oriental", cartagoCanton).orElseGet(() -> {
            DistrictEntity d = new DistrictEntity("Oriental", cartagoCanton, newAuditLog());
            return districtRepository.save(d);
        });
        districts.put("Oriental", oriental);
    }

    private void createCoordinateLocations() {
        log.info("Creating coordinate locations...");
        CoordinateLocationEntity ucrCoords = coordinateLocationRepository.findByYCoordinateAndXCoordinate((float) 9.9370, (float) -84.0528).orElseGet(() -> {
            CoordinateLocationEntity cl = new CoordinateLocationEntity((float) 9.9370, (float) -84.0528, newAuditLog());
            return coordinateLocationRepository.save(cl);
        });
        coordinateLocations.put("UCR", ucrCoords);

        CoordinateLocationEntity tecCoords = coordinateLocationRepository.findByYCoordinateAndXCoordinate((float) 9.8626, (float) -83.9145).orElseGet(() -> {
            CoordinateLocationEntity cl = new CoordinateLocationEntity((float) 9.8626, (float) -83.9145, newAuditLog());
            return coordinateLocationRepository.save(cl);
        });
        coordinateLocations.put("TEC", tecCoords);
    }

    private void createInstitutionsAndContacts() {
        log.info("Creating institutions and contacts...");
        InstitutionEntity ucr = institutionRepository.findByEmailDomain("ucr.ac.cr").orElseGet(() -> {
            InstitutionEntity i = new InstitutionEntity("UCR", "ucr.ac.cr", "https://www.ucr.ac.cr", newAuditLog());
            return institutionRepository.save(i);
        });
        institutions.put("UCR", ucr);

        InstitutionEntity tec = institutionRepository.findByEmailDomain("tec.ac.cr").orElseGet(() -> {
            InstitutionEntity i = new InstitutionEntity("TEC", "tec.ac.cr", "https://www.tec.ac.cr", newAuditLog());
            return institutionRepository.save(i);
        });
        institutions.put("TEC", tec);

        // Contacts - Assuming phone numbers per institution are unique enough
        contactPhoneNumberRepository.findByPhoneNumberAndInstitution("2511-2000", ucr).orElseGet(() ->
            contactPhoneNumberRepository.save(new ContactPhoneNumberEntity("2511-2000", ucr, newAuditLog()))
        );
        contactPhoneNumberRepository.findByPhoneNumberAndInstitution("2550-2000", tec).orElseGet(() ->
            contactPhoneNumberRepository.save(new ContactPhoneNumberEntity("2550-2000", tec, newAuditLog()))
        );
        contactEmailRepository.findByAddress("info@ucr.ac.cr").orElseGet(() ->
            contactEmailRepository.save(new ContactEmailEntity("info@ucr.ac.cr", ucr, newAuditLog()))
        );
        contactEmailRepository.findByAddress("info@tec.ac.cr").orElseGet(() ->
            contactEmailRepository.save(new ContactEmailEntity("info@tec.ac.cr", tec, newAuditLog()))
        );
    }
    
    // Genders are loaded in loadStaticData. If Populate.txt had truly distinct genders not in AppConstants,
    // they would be created there or here. We assume "No Binary" from Populate.txt is distinct.

    private void createPersonsAndRelatedInfo() {
        log.info("Creating persons and their contact info...");
        // Carlos
        PersonEntity carlos = personRepository.findByFirstNameAndFirstSurname("Carlos", "Rodríguez")
            .orElseGet(() -> {
                PersonEntity p = new PersonEntity("Carlos", "Alberto", "Rodríguez", null, LocalDate.of(1995, Month.MARCH, 15),
                                                "Costarricense", institutions.get("UCR"), genders.get(AppConstants.GENDER_MALE), newAuditLog());
                return personRepository.save(p);
            });
        persons.put("Carlos Rodríguez", carlos);
        phoneNumberRepository.findByPhoneNumberAndPerson("8888-1234", carlos).orElseGet(() ->
            phoneNumberRepository.save(new PhoneNumberEntity("8888-1234", carlos, newAuditLog())));
        emailRepository.findByEmailAddressAndPerson("carlos.rodriguez@gmail.com", carlos).orElseGet(() ->
            emailRepository.save(new EmailEntity("carlos.rodriguez@gmail.com", carlos, newAuditLog())));

        // María
        PersonEntity maria = personRepository.findByFirstNameAndFirstSurnameAndSecondSurname("María", "Fernández", "Castro")
            .orElseGet(() -> {
                PersonEntity p = new PersonEntity("María", null, "Fernández", "Castro", LocalDate.of(1997, Month.JULY, 22),
                                                "Costarricense", institutions.get("UCR"), genders.get(AppConstants.GENDER_FEMALE), newAuditLog());
                return personRepository.save(p);
            });
        persons.put("María Fernández", maria);
        phoneNumberRepository.findByPhoneNumberAndPerson("8777-5678", maria).orElseGet(() ->
            phoneNumberRepository.save(new PhoneNumberEntity("8777-5678", maria, newAuditLog())));
        emailRepository.findByEmailAddressAndPerson("maria.fernandez@gmail.com", maria).orElseGet(() ->
            emailRepository.save(new EmailEntity("maria.fernandez@gmail.com", maria, newAuditLog())));

        // José
        PersonEntity jose = personRepository.findByFirstNameAndFirstSurnameAndSecondSurname("José", "Vargas", "Solano")
            .orElseGet(() -> {
                PersonEntity p = new PersonEntity("José", "Luis", "Vargas", "Solano", LocalDate.of(1996, Month.NOVEMBER, 8),
                                                "Costarricense", institutions.get("TEC"), genders.get(AppConstants.GENDER_MALE), newAuditLog());
                return personRepository.save(p);
            });
        persons.put("José Vargas", jose);
        phoneNumberRepository.findByPhoneNumberAndPerson("8666-9012", jose).orElseGet(() ->
            phoneNumberRepository.save(new PhoneNumberEntity("8666-9012", jose, newAuditLog())));
        emailRepository.findByEmailAddressAndPerson("jose.vargas@gmail.com", jose).orElseGet(() ->
            emailRepository.save(new EmailEntity("jose.vargas@gmail.com", jose, newAuditLog())));
        
        // Ana
        PersonEntity ana = personRepository.findByFirstNameAndFirstSurnameAndSecondSurname("Ana", "Jiménez", null)
            .orElseGet(() -> {
                PersonEntity p = new PersonEntity("Ana", "Patricia", "Jiménez", null, LocalDate.of(1994, Month.MAY, 30),
                                                "Costarricense", institutions.get("TEC"), genders.get(AppConstants.GENDER_FEMALE), newAuditLog());
                return personRepository.save(p);
            });
        persons.put("Ana Jiménez", ana);
        phoneNumberRepository.findByPhoneNumberAndPerson("8555-3456", ana).orElseGet(() ->
            phoneNumberRepository.save(new PhoneNumberEntity("8555-3456", ana, newAuditLog())));
        emailRepository.findByEmailAddressAndPerson("ana.jimenez@gmail.com", ana).orElseGet(() ->
            emailRepository.save(new EmailEntity("ana.jimenez@gmail.com", ana, newAuditLog())));
    }

    private void createSpecificCredentialTypes() {
        TypeOfCredentialEntity credential = typeOfCredentialRepository.findByType(AppConstants.CREDENTIAL_TYPE_NATIONAL_ID).orElseGet(() -> {
            TypeOfCredentialEntity tc = new TypeOfCredentialEntity(AppConstants.CREDENTIAL_TYPE_NATIONAL_ID, newAuditLog());
            return typeOfCredentialRepository.save(tc);
        });
        typeOfCredentials.put(AppConstants.CREDENTIAL_TYPE_NATIONAL_ID, credential);

        TypeOfCredentialEntity carnet = typeOfCredentialRepository.findByType(AppConstants.CREDENTIAL_TYPE_NITE).orElseGet(() -> {
            TypeOfCredentialEntity tc = new TypeOfCredentialEntity(AppConstants.CREDENTIAL_TYPE_NITE, newAuditLog());
            return typeOfCredentialRepository.save(tc);
        });
        typeOfCredentials.put(AppConstants.CREDENTIAL_TYPE_NITE, carnet);
    }

    private void createCredentials() {
        // Carlos's Credentials
        CredentialEntity cred1 = credentialRepository.findByNumberOfCredential("LIC-123456789").orElseGet(() ->
            credentialRepository.save(new CredentialEntity(1, "LIC-123456789", persons.get("Carlos Rodríguez"), newAuditLog()))
        );
        credentials.put("LIC-123456789", cred1);
        
        CredentialEntity cred2 = credentialRepository.findByNumberOfCredential("2022098765").orElseGet(() ->
            credentialRepository.save(new CredentialEntity(1, "2022098765", persons.get("Carlos Rodríguez"), newAuditLog()))
        );
        credentials.put("2022098765", cred2);

        // José's Credentials
        CredentialEntity cred3 = credentialRepository.findByNumberOfCredential("LIC-987654321").orElseGet(() ->
            credentialRepository.save(new CredentialEntity(1, "LIC-987654321", persons.get("José Vargas"), newAuditLog()))
        );
        credentials.put("LIC-987654321", cred3);

        CredentialEntity cred4 = credentialRepository.findByNumberOfCredential("2023345678").orElseGet(() ->
            credentialRepository.save(new CredentialEntity(1, "2023345678", persons.get("José Vargas"), newAuditLog()))
        );
        credentials.put("2023345678", cred4);
    }
    
    // UserStatuses are loaded in loadStaticData. "Suspendido" created there if specific.

    private void createUsers() {
        log.info("Creating personal users...");
        PersonalUserEntity carlosUser = personalUserRepository.findByUsername("carlos.rodriguez").orElseGet(() -> {
            PersonalUserEntity u = new PersonalUserEntity("carlos.rodriguez", HashingUtil.hashPassword("password123"),
                                                      LocalDate.of(2025, Month.JANUARY, 15),
                                                      userStatuses.get(AppConstants.USER_STATUS_ACTIVE),
                                                      persons.get("Carlos Rodríguez"), newAuditLog());
            return personalUserRepository.save(u);
        });
        personalUsers.put("carlos.rodriguez", carlosUser);

        PersonalUserEntity mariaUser = personalUserRepository.findByUsername("maria.fernandez").orElseGet(() -> {
            PersonalUserEntity u = new PersonalUserEntity("maria.fernandez", HashingUtil.hashPassword("password456"),
                                                      LocalDate.of(2025, Month.JANUARY, 20),
                                                      userStatuses.get(AppConstants.USER_STATUS_ACTIVE),
                                                      persons.get("María Fernández"), newAuditLog());
            return personalUserRepository.save(u);
        });
        personalUsers.put("maria.fernandez", mariaUser);
        
        PersonalUserEntity joseUser = personalUserRepository.findByUsername("jose.vargas").orElseGet(() -> {
            PersonalUserEntity u = new PersonalUserEntity("jose.vargas", HashingUtil.hashPassword("password789"),
                                                      LocalDate.of(2025, Month.FEBRUARY, 10),
                                                      userStatuses.get(AppConstants.USER_STATUS_ACTIVE),
                                                      persons.get("José Vargas"), newAuditLog());
            return personalUserRepository.save(u);
        });
        personalUsers.put("jose.vargas", joseUser);

        PersonalUserEntity anaUser = personalUserRepository.findByUsername("ana.jimenez").orElseGet(() -> {
            PersonalUserEntity u = new PersonalUserEntity("ana.jimenez", HashingUtil.hashPassword("password012"),
                                                      LocalDate.of(2025, Month.FEBRUARY, 15),
                                                      userStatuses.get(AppConstants.USER_STATUS_ACTIVE),
                                                      persons.get("Ana Jiménez"), newAuditLog());
            return personalUserRepository.save(u);
        });
        personalUsers.put("ana.jimenez", anaUser);
    }

    private void createInstitutionalEmails() {
        log.info("Creating institutional emails...");
        institutionalEmailRepository.findByEmailAddress("carlos.rodriguez@ucr.ac.cr").orElseGet(() ->
            institutionalEmailRepository.save(new InstitutionalEmailEntity("carlos.rodriguez@ucr.ac.cr", personalUsers.get("carlos.rodriguez"), newAuditLog()))
        );
        institutionalEmailRepository.findByEmailAddress("maria.fernandez@ucr.ac.cr").orElseGet(() ->
            institutionalEmailRepository.save(new InstitutionalEmailEntity("maria.fernandez@ucr.ac.cr", personalUsers.get("maria.fernandez"), newAuditLog()))
        );
        institutionalEmailRepository.findByEmailAddress("jose.vargas@tec.ac.cr").orElseGet(() ->
            institutionalEmailRepository.save(new InstitutionalEmailEntity("jose.vargas@tec.ac.cr", personalUsers.get("jose.vargas"), newAuditLog()))
        );
        institutionalEmailRepository.findByEmailAddress("ana.jimenez@tec.ac.cr").orElseGet(() ->
            institutionalEmailRepository.save(new InstitutionalEmailEntity("ana.jimenez@tec.ac.cr", personalUsers.get("ana.jimenez"), newAuditLog()))
        );
    }

    private void createAdministratorsAndAccess() {
        log.info("Creating administrators and access statuses...");
        // Ana Jimenez as Administrator
        AdministratorEntity anaAdmin = administratorRepository.findById(persons.get("Ana Jiménez").getId()).orElseGet(() -> {
            AdministratorEntity admin = new AdministratorEntity(persons.get("Ana Jiménez"), newAuditLog());
            return administratorRepository.save(admin);
        });
        administrators.put("ana.jimenez", anaAdmin); // Keyed by username for consistency

        accessStatusRepository.findByAdministrator(anaAdmin).orElseGet(() -> // Assuming one access status per admin for simplicity
            accessStatusRepository.save(new AccessStatusEntity("Acceso total", anaAdmin, newAuditLog()))
        );
    }

    private void createDriversAndVehicles() {
        log.info("Creating drivers and vehicles...");
        // Carlos Rodriguez as Driver
        DriverEntity carlosDriver = driverRepository.findById(persons.get("Carlos Rodríguez").getId()).orElseGet(() -> {
            DriverEntity driver = new DriverEntity(persons.get("Carlos Rodríguez"), newAuditLog());
            return driverRepository.save(driver);
        });
        drivers.put("carlos.rodriguez", carlosDriver);

        VehicleEntity toyota = vehicleRepository.findByPlateNumber("SJO-123").orElseGet(() -> {
            VehicleEntity v = new VehicleEntity("Toyota", "Corolla 2020", "SJO-123", 4, 1, carlosDriver, newAuditLog());
            return vehicleRepository.save(v);
        });
        vehicles.put("SJO-123", toyota);

        // José Vargas as Driver
        DriverEntity joseDriver = driverRepository.findById(persons.get("José Vargas").getId()).orElseGet(() -> {
            DriverEntity driver = new DriverEntity(persons.get("José Vargas"), newAuditLog());
            return driverRepository.save(driver);
        });
        drivers.put("jose.vargas", joseDriver);

        VehicleEntity honda = vehicleRepository.findByPlateNumber("CAR-456").orElseGet(() -> {
            VehicleEntity v = new VehicleEntity("Honda", "Civic 2021", "CAR-456", 4, 1, joseDriver, newAuditLog());
            return vehicleRepository.save(v);
        });
        vehicles.put("CAR-456", honda);
    }
    
    private void createPassengers() {
        log.info("Creating passenger role links...");
        passengerRepository.findById(persons.get("Carlos Rodríguez").getId()).orElseGet(() ->
            passengerRepository.save(new PassengerEntity(persons.get("Carlos Rodríguez"), newAuditLog())));
        passengerRepository.findById(persons.get("María Fernández").getId()).orElseGet(() ->
            passengerRepository.save(new PassengerEntity(persons.get("María Fernández"), newAuditLog())));
        passengerRepository.findById(persons.get("José Vargas").getId()).orElseGet(() ->
            passengerRepository.save(new PassengerEntity(persons.get("José Vargas"), newAuditLog())));
        passengerRepository.findById(persons.get("Ana Jiménez").getId()).orElseGet(() ->
            passengerRepository.save(new PassengerEntity(persons.get("Ana Jiménez"), newAuditLog())));
    }

    private void createStops() {
        log.info("Creating stops...");
        StopEntity ucrStop = stopRepository.findByAddressAndDistrict("Campus Universitario UCR", districts.get("San Pedro")).orElseGet(() ->
            stopRepository.save(new StopEntity("Campus Universitario UCR", districts.get("San Pedro"), newAuditLog()))
        );
        stops.put("UCR Campus", ucrStop);

        StopEntity tecStop = stopRepository.findByAddressAndDistrict("Campus Central TEC", districts.get("Oriental")).orElseGet(() ->
            stopRepository.save(new StopEntity("Campus Central TEC", districts.get("Oriental"), newAuditLog()))
        );
        stops.put("TEC Campus", tecStop);

        StopEntity mallStop = stopRepository.findByAddressAndDistrict("Mall San Pedro", districts.get("San Pedro")).orElseGet(() ->
            stopRepository.save(new StopEntity("Mall San Pedro", districts.get("San Pedro"), newAuditLog()))
        );
        stops.put("Mall San Pedro", mallStop);
    }
    
    // PaymentMethods are loaded in loadStaticData.

    private void createSpecificPriceStatuses() {
        log.info("Creating specific price statuses for tests (Con costo)...");
        // AppConstants.PRICE_STATUS_FREE is already loaded.
        // Create "Con costo" if it's specific for tests and not covered by other AppConstants
        PriceStatusEntity conCosto = priceStatusRepository.findByStatus("Con costo").orElseGet(() -> {
            PriceStatusEntity ps = new PriceStatusEntity("Con costo", newAuditLog());
            return priceStatusRepository.save(ps);
        });
        priceStatuses.put("Con costo", conCosto);
    }

    // TripStatuses are loaded in loadStaticData.

    private void createTrips() {
        log.info("Creating trips...");
        // Trip 1 (Carlos) - Check based on driver and departure time to prevent duplicates if re-run
        TripEntity trip1 = tripRepository.findByDriverAndDepartureDateTime(drivers.get("carlos.rodriguez"), LocalDateTime.of(2025, 5, 18, 7, 0, 0))
            .orElseGet(() -> {
                TripEntity t = new TripEntity(3, LocalDateTime.of(2025, 5, 18, 7, 0, 0),
                                            LocalDateTime.of(2025, 5, 18, 7, 30, 0), 30,
                                            drivers.get("carlos.rodriguez"), vehicles.get("SJO-123"),
                                            priceStatuses.get("Con costo"), // Using specific "Con costo"
                                            newAuditLog());
                return tripRepository.save(t);
            });
        trips.put(1L, trip1); 

        // Trip 2 (José)
        TripEntity trip2 = tripRepository.findByDriverAndDepartureDateTime(drivers.get("jose.vargas"), LocalDateTime.of(2025, 5, 18, 17, 0, 0))
            .orElseGet(() -> {
                TripEntity t = new TripEntity(3, LocalDateTime.of(2025, 5, 18, 17, 0, 0),
                                            LocalDateTime.of(2025, 5, 18, 17, 45, 0), 45,
                                            drivers.get("jose.vargas"), vehicles.get("CAR-456"),
                                            priceStatuses.get(AppConstants.PRICE_STATUS_FREE), // Using static Free
                                            newAuditLog());
                return tripRepository.save(t);
            });
        trips.put(2L, trip2);
    }

    private void createDailyReports() {
        log.info("Creating daily reports...");
        // Assuming report description + institution is unique enough for test data
        DailyReportEntity dr1 = dailyReportRepository.findByInstitution(institutions.get("UCR")).orElseGet(() ->
            dailyReportRepository.save(new DailyReportEntity("Viajes del día", institutions.get("UCR"), newAuditLog()))
        );
        dailyReports.put(1L, dr1);

        DailyReportEntity dr2 = dailyReportRepository.findByInstitution(institutions.get("TEC")).orElseGet(() ->
            dailyReportRepository.save(new DailyReportEntity("Viajes del día", institutions.get("TEC"), newAuditLog()))
        );
        dailyReports.put(2L, dr2);
    }

    private void createSpecificParameters() {
        log.info("Creating specific parameters for tests...");
        // StaticDataInitializer creates its own parameters. These are additional for tests.
        parameterRepository.findByName("MAX_PASSENGERS_PER_TRIP").orElseGet(() ->
            parameterRepository.save(new ParameterEntity("MAX_PASSENGERS_PER_TRIP", "4", newAuditLog()))
        );
        parameterRepository.findByName("MIN_ADVANCE_BOOKING_HOURS").orElseGet(() ->
            parameterRepository.save(new ParameterEntity("MIN_ADVANCE_BOOKING_HOURS", "2", newAuditLog()))
        );
    }

    private void createLogBookEntries() {
        log.info("Creating logbook entries...");
        // Using timestamp for uniqueness for test data
        LogBookEntity lb1 = logBookRepository.findByLogTimeAndDescription(LocalDateTime.of(2025,5,17,10,0,0), "Sistema iniciado").orElseGet(() ->
            logBookRepository.save(new LogBookEntity(LocalDate.of(2025,5,17), LocalDateTime.of(2025,5,17,10,0,0), "Sistema iniciado", newAuditLog()))
        );
        logBooks.put(1L, lb1);

        LogBookEntity lb2 = logBookRepository.findByLogTimeAndDescription(LocalDateTime.of(2025,5,17,10,30,0), "Primer viaje creado").orElseGet(() ->
            logBookRepository.save(new LogBookEntity(LocalDate.of(2025,5,17), LocalDateTime.of(2025,5,17,10,30,0), "Primer viaje creado", newAuditLog()))
        );
        logBooks.put(2L, lb2);
    }

    private void createAuditingDetails() {
        log.info("Creating entity/attribute modified records...");
        // Entity Modified - Assuming entity name is unique for test data
        EntityModifiedEntity em1 = entityModifiedRepository.findByEntityName("Trip").orElseGet(() ->
            entityModifiedRepository.save(new EntityModifiedEntity("Trip", newAuditLog()))
        );
        entityModifiedRecords.put(1L, em1);

        EntityModifiedEntity em2 = entityModifiedRepository.findByEntityName("Person").orElseGet(() ->
            entityModifiedRepository.save(new EntityModifiedEntity("Person", newAuditLog()))
        );
        entityModifiedRecords.put(2L, em2);

        // Attribute Modified - More complex to ensure uniqueness, linking to EntityModified
        AttributeModifiedEntity am1 = attributeModifiedRepository.findByEntityModifiedAndAttributeNameAndOldValue(em1, "status", "Programado").orElseGet(() -> // Example: check old value too
            attributeModifiedRepository.save(new AttributeModifiedEntity(null, "Programado", "status", em1, newAuditLog()))
        );
        attributeModifiedRecords.put(1L, am1);

        AttributeModifiedEntity am2 = attributeModifiedRepository.findByEntityModifiedAndAttributeNameAndOldValueAndNewValue(em2, "userStatus", "Inactivo", "Activo").orElseGet(() ->
            attributeModifiedRepository.save(new AttributeModifiedEntity("Inactivo", "Activo", "userStatus", em2, newAuditLog()))
        );
        attributeModifiedRecords.put(2L, am2);
    }

    // --- N:N Relationship Creation Methods (with basic exists check) ---
    private void createCredentialTypeLinks() {
        log.info("Linking credentials to types...");
        // Carlos: 123456789 -> Licencia
        credentialHasTypeOfCredentialRepository.findByCredentialAndTypeOfCredential(
            credentials.get("123456789"), typeOfCredentials.get(AppConstants.CREDENTIAL_TYPE_NATIONAL_ID)
        ).orElseGet(() -> credentialHasTypeOfCredentialRepository.save(
            new CredentialHasTypeOfCredentialEntity(credentials.get("123456789"), typeOfCredentials.get(AppConstants.CREDENTIAL_TYPE_NATIONAL_ID), newAuditLog())
        ));
        // Carlos: 2022098765 -> Carnet estudiantil
        credentialHasTypeOfCredentialRepository.findByCredentialAndTypeOfCredential(
            credentials.get("2022098765"), typeOfCredentials.get(AppConstants.CREDENTIAL_TYPE_DIMEX)
        ).orElseGet(() -> credentialHasTypeOfCredentialRepository.save(
            new CredentialHasTypeOfCredentialEntity(credentials.get("2022098765"), typeOfCredentials.get(AppConstants.CREDENTIAL_TYPE_DIMEX), newAuditLog())
        ));
        // Jose: LIC-987654321 -> Licencia
        credentialHasTypeOfCredentialRepository.findByCredentialAndTypeOfCredential(
            credentials.get("987654321"), typeOfCredentials.get(AppConstants.CREDENTIAL_TYPE_NATIONAL_ID)
        ).orElseGet(() -> credentialHasTypeOfCredentialRepository.save(
            new CredentialHasTypeOfCredentialEntity(credentials.get("987654321"), typeOfCredentials.get(AppConstants.CREDENTIAL_TYPE_NATIONAL_ID), newAuditLog())
        ));
    }

    private void createAdminInstitutionLinks() {
        adminManageInstitutionRepository.findByAdministratorAndInstitution(
            administrators.get("ana.jimenez"), institutions.get("TEC")
        ).orElseGet(() -> adminManageInstitutionRepository.save(
            new AdminManageInstitutionEntity(administrators.get("ana.jimenez"), institutions.get("TEC"), newAuditLog())
        ));
    }
    
    private void createPassengerTripLinks() {
        // Maria joins Carlos's trip (Trip 1)
        passengerJoinTripRepository.findByPersonalUserAndTrip(
            personalUsers.get("maria.fernandez"), trips.get(1L)
        ).orElseGet(() -> passengerJoinTripRepository.save(
            new PassengerJoinTripEntity(personalUsers.get("maria.fernandez"), trips.get(1L), LocalDate.of(2025, 5, 17), newAuditLog())
        ));

        // Ana queries José's trip (Trip 2)
        // Assuming a query isn't typically "unique" in the same way a join is, but for test data, we might want one entry.
        // If PassengerQueryTripEntity has a unique constraint on (user, trip), this is fine.
        // Otherwise, this might create duplicates on re-runs if not designed carefully.
        // For now, let's assume it's fine for initial population.
        if (!passengerQueryTripRepository.existsByPersonalUserAndTrip(personalUsers.get("ana.jimenez"), trips.get(2L))) {
             passengerQueryTripRepository.save(
                new PassengerQueryTripEntity(personalUsers.get("ana.jimenez"), trips.get(2L), newAuditLog())
            );
        }
    }

    private void createTripStatusLinks() {
        log.info("Linking trips to statuses...");
        // Trip 1 -> Scheduled
        // A trip usually has ONE current status. This N:N table might be for status history.
        // For initial population, we set the initial status.
        if (!tripHasTripStatusRepository.existsByTripAndTripStatus(trips.get(1L), tripStatuses.get(AppConstants.TRIP_STATUS_SCHEDULED))) {
            tripHasTripStatusRepository.save(
                new TripHasTripStatusEntity(trips.get(1L), tripStatuses.get(AppConstants.TRIP_STATUS_SCHEDULED), newAuditLog())
            );
        }
        // Trip 2 -> Scheduled
        if (!tripHasTripStatusRepository.existsByTripAndTripStatus(trips.get(2L), tripStatuses.get(AppConstants.TRIP_STATUS_SCHEDULED))) {
            tripHasTripStatusRepository.save(
                new TripHasTripStatusEntity(trips.get(2L), tripStatuses.get(AppConstants.TRIP_STATUS_SCHEDULED), newAuditLog())
            );
        }
    }

    private void createStopCoordinateLinks() {
        log.info("Linking stops to coordinates...");
        stopHasCoordinateLocationRepository.findByStopAndCoordinateLocation(
            stops.get("UCR Campus"), coordinateLocations.get("UCR")
        ).orElseGet(() -> stopHasCoordinateLocationRepository.save(
            new StopHasCoordinateLocationEntity(stops.get("UCR Campus"), coordinateLocations.get("UCR"), newAuditLog())
        ));
        stopHasCoordinateLocationRepository.findByStopAndCoordinateLocation(
            stops.get("TEC Campus"), coordinateLocations.get("TEC")
        ).orElseGet(() -> stopHasCoordinateLocationRepository.save(
            new StopHasCoordinateLocationEntity(stops.get("TEC Campus"), coordinateLocations.get("TEC"), newAuditLog())
        ));
    }

    private void createTripStopLinks() {
        log.info("Linking trips to stops (route definition)...");
        // Trip 1, Stop 1 (Mall)
        tripHasStopRepository.findByTripAndStopAndNumberStop(trips.get(1L), stops.get("Mall San Pedro"), 1).orElseGet(() ->
            tripHasStopRepository.save(new TripHasStopEntity(trips.get(1L), stops.get("Mall San Pedro"), LocalDate.of(2025,5,18), 500.0, 1, newAuditLog()))
        );
        // Trip 1, Stop 2 (UCR)
        tripHasStopRepository.findByTripAndStopAndNumberStop(trips.get(1L), stops.get("UCR Campus"), 2).orElseGet(() ->
            tripHasStopRepository.save(new TripHasStopEntity(trips.get(1L), stops.get("UCR Campus"), LocalDate.of(2025,5,18), 0.0, 2, newAuditLog()))
        );
        // Trip 2, Stop 1 (TEC)
        tripHasStopRepository.findByTripAndStopAndNumberStop(trips.get(2L), stops.get("TEC Campus"), 1).orElseGet(() ->
            tripHasStopRepository.save(new TripHasStopEntity(trips.get(2L), stops.get("TEC Campus"), LocalDate.of(2025,5,18), 0.0, 1, newAuditLog()))
        );
    }
    
    private void createTripStopPaymentMethodLinks() {
        log.info("Linking payment methods to trip stops...");
        // Trip 1, Mall San Pedro -> Cash
        tripHasStopHasPaymentMethodRepository.findByTripAndStopAndPaymentMethod(
            trips.get(1L), stops.get("Mall San Pedro"), paymentMethods.get(AppConstants.PAYMENT_METHOD_CASH)
        ).orElseGet(() -> tripHasStopHasPaymentMethodRepository.save(
            new TripHasStopHasPaymentMethodEntity(paymentMethods.get(AppConstants.PAYMENT_METHOD_CASH), trips.get(1L), stops.get("Mall San Pedro"), newAuditLog())
        ));
        // Trip 1, Mall San Pedro -> SINPE
        tripHasStopHasPaymentMethodRepository.findByTripAndStopAndPaymentMethod(
            trips.get(1L), stops.get("Mall San Pedro"), paymentMethods.get(AppConstants.PAYMENT_METHOD_SINPE)
        ).orElseGet(() -> tripHasStopHasPaymentMethodRepository.save(
            new TripHasStopHasPaymentMethodEntity(paymentMethods.get(AppConstants.PAYMENT_METHOD_SINPE), trips.get(1L), stops.get("Mall San Pedro"), newAuditLog())
        ));
    }

    private void createTripDailyReportLinks() {
        log.info("Linking trips to daily reports...");
        tripReportDailyReportRepository.findByTripAndDailyReport(trips.get(1L), dailyReports.get(1L)).orElseGet(() ->
            tripReportDailyReportRepository.save(new TripReportDailyReportEntity(trips.get(1L), dailyReports.get(1L), LocalDate.of(2025,5,18), "RPT-UCR-001", newAuditLog()))
        );
        tripReportDailyReportRepository.findByTripAndDailyReport(trips.get(2L), dailyReports.get(2L)).orElseGet(() ->
            tripReportDailyReportRepository.save(new TripReportDailyReportEntity(trips.get(2L), dailyReports.get(2L), LocalDate.of(2025,5,18), "RPT-TEC-001", newAuditLog()))
        );
    }

    private void createAdminDailyReportLinks() {
        log.info("Linking admins to daily reports received...");
        adminReceiveDailyReportRepository.findByAdministratorAndDailyReport(administrators.get("ana.jimenez"), dailyReports.get(2L)).orElseGet(() ->
            adminReceiveDailyReportRepository.save(new AdminReceiveDailyReportEntity(administrators.get("ana.jimenez"), dailyReports.get(2L), newAuditLog()))
        );
    }

    private void createLogBookEntityModifiedLinks() {
        log.info("Linking logbook entries to entity modified records...");
        logBookHasEntityModifiedRepository.findByLogBookAndEntityModified(logBooks.get(2L), entityModifiedRecords.get(1L)).orElseGet(() ->
            logBookHasEntityModifiedRepository.save(new LogBookHasEntityModifiedEntity(logBooks.get(2L), entityModifiedRecords.get(1L), newAuditLog()))
        );
    }

    private void createAttributeModifiedLogBookLinks() {
        log.info("Linking attribute modified records to logbook entries (via AttrModHasEntMod)...");
        attrModHasEntModRepository.findByLogBookAndAttributeModified(logBooks.get(2L), attributeModifiedRecords.get(1L)).orElseGet(() ->
            attrModHasEntModRepository.save(new AttrModHasEntModEntity(logBooks.get(2L), attributeModifiedRecords.get(1L), newAuditLog()))
        );
    }
}