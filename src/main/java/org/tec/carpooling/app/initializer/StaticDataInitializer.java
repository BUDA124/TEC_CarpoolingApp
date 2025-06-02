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
import org.tec.carpooling.da.entities.*;


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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
@Order(2)
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
        initializeCountries();
        initializeCostaRicaLocations();
        initializeUserStatuses();
        initializeTypeOfCredentials();
        initializePaymentMethods();
        initializePriceStatuses();
        initializeTripStatuses();
        initializeLocations();
        initializeParameters();
    }

    private void initializeCountries() {
        List<String> countryNames = Arrays.asList(
                "Afganistán", "Alemania", "Andorra", "Angola",
                "Arabia Saudita", "Argelia", "Argentina", "Armenia", "Australia",
                "Azerbaiyán", "Bahamas", "Bangladés", "Barbados",
                "Belice", "Brasil", "Costa Rica", "Croacia",
                "Ruanda", "Rumania", "Samoa"
        );

        for (String countryName : countryNames) {
            if (countryRepository.findByName(countryName).isEmpty()) {
                CountryEntity country = new CountryEntity();
                country.setName(countryName);
                country.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
                countryRepository.save(country);
            } else {
                log.info("País '{}' ya existe, omitiendo.", countryName);
            }
        }
    }

    private void initializeCostaRicaLocations() {
        log.info("Inicializando Ubicaciones de Costa Rica (Provincia, Cantón, Distrito)...");

        String paisCostaRicaNombre = "Costa Rica";
        CountryEntity costaRica = countryRepository.findByName(paisCostaRicaNombre).orElseGet(() -> {
            CountryEntity country = new CountryEntity();
            country.setName(paisCostaRicaNombre);
            country.setAuditLog(auditService.createInitialAuditLog(AppConstants.SYSTEM_USER));
            log.info("País '{}' creado.", paisCostaRicaNombre);
            return countryRepository.save(country);
        });

        Map<String, Map<String, List<String>>> costaRicaDivisions = new LinkedHashMap<>();

        // ------------------------ PROVINCIA SAN JOSÉ ------------------------
        String provinciaSanJoseNombre = "San José";
        Map<String, List<String>> sanJoseCantones = new LinkedHashMap<>();

        sanJoseCantones.put("San José", Arrays.asList( // Cantón San José (101)
                "Carmen", "Merced", "Hospital", "Catedral", "Zapote", "San Francisco de Dos Ríos",
                "Uruca", "Mata Redonda", "Pavas", "Hatillo", "San Sebastián"
        ));
        sanJoseCantones.put("Escazú", Arrays.asList( // Cantón Escazú (102)
                "Escazú", "San Antonio", "San Rafael"
        ));
        sanJoseCantones.put("Desamparados", Arrays.asList( // Cantón Desamparados (103)
                "Desamparados", "San Miguel", "San Juan de Dios", "San Rafael Arriba", "San Antonio",
                "Frailes", "Patarrá", "San Cristóbal", "Rosario", "Damas", "San Rafael Abajo",
                "Gravilias", "Los Guido"
        ));
        sanJoseCantones.put("Puriscal", Arrays.asList( // Cantón Puriscal (104)
                "Santiago", "Mercedes Sur", "Barbacoas", "Grifo Alto", "San Rafael",
                "Candelaria", "Desamparaditos", "San Antonio", "Chires"
        ));
        sanJoseCantones.put("Tarrazú", Arrays.asList( // Cantón Tarrazú (105)
                "San Marcos", "San Lorenzo", "San Carlos"
        ));
        sanJoseCantones.put("Aserrí", Arrays.asList( // Cantón Aserrí (106)
                "Aserrí", "Tarbaca o Praga", "Vuelta de Jorco", "San Gabriel", "La Legua",
                "Monterrey", "Salitrillos"
        ));
        sanJoseCantones.put("Mora", Arrays.asList( // Cantón Mora (107)
                "Colón", "Guayabo", "Tabarcia", "Piedras Negras", "Picagres"
        ));
        sanJoseCantones.put("Goicoechea", Arrays.asList( // Cantón Goicoechea (108)
                "Guadalupe", "San Francisco", "Calle Blancos", "Mata de Plátano", "Ipís",
                "Rancho Redondo", "Purral"
        ));
        sanJoseCantones.put("Santa Ana", Arrays.asList( // Cantón Santa Ana (109)
                "Santa Ana", "Salitral", "Pozos o Concepción", "Uruca o San Joaquín", "Piedades", "Brasil"
        ));
        sanJoseCantones.put("Alajuelita", Arrays.asList( // Cantón Alajuelita (110)
                "Alajuelita", "San Josecito", "San Antonio", "Concepción", "San Felipe"
        ));
        sanJoseCantones.put("Coronado", Arrays.asList( // Cantón Vázquez de Coronado (111)
                "San Isidro", "San Rafael", "Dulce Nombre o Jesús", "Patalillo", "Cascajal"
        ));
        sanJoseCantones.put("Acosta", Arrays.asList( // Cantón Acosta (112)
                "San Ignacio", "Guaitil", "Palmichal", "Cangrejal", "Sabanillas"
        ));
        sanJoseCantones.put("Tibás", Arrays.asList( // Cantón Tibás (113)
                "San Juan", "Cinco Esquinas", "Anselmo Llorente", "León XIII", "Colima"
        ));
        sanJoseCantones.put("Moravia", Arrays.asList( // Cantón Moravia (114)
                "San Vicente", "San Jerónimo", "La Trinidad"
        ));
        sanJoseCantones.put("Montes de Oca", Arrays.asList( // Cantón Montes de Oca (115)
                "San Pedro", "Sabanilla", "Mercedes o Betania", "San Rafael"
        ));
        sanJoseCantones.put("Turrubares", Arrays.asList( // Cantón Turrubares (116)
                "San Pablo", "San Pedro", "San Juan de Mata", "San Luis", "Carara"
        ));
        sanJoseCantones.put("Dota", Arrays.asList( // Cantón Dota (117)
                "Santa María", "Jardín", "Copey"
        ));
        sanJoseCantones.put("Curridabat", Arrays.asList( // Cantón Curridabat (118)
                "Curridabat", "Granadilla", "Sánchez", "Tirrases"
        ));
        sanJoseCantones.put("Pérez Zeledón", Arrays.asList( // Cantón Pérez Zeledón (119)
                "San Isidro", "General", "Daniel Flores", "Rivas", "San Pedro", "Platanares",
                "Pejibaye", "Cajón o Carmen", "Barú", "Río Nuevo", "Páramo"
        ));
        sanJoseCantones.put("León Cortés", Arrays.asList( // Cantón León Cortés Castro (120)
                "San Pablo", "San Andrés", "Llano Bonito", "San Isidro", "Santa Cruz", "San Antonio"
        ));
        costaRicaDivisions.put(provinciaSanJoseNombre, sanJoseCantones);

        // -------------------- PROVINCIA ALAJUELA (Ejemplo Parcial) --------------------
        String provinciaAlajuelaNombre = "Alajuela";
        Map<String, List<String>> alajuelaCantones = new LinkedHashMap<>();
        alajuelaCantones.put("Alajuela", Arrays.asList( // Cantón Alajuela (201)
                "Alajuela", "San José", "Carrizal", "San Antonio", "Guácima", "San Isidro", "Sabanilla",
                "San Rafael", "Río Segundo", "Desamparados", "Turrúcares", "Tambor", "La Garita", "Sarapiquí"
        ));
        alajuelaCantones.put("San Ramón", Arrays.asList( // Cantón San Ramón (202)
                "San Ramón", "Santiago", "San Juan", "Piedades Norte", "Piedades Sur", "San Rafael",
                "San Isidro", "Ángeles", "Alfaro", "Volio", "Concepción", "Zapotal", "San Isidro de Peñas Blancas"
        ));
        costaRicaDivisions.put(provinciaAlajuelaNombre, alajuelaCantones);

        // -------------------- PROVINCIA CARTAGO (Ejemplo Parcial) --------------------
        String provinciaCartagoNombre = "Cartago";
        Map<String, List<String>> cartagoCantones = new LinkedHashMap<>();
        cartagoCantones.put("Cartago", Arrays.asList( // Cantón Cartago (301)
                "Oriental", "Occidental", "Carmen", "San Nicolás", "Aguacaliente (San Francisco)", // DTE dice "Aguacaliente (San Francisco)"
                "Guadalupe (Arenilla)", "Corralillo", "Tierra Blanca", "Dulce Nombre", "Llano Grande", "Quebradilla"
        ));
        costaRicaDivisions.put(provinciaCartagoNombre, cartagoCantones);

        // -------------------- PROVINCIA HEREDIA (Ejemplo Parcial) --------------------
        String provinciaHerediaNombre = "Heredia";
        Map<String, List<String>> herediaCantones = new LinkedHashMap<>();
        herediaCantones.put("Heredia", Arrays.asList( // Cantón Heredia (401)
                "Heredia", "Mercedes", "San Francisco", "Ulloa", "Varablanca"
        ));
        costaRicaDivisions.put(provinciaHerediaNombre, herediaCantones);

        // -------------------- PROVINCIA GUANACASTE (Ejemplo Parcial) --------------------
        String provinciaGuanacasteNombre = "Guanacaste";
        Map<String, List<String>> guanacasteCantones = new LinkedHashMap<>();
        guanacasteCantones.put("Liberia", Arrays.asList( // Cantón Liberia (501)
                "Liberia", "Cañas Dulces", "Mayorga", "Nacascolo", "Curubandé"
        ));
        costaRicaDivisions.put(provinciaGuanacasteNombre, guanacasteCantones);

        // -------------------- PROVINCIA PUNTARENAS (Ejemplo Parcial) --------------------
        String provinciaPuntarenasNombre = "Puntarenas";
        Map<String, List<String>> puntarenasCantones = new LinkedHashMap<>();
        puntarenasCantones.put("Puntarenas", Arrays.asList( // Cantón Puntarenas (601)
                "Puntarenas", "Pitahaya", "Chomes", "Lepanto", "Paquera", "Manzanillo", "Guacimal", "Barranca",
                "Monte Verde", "Isla del Coco", "Cóbano", "Chacarita", "Chira (Isla)", "Acapulco", "El Roble", "Arancibia"
        ));
        costaRicaDivisions.put(provinciaPuntarenasNombre, puntarenasCantones);

        // -------------------- PROVINCIA LIMÓN (Ejemplo Parcial) --------------------
        String provinciaLimonNombre = "Limón";
        Map<String, List<String>> limonCantones = new LinkedHashMap<>();
        limonCantones.put("Limón", Arrays.asList( // Cantón Limón (701)
                "Limón", "Valle La Estrella", "Río Blanco", "Matama"
        ));
        costaRicaDivisions.put(provinciaLimonNombre, limonCantones);


        // Iterar sobre la estructura de datos para crear las entidades
        for (Map.Entry<String, Map<String, List<String>>> provinceEntry : costaRicaDivisions.entrySet()) {
            String provinceName = provinceEntry.getKey();
            Map<String, List<String>> cantonesMap = provinceEntry.getValue();

            AuditLogEntity provinceAuditLog = auditService.createInitialAuditLog(AppConstants.SYSTEM_USER);
            ProvinceEntity province = provinceRepository.findByNameAndCountry(provinceName, costaRica).orElseGet(() -> {
                ProvinceEntity newProvince = new ProvinceEntity();
                newProvince.setName(provinceName);
                newProvince.setCountry(costaRica);
                newProvince.setAuditLog(provinceAuditLog);
                log.info("Provincia '{}' creada en {}.", provinceName, costaRica.getName());
                return provinceRepository.save(newProvince);
            });

            for (Map.Entry<String, List<String>> cantonEntry : cantonesMap.entrySet()) {
                String cantonName = cantonEntry.getKey();
                List<String> districtNames = cantonEntry.getValue();

                AuditLogEntity cantonAuditLog = auditService.createInitialAuditLog(AppConstants.SYSTEM_USER);
                CantonEntity canton = cantonRepository.findByNameAndProvince(cantonName, province).orElseGet(() -> {
                    CantonEntity newCanton = new CantonEntity();
                    newCanton.setName(cantonName);
                    newCanton.setProvince(province);
                    newCanton.setAuditLog(cantonAuditLog);
                    log.info("Cantón '{}' creado en {}.", cantonName, province.getName());
                    return cantonRepository.save(newCanton);
                });

                for (String districtName : districtNames) {
                    // No es necesario crear un nuevo AuditLogEntity por cada distrito si todos comparten la misma info de auditoría inicial.
                    // Pero si cada uno debe tener su propio registro de auditoría individual (con su propio ID), entonces está bien.
                    // Para datos estáticos masivos, a veces se usa un solo log o se optimiza.
                    // Siguiendo el patrón de las entidades provistas, cada una tiene su propio AuditLog.
                    AuditLogEntity districtAuditLog = auditService.createInitialAuditLog(AppConstants.SYSTEM_USER);
                    districtRepository.findByNameAndCanton(districtName, canton).orElseGet(() -> {
                        DistrictEntity newDistrict = new DistrictEntity();
                        newDistrict.setName(districtName);
                        newDistrict.setCanton(canton);
                        newDistrict.setAuditLog(districtAuditLog);
                        log.info("Distrito '{}' creado en {}.", districtName, canton.getName());
                        return districtRepository.save(newDistrict);
                    });
                }
            }
        }
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