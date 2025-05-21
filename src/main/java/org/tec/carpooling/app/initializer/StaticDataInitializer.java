package org.tec.carpooling.app.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.tec.carpooling.bl.services.AuditLogService;
import org.tec.carpooling.common.constants.AppConstants;

// Entidades (asumimos que existen estas clases en org.tec.carpooling.da.entities)
import org.tec.carpooling.da.entities.GenderEntity;
import org.tec.carpooling.da.entities.UserStatusEntity;
import org.tec.carpooling.da.entities.TypeOfCredentialEntity;
import org.tec.carpooling.da.entities.PaymentMethodEntity;
import org.tec.carpooling.da.entities.PriceStatusEntity;
import org.tec.carpooling.da.entities.TripStatusEntity;
import org.tec.carpooling.da.entities.CountryEntity;
import org.tec.carpooling.da.entities.ProvinceEntity;
import org.tec.carpooling.da.entities.CantonEntity;
import org.tec.carpooling.da.entities.DistrictEntity;
import org.tec.carpooling.da.entities.ParameterEntity;


// Repositorios (asumimos que existen estas interfaces en org.tec.carpooling.da.repositories)
import org.tec.carpooling.da.repositories.GenderRepository;
import org.tec.carpooling.da.repositories.UserStatusRepository;
import org.tec.carpooling.da.repositories.TypeOfCredentialRepository;
import org.tec.carpooling.da.repositories.PaymentMethodRepository;
import org.tec.carpooling.da.repositories.PriceStatusRepository;
import org.tec.carpooling.da.repositories.TripStatusRepository;
import org.tec.carpooling.da.repositories.CountryRepository;
import org.tec.carpooling.da.repositories.ProvinceRepository;
import org.tec.carpooling.da.repositories.CantonRepository;
import org.tec.carpooling.da.repositories.DistrictRepository;
import org.tec.carpooling.da.repositories.ParameterRepository;


import java.util.Arrays;
import java.util.List;

