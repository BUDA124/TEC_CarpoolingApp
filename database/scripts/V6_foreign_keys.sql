-- Switch to the target database
USE CARPOOLING_DB;

-- Drop the procedure if it already exists to allow re-creation
DROP PROCEDURE IF EXISTS AddForeignKeyAndIndexIfNotExists;

DELIMITER //

CREATE PROCEDURE AddForeignKeyAndIndexIfNotExists (
    IN p_table_name        VARCHAR(128),
    IN p_constraint_name   VARCHAR(128),
    IN p_fk_column         VARCHAR(128),
    IN p_ref_table         VARCHAR(128),
    IN p_ref_column        VARCHAR(128),
    IN p_index_name        VARCHAR(128)
)
BEGIN
    DECLARE v_constraint_exists INT DEFAULT 0;
        DECLARE v_index_exists INT DEFAULT 0;
        DECLARE current_db_name VARCHAR(64);

        -- Handler for SQL exceptions
        DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
        @p1 = MYSQL_ERRNO, @p2 = MESSAGE_TEXT;
    END;

    SELECT DATABASE() INTO current_db_name;

    -- Check if constraint exists
    SELECT COUNT(*)
    INTO v_constraint_exists
    FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
    WHERE CONSTRAINT_SCHEMA = current_db_name
      AND TABLE_NAME = p_table_name
      AND CONSTRAINT_NAME = p_constraint_name
      AND CONSTRAINT_TYPE = 'FOREIGN KEY';

    IF v_constraint_exists = 0 THEN
        SET @sql_stmt_fk = CONCAT('ALTER TABLE `', p_table_name, '` ',
                                  'ADD CONSTRAINT `', p_constraint_name, '` ',
                                  'FOREIGN KEY (`', p_fk_column, '`) ',
                                  'REFERENCES `', p_ref_table, '` (`', p_ref_column, '`)');
        PREPARE stmt_fk FROM @sql_stmt_fk;
        EXECUTE stmt_fk;
        DEALLOCATE PREPARE stmt_fk;
        SELECT CONCAT('Constraint ', p_constraint_name, ' added to table ', p_table_name, '.') AS OperationStatus;
    ELSE
        SELECT CONCAT('Constraint ', p_constraint_name, ' already exists on table ', p_table_name, '.') AS OperationStatus;
    END IF;

    SELECT COUNT(*)
    INTO v_index_exists
    FROM INFORMATION_SCHEMA.STATISTICS
    WHERE TABLE_SCHEMA = current_db_name
      AND TABLE_NAME = p_table_name
      AND INDEX_NAME = p_index_name;

    IF v_index_exists = 0 THEN
        SET @sql_stmt_idx = CONCAT('CREATE INDEX `', p_index_name, '` ',
                                   'ON `', p_table_name, '` (`', p_fk_column, '`)');
        PREPARE stmt_idx FROM @sql_stmt_idx;
        EXECUTE stmt_idx;
        DEALLOCATE PREPARE stmt_idx;
        SELECT CONCAT('Index ', p_index_name, ' created on table ', p_table_name, ' for column ', p_fk_column, '.') AS OperationStatus;
    ELSE
        SELECT CONCAT('Index ', p_index_name, ' already exists on table ', p_table_name, '.') AS OperationStatus;
    END IF;

END //

DELIMITER ;

-- Table DailyReport
CALL AddForeignKeyAndIndexIfNotExists('DailyReport', 'FK_DAILYREPORT_INSTITUTION', 'idInstitution', 'Institution', 'id', 'IX_DAILYREPORT_IDINSTITUTION');
CALL AddForeignKeyAndIndexIfNotExists('DailyReport', 'FK_DAILYREPORT_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_DAILYREPORT_IDAUDITLOG');

-- Table Institution
CALL AddForeignKeyAndIndexIfNotExists('Institution', 'FK_INST_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_INST_AUDITLOG');

-- Table ContactPhoneNumber
CALL AddForeignKeyAndIndexIfNotExists('ContactPhoneNumber', 'FK_CPN_INSTITUTION', 'idInstitution', 'Institution', 'id', 'IX_CPN_INSTITUTION');
CALL AddForeignKeyAndIndexIfNotExists('ContactPhoneNumber', 'FK_CPN_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_CPN_AUDITLOG');

-- Table ContactEmail
CALL AddForeignKeyAndIndexIfNotExists('ContactEmail', 'FK_CE_INSTITUTION', 'idInstitution', 'Institution', 'id', 'IX_CE_INSTITUTION');
CALL AddForeignKeyAndIndexIfNotExists('ContactEmail', 'FK_CE_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_CE_AUDITLOG');

