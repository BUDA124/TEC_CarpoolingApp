package org.tec.carpooling.app.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.tec.carpooling.bl.services.AuditLogService;
import org.tec.carpooling.common.constants.AppConstants;
import org.tec.carpooling.common.utils.CatalogEntity;
import org.tec.carpooling.da.entities.*;
import org.tec.carpooling.da.repositories.*;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        log.info("Iniciando la carga de datos estáticos...");

        AuditLogEntity sharedAuditLog = auditService.createInitialAuditLog(AppConstants.SYSTEM_USER);

        // Pass the SAME audit log instance to all catalog initializers.
        initializeGenders(sharedAuditLog);
        initializeUserStatuses(sharedAuditLog);
        initializeTypeOfCredentials(sharedAuditLog);
        initializePaymentMethods(sharedAuditLog);
        initializePriceStatuses(sharedAuditLog);
        initializeTripStatuses(sharedAuditLog);

        // Already work without sharedAuditLog
        initializeCountries();
        initializeCostaRicaLocations();
        initializeParameters();

        log.info("Carga de datos estáticos finalizada.");
    }

    /**
     * REFACTORED: Uses the efficient batch pattern.
     */
    private void initializeCountries() {
        log.info("Inicializando Países...");
        List<String> requiredCountries = Arrays.asList(
                "Afganistán", "Alemania", "Argentina", "Brasil", "Costa Rica", "Rumania", "Samoa"
        );

        // Fetch all existing country names in ONE query.
        Set<String> existingNames = countryRepository.findAll().stream()
                .map(CountryEntity::getName)
                .collect(Collectors.toSet());

        // Determine which countries are missing (in-memory operation).
        List<CountryEntity> countriesToSave = requiredCountries.stream()
                .filter(name -> !existingNames.contains(name)) // Filter out existing ones
                .map(name -> {
                    CountryEntity country = new CountryEntity();
                    country.setName(name);
                    return country;
                })
                .collect(Collectors.toList());

        // If there's anything to save, proceed.
        if (!countriesToSave.isEmpty()) {
            // Create the audit log only ONCE for the entire batch.
            AuditLogEntity auditLog = auditService.createInitialAuditLog(AppConstants.SYSTEM_USER);
            countriesToSave.forEach(country -> {
                country.setAuditLog(auditLog);
                log.info("País '{}' creado.", country.getName());
            });

            // Save all new countries in ONE batch operation.
            countryRepository.saveAll(countriesToSave);
            log.info("{} países nuevos guardados.", countriesToSave.size());
        }
    }

    /**
     * HEAVILY REFACTORED: This method now uses batch processing for each level of the hierarchy
     * (Province, Canton, District) to be extremely efficient and transactionally safe.
     * It creates only ONE audit log for all location data.
     */
    private void initializeCostaRicaLocations() {
        log.info("Inicializando Ubicaciones de Costa Rica...");

        // Create ONE shared audit log for the entire operation.
        AuditLogEntity auditLog = auditService.createInitialAuditLog(AppConstants.SYSTEM_USER);

        // Find or create the root entity, "Costa Rica". This single save is acceptable as a prerequisite.
        CountryEntity costaRica = countryRepository.findByName("Costa Rica").orElseGet(() -> {
            CountryEntity country = new CountryEntity();
            country.setName("Costa Rica");
            country.setAuditLog(auditLog);
            log.info("País" + country.getName() + "creado para las ubicaciones.");
            return countryRepository.save(country);
        });

        Map<String, Map<String, List<String>>> costaRicaDivisions = getCostaRicaDivisions();

        // --- BATCH PROCESS PROVINCES ---
        log.info("Verificando y creando Provincias...");
        Map<String, ProvinceEntity> provincesByName = provinceRepository.findByCountry(costaRica).stream()
                .collect(Collectors.toMap(ProvinceEntity::getName, p -> p));
        List<ProvinceEntity> provincesToSave = new ArrayList<>();

        for (String provinceName : costaRicaDivisions.keySet()) {
            provincesByName.computeIfAbsent(provinceName, name -> {
                ProvinceEntity newProvince = new ProvinceEntity();
                newProvince.setName(name);
                newProvince.setCountry(costaRica);
                newProvince.setAuditLog(auditLog);
                provincesToSave.add(newProvince);
                log.info("Provincia '{}' preparada para creación.", name);
                return newProvince;
            });
        }
        if (!provincesToSave.isEmpty()) {
            provinceRepository.saveAll(provincesToSave);
            log.info("{} provincias nuevas guardadas.", provincesToSave.size());
            // Update the map with the newly saved (and now managed) entities
            provincesToSave.forEach(p -> provincesByName.put(p.getName(), p));
        }

        // --- BATCH PROCESS CANTONS ---
        log.info("Verificando y creando Cantones...");
        List<ProvinceEntity> allProvinces = new ArrayList<>(provincesByName.values());
        Map<String, CantonEntity> cantonsByCompositeKey = cantonRepository.findByProvinceIn(allProvinces).stream()
                .collect(Collectors.toMap(c -> c.getProvince().getName() + ":" + c.getName(), c -> c));
        List<CantonEntity> cantonsToSave = new ArrayList<>();

        for (Map.Entry<String, Map<String, List<String>>> provinceEntry : costaRicaDivisions.entrySet()) {
            String provinceName = provinceEntry.getKey();
            ProvinceEntity currentProvince = provincesByName.get(provinceName);
            for (String cantonName : provinceEntry.getValue().keySet()) {
                cantonsByCompositeKey.computeIfAbsent(provinceName + ":" + cantonName, key -> {
                    CantonEntity newCanton = new CantonEntity();
                    newCanton.setName(cantonName);
                    newCanton.setProvince(currentProvince);
                    newCanton.setAuditLog(auditLog);
                    cantonsToSave.add(newCanton);
                    log.info("Cantón '{}' en {} preparado para creación.", cantonName, provinceName);
                    return newCanton;
                });
            }
        }
        if (!cantonsToSave.isEmpty()) {
            cantonRepository.saveAll(cantonsToSave);
            log.info("{} cantones nuevos guardados.", cantonsToSave.size());
            cantonsToSave.forEach(c -> cantonsByCompositeKey.put(c.getProvince().getName() + ":" + c.getName(), c));
        }

        // --- BATCH PROCESS DISTRICTS ---
        log.info("Verificando y creando Distritos...");
        List<CantonEntity> allCantons = new ArrayList<>(cantonsByCompositeKey.values());
        Map<String, DistrictEntity> districtsByCompositeKey = districtRepository.findByCantonIn(allCantons).stream()
                .collect(Collectors.toMap(d -> d.getCanton().getName() + ":" + d.getName(), d -> d));
        List<DistrictEntity> districtsToSave = new ArrayList<>();

        for (Map.Entry<String, Map<String, List<String>>> provinceEntry : costaRicaDivisions.entrySet()) {
            for (Map.Entry<String, List<String>> cantonEntry : provinceEntry.getValue().entrySet()) {
                String provinceName = provinceEntry.getKey();
                String cantonName = cantonEntry.getKey();
                CantonEntity currentCanton = cantonsByCompositeKey.get(provinceName + ":" + cantonName);
                for (String districtName : cantonEntry.getValue()) {
                    districtsByCompositeKey.computeIfAbsent(cantonName + ":" + districtName, key -> {
                        DistrictEntity newDistrict = new DistrictEntity();
                        newDistrict.setName(districtName);
                        newDistrict.setCanton(currentCanton);
                        newDistrict.setAuditLog(auditLog);
                        districtsToSave.add(newDistrict);
                        log.info("Distrito '{}' en {} preparado para creación.", districtName, cantonName);
                        return newDistrict;
                    });
                }
            }
        }
        if (!districtsToSave.isEmpty()) {
            districtRepository.saveAll(districtsToSave);
            log.info("{} distritos nuevos guardados.", districtsToSave.size());
        }
    }

    private void initializeGenders(AuditLogEntity auditLog) {
        initializeCatalogData("Género",
                Arrays.asList(AppConstants.GENDER_MALE, AppConstants.GENDER_FEMALE,
                        AppConstants.GENDER_NON_BINARY, AppConstants.GENDER_PREFER_NOT_TO_SAY),
                genderRepository,
                GenderEntity::new,
                auditLog);
    }

    private void initializeUserStatuses(AuditLogEntity auditLog) {
        initializeCatalogData("Estado de Usuario",
                Arrays.asList(AppConstants.USER_STATUS_ACTIVE, AppConstants.USER_STATUS_BANNED,
                        AppConstants.USER_STATUS_PENDING_VERIFICATION, AppConstants.USER_STATUS_INACTIVE),
                userStatusRepository,
                UserStatusEntity::new,
                auditLog);
    }

    private void initializeTypeOfCredentials(AuditLogEntity auditLog) {
        initializeCatalogData("Tipo de Credencial",
                Arrays.asList(AppConstants.CREDENTIAL_TYPE_NATIONAL_ID, AppConstants.CREDENTIAL_TYPE_DIMEX,
                        AppConstants.CREDENTIAL_TYPE_NITE, AppConstants.CREDENTIAL_TYPE_PASSPORT),
                typeOfCredentialRepository,
                TypeOfCredentialEntity::new,
                auditLog);
    }

    private void initializePaymentMethods(AuditLogEntity auditLog) {
        initializeCatalogData("Método de Pago",
                Arrays.asList(AppConstants.PAYMENT_METHOD_CASH, AppConstants.PAYMENT_METHOD_SINPE,
                        AppConstants.PAYMENT_METHOD_APP_WALLET, AppConstants.PAYMENT_METHOD_CREDIT_CARD, AppConstants.PAYMENT_METHOD_DEBIT_CARD),
                paymentMethodRepository,
                PaymentMethodEntity::new,
                auditLog);
    }

    private void initializePriceStatuses(AuditLogEntity auditLog) {
        initializeCatalogData("Estado de Precio",
                Arrays.asList(AppConstants.PRICE_STATUS_FREE, AppConstants.PRICE_STATUS_NEGOTIABLE,
                        AppConstants.PRICE_STATUS_FIXED_TOTAL, AppConstants.PRICE_STATUS_PER_PASSENGER),
                priceStatusRepository,
                PriceStatusEntity::new,
                auditLog);
    }

    private void initializeTripStatuses(AuditLogEntity auditLog) {
        initializeCatalogData("Estado de Viaje",
                Arrays.asList(AppConstants.TRIP_STATUS_SCHEDULED, AppConstants.TRIP_STATUS_IN_PROGRESS,
                        AppConstants.TRIP_STATUS_COMPLETED, AppConstants.TRIP_STATUS_CANCELLED),
                tripStatusRepository,
                TripStatusEntity::new,
                auditLog);
    }

    /**
     * REFACTORED: This generic method is now even better. It no longer creates its own
     * audit log, but accepts one, ensuring its part of a larger unit of work.
     */
    private <T extends CatalogEntity> void initializeCatalogData(
            String entityName, List<String> requiredItems, JpaRepository<T, ?> repository, Supplier<T> constructor, AuditLogEntity auditLog) { // <-- ADDED a new parameter

        log.info("Inicializando {}...", entityName);

        Set<String> existingNames = repository.findAll().stream()
                .map(CatalogEntity::getName)
                .collect(Collectors.toSet());

        List<String> namesToCreate = requiredItems.stream()
                .filter(name -> !existingNames.contains(name))
                .toList();

        if (!namesToCreate.isEmpty()) {
            List<T> entitiesToSave = namesToCreate.stream()
                    .map(name -> {
                        T entity = constructor.get();
                        entity.setName(name);
                        entity.setAuditLog(auditLog);
                        log.info("{} '{}' creado.", entityName, name);
                        return entity;
                    })
                    .collect(Collectors.toList());
            repository.saveAll(entitiesToSave);
        }
    }

    /**
     * REFACTORED: Uses the efficient batch pattern for consistency and robustness.
     */
    private void initializeParameters() {
        log.info("Inicializando Parámetros del Sistema...");

        Map<String, String> requiredParameters = new HashMap<>();
        requiredParameters.put(AppConstants.PARAM_MAX_LOGIN_ATTEMPTS, "5");
        requiredParameters.put(AppConstants.PARAM_SESSION_TIMEOUT_MINUTES, "30");

        Set<String> existingNames = parameterRepository.findAll().stream()
                .map(ParameterEntity::getName)
                .collect(Collectors.toSet());

        List<ParameterEntity> parametersToSave = requiredParameters.entrySet().stream()
                .filter(entry -> !existingNames.contains(entry.getKey()))
                .map(entry -> {
                    ParameterEntity parameter = new ParameterEntity();
                    parameter.setName(entry.getKey());
                    parameter.setValue(entry.getValue());
                    return parameter;
                })
                .collect(Collectors.toList());

        if (!parametersToSave.isEmpty()) {
            AuditLogEntity auditLog = auditService.createInitialAuditLog(AppConstants.SYSTEM_USER);
            parametersToSave.forEach(parameter -> {
                parameter.setAuditLog(auditLog);
                log.info("Parámetro del sistema '{}' creado con valor '{}'.", parameter.getName(), parameter.getValue());
            });
            parameterRepository.saveAll(parametersToSave);
        }
    }

    private Map<String, Map<String, List<String>>> getCostaRicaDivisions() {
        Map<String, Map<String, List<String>>> costaRicaDivisions = new LinkedHashMap<>();
        // ... method content is unchanged ...
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

        return costaRicaDivisions;
    }
}