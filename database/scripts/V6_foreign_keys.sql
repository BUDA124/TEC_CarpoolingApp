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
        l_constraint_name_upper VARCHAR2(128) := UPPER(p_constraint_name);
        l_table_name_upper      VARCHAR2(128) := UPPER(p_table_name);
        l_index_name_upper      VARCHAR2(128) := UPPER(p_index_name);
    BEGIN
        -- Check if constraint exists
        SELECT COUNT(*)
        INTO v_exists
        FROM user_constraints
        WHERE constraint_name = l_constraint_name_upper
          AND table_name = l_table_name_upper;

        IF v_exists = 0 THEN
            BEGIN
                EXECUTE IMMEDIATE '
                ALTER TABLE ' || DBMS_ASSERT.ENQUOTE_NAME(p_table_name, FALSE) || '
                    ADD CONSTRAINT ' || DBMS_ASSERT.ENQUOTE_NAME(p_constraint_name, FALSE) || '
                        FOREIGN KEY (' || DBMS_ASSERT.ENQUOTE_NAME(p_fk_column, FALSE) || ')
                            REFERENCES ' || DBMS_ASSERT.ENQUOTE_NAME(p_ref_table, FALSE) ||
                                  ' (' || DBMS_ASSERT.ENQUOTE_NAME(p_ref_column, FALSE) || ')';
                DBMS_OUTPUT.PUT_LINE('Constraint ' || p_constraint_name || ' added.');
            EXCEPTION
                WHEN OTHERS THEN
                    DBMS_OUTPUT.PUT_LINE('Error adding constraint ' || p_constraint_name || ': ' || SQLERRM);
            END;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Constraint ' || p_constraint_name || ' already existed.');
        END IF;

        -- Check if index exists
        SELECT COUNT(*)
        INTO v_exists
        FROM user_indexes
        WHERE index_name = l_index_name_upper
          AND table_name = l_table_name_upper;

        IF v_exists = 0 THEN
            BEGIN
                EXECUTE IMMEDIATE '
                 CREATE INDEX ' || DBMS_ASSERT.ENQUOTE_NAME(p_index_name, FALSE) || '
                 ON ' || DBMS_ASSERT.ENQUOTE_NAME(p_table_name, FALSE) || ' (' || DBMS_ASSERT.ENQUOTE_NAME(p_fk_column, FALSE) || ')
                 TABLESPACE ' || DBMS_ASSERT.ENQUOTE_NAME(v_index_tablespace, FALSE);
                DBMS_OUTPUT.PUT_LINE('Index ' || p_index_name || ' created in tablespace ' || v_index_tablespace || '.');
            EXCEPTION
                WHEN OTHERS THEN
                    DBMS_OUTPUT.PUT_LINE('Error creating index ' || p_index_name || ': ' || SQLERRM);
            END;
        ELSE
            DBMS_OUTPUT.PUT_LINE('Index ' || p_index_name || ' already existed.');
        END IF;

    EXCEPTION
        WHEN OTHERS THEN
            -- Catch potential errors in the SELECT statements or parameter handling
            DBMS_OUTPUT.PUT_LINE('Unhandled error in procedure for ' || p_constraint_name || ' or ' || p_index_name || ': ' || SQLERRM);
        -- RAISE;
    END add_fk_and_index_if_missing;

