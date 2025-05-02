-- =============================================================================
-- CARPOOLING SYSTEM
-- =============================================================================

-- =============================================================================
-- I. USER REGISTRATION & PROFILE MANAGEMENT
-- =============================================================================

-- 1. Register New User (Requires multiple inserts, best handled within a transaction)
-- Step 1.1: Create Person record
INSERT INTO Person (id, firstName, secondName, firstSurname, secondSurname, birthdate, nationality, profilePicture, idInstitution, idAuditLog)
VALUES (seq_person.nextval, :first_name, :second_name, :first_surname, :second_surname, :birthdate, :nationality, :profile_picture_blob, :institution_id, :audit_log_id)
RETURNING id INTO :new_person_id;

-- Step 1.2: Create PersonalUser account linked to Person
INSERT INTO PERSONALUSER (id, password, username, registrationDate, idPerson, idAuditLog)
VALUES (seq_personaluser.nextval, :hashed_password, :username, SYSDATE, :new_person_id, :audit_log_id)
RETURNING id INTO :new_user_id;

-- Step 1.3: Record User Gender
INSERT INTO Gender (id, genderName, idAuditLog)
VALUES (seq_gender.nextval, :gender_name, :audit_log_id);

-- Step 1.4: Add Personal Email
INSERT INTO Email (id, emailAddress, idPerson, idAuditLog)
VALUES (seq_email.nextval, :email_address, :new_person_id, :audit_log_id);

-- Step 1.5: Add Phone Number
INSERT INTO PhoneNumber (id, phoneNumber, idPerson, idAuditLog)
VALUES (seq_phonenumber.nextval, :phone_number, :new_person_id, :audit_log_id);

-- Step 1.6: Add Institutional Email (Crucial for institution validation)
INSERT INTO INSTITUTIONALEMAIL (id, emailAddress, idUser, idAuditLog)
VALUES (seq_institutionalemail.nextval, :institutional_email, :new_user_id, :audit_log_id);

-- Step 1.7: Set user as potentially 'Active' using Credential (if this approach is used)
-- Note: Check if institutional email domain matches the Person's institution domain before activation.
INSERT INTO Credential (id, isActive, numberOfCredential, idPerson, idAuditLog)
VALUES(seq_credential.nextval, 1, :id_card_number, :new_person_id, :audit_log_id); -- Assuming id_card_number is the credential. Adjust 'type' via CredentialHasTypeOfCredential if needed.

-- Step 1.8: Assign Role(s) - Driver / Passenger (based on registration choice)
-- Register as Passenger
INSERT INTO Passenger (idPerson, idAuditLog) VALUES (:new_person_id, :audit_log_id);

-- Register as Driver (conditional on registration form)
INSERT INTO Driver (idPerson, idAuditLog) VALUES (:new_person_id, :audit_log_id);

-- 2. Get User Profile Details (by Username)
SELECT
    p.id AS person_id, p.firstName, p.secondName, p.firstSurname, p.secondSurname, p.birthdate, p.nationality, p.profilePicture,
    pu.id AS user_id, pu.username, pu.registrationDate,
    pers_email.emailAddress AS personal_email,
    phone.phoneNumber AS phone_number,
    g.genderName,
    inst.institutionName, inst.emailDomain AS institution_domain,
    inst_email.emailAddress AS institutional_email,
    cred.isActive AS user_is_active, -- Get status from Credential
    (SELECT COUNT(*) FROM Driver WHERE idPerson = p.id) AS is_driver, -- Check Driver role
    (SELECT COUNT(*) FROM Passenger WHERE idPerson = p.id) AS is_passenger -- Check Passenger role
FROM PERSON p
         JOIN PERSONALUSER pu ON p.id = pu.idPerson
         LEFT JOIN EMAIL pers_email ON p.id = pers_email.idPerson -- Assuming one personal email for display simplicity
         LEFT JOIN PHONENUMBER phone ON p.id = phone.idPerson -- Assuming one phone for display simplicity
         LEFT JOIN GENDER g ON p.id = g.idPerson
         LEFT JOIN INSTITUTION inst ON p.idInstitution = inst.id
         LEFT JOIN INSTITUTIONALEMAIL inst_email ON pu.id = inst_email.idUser -- Critical link for affiliation
         LEFT JOIN Credential cred ON p.id = cred.idPerson -- Link to credential for status (adjust if linking differs)
