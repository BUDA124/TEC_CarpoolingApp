-- =============================================================================
-- I. USER REGISTRATION & PROFILE MANAGEMENT
-- =============================================================================

CREATE OR REPLACE PACKAGE USER_MANAGEMENT_PKG AS
    -- Procedimiento para registrar un nuevo usuario completo
    -- Incluye la creación de registros en Persona, PersonalUser, Gender, Email, PhoneNumber, InstitutionalEmail, Credential, Passenger, y Driver (condicional).
    PROCEDURE RegisterNewUser (
        p_first_name        IN VARCHAR2,
        p_second_name       IN VARCHAR2,
        p_first_surname     IN VARCHAR2,
        p_second_surname    IN VARCHAR2,
        p_birthdate         IN DATE,
        p_nationality       IN VARCHAR2,
        p_profile_picture   IN BLOB,
        p_institution_id    IN NUMBER,
        p_audit_log_id      IN NUMBER, -- ID del log de auditoría para todas las operaciones
        p_hashed_password   IN VARCHAR2,
        p_username          IN VARCHAR2,
        p_gender_name       IN VARCHAR2,
        p_personal_email    IN VARCHAR2,
        p_phone_number      IN VARCHAR2,
        p_institutional_email IN VARCHAR2,
        p_id_card_number    IN VARCHAR2, -- Asumiendo número de cédula como credencial
        p_is_driver         IN BOOLEAN DEFAULT FALSE, -- Indica si también se registra como conductor
        p_is_passenger      IN BOOLEAN DEFAULT TRUE, -- Indica si también se registra como pasajero
        po_new_person_id    OUT NUMBER, -- ID de la nueva persona
        po_new_user_id      OUT NUMBER  --  ID del nuevo usuario
    );

    -- Función para obtener los detalles del perfil de un usuario por su nombre de usuario.
    FUNCTION GetUserProfileByUsername (
        p_username IN VARCHAR2
    ) RETURN SYS_REFCURSOR;

    -- Función para verificar si un nombre de usuario ya existe.
    FUNCTION CheckUsernameExists (
        p_username IN VARCHAR2
    ) RETURN NUMBER;

    -- Función para obtener el hash de la contraseña de un usuario por su nombre de usuario.
    FUNCTION GetUserPasswordHash (
        p_username IN VARCHAR2
    ) RETURN VARCHAR2;

    -- Procedimiento para actualizar la información básica de una persona.
    PROCEDURE UpdatePersonInfo (
        p_person_id         IN NUMBER,
        p_first_name        IN VARCHAR2 DEFAULT NULL,
        p_second_name       IN VARCHAR2 DEFAULT NULL,
        p_first_surname     IN VARCHAR2 DEFAULT NULL,
        p_second_surname    IN VARCHAR2 DEFAULT NULL,
        p_birthdate         IN DATE DEFAULT NULL,
        p_nationality       IN VARCHAR2 DEFAULT NULL,
        p_profile_picture   IN BLOB DEFAULT NULL,
        p_institution_id IN NUMBER DEFAULT NULL,
        p_audit_log_id      IN NUMBER
    );

    -- Procedimiento para actualizar una dirección de correo electrónico específica de una persona.
    PROCEDURE UpdateEmail (
        p_email_record_id   IN NUMBER,
        p_person_id         IN NUMBER,
        p_new_email         IN VARCHAR2,
        p_audit_log_id      IN NUMBER
    );

    -- Procedimiento para actualizar un número de teléfono específico de una persona.
    PROCEDURE UpdatePhoneNumber (
        p_phone_record_id   IN NUMBER,
        p_person_id         IN NUMBER,
        p_new_phone         IN VARCHAR2,
        p_audit_log_id      IN NUMBER
    );

    -- Procedimiento para actualizar la contraseña de un usuario.
    PROCEDURE UpdateUserPassword (
        p_user_id           IN NUMBER,
        p_new_hashed_password IN VARCHAR2,
        p_audit_log_id      IN NUMBER
    );

    -- Procedimiento para desactivar una cuenta de usuario.
    PROCEDURE DeactivateUserAccount (
        p_person_id         IN NUMBER,
        p_audit_log_id      IN NUMBER
    );

END USER_MANAGEMENT_PKG;
/

-- =============================================================================
-- II. DRIVER & VEHICLE MANAGEMENT
-- =============================================================================

