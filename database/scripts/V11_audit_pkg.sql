-- =============================================================================
-- III. Section 7 of Indications document
-- =============================================================================

CREATE OR REPLACE PACKAGE AUDIT_PKG AS

    -- Is:  [SELECT]
    -- From: Section: 7
    FUNCTION get_log_book_information (id NUMBER) RETURN SYS_REFCURSOR;

END AUDIT_PKG;

/

CREATE OR REPLACE PACKAGE BODY AUDIT_PKG AS

END AUDIT_PKG;

/

