DECLARE
    v_index_tablespace VARCHAR2(30) := 'CARPOOLING_INDX';

    PROCEDURE add_fk_and_index_if_missing (
        p_table_name        IN VARCHAR2,
        p_constraint_name   IN VARCHAR2,
        p_fk_column         IN VARCHAR2,
        p_ref_table         IN VARCHAR2,
        p_ref_column        IN VARCHAR2,
        p_index_name        IN VARCHAR2
    ) IS
        v_exists NUMBER;
    BEGIN
        SELECT COUNT(*)
        INTO v_exists
        FROM user_constraints
        WHERE constraint_name = p_constraint_name
          AND table_name = p_table_name;

        IF v_exists = 0 THEN
            EXECUTE IMMEDIATE '
            ALTER TABLE ' || DBMS_ASSERT.ENQUOTE_NAME(p_table_name, FALSE) || '
                ADD CONSTRAINT ' || DBMS_ASSERT.ENQUOTE_NAME(p_constraint_name, FALSE) || '
                    FOREIGN KEY (' || DBMS_ASSERT.ENQUOTE_NAME(p_fk_column, FALSE) || ')
                        REFERENCES ' || DBMS_ASSERT.ENQUOTE_NAME(p_ref_table, FALSE) ||
                              ' (' || DBMS_ASSERT.ENQUOTE_NAME(p_ref_column, FALSE) || ')';
            DBMS_OUTPUT.PUT_LINE('Constraint ' || p_constraint_name || ' added.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Constraint ' || p_constraint_name || ' already existed.');
        END IF;

        SELECT COUNT(*)
        INTO v_exists
        FROM user_indexes
        WHERE index_name = p_index_name
          AND table_name = p_table_name;

        IF v_exists = 0 THEN
            EXECUTE IMMEDIATE '
            CREATE INDEX ' || DBMS_ASSERT.ENQUOTE_NAME(p_index_name, FALSE) || '
            ON ' || DBMS_ASSERT.ENQUOTE_NAME(p_table_name, FALSE) || ' (' || DBMS_ASSERT.ENQUOTE_NAME(p_fk_column, FALSE) || ')
            TABLESPACE ' || DBMS_ASSERT.ENQUOTE_NAME(v_index_tablespace, FALSE);
            DBMS_OUTPUT.PUT_LINE('Index ' || p_index_name || ' created in tablespace ' || v_index_tablespace || '.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Index ' || p_index_name || ' already existed.');
        END IF;

    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error processing ' || p_constraint_name || ' or ' || p_index_name || ': ' || SQLERRM);
    END add_fk_and_index_if_missing;

BEGIN
    --Table DailyReport
    add_fk_and_index_if_missing(
            p_table_name        => 'DAILYREPORT',
            p_constraint_name   => 'FK_DAILYREPORT_INSTITUTION',
            p_fk_column         => 'idInstitution',
            p_ref_table         => 'Institution',
            p_ref_column        => 'id',
            p_index_name        => 'IX_DAILYREPORT_IDINSTITUTION'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'DAILYREPORT', --Donde pertenece la llave
            p_constraint_name   => 'FK_DAILYREPORT_AUDITLOG', --Tabla donde pertenece a donde va
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_DAILYREPORT_IDAUDITLOG' --Nombre de tabla _ Nombre Foreign key
    );

    -- Table Institution
    add_fk_and_index_if_missing(
            p_table_name        => 'INSTITUTION',
            p_constraint_name   => 'FK_INST_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_INST_AUDITLOG'
    );

-- Table ContactPhoneNumber
    add_fk_and_index_if_missing(
            p_table_name        => 'CONTACTPHONENUMBER',
            p_constraint_name   => 'FK_CPN_INSTITUTION',
            p_fk_column         => 'idInstitution',
            p_ref_table         => 'Institution',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CPN_INSTITUTION'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CONTACTPHONENUMBER',
            p_constraint_name   => 'FK_CPN_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CPN_AUDITLOG'
    );

