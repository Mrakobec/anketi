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
);