-- Table Person
CALL AddForeignKeyAndIndexIfNotExists('Person', 'FK_PERSON_INSTITUTION', 'idInstitution', 'Institution', 'id', 'IX_PERSON_IDINSTITUTION');
CALL AddForeignKeyAndIndexIfNotExists('Person', 'FK_PERSON_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_PERSON_IDAUDITLOG');
CALL AddForeignKeyAndIndexIfNotExists('Person', 'FK_PERSON_GENDER', 'idGender', 'Gender', 'id', 'IX_PERSON_IDGENDER'); -- Corrected index name

-- Table PhoneNumber
CALL AddForeignKeyAndIndexIfNotExists('PhoneNumber', 'FK_PHONENUMBER_PERSON', 'idPerson', 'Person', 'id', 'IX_PHONENUMBER_IDPERSON');
CALL AddForeignKeyAndIndexIfNotExists('PhoneNumber', 'FK_PHONENUMBER_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_PHONENUMBER_IDAUDITLOG');

-- Table Email
CALL AddForeignKeyAndIndexIfNotExists('Email', 'FK_EMAIL_PERSON', 'idPerson', 'Person', 'id', 'IX_EMAIL_IDPERSON');
CALL AddForeignKeyAndIndexIfNotExists('Email', 'FK_EMAIL_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_EMAIL_IDAUDITLOG');

-- Table Credential
CALL AddForeignKeyAndIndexIfNotExists('Credential', 'FK_CRED_PERSON', 'idPerson', 'Person', 'id', 'IX_CRED_IDPERSON');
CALL AddForeignKeyAndIndexIfNotExists('Credential', 'FK_CRED_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_CRED_IDAUDITLOG');

-- Table TypeOfCredential
CALL AddForeignKeyAndIndexIfNotExists('TypeOfCredential', 'FK_TOC_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_TOC_IDAUDITLOG');

-- Table Gender
CALL AddForeignKeyAndIndexIfNotExists('Gender', 'FK_GENDER_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_GENDER_IDAUDITLOG');

-- Table PersonalUser
CALL AddForeignKeyAndIndexIfNotExists('PersonalUser', 'FK_USER_PERSON', 'idPerson', 'Person', 'id', 'IX_USER_IDPERSON');
CALL AddForeignKeyAndIndexIfNotExists('PersonalUser', 'FK_USER_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_USER_IDAUDITLOG');
CALL AddForeignKeyAndIndexIfNotExists('PersonalUser', 'FK_USER_USERSTATUS', 'idUserStatus', 'UserStatus', 'id', 'IX_USER_IDUSERSTATUS');

-- Table UserStatus
CALL AddForeignKeyAndIndexIfNotExists('UserStatus', 'FK_USERSTATUS_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_USERSTATUS_IDAUDITLOG');

-- Table InstitutionalEmail
CALL AddForeignKeyAndIndexIfNotExists('InstitutionalEmail', 'FK_INSTEMAIL_USER', 'idUser', 'PersonalUser', 'id', 'IX_INSTEMAIL_IDUSER');
CALL AddForeignKeyAndIndexIfNotExists('InstitutionalEmail', 'FK_INSTEMAIL_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_INSTEMAIL_IDAUDITLOG');

-- Table AccessStatus
CALL AddForeignKeyAndIndexIfNotExists('AccessStatus', 'FK_ACCSTS_ADMIN', 'idAdministrator', 'Administrator', 'idPerson', 'IX_ACCSTS_IDADMIN');
CALL AddForeignKeyAndIndexIfNotExists('AccessStatus', 'FK_ACCSTS_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_ACCSTS_IDAUDITLOG');

-- Table Driver
CALL AddForeignKeyAndIndexIfNotExists('Driver', 'FK_DRIVER_PERSON', 'idPerson', 'Person', 'id', 'IX_DRIVER_IDPERSON');
CALL AddForeignKeyAndIndexIfNotExists('Driver', 'FK_DRIVER_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_DRIVER_IDAUDITLOG');

-- Table Vehicle
CALL AddForeignKeyAndIndexIfNotExists('Vehicle', 'FK_VEHICLE_DRIVER', 'idDriver', 'Driver', 'idPerson', 'IX_VEHICLE_IDDRIVER');
CALL AddForeignKeyAndIndexIfNotExists('Vehicle', 'FK_VEHICLE_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_VEHICLE_IDAUDITLOG');

