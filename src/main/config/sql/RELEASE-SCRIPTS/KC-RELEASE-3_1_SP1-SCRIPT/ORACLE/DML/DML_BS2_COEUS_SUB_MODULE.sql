insert into COEUS_SUB_MODULE (COEUS_SUB_MODULE_ID , MODULE_CODE , SUB_MODULE_CODE , DESCRIPTION, UPDATE_TIMESTAMP ,UPDATE_USER, VER_NBR, OBJ_ID)   values 
( 1,3,1,'Proposal Budget',SYSDATE, 'quickstart',1,SYS_GUID());      
 
insert into COEUS_SUB_MODULE (COEUS_SUB_MODULE_ID , MODULE_CODE , SUB_MODULE_CODE , DESCRIPTION, UPDATE_TIMESTAMP ,UPDATE_USER, VER_NBR, OBJ_ID)   values 
( 2,7,1,'Amendment / Renewal',SYSDATE, 'quickstart',1,SYS_GUID());    

insert into COEUS_SUB_MODULE (COEUS_SUB_MODULE_ID , MODULE_CODE , SUB_MODULE_CODE , DESCRIPTION, UPDATE_TIMESTAMP ,UPDATE_USER, VER_NBR, OBJ_ID)   values 
( 3,7,2,'Protocol Submission',SYSDATE, 'quickstart',1,SYS_GUID());        

INSERT INTO COEUS_SUB_MODULE ( COEUS_SUB_MODULE_ID, MODULE_CODE, SUB_MODULE_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER, VER_NBR, OBJ_ID )
VALUES ( 4, 3, 2, 'S2S Questionnaires', SYSDATE, 'kr', '0', SYS_GUID() );

COMMIT;
