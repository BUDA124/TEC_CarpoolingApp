-- ########## PASO 3: CONCEDER PRIVILEGIOS A CARPOOLING_APP_USER ##########

-- 3.1 Privilegio ESENCIAL para conectarse a la base de datos.
GRANT CONNECT TO CARPOOLING_APP_USER;

-- 3.2 Rol RESOURCE: Concede un conjunto de privilegios para CREAR objetos
--     comunes DENTRO DE SU PROPIO SCHEMA. Esto incluye:
--     - CREATE TABLE  (Crear tablas)
--     - CREATE SEQUENCE (Crear secuencias)
--     - CREATE PROCEDURE (Crear procedimientos, funciones y paquetes)
--     - CREATE TRIGGER (Crear triggers)
--     - CREATE TYPE
--     - CREATE OPERATOR
--     - CREATE INDEXTYPE
--     IMPORTANTE: NO incluye CREATE VIEW por defecto.
GRANT RESOURCE TO CARPOOLING_APP_USER;

-- 3.3 Privilegio específico para CREAR VISTAS (Views) en su propio schema,
GRANT CREATE VIEW TO CARPOOLING_APP_USER;