WHERE pu.username = :username;

-- 3. Check if Username Already Exists (for registration validation)
SELECT COUNT(*) FROM PERSONALUSER WHERE username = :username;

-- 4. Get User Password Hash (for login verification)
SELECT password FROM PERSONALUSER WHERE username = :username;

-- 5. Update User Profile (Person basic info)
UPDATE Person
SET
    firstName = :first_name,
    secondName = :second_name,
    firstSurname = :first_surname,
    secondSurname = :second_surname,
    birthdate = :birthdate,
    nationality = :nationality,
    profilePicture = :profile_picture_blob,
    -- idInstitution = :institution_id, -- Changing institution might need complex re-validation logic
    idAuditLog = :audit_log_id
WHERE id = :person_id;

-- 6. Update Specific User Email Address
UPDATE Email
SET
    emailAddress = :new_email,
    idAuditLog = :audit_log_id
WHERE id = :email_record_id AND idPerson = :person_id; -- Target specific record

-- 7. Update Specific User Phone Number
UPDATE PhoneNumber
SET
    phoneNumber = :new_phone,
    idAuditLog = :audit_log_id
WHERE id = :phone_record_id AND idPerson = :person_id; -- Target specific record

-- 8. Update User Password
UPDATE PERSONALUSER
SET
    password = :new_hashed_password,
    idAuditLog = :audit_log_id
WHERE id = :user_id;

-- 9. Deactivate User Account (Soft Delete using Credential)
UPDATE Credential
SET
    isActive = 0,
    idAuditLog = :audit_log_id
WHERE idPerson = :person_id; -- Assumes Credential link represents account activation

-- =============================================================================
-- II. DRIVER & VEHICLE MANAGEMENT
-- =============================================================================

-- 10. Register Person as a Driver (Assign role if not done at registration)
INSERT INTO Driver (idPerson, idAuditLog)
SELECT :person_id, :audit_log_id
FROM dual
WHERE NOT EXISTS (SELECT 1 FROM Driver WHERE idPerson = :person_id);

-- 11. Add New Vehicle for a Driver
INSERT INTO Vehicle (id, isVerified, brand, carPhoto, plateNumber, carModel, seatQuantity, idDriver, idAuditLog)
VALUES (seq_vehicle.nextval, 0, :brand, :car_photo_blob, :plate_number, :car_model, :seat_quantity, :driver_person_id, :audit_log_id); -- Defaults to not verified

-- 12. Get Vehicles Owned by a Specific Driver
SELECT v.id, v.brand, v.carModel, v.plateNumber, v.seatQuantity, v.isVerified
FROM Vehicle v
WHERE v.idDriver = :driver_person_id;

-- 13. Get Details of a Specific Vehicle (includes driver name)
SELECT v.id, v.brand, v.carModel, v.plateNumber, v.seatQuantity, v.isVerified, v.carPhoto,
       p.firstName AS driver_firstName, p.firstSurname AS driver_firstSurname
FROM Vehicle v
         JOIN Person p ON v.idDriver = p.id -- Driver ID is the Person ID
WHERE v.id = :vehicle_id;

-- 14. Update Vehicle Details (Driver edits their own vehicle)
UPDATE Vehicle
SET
    brand = :brand,
    carModel = :car_model,
    seatQuantity = :seat_quantity,
    carPhoto = :car_photo_blob,
    idAuditLog = :audit_log_id
WHERE id = :vehicle_id
  AND idDriver = :driver_person_id; -- Security check: ensure owner is making changes

-- 15. Verify/Unverify Vehicle (Admin action)
UPDATE Vehicle
SET
    isVerified = :verification_status, -- Use 1 for verified, 0 for not verified
    idAuditLog = :audit_log_id
WHERE id = :vehicle_id;

