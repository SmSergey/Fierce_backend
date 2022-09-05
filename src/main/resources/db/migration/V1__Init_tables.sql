CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE app_role
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_app_role PRIMARY KEY (id)
);

CREATE TABLE app_user
(
    id         BIGINT NOT NULL,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    password   VARCHAR(255),
    CONSTRAINT pk_app_user PRIMARY KEY (id)
);