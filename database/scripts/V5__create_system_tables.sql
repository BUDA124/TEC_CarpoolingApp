-- Bloque Crear Tabla
DECLARE
    v_table_exists NUMBER;
BEGIN
    -- Comprueba si la tabla ya existe en el esquema del usuario actual
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'DAILYREPORT'; -- IMPORTANTE: Los nombres de tabla se guardan en mayúsculas por defecto

    -- Si la tabla no existe (count = 0), entonces créala
    IF v_table_exists = 0 THEN
        -- Usa EXECUTE IMMEDIATE para ejecutar DDL (Data Definition Language) dentro de PL/SQL
        EXECUTE IMMEDIATE '
        CREATE TABLE DailyReport (
                                     id          NUMBER          CONSTRAINT pk_dailyreport PRIMARY KEY,
                                     reportType  VARCHAR2(100)   CONSTRAINT nn_DailyReport_reportType NULL
        )';
        DBMS_OUTPUT.PUT_LINE('Tabla 1 creada exitosamente.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Tabla 1 ya existe. No se realizó ninguna acción.');
    END IF;
END;
/

-- Bloque Crear Tabla
DECLARE
    v_table_exists NUMBER;
BEGIN
    -- Comprueba si la tabla ya existe en el esquema del usuario actual
    SELECT COUNT(*)
    INTO v_table_exists
    FROM user_tables
    WHERE table_name = 'INSTITUTION'; -- Nombre de la tabla en mayúsculas

    -- Si la tabla no existe (count = 0), entonces créala
    IF v_table_exists = 0 THEN
        -- Usa EXECUTE IMMEDIATE para ejecutar DDL dentro de PL/SQL
        EXECUTE IMMEDIATE '
        CREATE TABLE Institution (
                                     id                NUMBER,
                                     emailDomain       VARCHAR2(100), -- Tamaño ejemplo, ajústalo si es necesario
                                     institutionName   VARCHAR2(255), -- Tamaño ejemplo
                                     website           VARCHAR2(512), -- Tamaño ejemplo para URLs

            -- Constraint para la Clave Primaria (PK)
                                     CONSTRAINT pk_Institution_id PRIMARY KEY (id),
                                     CONSTRAINT uk_Institution_emaildomain UNIQUE (emailDomain),
                                     CONSTRAINT nn_Institution_emaildomain CHECK (emailDomain IS NOT NULL),
                                     CONSTRAINT nn_Institution_institutionname CHECK (institutionName IS NOT NULL)

            -- Nota: La constraint PK (pk_institution_id) ya asegura que id IS NOT NULL
            -- Nota: website es opcional (puede ser NULL) por lo que no tiene constraint NOT NULL
        )';
        DBMS_OUTPUT.PUT_LINE('Tabla Institution creada exitosamente.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Tabla Institution ya existe. No se realizó ninguna acción.');
    END IF;
END;
/