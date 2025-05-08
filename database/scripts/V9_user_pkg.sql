-- =============================================================================
-- I. Section 4 of Indications document
-- =============================================================================

CREATE OR REPLACE PACKAGE USER_PKG AS

    -- Is: [INSERT] Register new user (with all profile data).
    -- From: Section: 4 & Subsection: (a)
    PROCEDURE register_new_user (id NUMBER);

    -- Is: [SELECT] Authenticate user by credentials (email and password).
    -- From: Section: 4 & Subsection: (a)
    PROCEDURE authenticate_user_credentials (id NUMBER);

    -- Is: [SELECT] Get profile data for a specific user.
    -- From: Section: 4 & Subsection: (c)
    FUNCTION get_user_profile_data (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [UPDATE] Update user profile information.
    -- From: Section: 4 & Subsection: (a)
    PROCEDURE update_user_information (id NUMBER);

    -- Is: [UPDATE] Mark user acceptance of terms and conditions.
    -- From: Section: 4 & Subsection: (a)
    PROCEDURE accepted_terms_conditions (id NUMBER);

    -- Is: [SELECT] Get current terms and conditions.               ---------
    -- From: Section: 4 & Subsection: (a)                           ---------
    FUNCTION get_terms_conditions (id NUMBER) RETURN SYS_REFCURSOR; ---------

    -- Is: [SELECT] Get vehicles registered by a driver user.
    -- From: Section: 4 & Subsection: (b)
    FUNCTION get_driver_vehicles (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [INSERT] Publish a new trip offered by a driver (includes route
    -- details, vehicle, schedule, capacity, cost).
    -- From: Section: 4 & Subsection: (b)
    PROCEDURE publish_new_trip (id NUMBER);

    -- Is: [INSERT] Add a new stop into the trip.
    -- From: Section: 4 & Subsection: (b)
    PROCEDURE add_stop_to_trip (id NUMBER);

    -- Is: [SELECT] Search for available routes filtering by destination, schedule,
    -- and (crucially) passenger's same institution.
    -- From: Section: 4 & Subsection: (b)
    FUNCTION search_available_routes (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Get full details of a specific trip (including driver and vehicle data).
    -- From: Section: 4 & Subsection: (b)
    FUNCTION get_trip_details (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Check available capacity on a specific trip before allowing join.
    -- From: Section: 4 & Subsection: (b)
    FUNCTION get_trip_availability (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [INSERT] Register a passenger for a specific trip (associate passenger user with
    -- published route/trip, record pickup point).
    -- From: Section: 4 & Subsection: (b)
    PROCEDURE add_trip_passenger (id NUMBER);

    -- Is: [UPDATE] Update the available capacity of a trip when a passenger joins.
    -- From: Section: 4 & Subsection: (b)
    PROCEDURE update_trip_availability (id NUMBER);

    -- Is: [SELECT] Get the history of trips made by a user as a driver.
    -- From: Section: 4 & Subsection: (c)
    FUNCTION get_driver_trip_history (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Get the history of trips taken by a user as a passenger.
    -- From: Section: 4 & Subsection: (c)
    FUNCTION get_passenger_trip_history (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Verify the institution a user belongs to (used internally to validate interactions).
    -- From: Section: 4 & Subsection: (d)
    FUNCTION verify_user_institution (id NUMBER) RETURN SYS_REFCURSOR;

END USER_PKG;

/

CREATE OR REPLACE PACKAGE BODY USER_PKG AS

END USER_PKG;

/

