-- =============================================================================
-- IV. Section 8 of Indications document
-- =============================================================================

CREATE OR REPLACE PACKAGE ADMIN_PKG AS

    -- Is: [SELECT] Count drivers by gender, filtering by institution and
    -- registration date range (group by gender, count).
    -- From: Section: 8 & Subsection: (a)
    FUNCTION get_divers_by_gender (id NUMBER) RETURN SYS_REFCURSOR;
    FUNCTION get_divers_by_institution (id NUMBER) RETURN SYS_REFCURSOR;
    FUNCTION get_divers_by_registration_date (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Count passengers by gender, filtering by institution and
    -- registration date range (group by gender, count).
    -- From: Section: 8 & Subsection: (a)
    FUNCTION get_passengers_by_gender (id NUMBER) RETURN SYS_REFCURSOR;
    FUNCTION get_passengers_by_institution (id NUMBER) RETURN SYS_REFCURSOR;
    FUNCTION get_passengers_by_registration (id NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Count users grouped by predefined age ranges (0-18, 19-30,
    -- 31-45, 46-60, 61-75, >75), filtering by institution and by
    -- gender (calculate age based on date of birth).
    -- From: Section: 5 & Subsection: (c)
    FUNCTION get_users_by_gender (id NUMBER) RETURN SYS_REFCURSOR;
    FUNCTION get_users_by_institution (id NUMBER) RETURN SYS_REFCURSOR;
    FUNCTION get_users_by_age_range (youngest_age NUMBER, oldest_age NUMBER) RETURN SYS_REFCURSOR;

    -- Is: [SELECT] Calculates peak hours for publishing/traveling
    -- From: Section: 5 & Subsection: (d)
    FUNCTION get_system_peak_hours (id NUMBER) RETURN SYS_REFCURSOR;

END ADMIN_PKG;

/

CREATE OR REPLACE PACKAGE BODY ADMIN_PKG AS

END ADMIN_PKG;

/

