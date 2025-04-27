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
                                     id          NUMBER          CONSTRAINT pk_dailyreport PRIMARY KEY,
                                     reportType  VARCHAR2(100)   CONSTRAINT nn_DailyReport_reportType NULL
        )';
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

                                     CONSTRAINT pk_Institution_id PRIMARY KEY (id),
                                     CONSTRAINT uk_Institution_emaildomain UNIQUE (emailDomain),
                                     CONSTRAINT nn_Institution_emaildomain CHECK (emailDomain IS NOT NULL),
                                     CONSTRAINT nn_Institution_institutionname CHECK (institutionName IS NOT NULL)

        )';
        DBMS_OUTPUT.PUT_LINE('Created table INSTITUTION.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Table INSTITUTION already existed.');
    END IF;
END;
/