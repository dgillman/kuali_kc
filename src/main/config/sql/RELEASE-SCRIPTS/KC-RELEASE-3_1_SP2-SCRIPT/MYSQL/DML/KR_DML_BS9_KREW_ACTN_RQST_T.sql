INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_ID,ACTN_RQST_CD,DOC_HDR_ID,STAT_CD,RSP_ID,PRNCPL_ID,RECIP_TYP_CD,PRIO_NBR,RTE_LVL_NBR,RTE_NODE_INSTN_ID,ACTN_TKN_ID,DOC_VER_NBR,CRTE_DT,RSP_DESC_TXT,FRC_ACTN,APPR_PLCY,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),'C',(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'NSF s2s form supporting questions'),'D',-3,'10000000001','U',0,0,(SELECT RTE_NODE_INSTN_ID FROM KREW_RTE_NODE_INSTN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'NSF s2s form supporting questions')),(SELECT ACTN_TKN_ID FROM KREW_ACTN_TKN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'NSF s2s form supporting questions') AND ACTN_CD = 'C'),1,STR_TO_DATE('20100708','%Y%m%d'),'Initiator needs to complete document.',1,'F',1,0);

INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_ID,ACTN_RQST_CD,DOC_HDR_ID,STAT_CD,RSP_ID,PRNCPL_ID,RECIP_TYP_CD,PRIO_NBR,RTE_LVL_NBR,RTE_NODE_INSTN_ID,ACTN_TKN_ID,DOC_VER_NBR,CRTE_DT,RSP_DESC_TXT,FRC_ACTN,APPR_PLCY,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),'C',(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-0 & 1-1'),'D',-3,'10000000001','U',0,0,(SELECT RTE_NODE_INSTN_ID FROM KREW_RTE_NODE_INSTN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-0 & 1-1')),(SELECT ACTN_TKN_ID FROM KREW_ACTN_TKN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-0 & 1-1') AND ACTN_CD = 'C'),1,STR_TO_DATE('20100708','%Y%m%d'),'Initiator needs to complete document.',1,'F',1,0);

INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_ID,ACTN_RQST_CD,DOC_HDR_ID,STAT_CD,RSP_ID,PRNCPL_ID,RECIP_TYP_CD,PRIO_NBR,RTE_LVL_NBR,RTE_NODE_INSTN_ID,ACTN_TKN_ID,DOC_VER_NBR,CRTE_DT,RSP_DESC_TXT,FRC_ACTN,APPR_PLCY,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),'C',(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS398 Training Budget Form version 1-0'),'D',-3,'10000000001','U',0,0,(SELECT RTE_NODE_INSTN_ID FROM KREW_RTE_NODE_INSTN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS398 Training Budget Form version 1-0')),(SELECT ACTN_TKN_ID FROM KREW_ACTN_TKN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS398 Training Budget Form version 1-0') AND ACTN_CD = 'C'),1,STR_TO_DATE('20100708','%Y%m%d'),'Initiator needs to complete document.',1,'F',1,0);

INSERT INTO KREW_ACTN_TKN_S VALUES (NULL);
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_ID,ACTN_RQST_CD,DOC_HDR_ID,STAT_CD,RSP_ID,PRNCPL_ID,RECIP_TYP_CD,PRIO_NBR,RTE_LVL_NBR,RTE_NODE_INSTN_ID,ACTN_TKN_ID,DOC_VER_NBR,CRTE_DT,RSP_DESC_TXT,FRC_ACTN,APPR_PLCY,CUR_IND,VER_NBR) 
  VALUES ((SELECT MAX(ID) FROM KREW_ACTN_TKN_S),'C',(SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-2'),'D',-3,'10000000001','U',0,0,(SELECT RTE_NODE_INSTN_ID FROM KREW_RTE_NODE_INSTN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-2')),(SELECT ACTN_TKN_ID FROM KREW_ACTN_TKN_T WHERE DOC_HDR_ID = (SELECT DOC_HDR_ID FROM KREW_DOC_HDR_T WHERE TTL = 'PHS Fellowship Supplemental Form V1-2') AND ACTN_CD = 'C'),1,STR_TO_DATE('20100708','%Y%m%d'),'Initiator needs to complete document.',1,'F',1,0);

COMMIT;