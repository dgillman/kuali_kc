CREATE OR REPLACE VIEW OSP$PROTOCOL_VULNERABLE_SUB AS 
SELECT PROTOCOL_NUMBER,
       SEQUENCE_NUMBER,
       VULNERABLE_SUBJECT_TYPE_CODE,
       SUBJECT_COUNT,
       UPDATE_TIMESTAMP,
       UPDATE_USER
FROM   PROTOCOL_VULNERABLE_SUB;