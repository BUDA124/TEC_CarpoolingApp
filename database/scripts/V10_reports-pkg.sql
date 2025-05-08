-- =============================================================================
-- II. Section 5 of Indications document
-- =============================================================================

CREATE OR REPLACE PACKAGE REPORTS_PKG AS

    -- Is: [SELECT] Get the top 5 drivers with the highest number of completed
    -- trips as a driver (count trips per driver, sort desc, limit 5).
    -- From: Section: 5 & Subsection: (a)
    FUNCTION get_top_drivers_completed_trips (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Get the top 5 most frequent start/pickup points within a
    -- date range (group by point, count, filter by date, sort desc, limit 5).
    -- From: Section: 5 & Subsection: (b)
    FUNCTION get_most_frequent_points (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Get the top 5 users with the highest total number of trips
    -- (sum of trips as driver and passenger) (count total trips per user, sort
    -- desc, limit 5).
    -- From: Section: 5 & Subsection: (c)
    FUNCTION get_top_users_number_trips (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Get a complete list of trips with details of the driver,
    -- passengers, and route (joins between trips, users, routes, vehicles,
    -- passengers_per_trip tables).
    -- From: Section: 5 & Subsection: (d)
    FUNCTION get_list_trips_details (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Calculate the average of the 'cost' field for all trips
    -- that have a defined value.
    -- date range (group by point, count, filter by date, sort desc, limit 5).
    -- From: Section: 5 & Subsection: (e)
    FUNCTION get_average_cost_trips (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Count users whose registration date is within the last 3 months.
    -- From: Section: 5 & Subsection: (f)
    FUNCTION get_last_registered_users (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [COUNT] Display the total number of records for each query/list generated
    -- in package.
    -- From: Section: 5 & Subsection: (g)
    FUNCTION get_queries_package_records (id NUMBER) RETURN SYS_REFCURSOR;

END REPORTS_PKG;

/

CREATE OR REPLACE PACKAGE BODY REPORTS_PKG AS

END REPORTS_PKG;

/

