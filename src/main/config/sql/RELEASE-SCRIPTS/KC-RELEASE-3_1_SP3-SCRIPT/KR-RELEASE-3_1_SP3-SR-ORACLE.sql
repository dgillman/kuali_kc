set define off
set sqlblanklines on
spool KR-RELEASE-3_1_SP3-SR-ORACLE-Install.log
@ORACLE/DML/KR_DML_BS1_KRIM_PERM_T.sql
@ORACLE/DML/SR/KR_DML_BS1_KRNS_PARM_T.sql
@ORACLE/DML/KR_DML_BS2_KRIM_PERM_ATTR_DATA_T.sql
@ORACLE/DML/KR_DML_BS2_KRIM_ROLE_T.sql
@ORACLE/DML/KR_DML_BS3_KRIM_ROLE_PERM_T.sql
@ORACLE/DML/KR_DML_BS4_KRIM_GRP_T.sql
--@ORACLE/DML/KR_DML_BS5_KREW_DOC_HDR_T.sql
@ORACLE/DML/SR/KR_DML_BS5_KRNS_PARM_T.sql
--@ORACLE/DML/KR_DML_BS6_KREW_ACTN_TKN_T.sql
--@ORACLE/DML/KR_DML_BS6_KREW_DOC_HDR_CNTNT_T.sql
--@ORACLE/DML/KR_DML_BS6_KREW_RTE_NODE_INSTN_T.sql
@ORACLE/DML/KR_DML_BS7_KREN_CHNL_PRODCR_T.sql
--@ORACLE/DML/KR_DML_BS7_KREW_ACTN_RQST_T.sql
--@ORACLE/DML/KR_DML_BS7_KRIM_AFLTN_TYP_T.sql
@ORACLE/DML/KR_DML_BS7_KRIM_TYP_T.sql
commit;
exit