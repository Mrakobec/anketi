alter table ANKETI_PERSON_VARIAN_QUESTIONNAIRE add constraint FK_ANKETI_PERSON_VARIAN_QUESTIONNAIRE_ON_PERSON_VARIANT foreign key (PERSON_VARIANT_ID) references ANKETI_PERSON_VARIANT(ID);
create index IDX_ANKETI_PERSON_VARIAN_QUESTIONNAIRE_ON_PERSON_VARIANT on ANKETI_PERSON_VARIAN_QUESTIONNAIRE (PERSON_VARIANT_ID);