-- Table Passenger
CALL AddForeignKeyAndIndexIfNotExists('Passenger', 'FK_PASSENGER_PERSON', 'idPerson', 'Person', 'id', 'IX_PASSENGER_IDPERSON');
CALL AddForeignKeyAndIndexIfNotExists('Passenger', 'FK_PASSENGER_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_PASSENGER_IDAUDITLOG');

-- Table Trip
CALL AddForeignKeyAndIndexIfNotExists('Trip', 'FK_TRIP_DRIVER', 'idDriver', 'Driver', 'idPerson', 'IX_TRIP_IDDRIVER');
CALL AddForeignKeyAndIndexIfNotExists('Trip', 'FK_TRIP_PRICESTATUS', 'idPriceStatus', 'PriceStatus', 'id', 'IX_TRIP_IDPRICESTATUS');
CALL AddForeignKeyAndIndexIfNotExists('Trip', 'FK_TRIP_VEHICLE', 'idVehicle', 'Vehicle', 'id', 'IX_TRIP_IDVEHICLE');
CALL AddForeignKeyAndIndexIfNotExists('Trip', 'FK_TRIP_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_TRIP_IDAUDITLOG');

-- Table PriceStatus
CALL AddForeignKeyAndIndexIfNotExists('PriceStatus', 'FK_PRICESTATUS_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_PRICESTATUS_IDAUDITLOG');

-- Table TripStatus
CALL AddForeignKeyAndIndexIfNotExists('TripStatus', 'FK_TRIPSTATUS_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_TRIPSTATUS_IDAUDITLOG');

-- Table Stop
CALL AddForeignKeyAndIndexIfNotExists('Stop', 'FK_STOP_DISTRICT', 'idDistrict', 'District', 'id', 'IX_STOP_IDDISTRICT');
CALL AddForeignKeyAndIndexIfNotExists('Stop', 'FK_STOP_STARTTRIP', 'idStarTrip', 'Trip', 'id', 'IX_STOP_IDSTARTRIP');
CALL AddForeignKeyAndIndexIfNotExists('Stop', 'FK_STOP_ENDTRIP', 'idEndTrip', 'Trip', 'id', 'IX_STOP_IDENDTRIP');
CALL AddForeignKeyAndIndexIfNotExists('Stop', 'FK_STOP_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_STOP_IDAUDITLOG');

-- Table CoordinateLocation
CALL AddForeignKeyAndIndexIfNotExists('CoordinateLocation', 'FK_COORDLOC_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_COORDLOC_IDAUDITLOG');

-- Table District
CALL AddForeignKeyAndIndexIfNotExists('District', 'FK_DISTRICT_CANTON', 'idCanton', 'Canton', 'id', 'IX_DISTRICT_IDCANTON');
CALL AddForeignKeyAndIndexIfNotExists('District', 'FK_DISTRICT_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_DISTRICT_IDAUDITLOG');

-- Table Canton
CALL AddForeignKeyAndIndexIfNotExists('Canton', 'FK_CANTON_PROVINCE', 'idProvince', 'Province', 'id', 'IX_CANTON_IDPROVINCE');
CALL AddForeignKeyAndIndexIfNotExists('Canton', 'FK_CANTON_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_CANTON_IDAUDITLOG');

-- Table Province
CALL AddForeignKeyAndIndexIfNotExists('Province', 'FK_PROVINCE_COUNTRY', 'idCountry', 'Country', 'id', 'IX_PROVINCE_IDCOUNTRY');
CALL AddForeignKeyAndIndexIfNotExists('Province', 'FK_PROVINCE_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_PROVINCE_IDAUDITLOG');

-- Table Country
CALL AddForeignKeyAndIndexIfNotExists('Country', 'FK_COUNTRY_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_COUNTRY_IDAUDITLOG');

-- Table PaymentMethod
CALL AddForeignKeyAndIndexIfNotExists('PaymentMethod', 'FK_PAYMETHOD_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_PAYMETHOD_IDAUDITLOG');

-- Table Parameter
CALL AddForeignKeyAndIndexIfNotExists('Parameter', 'FK_PARAMETER_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_PARAMETER_IDAUDITLOG');

-- Table LogBook
CALL AddForeignKeyAndIndexIfNotExists('LogBook', 'FK_LOGBOOK_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_LOGBOOK_IDAUDITLOG');

-- Table EntityModified
CALL AddForeignKeyAndIndexIfNotExists('EntityModified', 'FK_ENTMOD_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_ENTMOD_IDAUDITLOG');

