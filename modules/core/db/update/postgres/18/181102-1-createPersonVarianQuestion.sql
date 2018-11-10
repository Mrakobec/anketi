create table ANKETI_PERSON_VARIAN_QUESTION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ORDER_NUMBER integer,
    TYPE_ varchar(50),
    CONTENT text,
    QUESTION_ID uuid,
    PERSON_VARIAN_QUESTIONNAIRE_ID uuid,
    PERSON_VARIANT_ID uuid,
    --
    primary key (ID)
);
