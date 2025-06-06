-- Switch to the target database
USE CARPOOLING_DB;

-- Set character set for the session if needed (usually handled by connection parameters)
-- SET NAMES 'utf8mb4';

-- Creating table DAILYREPORT
CREATE TABLE DailyReport (
                             id            INT,
                             reportType    VARCHAR(100) NOT NULL,
                             idInstitution INT          NOT NULL,
                             idAuditLog    INT          NOT NULL,
                             CONSTRAINT pk_dailyreport PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table INSTITUTION
CREATE TABLE Institution (
                             id                INT,
                             emailDomain       VARCHAR(100) NOT NULL,
                             institutionName   VARCHAR(255) NOT NULL,
                             website           VARCHAR(512),
                             idAuditLog        INT          NOT NULL,
                             CONSTRAINT pk_Institution_id PRIMARY KEY (id),
                             CONSTRAINT uk_Institution_emaildomain UNIQUE (emailDomain)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table CONTACTPHONENUMBER
CREATE TABLE ContactPhoneNumber ( -- Changed from CONTACTPHONENUMBER for consistency
                                    id            INT,
                                    phoneNumber   VARCHAR(20) NOT NULL,
                                    idInstitution INT         NOT NULL,
                                    idAuditLog    INT         NOT NULL,
                                    CONSTRAINT pk_ContactPhoneNumber PRIMARY KEY (id) -- pk_ContactNumber might be a typo
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table CONTACTEMAIL
CREATE TABLE ContactEmail (
                              id            INT,
                              address       VARCHAR(255) NOT NULL,
                              idInstitution INT          NOT NULL,
                              idAuditLog    INT          NOT NULL,
                              CONSTRAINT pk_ContactEmail PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table ADMINISTRATOR
CREATE TABLE Administrator (
                               idPerson   INT,
                               idAuditLog INT NOT NULL,
                               CONSTRAINT pk_administrator PRIMARY KEY (idPerson)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table ACCESSSTATUS
CREATE TABLE AccessStatus ( -- Changed from ACCESSSTATUS for consistency
                              id              INT,
                              status          VARCHAR(50) NOT NULL,
                              idAdministrator INT         NOT NULL,
                              idAuditLog      INT         NOT NULL,
                              CONSTRAINT pk_accessStatus PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table PERSON
CREATE TABLE Person (
                        id             INT,
                        firstName      VARCHAR(100) NOT NULL,
                        secondName     VARCHAR(100),
                        firstSurname   VARCHAR(100) NOT NULL,
                        secondSurname  VARCHAR(100),
                        birthdate      DATE         NOT NULL,
                        nationality    VARCHAR(100) NOT NULL,
                        profilePicture BLOB,
                        idInstitution  INT          NOT NULL,
                        idAuditLog     INT          NOT NULL,
                        idGender       INT          NOT NULL,
                        CONSTRAINT pk_Person PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table PHONENUMBER
CREATE TABLE PhoneNumber (
                             id          INT,
                             phoneNumber VARCHAR(20) NOT NULL,
                             idPerson    INT         NOT NULL,
                             idAuditLog  INT         NOT NULL,
                             CONSTRAINT pk_PhoneNumber PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table EMAIL
CREATE TABLE Email (
                       id           INT,
                       emailAddress VARCHAR(255) NOT NULL,
                       idPerson     INT          NOT NULL,
                       idAuditLog   INT          NOT NULL,
                       CONSTRAINT pk_Email PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table CREDENTIAL
CREATE TABLE Credential (
                            id                 INT,
                            isActive           TINYINT(1)    NOT NULL, -- NUMBER(1) to TINYINT(1)
                            numberOfCredential VARCHAR(255)  NOT NULL,
                            idPerson           INT           NOT NULL,
                            idAuditLog         INT           NOT NULL,
                            CONSTRAINT pk_Credential PRIMARY KEY (id),
                            CONSTRAINT ck_Credential_isActive CHECK (isActive IN (0, 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table TYPEOFCREDENTIAL
CREATE TABLE TypeOfCredential (
                                  id         INT,
                                  type       VARCHAR(50) NOT NULL,
                                  idAuditLog INT         NOT NULL,
                                  CONSTRAINT pk_TypeOfCredential PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table GENDER
CREATE TABLE Gender (
                        id          INT,
                        genderName  VARCHAR(50) NOT NULL,
                        idAuditLog  INT         NOT NULL,
                        CONSTRAINT pk_Gender PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table PERSONALUSER
CREATE TABLE PersonalUser ( -- Changed from PERSONALUSER for consistency
                              id               INT,
                              password         VARCHAR(255) NOT NULL,
                              username         VARCHAR(100) NOT NULL,
                              registrationDate DATE         NOT NULL,
                              idUserStatus     INT          NOT NULL,
                              idPerson         INT          NOT NULL,
                              idAuditLog       INT          NOT NULL,
                              CONSTRAINT pk_PersonalUser PRIMARY KEY (id),
                              CONSTRAINT uk_PersonalUser_username UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table USERSTATUS
CREATE TABLE UserStatus ( -- Changed from USERSTATUS for consistency
                            id             INT,
                            status         VARCHAR(50),
                            idAuditLog     INT          NOT NULL,
                            CONSTRAINT pk_UserStatus PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table INSTITUTIONALEMAIL
CREATE TABLE InstitutionalEmail ( -- Changed from INSTITUTIONALEMAIL for consistency
                                    id                INT,
                                    emailAddress      VARCHAR(255) NOT NULL,
                                    idUser            INT          NOT NULL,
                                    idAuditLog        INT          NOT NULL,
                                    CONSTRAINT pk_InstitutionalEmail PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table DRIVER
CREATE TABLE Driver (
                        idPerson   INT,
                        idAuditLog INT NOT NULL,
                        CONSTRAINT pk_driver PRIMARY KEY (idPerson)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table VEHICLE
CREATE TABLE Vehicle (
                         id            INT,
                         isVerified    TINYINT(1)   NOT NULL,
                         brand         VARCHAR(100) NOT NULL,
                         carPhoto      BLOB,
                         plateNumber   VARCHAR(20)  NOT NULL,
                         carModel      VARCHAR(100) NOT NULL,
                         seatQuantity  INT          NOT NULL,
                         idDriver      INT          NOT NULL,
                         idAuditLog    INT          NOT NULL,
                         CONSTRAINT pk_Vehicle PRIMARY KEY (id),
                         CONSTRAINT ck_Vehicle_isVerified CHECK (isVerified IN (0, 1)),
                         CONSTRAINT ck_Vehicle_seatQuantity CHECK (seatQuantity > 0),
                         CONSTRAINT uk_Vehicle_plateNumber UNIQUE (plateNumber)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table PASSENGER
CREATE TABLE Passenger (
                           idPerson   INT,
                           idAuditLog INT NOT NULL,
                           CONSTRAINT pk_passenger PRIMARY KEY (idPerson)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table TRIP
CREATE TABLE Trip (
                      id                 INT,
                      maximunPassengers  INT           NOT NULL,
                      departureDateTime  TIMESTAMP     NOT NULL,
                      durationEstimate   INT           NOT NULL,
                      arrivalDateTime    TIMESTAMP     NOT NULL,
                      idDriver           INT           NOT NULL,
                      idPriceStatus      INT,
                      idVehicle          INT           NOT NULL,
                      idAuditLog         INT           NOT NULL,
                      CONSTRAINT pk_Trip PRIMARY KEY (id),
                      CONSTRAINT ck_Trip_maximunPassengers CHECK (maximunPassengers > 0),
                      CONSTRAINT ck_Trip_durationEstimate CHECK (durationEstimate > 0),
                      CONSTRAINT ck_Trip_arrivalDateTime CHECK (arrivalDateTime > departureDateTime)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table PRICESTATUS
CREATE TABLE PriceStatus (
                             id               INT,
                             status           VARCHAR(50),
                             idAuditLog       INT          NOT NULL,
                             CONSTRAINT pk_PriceStatus PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table TRIPSTATUS
CREATE TABLE TripStatus (
                            id               INT,
                            status           VARCHAR(50),
                            idAuditLog       INT          NOT NULL,
                            CONSTRAINT pk_TripStatus PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table STOP
CREATE TABLE Stop (
                      id         INT,
                      address    VARCHAR(255) NOT NULL,
                      idDistrict INT          NOT NULL,
                      idStarTrip INT,
                      idEndTrip  INT,
                      idAuditLog INT          NOT NULL,
                      CONSTRAINT pk_Stop PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table COORDINATELOCATION
CREATE TABLE CoordinateLocation (
                                    id          INT,
                                    yCoordinate FLOAT NOT NULL,
                                    xCoordinate FLOAT NOT NULL,
                                    idAuditLog  INT   NOT NULL,
                                    CONSTRAINT pk_CoordinateLocation PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table DISTRICT
CREATE TABLE District (
                          id         INT,
                          name       VARCHAR(255) NOT NULL,
                          idCanton   INT          NOT NULL,
                          idAuditLog INT          NOT NULL,
                          CONSTRAINT pk_District PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table CANTON
CREATE TABLE Canton (
                        id         INT,
                        name       VARCHAR(255) NOT NULL,
                        idProvince INT          NOT NULL,
                        idAuditLog INT          NOT NULL,
                        CONSTRAINT pk_Canton PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table PROVINCE
CREATE TABLE Province (
                          id         INT,
                          name       VARCHAR(255) NOT NULL,
                          idCountry  INT          NOT NULL,
                          idAuditLog INT          NOT NULL,
                          CONSTRAINT pk_Province PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table COUNTRY
CREATE TABLE Country (
                         id         INT,
                         name       VARCHAR(255) NOT NULL,
                         idAuditLog INT          NOT NULL,
                         CONSTRAINT pk_Country PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table PAYMENTMETHOD
CREATE TABLE PaymentMethod (
                               id         INT,
                               method     VARCHAR(100) NOT NULL,
                               idAuditLog INT          NOT NULL,
                               CONSTRAINT pk_PaymentMethod PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table AUDITLOG
CREATE TABLE AuditLog (
                          id               INT,
                          creationDate     TIMESTAMP     NOT NULL,
                          createdBy        VARCHAR(255)  NOT NULL,
                          lastUpdateDate   TIMESTAMP     NULL,
                          updatedBy        VARCHAR(255),
                          CONSTRAINT pk_AuditLog PRIMARY KEY (id),
                          CONSTRAINT ck_AuditLog_update_consistency CHECK ( (lastUpdateDate IS NULL AND updatedBy IS NULL) OR
                                                                            (lastUpdateDate IS NOT NULL AND updatedBy IS NOT NULL) )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table PARAMETER
CREATE TABLE Parameter (
                           id         INT,
                           name       VARCHAR(100) NOT NULL,
                           value      VARCHAR(255) NOT NULL,
                           idAuditLog INT          NOT NULL,
                           CONSTRAINT pk_Parameter PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table LOGBOOK
CREATE TABLE LogBook (
                         id          INT,
                         logDate     DATE          NOT NULL,
                         logTime     TIMESTAMP     NOT NULL,
                         description VARCHAR(255)  NOT NULL,
                         idAuditLog  INT           NOT NULL,
                         CONSTRAINT pk_LogBook PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table ENTITYMODIFIED
CREATE TABLE EntityModified (
                                id          INT,
                                entityName  VARCHAR(100) NOT NULL,
                                idAuditLog  INT          NOT NULL,
                                CONSTRAINT pk_EntityModified PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table ATTRIBUTEMODIFIED
CREATE TABLE AttributeModified (
                                   id               INT,
                                   newValue         VARCHAR(255),
                                   oldValue         VARCHAR(255),
                                   attributeName    VARCHAR(100) NOT NULL,
                                   idEntityModified INT          NOT NULL,
                                   idAuditLog       INT          NOT NULL,
                                   CONSTRAINT pk_AttributeModified PRIMARY KEY (id),
                                   CONSTRAINT ck_AttMod_ValuesDiffer CHECK (newValue <> oldValue OR
                                                                            (newValue IS NULL AND oldValue IS NOT NULL)
                                       OR (newValue IS NOT NULL AND oldValue IS NULL))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- Creating table TRIPREPORTDAILYREPORT (NXN)
CREATE TABLE TripReportDailyReport (
                                       idTrip        INT,
                                       idDailyReport INT,
                                       reportDate    DATE,
                                       reportNumber  VARCHAR(100),
                                       idAuditLog    INT            NOT NULL,
                                       CONSTRAINT pk_tripreportdailyreport PRIMARY KEY (idTrip, idDailyReport)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table ADMINMANAGEINSTITUTION (NXN)
CREATE TABLE AdminManageInstitution (
                                        idPerson      INT,
                                        idInstitution INT,
                                        idAuditLog    INT NOT NULL,
                                        CONSTRAINT pk_adminmanageinstitution PRIMARY KEY (idPerson, idInstitution)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table ADMINRECEIVEDAIILYREPORT (NXN)
CREATE TABLE AdminReceiveDailyReport (
                                         idAdministrator INT,
                                         idDailyReport   INT,
                                         idAuditLog      INT NOT NULL,
                                         CONSTRAINT pk_adminreceivedailyreport PRIMARY KEY (idAdministrator, idDailyReport)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table CREDENTIALHASTYPEOFCREDENTIAL (NXN)
CREATE TABLE CredentialHasTypeOfCredential (
                                               idCredential       INT,
                                               idTypeOfCredential INT,
                                               idAuditLog         INT NOT NULL,
                                               CONSTRAINT pk_credhtyofcred PRIMARY KEY (idCredential, idTypeOfCredential)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table PASSENGERQUERYTRIP (NXN)
CREATE TABLE PassengerQueryTrip (
                                    idUser     INT,
                                    idTrip     INT,
                                    idAuditLog INT NOT NULL,
                                    CONSTRAINT pk_passengerQueryTrip PRIMARY KEY (idUser, idTrip)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table PASSENGERJOINTRIP (NXN)
CREATE TABLE PassengerJoinTrip (
                                   idUser     INT,
                                   idTrip     INT,
                                   joinDate   DATE NOT NULL,
                                   idAuditLog INT  NOT NULL,
                                   CONSTRAINT pk_passengerJoinTrip PRIMARY KEY (idUser, idTrip)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table TRIPHASTRIPSTATUS (NXN)
CREATE TABLE TripHasTripStatus (
                                   idTrip       INT,
                                   idTripStatus INT,
                                   idAuditLog   INT NOT NULL,
                                   CONSTRAINT pk_triphastripstatus PRIMARY KEY (idTrip, idTripStatus)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table STOPHASCOORDINATELOCATION (NXN)
CREATE TABLE StopHasCoordinateLocation (
                                           idStop               INT,
                                           idCoordinateLocation INT,
                                           idAuditLog           INT NOT NULL,
                                           CONSTRAINT pk_stophascoordloc PRIMARY KEY (idStop, idCoordinateLocation)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table TRIPHASSTOPHASPAYMENTMETHOD (NXN)
CREATE TABLE TripHasStopHasPaymentMethod (
                                             idPaymentMethod INT,
                                             idTrip          INT,
                                             idStop          INT,
                                             idAuditLog      INT NOT NULL,
                                             CONSTRAINT pk_tripstoppaymethod PRIMARY KEY (idPaymentMethod, idTrip, idStop)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table TRIPHASSTOP (NXN)
CREATE TABLE TripHasStop (
                             idTrip           INT,
                             idStop           INT,
                             estimatedArrival DATE   NOT NULL,
                             stopCost         FLOAT  NOT NULL,
                             numberStop       INT    NOT NULL,
                             idAuditLog       INT    NOT NULL,
                             CONSTRAINT pk_triphasstop PRIMARY KEY (idTrip, idStop),
                             CONSTRAINT ck_THS_stopCost CHECK (stopCost >= 0),
                             CONSTRAINT ck_THS_numberStop CHECK (numberStop >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table LOGBOOKHASENTITYMODIFIED (NXN)
CREATE TABLE LogBookHasEntityModified (
                                          idLogBook        INT,
                                          idEntityModified INT,
                                          idAuditLog       INT NOT NULL,
                                          CONSTRAINT pk_logbookhasentmod PRIMARY KEY (idLogBook, idEntityModified)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Creating table ATTRMODHASENTMOD (NXN)
CREATE TABLE AttrModHasEntMod (
                                  idLogBook           INT,
                                  idAttributeModified INT,
                                  idAuditLog          INT NOT NULL,
                                  CONSTRAINT pk_attrmodhasentmod PRIMARY KEY (idLogBook, idAttributeModified)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;