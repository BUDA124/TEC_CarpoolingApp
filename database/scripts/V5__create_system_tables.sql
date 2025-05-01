-- Creating table DAILYREPORT
CREATE TABLE DailyReport (
                             id            NUMBER,
                             reportType    VARCHAR2(100) CONSTRAINT nn_DailyReport_reportType NOT NULL,
                             idInstitution NUMBER        CONSTRAINT nn_DailyReport_idInstitution NOT NULL,
                             idAuditLog    NUMBER        CONSTRAINT nn_DailyReport_idAuditLog NOT NULL,
                             CONSTRAINT pk_dailyreport PRIMARY KEY (id)
                                 USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table INSTITUTION
CREATE TABLE Institution (
                             id                NUMBER,
                             emailDomain       VARCHAR2(100) CONSTRAINT nn_Institution_emaildomain NOT NULL,
                             institutionName   VARCHAR2(255) CONSTRAINT nn_Institution_institutionname NOT NULL,
                             website           VARCHAR2(512),
                             idAuditLog        NUMBER        CONSTRAINT nn_Institution_idAuditLog NOT NULL,
                             CONSTRAINT pk_Institution_id PRIMARY KEY (id)
                                 USING INDEX TABLESPACE CARPOOLING_INDX,
                             CONSTRAINT uk_Institution_emaildomain UNIQUE (emailDomain)
                                 USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table CONTACTPHONENUMBER
CREATE TABLE CONTACTPHONENUMBER (
                                    id            NUMBER,
                                    phoneNumber   VARCHAR2(20) CONSTRAINT nn_CPNumber_number NOT NULL,
                                    idInstitution NUMBER       CONSTRAINT nn_CPNumber_idInstitution NOT NULL,
                                    idAuditLog    NUMBER       CONSTRAINT nn_CPNumber_idAuditLog NOT NULL,
                                    CONSTRAINT pk_ContactNumber PRIMARY KEY (id)
                                        USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table CONTACTEMAIL
CREATE TABLE ContactEmail (
                              id            NUMBER,
                              address       VARCHAR2(255) CONSTRAINT nn_ContactEmail_address NOT NULL,
                              idInstitution NUMBER        CONSTRAINT nn_ContactEmail_idInstitution NOT NULL,
                              idAuditLog    NUMBER        CONSTRAINT nn_ContactEmail_idAuditLog NOT NULL,
                              CONSTRAINT pk_ContactEmail PRIMARY KEY (id)
                                  USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table ADMINISTRATOR
CREATE TABLE Administrator (
                               idPerson   NUMBER,
                               idAuditLog NUMBER CONSTRAINT nn_Administrator_idAuditLog NOT NULL,
                               CONSTRAINT pk_administrator PRIMARY KEY (idPerson)
                                   USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table ACCESSSTATUS
CREATE TABLE ACCESSSTATUS (
                              id              NUMBER,
                              status          VARCHAR2(50) CONSTRAINT nn_accessSts_status NOT NULL,
                              idAdministrator NUMBER       CONSTRAINT nn_accessSts_idAdministrator NOT NULL,
                              idAuditLog      NUMBER       CONSTRAINT nn_accessSts_idAuditLog NOT NULL,
                              CONSTRAINT pk_accessStatus PRIMARY KEY (id)
                                  USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table PERSON
CREATE TABLE Person (
                        id             NUMBER,
                        firstName      VARCHAR2(100) CONSTRAINT nn_Person_firstName NOT NULL,
                        secondName     VARCHAR2(100),
                        firstSurname   VARCHAR2(100) CONSTRAINT nn_Person_firstSurname NOT NULL,
                        secondSurname  VARCHAR2(100),
                        birthdate      DATE          CONSTRAINT nn_Person_birthdate NOT NULL,
                        nationality    VARCHAR2(100) CONSTRAINT nn_Person_nationality NOT NULL,
                        profilePicture BLOB,
                        idInstitution  NUMBER        CONSTRAINT nn_Person_idInstitution NOT NULL,
                        idAuditLog     NUMBER        CONSTRAINT nn_Person_idAuditLog NOT NULL,
                        idGender       NUMBER        CONSTRAINT nn_Person_idGender NOT NULL,
                        CONSTRAINT pk_Person PRIMARY KEY (id)
                            USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table PHONENUMBER
CREATE TABLE PhoneNumber (
                             id          NUMBER,
                             phoneNumber VARCHAR2(20) CONSTRAINT nn_PhoneNumber_number NOT NULL,
                             idPerson    NUMBER       CONSTRAINT nn_PhoneNumber_idPerson NOT NULL,
                             idAuditLog  NUMBER       CONSTRAINT nn_PhoneNumber_idAuditLog NOT NULL,
                             CONSTRAINT pk_PhoneNumber PRIMARY KEY (id)
                                 USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table EMAIL
CREATE TABLE Email (
                       id           NUMBER,
                       emailAddress VARCHAR2(255) CONSTRAINT nn_Email_emailAddress NOT NULL,
                       idPerson     NUMBER        CONSTRAINT nn_Email_idPerson NOT NULL,
                       idAuditLog   NUMBER        CONSTRAINT nn_Email_idAuditLog NOT NULL,
                       CONSTRAINT pk_Email PRIMARY KEY (id)
                           USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table CREDENTIAL
CREATE TABLE Credential (
                            id                 NUMBER,
                            isActive           NUMBER(1)       CONSTRAINT nn_Credential_isActive NOT NULL,
                            CONSTRAINT ck_Credential_isActive CHECK (isActive IN (0, 1)),
                            numberOfCredential VARCHAR2(255)   CONSTRAINT nn_Credential_numCredential NOT NULL,
                            idPerson           NUMBER          CONSTRAINT nn_Credential_idPerson NOT NULL,
                            idAuditLog         NUMBER          CONSTRAINT nn_Credential_idAuditLog NOT NULL,
                            CONSTRAINT pk_Credential PRIMARY KEY (id)
                                USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table TYPEOFCREDENTIAL
CREATE TABLE TypeOfCredential (
                                  id         NUMBER,
                                  type       VARCHAR2(50) CONSTRAINT nn_TypeOfCredential_type NOT NULL,
                                  idAuditLog NUMBER       CONSTRAINT nn_TypeOfCredential_idAuditLog NOT NULL,
                                  CONSTRAINT pk_TypeOfCredential PRIMARY KEY (id)
                                      USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table GENDER
CREATE TABLE Gender (
                        id          NUMBER,
                        genderName  VARCHAR2(50) CONSTRAINT nn_Gender_genderName NOT NULL,
                        idAuditLog  NUMBER       CONSTRAINT nn_Gender_idAuditLog NOT NULL,
                        CONSTRAINT pk_Gender PRIMARY KEY (id)
                            USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table PERSONALUSER
CREATE TABLE PERSONALUSER (
                              id             NUMBER,
                              password       VARCHAR2(255) CONSTRAINT nn_USER_password NOT NULL,
                              username       VARCHAR2(100) CONSTRAINT nn_USER_username NOT NULL,
                              registrationDate DATE        CONSTRAINT nn_USER_registrationDate NOT NULL,
                              idPerson       NUMBER        CONSTRAINT nn_USER_idPerson NOT NULL,
                              idAuditLog     NUMBER        CONSTRAINT nn_USER_idAuditLog NOT NULL,
                              CONSTRAINT pk_USER PRIMARY KEY (id)
                                  USING INDEX TABLESPACE CARPOOLING_INDX,
                              CONSTRAINT uk_USER_username UNIQUE (username)
                                  USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table USERSTATUS
CREATE TABLE USERSTATUS (
                            id             NUMBER,
                            status         VARCHAR2(50),
                            idAuditLog     NUMBER       CONSTRAINT nn_UserStatus_idAuditLog NOT NULL,
                            CONSTRAINT pk_UserStatus PRIMARY KEY (id)
                                USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table INSTITUTIONALEMAIL
CREATE TABLE INSTITUTIONALEMAIL (
                                    id                NUMBER,
                                    emailAddress      VARCHAR2(255) CONSTRAINT nn_Institutional_emailAddress NOT NULL,
                                    idUser            NUMBER        CONSTRAINT nn_Institutional_idPerson NOT NULL,
                                    idAuditLog        NUMBER        CONSTRAINT nn_Institutional_idAuditLog NOT NULL,
                                    CONSTRAINT pk_InstitutionalEmail PRIMARY KEY (id)
                                        USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table DRIVER
CREATE TABLE Driver (
                        idPerson   NUMBER,
                        idAuditLog NUMBER CONSTRAINT nn_Driver_idAuditlog NOT NULL,
                        CONSTRAINT pk_driver PRIMARY KEY (idPerson)
                            USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table VEHICLE
CREATE TABLE Vehicle (
                         id            NUMBER,
                         isVerified    NUMBER(1)      CONSTRAINT nn_Vehicle_isVerified NOT NULL,
                         CONSTRAINT ck_Vehicle_isVerified CHECK (isVerified IN (0, 1)),
                         brand         VARCHAR2(100)  CONSTRAINT nn_Vehicle_brand NOT NULL,
                         carPhoto      BLOB,
                         plateNumber   VARCHAR2(20)   CONSTRAINT nn_Vehicle_plateNumber NOT NULL,
                         carModel      VARCHAR2(100)  CONSTRAINT nn_Vehicle_carModel NOT NULL,
                         seatQuantity  NUMBER         CONSTRAINT nn_Vehicle_seatQuantity NOT NULL,
                         CONSTRAINT ck_Vehicle_seatQuantity CHECK (seatQuantity > 0),
                         idDriver      NUMBER         CONSTRAINT nn_Vehicle_idDriver NOT NULL,
                         idAuditLog    NUMBER         CONSTRAINT nn_Vehicle_idAuditlog NOT NULL,
                         CONSTRAINT pk_Vehicle PRIMARY KEY (id)
                             USING INDEX TABLESPACE CARPOOLING_INDX,
                         CONSTRAINT uk_Vehicle_plateNumber UNIQUE (plateNumber)
                             USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table PASSENGER
CREATE TABLE Passenger (
                           idPerson   NUMBER,
                           idAuditLog NUMBER CONSTRAINT nn_Passenger_auditlog NOT NULL,
                           CONSTRAINT pk_passenger PRIMARY KEY (idPerson)
                               USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table TRIP
CREATE TABLE Trip (
                      id                 NUMBER,
                      maximunPassengers  NUMBER          CONSTRAINT nn_Trip_maximunPassengers NOT NULL,
                      CONSTRAINT ck_Trip_maximunPassengers CHECK (maximunPassengers > 0),
                      departureDateTime  TIMESTAMP       CONSTRAINT nn_Trip_departureDateTime NOT NULL,
                      durationEstimate   NUMBER          CONSTRAINT nn_Trip_durationEstimate NOT NULL,
                      CONSTRAINT ck_Trip_durationEstimate CHECK (durationEstimate > 0),
                      arrivalDateTime    TIMESTAMP       CONSTRAINT nn_Trip_arrivalDateTime NOT NULL,
                      idDriver           NUMBER          CONSTRAINT nn_Trip_idDriver NOT NULL,
                      idPriceStatus      NUMBER,
                      idVehicle          NUMBER          CONSTRAINT nn_Trip_idVehicle NOT NULL,
                      idAuditLog         NUMBER          CONSTRAINT nn_Trip_idAuditLog NOT NULL,
                      CONSTRAINT pk_Trip PRIMARY KEY (id)
                          USING INDEX TABLESPACE CARPOOLING_INDX,
                      CONSTRAINT ck_Trip_arrivalDateTime CHECK (arrivalDateTime > departureDateTime)
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table PRICESTATUS
CREATE TABLE PriceStatus (
                             id               NUMBER,
                             status           VARCHAR2(50),
                             idAuditLog       NUMBER       CONSTRAINT nn_PriceStatus_idAuditLog NOT NULL,
                             CONSTRAINT pk_PriceStatus PRIMARY KEY (id)
                                 USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table TRIPSTATUS
CREATE TABLE TripStatus (
                            id               NUMBER,
                            status           VARCHAR2(50),
                            idAuditLog       NUMBER       CONSTRAINT nn_TripStatus_idAuditLog NOT NULL,
                            CONSTRAINT pk_TripStatus PRIMARY KEY (id)
                                USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table STOP
CREATE TABLE Stop (
                      id         NUMBER,
                      address    VARCHAR2(255) CONSTRAINT nn_Stop_address NOT NULL,
                      idDistrict NUMBER        CONSTRAINT nn_Stop_idDistrict NOT NULL,
                      idStarTrip NUMBER,
                      idEndTrip  NUMBER,
                      idAuditLog NUMBER        CONSTRAINT nn_Stop_idAuditLog NOT NULL,
                      CONSTRAINT pk_Stop PRIMARY KEY (id)
                          USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table COORDINATELOCATION
CREATE TABLE CoordinateLocation (
                                    id          NUMBER,
                                    yCoordinate NUMBER CONSTRAINT nn_yCoordinate NOT NULL,
                                    xCoordinate NUMBER CONSTRAINT nn_xCoordinate NOT NULL,
                                    idAuditLog  NUMBER CONSTRAINT nn_Coordinates_idAuditLog NOT NULL,
                                    CONSTRAINT pk_CoordinateLocation PRIMARY KEY (id)
                                        USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table DISTRICT
CREATE TABLE District (
                          id         NUMBER,
                          name       VARCHAR2(255) CONSTRAINT nn_District_name NOT NULL,
                          idCanton   NUMBER        CONSTRAINT nn_District_idCanton NOT NULL,
                          idAuditLog NUMBER        CONSTRAINT nn_District_idAuditLog NOT NULL,
                          CONSTRAINT pk_District PRIMARY KEY (id)
                              USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table CANTON
CREATE TABLE Canton (
                        id         NUMBER,
                        name       VARCHAR2(255) CONSTRAINT nn_Canton_name NOT NULL,
                        idProvince NUMBER        CONSTRAINT nn_Canton_idProvince NOT NULL,
                        idAuditLog NUMBER        CONSTRAINT nn_Canton_idAuditLog NOT NULL,
                        CONSTRAINT pk_Canton PRIMARY KEY (id)
                            USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table PROVINCE
CREATE TABLE Province (
                          id         NUMBER,
                          name       VARCHAR2(255) CONSTRAINT nn_Province_name NOT NULL,
                          idCountry  NUMBER        CONSTRAINT nn_Province_idCountry NOT NULL,
                          idAuditLog NUMBER        CONSTRAINT nn_Province_idAuditLog NOT NULL,
                          CONSTRAINT pk_Province PRIMARY KEY (id)
                              USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table COUNTRY
CREATE TABLE Country (
                         id         NUMBER,
                         name       VARCHAR2(255) CONSTRAINT nn_Country_name NOT NULL,
                         idAuditLog NUMBER        CONSTRAINT nn_Country_idAuditLog NOT NULL,
                         CONSTRAINT pk_Country PRIMARY KEY (id)
                             USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table PAYMENTMETHOD
CREATE TABLE PaymentMethod (
                               id         NUMBER,
                               method     VARCHAR2(100) CONSTRAINT nn_PaymentMethod_method NOT NULL,
                               idAuditLog NUMBER        CONSTRAINT nn_PaymentMethod_idAuditLog NOT NULL,
                               CONSTRAINT pk_PaymentMethod PRIMARY KEY (id)
                                   USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table AUDITLOG
CREATE TABLE AuditLog (
                          id               NUMBER,
                          creationDate     TIMESTAMP     CONSTRAINT nn_AuditLog_creationDate NOT NULL,
                          createdBy        VARCHAR2(255) CONSTRAINT nn_AuditLog_createdBy NOT NULL,
                          lastUpdateDate   TIMESTAMP,
                          updatedBy        VARCHAR2(255),
                          CONSTRAINT pk_AuditLog PRIMARY KEY (id)
                              USING INDEX TABLESPACE CARPOOLING_INDX,
                          CONSTRAINT ck_AuditLog_update_consistency CHECK ( (lastUpdateDate IS NULL AND updatedBy IS NULL) OR
                                                                            (lastUpdateDate IS NOT NULL AND updatedBy IS NOT NULL) )
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table PARAMETER
CREATE TABLE Parameter (
                           id         NUMBER,
                           name       VARCHAR2(100) CONSTRAINT nn_Parameter_name NOT NULL,
                           value      VARCHAR2(255) CONSTRAINT nn_Parameter_value NOT NULL,
                           idAuditLog NUMBER        CONSTRAINT nn_Parameter_idAuditLog NOT NULL,
                           CONSTRAINT pk_Parameter PRIMARY KEY (id)
                               USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table LOGBOOK
CREATE TABLE LogBook (
                         id          NUMBER,
                         logDate     DATE          CONSTRAINT nn_LogBook_logDate NOT NULL,
                         logTime     TIMESTAMP     CONSTRAINT nn_LogBook_logTime NOT NULL,
                         description VARCHAR2(255) CONSTRAINT nn_LogBook_description NOT NULL,
                         idAuditLog  NUMBER        CONSTRAINT nn_LogBook_idAuditLog NOT NULL,
                         CONSTRAINT pk_LogBook PRIMARY KEY (id)
                             USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table ENTITYMODIFIED
CREATE TABLE EntityModified (
                                id          NUMBER,
                                entityName  VARCHAR2(100) CONSTRAINT nn_EntityModified_entityName NOT NULL,
                                idAuditLog  NUMBER        CONSTRAINT nn_EntityModified_idAuditLog NOT NULL,
                                CONSTRAINT pk_EntityModified PRIMARY KEY (id)
                                    USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table ATTRIBUTEMODIFIED
CREATE TABLE AttributeModified (
                                   id               NUMBER,
                                   newValue         VARCHAR2(255), -- Retaining nullability as change might be TO null
                                   oldValue         VARCHAR2(255), -- Retaining nullability as change might be FROM null
                                   attributeName    VARCHAR2(100) CONSTRAINT nn_attributeName NOT NULL,
                                   idEntityModified NUMBER        CONSTRAINT nn_AttMod_idEntityModified NOT NULL,
                                   idAuditLog       NUMBER        CONSTRAINT nn_AttMod_idAuditLog NOT NULL,
                                   CONSTRAINT pk_AttributeModified PRIMARY KEY (id)
                                       USING INDEX TABLESPACE CARPOOLING_INDX,
                                   CONSTRAINT ck_AttMod_ValuesDiffer CHECK (newValue <> oldValue OR
                                                                            (newValue IS NULL AND oldValue IS NOT NULL)
                                                                                OR (newValue IS NOT NULL AND oldValue IS NULL))
)
    TABLESPACE CARPOOLING_DATA;


-- Creating table TRIPREPORTDAILYREPORT (NXN)
CREATE TABLE TripReportDailyReport (
                                       idTrip        NUMBER,
                                       idDailyReport NUMBER,
                                       reportDate    DATE,
                                       reportNumber  VARCHAR2(100),
                                       idAuditLog    NUMBER         CONSTRAINT nn_TRDR_idAuditLog NOT NULL,
                                       CONSTRAINT pk_tripreportdailyreport PRIMARY KEY (idTrip, idDailyReport)
                                           USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table ADMINMANAGEINSTITUTION (NXN)
CREATE TABLE AdminManageInstitution (
                                        idPerson      NUMBER,
                                        idInstitution NUMBER,
                                        idAuditLog    NUMBER CONSTRAINT nn_AMI_idAuditLog NOT NULL,
                                        CONSTRAINT pk_adminmanageinstitution PRIMARY KEY (idPerson, idInstitution)
                                            USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table ADMINRECEIVEDAIILYREPORT (NXN)
CREATE TABLE AdminReceiveDailyReport (
                                         idAdministrator NUMBER,
                                         idDailyReport   NUMBER,
                                         idAuditLog      NUMBER CONSTRAINT nn_ARDR_idAuditLog NOT NULL,
                                         CONSTRAINT pk_adminreceivedailyreport PRIMARY KEY (idAdministrator, idDailyReport)
                                             USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table CREDENTIALHASTYPEOFCREDENTIAL (NXN)
CREATE TABLE CredentialHasTypeOfCredential (
                                               idCredential       NUMBER,
                                               idTypeOfCredential NUMBER,
                                               idAuditLog         NUMBER CONSTRAINT nn_CHTOC_idAuditLog NOT NULL,
                                               CONSTRAINT pk_credhtyofcred PRIMARY KEY (idCredential, idTypeOfCredential)
                                                   USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table PASSENGERQUERYTRIP (NXN)
CREATE TABLE PassengerQueryTrip (
                                    idUser     NUMBER,
                                    idTrip     NUMBER,
                                    idAuditLog NUMBER CONSTRAINT nn_PassengerQueryTrip_auditlog NOT NULL,
                                    CONSTRAINT pk_passengerQueryTrip PRIMARY KEY (idUser, idTrip)
                                        USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table PASSENGERJOINTRIP (NXN)
CREATE TABLE PassengerJoinTrip (
                                   idUser     NUMBER,
                                   idTrip     NUMBER,
                                   joinDate   DATE   CONSTRAINT nn_PassengerJoinTrip_joinDate NOT NULL,
                                   idAuditLog NUMBER CONSTRAINT nn_PassengerJoinTrip_auditlog NOT NULL,
                                   CONSTRAINT pk_passengerJoinTrip PRIMARY KEY (idUser, idTrip)
                                       USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table TRIPHASTRIPSTATUS (NXN)
CREATE TABLE TripHasTripStatus (
                                   idTrip       NUMBER,
                                   idTripStatus NUMBER,
                                   idAuditLog   NUMBER CONSTRAINT nn_TripHasTripStatus_auditlog NOT NULL,
                                   CONSTRAINT pk_triphastripstatus PRIMARY KEY (idTrip, idTripStatus)
                                       USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table STOPHASCOORDINATELOCATION (NXN)
CREATE TABLE StopHasCoordinateLocation (
                                           idStop               NUMBER,
                                           idCoordinateLocation NUMBER,
                                           idAuditLog           NUMBER CONSTRAINT nn_SHCL_auditlog NOT NULL,
                                           CONSTRAINT pk_stophascoordloc PRIMARY KEY (idStop, idCoordinateLocation)
                                               USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table TRIPHASSTOPHASPAYMENTMETHOD (NXN)
CREATE TABLE TripHasStopHasPaymentMethod (
                                             idPaymentMethod NUMBER,
                                             idTrip          NUMBER,
                                             idStop          NUMBER,
                                             idAuditLog      NUMBER CONSTRAINT nn_TripStopPayMethod_auditlog NOT NULL,
                                             CONSTRAINT pk_tripstoppaymethod PRIMARY KEY (idPaymentMethod, idTrip, idStop)
                                                 USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table TRIPHASSTOP (NXN)
CREATE TABLE TripHasStop (
                             idTrip           NUMBER,
                             idStop           NUMBER,
                             estimatedArrival DATE   CONSTRAINT nn_THS_estimatedArrival NOT NULL,
                             stopCost         NUMBER CONSTRAINT nn_THS_stopCost NOT NULL,
                                                     CONSTRAINT ck_THS_stopCost CHECK (stopCost >= 0),
                             numberStop       NUMBER CONSTRAINT nn_THS_numberStop NOT NULL,
                                                     CONSTRAINT ck_THS_numberStop CHECK (numberStop >= 0),
                             idAuditLog       NUMBER CONSTRAINT nn_THS_auditlog NOT NULL,
                             CONSTRAINT pk_triphasstop PRIMARY KEY (idTrip, idStop)
                                 USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table LOGBOOKHASENTITYMODIFIED (NXN)
CREATE TABLE LogBookHasEntityModified (
                                          idLogBook        NUMBER,
                                          idEntityModified NUMBER,
                                          idAuditLog       NUMBER CONSTRAINT nn_LogBookHasEntMod_auditlog NOT NULL,
                                          CONSTRAINT pk_logbookhasentmod PRIMARY KEY (idLogBook, idEntityModified)
                                              USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;

-- Creating table ATTRMODHASENTMOD (NXN)
CREATE TABLE AttrModHasEntMod (
                                  idLogBook           NUMBER,
                                  idAttributeModified NUMBER,
                                  idAuditLog          NUMBER CONSTRAINT nn_AMHEM_auditlog NOT NULL,
                                  CONSTRAINT pk_attrmodhasentmod PRIMARY KEY (idLogBook, idAttributeModified)
                                      USING INDEX TABLESPACE CARPOOLING_INDX
)
    TABLESPACE CARPOOLING_DATA;