@Component
@Order(1)
public class StaticDataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(StaticDataInitializer.class);

    @Autowired private AuditLogService auditService;
    @Autowired private GenderRepository genderRepository;
    @Autowired private UserStatusRepository userStatusRepository;
    @Autowired private TypeOfCredentialRepository typeOfCredentialRepository;
    @Autowired private PaymentMethodRepository paymentMethodRepository;
    @Autowired private PriceStatusRepository priceStatusRepository;
    @Autowired private TripStatusRepository tripStatusRepository;
    @Autowired private CountryRepository countryRepository;
    @Autowired private ProvinceRepository provinceRepository;
    @Autowired private CantonRepository cantonRepository;
    @Autowired private DistrictRepository districtRepository;
    @Autowired private ParameterRepository parameterRepository;


    public StaticDataInitializer() {}

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        initializeGenders();
        initializeUserStatuses();
        initializeTypeOfCredentials();
        initializePaymentMethods();
        initializePriceStatuses();
        initializeTripStatuses();
        initializeLocations();
        initializeParameters();
    }

    private void initializeGenders() {
        List<String> genderNames = Arrays.asList(
                AppConstants.GENDER_MALE,
                AppConstants.GENDER_FEMALE,
                AppConstants.GENDER_NON_BINARY,
                AppConstants.GENDER_PREFER_NOT_TO_SAY
        );

        for (String genderName : genderNames) {
            if (genderRepository.findByGenderName(genderName).isEmpty()) {
                GenderEntity gender = new GenderEntity();
                gender.setGenderName(genderName);
                gender.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
                genderRepository.save(gender);
            } else {
                log.info("Género '{}' ya existe, omitiendo.", genderName);
            }
        }
    }

    private void initializeUserStatuses() {
        List<String> statuses = Arrays.asList(
                AppConstants.USER_STATUS_ACTIVE,
                AppConstants.USER_STATUS_BANNED,
                AppConstants.USER_STATUS_PENDING_VERIFICATION,
                AppConstants.USER_STATUS_INACTIVE
        );

        for (String statusName : statuses) {
            if (userStatusRepository.findByStatus(statusName).isEmpty()) {
                UserStatusEntity statusEntity = new UserStatusEntity();
                statusEntity.setStatus(statusName);
                statusEntity.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
                userStatusRepository.save(statusEntity);
            } else {
                log.info("Estado de usuario '{}' ya existe, omitiendo.", statusName);
            }
        }
    }

    private void initializeTypeOfCredentials() {
        List<String> types = Arrays.asList(
                AppConstants.CREDENTIAL_TYPE_NATIONAL_ID,
                AppConstants.CREDENTIAL_TYPE_DIMEX,
                AppConstants.CREDENTIAL_TYPE_NITE,
                AppConstants.CREDENTIAL_TYPE_PASSPORT
        );

        for (String typeName : types) {
            if (typeOfCredentialRepository.findByType(typeName).isEmpty()) {
                TypeOfCredentialEntity typeEntity = new TypeOfCredentialEntity();
                typeEntity.setType(typeName);
                typeEntity.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
                typeOfCredentialRepository.save(typeEntity);
            } else {
                log.info("Tipo de credencial '{}' ya existe, omitiendo.", typeName);
            }
        }
    }

    private void initializePaymentMethods() {
        List<String> methods = Arrays.asList(
                AppConstants.PAYMENT_METHOD_CASH,
                AppConstants.PAYMENT_METHOD_SINPE,
                AppConstants.PAYMENT_METHOD_APP_WALLET,
                AppConstants.PAYMENT_METHOD_CREDIT_CARD,
                AppConstants.PAYMENT_METHOD_DEBIT_CARD
        );

        for (String methodName : methods) {
            if (paymentMethodRepository.findByMethod(methodName).isEmpty()) {
                PaymentMethodEntity methodEntity = new PaymentMethodEntity();
                methodEntity.setMethod(methodName);
                methodEntity.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
                paymentMethodRepository.save(methodEntity);
            } else {
                log.info("Método de pago '{}' ya existe, omitiendo.", methodName);
            }
        }
    }

    private void initializePriceStatuses() {
        List<String> statuses = Arrays.asList(
                AppConstants.PRICE_STATUS_FREE,
                AppConstants.PRICE_STATUS_NEGOTIABLE,
                AppConstants.PRICE_STATUS_FIXED_TOTAL,
                AppConstants.PRICE_STATUS_PER_PASSENGER
        );

        for (String statusName : statuses) {
            if (priceStatusRepository.findByStatus(statusName).isEmpty()) {
                PriceStatusEntity statusEntity = new PriceStatusEntity();
                statusEntity.setStatus(statusName);
                statusEntity.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
                priceStatusRepository.save(statusEntity);
            } else {
                log.info("Estado de precio '{}' ya existe, omitiendo.", statusName);
            }
        }
    }

    private void initializeTripStatuses() {
        List<String> statuses = Arrays.asList(
                AppConstants.TRIP_STATUS_SCHEDULED,
                AppConstants.TRIP_STATUS_IN_PROGRESS,
                AppConstants.TRIP_STATUS_COMPLETED,
                AppConstants.TRIP_STATUS_CANCELLED
        );

        for (String statusName : statuses) {
            if (tripStatusRepository.findByStatus(statusName).isEmpty()) {
                TripStatusEntity statusEntity = new TripStatusEntity();
                statusEntity.setStatus(statusName);
                statusEntity.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
                tripStatusRepository.save(statusEntity);
            } else {
                log.info("Estado de viaje '{}' ya existe, omitiendo.", statusName);
            }
        }
    }

    private void initializeLocations() {
        log.info("Inicializando Ubicaciones (País, Provincia, Cantón, Distrito)...");

        // País
        CountryEntity costaRica = countryRepository.findByName(AppConstants.COUNTRY_COSTA_RICA).orElseGet(() -> {
            CountryEntity country = new CountryEntity();
            country.setName(AppConstants.COUNTRY_COSTA_RICA);
            country.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
            log.info("País '{}' creado.", AppConstants.COUNTRY_COSTA_RICA);
            return countryRepository.save(country);
        });

        // Provincia
        ProvinceEntity sanJoseProvince = provinceRepository.findByNameAndCountry(AppConstants.PROVINCE_SAN_JOSE, costaRica).orElseGet(() -> {
            ProvinceEntity province = new ProvinceEntity();
            province.setName(AppConstants.PROVINCE_SAN_JOSE);
            province.setCountry(costaRica);
            province.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
            log.info("Provincia '{}' creada en {}.", AppConstants.PROVINCE_SAN_JOSE, costaRica.getName());
            return provinceRepository.save(province);
        });

        // Cantón
        CantonEntity sanJoseCanton = cantonRepository.findByNameAndProvince(AppConstants.CANTON_SAN_JOSE_CENTRAL, sanJoseProvince).orElseGet(() -> {
            CantonEntity canton = new CantonEntity();
            canton.setName(AppConstants.CANTON_SAN_JOSE_CENTRAL);
            canton.setProvince(sanJoseProvince);
            canton.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
            log.info("Cantón '{}' creado en {}.", AppConstants.CANTON_SAN_JOSE_CENTRAL, sanJoseProvince.getName());
            return cantonRepository.save(canton);
        });

        // Distrito
        DistrictEntity carmenDistrict = districtRepository.findByNameAndCanton(AppConstants.DISTRICT_CARMEN_SAN_JOSE, sanJoseCanton).orElseGet(() -> {
            DistrictEntity district = new DistrictEntity();
            district.setName(AppConstants.DISTRICT_CARMEN_SAN_JOSE);
            district.setCanton(sanJoseCanton);
            district.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
            log.info("Distrito '{}' creado en {}.", AppConstants.DISTRICT_CARMEN_SAN_JOSE, sanJoseCanton.getName());
            return districtRepository.save(district);
        });
    }

    private void initializeParameters() {
        initializeParameter(AppConstants.PARAM_MAX_LOGIN_ATTEMPTS, "5");
        initializeParameter(AppConstants.PARAM_SESSION_TIMEOUT_MINUTES, "30");
    }

    private void initializeParameter(String name, String value) {
        if (parameterRepository.findByName(name).isEmpty()) {
            ParameterEntity parameter = new ParameterEntity();
            parameter.setName(name);
            parameter.setValue(value);
            parameter.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
            parameterRepository.save(parameter);
        } else {
            log.info("Parámetro del sistema '{}' ya existe, omitiendo.", name);
        }
    }
}