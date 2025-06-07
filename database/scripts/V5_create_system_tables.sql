CREATE TABLE DailyReport (
                             id            BIGINT NOT NULL AUTO_INCREMENT,
                             reportType    VARCHAR(100) NOT NULL,
                             idInstitution BIGINT       NOT NULL,
                             idAuditLog    BIGINT       NOT NULL,
                             CONSTRAINT pk_dailyreport PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Institution (
                             id                BIGINT NOT NULL AUTO_INCREMENT,
                             emailDomain       VARCHAR(100) NOT NULL,
                             institutionName   VARCHAR(255) NOT NULL,
                             website           VARCHAR(512),
                             idAuditLog        BIGINT       NOT NULL,
                             CONSTRAINT pk_Institution_id PRIMARY KEY (id),
                             CONSTRAINT uk_Institution_emaildomain UNIQUE (emailDomain)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE ContactPhoneNumber (
                                    id            BIGINT NOT NULL AUTO_INCREMENT,
                                    phoneNumber   VARCHAR(20) NOT NULL,
                                    idInstitution BIGINT      NOT NULL,
                                    idAuditLog    BIGINT      NOT NULL,
                                    CONSTRAINT pk_ContactPhoneNumber PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE ContactEmail (
                              id            BIGINT NOT NULL AUTO_INCREMENT,
                              address       VARCHAR(255) NOT NULL,
                              idInstitution BIGINT       NOT NULL,
                              idAuditLog    BIGINT       NOT NULL,
                              CONSTRAINT pk_ContactEmail PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Administrator (
                               idPerson   BIGINT NOT NULL,
                               idAuditLog BIGINT NOT NULL,
                               CONSTRAINT pk_administrator PRIMARY KEY (idPerson)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Driver (
                        idPerson   BIGINT NOT NULL,
                        idAuditLog BIGINT NOT NULL,
                        CONSTRAINT pk_driver PRIMARY KEY (idPerson)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Passenger (
                           idPerson   BIGINT NOT NULL,
                           idAuditLog BIGINT NOT NULL,
                           CONSTRAINT pk_passenger PRIMARY KEY (idPerson)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE AccessStatus (
                              id              BIGINT NOT NULL AUTO_INCREMENT,
                              status          VARCHAR(50) NOT NULL,
                              idAdministrator BIGINT      NOT NULL,
                              idAuditLog      BIGINT      NOT NULL,
                              CONSTRAINT pk_accessStatus PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Person (
                        id             BIGINT NOT NULL AUTO_INCREMENT,
                        firstName      VARCHAR(100) NOT NULL,
                        secondName     VARCHAR(100),
                        firstSurname   VARCHAR(100) NOT NULL,
                        secondSurname  VARCHAR(100),
                        birthdate      DATE         NOT NULL,
                        nationality    VARCHAR(100) NOT NULL,
                        profilePicture BLOB,
                        idInstitution  BIGINT       NOT NULL,
                        idAuditLog     BIGINT       NOT NULL,
                        idGender       BIGINT       NOT NULL,
                        CONSTRAINT pk_Person PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE PhoneNumber (
                             id          BIGINT NOT NULL AUTO_INCREMENT,
                             phoneNumber VARCHAR(20) NOT NULL,
                             idPerson    BIGINT      NOT NULL,
                             idAuditLog  BIGINT      NOT NULL,
                             CONSTRAINT pk_PhoneNumber PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Email (
                       id           BIGINT NOT NULL AUTO_INCREMENT,
                       emailAddress VARCHAR(255) NOT NULL,
                       idPerson     BIGINT       NOT NULL,
                       idAuditLog   BIGINT       NOT NULL,
                       CONSTRAINT pk_Email PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Credential (
                            id                 BIGINT NOT NULL AUTO_INCREMENT,
                            isActive           TINYINT(1)    NOT NULL,
                            numberOfCredential VARCHAR(255)  NOT NULL,
                            idPerson           BIGINT        NOT NULL,
                            idAuditLog         BIGINT        NOT NULL,
                            CONSTRAINT pk_Credential PRIMARY KEY (id),
                            CONSTRAINT ck_Credential_isActive CHECK (isActive IN (0, 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE TypeOfCredential (
                                  id         BIGINT NOT NULL AUTO_INCREMENT,
                                  type       VARCHAR(50) NOT NULL,
                                  idAuditLog BIGINT      NOT NULL,
                                  CONSTRAINT pk_TypeOfCredential PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Gender (
                        id          BIGINT NOT NULL AUTO_INCREMENT,
                        genderName  VARCHAR(50) NOT NULL,
                        idAuditLog  BIGINT      NOT NULL,
                        CONSTRAINT pk_Gender PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE PersonalUser (
                              id               BIGINT NOT NULL AUTO_INCREMENT,
                              password         VARCHAR(255) NOT NULL,
                              username         VARCHAR(100) NOT NULL,
                              registrationDate DATE         NOT NULL,
                              idUserStatus     BIGINT       NOT NULL,
                              idPerson         BIGINT       NOT NULL,
                              idAuditLog       BIGINT       NOT NULL,
                              CONSTRAINT pk_PersonalUser PRIMARY KEY (id),
                              CONSTRAINT uk_PersonalUser_username UNIQUE (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE UserStatus (
                            id             BIGINT NOT NULL AUTO_INCREMENT,
                            status         VARCHAR(50),
                            idAuditLog     BIGINT       NOT NULL,
                            CONSTRAINT pk_UserStatus PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE InstitutionalEmail (
                                    id                BIGINT NOT NULL AUTO_INCREMENT,
                                    emailAddress      VARCHAR(255) NOT NULL,
                                    idUser            BIGINT       NOT NULL,
                                    idAuditLog        BIGINT       NOT NULL,
                                    CONSTRAINT pk_InstitutionalEmail PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Vehicle (
                         id            BIGINT NOT NULL AUTO_INCREMENT,
                         isVerified    TINYINT(1)   NOT NULL,
                         brand         VARCHAR(100) NOT NULL,
                         carPhoto      BLOB,
                         plateNumber   VARCHAR(20)  NOT NULL,
                         carModel      VARCHAR(100) NOT NULL,
                         seatQuantity  INT          NOT NULL,
                         idDriver      BIGINT       NOT NULL,
                         idAuditLog    BIGINT       NOT NULL,
                         CONSTRAINT pk_Vehicle PRIMARY KEY (id),
                         CONSTRAINT ck_Vehicle_isVerified CHECK (isVerified IN (0, 1)),
                         CONSTRAINT ck_Vehicle_seatQuantity CHECK (seatQuantity > 0),
                         CONSTRAINT uk_Vehicle_plateNumber UNIQUE (plateNumber)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Trip (
                      id                 BIGINT NOT NULL AUTO_INCREMENT,
                      maximunPassengers  INT           NOT NULL,
                      departureDateTime  TIMESTAMP     NOT NULL,
                      durationEstimate   INT           NOT NULL,
                      arrivalDateTime    TIMESTAMP     NOT NULL,
                      idDriver           BIGINT        NOT NULL,
                      idPriceStatus      BIGINT,
                      idVehicle          BIGINT        NOT NULL,
                      idAuditLog         BIGINT        NOT NULL,
                      CONSTRAINT pk_Trip PRIMARY KEY (id),
                      CONSTRAINT ck_Trip_maximunPassengers CHECK (maximunPassengers > 0),
                      CONSTRAINT ck_Trip_durationEstimate CHECK (durationEstimate > 0),
                      CONSTRAINT ck_Trip_arrivalDateTime CHECK (arrivalDateTime > departureDateTime)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE PriceStatus (
                             id               BIGINT NOT NULL AUTO_INCREMENT,
                             status           VARCHAR(50),
                             idAuditLog       BIGINT       NOT NULL,
                             CONSTRAINT pk_PriceStatus PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE TripStatus (
                            id               BIGINT NOT NULL AUTO_INCREMENT,
                            status           VARCHAR(50),
                            idAuditLog       BIGINT       NOT NULL,
                            CONSTRAINT pk_TripStatus PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Stop (
                      id         BIGINT NOT NULL AUTO_INCREMENT,
                      address    VARCHAR(255) NOT NULL,
                      idDistrict BIGINT       NOT NULL,
                      idStartTrip BIGINT,
                      idEndTrip  BIGINT,
                      idAuditLog BIGINT       NOT NULL,
                      CONSTRAINT pk_Stop PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE CoordinateLocation (
                                    id          BIGINT NOT NULL AUTO_INCREMENT,
                                    yCoordinate FLOAT NOT NULL,
                                    xCoordinate FLOAT NOT NULL,
                                    idAuditLog  BIGINT   NOT NULL,
                                    CONSTRAINT pk_CoordinateLocation PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE District (
                          id         BIGINT NOT NULL AUTO_INCREMENT,
                          name       VARCHAR(255) NOT NULL,
                          idCanton   BIGINT       NOT NULL,
                          idAuditLog BIGINT       NOT NULL,
                          CONSTRAINT pk_District PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Canton (
                        id         BIGINT NOT NULL AUTO_INCREMENT,
                        name       VARCHAR(255) NOT NULL,
                        idProvince BIGINT       NOT NULL,
                        idAuditLog BIGINT       NOT NULL,
                        CONSTRAINT pk_Canton PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Province (
                          id         BIGINT NOT NULL AUTO_INCREMENT,
                          name       VARCHAR(255) NOT NULL,
                          idCountry  BIGINT       NOT NULL,
                          idAuditLog BIGINT       NOT NULL,
                          CONSTRAINT pk_Province PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Country (
                         id         BIGINT NOT NULL AUTO_INCREMENT,
                         name       VARCHAR(255) NOT NULL,
                         idAuditLog BIGINT       NOT NULL,
                         CONSTRAINT pk_Country PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE PaymentMethod (
                               id         BIGINT NOT NULL AUTO_INCREMENT,
                               method     VARCHAR(100) NOT NULL,
                               idAuditLog BIGINT       NOT NULL,
                               CONSTRAINT pk_PaymentMethod PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Tablas de Log y Parámetros
-- ==============================================

CREATE TABLE AuditLog (
                          id               BIGINT        NOT NULL AUTO_INCREMENT,
                          creationDate     TIMESTAMP     NOT NULL,
                          createdBy        VARCHAR(255)  NOT NULL,
                          lastUpdateDate   TIMESTAMP     NULL,
                          updatedBy        VARCHAR(255),
                          CONSTRAINT pk_AuditLog PRIMARY KEY (id),
                          CONSTRAINT ck_AuditLog_update_consistency CHECK ( (lastUpdateDate IS NULL AND updatedBy IS NULL) OR
                                                                            (lastUpdateDate IS NOT NULL AND updatedBy IS NOT NULL) )
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE Parameter (
                           id         BIGINT NOT NULL AUTO_INCREMENT,
                           name       VARCHAR(100) NOT NULL,
                           value      VARCHAR(255) NOT NULL,
                           idAuditLog BIGINT       NOT NULL,
                           CONSTRAINT pk_Parameter PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE LogBook (
                         id          BIGINT NOT NULL AUTO_INCREMENT,
                         logDate     DATE          NOT NULL,
                         logTime     TIMESTAMP     NOT NULL,
                         description VARCHAR(255)  NOT NULL,
                         idAuditLog  BIGINT        NOT NULL,
                         CONSTRAINT pk_LogBook PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE EntityModified (
                                id          BIGINT NOT NULL AUTO_INCREMENT,
                                entityName  VARCHAR(100) NOT NULL,
                                idAuditLog  BIGINT       NOT NULL,
                                CONSTRAINT pk_EntityModified PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE AttributeModified (
                                   id               BIGINT NOT NULL AUTO_INCREMENT,
                                   newValue         VARCHAR(255),
                                   oldValue         VARCHAR(255),
                                   attributeName    VARCHAR(100) NOT NULL,
                                   idEntityModified BIGINT       NOT NULL,
                                   idAuditLog       BIGINT       NOT NULL,
                                   CONSTRAINT pk_AttributeModified PRIMARY KEY (id),
                                   CONSTRAINT ck_AttMod_ValuesDiffer CHECK (newValue <> oldValue OR
                                                                            (newValue IS NULL AND oldValue IS NOT NULL)
                                       OR (newValue IS NOT NULL AND oldValue IS NULL))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- Tablas de Unión N:N
-- ============================================================================

CREATE TABLE TripReportDailyReport (
                                       idTrip        BIGINT NOT NULL,
                                       idDailyReport BIGINT NOT NULL,
                                       reportDate    DATE,
                                       reportNumber  VARCHAR(100),
                                       idAuditLog    BIGINT NOT NULL,
                                       CONSTRAINT pk_tripreportdailyreport PRIMARY KEY (idTrip, idDailyReport)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE AdminManageInstitution (
                                        idPerson      BIGINT NOT NULL,
                                        idInstitution BIGINT NOT NULL,
                                        idAuditLog    BIGINT NOT NULL,
                                        CONSTRAINT pk_adminmanageinstitution PRIMARY KEY (idPerson, idInstitution)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE AdminReceiveDailyReport (
                                         idAdministrator BIGINT NOT NULL,
                                         idDailyReport   BIGINT NOT NULL,
                                         idAuditLog      BIGINT NOT NULL,
                                         CONSTRAINT pk_adminreceivedailyreport PRIMARY KEY (idAdministrator, idDailyReport)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE CredentialHasTypeOfCredential (
                                               idCredential       BIGINT NOT NULL,
                                               idTypeOfCredential BIGINT NOT NULL,
                                               idAuditLog         BIGINT NOT NULL,
                                               CONSTRAINT pk_credhtyofcred PRIMARY KEY (idCredential, idTypeOfCredential)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE PassengerQueryTrip (
                                    idUser     BIGINT NOT NULL,
                                    idTrip     BIGINT NOT NULL,
                                    idAuditLog BIGINT NOT NULL,
                                    CONSTRAINT pk_passengerQueryTrip PRIMARY KEY (idUser, idTrip)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE PassengerJoinTrip (
                                   idUser     BIGINT NOT NULL,
                                   idTrip     BIGINT NOT NULL,
                                   joinDate   DATE NOT NULL,
                                   idAuditLog BIGINT  NOT NULL,
                                   CONSTRAINT pk_passengerJoinTrip PRIMARY KEY (idUser, idTrip)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE TripHasTripStatus (
                                   idTrip       BIGINT NOT NULL,
                                   idTripStatus BIGINT NOT NULL,
                                   idAuditLog   BIGINT NOT NULL,
                                   CONSTRAINT pk_triphastripstatus PRIMARY KEY (idTrip, idTripStatus)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE StopHasCoordinateLocation (
                                           idStop               BIGINT NOT NULL,
                                           idCoordinateLocation BIGINT NOT NULL,
                                           idAuditLog           BIGINT NOT NULL,
                                           CONSTRAINT pk_stophascoordloc PRIMARY KEY (idStop, idCoordinateLocation)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE TripHasStopHasPaymentMethod (
                                             idPaymentMethod BIGINT NOT NULL,
                                             idTrip          BIGINT NOT NULL,
                                             idStop          BIGINT NOT NULL,
                                             idAuditLog      BIGINT NOT NULL,
                                             CONSTRAINT pk_tripstoppaymethod PRIMARY KEY (idPaymentMethod, idTrip, idStop)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE TripHasStop (
                             idTrip           BIGINT   NOT NULL,
                             idStop           BIGINT   NOT NULL,
                             estimatedArrival DATE   NOT NULL,
                             stopCost         FLOAT  NOT NULL,
                             numberStop       INT    NOT NULL,
                             idAuditLog       BIGINT   NOT NULL,
                             CONSTRAINT pk_triphasstop PRIMARY KEY (idTrip, idStop),
                             CONSTRAINT ck_THS_stopCost CHECK (stopCost >= 0),
                             CONSTRAINT ck_THS_numberStop CHECK (numberStop >= 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE LogBookHasEntityModified (
                                          idLogBook        BIGINT NOT NULL,
                                          idEntityModified BIGINT NOT NULL,
                                          idAuditLog       BIGINT NOT NULL,
                                          CONSTRAINT pk_logbookhasentmod PRIMARY KEY (idLogBook, idEntityModified)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE AttrModHasEntMod (
                                  idLogBook           BIGINT NOT NULL,
                                  idAttributeModified BIGINT NOT NULL,
                                  idAuditLog          BIGINT NOT NULL,
                                  CONSTRAINT pk_attrmodhasentmod PRIMARY KEY (idLogBook, idAttributeModified)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;