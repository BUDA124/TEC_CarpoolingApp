-- ============================================================================
-- SCRIPT DE INSERCIÓN DE DATOS PARA SISTEMA DE CARPOOLING
-- ============================================================================


-- ============================================================================
-- 1. Insertar AuditLog inicial
-- ============================================================================
INSERT INTO AuditLog (id, creationDate, createdBy, lastUpdateDate, updatedBy)
VALUES (seq_auditlog.NEXTVAL, TIMESTAMP '2025-05-17 10:00:00', 'CARPOOLING_APP_USER', TIMESTAMP '2025-05-17 10:00:00', 'CARPOOLING_APP_USER');

-- ============================================================================
-- 2. Insertar datos geográficos (Country -> Province -> Canton -> District)
-- ============================================================================
INSERT INTO Country (id, name, idAuditLog)
VALUES (seq_country.NEXTVAL, 'Costa Rica', 1);

INSERT INTO Province (id, name, idCountry, idAuditLog)
VALUES (seq_province.NEXTVAL, 'San José', 1, 1);

INSERT INTO Province (id, name, idCountry, idAuditLog)
VALUES (seq_province.NEXTVAL, 'Cartago', 1, 1);

INSERT INTO Canton (id, name, idProvince, idAuditLog)
VALUES (seq_canton.NEXTVAL, 'Montes de Oca', 1, 1);

INSERT INTO Canton (id, name, idProvince, idAuditLog)
VALUES (seq_canton.NEXTVAL, 'Cartago', 2, 1);

INSERT INTO District (id, name, idCanton, idAuditLog)
VALUES (seq_district.NEXTVAL, 'San Pedro', 1, 1);

INSERT INTO District (id, name, idCanton, idAuditLog)
VALUES (seq_district.NEXTVAL, 'Oriental', 2, 1);

-- ============================================================================
-- 3. Insertar coordenadas de ubicación
-- ============================================================================
INSERT INTO CoordinateLocation (id, yCoordinate, xCoordinate, idAuditLog)
VALUES (seq_coordinatelocation.NEXTVAL, 9.9370, -84.0528, 1); -- UCR

INSERT INTO CoordinateLocation (id, yCoordinate, xCoordinate, idAuditLog)
VALUES (seq_coordinatelocation.NEXTVAL, 9.8626, -83.9145, 1); -- TEC

-- ============================================================================
-- 4. Insertar instituciones universitarias
-- ============================================================================
INSERT INTO Institution (id, emailDomain, institutionName, website, idAuditLog)
VALUES (seq_institution.NEXTVAL, 'ucr.ac.cr', 'Universidad de Costa Rica', 'https://www.ucr.ac.cr', 1);

INSERT INTO Institution (id, emailDomain, institutionName, website, idAuditLog)
VALUES (seq_institution.NEXTVAL, 'tec.ac.cr', 'Instituto Tecnológico de Costa Rica', 'https://www.tec.ac.cr', 1);

-- ============================================================================
-- 5. Insertar información de contacto de las instituciones
-- ============================================================================
INSERT INTO ContactPhoneNumber (id, phoneNumber, idInstitution, idAuditLog)
VALUES (seq_contactphonenumber.NEXTVAL, '2511-2000', 1, 1);

INSERT INTO ContactPhoneNumber (id, phoneNumber, idInstitution, idAuditLog)
VALUES (seq_contactphonenumber.NEXTVAL, '2550-2000', 2, 1);

INSERT INTO ContactEmail (id, address, idInstitution, idAuditLog)
VALUES (seq_contactemail.NEXTVAL, 'info@ucr.ac.cr', 1, 1);

INSERT INTO ContactEmail (id, address, idInstitution, idAuditLog)
VALUES (seq_contactemail.NEXTVAL, 'info@tec.ac.cr', 2, 1);

-- ============================================================================
-- 6. Insertar géneros
-- ============================================================================
INSERT INTO Gender (id, genderName, idAuditLog)
VALUES (seq_gender.NEXTVAL, 'Masculino', 1);

