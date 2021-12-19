create table USER
(
    ID           INT auto_increment,
    NAME         VARCHAR(50),
    ACCOUNT_ID   VARCHAR(50),
    TOKEN        VARCHAR(50),
    GMT_CREATE   BIGINT,
    GMT_MODIFIED BIGINT,
    BIO          VARCHAR(256),
    constraint USER_PK
        primary key (ID)
);