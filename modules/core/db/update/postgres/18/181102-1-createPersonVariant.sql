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
);