BEGIN
    DBMS_OUTPUT.ENABLE(NULL); -- Enable output buffer

    --Table DailyReport
    add_fk_and_index_if_missing(
            p_table_name        => 'DAILYREPORT',
            p_constraint_name   => 'FK_DAILYREPORT_INSTITUTION',
            p_fk_column         => 'IDINSTITUTION',
            p_ref_table         => 'INSTITUTION',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_DAILYREPORT_IDINSTITUTION'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'DAILYREPORT',
            p_constraint_name   => 'FK_DAILYREPORT_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_DAILYREPORT_IDAUDITLOG'
    );

    -- Table Institution
    add_fk_and_index_if_missing(
            p_table_name        => 'INSTITUTION',
            p_constraint_name   => 'FK_INST_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_INST_AUDITLOG'
    );

    -- Table ContactPhoneNumber
    add_fk_and_index_if_missing(
            p_table_name        => 'CONTACTPHONENUMBER',
            p_constraint_name   => 'FK_CPN_INSTITUTION',
            p_fk_column         => 'IDINSTITUTION',
            p_ref_table         => 'INSTITUTION',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CPN_INSTITUTION'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CONTACTPHONENUMBER',
            p_constraint_name   => 'FK_CPN_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CPN_AUDITLOG'
    );

    -- Table ContactEmail
    add_fk_and_index_if_missing(
            p_table_name        => 'CONTACTEMAIL',
            p_constraint_name   => 'FK_CE_INSTITUTION',
            p_fk_column         => 'IDINSTITUTION',
            p_ref_table         => 'INSTITUTION',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CE_INSTITUTION'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CONTACTEMAIL',
            p_constraint_name   => 'FK_CE_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CE_AUDITLOG'
    );

    -- Table Person
    add_fk_and_index_if_missing(
            p_table_name        => 'PERSON',
            p_constraint_name   => 'FK_PERSON_INSTITUTION',
            p_fk_column         => 'IDINSTITUTION',
            p_ref_table         => 'INSTITUTION',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PERSON_INSTITUTION'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PERSON',
            p_constraint_name   => 'FK_PERSON_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PERSON_AUDITLOG'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PERSON',
            p_constraint_name   => 'FK_PERSON_GENDER',
            p_fk_column         => 'IDGENDER',
            p_ref_table         => 'GENDER',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PERSON_AUDITLOG'
    );

    -- Table PhoneNumber
    add_fk_and_index_if_missing(
            p_table_name        => 'PHONENUMBER',
            p_constraint_name   => 'FK_PHONENUMBER_PERSON',
            p_fk_column         => 'IDPERSON',
            p_ref_table         => 'PERSON',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PHONENUMBER_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PHONENUMBER',
            p_constraint_name   => 'FK_PHONENUMBER_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PHONENUMBER_AUDITLOG'
    );

    -- Table Email
    add_fk_and_index_if_missing(
            p_table_name        => 'EMAIL',
            p_constraint_name   => 'FK_EMAIL_PERSON',
            p_fk_column         => 'IDPERSON',
            p_ref_table         => 'PERSON',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_EMAIL_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'EMAIL',
            p_constraint_name   => 'FK_EMAIL_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_EMAIL_AUDITLOG'
    );

    -- Table Credential
    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIAL',
            p_constraint_name   => 'FK_CRED_PERSON',
            p_fk_column         => 'IDPERSON',
            p_ref_table         => 'PERSON',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CRED_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIAL',
            p_constraint_name   => 'FK_CRED_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CRED_AUDITLOG'
    );

    -- Table TypeOfCredential
    add_fk_and_index_if_missing(
            p_table_name        => 'TYPEOFCREDENTIAL',
            p_constraint_name   => 'FK_TOC_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_TOC_AUDITLOG'
    );

    -- Table Gender
    add_fk_and_index_if_missing(
            p_table_name        => 'GENDER',
            p_constraint_name   => 'FK_GENDER_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_GENDER_AUDITLOG'
    );

    -- Table PERSONALUSER
    add_fk_and_index_if_missing(
            p_table_name        => 'PERSONALUSER',
            p_constraint_name   => 'FK_USER_PERSON',
            p_fk_column         => 'IDPERSON',
            p_ref_table         => 'PERSON',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_USER_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PERSONALUSER',
            p_constraint_name   => 'FK_USER_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_USER_AUDITLOG'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PERSONALUSER',
            p_constraint_name   => 'FK_USER_USERSTATUS',
            p_fk_column         => 'IDUSERSTATUS',
            p_ref_table         => 'USERSTATUS',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_USER_USERSTATUS'
    );

    -- Table USERSTATUS
    add_fk_and_index_if_missing(
            p_table_name        => 'USERSTATUS',
            p_constraint_name   => 'FK_USERSTATUS_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_USERSTATUS_AUDITLOG'
    );

    -- Table InstitutionalEmail
    add_fk_and_index_if_missing(
            p_table_name        => 'INSTITUTIONALEMAIL',
            p_constraint_name   => 'FK_INSTEMAIL_USER',
            p_fk_column         => 'IDUSER',
            p_ref_table         => 'PERSONALUSER',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_INSTEMAIL_USER'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'INSTITUTIONALEMAIL',
            p_constraint_name   => 'FK_INSTEMAIL_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_INSTEMAIL_AUDITLOG'
    );

    -- Table AccessStatus
    add_fk_and_index_if_missing(
            p_table_name        => 'ACCESSSTATUS',
            p_constraint_name   => 'FK_ACCSTS_ADMIN',
            p_fk_column         => 'IDADMINISTRATOR',
            p_ref_table         => 'ADMINISTRATOR',
            p_ref_column        => 'IDPERSON',
            p_index_name        => 'IX_ACCSTS_ADMIN'
    );
    add_fk_and_index_if_missing(
            p_table_name        => 'ACCESSSTATUS',
            p_constraint_name   => 'FK_ACCSTS_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_ACCSTS_AUDITLOG'
    );

    -- Table Driver
    add_fk_and_index_if_missing(
            p_table_name        => 'DRIVER',
            p_constraint_name   => 'FK_DRIVER_PERSON',
            p_fk_column         => 'IDPERSON',
            p_ref_table         => 'PERSON',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_DRIVER_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'DRIVER',
            p_constraint_name   => 'FK_DRIVER_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_DRIVER_AUDITLOG'
    );


    -- Table Vehicle
    add_fk_and_index_if_missing(
            p_table_name        => 'VEHICLE',
            p_constraint_name   => 'FK_VEHICLE_DRIVER',
            p_fk_column         => 'IDDRIVER',
            p_ref_table         => 'DRIVER',
            p_ref_column        => 'IDPERSON',
            p_index_name        => 'IX_VEHICLE_DRIVER'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'VEHICLE',
            p_constraint_name   => 'FK_VEHICLE_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_VEHICLE_AUDITLOG'
    );

    -- Table PASSENGER
    add_fk_and_index_if_missing(
            p_table_name        => 'PASSENGER',
            p_constraint_name   => 'FK_PASSENGER_PERSON',
            p_fk_column         => 'IDPERSON',
            p_ref_table         => 'PERSON',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PASSENGER_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PASSENGER',
            p_constraint_name   => 'FK_PASSENGER_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PASSENGER_AUDITLOG'
    );

    -- Table Trip
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIP',
            p_constraint_name   => 'FK_TRIP_DRIVER',
            p_fk_column         => 'IDDRIVER',
            p_ref_table         => 'DRIVER',
            p_ref_column        => 'IDPERSON',
            p_index_name        => 'IX_TRIP_DRIVER'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIP',
            p_constraint_name   => 'FK_TRIP_PRICESTATUS',
            p_fk_column         => 'IDPRICESTATUS',
            p_ref_table         => 'PRICESTATUS',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_TRIP_PRICESTATUS'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIP',
            p_constraint_name   => 'FK_TRIP_VEHICLE',
            p_fk_column         => 'IDVEHICLE',
            p_ref_table         => 'VEHICLE',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_TRIP_VEHICLE'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIP',
            p_constraint_name   => 'FK_TRIP_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_TRIP_AUDITLOG'
    );

    -- Table PRICESTATUS
    add_fk_and_index_if_missing(
            p_table_name        => 'PRICESTATUS',
            p_constraint_name   => 'FK_PRICESTATUS_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PRICESTATUS_AUDITLOG'
    );

    -- Table TripStatus
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPSTATUS',
            p_constraint_name   => 'FK_TRIPSTATUS_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_TRIPSTATUS_AUDITLOG'
    );

    -- Table Stop
    add_fk_and_index_if_missing(
            p_table_name        => 'STOP',
            p_constraint_name   => 'FK_STOP_DISTRICT',
            p_fk_column         => 'IDDISTRICT',
            p_ref_table         => 'DISTRICT',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_STOP_DISTRICT'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'STOP',
            p_constraint_name   => 'FK_STOP_STARTTRIP', -- Example name
            p_fk_column         => 'IDSTARTRIP',
            p_ref_table         => 'TRIP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_STOP_STARTTRIP'  -- Example name
    );
    add_fk_and_index_if_missing(
            p_table_name        => 'STOP',
            p_constraint_name   => 'FK_STOP_ENDTRIP',   -- Example name
            p_fk_column         => 'IDENDTRIP',
            p_ref_table         => 'TRIP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_STOP_ENDTRIP'    -- Example name
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'STOP',
            p_constraint_name   => 'FK_STOP_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_STOP_AUDITLOG'
    );

    -- Table CoordinateLocation
    add_fk_and_index_if_missing(
            p_table_name        => 'COORDINATELOCATION',
            p_constraint_name   => 'FK_COORDLOC_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_COORDLOC_AUDITLOG'
    );

    -- Table District
    add_fk_and_index_if_missing(
            p_table_name        => 'DISTRICT',
            p_constraint_name   => 'FK_DISTRICT_CANTON',
            p_fk_column         => 'IDCANTON',
            p_ref_table         => 'CANTON',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_DISTRICT_CANTON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'DISTRICT',
            p_constraint_name   => 'FK_DISTRICT_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_DISTRICT_AUDITLOG'
    );

    -- Table Canton
    add_fk_and_index_if_missing(
            p_table_name        => 'CANTON',
            p_constraint_name   => 'FK_CANTON_PROVINCE',
            p_fk_column         => 'IDPROVINCE',
            p_ref_table         => 'PROVINCE',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CANTON_PROVINCE'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CANTON',
            p_constraint_name   => 'FK_CANTON_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CANTON_AUDITLOG'
    );

    -- Table Province
    add_fk_and_index_if_missing(
            p_table_name        => 'PROVINCE',
            p_constraint_name   => 'FK_PROVINCE_COUNTRY',
            p_fk_column         => 'IDCOUNTRY',
            p_ref_table         => 'COUNTRY',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PROVINCE_COUNTRY'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PROVINCE',
            p_constraint_name   => 'FK_PROVINCE_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PROVINCE_AUDITLOG'
    );

    -- Table Country
    add_fk_and_index_if_missing(
            p_table_name        => 'COUNTRY',
            p_constraint_name   => 'FK_COUNTRY_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_COUNTRY_AUDITLOG'
    );

    -- Table PaymentMethod
    add_fk_and_index_if_missing(
            p_table_name        => 'PAYMENTMETHOD',
            p_constraint_name   => 'FK_PAYMETHOD_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PAYMETHOD_AUDITLOG'
    );

    -- Table Parameter
    add_fk_and_index_if_missing(
            p_table_name        => 'PARAMETER',
            p_constraint_name   => 'FK_PARAMETER_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PARAMETER_AUDITLOG'
    );

    -- Table LogBook
    add_fk_and_index_if_missing(
            p_table_name        => 'LOGBOOK',
            p_constraint_name   => 'FK_LOGBOOK_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_LOGBOOK_AUDITLOG'
    );

    -- Table EntityModified
    add_fk_and_index_if_missing(
            p_table_name        => 'ENTITYMODIFIED',
            p_constraint_name   => 'FK_ENTMOD_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_ENTMOD_AUDITLOG'
    );

    -- Table AttributeModified
    add_fk_and_index_if_missing(
            p_table_name        => 'ATTRIBUTEMODIFIED',
            p_constraint_name   => 'FK_ATTRMOD_ENTMOD',
            p_fk_column         => 'IDENTITYMODIFIED',
            p_ref_table         => 'ENTITYMODIFIED',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_ATTRMOD_ENTMOD'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ATTRIBUTEMODIFIED',
            p_constraint_name   => 'FK_ATTRMOD_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_ATTRMOD_AUDITLOG'
    );

    --------------------------
    -- FK De tablas NXN --
    --------------------------

    --Table TripReportDailyReport
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPREPORTDAILYREPORT',
            p_constraint_name   => 'FK_TRPDR_TRIP',
            p_fk_column         => 'IDTRIP',
            p_ref_table         => 'TRIP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_TRPDR_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPREPORTDAILYREPORT',
            p_constraint_name   => 'FK_TRPDR_DAILYREPORT',
            p_fk_column         => 'IDDAILYREPORT',
            p_ref_table         => 'DAILYREPORT',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_TRPDR_DAILYREPORT'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPREPORTDAILYREPORT',
            p_constraint_name   => 'FK_TRPDR_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_TRPDR_AUDITLOG'
    );

    --Tabla Administrator
    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINISTRATOR',
            p_constraint_name   => 'FK_ADMIN_PERSON',
            p_fk_column         => 'IDPERSON',
            p_ref_table         => 'PERSON',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_ADMIN_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINISTRATOR',
            p_constraint_name   => 'FK_ADMIN_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_ADMIN_AUDITLOG'
    );

    --Tabla AdminManagesInstitution
    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINMANAGEINSTITUTION',
            p_constraint_name   => 'FK_ADMNINS_ADMIN',
            p_fk_column         => 'IDPERSON',
            p_ref_table         => 'ADMINISTRATOR',
            p_ref_column        => 'IDPERSON',
            p_index_name        => 'IX_ADMNINS_PERSON'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINMANAGEINSTITUTION',
            p_constraint_name   => 'FK_ADMNINS_INST',
            p_fk_column         => 'IDINSTITUTION',
            p_ref_table         => 'INSTITUTION',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_ADMNINS_INST'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINMANAGEINSTITUTION',
            p_constraint_name   => 'FK_ADMNINS_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_ADMNINS_AUDITLOG'
    );

    -- Table AdminReceiveDailyReport
    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINRECEIVEDAILYREPORT',
            p_constraint_name   => 'FK_ADRDR_ADMIN',
            p_fk_column         => 'IDADMINISTRATOR',
            p_ref_table         => 'ADMINISTRATOR',
            p_ref_column        => 'IDPERSON',
            p_index_name        => 'IX_ADRDR_ADMIN'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINRECEIVEDAILYREPORT',
            p_constraint_name   => 'FK_ADRDR_DAILYREPORT',
            p_fk_column         => 'IDDAILYREPORT',
            p_ref_table         => 'DAILYREPORT',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_ADRDR_DAILYREPORT'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ADMINRECEIVEDAILYREPORT',
            p_constraint_name   => 'FK_ADRDR_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_ADRDR_AUDITLOG'
    );

    -- Table CredentialHasTypeOfCredential
    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIALHASTYPEOFCREDENTIAL',
            p_constraint_name   => 'FK_CHTC_CREDENTIAL',
            p_fk_column         => 'IDCREDENTIAL',
            p_ref_table         => 'CREDENTIAL',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CHTC_CREDENTIAL'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIALHASTYPEOFCREDENTIAL',
            p_constraint_name   => 'FK_CHTC_TYPEOFCREDENTIAL',
            p_fk_column         => 'IDTYPEOFCREDENTIAL',
            p_ref_table         => 'TYPEOFCREDENTIAL',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CHTC_TYPEOFCREDENTIAL'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'CREDENTIALHASTYPEOFCREDENTIAL',
            p_constraint_name   => 'FK_CHTC_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_CHTC_AUDITLOG'
    );

    --Table PASSENGERQUERYTRIP
    add_fk_and_index_if_missing(
            p_table_name        => 'PASSENGERQUERYTRIP',
            p_constraint_name   => 'FK_PQTRIP_USER',   -- Name references conceptual 'User'
            p_fk_column         => 'IDUSER',
            p_ref_table         => 'PERSONALUSER',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PQTRIP_USER'    -- Name references conceptual 'User'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PASSENGERQUERYTRIP',
            p_constraint_name   => 'FK_PQTRIP_TRIP',
            p_fk_column         => 'IDTRIP',
            p_ref_table         => 'TRIP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PQTRIP_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PASSENGERQUERYTRIP',
            p_constraint_name   => 'FK_PQTRIP_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PQTRIP_AUDITLOG'
    );

    --Table PASSENGERJOINTRIP
    add_fk_and_index_if_missing(
            p_table_name        => 'PASSENGERJOINTRIP',
            p_constraint_name   => 'FK_PJTRIP_USER',  -- Name references conceptual 'User'
            p_fk_column         => 'IDUSER',
            p_ref_table         => 'PERSONALUSER',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PJTRIP_USER'   -- Name references conceptual 'User'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PASSENGERJOINTRIP',
            p_constraint_name   => 'FK_PJTRIP_TRIP',
            p_fk_column         => 'IDTRIP',
            p_ref_table         => 'TRIP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PJTRIP_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'PASSENGERJOINTRIP',
            p_constraint_name   => 'FK_PJTRIP_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_PJTRIP_AUDITLOG'
    );

    --Table TripHasTripStatus
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASTRIPSTATUS',
            p_constraint_name   => 'FK_THTS_TRIP',
            p_fk_column         => 'IDTRIP',
            p_ref_table         => 'TRIP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_THTS_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASTRIPSTATUS',
            p_constraint_name   => 'FK_THTS_TRIPSTATUS',
            p_fk_column         => 'IDTRIPSTATUS',
            p_ref_table         => 'TRIPSTATUS',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_THTS_TRIPSTATUS'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASTRIPSTATUS',
            p_constraint_name   => 'FK_THTS_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_THTS_AUDITLOG'
    );

    --Table StopHasCoordinateLocation
    add_fk_and_index_if_missing(
            p_table_name        => 'STOPHASCOORDINATELOCATION',
            p_constraint_name   => 'FK_SHCL_STOP',
            p_fk_column         => 'IDSTOP',
            p_ref_table         => 'STOP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_SHCL_STOP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'STOPHASCOORDINATELOCATION',
            p_constraint_name   => 'FK_SHCL_COORDLOC',
            p_fk_column         => 'IDCOORDINATELOCATION',
            p_ref_table         => 'COORDINATELOCATION',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_SHCL_COORDLOC'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'STOPHASCOORDINATELOCATION',
            p_constraint_name   => 'FK_SHCL_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_SHCL_AUDITLOG'
    );

    --Table TripHasStopHasPaymentMethod
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOPHASPAYMENTMETHOD',
            p_constraint_name   => 'FK_THSHPM_PAYMETHOD',
            p_fk_column         => 'IDPAYMENTMETHOD',
            p_ref_table         => 'PAYMENTMETHOD',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_THSHPM_PAYMETHOD'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOPHASPAYMENTMETHOD',
            p_constraint_name   => 'FK_THSHPM_TRIP',
            p_fk_column         => 'IDTRIP',
            p_ref_table         => 'TRIP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_THSHPM_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOPHASPAYMENTMETHOD',
            p_constraint_name   => 'FK_THSHPM_STOP',
            p_fk_column         => 'IDSTOP',
            p_ref_table         => 'STOP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_THSHPM_STOP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOPHASPAYMENTMETHOD',
            p_constraint_name   => 'FK_THSHPM_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_THSHPM_AUDITLOG'
    );

    --Table TripHasStop
    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOP',
            p_constraint_name   => 'FK_THS_TRIP',
            p_fk_column         => 'IDTRIP',
            p_ref_table         => 'TRIP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_THS_TRIP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOP',
            p_constraint_name   => 'FK_THS_STOP',
            p_fk_column         => 'IDSTOP',
            p_ref_table         => 'STOP',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_THS_STOP'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'TRIPHASSTOP',
            p_constraint_name   => 'FK_THS_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_THS_AUDITLOG'
    );

    --Table LogBookHasEntityModified
    add_fk_and_index_if_missing(
            p_table_name        => 'LOGBOOKHASENTITYMODIFIED',
            p_constraint_name   => 'FK_LBHEM_LOGBOOK',
            p_fk_column         => 'IDLOGBOOK',
            p_ref_table         => 'LOGBOOK',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_LBHEM_LOGBOOK'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'LOGBOOKHASENTITYMODIFIED',
            p_constraint_name   => 'FK_LBHEM_ENTMOD',
            p_fk_column         => 'IDENTITYMODIFIED',
            p_ref_table         => 'ENTITYMODIFIED',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_LBHEM_ENTMOD'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'LOGBOOKHASENTITYMODIFIED',
            p_constraint_name   => 'FK_LBHEM_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_LBHEM_AUDITLOG'
    );

    -- Table ATTRMODHASENTMOD
    add_fk_and_index_if_missing(
            p_table_name        => 'ATTRMODHASENTMOD',
            p_constraint_name   => 'FK_AMHEM_LOGBOOK',
            p_fk_column         => 'IDLOGBOOK',
            p_ref_table         => 'LOGBOOK',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_AMHEM_LOGBOOK'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ATTRMODHASENTMOD',
            p_constraint_name   => 'FK_AMHEM_ATTRMOD',
            p_fk_column         => 'IDATTRIBUTEMODIFIED',
            p_ref_table         => 'ATTRIBUTEMODIFIED',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_AMHEM_ATTRMOD'
    );

    add_fk_and_index_if_missing(
            p_table_name        => 'ATTRMODHASENTMOD',
            p_constraint_name   => 'FK_AMHEM_AUDITLOG',
            p_fk_column         => 'IDAUDITLOG',
            p_ref_table         => 'AUDITLOG',
            p_ref_column        => 'ID',
            p_index_name        => 'IX_AMHEM_AUDITLOG'
    );

    DBMS_OUTPUT.PUT_LINE('Foreign key and index check/creation process completed.');

END;
/