-- 16. Delete Vehicle (Driver deletes their own vehicle)
DELETE FROM Vehicle
WHERE id = :vehicle_id
  AND idDriver = :driver_person_id;

-- =============================================================================
-- III. TRIP CREATION & SEARCH
-- =============================================================================

-- 17. Create a New Trip (by Driver)
-- Step 17.1: Create the main Trip record
INSERT INTO Trip (id, maximunPassengers, departureDateTime, durationEstimate, arrivalDateTime, idDriver, idPriceStatus, idVehicle, idAuditLog)
VALUES (seq_trip.nextval, :max_passengers, :departure_ts, :duration_minutes, :arrival_ts, :driver_person_id, :price_status_id, :vehicle_id, :audit_log_id)
RETURNING id INTO :new_trip_id;

INSERT INTO TripHasStop (idTrip, idStop, estimatedArrival, stopCost, numberStop, idAuditLog)
VALUES (:new_trip_id, :stop_id, :estimated_arrival_dt, :stop_cost, :stop_number, :audit_log_id);

-- 18. Find Available Trips for Passengers
SELECT
    t.id AS trip_id,
    t.departureDateTime,
    t.arrivalDateTime,
    origin_stop.address AS origin_address,
    (SELECT s.address FROM Stop s JOIN TripHasStop ths_d ON s.id = ths_d.idStop WHERE ths_d.idTrip = t.id ORDER BY ths_d.numberStop DESC FETCH FIRST 1 ROW ONLY) AS final_destination_address,
    p.firstName AS driver_firstName,
    v.brand AS vehicle_brand,
    v.carModel AS vehicle_model,
    (t.maximunPassengers - COUNT(DISTINCT pjt.idUser)) AS available_seats,
    (SELECT ts.status FROM TripStatus ts JOIN TripHasTripStatus thts ON ts.id=thts.idTripStatus WHERE thts.idTrip = t.id ORDER BY thts.idAuditLog DESC FETCH FIRST 1 ROW ONLY) AS current_status -- Assuming latest audit log means current status
FROM Trip t
         JOIN Driver dr ON t.idDriver = dr.idPerson
         JOIN Person p ON dr.idPerson = p.id
         JOIN Vehicle v ON t.idVehicle = v.id AND v.isVerified = 1 -- Ensure vehicle is verified
         JOIN TripHasStop ths_origin ON t.id = ths_origin.idTrip AND ths_origin.numberStop = 0 -- Origin is stop 0
         JOIN Stop origin_stop ON ths_origin.idStop = origin_stop.id
         JOIN TripHasStop ths_dest ON t.id = ths_dest.idTrip -- Find *any* stop in the destination district
         JOIN Stop dest_stop ON ths_dest.idStop = dest_stop.id
         LEFT JOIN PassengerJoinTrip pjt ON t.id = pjt.idTrip -- Count current passengers
-- Ensure Driver and potential Passenger are from the same Institution
         JOIN Person p_driver ON t.idDriver = p_driver.id
         JOIN Person p_potential_passenger ON p_potential_passenger.id = :searching_passenger_person_id -- Pass the searcher's person ID
WHERE
    origin_stop.idDistrict = :origin_district_id
  AND dest_stop.idDistrict = :destination_district_id
  AND t.departureDateTime > :min_departure_time
  AND p_driver.idInstitution = p_potential_passenger.idInstitution -- Crucial check from PDF Requirements (d)
  AND EXISTS (
    SELECT 1
    FROM TripHasTripStatus thts_stat
             JOIN TripStatus ts_stat ON thts_stat.idTripStatus = ts_stat.id
    WHERE thts_stat.idTrip = t.id AND ts_stat.status IN ('Scheduled') -- Add other bookable statuses if they exist
    -- Add logic to check if *this* status is the *current* one if history is kept
    ORDER BY thts_stat.idAuditLog DESC -- Example way to get latest status
)
GROUP BY
    t.id, t.departureDateTime, t.arrivalDateTime, t.maximunPassengers, origin_stop.address, p.firstName, v.brand, v.carModel