INSERT INTO Gender (id, genderName, idAuditLog)
VALUES (seq_gender.NEXTVAL, 'Femenino', 1);

INSERT INTO Gender (id, genderName, idAuditLog)
VALUES (seq_gender.NEXTVAL, 'No Binario', 1);

-- ============================================================================
-- 7. Insertar personas
-- ============================================================================
INSERT INTO Person (id, firstName, secondName, firstSurname, secondSurname, birthdate, nationality, idInstitution, idAuditLog, idGender)
VALUES (seq_person.NEXTVAL, 'Carlos', 'Alberto', 'Rodríguez', NULL, DATE '1995-03-15', 'Costarricense', 1, 1, 1);

INSERT INTO Person (id, firstName, secondName, firstSurname, secondSurname, birthdate, nationality, idInstitution, idAuditLog, idGender)
VALUES (seq_person.NEXTVAL, 'María', NULL, 'Fernández', 'Castro', DATE '1997-07-22', 'Costarricense', 1, 1, 2);

INSERT INTO Person (id, firstName, secondName, firstSurname, secondSurname, birthdate, nationality, idInstitution, idAuditLog, idGender)
VALUES (seq_person.NEXTVAL, 'José', 'Luis', 'Vargas', 'Solano', DATE '1996-11-08', 'Costarricense', 2, 1, 1);

INSERT INTO Person (id, firstName, secondName, firstSurname, secondSurname, birthdate, nationality, idInstitution, idAuditLog, idGender)
VALUES (seq_person.NEXTVAL, 'Ana', 'Patricia', 'Jiménez', NULL, DATE '1994-05-30', 'Costarricense', 2, 1, 2);

-- ============================================================================
-- 8. Insertar números de teléfono de personas
-- ============================================================================
INSERT INTO PhoneNumber (id, phoneNumber, idPerson, idAuditLog)
VALUES (seq_phonenumber.NEXTVAL, '8888-1234', 1, 1);

INSERT INTO PhoneNumber (id, phoneNumber, idPerson, idAuditLog)
VALUES (seq_phonenumber.NEXTVAL, '8777-5678', 2, 1);

INSERT INTO PhoneNumber (id, phoneNumber, idPerson, idAuditLog)
VALUES (seq_phonenumber.NEXTVAL, '8666-9012', 3, 1);

INSERT INTO PhoneNumber (id, phoneNumber, idPerson, idAuditLog)
VALUES (seq_phonenumber.NEXTVAL, '8555-3456', 4, 1);

-- ============================================================================
-- 9. Insertar emails personales
-- ============================================================================
INSERT INTO Email (id, emailAddress, idPerson, idAuditLog)
VALUES (seq_email.NEXTVAL, 'carlos.rodriguez@gmail.com', 1, 1);

INSERT INTO Email (id, emailAddress, idPerson, idAuditLog)
VALUES (seq_email.NEXTVAL, 'maria.fernandez@gmail.com', 2, 1);

INSERT INTO Email (id, emailAddress, idPerson, idAuditLog)
VALUES (seq_email.NEXTVAL, 'jose.vargas@gmail.com', 3, 1);

INSERT INTO Email (id, emailAddress, idPerson, idAuditLog)
VALUES (seq_email.NEXTVAL, 'ana.jimenez@gmail.com', 4, 1);

-- ============================================================================
-- 10. Insertar tipos de credencial
-- ============================================================================
INSERT INTO TypeOfCredential (id, type, idAuditLog)
VALUES (seq_typeofcredential.NEXTVAL, 'Licencia', 1);

INSERT INTO TypeOfCredential (id, type, idAuditLog)
VALUES (seq_typeofcredential.NEXTVAL, 'Carnet estudiantil', 1);

-- ============================================================================
-- 11. Insertar credenciales
-- ============================================================================
INSERT INTO Credential (id, isActive, numberOfCredential, idPerson, idAuditLog)
VALUES (seq_credential.NEXTVAL, 1, 'LIC-123456789', 1, 1);

