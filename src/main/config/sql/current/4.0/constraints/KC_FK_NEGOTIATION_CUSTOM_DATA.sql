ALTER TABLE NEGOTIATION_CUSTOM_DATA 
ADD CONSTRAINT FK_NEGOTIATION_CUSTOM_DATA 
FOREIGN KEY (CUSTOM_ATTRIBUTE_ID)
REFERENCES CUSTOM_ATTRIBUTE(ID)
/