-- Table AttributeModified
CALL AddForeignKeyAndIndexIfNotExists('AttributeModified', 'FK_ATTRMOD_ENTMOD', 'idEntityModified', 'EntityModified', 'id', 'IX_ATTRMOD_IDENTITYMODIFIED');
CALL AddForeignKeyAndIndexIfNotExists('AttributeModified', 'FK_ATTRMOD_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_ATTRMOD_IDAUDITLOG');

-- --------------------------
-- -- FK De tablas NXN --
-- --------------------------

-- Table TripReportDailyReport
CALL AddForeignKeyAndIndexIfNotExists('TripReportDailyReport', 'FK_TRPDR_TRIP', 'idTrip', 'Trip', 'id', 'IX_TRPDR_IDTRIP');
CALL AddForeignKeyAndIndexIfNotExists('TripReportDailyReport', 'FK_TRPDR_DAILYREPORT', 'idDailyReport', 'DailyReport', 'id', 'IX_TRPDR_IDDAILYREPORT');
CALL AddForeignKeyAndIndexIfNotExists('TripReportDailyReport', 'FK_TRPDR_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_TRPDR_IDAUDITLOG');

-- Table Administrator
CALL AddForeignKeyAndIndexIfNotExists('Administrator', 'FK_ADMIN_PERSON', 'idPerson', 'Person', 'id', 'IX_ADMIN_IDPERSON');
CALL AddForeignKeyAndIndexIfNotExists('Administrator', 'FK_ADMIN_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_ADMIN_IDAUDITLOG');

-- Table AdminManageInstitution
CALL AddForeignKeyAndIndexIfNotExists('AdminManageInstitution', 'FK_ADMNINS_ADMIN', 'idPerson', 'Administrator', 'idPerson', 'IX_ADMNINS_IDPERSON');
CALL AddForeignKeyAndIndexIfNotExists('AdminManageInstitution', 'FK_ADMNINS_INST', 'idInstitution', 'Institution', 'id', 'IX_ADMNINS_IDINST');
CALL AddForeignKeyAndIndexIfNotExists('AdminManageInstitution', 'FK_ADMNINS_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_ADMNINS_IDAUDITLOG');

-- Table AdminReceiveDailyReport
CALL AddForeignKeyAndIndexIfNotExists('AdminReceiveDailyReport', 'FK_ADRDR_ADMIN', 'idAdministrator', 'Administrator', 'idPerson', 'IX_ADRDR_IDADMIN');
CALL AddForeignKeyAndIndexIfNotExists('AdminReceiveDailyReport', 'FK_ADRDR_DAILYREPORT', 'idDailyReport', 'DailyReport', 'id', 'IX_ADRDR_IDDAILYREPORT');
CALL AddForeignKeyAndIndexIfNotExists('AdminReceiveDailyReport', 'FK_ADRDR_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_ADRDR_IDAUDITLOG');

-- Table CredentialHasTypeOfCredential
CALL AddForeignKeyAndIndexIfNotExists('CredentialHasTypeOfCredential', 'FK_CHTC_CREDENTIAL', 'idCredential', 'Credential', 'id', 'IX_CHTC_IDCREDENTIAL');
CALL AddForeignKeyAndIndexIfNotExists('CredentialHasTypeOfCredential', 'FK_CHTC_TYPEOFCREDENTIAL', 'idTypeOfCredential', 'TypeOfCredential', 'id', 'IX_CHTC_IDTYPEOFCRED');
CALL AddForeignKeyAndIndexIfNotExists('CredentialHasTypeOfCredential', 'FK_CHTC_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_CHTC_IDAUDITLOG');

-- Table PassengerQueryTrip
CALL AddForeignKeyAndIndexIfNotExists('PassengerQueryTrip', 'FK_PQTRIP_USER', 'idUser', 'PersonalUser', 'id', 'IX_PQTRIP_IDUSER');
CALL AddForeignKeyAndIndexIfNotExists('PassengerQueryTrip', 'FK_PQTRIP_TRIP', 'idTrip', 'Trip', 'id', 'IX_PQTRIP_IDTRIP');
CALL AddForeignKeyAndIndexIfNotExists('PassengerQueryTrip', 'FK_PQTRIP_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_PQTRIP_IDAUDITLOG');

-- Table PassengerJoinTrip
CALL AddForeignKeyAndIndexIfNotExists('PassengerJoinTrip', 'FK_PJTRIP_USER', 'idUser', 'PersonalUser', 'id', 'IX_PJTRIP_IDUSER');
CALL AddForeignKeyAndIndexIfNotExists('PassengerJoinTrip', 'FK_PJTRIP_TRIP', 'idTrip', 'Trip', 'id', 'IX_PJTRIP_IDTRIP');
CALL AddForeignKeyAndIndexIfNotExists('PassengerJoinTrip', 'FK_PJTRIP_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_PJTRIP_IDAUDITLOG');