INSERT INTO Credential (id, isActive, numberOfCredential, idPerson, idAuditLog)
VALUES (seq_credential.NEXTVAL, 1, '2022098765', 1, 1);

INSERT INTO Credential (id, isActive, numberOfCredential, idPerson, idAuditLog)
VALUES (seq_credential.NEXTVAL, 1, 'LIC-987654321', 3, 1);

INSERT INTO Credential (id, isActive, numberOfCredential, idPerson, idAuditLog)
VALUES (seq_credential.NEXTVAL, 1, '2023345678', 3, 1);

-- ============================================================================
-- 12. Insertar estados de usuario
-- ============================================================================
INSERT INTO UserStatus (id, status, idAuditLog)
VALUES (seq_userstatus.NEXTVAL, 'Activo', 1);

INSERT INTO UserStatus (id, status, idAuditLog)
VALUES (seq_userstatus.NEXTVAL, 'Inactivo', 1);

INSERT INTO UserStatus (id, status, idAuditLog)
VALUES (seq_userstatus.NEXTVAL, 'Suspendido', 1);

-- ============================================================================
-- 13. Insertar usuarios personales
-- ============================================================================
INSERT INTO PersonalUser (id, password, username, registrationDate, idUserStatus, idPerson, idAuditLog)
VALUES (seq_personaluser.NEXTVAL, 'password123', 'carlos.rodriguez', DATE '2025-01-15', 1, 1, 1);

INSERT INTO PersonalUser (id, password, username, registrationDate, idUserStatus, idPerson, idAuditLog)
VALUES (seq_personaluser.NEXTVAL, 'password456', 'maria.fernandez', DATE '2025-01-20', 1, 2, 1);

INSERT INTO PersonalUser (id, password, username, registrationDate, idUserStatus, idPerson, idAuditLog)
VALUES (seq_personaluser.NEXTVAL, 'password789', 'jose.vargas', DATE '2025-02-10', 1, 3, 1);

INSERT INTO PersonalUser (id, password, username, registrationDate, idUserStatus, idPerson, idAuditLog)
VALUES (seq_personaluser.NEXTVAL, 'password012', 'ana.jimenez', DATE '2025-02-15', 1, 4, 1);

-- ============================================================================
-- 14. Insertar emails institucionales
-- ============================================================================
INSERT INTO InstitutionalEmail (id, emailAddress, idUser, idAuditLog)
VALUES (seq_institutionalemail.NEXTVAL, 'carlos.rodriguez@ucr.ac.cr', 1, 1);

INSERT INTO InstitutionalEmail (id, emailAddress, idUser, idAuditLog)
VALUES (seq_institutionalemail.NEXTVAL, 'maria.fernandez@ucr.ac.cr', 2, 1);

INSERT INTO InstitutionalEmail (id, emailAddress, idUser, idAuditLog)
VALUES (seq_institutionalemail.NEXTVAL, 'jose.vargas@tec.ac.cr', 3, 1);

INSERT INTO InstitutionalEmail (id, emailAddress, idUser, idAuditLog)
VALUES (seq_institutionalemail.NEXTVAL, 'ana.jimenez@tec.ac.cr', 4, 1);

-- ============================================================================
-- 15. Insertar administradores
-- ============================================================================
INSERT INTO Administrator (idPerson, idAuditLog)
VALUES (4, 1); -- Ana es administradora

-- ============================================================================
-- 16. Insertar estados de acceso
-- ============================================================================
INSERT INTO AccessStatus (id, status, idAdministrator, idAuditLog)
VALUES (seq_accessstatus.NEXTVAL, 'Acceso total', 4, 1);

-- ============================================================================
-- 17. Insertar conductores
-- ============================================================================
INSERT INTO Driver (idPerson, idAuditLog)
VALUES (1, 1); -- Carlos es conductor

