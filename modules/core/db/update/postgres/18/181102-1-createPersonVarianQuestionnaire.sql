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
);
