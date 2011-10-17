ALTER TABLE SUBAWARD_CONTACT
  ADD CONSTRAINT UQ_SUBAWARD_CONTACT UNIQUE (CONTACT_TYPE_CODE, ROLODEX_ID)
/
ALTER TABLE SUBAWARD_CONTACT
  ADD CONSTRAINT FK_SUBAWARD_CONTACT_CONTACT
  FOREIGN KEY (CONTACT_TYPE_CODE)  REFERENCES CONTACT_TYPE (CONTACT_TYPE_CODE)
/
ALTER TABLE SUBAWARD_CONTACT
  ADD CONSTRAINT FK_SUBAWARD_CONTACT_ROLODEX 
  FOREIGN KEY (ROLODEX_ID) REFERENCES ROLODEX (ROLODEX_ID)
/
ALTER TABLE SUBAWARD_CONTACT
  ADD CONSTRAINT FK_SUBAWARD_CONTACT 
  FOREIGN KEY (SUBAWARD_ID) REFERENCES SUBAWARD (SUBAWARD_ID)
/
COMMIT 
/