HAVING (t.maximunPassengers - COUNT(DISTINCT pjt.idUser)) > 0 -- Only show trips with space
ORDER BY t.departureDateTime;

-- =============================================================================
-- IV. VIEWING & JOINING TRIPS
-- =============================================================================

-- 19. Get Full Details for a Specific Trip
-- Part 19.1: Core Trip Info, Driver, Vehicle, Status
SELECT
    t.id AS trip_id, t.departureDateTime, t.arrivalDateTime, t.maximunPassengers, t.durationEstimate,
    p.id AS driver_person_id, p.firstName AS driver_firstName, p.firstSurname AS driver_firstSurname,
    v.id AS vehicle_id, v.brand, v.carModel, v.plateNumber, v.seatQuantity, v.isVerified AS vehicle_is_verified,
    ps.status AS price_status_desc,
    (SELECT ts.status FROM TripStatus ts JOIN TripHasTripStatus thts ON ts.id=thts.idTripStatus WHERE thts.idTrip = t.id ORDER BY thts.idAuditLog DESC FETCH FIRST 1 ROW ONLY) AS current_trip_status
FROM Trip t
         JOIN Driver dr ON t.idDriver = dr.idPerson
         JOIN Person p ON dr.idPerson = p.id
         JOIN Vehicle v ON t.idVehicle = v.id
         LEFT JOIN PriceStatus ps ON t.idPriceStatus = ps.id
WHERE t.id = :trip_id;

-- Part 19.2: Trip Route/Stops
SELECT
    ths.numberStop, ths.estimatedArrival, ths.stopCost,
    s.id AS stop_id, s.address,
    d.name AS district, ca.name AS canton, pr.name AS province,
    -- Optionally add coordinates if joined with StopHasCoordinateLocation & CoordinateLocation
    cl.xCoordinate, cl.yCoordinate
FROM TripHasStop ths
         JOIN Stop s ON ths.idStop = s.id
         JOIN District d ON s.idDistrict = d.id
         JOIN Canton ca ON d.idCanton = ca.id
         JOIN Province pr ON ca.idProvince = pr.id
         LEFT JOIN StopHasCoordinateLocation shcl ON s.id = shcl.idStop -- Link stop to coordinates N:N table
         LEFT JOIN CoordinateLocation cl ON shcl.idCoordinateLocation = cl.id -- Get coordinates
WHERE ths.idTrip = :trip_id
ORDER BY ths.numberStop;

-- Part 19.3: Passengers Currently Joined to Trip
SELECT
    pu.id AS user_id, pu.username,
    p.firstName, p.firstSurname,
    pjt.joinDate
FROM PassengerJoinTrip pjt
         JOIN PERSONALUSER pu ON pjt.idUser = pu.id
         JOIN Person p ON pu.idPerson = p.id
WHERE pjt.idTrip = :trip_id;

-- 20. Passenger Joins a Trip
INSERT INTO PassengerJoinTrip (idUser, idTrip, joinDate, idAuditLog)
SELECT :passenger_user_id, :trip_id, SYSDATE, :audit_log_id
FROM DUAL
WHERE ( -- Check 1: Seats available
          SELECT COUNT(*) FROM PassengerJoinTrip WHERE idTrip = :trip_id
      ) < (
          SELECT maximunPassengers FROM Trip WHERE id = :trip_id
      ) AND ( -- Check 2: User is registered as a Passenger
                SELECT COUNT(*) FROM Passenger p JOIN PERSONALUSER pu ON p.idPerson = pu.idPerson WHERE pu.id = :passenger_user_id
            ) > 0 AND ( -- Check 3: Passenger and Driver are from the same institution (important validation)
                          SELECT p_pass.idInstitution FROM Person p_pass JOIN PERSONALUSER pu ON p_pass.id = pu.idPerson WHERE pu.id = :passenger_user_id
                      ) = (
                          SELECT p_driver.idInstitution FROM Person p_driver JOIN Trip t ON p_driver.id = t.idDriver WHERE t.id = :trip_id
                      );
-- Ensure this query executes only if the checks pass (e.g., Business Layer logic or database trigger)