-- Table TripHasTripStatus
CALL AddForeignKeyAndIndexIfNotExists('TripHasTripStatus', 'FK_THTS_TRIP', 'idTrip', 'Trip', 'id', 'IX_THTS_IDTRIP');
CALL AddForeignKeyAndIndexIfNotExists('TripHasTripStatus', 'FK_THTS_TRIPSTATUS', 'idTripStatus', 'TripStatus', 'id', 'IX_THTS_IDTRIPSTATUS');
CALL AddForeignKeyAndIndexIfNotExists('TripHasTripStatus', 'FK_THTS_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_THTS_IDAUDITLOG');

-- Table StopHasCoordinateLocation
CALL AddForeignKeyAndIndexIfNotExists('StopHasCoordinateLocation', 'FK_SHCL_STOP', 'idStop', 'Stop', 'id', 'IX_SHCL_IDSTOP');
CALL AddForeignKeyAndIndexIfNotExists('StopHasCoordinateLocation', 'FK_SHCL_COORDLOC', 'idCoordinateLocation', 'CoordinateLocation', 'id', 'IX_SHCL_IDCOORDLOC');
CALL AddForeignKeyAndIndexIfNotExists('StopHasCoordinateLocation', 'FK_SHCL_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_SHCL_IDAUDITLOG');

-- Table TripHasStopHasPaymentMethod
CALL AddForeignKeyAndIndexIfNotExists('TripHasStopHasPaymentMethod', 'FK_THSHPM_PAYMETHOD', 'idPaymentMethod', 'PaymentMethod', 'id', 'IX_THSHPM_IDPAYMETHOD');
CALL AddForeignKeyAndIndexIfNotExists('TripHasStopHasPaymentMethod', 'FK_THSHPM_TRIP', 'idTrip', 'Trip', 'id', 'IX_THSHPM_IDTRIP');
CALL AddForeignKeyAndIndexIfNotExists('TripHasStopHasPaymentMethod', 'FK_THSHPM_STOP', 'idStop', 'Stop', 'id', 'IX_THSHPM_IDSTOP');
CALL AddForeignKeyAndIndexIfNotExists('TripHasStopHasPaymentMethod', 'FK_THSHPM_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_THSHPM_IDAUDITLOG');

-- Table TripHasStop
CALL AddForeignKeyAndIndexIfNotExists('TripHasStop', 'FK_THS_TRIP', 'idTrip', 'Trip', 'id', 'IX_THS_IDTRIP');
CALL AddForeignKeyAndIndexIfNotExists('TripHasStop', 'FK_THS_STOP', 'idStop', 'Stop', 'id', 'IX_THS_IDSTOP');
CALL AddForeignKeyAndIndexIfNotExists('TripHasStop', 'FK_THS_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_THS_IDAUDITLOG');

-- Table LogBookHasEntityModified
CALL AddForeignKeyAndIndexIfNotExists('LogBookHasEntityModified', 'FK_LBHEM_LOGBOOK', 'idLogBook', 'LogBook', 'id', 'IX_LBHEM_IDLOGBOOK');
CALL AddForeignKeyAndIndexIfNotExists('LogBookHasEntityModified', 'FK_LBHEM_ENTMOD', 'idEntityModified', 'EntityModified', 'id', 'IX_LBHEM_IDENTITYMOD');
CALL AddForeignKeyAndIndexIfNotExists('LogBookHasEntityModified', 'FK_LBHEM_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_LBHEM_IDAUDITLOG');

-- Table AttrModHasEntMod
CALL AddForeignKeyAndIndexIfNotExists('AttrModHasEntMod', 'FK_AMHEM_LOGBOOK', 'idLogBook', 'LogBook', 'id', 'IX_AMHEM_IDLOGBOOK');
CALL AddForeignKeyAndIndexIfNotExists('AttrModHasEntMod', 'FK_AMHEM_ATTRMOD', 'idAttributeModified', 'AttributeModified', 'id', 'IX_AMHEM_IDATTRMOD');
CALL AddForeignKeyAndIndexIfNotExists('AttrModHasEntMod', 'FK_AMHEM_AUDITLOG', 'idAuditLog', 'AuditLog', 'id', 'IX_AMHEM_IDAUDITLOG');

SELECT 'Foreign key and index check/creation process completed.' AS FinalStatus;
