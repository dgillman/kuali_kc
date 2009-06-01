-- Table Script 
CREATE TABLE COEUS_SUB_MODULE ( 
    COEUS_SUB_MODULE_ID NUMBER(12,0) NOT NULL, 
    MODULE_CODE NUMBER(3,0) NOT NULL, 
    SUB_MODULE_CODE NUMBER(3,0) NOT NULL, 
    DESCRIPTION VARCHAR2(200) NOT NULL, 
    UPDATE_TIMESTAMP DATE NOT NULL, 
    UPDATE_USER VARCHAR2(60) NOT NULL, 
    VER_NBR NUMBER(8,0) DEFAULT 1 NOT NULL, 
    OBJ_ID VARCHAR2(36) DEFAULT SYS_GUID() NOT NULL);


-- Primary Key Constraint 
ALTER TABLE COEUS_SUB_MODULE 
ADD CONSTRAINT PK_COEUS_SUB_MODULE 
PRIMARY KEY (COEUS_SUB_MODULE_ID);

-- *************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ 
-- ALTER TABLE COEUS_SUB_MODULE 
-- ADD CONSTRAINT PK_COEUS_SUB_MODULE 
-- PRIMARY KEY (MODULE_CODE, SUB_MODULE_CODE);
-- *************** MODIFIED PRIMARY KEY COLUMN - Introduced new primary key for existing composite key ************ 
 

-- *************** UNIQUE CONSTRAINT DEFINED FOR COMPOSITE KEY COLUMNS ************ 
ALTER TABLE COEUS_SUB_MODULE 
ADD CONSTRAINT UQ_COEUS_SUB_MODULE 
UNIQUE (MODULE_CODE, SUB_MODULE_CODE);

-- Foreign Key Constraint(s)
ALTER TABLE COEUS_SUB_MODULE 
ADD CONSTRAINT FK_COEUS_SUB_MODULE 
FOREIGN KEY (MODULE_CODE) 
REFERENCES COEUS_MODULE (MODULE_CODE);