INSERT INTO Driver (idPerson, idAuditLog)
VALUES (3, 1); -- José es conductor

-- ============================================================================
-- 18. Insertar vehículos
-- ============================================================================
INSERT INTO Vehicle (id, isVerified, brand, plateNumber, carModel, seatQuantity, idDriver, idAuditLog)
VALUES (seq_vehicle.NEXTVAL, 1, 'Toyota', 'SJO-123', 'Corolla 2020', 4, 1, 1);

INSERT INTO Vehicle (id, isVerified, brand, plateNumber, carModel, seatQuantity, idDriver, idAuditLog)
VALUES (seq_vehicle.NEXTVAL, 1, 'Honda', 'CAR-456', 'Civic 2021', 4, 3, 1);

-- ============================================================================
-- 19. Insertar pasajeros (todos pueden ser pasajeros)
-- ============================================================================
INSERT INTO Passenger (idPerson, idAuditLog)
VALUES (1, 1);

INSERT INTO Passenger (idPerson, idAuditLog)
VALUES (2, 1);

INSERT INTO Passenger (idPerson, idAuditLog)
VALUES (3, 1);

INSERT INTO Passenger (idPerson, idAuditLog)
VALUES (4, 1);

-- ============================================================================
-- 20. Insertar paradas
-- ============================================================================
INSERT INTO Stop (id, address, idDistrict, idAuditLog)
VALUES (seq_stop.NEXTVAL, 'Campus Universitario UCR', 1, 1);

INSERT INTO Stop (id, address, idDistrict, idAuditLog)
VALUES (seq_stop.NEXTVAL, 'Campus Central TEC', 2, 1);

INSERT INTO Stop (id, address, idDistrict, idAuditLog)
VALUES (seq_stop.NEXTVAL, 'Mall San Pedro', 1, 1);

-- ============================================================================
-- 21. Insertar métodos de pago
-- ============================================================================
INSERT INTO PaymentMethod (id, method, idAuditLog)
VALUES (seq_paymentmethod.NEXTVAL, 'Efectivo', 1);

INSERT INTO PaymentMethod (id, method, idAuditLog)
VALUES (seq_paymentmethod.NEXTVAL, 'SINPE Móvil', 1);

INSERT INTO PaymentMethod (id, method, idAuditLog)
VALUES (seq_paymentmethod.NEXTVAL, 'Tarjeta de débito', 1);

-- ============================================================================
-- 22. Insertar estados de precio
-- ============================================================================
INSERT INTO PriceStatus (id, status, idAuditLog)
VALUES (seq_pricestatus.NEXTVAL, 'Gratis', 1);

INSERT INTO PriceStatus (id, status, idAuditLog)
VALUES (seq_pricestatus.NEXTVAL, 'Con costo', 1);

-- ============================================================================
-- 23. Insertar estados de viaje
-- ============================================================================
INSERT INTO TripStatus (id, status, idAuditLog)
VALUES (seq_tripstatus.NEXTVAL, 'Programado', 1);

INSERT INTO TripStatus (id, status, idAuditLog)
VALUES (seq_tripstatus.NEXTVAL, 'En progreso', 1);

INSERT INTO TripStatus (id, status, idAuditLog)
VALUES (seq_tripstatus.NEXTVAL, 'Completado', 1);

INSERT INTO TripStatus (id, status, idAuditLog)
VALUES (seq_tripstatus.NEXTVAL, 'Cancelado', 1);

-- ============================================================================
-- 24. Insertar viajes
-- ============================================================================
INSERT INTO Trip (id, maximunPassengers, departureDateTime, durationEstimate, arrivalDateTime, idDriver, idPriceStatus, idVehicle, idAuditLog)
VALUES (seq_trip.NEXTVAL, 3, TIMESTAMP '2025-05-18 07:00:00', 30, TIMESTAMP '2025-05-18 07:30:00', 1, 2, 1, 1);

