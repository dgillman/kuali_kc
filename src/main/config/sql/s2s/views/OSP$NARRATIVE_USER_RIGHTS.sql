create or replace view OSP$NARRATIVE_USER_RIGHTS as 
	select PROPOSAL_NUMBER,MODULE_NUMBER,USER_ID,ACCESS_TYPE,UPDATE_TIMESTAMP,UPDATE_USER
	
	from NARRATIVE_USER_RIGHTS;