CREATE OR REPLACE PACKAGE PKG_VEHICLE_MGMT AS

    -- Procedimiento para registrar a una persona como conductor si aún no lo es.
    PROCEDURE RegisterPersonAsDriver (
        p_person_id     IN NUMBER,
        p_audit_log_id  IN NUMBER
    );

    -- Procedimiento para agregar un nuevo vehículo a un conductor.
    PROCEDURE AddNewVehicle (
        p_brand         IN VARCHAR2,
        p_car_photo     IN BLOB,
        p_plate_number  IN VARCHAR2,
        p_car_model     IN VARCHAR2,
        p_seat_quantity IN NUMBER,
        p_driver_person_id IN NUMBER,
        p_audit_log_id  IN NUMBER,
        po_new_vehicle_id OUT NUMBER
    );

    -- Función para obtener la lista de vehículos de un conductor específico.
    FUNCTION GetVehiclesByDriver (
        p_driver_person_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

    -- Función para obtener los detalles completos de un vehículo específico.
    FUNCTION GetVehicleDetails (
        p_vehicle_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

    -- Procedimiento para actualizar los detalles de un vehículo.
    PROCEDURE UpdateVehicleDetails (
        p_vehicle_id        IN NUMBER,
        p_driver_person_id  IN NUMBER,
        p_brand             IN VARCHAR2 DEFAULT NULL,
        p_car_model         IN VARCHAR2 DEFAULT NULL,
        p_seat_quantity     IN NUMBER DEFAULT NULL,
        p_car_photo         IN BLOB DEFAULT NULL,
        p_audit_log_id      IN NUMBER
    );

    -- Procedimiento para verificar o desverificar un vehículo.
    PROCEDURE VerifyVehicle (
        p_vehicle_id            IN NUMBER,
        p_verification_status   IN NUMBER,
        p_audit_log_id          IN NUMBER
    );

    -- Procedimiento para eliminar un vehículo.
    PROCEDURE DeleteVehicle (
        p_vehicle_id        IN NUMBER,
        p_driver_person_id  IN NUMBER,
        p_audit_log_id      IN NUMBER
    );

END PKG_VEHICLE_MGMT;
/


-- =============================================================================
-- III. TRIP CREATION & SEARCH
-- =============================================================================
CREATE OR REPLACE PACKAGE PKG_TRIP_MGMT AS

    -- Procedimiento para crear un nuevo viaje.
    PROCEDURE CreateNewTrip (
        p_max_passengers    IN NUMBER,
        p_departure_ts      IN TIMESTAMP,
        p_duration_minutes  IN NUMBER,
        p_arrival_ts        IN TIMESTAMP,
        p_driver_person_id  IN NUMBER,
        p_price_status_id   IN NUMBER,
        p_vehicle_id        IN NUMBER,
        p_audit_log_id      IN NUMBER,
        p_origin_stop_id    IN NUMBER,
        p_origin_est_arrival IN TIMESTAMP,
        p_origin_stop_cost  IN NUMBER,
        p_origin_stop_number IN NUMBER,
        po_new_trip_id      OUT NUMBER
    );

    -- Función para encontrar viajes disponibles para pasajeros basados en criterios de origen, destino, tiempo y institución.
    FUNCTION FindAvailableTrips (
        p_searching_passenger_person_id IN NUMBER,
        p_origin_district_id            IN NUMBER,
        p_destination_district_id       IN NUMBER,
        p_min_departure_time            IN TIMESTAMP
    ) RETURN SYS_REFCURSOR;

-- =============================================================================
-- IV. VIEWING & JOINING TRIPS
-- =============================================================================

    -- Función para obtener la información principal de un viaje específico.
    FUNCTION GetTripCoreInfo (
        p_trip_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

    -- Función para obtener los detalles de las paradas de un viaje específico.
    FUNCTION GetTripStops (
        p_trip_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

    -- Función para obtener la lista de pasajeros unidos a un viaje específico.
    FUNCTION GetTripPassengers (
        p_trip_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

    -- Procedimiento para que un pasajero se una a un viaje.
    PROCEDURE PassengerJoinsTrip (
        p_passenger_user_id IN NUMBER,
        p_trip_id           IN NUMBER,
        p_audit_log_id      IN NUMBER
    );

    -- Procedimiento para que un pasajero salga de un viaje.
    PROCEDURE PassengerLeavesTrip (
        p_passenger_user_id IN NUMBER,
        p_trip_id           IN NUMBER
    );

    -- Función para obtener la lista de viajes a los que un pasajero está unido.
    FUNCTION GetTripsJoinedByPassenger (
        p_passenger_user_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

    -- Procedimiento para que el conductor actualice los detalles de su viaje.
    PROCEDURE UpdateTripDetails (
        p_trip_id           IN NUMBER,
        p_driver_person_id  IN NUMBER, -- ID del conductor para verificación
        p_new_departure_ts  IN TIMESTAMP DEFAULT NULL,
        p_new_arrival_ts    IN TIMESTAMP DEFAULT NULL,
        p_new_max_passengers IN NUMBER DEFAULT NULL,
        p_audit_log_id      IN NUMBER
    );

    -- Procedimiento para añadir un nuevo estado al historial de un viaje.
    PROCEDURE AddTripStatusHistory (
        p_trip_id       IN NUMBER,
        p_new_status_id IN NUMBER,
        p_audit_log_id  IN NUMBER
    );

    -- Procedimiento para cancelar un viaje (caso específico de actualización de estado).
    PROCEDURE CancelTrip (
        p_trip_id       IN NUMBER,
        p_audit_log_id  IN NUMBER
    );

END PKG_TRIP_MGMT;
/

-- =============================================================================
-- VI. LOCATION & INSTITUTION LOOKUPS
-- =============================================================================

CREATE OR REPLACE PACKAGE PKG_LOOKUPS AS

    -- Función para obtener todas las instituciones.
    FUNCTION GetAllInstitutions
        RETURN SYS_REFCURSOR;

    -- Función para obtener detalles de una institución por su ID.
    FUNCTION GetInstitutionDetailsByID (
        p_institution_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

    -- Función para obtener todos los países.
    FUNCTION GetCountries
        RETURN SYS_REFCURSOR;

    -- Función para obtener las provincias de un país específico.
    FUNCTION GetProvincesForCountry (
        p_country_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

    -- Función para obtener los cantones de una provincia específica.
    FUNCTION GetCantonsForProvince (
        p_province_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

    -- Función para obtener los distritos de un cantón específico.
    FUNCTION GetDistrictsForCanton (
        p_canton_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

END PKG_LOOKUPS;
/


-- =============================================================================
-- VII. GENERAL REPORTS & STATISTICS
-- =============================================================================
CREATE OR REPLACE PACKAGE PKG_REPORTING AS

    -- Función para obtener el Top 5 de conductores con más viajes completados.
    FUNCTION GetTop5DriversByCompletedTrips
        RETURN SYS_REFCURSOR;

    -- Función para obtener el Top 5 de paradas más frecuentes en un rango de fechas.
    FUNCTION GetTop5MostFrequentStops (
        p_start_date IN DATE,
        p_end_date   IN DATE
    ) RETURN SYS_REFCURSOR;

    -- Función para obtener el Top 5 de pasajeros más activos (más viajes unidos).
    FUNCTION GetTop5MostActivePassengers
        RETURN SYS_REFCURSOR;

    -- Función para obtener información detallada de todos los viajes.
    FUNCTION GetAllTripsDetailedInfo
        RETURN SYS_REFCURSOR;

    -- Función para calcular el promedio de tarifa cobrada por los conductores.
    FUNCTION GetAverageFareCharged
        RETURN NUMBER;

    -- Función para obtener el conteo de nuevos usuarios registrados en los últimos 3 meses.
    FUNCTION GetNewUserCountLast3Months
        RETURN NUMBER;

    -- Función para obtener el conteo de conductores por género en un rango de fechas de registro.
    FUNCTION GetDriverCountByGenderAndRegDate (
        p_start_date IN DATE,
        p_end_date   IN DATE
    ) RETURN SYS_REFCURSOR;

    -- Función para obtener el conteo de pasajeros por género en un rango de fechas de registro.
    FUNCTION GetPassengerCountByGenderAndRegDate (
        p_start_date IN DATE,
        p_end_date   IN DATE
    ) RETURN SYS_REFCURSOR;

    -- Función para obtener el conteo de usuarios por rango de edad y género.
    FUNCTION GetUserCountByAgeRangeAndGender
        RETURN SYS_REFCURSOR;

END PKG_REPORTING;
/


-- =============================================================================
-- VIII. ADMINISTRATION & AUDITING
-- =============================================================================
CREATE OR REPLACE PACKAGE PKG_ADMIN_TOOLS AS

    -- Procedimiento para registrar a una persona como administrador.
    PROCEDURE RegisterAdministrator (
        p_person_id     IN NUMBER,
        p_audit_log_id  IN NUMBER
    );

    -- Función para obtener información básica de un administrador por su ID de persona.
    FUNCTION GetAdministratorBasicInfo (
        p_admin_person_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

    -- Procedimiento para crear un registro de reporte diario.
    PROCEDURE CreateDailyReportRecord (
        p_report_type   IN VARCHAR2,
        p_institution_id IN NUMBER,
        p_audit_log_id  IN NUMBER,
        po_new_report_id OUT NUMBER
    );

    -- Función para consultar entradas del LogBook (filtrado por patrón de descripción).
    FUNCTION QueryLogBookEntries (
        p_search_pattern IN VARCHAR2 DEFAULT NULL -- Patrón de búsqueda opcional
    ) RETURN SYS_REFCURSOR;

    -- Función para obtener los cambios de atributos para una entrada específica del LogBook.
    FUNCTION GetAttributeChangesForLogEntry (
        p_logbook_id IN NUMBER
    ) RETURN SYS_REFCURSOR;

-- =============================================================================
-- IX. PARAMETERS
-- =============================================================================
    -- Función para obtener el valor de un parámetro del sistema por su nombre.
    FUNCTION GetParameterValue (
        p_parameter_name IN VARCHAR2
    ) RETURN VARCHAR2;

    -- Función para obtener todos los parámetros del sistema.
    FUNCTION GetAllParameters
        RETURN SYS_REFCURSOR;

    -- Procedimiento para actualizar el valor de un parámetro del sistema.
    PROCEDURE UpdateParameterValue (
        p_parameter_name IN VARCHAR2,
        p_new_value      IN VARCHAR2,
        p_audit_log_id   IN NUMBER
    );

END PKG_ADMIN_TOOLS;
/
