CREATE TABLESPACE CARPOOLING_DATA
    DATAFILE 'C:\app\User\oradata\FARA_DB\carpooling_data01.dbf' -- ¡¡VERIFICAR Y AJUSTAR RUTA!!
    SIZE 100M
    AUTOEXTEND ON
    NEXT 50M
    MAXSIZE 10G;
CREATE TABLESPACE CARPOOLING_INDX
    DATAFILE 'C:\app\User\oradata\FARA_DB\carpooling_indx01.dbf' -- ¡¡VERIFICAR Y AJUSTAR RUTA!!
    SIZE 50M
    AUTOEXTEND ON
    NEXT 25M
    MAXSIZE 500M;