-- 21. Passenger Leaves a Trip (Cancels Booking)
DELETE FROM PassengerJoinTrip
WHERE idUser = :passenger_user_id AND idTrip = :trip_id;

-- 22. Get Trips Currently Joined by a Specific Passenger
SELECT
    t.id AS trip_id,
    t.departureDateTime,
    t.arrivalDateTime,
    (SELECT s.address FROM Stop s JOIN TripHasStop ths_o ON s.id = ths_o.idStop WHERE ths_o.idTrip = t.id AND ths_o.numberStop = 0) AS origin_address,
    (SELECT s.address FROM Stop s JOIN TripHasStop ths_d ON s.id = ths_d.idStop WHERE ths_d.idTrip = t.id ORDER BY ths_d.numberStop DESC FETCH FIRST 1 ROW ONLY) AS final_destination_address,
    p_driver.firstName AS driver_name,
    (SELECT ts.status FROM TripStatus ts JOIN TripHasTripStatus thts ON ts.id=thts.idTripStatus WHERE thts.idTrip = t.id ORDER BY thts.idAuditLog DESC FETCH FIRST 1 ROW ONLY) AS current_trip_status
FROM PassengerJoinTrip pjt
         JOIN Trip t ON pjt.idTrip = t.id
         LEFT JOIN Driver dr ON t.idDriver = dr.idPerson
         LEFT JOIN Person p_driver ON dr.idPerson = p_driver.id
WHERE pjt.idUser = :passenger_user_id -- :passenger_user_id is the ID from PERSONALUSER table
ORDER BY t.departureDateTime DESC;

-- =============================================================================
-- V. TRIP STATUS & UPDATES
-- =============================================================================

-- 23. Update Trip Details (Driver modifies their trip - e.g., time)
UPDATE Trip
SET
    departureDateTime = :new_departure_ts,
    arrivalDateTime = :new_arrival_ts,
    maximunPassengers = :new_max_passengers,
    idAuditLog = :audit_log_id
WHERE id = :trip_id
  AND idDriver = :driver_person_id;

-- 24. Add/Update Trip Status History (e.g., Scheduled -> Started -> Completed/Cancelled)
INSERT INTO TripHasTripStatus (idTrip, idTripStatus, idAuditLog)
VALUES (:trip_id, :new_status_id, :audit_log_id); -- :new_status_id from TripStatus table

-- 25. Cancel a Trip (Specific case of updating status)
INSERT INTO TripHasTripStatus (idTrip, idTripStatus, idAuditLog)
VALUES (
           :trip_id,
           (SELECT id FROM TripStatus WHERE status = 'Cancelled'), -- Get the ID for 'Cancelled' status
           :audit_log_id
       );

-- =============================================================================
-- VI. LOCATION & INSTITUTION LOOKUPS
-- =============================================================================

-- 26. Get All Institutions (for dropdowns)
SELECT id, institutionName, emailDomain FROM Institution ORDER BY institutionName;

-- 27. Get Institution Details by ID
SELECT id, emailDomain, institutionName, website
FROM Institution
WHERE id = :institution_id;

-- 28. Get Countries
SELECT id, name FROM Country ORDER BY name;

-- 29. Get Provinces for a Country
SELECT id, name FROM Province WHERE idCountry = :country_id ORDER BY name;

-- 30. Get Cantons for a Province
SELECT id, name FROM Canton WHERE idProvince = :province_id ORDER BY name;

-- 31. Get Districts for a Canton
SELECT id, name FROM District WHERE idCanton = :canton_id ORDER BY name;


-- =============================================================================
-- VII. GENERAL REPORTS & STATISTICS
-- =============================================================================

-- 32. [REPORT] Top 5 Drivers (Most Trips Completed/Driven)
-- Placeholder - Requires logic: Count completed Trips per driver.
SELECT p.firstName, p.firstSurname, COUNT(t.id) AS trips_driven
FROM Trip t
         JOIN Person p ON t.idDriver = p.id
         JOIN TripHasTripStatus thts ON t.id = thts.idTrip
         JOIN TripStatus ts ON thts.idTripStatus = ts.id AND ts.status = 'Completed' -- Assuming 'Completed' status exists
