-- 3.1 Privilegio ESENCIAL para conectarse a la base de datos.
GRANT CONNECT TO CARPOOLING_APP_USER;

-- 3.2 Rol RESOURCE incluye:
--     - CREATE TABLE
--     - CREATE SEQUENCE
--     - CREATE PROCEDURE (Crear procedimientos, funciones y paquetes)
--     - CREATE TRIGGER
--     - CREATE TYPE
--     - CREATE OPERATOR
--     - CREATE INDEXTYPE
GRANT RESOURCE TO CARPOOLING_APP_USER;
GRANT CREATE VIEW TO CARPOOLING_APP_USER;