-- Table ContactEmail
    add_fk_and_index_if_missing(
            p_table_name        => 'CONTACTEMAIL',
            p_constraint_name   => 'FK_CE_INSTITUTION',
            p_fk_column         => 'idInstitution',
            p_ref_table         => 'Institution',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CE_INSTITUTION'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CONTACTEMAIL',
            p_constraint_name   => 'FK_CE_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CE_AUDITLOG'
    );

    -- Table Person
    add_fk_and_index_if_missing(
            p_table_name        => 'PERSON',
            p_constraint_name   => 'FK_PERSON_INSTITUTION',
            p_fk_column         => 'idInstitution',
            p_ref_table         => 'Institution',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PERSON_INSTITUTION'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PERSON',
            p_constraint_name   => 'FK_PERSON_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PERSON_AUDITLOG'
    );

-- Table PhoneNumber
    add_fk_and_index_if_missing(
            p_table_name        => 'PHONENUMBER',
            p_constraint_name   => 'FK_PHONENUMBER_PERSON',
            p_fk_column         => 'idPerson',
            p_ref_table         => 'Person',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PHONENUMBER_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PHONENUMBER',
            p_constraint_name   => 'FK_PHONENUMBER_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PHONENUMBER_AUDITLOG'
    );

-- Table Email
    add_fk_and_index_if_missing(
            p_table_name        => 'EMAIL',
            p_constraint_name   => 'FK_EMAIL_PERSON',
            p_fk_column         => 'idPerson',
            p_ref_table         => 'Person',
            p_ref_column        => 'id',
            p_index_name        => 'IX_EMAIL_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'EMAIL',
            p_constraint_name   => 'FK_EMAIL_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_EMAIL_AUDITLOG'
    );

    -- Table Credential
    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIAL',
            p_constraint_name   => 'FK_CRED_PERSON',
            p_fk_column         => 'idPerson',
            p_ref_table         => 'Person',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CRED_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIAL',
            p_constraint_name   => 'FK_CRED_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CRED_AUDITLOG'
    );

-- Table TypeOfCredential
    add_fk_and_index_if_missing(
            p_table_name        => 'TYPEOFCREDENTIAL',
            p_constraint_name   => 'FK_TOC_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_TOC_AUDITLOG'
    );

-- Table Credential
    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIAL',
            p_constraint_name   => 'FK_CRED_PERSON',
            p_fk_column         => 'idPerson',
            p_ref_table         => 'Person',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CRED_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIAL',
            p_constraint_name   => 'FK_CRED_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CRED_AUDITLOG'
    );

-- Table TypeOfCredential
    add_fk_and_index_if_missing(
            p_table_name        => 'TYPEOFCREDENTIAL',
            p_constraint_name   => 'FK_TOC_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_TOC_AUDITLOG'
    );

    -- Table Gender
    add_fk_and_index_if_missing(
            p_table_name        => 'GENDER',
            p_constraint_name   => 'FK_GENDER_PERSON',
            p_fk_column         => 'idPerson',
            p_ref_table         => 'Person',
            p_ref_column        => 'id',
            p_index_name        => 'IX_GENDER_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'GENDER',
            p_constraint_name   => 'FK_GENDER_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_GENDER_AUDITLOG'
    );

-- Table User
    add_fk_and_index_if_missing(
            p_table_name        => 'USER',
            p_constraint_name   => 'FK_USER_PERSON',
            p_fk_column         => 'idPerson',
            p_ref_table         => 'Person',
            p_ref_column        => 'id',
            p_index_name        => 'IX_USER_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'USER',
            p_constraint_name   => 'FK_USER_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_USER_AUDITLOG'
    );