INSERT INTO Trip (id, maximunPassengers, departureDateTime, durationEstimate, arrivalDateTime, idDriver, idPriceStatus, idVehicle, idAuditLog)
VALUES (seq_trip.NEXTVAL, 3, TIMESTAMP '2025-05-18 17:00:00', 45, TIMESTAMP '2025-05-18 17:45:00', 3, 1, 2, 1);

-- ============================================================================
-- 25. Insertar reportes diarios
-- ============================================================================
INSERT INTO DailyReport (id, reportType, idInstitution, idAuditLog)
VALUES (seq_dailyreport.NEXTVAL, 'Viajes del día', 1, 1);

INSERT INTO DailyReport (id, reportType, idInstitution, idAuditLog)
VALUES (seq_dailyreport.NEXTVAL, 'Viajes del día', 2, 1);

-- ============================================================================
-- 26. Insertar parámetros del sistema
-- ============================================================================
INSERT INTO Parameter (id, name, value, idAuditLog)
VALUES (seq_parameter.NEXTVAL, 'MAX_PASSENGERS_PER_TRIP', '4', 1);

INSERT INTO Parameter (id, name, value, idAuditLog)
VALUES (seq_parameter.NEXTVAL, 'MIN_ADVANCE_BOOKING_HOURS', '2', 1);

-- ============================================================================
-- 27. Insertar registros de log
-- ============================================================================
INSERT INTO LogBook (id, logDate, logTime, description, idAuditLog)
VALUES (seq_logbook.NEXTVAL, DATE '2025-05-17', TIMESTAMP '2025-05-17 10:00:00', 'Sistema iniciado', 1);

INSERT INTO LogBook (id, logDate, logTime, description, idAuditLog)
VALUES (seq_logbook.NEXTVAL, DATE '2025-05-17', TIMESTAMP '2025-05-17 10:30:00', 'Primer viaje creado', 1);

-- ============================================================================
-- 28. Insertar entidades modificadas
-- ============================================================================
INSERT INTO EntityModified (id, entityName, idAuditLog)
VALUES (seq_entitymodified.NEXTVAL, 'Trip', 1);

INSERT INTO EntityModified (id, entityName, idAuditLog)
VALUES (seq_entitymodified.NEXTVAL, 'Person', 1);

-- ============================================================================
-- 29. Insertar atributos modificados
-- ============================================================================
INSERT INTO AttributeModified (id, newValue, oldValue, attributeName, idEntityModified, idAuditLog)
VALUES (seq_attributemodified.NEXTVAL, 'Programado', NULL, 'status', 1, 1);

INSERT INTO AttributeModified (id, newValue, oldValue, attributeName, idEntityModified, idAuditLog)
VALUES (seq_attributemodified.NEXTVAL, 'Activo', 'Inactivo', 'userStatus', 2, 1);

-- ============================================================================
-- Tablas de relación N:N
-- 30. Relacionar credenciales con tipos
-- ============================================================================
INSERT INTO CredentialHasTypeOfCredential (idCredential, idTypeOfCredential, idAuditLog)
VALUES (1, 1, 1); -- Carlos tiene licencia

INSERT INTO CredentialHasTypeOfCredential (idCredential, idTypeOfCredential, idAuditLog)
VALUES (2, 2, 1); -- Carlos tiene carnet

INSERT INTO CredentialHasTypeOfCredential (idCredential, idTypeOfCredential, idAuditLog)
VALUES (3, 1, 1); -- José tiene licencia

INSERT INTO CredentialHasTypeOfCredential (idCredential, idTypeOfCredential, idAuditLog)
VALUES (4, 2, 1); -- José tiene carnet

-- ============================================================================
-- 31. Relacionar administradores con instituciones
-- ============================================================================
INSERT INTO AdminManageInstitution (idPerson, idInstitution, idAuditLog)
VALUES (4, 2, 1); -- Ana administra TEC

