delete from PROPOSAL_LOG;
delete from PROPOSAL_LOG_STATUS;
insert into PROPOSAL_LOG_STATUS (PROPOSAL_LOG_STATUS_CODE, DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER) 
values ('1', 'PENDING', SYSDATE, USER);
