DECLARE
    v_index_tablespace VARCHAR2(30) := 'CARPOOLING_INDX'; -- Tu tablespace de índices

    -- Procedimiento local para añadir FK y su índice correspondiente
    PROCEDURE add_fk_and_index_if_missing (
        p_table_name        IN VARCHAR2,
        p_constraint_name   IN VARCHAR2,
        p_fk_column         IN VARCHAR2,
        p_ref_table         IN VARCHAR2,
        p_ref_column        IN VARCHAR2,
        p_index_name        IN VARCHAR2 -- Nombre deseado para el índice FK
        -- El tablespace del índice se toma de la variable externa v_index_tablespace
    ) IS
        v_exists NUMBER;
    BEGIN
        -- 1. Manejar la Constraint FK
        SELECT COUNT(*)
        INTO v_exists
        FROM user_constraints
        WHERE constraint_name = p_constraint_name
          AND table_name = p_table_name;

        IF v_exists = 0 THEN
            EXECUTE IMMEDIATE '
            ALTER TABLE ' || DBMS_ASSERT.ENQUOTE_NAME(p_table_name, FALSE) || '
                ADD CONSTRAINT ' || DBMS_ASSERT.ENQUOTE_NAME(p_constraint_name, FALSE) || '
                    FOREIGN KEY (' || DBMS_ASSERT.ENQUOTE_NAME(p_fk_column, FALSE) || ')
                        REFERENCES ' || DBMS_ASSERT.ENQUOTE_NAME(p_ref_table, FALSE) ||
                              ' (' || DBMS_ASSERT.ENQUOTE_NAME(p_ref_column, FALSE) || ')';
            DBMS_OUTPUT.PUT_LINE('Constraint ' || p_constraint_name || ' added.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Constraint ' || p_constraint_name || ' already existed.');
        END IF;

        -- 2. Manejar el Índice para la columna FK
        SELECT COUNT(*)
        INTO v_exists
        FROM user_indexes
        WHERE index_name = p_index_name
          AND table_name = p_table_name;

        IF v_exists = 0 THEN
            EXECUTE IMMEDIATE '
            CREATE INDEX ' || DBMS_ASSERT.ENQUOTE_NAME(p_index_name, FALSE) || '
            ON ' || DBMS_ASSERT.ENQUOTE_NAME(p_table_name, FALSE) || ' (' || DBMS_ASSERT.ENQUOTE_NAME(p_fk_column, FALSE) || ')
            TABLESPACE ' || DBMS_ASSERT.ENQUOTE_NAME(v_index_tablespace, FALSE); -- Usar la variable externa
            DBMS_OUTPUT.PUT_LINE('Index ' || p_index_name || ' created in tablespace ' || v_index_tablespace || '.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Index ' || p_index_name || ' already existed.');
        END IF;

    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error processing ' || p_constraint_name || ' or ' || p_index_name || ': ' || SQLERRM);
        -- Considerar RAISING el error si el script debe detenerse en caso de fallo
    END add_fk_and_index_if_missing;

BEGIN
    -- Llamada para la primera FK e Índice
    add_fk_and_index_if_missing(
            p_table_name        => 'DAILYREPORT',
            p_constraint_name   => 'FK_DAILYREPORT_INSTITUTION',
            p_fk_column         => 'idInstitution',
            p_ref_table         => 'Institution',
            p_ref_column        => 'id',
            p_index_name        => 'IX_DAILYREPORT_IDINSTITUTION'
    );

    -- Llamada para la segunda FK e Índice
    add_fk_and_index_if_missing(
            p_table_name        => 'DAILYREPORT',
            p_constraint_name   => 'FK_DAILYREPORT_AUDITLOG',
            p_fk_column         => 'idAuditLog',
            p_ref_table         => 'AuditLog',
            p_ref_column        => 'id',
            p_index_name        => 'IX_DAILYREPORT_IDAUDITLOG'
    );

    -- Puedes añadir más llamadas aquí para otras FKs...

END;
/