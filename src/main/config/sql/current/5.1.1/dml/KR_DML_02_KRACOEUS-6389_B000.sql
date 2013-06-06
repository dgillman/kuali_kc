insert into KRMS_AGENDA_T (agenda_id, nm, cntxt_id, init_agenda_itm_id, typ_id, actv, ver_nbr)
  VALUES (CONCAT('KC', KRMS_AGENDA_S.NEXTVAL), 'COI Disclosure Validation Agenda', 'KC-COIDISCLOSURE-CONTEXT', null, (select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KC-KRMS' and NM = 'Unit Agenda'), 'Y', 1)
/

insert into KRMS_AGENDA_ATTR_T (agenda_attr_id, agenda_id, 
	attr_val, attr_defn_id, ver_nbr)
  VALUES (CONCAT('KC', KRMS_AGENDA_ATTR_S.NEXTVAL), (select AGENDA_ID from KRMS_AGENDA_T where CNTXT_ID = 'KC-COIDISCLOSURE-CONTEXT' and NM = 'COI Disclosure Validation Agenda'), 
  	'000001', (select ATTR_DEFN_ID from KRMS_ATTR_DEFN_T where NMSPC_CD = 'KC-KRMS' and NM = 'Unit Number'), 1)
/

insert into KRMS_RULE_T (rule_id, nmspc_cd, nm, 
	typ_id, prop_id, actv, ver_nbr, desc_txt)
  VALUES (CONCAT('KC', KRMS_RULE_S.NEXTVAL), 'KC-COIDISCLOSURE', 'COI Screening Questionnaire Validation', 
  	(select typ_id from KRMS_TYP_T where NMSPC_CD = 'KR-RULE' and NM = 'Validation Rule'), null, 'Y', 1, 'COI Screening Questionnaire Validation to ensure necessary financial entities are created.')
/

insert into KRMS_RULE_ATTR_T (RULE_ATTR_ID, RULE_ID, 
		ATTR_DEFN_ID, ATTR_VAL, VER_NBR)
	VALUES (CONCAT('KC', KRMS_RULE_ATTR_S.NEXTVAL), (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'),
	(select ATTR_DEFN_ID from KRMS_ATTR_DEFN_T where NMSPC_CD = 'KR-RULE' and NM = 'ruleTypeCode'), 'I', 1)
/

insert into KRMS_ACTN_T (ACTN_ID, NM, DESC_TXT, TYP_ID, 
		RULE_ID, SEQ_NO, VER_NBR, NMSPC_CD)
	VALUES (CONCAT('KC', KRMS_ACTN_S.NEXTVAL), 'Require Financial Entity', null, (select TYP_ID from KRMS_TYP_T where NMSPC_CD = 'KR-RULE' and NM = 'Validation Action'),
	(select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'), 1, 1, 'KC-COIDISCLOSURE')
/

insert into KRMS_ACTN_ATTR_T (ACTN_ATTR_DATA_ID, ACTN_ID, 
		ATTR_DEFN_ID, ATTR_VAL, VER_NBR)
	VALUES (CONCAT('KC', KRMS_ACTN_ATTR_S.NEXTVAL), (select ACTN_ID from KRMS_ACTN_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'Require Financial Entity'),
		(select ATTR_DEFN_ID from KRMS_ATTR_DEFN_T where NMSPC_CD = 'KR-RULE' and NM = 'actionMessage'), 'Based on answers to the screening questionnaire you are required to have at least one active financial entity to submit this disclosure.', 1)
/

insert into KRMS_ACTN_ATTR_T (ACTN_ATTR_DATA_ID, ACTN_ID, 
		ATTR_DEFN_ID, ATTR_VAL, VER_NBR)
	VALUES (CONCAT('KC', KRMS_ACTN_ATTR_S.NEXTVAL), (select ACTN_ID from KRMS_ACTN_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'Require Financial Entity'),
		(select ATTR_DEFN_ID from KRMS_ATTR_DEFN_T where NMSPC_CD = 'KR-RULE' and NM = 'actionTypeCode'), 'E', 1)
/

insert into KRMS_AGENDA_ITM_T (AGENDA_ITM_ID, RULE_ID, 
		SUB_AGENDA_ID, AGENDA_ID, VER_NBR, WHEN_TRUE, WHEN_FALSE, ALWAYS)
	VALUES (CONCAT('KC', KRMS_AGENDA_ITM_S.NEXTVAL), (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'),
	null, (select AGENDA_ID from KRMS_AGENDA_T where CNTXT_ID = 'KC-COIDISCLOSURE-CONTEXT' and NM = 'COI Disclosure Validation Agenda'), 1, null, null, null)
/

update KRMS_AGENDA_T set INIT_AGENDA_ITM_ID = (select AGENDA_ITM_ID from KRMS_AGENDA_ITM_T where AGENDA_ID = (select AGENDA_ID from KRMS_AGENDA_T where CNTXT_ID = 'KC-COIDISCLOSURE-CONTEXT' and NM = 'COI Disclosure Validation Agenda') and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'))
	where AGENDA_ID = (select AGENDA_ID from KRMS_AGENDA_T where CNTXT_ID = 'KC-COIDISCLOSURE-CONTEXT' and NM = 'COI Disclosure Validation Agenda')
/

insert into KRMS_PROP_T (PROP_ID, DESC_TXT, TYP_ID, DSCRM_TYP_CD, CMPND_OP_CD, RULE_ID, VER_NBR, CMPND_SEQ_NO)
	VALUES (CONCAT('KC', KRMS_PROP_S.NEXTVAL), 'Compound Proposition', null, 'C', '&', (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'), 1, null)
/

update KRMS_RULE_T set PROP_ID = (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Compound Proposition' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')) 
	where RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')
/

insert into KRMS_PROP_T (PROP_ID, DESC_TXT, TYP_ID, DSCRM_TYP_CD, CMPND_OP_CD, RULE_ID, VER_NBR, CMPND_SEQ_NO)
	VALUES (CONCAT('KC', KRMS_PROP_S.NEXTVAL), 'Yes answer to screening question', null, 'S', null, (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'), 1, 1)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, 
	PARM_VAL, 
	PARM_TYP_CD, SEQ_NO, VER_NBR)
	VALUES (CONCAT('KC', KRMS_PROP_PARM_S.NEXTVAL), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Yes answer to screening question' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
		(select TERM_ID from KRMS_TERM_T where TERM_SPEC_ID = (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='getScreeningQuestionYesAnswerCount' and NMSPC_CD='KC-COIDISCLOSURE'))),
		'T', 0, 1)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, 
	PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
	VALUES (CONCAT('KC', KRMS_PROP_PARM_S.NEXTVAL), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Yes answer to screening question' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
		'1', 'C', 1, 1)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, 
	PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
	VALUES (CONCAT('KC', KRMS_PROP_PARM_S.NEXTVAL), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Yes answer to screening question' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
		'>=', 'O', 2, 1)
/

insert into KRMS_PROP_T (PROP_ID, DESC_TXT, TYP_ID, DSCRM_TYP_CD, CMPND_OP_CD, RULE_ID, VER_NBR, CMPND_SEQ_NO)
	VALUES (CONCAT('KC', KRMS_PROP_S.NEXTVAL), 'No active financial entities', null, 'S', null, (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'), 1, 2)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, 
	PARM_VAL, 
	PARM_TYP_CD, SEQ_NO, VER_NBR)
	VALUES (CONCAT('KC', KRMS_PROP_PARM_S.NEXTVAL), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'No active financial entities' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
		(select TERM_ID from KRMS_TERM_T where TERM_SPEC_ID = (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-COIDISCLOSURE' and 
					NM=(select FUNC_ID from KRMS_FUNC_T where  NM='getReporterActiveFinancialEntityCount' and NMSPC_CD='KC-COIDISCLOSURE'))),
		'T', 0, 1)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, 
	PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
	VALUES (CONCAT('KC', KRMS_PROP_PARM_S.NEXTVAL), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'No active financial entities' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
		'0', 'C', 1, 1)
/

insert into KRMS_PROP_PARM_T (PROP_PARM_ID, PROP_ID, 
	PARM_VAL, PARM_TYP_CD, SEQ_NO, VER_NBR)
	VALUES (CONCAT('KC', KRMS_PROP_PARM_S.NEXTVAL), (select PROP_ID from KRMS_PROP_T where DESC_TXT = 'No active financial entities' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
		'=', 'O', 2, 1)
/

insert into KRMS_CMPND_PROP_PROPS_T (CMPND_PROP_ID, PROP_ID)
	VALUES ((select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Compound Proposition' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
		(select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Yes answer to screening question' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'))
	)
/

insert into KRMS_CMPND_PROP_PROPS_T (CMPND_PROP_ID, PROP_ID)
	VALUES ((select PROP_ID from KRMS_PROP_T where DESC_TXT = 'Compound Proposition' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation')),
		(select PROP_ID from KRMS_PROP_T where DESC_TXT = 'No active financial entities' and RULE_ID = (select RULE_ID from KRMS_RULE_T where NMSPC_CD = 'KC-COIDISCLOSURE' and NM = 'COI Screening Questionnaire Validation'))
	)
/


