-- For getting an array of strings
CREATE OR REPLACE TYPE type_string_array IS VARRAY(20) OF VARCHAR2(255);
/

-- It's thought to group together the necessary information for a single credential that a user might have
CREATE OR REPLACE TYPE type_credential_record IS OBJECT (
                                                            credential_number VARCHAR2(255),
                                                            type_id           NUMBER
                                                        );
/

-- This defines a collection type, specifically a nested table. It's designed to hold a list or array of type_credential_record objects.
CREATE OR REPLACE TYPE type_credential_table IS TABLE OF type_credential_record;
/