-- Table InstitutionalEmail
    add_fk_and_index_if_missing(
            p_table_name        => 'INSTITUTIONALEMAIL',
            p_constraint_name   => 'FK_INSTEMAIL_USER',
            p_fk_column         => 'idUser',
            p_ref_table         => 'User',
            p_ref_column        => 'id',
            p_index_name        => 'IX_INSTEMAIL_USER'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'INSTITUTIONALEMAIL',
            p_constraint_name   => 'FK_INSTEMAIL_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_INSTEMAIL_AUDITLOG'
    );

    -- Table Vehicle
    add_fk_and_index_if_missing(
            p_table_name        => 'VEHICLE',
            p_constraint_name   => 'FK_VEHICLE_DRIVER',
            p_fk_column         => 'idDriver',
            p_ref_table         => 'Driver',
            p_ref_column        => 'idPerson',
            p_index_name        => 'IX_VEHICLE_DRIVER'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'VEHICLE',
            p_constraint_name   => 'FK_VEHICLE_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_VEHICLE_AUDITLOG'
    );

    -- Table Trip
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIP',
            p_constraint_name   => 'FK_TRIP_DRIVER',
            p_fk_column         => 'idDriver',
            p_ref_table         => 'Driver',
            p_ref_column        => 'idPerson',
            p_index_name        => 'IX_TRIP_DRIVER'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIP',
            p_constraint_name   => 'FK_TRIP_PRICE',
            p_fk_column         => 'idPrice',
            p_ref_table         => 'Price',
            p_ref_column        => 'id',
            p_index_name        => 'IX_TRIP_PRICE'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIP',
            p_constraint_name   => 'FK_TRIP_VEHICLE',
            p_fk_column         => 'idVehicle',
            p_ref_table         => 'Vehicle',
            p_ref_column        => 'id',
            p_index_name        => 'IX_TRIP_VEHICLE'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIP',
            p_constraint_name   => 'FK_TRIP_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_TRIP_AUDITLOG'
    );

    -- Table Price
    add_fk_and_index_if_missing(
            p_table_name        => 'PRICE',
            p_constraint_name   => 'FK_PRICE_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PRICE_AUDITLOG'
    );

    -- Table TripStatus
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPSTATUS',
            p_constraint_name   => 'FK_TRIPSTATUS_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_TRIPSTATUS_AUDITLOG'
    );

    -- Table Stop
    add_fk_and_index_if_missing(
            p_table_name        => 'STOP',
            p_constraint_name   => 'FK_STOP_DISTRICT',
            p_fk_column         => 'idDistrict',
            p_ref_table         => 'District',
            p_ref_column        => 'id',
            p_index_name        => 'IX_STOP_DISTRICT'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'STOP',
            p_constraint_name   => 'FK_STOP_TRIP',
            p_fk_column         => 'idTrip',
            p_ref_table         => 'Trip',
            p_ref_column        => 'id',
            p_index_name        => 'IX_STOP_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'STOP',
            p_constraint_name   => 'FK_STOP_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_STOP_AUDITLOG'
    );

    -- Table CoordinateLocation
    add_fk_and_index_if_missing(
            p_table_name        => 'COORDINATELOCATION',
            p_constraint_name   => 'FK_COORDLOC_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_COORDLOC_AUDITLOG'
    );

    -- Table District
    add_fk_and_index_if_missing(
            p_table_name        => 'DISTRICT',
            p_constraint_name   => 'FK_DISTRICT_CANTON',
            p_fk_column         => 'idCanton',
            p_ref_table         => 'Canton',
            p_ref_column        => 'id',
            p_index_name        => 'IX_DISTRICT_CANTON'
    );

    -- Table District
    add_fk_and_index_if_missing(
            p_table_name        => 'DISTRICT',
            p_constraint_name   => 'FK_DISTRICT_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_DISTRICT_AUDITLOG'
    );

    -- Table Canton
    add_fk_and_index_if_missing(
            p_table_name        => 'CANTON',
            p_constraint_name   => 'FK_CANTON_PROVINCE',
            p_fk_column         => 'idProvince',
            p_ref_table         => 'Province',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CANTON_PROVINCE'
    );

    -- Table Canton
    add_fk_and_index_if_missing(
            p_table_name        => 'CANTON',
            p_constraint_name   => 'FK_CANTON_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CANTON_AUDITLOG'
    );

    -- Table Province
    add_fk_and_index_if_missing(
            p_table_name        => 'PROVINCE',
            p_constraint_name   => 'FK_PROVINCE_COUNTRY',
            p_fk_column         => 'idCountry',
            p_ref_table         => 'Country',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PROVINCE_COUNTRY'
    );

    -- Table Province
    add_fk_and_index_if_missing(
            p_table_name        => 'PROVINCE',
            p_constraint_name   => 'FK_PROVINCE_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PROVINCE_AUDITLOG'
    );

    -- Table Country
    add_fk_and_index_if_missing(
            p_table_name        => 'COUNTRY',
            p_constraint_name   => 'FK_COUNTRY_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_COUNTRY_AUDITLOG'
    );

    -- Table PaymentMethod
    add_fk_and_index_if_missing(
            p_table_name        => 'PAYMENTMETHOD',
            p_constraint_name   => 'FK_PAYMETHOD_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PAYMETHOD_AUDITLOG'
    );

    -- Table Parameter
    add_fk_and_index_if_missing(
            p_table_name        => 'PARAMETER',
            p_constraint_name   => 'FK_PARAMETER_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PARAMETER_AUDITLOG'
    );

    -- Table LogBook
    add_fk_and_index_if_missing(
            p_table_name        => 'LOGBOOK',
            p_constraint_name   => 'FK_LOGBOOK_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_LOGBOOK_AUDITLOG'
    );

    -- Table EntityModified
    add_fk_and_index_if_missing(
            p_table_name        => 'ENTITYMODIFIED',
            p_constraint_name   => 'FK_ENTMOD_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_ENTMOD_AUDITLOG'
    );

    -- Table AttributeModified
    add_fk_and_index_if_missing(
            p_table_name        => 'ATTRIBUTEMODIFIED',
            p_constraint_name   => 'FK_ATTRMOD_ENTMOD',
            p_fk_column         => 'idEntityModified',
            p_ref_table         => 'EntityModified',
            p_ref_column        => 'id',
            p_index_name        => 'IX_ATTRMOD_ENTMOD'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ATTRIBUTEMODIFIED',
            p_constraint_name   => 'FK_ATTRMOD_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_ATTRMOD_AUDITLOG'
    );

    --FK De tablas NXN

    --Tabla TripReportDailyReport
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPREPORTDAILYREPORT',
            p_constraint_name   => 'FK_TRPDR_TRIP',
            p_fk_column         => 'idTrip',
            p_ref_table         => 'Trip',
            p_ref_column        => 'id',
            p_index_name        => 'IX_TRPDR_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPREPORTDAILYREPORT',
            p_constraint_name   => 'FK_TRPDR_DAILYREPORT',
            p_fk_column         => 'idDailyReport',
            p_ref_table         => 'DailyReport',
            p_ref_column        => 'id',
            p_index_name        => 'IX_TRPDR_DAILYREPORT'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPREPORTDAILYREPORT',
            p_constraint_name   => 'FK_TRPDR_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_TRPDR_AUDITLOG'
    );


    --Tabla Administrator
    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINISTRATOR',
            p_constraint_name   => 'FK_ADMIN_PERSON',
            p_fk_column         => 'idPerson',
            p_ref_table         => 'Person',
            p_ref_column        => 'id',
            p_index_name        => 'IX_ADMIN_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINISTRATOR',
            p_constraint_name   => 'FK_ADMIN_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_ADMIN_AUDITLOG'
    );

    --Tabla AdminManagesInstitution
    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINMANAGEINSTITUTION',
            p_constraint_name   => 'FK_ADMNINS_ADMIN',
            p_fk_column         => 'idPerson',
            p_ref_table         => 'Administrator',
            p_ref_column        => 'idPerson',
            p_index_name        => 'IX_ADMNINS_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINMANAGEINSTITUTION',
            p_constraint_name   => 'FK_ADMNINS_INST',
            p_fk_column         => 'idInstitution',
            p_ref_table         => 'Institution',
            p_ref_column        => 'id',
            p_index_name        => 'IX_ADMNINS_INST'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINMANAGEINSTITUTION',
            p_constraint_name   => 'FK_ADMNINS_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_ADMNINS_AUDITLOG'
    );

    -- Table AdminRecievesDailyReport
    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINRECEIVEDAIILYREPORT',
            p_constraint_name   => 'FK_ADRDR_ADMIN',
            p_fk_column         => 'idAdministrator',
            p_ref_table         => 'Administrator',
            p_ref_column        => 'idPerson',
            p_index_name        => 'IX_ADRDR_ADMIN'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINRECEIVEDAIILYREPORT',
            p_constraint_name   => 'FK_ADRDR_DAILYREPORT',
            p_fk_column         => 'idDailyReport',
            p_ref_table         => 'DailyReport',
            p_ref_column        => 'id',
            p_index_name        => 'IX_ADRDR_DAILYREPORT'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINRECEIVEDAIILYREPORT',
            p_constraint_name   => 'FK_ADRDR_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_ADRDR_AUDITLOG'
    );

    -- Table CredentialHasTypeOfCredential
    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIALHASTYPEOFCREDENTIAL',
            p_constraint_name   => 'FK_CHTC_CREDENTIAL',
            p_fk_column         => 'idCredential',
            p_ref_table         => 'Credential',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CHTC_CREDENTIAL'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIALHASTYPEOFCREDENTIAL',
            p_constraint_name   => 'FK_CHTC_TYPEOFCREDENTIAL',
            p_fk_column         => 'idTypeOfCredential',
            p_ref_table         => 'TypeOfCredential',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CHTC_TYPEOFCREDENTIAL'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIALHASTYPEOFCREDENTIAL',
            p_constraint_name   => 'FK_CHTC_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_CHTC_AUDITLOG'
    );

    --Table Driver
    add_fk_and_index_if_missing(
            p_table_name        => 'DRIVER',
            p_constraint_name   => 'FK_DRIVER_PERSON',
            p_fk_column         => 'idPerson',
            p_ref_table         => 'Person',
            p_ref_column        => 'id',
            p_index_name        => 'IX_DRIVER_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'DRIVER',
            p_constraint_name   => 'FK_DRIVER_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_DRIVER_AUDITLOG'
    );

    -- Table Passanger
    add_fk_and_index_if_missing(
            p_table_name        => 'PASSANGER',
            p_constraint_name   => 'FK_PASSANGER_PERSON',
            p_fk_column         => 'idPerson',
            p_ref_table         => 'Person',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PASSANGER_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PASSANGER',
            p_constraint_name   => 'FK_PASSANGER_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PASSANGER_AUDITLOG'
    );

    --Table PassangerQueryTrip
    add_fk_and_index_if_missing(
            p_table_name        => 'PASSANGERQUERYTRIP',
            p_constraint_name   => 'FK_PQTRIP_USER',
            p_fk_column         => 'idUser',
            p_ref_table         => 'User',
            p_ref_column        => 'idPerson',
            p_index_name        => 'IX_PQTRIP_USER'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PASSANGERQUERYTRIP',
            p_constraint_name   => 'FK_PQTRIP_TRIP',
            p_fk_column         => 'idTrip',
            p_ref_table         => 'Trip',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PQTRIP_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PASSANGERQUERYTRIP',
            p_constraint_name   => 'FK_PQTRIP_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PQTRIP_AUDITLOG'
    );

    --Table PassangerJoinTrip
    add_fk_and_index_if_missing(
            p_table_name        => 'PASSANGERJOINTRIP',
            p_constraint_name   => 'FK_PJTRIP_USER',
            p_fk_column         => 'idUser',
            p_ref_table         => 'User',
            p_ref_column        => 'idPerson',
            p_index_name        => 'IX_PJTRIP_USER'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PASSANGERJOINTRIP',
            p_constraint_name   => 'FK_PJTRIP_TRIP',
            p_fk_column         => 'idTrip',
            p_ref_table         => 'Trip',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PJTRIP_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PASSANGERJOINTRIP',
            p_constraint_name   => 'FK_PJTRIP_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_PJTRIP_AUDITLOG'
    );

    --Table TripHasTripStatus
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASTRIPSTATUS',
            p_constraint_name   => 'FK_THTS_TRIP',
            p_fk_column         => 'idTrip',
            p_ref_table         => 'Trip',
            p_ref_column        => 'id',
            p_index_name        => 'IX_THTS_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASTRIPSTATUS',
            p_constraint_name   => 'FK_THTS_TRIPSTATUS',
            p_fk_column         => 'idTripStatus',
            p_ref_table         => 'TripStatus',
            p_ref_column        => 'id',
            p_index_name        => 'IX_THTS_TRIPSTATUS'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASTRIPSTATUS',
            p_constraint_name   => 'FK_THTS_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_THTS_AUDITLOG'
    );

    --Table StopHasCoordinateLocation
    add_fk_and_index_if_missing(
            p_table_name        => 'STOPHASCOORDINATELOCATION',
            p_constraint_name   => 'FK_SHCL_STOP',
            p_fk_column         => 'idStop',
            p_ref_table         => 'Stop',
            p_ref_column        => 'id',
            p_index_name        => 'IX_SHCL_STOP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'STOPHASCOORDINATELOCATION',
            p_constraint_name   => 'FK_SHCL_COORDLOC',
            p_fk_column         => 'idCoordinateLocation',
            p_ref_table         => 'CoordinateLocation',
            p_ref_column        => 'id',
            p_index_name        => 'IX_SHCL_COORDLOC'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'STOPHASCOORDINATELOCATION',
            p_constraint_name   => 'FK_SHCL_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_SHCL_AUDITLOG'
    );

    --Table TripHasStopHasPaymenthMethod
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOPHASPAYMENTMETHOD',
            p_constraint_name   => 'FK_THSHPM_PAYMETHOD',
            p_fk_column         => 'idPaymentMethod',
            p_ref_table         => 'PaymentMethod',
            p_ref_column        => 'id',
            p_index_name        => 'IX_THSHPM_PAYMETHOD'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOPHASPAYMENTMETHOD',
            p_constraint_name   => 'FK_THSHPM_TRIP',
            p_fk_column         => 'idTrip',
            p_ref_table         => 'Trip',
            p_ref_column        => 'id',
            p_index_name        => 'IX_THSHPM_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOPHASPAYMENTMETHOD',
            p_constraint_name   => 'FK_THSHPM_STOP',
            p_fk_column         => 'idStop',
            p_ref_table         => 'Stop',
            p_ref_column        => 'id',
            p_index_name        => 'IX_THSHPM_STOP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOPHASPAYMENTMETHOD',
            p_constraint_name   => 'FK_THSHPM_AUDITLOG',
            p_fk_column         => 'AuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_THSHPM_AUDITLOG'
    );

    --Table TripHasStop
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOP',
            p_constraint_name   => 'FK_THS_TRIP',
            p_fk_column         => 'idTrip',
            p_ref_table         => 'Trip',
            p_ref_column        => 'id',
            p_index_name        => 'IX_THS_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOP',
            p_constraint_name   => 'FK_THS_STOP',
            p_fk_column         => 'idStop',
            p_ref_table         => 'Stop',
            p_ref_column        => 'id',
            p_index_name        => 'IX_THS_STOP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOP',
            p_constraint_name   => 'FK_THS_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_THS_AUDITLOG'
    );

    --Table LogBookHasEntityModified
    add_fk_and_index_if_missing(
            p_table_name        => 'LOGBOOKHASENTITYMODIFIED',
            p_constraint_name   => 'FK_LBHEM_LOGBOOK',
            p_fk_column         => 'idLogBook',
            p_ref_table         => 'LogBook',
            p_ref_column        => 'id',
            p_index_name        => 'IX_LBHEM_LOGBOOK'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'LOGBOOKHASENTITYMODIFIED',
            p_constraint_name   => 'FK_LBHEM_ENTMOD',
            p_fk_column         => 'idEntityModified',
            p_ref_table         => 'EntityModified',
            p_ref_column        => 'id',
            p_index_name        => 'IX_LBHEM_ENTMOD'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'LOGBOOKHASENTITYMODIFIED',
            p_constraint_name   => 'FK_LBHEM_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_LBHEM_AUDITLOG'
    );

    -- Table ATTRMODHASENTMOD

    add_fk_and_index_if_missing(
            p_table_name        => 'ATTRMODHASENTMOD',
            p_constraint_name   => 'FK_AMHEM_LOGBOOK',
            p_fk_column         => 'idLogBook',
            p_ref_table         => 'LogBook',
            p_ref_column        => 'id',
            p_index_name        => 'IX_AMHEM_LOGBOOK'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ATTRMODHASENTMOD',
            p_constraint_name   => 'FK_AMHEM_ATTRMOD',
            p_fk_column         => 'idAttributeModified',
            p_ref_table         => 'AttributeModified',
            p_ref_column        => 'id',
            p_index_name        => 'IX_AMHEM_ATTRMOD'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ATTRMODHASENTMOD',
            p_constraint_name   => 'FK_AMHEM_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_AMHEM_AUDITLOG'
    );

END;
/