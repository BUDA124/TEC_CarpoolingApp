DECLARE
    v_constraint_exists NUMBER;
BEGIN
    SELECT COUNT(*)
    INTO v_constraint_exists
    FROM user_constraints
    WHERE constraint_name = 'FK_DAILYREPORT_INSTITUTION'
      AND table_name = 'DAILYREPORT';

    IF v_constraint_exists = 0 THEN
        EXECUTE IMMEDIATE '
        ALTER TABLE DailyReport
            ADD CONSTRAINT fk_dailyreport_institution
                FOREIGN KEY (idInstitution)
                    REFERENCES Institution (id)';
        DBMS_OUTPUT.PUT_LINE('Constraint FK_DAILYREPORT_INSTITUTION added.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Constraint FK_DAILYREPORT_INSTITUTION already existed.');
    END IF;

    SELECT COUNT(*)
    INTO v_constraint_exists
    FROM user_constraints
    WHERE constraint_name = 'FK_DAILYREPORT_AUDITLOG' -- Nombre constraint 2
      AND table_name = 'DAILYREPORT';

    IF v_constraint_exists = 0 THEN
        EXECUTE IMMEDIATE '
        ALTER TABLE DailyReport
            ADD CONSTRAINT fk_dailyreport_auditlog
                FOREIGN KEY (idAuditLog)
                    REFERENCES AuditLog (id)';
        DBMS_OUTPUT.PUT_LINE('Constraint FK_DAILYREPORT_AUDITLOG added');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Constraint FK_DAILYREPORT_AUDITLOG already existed.');
    END IF;

END;
/