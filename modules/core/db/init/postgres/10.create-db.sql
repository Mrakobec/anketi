-- begin ANKETI_ANSWER_VARIAN
create table ANKETI_ANSWER_VARIAN (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ integer,
    CONTENT text,
    QUESTION_ID uuid,
    --
    primary key (ID)
)^
-- end ANKETI_ANSWER_VARIAN
-- begin ANKETI_QUESTION
create table ANKETI_QUESTION (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ integer,
    TYPE_ varchar(50),
    CONTENT text,
    QUESTIONNAIRE_ID uuid,
    --
    primary key (ID)
)^
-- end ANKETI_QUESTION
-- begin ANKETI_QUESTIONNAIRE
create table ANKETI_QUESTIONNAIRE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ integer,
    NAME text,
    STATUS_ID varchar(255),
    --
    primary key (ID)
)^
-- end ANKETI_QUESTIONNAIRE
-- begin ANKETI_PERSON_VARIANT_ANSWER
create table ANKETI_PERSON_VARIANT_ANSWER (
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
    CONTENT text,
    SELECTED_BY_PERSON boolean,
    PERSON_VARIAN_QUESTION_ID uuid,
    --
    primary key (ID)
)^
-- end ANKETI_PERSON_VARIANT_ANSWER
-- begin ANKETI_PERSON_VARIAN_QUESTION
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
)^
-- end ANKETI_PERSON_VARIAN_QUESTION
-- begin ANKETI_PERSON_VARIAN_QUESTIONNAIRE
create table ANKETI_PERSON_VARIAN_QUESTIONNAIRE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ integer,
    NAME text,
    PERSON_VARIANT_ID uuid,
    --
    primary key (ID)
)^
-- end ANKETI_PERSON_VARIAN_QUESTIONNAIRE
-- begin ANKETI_PERSON_VARIANT
create table ANKETI_PERSON_VARIANT (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME text,
    START_TIME timestamp,
    END_TIME timestamp,
    SOLVER_LOGIN varchar(255),
    --
    primary key (ID)
)^
-- end ANKETI_PERSON_VARIANT