-- ============================================================================
-- 32. Relacionar pasajeros con viajes
-- ============================================================================
INSERT INTO PassengerJoinTrip (idUser, idTrip, joinDate, idAuditLog)
VALUES (2, 1, DATE '2025-05-17', 1); -- María se une al viaje de Carlos

INSERT INTO PassengerQueryTrip (idUser, idTrip, idAuditLog)
VALUES (4, 2, 1); -- Ana consulta el viaje de José

-- ============================================================================
-- 33. Relacionar viajes con estados
-- ============================================================================
INSERT INTO TripHasTripStatus (idTrip, idTripStatus, idAuditLog)
VALUES (1, 1, 1); -- Viaje 1 está programado

INSERT INTO TripHasTripStatus (idTrip, idTripStatus, idAuditLog)
VALUES (2, 1, 1); -- Viaje 2 está programado

-- ============================================================================
-- 34. Relacionar paradas con coordenadas
-- ============================================================================
INSERT INTO StopHasCoordinateLocation (idStop, idCoordinateLocation, idAuditLog)
VALUES (1, 1, 1); -- Parada UCR con coordenadas UCR

INSERT INTO StopHasCoordinateLocation (idStop, idCoordinateLocation, idAuditLog)
VALUES (2, 2, 1); -- Parada TEC con coordenadas TEC

-- ============================================================================
-- 35. Relacionar viajes con paradas
-- ============================================================================
INSERT INTO TripHasStop (idTrip, idStop, estimatedArrival, stopCost, numberStop, idAuditLog)
VALUES (1, 3, DATE '2025-05-18', 500, 1, 1); -- Viaje 1 para en Mall San Pedro

INSERT INTO TripHasStop (idTrip, idStop, estimatedArrival, stopCost, numberStop, idAuditLog)
VALUES (1, 1, DATE '2025-05-18', 0, 2, 1); -- Viaje 1 llega a UCR

INSERT INTO TripHasStop (idTrip, idStop, estimatedArrival, stopCost, numberStop, idAuditLog)
VALUES (2, 2, DATE '2025-05-18', 0, 1, 1); -- Viaje 2 sale de TEC

-- ============================================================================
-- 36. Relacionar viajes, paradas y métodos de pago
-- ============================================================================
INSERT INTO TripHasStopHasPaymentMethod (idPaymentMethod, idTrip, idStop, idAuditLog)
VALUES (1, 1, 3, 1); -- Efectivo en Mall San Pedro para viaje 1

INSERT INTO TripHasStopHasPaymentMethod (idPaymentMethod, idTrip, idStop, idAuditLog)
VALUES (2, 1, 3, 1); -- SINPE en Mall San Pedro para viaje 1

-- ============================================================================
-- 37. Relacionar reportes con viajes
-- ============================================================================
INSERT INTO TripReportDailyReport (idTrip, idDailyReport, reportDate, reportNumber, idAuditLog)
VALUES (1, 1, DATE '2025-05-18', 'RPT-UCR-001', 1);

INSERT INTO TripReportDailyReport (idTrip, idDailyReport, reportDate, reportNumber, idAuditLog)
VALUES (2, 2, DATE '2025-05-18', 'RPT-TEC-001', 1);

-- ============================================================================
-- 38. Relacionar administradores con reportes
-- ============================================================================
INSERT INTO AdminReceiveDailyReport (idAdministrator, idDailyReport, idAuditLog)
VALUES (4, 2, 1); -- Ana recibe reporte del TEC

-- ============================================================================
-- 39. Relacionar logs con entidades modificadas
-- ============================================================================
INSERT INTO LogBookHasEntityModified (idLogBook, idEntityModified, idAuditLog)
VALUES (2, 1, 1); -- Log de primer viaje con entidad Trip

-- ============================================================================
-- 40. Relacionar atributos modificados con logs
-- ============================================================================
INSERT INTO AttrModHasEntMod (idLogBook, idAttributeModified, idAuditLog)
VALUES (2, 1, 1); -- Cambio de estado en el log

COMMIT;