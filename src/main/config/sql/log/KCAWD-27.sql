/* Table Script */ 
CREATE TABLE VALID_RATES ( 
	VALID_RATES_ID NUMBER(12,0) NOT NULL, 
	ON_CAMPUS_RATE NUMBER(5,2) NOT NULL, 
	OFF_CAMPUS_RATE NUMBER(5,2) NOT NULL, 
	RATE_CLASS_TYPE VARCHAR2(1) NOT NULL, 
	ADJUSTMENT_KEY VARCHAR2(6) NOT NULL, 
	OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL, 
	VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
	UPDATE_TIMESTAMP DATE NOT NULL, 
	UPDATE_USER VARCHAR2(60) NOT NULL)
/

/* Primary Key Constraint */ 
ALTER TABLE VALID_RATES 
ADD CONSTRAINT PK_VALID_RATES 
PRIMARY KEY (VALID_RATES_ID)
/

/* View for Coeus compatibility */ 
CREATE OR REPLACE VIEW OSP$VALID_RATES AS SELECT 
	ON_CAMPUS_RATE, 
	OFF_CAMPUS_RATE, 
	RATE_CLASS_TYPE, 
	ADJUSTMENT_KEY, 
	UPDATE_TIMESTAMP, 
	UPDATE_USER
FROM VALID_RATES
/