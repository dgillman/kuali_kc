DECLARE   max_id NUMBER; max_sq NUMBER;
BEGIN
  SELECT max(PROPOSAL_PERSON_ID) INTO max_id FROM PROPOSAL_PERSONS;
  SELECT max(PROPOSAL_PERSON_UNIT_ID) INTO max_sq FROM PROPOSAL_PERSON_UNITS;
  IF max_id is null THEN max_id:=0; END IF;
  IF max_sq is null THEN max_sq:=0; END IF;
  SELECT GREATEST (max_id, max_sq) INTO max_id FROM dual;
  SELECT SEQ_PROPOSAL_PROPOSAL_ID.NEXTVAL INTO max_sq FROM dual;
  IF max_sq < max_id THEN 
   max_sq := max_id - max_sq;
   EXECUTE IMMEDIATE 'ALTER SEQUENCE SEQ_PROPOSAL_PROPOSAL_ID INCREMENT BY ' || max_sq; 
   EXECUTE IMMEDIATE 'SELECT SEQ_PROPOSAL_PROPOSAL_ID.NEXTVAL FROM DUAL' INTO max_id;
   EXECUTE IMMEDIATE 'ALTER SEQUENCE SEQ_PROPOSAL_PROPOSAL_ID INCREMENT BY 1';
  END IF;
END;
/