GROUP BY p.id, p.firstName, p.firstSurname
ORDER BY trips_driven DESC
    FETCH FIRST 5 ROWS ONLY;

-- 33. [REPORT] Top 5 Most Frequent Stops (Date Range)
SELECT s.address, d.name AS district, COUNT(*) AS frequency
FROM TripHasStop ths
         JOIN Stop s ON ths.idStop = s.id
         JOIN District d ON s.idDistrict = d.id
         JOIN Trip t ON ths.idTrip = t.id
WHERE t.departureDateTime BETWEEN :start_date AND :end_date
GROUP BY s.id, s.address, d.name
ORDER BY frequency DESC
    FETCH FIRST 5 ROWS ONLY;

-- 34. [REPORT] Top 5 Most Active Passengers (Most Trips Joined)
SELECT pu.username, p.firstName, p.firstSurname, COUNT(pjt.idTrip) AS trips_joined
FROM PassengerJoinTrip pjt
         JOIN PERSONALUSER pu ON pjt.idUser = pu.id
         JOIN Person p ON pu.idPerson = p.id
GROUP BY pu.id, pu.username, p.firstName, p.firstSurname
ORDER BY trips_joined DESC
    FETCH FIRST 5 ROWS ONLY;

-- 35. [REPORT] List All Trips Information (Detailed Multi-Trip View)
SELECT t.id AS trip_id, t.departureDateTime,
       p_driver.firstName || ' ' || p_driver.firstSurname AS driver_name,
       v.brand || ' ' || v.carModel AS vehicle,
       (SELECT s.address FROM Stop s JOIN TripHasStop ths_o ON s.id = ths_o.idStop WHERE ths_o.idTrip = t.id AND ths_o.numberStop = 0) AS origin,
       (SELECT s.address FROM Stop s JOIN TripHasStop ths_d ON s.id = ths_d.idStop WHERE ths_d.idTrip = t.id ORDER BY ths_d.numberStop DESC FETCH FIRST 1 ROW ONLY) AS destination,
       (SELECT ts.status FROM TripStatus ts JOIN TripHasTripStatus thts ON ts.id=thts.idTripStatus WHERE thts.idTrip = t.id ORDER BY thts.idAuditLog DESC FETCH FIRST 1 ROW ONLY) AS current_status
FROM Trip t
         JOIN Person p_driver ON t.idDriver = p_driver.id
         JOIN Vehicle v on t.idVehicle = v.id
ORDER BY t.departureDateTime DESC;

-- 36. [REPORT] Average Fare Charged by Drivers
SELECT AVG(ths.stopCost) AS average_final_stop_cost
FROM TripHasStop ths
         JOIN Trip t ON ths.idTrip = t.id
         JOIN PriceStatus ps ON t.idPriceStatus = ps.id
WHERE ps.status <> 'Free' -- Only include priced trips
  AND ths.stopCost > 0
  AND ths.numberStop = (SELECT MAX(ths_inner.numberStop) FROM TripHasStop ths_inner WHERE ths_inner.idTrip = ths.idTrip); -- Only consider the last stop

-- 37. [REPORT] New User Count (Last 3 Months)
SELECT COUNT(*) AS new_users_last_3_months
FROM PERSONALUSER
WHERE registrationDate >= ADD_MONTHS(SYSDATE, -3);

-- 38. [STATISTIC] Driver Count by Gender & Date Range (uses registration date)
-- Uses "PersonalUser" registration date. Date range applies to *when they registered*.
SELECT g.genderName, COUNT(DISTINCT d.idPerson) AS driver_count
FROM Driver d
         JOIN Person p ON d.idPerson = p.id
         JOIN Gender g ON p.id = g.idPerson
         JOIN PERSONALUSER pu ON p.id = pu.idPerson
WHERE pu.registrationDate BETWEEN :start_date AND :end_date
GROUP BY g.genderName;

