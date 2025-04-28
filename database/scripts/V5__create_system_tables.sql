DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'DAILYREPORT';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE DailyReport (
                                     id          NUMBER,
                                     reportType  VARCHAR2(100),

                                     CONSTRAINT pk_dailyreport PRIMARY KEY (id)
                                         USING INDEX TABLESPACE CARPOOLING_INDX,
                                     CONSTRAINT nn_DailyReport_reportType CHECK (reportType IS NOT NULL)
        )
        TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table DAILYREPORT.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table DAILYREPORT already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'INSTITUTION';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Institution (
                                     id                NUMBER,
                                     emailDomain       VARCHAR2(100),
                                     institutionName   VARCHAR2(255),
                                     website           VARCHAR2(512),

                                     CONSTRAINT pk_Institution_id PRIMARY KEY (id)
                                         USING INDEX TABLESPACE CARPOOLING_INDX,
                                     CONSTRAINT uk_Institution_emaildomain UNIQUE (emailDomain)
                                         USING INDEX TABLESPACE CARPOOLING_INDX,
                                     CONSTRAINT nn_Institution_emaildomain CHECK (emailDomain IS NOT NULL),
                                     CONSTRAINT nn_Institution_institutionname CHECK (institutionName IS NOT NULL)

        )
        TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table INSTITUTION.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table INSTITUTION already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'CONTACTPHONENUMBER';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE ContactPhoneNumber (
                                            id     NUMBER,
                                            phoneNumber VARCHAR2(20),

                                            CONSTRAINT pk_ContactPhoneNumber PRIMARY KEY (id)
                                                USING INDEX TABLESPACE CARPOOLING_INDX,
                                            CONSTRAINT nn_ContactPhoneNumber_number CHECK (phoneNumber IS NOT NULL)
            )
        TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table CONTACTPHONENUMBER.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table CONTACTPHONENUMBER already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'CONTACTEMAIL';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE ContactEmail (
                                      id      NUMBER,
                                      address VARCHAR2(255),

                                      CONSTRAINT pk_ContactEmail PRIMARY KEY (id)
                                          USING INDEX TABLESPACE CARPOOLING_INDX,
                                      CONSTRAINT nn_ContactEmail_address CHECK (address IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table CONTACTEMAIL.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table CONTACTEMAIL already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'PERSON';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Person (
                                id             NUMBER,
                                firstName      VARCHAR2(100),
                                secondName     VARCHAR2(100),
                                firstSurname   VARCHAR2(100),
                                secondSurname  VARCHAR2(100),
                                birthdate      DATE,
                                profilePicture BLOB,

                                CONSTRAINT pk_Person PRIMARY KEY (id)
                                    USING INDEX TABLESPACE CARPOOLING_INDX,
                                CONSTRAINT nn_Person_firstName CHECK (firstName IS NOT NULL),
                                CONSTRAINT nn_Person_firstSurname CHECK (firstSurname IS NOT NULL),
                                CONSTRAINT nn_Person_birthdate CHECK (birthdate IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table PERSON.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table PERSON already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'PHONENUMBER';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE PhoneNumber (
                                     id     NUMBER,
                                     phoneNumber VARCHAR2(20),

                                     CONSTRAINT pk_PhoneNumber PRIMARY KEY (id)
                                         USING INDEX TABLESPACE CARPOOLING_INDX,
                                     CONSTRAINT nn_PhoneNumber_number CHECK (phoneNumber IS NOT NULL)
            )
        TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table PHONENUMBER.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table PHONENUMBER already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'EMAIL';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Email (
                               id      NUMBER,
                               address VARCHAR2(255),

                               CONSTRAINT pk_Email PRIMARY KEY (id)
                                   USING INDEX TABLESPACE CARPOOLING_INDX,
                               CONSTRAINT nn_Email_address CHECK (address IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table EMAIL.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table EMAIL already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'CREDENTIAL';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Credential (
                                    id                 NUMBER,
                                    isActive           NUMBER(1),
                                    numberOfCredential VARCHAR2(255),

                                    CONSTRAINT pk_Credential PRIMARY KEY (id)
                                        USING INDEX TABLESPACE CARPOOLING_INDX,
                                    CONSTRAINT nn_Credential_isActive CHECK (isActive IS NOT NULL),
                                    CONSTRAINT ck_Credential_isActive CHECK (isActive IN (0, 1)),
                                    CONSTRAINT nn_Credential_numCredential CHECK (numberOfCredential IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table CREDENTIAL.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table CREDENTIAL already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'TYPEOFCREDENTIAL';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE TypeOfCredential (
                                          id        NUMBER,
                                          nationalId VARCHAR2(255),
                                          dimex     VARCHAR2(255),
                                          nite      VARCHAR2(255),
                                          passport  VARCHAR2(255),

                                          CONSTRAINT pk_TypeOfCredential PRIMARY KEY (id)
                                              USING INDEX TABLESPACE CARPOOLING_INDX
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table TYPEOFCREDENTIAL.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table TYPEOFCREDENTIAL already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'GENDER';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Gender (
                                id          NUMBER,
                                isMale      NUMBER(1),
                                isFemale    NUMBER(1),
                                isNonBinary NUMBER(1),
                                description VARCHAR2(255),

                                CONSTRAINT pk_Gender PRIMARY KEY (id)
                                    USING INDEX TABLESPACE CARPOOLING_INDX
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table GENDER.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table GENDER already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'ENDUSER';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE ENDUSER (
                                 id             NUMBER,
                                 password       VARCHAR2(255),
                                 username       VARCHAR2(100),
                                 isVerified     NUMBER(1),
                                 registrationDate DATE,

                                 CONSTRAINT pk_ENDUSER PRIMARY KEY (id)
                                     USING INDEX TABLESPACE CARPOOLING_INDX,
                                 CONSTRAINT uk_ENDUSER_username UNIQUE (username)
                                     USING INDEX TABLESPACE CARPOOLING_INDX,
                                 CONSTRAINT nn_ENDUSER_password CHECK (password IS NOT NULL),
                                 CONSTRAINT nn_ENDUSER_username CHECK (username IS NOT NULL),
                                 CONSTRAINT nn_ENDUSER_isVerified CHECK (isVerified IS NOT NULL),
                                 CONSTRAINT nn_ENDUSER_registrationDate CHECK (registrationDate IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table ENDUSER.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table ENDUSER already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'VEHICLE';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Vehicle (
                                 id            NUMBER,
                                 isVerified    NUMBER(1),
                                 brand         VARCHAR2(100),
                                 carPhoto      BLOB,
                                 plateNumber   VARCHAR2(20),
                                 carModel      VARCHAR2(100),
                                 seatQuantity  NUMBER,

                                 CONSTRAINT pk_Vehicle PRIMARY KEY (id)
                                     USING INDEX TABLESPACE CARPOOLING_INDX,
                                 CONSTRAINT nn_Vehicle_isVerified CHECK (isVerified IS NOT NULL),
                                 CONSTRAINT nn_Vehicle_brand CHECK (brand IS NOT NULL),
                                 CONSTRAINT nn_Vehicle_plateNumber CHECK (plateNumber IS NOT NULL),
                                 CONSTRAINT uk_Vehicle_plateNumber UNIQUE (plateNumber)
                                     USING INDEX TABLESPACE CARPOOLING_INDX,
                                 CONSTRAINT nn_Vehicle_carModel CHECK (carModel IS NOT NULL),
                                 CONSTRAINT nn_Vehicle_seatQuantity CHECK (seatQuantity IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table VEHICLE.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table VEHICLE already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'TRIP';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Trip (
                              id                 NUMBER,
                              maximunPassangers  NUMBER,
                              departureDateTime  TIMESTAMP,
                              durationEstimate   NUMBER,
                              arrivalDateTime    TIMESTAMP,
                              status             VARCHAR2(50),

                              CONSTRAINT pk_Trip PRIMARY KEY (id)
                                  USING INDEX TABLESPACE CARPOOLING_INDX,
                              CONSTRAINT nn_Trip_maximunPassangers CHECK (maximunPassangers IS NOT NULL),
                              CONSTRAINT ck_Trip_maximunPassangers CHECK (maximunPassangers > 0),
                              CONSTRAINT nn_Trip_departureDateTime CHECK (departureDateTime IS NOT NULL),
                              CONSTRAINT nn_Trip_durationEstimate CHECK (durationEstimate IS NOT NULL),
                              CONSTRAINT ck_Trip_durationEstimate CHECK (durationEstimate > 0),
                              CONSTRAINT nn_Trip_arrivalDateTime CHECK (arrivalDateTime IS NOT NULL),
                              CONSTRAINT ck_Trip_arrivalDateTime CHECK (arrivalDateTime > departureDateTime),
                              CONSTRAINT nn_Trip_status CHECK (status IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table TRIP.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table TRIP already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'PRICE';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Price (
                               id               NUMBER,
                               isFixed          NUMBER(1),
                               isFree           NUMBER(1),
                               isCustomPerStop  NUMBER(1),
                               priceAmount      NUMBER,
                               currency         VARCHAR2(3),
                               description      VARCHAR2(255),

                               CONSTRAINT pk_Price PRIMARY KEY (id)
                                   USING INDEX TABLESPACE CARPOOLING_INDX
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table PRICE.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table PRICE already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'TRIPSTATUS';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE TripStatus (
                                    id          NUMBER,
                                    isPending   NUMBER(1),
                                    isOngoing   NUMBER(1),
                                    isCancelled NUMBER(1),
                                    isFinished  NUMBER(1),
                                    description VARCHAR2(255),

                                    CONSTRAINT pk_TripStatus PRIMARY KEY (id)
                                        USING INDEX TABLESPACE CARPOOLING_INDX
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table TRIPSTATUS.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table TRIPSTATUS already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'STOP';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Stop (
                              id      NUMBER,
                              address VARCHAR2(255),
                              latitude NUMBER,
                              longitude NUMBER,

                              CONSTRAINT pk_Stop PRIMARY KEY (id)
                                  USING INDEX TABLESPACE CARPOOLING_INDX,
                              CONSTRAINT nn_Stop_address CHECK (address IS NOT NULL),
                              CONSTRAINT nn_Stop_latitude CHECK (latitude IS NOT NULL),
                              CONSTRAINT nn_Stop_longitude CHECK (longitude IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table STOP.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table STOP already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'COORDINATELOCATION';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE CoordinateLocation (
                                            id          NUMBER,
                                            yCoordinate NUMBER,
                                            xCoordinate NUMBER,

                                            CONSTRAINT pk_CoordinateLocation PRIMARY KEY (id)
                                                USING INDEX TABLESPACE CARPOOLING_INDX,
                                            CONSTRAINT nn_yCoordinate CHECK (yCoordinate IS NOT NULL),
                                            CONSTRAINT nn_xCoordinate CHECK (xCoordinate IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table COORDINATELOCATION.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table COORDINATELOCATION already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'DISTRICT';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE District (
                                  id   NUMBER,
                                  name VARCHAR2(255),

                                  CONSTRAINT pk_District PRIMARY KEY (id)
                                      USING INDEX TABLESPACE CARPOOLING_INDX,
                                  CONSTRAINT nn_District_name CHECK (name IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table DISTRICT.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table DISTRICT already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'CANTON';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Canton (
                                id   NUMBER,
                                name VARCHAR2(255),

                                CONSTRAINT pk_Canton PRIMARY KEY (id)
                                    USING INDEX TABLESPACE CARPOOLING_INDX,
                                CONSTRAINT nn_Canton_name CHECK (name IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table CANTON.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table CANTON already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'PROVINCE';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Province (
                                  id   NUMBER,
                                  name VARCHAR2(255),

                                  CONSTRAINT pk_Province PRIMARY KEY (id)
                                      USING INDEX TABLESPACE CARPOOLING_INDX,
                                  CONSTRAINT nn_Province_name CHECK (name IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table PROVINCE.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table PROVINCE already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'COUNTRY';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Country (
                                 id   NUMBER,
                                 name VARCHAR2(255),

                                 CONSTRAINT pk_Country PRIMARY KEY (id)
                                     USING INDEX TABLESPACE CARPOOLING_INDX,
                                 CONSTRAINT nn_Country_name CHECK (name IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table COUNTRY.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table COUNTRY already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'PAYMENTMETHOD';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE PaymentMethod (
                                       id     NUMBER,
                                       method VARCHAR2(100),

                                       CONSTRAINT pk_PaymentMethod PRIMARY KEY (id)
                                           USING INDEX TABLESPACE CARPOOLING_INDX,
                                       CONSTRAINT nn_PaymentMethod_method CHECK (method IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table PAYMENTMETHOD.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table PAYMENTMETHOD already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'AUDITLOG';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE AuditLog (
                                  id               NUMBER,
                                  creationDate     TIMESTAMP,
                                  createdBy        VARCHAR2(255),
                                  lastUpdateDate   TIMESTAMP,
                                  updatedBy        VARCHAR2(255),

                                  CONSTRAINT pk_AuditLog PRIMARY KEY (id)
                                      USING INDEX TABLESPACE CARPOOLING_INDX,
                                  CONSTRAINT nn_AuditLog_creationDate CHECK (creationDate IS NOT NULL),
                                  CONSTRAINT nn_AuditLog_createdBy CHECK (createdBy IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table AUDITLOG.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table AUDITLOG already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'PARAMETER';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Parameter (
                                   id    NUMBER,
                                   name  VARCHAR2(100),
                                   value VARCHAR2(255),

                                   CONSTRAINT pk_Parameter PRIMARY KEY (id)
                                       USING INDEX TABLESPACE CARPOOLING_INDX,
                                   CONSTRAINT nn_Parameter_name CHECK (name IS NOT NULL),
                                   CONSTRAINT nn_Parameter_value CHECK (value IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table PARAMETER.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table PARAMETER already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'LOGBOOK';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE LogBook (
                                 id          NUMBER,
                                 logDate     DATE,
                                 logTime     TIMESTAMP,
                                 description VARCHAR2(255),

                                 CONSTRAINT pk_LogBook PRIMARY KEY (id)
                                     USING INDEX TABLESPACE CARPOOLING_INDX,
                                 CONSTRAINT nn_LogBook_logDate CHECK (logDate IS NOT NULL),
                                 CONSTRAINT nn_LogBook_logTime CHECK (logTime IS NOT NULL),
                                 CONSTRAINT nn_LogBook_description CHECK (description IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table LOGBOOK.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table LOGBOOK already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'ENTITYMODIFIED';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE EntityModified (
                                        id          NUMBER,
                                        entityName  VARCHAR2(100),

                                        CONSTRAINT pk_EntityModified PRIMARY KEY (id)
                                            USING INDEX TABLESPACE CARPOOLING_INDX,
                                        CONSTRAINT nn_EntityModified_entityName CHECK (entityName IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table ENTITYMODIFIED.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table ENTITYMODIFIED already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'ATTRIBUTEMODIFIED';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE AttributeModified (
                                           id            NUMBER,
                                           newValue      VARCHAR2(255),
                                           oldValue      VARCHAR2(255),
                                           attributeName VARCHAR2(100),

                                           CONSTRAINT pk_AttributeModified PRIMARY KEY (id)
                                               USING INDEX TABLESPACE CARPOOLING_INDX,
                                           CONSTRAINT nn_newValue CHECK (newValue IS NOT NULL),
                                           CONSTRAINT nn_oldValue CHECK (oldValue IS NOT NULL),
                                           CONSTRAINT nn_attributeName CHECK (attributeName IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table ATTRIBUTEMODIFIED.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table ATTRIBUTEMODIFIED already existed.');
    END IF;
END;
/

--TABLAS NXN

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'TRIPREPORTDAILYREPORT'; -- Nombre de la tabla

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE TripReportDailyReport (
                                               idTrip NUMBER,
                                               idDailyReport NUMBER,
                                               reportDate DATE,
                                               reportNumber VARCHAR2(100),
                                               idAuditLog NUMBER,


                                               CONSTRAINT pk_tripreportdailyreport PRIMARY KEY (idTrip, idDailyReport)
                                                   USING INDEX TABLESPACE CARPOOLING_INDX
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table TRIPREPORTDAILYREPORT.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table TRIPREPORTDAILYREPORT already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'ADMINISTRATOR';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Administrator (
                                       idPerson NUMBER,
                                       accessStatus VARCHAR2(50),
                                       idAuditLog NUMBER,

                                       CONSTRAINT pk_administrator PRIMARY KEY (idPerson)
                                           USING INDEX TABLESPACE CARPOOLING_INDX
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table ADMINISTRATOR.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table ADMINISTRATOR already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'ADMINMANAGEINSTITUTION';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE AdminManageInstitution (
                                                idPerson NUMBER,
                                                idInstitution NUMBER,
                                                idAuditLog NUMBER,

                                                CONSTRAINT pk_adminmanageinstitution PRIMARY KEY (idPerson, idInstitution)
                                                    USING INDEX TABLESPACE CARPOOLING_INDX,
                                                CONSTRAINT nn_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table ADMINMANAGEINSTITUTION.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table ADMINMANAGEINSTITUTION already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'ADMINRECEIVEDAIILYREPORT';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE AdminReceiveDailyReport (
                                                 idAdministrator NUMBER,
                                                 idDailyReport NUMBER,
                                                 idAuditLog NUMBER,

                                                 CONSTRAINT pk_adminreceivedailyreport PRIMARY KEY (idAdministrator, idDailyReport)
                                                     USING INDEX TABLESPACE CARPOOLING_INDX,
                                                 CONSTRAINT nn_ARDR_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table ADMINRECEIVEDAIILYREPORT.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table ADMINRECEIVEDAIILYREPORT already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'CREDENTIALHASTYPEOFCREDENTIAL';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE CredentialHasTypeOfCredential (
                                                       idCredential NUMBER,
                                                       idTypeOfCredential NUMBER,
                                                       idAuditLog NUMBER,

                                                       CONSTRAINT pk_credhtyofcred PRIMARY KEY (idCredential, idTypeOfCredential)
                                                           USING INDEX TABLESPACE CARPOOLING_INDX,
                                                       CONSTRAINT nn_CredHasTypeOfCred_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table CREDENTIALHASTYPEOFCREDENTIAL.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table CREDENTIALHASTYPEOFCREDENTIAL already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'DRIVER';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Driver (
                                idPerson NUMBER,
                                idAuditLog NUMBER,

                                CONSTRAINT pk_driver PRIMARY KEY (idPerson)
                                    USING INDEX TABLESPACE CARPOOLING_INDX,
                                CONSTRAINT nn_Driver_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table DRIVER.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table DRIVER already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'PASSANGER';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE Passanger (
                                   idPerson NUMBER,
                                   idAuditLog NUMBER,

                                   CONSTRAINT pk_passanger PRIMARY KEY (idPerson)
                                       USING INDEX TABLESPACE CARPOOLING_INDX,
                                   CONSTRAINT nn_Passanger_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table PASSANGER.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table PASSANGER already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'PASSANGERQUERYTRIP';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE PassangerQueryTrip (
                                            idUser NUMBER,
                                            idTrip NUMBER,
                                            idAuditLog NUMBER,

                                            CONSTRAINT pk_passangerquerytrip PRIMARY KEY (idUser, idTrip)
                                                USING INDEX TABLESPACE CARPOOLING_INDX,
                                            CONSTRAINT nn_PassangerQueryTrip_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table PASSANGERQUERYTRIP.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table PASSANGERQUERYTRIP already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'PASSANGERJOINTRIP';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE PassangerJoinTrip (
                                           idUser NUMBER,
                                           idTrip NUMBER,
                                           status VARCHAR2(50),
                                           joinDate DATE,
                                           idAuditLog NUMBER,

                                           CONSTRAINT pk_passangerjointrip PRIMARY KEY (idUser, idTrip)
                                               USING INDEX TABLESPACE CARPOOLING_INDX,
                                           CONSTRAINT nn_PassangerJoinTrip_status CHECK (status IS NOT NULL),
                                           CONSTRAINT nn_PassangerJoinTrip_joinDate CHECK (joinDate IS NOT NULL),
                                           CONSTRAINT nn_PassangerJoinTrip_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table PASSANGERJOINTRIP.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table PASSANGERJOINTRIP already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'TRIPHASTRIPSTATUS';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE TripHasTripStatus (
                                           idTrip NUMBER,
                                           idTripStatus NUMBER,
                                           idAuditLog NUMBER,

                                           CONSTRAINT pk_triphastripstatus PRIMARY KEY (idTrip, idTripStatus)
                                               USING INDEX TABLESPACE CARPOOLING_INDX,
                                           CONSTRAINT nn_TripHasTripStatus_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table TRIPHASTRIPSTATUS.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table TRIPHASTRIPSTATUS already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'STOPHASCOORDINATELOCATION';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE StopHasCoordinateLocation (
                                                   idStop NUMBER,
                                                   idCoordinateLocation NUMBER,
                                                   idAuditLog NUMBER,

                                                   CONSTRAINT pk_stophascoordloc PRIMARY KEY (idStop, idCoordinateLocation)
                                                       USING INDEX TABLESPACE CARPOOLING_INDX,
                                                   CONSTRAINT nn_SHCL_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table STOPHASCOORDINATELOCATION.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table STOPHASCOORDINATELOCATION already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'TRIPHASSTOPHASPAYMENTMETHOD';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE TripHasStopHasPaymentMethod (
                                                     idPaymentMethod NUMBER,
                                                     idTrip NUMBER,
                                                     idStop NUMBER,
                                                     AuditLog NUMBER,

                                                     CONSTRAINT pk_tripstoppaymethod PRIMARY KEY (idPaymentMethod, idTrip, idStop)
                                                         USING INDEX TABLESPACE CARPOOLING_INDX,
                                                     CONSTRAINT nn_TripStopPayMethod_auditlog CHECK (AuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table TRIPHASSTOPHASPAYMENTMETHOD.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table TRIPHASSTOPHASPAYMENTMETHOD already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'TRIPHASSTOP';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE TripHasStop (
                                     idTrip NUMBER,
                                     idStop NUMBER,
                                     estimatedArrival DATE,
                                     stopCost NUMBER,
                                     numberStop NUMBER,
                                     idAuditLog NUMBER,

                                     CONSTRAINT pk_triphasstop PRIMARY KEY (idTrip, idStop)
                                         USING INDEX TABLESPACE CARPOOLING_INDX,
                                     CONSTRAINT nn_THS_estimatedArrival CHECK (estimatedArrival IS NOT NULL),
                                     CONSTRAINT nn_THS_stopCost CHECK (stopCost IS NOT NULL),
                                     CONSTRAINT nn_THS_numberStop CHECK (numberStop IS NOT NULL),
                                     CONSTRAINT nn_THS_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table TRIPHASSTOP.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table TRIPHASSTOP already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'LOGBOOKHASENTITYMODIFIED';

    IF v_table_exists = 0 THEN
        EXECUTE IMMEDIATE '
        CREATE TABLE LogBookHasEntityModified (
                                                  idLogBook NUMBER,
                                                  idEntityModified NUMBER,
                                                  idAuditLog NUMBER,

                                                  CONSTRAINT pk_logbookhasentmod PRIMARY KEY (idLogBook, idEntityModified)
                                                      USING INDEX TABLESPACE CARPOOLING_INDX,
                                                  CONSTRAINT nn_LogBookHasEntMod_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table LOGBOOKHASENTITYMODIFIED.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table LOGBOOKHASENTITYMODIFIED already existed.');
    END IF;
END;
/

DECLARE
    v_table_exists NUMBER;
BEGIN
    -- Verificar si la tabla ya existe usando el nombre acortado
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'ATTRMODHASENTMOD'; -- Nombre de tabla acortado

    IF v_table_exists = 0 THEN
        -- Si no existe, crear la tabla con el nombre acortado
        EXECUTE IMMEDIATE '
        CREATE TABLE AttrModHasEntMod ( -- Nombre de tabla acortado aquí
                                          idLogBook NUMBER,
                                          idAttributeModified NUMBER,
                                          idAuditLog NUMBER,

                                          CONSTRAINT pk_attrmodhasentmod PRIMARY KEY (idLogBook, idAttributeModified)
                                              USING INDEX TABLESPACE CARPOOLING_INDX,
                                          CONSTRAINT nn_AMHEM_auditlog CHECK (idAuditLog IS NOT NULL)
        )
            TABLESPACE CARPOOLING_DATA';

        DBMS_OUTPUT.PUT_LINE('Created table ATTRMODHASENTMOD.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table ATTRMODHASENTMOD already existed.');
    END IF;
END;
/