-- 39. [STATISTIC] Passenger Count by Gender & Date Range (uses registration date)
SELECT g.genderName, COUNT(DISTINCT pas.idPerson) AS passenger_count
FROM Passenger pas
         JOIN Person p ON pas.idPerson = p.id
         JOIN Gender g ON p.id = g.idPerson
         JOIN PERSONALUSER pu ON p.id = pu.idPerson
WHERE pu.registrationDate BETWEEN :start_date AND :end_date
GROUP BY g.genderName;

-- 40. [STATISTIC] User Count by Age Range & Gender
SELECT
    CASE
        WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, p.birthdate) / 12) BETWEEN 0 AND 18 THEN '0-18'
        WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, p.birthdate) / 12) BETWEEN 19 AND 30 THEN '19-30'
        WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, p.birthdate) / 12) BETWEEN 31 AND 45 THEN '31-45'
        WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, p.birthdate) / 12) BETWEEN 46 AND 60 THEN '46-60'
        WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, p.birthdate) / 12) BETWEEN 61 AND 75 THEN '61-75'
        ELSE '>75'
        END AS age_range,
    g.genderName,
    COUNT(DISTINCT p.id) AS user_count
FROM Person p
         JOIN Gender g ON p.id = g.idPerson
GROUP BY
    CASE
        WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, p.birthdate) / 12) BETWEEN 0 AND 18 THEN '0-18'
        WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, p.birthdate) / 12) BETWEEN 19 AND 30 THEN '19-30'
        WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, p.birthdate) / 12) BETWEEN 31 AND 45 THEN '31-45'
        WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, p.birthdate) / 12) BETWEEN 46 AND 60 THEN '46-60'
        WHEN TRUNC(MONTHS_BETWEEN(SYSDATE, p.birthdate) / 12) BETWEEN 61 AND 75 THEN '61-75'
        ELSE '>75'
        END,
    g.genderName
ORDER BY age_range, g.genderName;


-- =============================================================================
-- VIII. ADMINISTRATION & AUDITING
-- =============================================================================

-- 41. Register an Administrator (Assign role)
INSERT INTO Administrator (idPerson, idAuditLog)
VALUES (:person_id, :audit_log_id);

-- 42. Get Basic Administrator Info
SELECT p.firstName, p.firstSurname, pu.username
FROM Administrator a
         JOIN Person p ON a.idPerson = p.id
         JOIN PERSONALUSER pu ON p.id = pu.idPerson -- Assumes Admins are also registered users
WHERE a.idPerson = :admin_person_id;

-- 43. Create Daily Report Record (Likely triggered by the background Job)
INSERT INTO DailyReport (id, reportType, idInstitution, idAuditLog)
VALUES (seq_dailyreport.nextval, :report_type, :institution_id, :audit_log_id);

-- 44. Query LogBook Entries (Filtered Example)
SELECT lb.id, lb.logDate, lb.logTime, lb.description, al.createdBy
FROM LogBook lb
         JOIN AuditLog al ON lb.idAuditLog = al.id
WHERE lb.description LIKE :search_pattern -- Example filter
ORDER BY lb.logDate DESC, lb.logTime DESC;

-- 45. Get Attribute Changes for a Specific LogBook Entry (Detailed Audit Trail)
SELECT em.entityName, am.attributeName, am.oldValue, am.newValue
FROM AttributeModified am
         JOIN AttrModHasEntMod amhem ON am.id = amhem.idAttributeModified
         JOIN EntityModified em ON am.idEntityModified = em.id -- Assumed link via FK on AttributeModified table (as per schema diagram structure)
WHERE amhem.idLogBook = :logbook_id;


-- =============================================================================
-- IX. PARAMETERS
-- =============================================================================

-- 46. Get System Parameter Value
SELECT value FROM Parameter WHERE name = :parameter_name;

-- 47. Get All System Parameters
SELECT name, value FROM Parameter ORDER BY name;

-- 48. Update System Parameter Value (Admin Action)
UPDATE Parameter SET value = :new_value, idAuditLog = :audit_log_id WHERE name = :parameter_name;

-- =============================================================================
-- END OF QUERIES
-- =============================================================================