-- config

CREATE ROLE db_user;

ALTER ROLE db_user WITH SUPERUSER
INHERIT
CREATEROLE
CREATEDB
LOGIN
NOREPLICATION
NOBYPASSRLS
PASSWORD 'db_pass';

CREATE DATABASE guild-manager-db WITH TEMPLATE = template0 OWNER = postgres;

GRANT ALL ON DATABASE guild-manager-db TO db_user;

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;
COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;
COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';

SET default_tablespace = '';
SET default_with_oids = false;

-- data

CONNECT guild-manager-db;

CREATE SCHEMA gm_schema;

CREATE TABLE gm_schema.hierarchy
(
    id   uuid PRIMARY KEY NOT NULL,
    name varchar(255)     NOT NULL
);

CREATE TABLE gm_schema.class
(
    id   uuid PRIMARY KEY NOT NULL,
    name varchar(255)     NOT NULL
);

CREATE TABLE gm_schema.guild
(
    id            uuid PRIMARY KEY NOT NULL,
    name          varchar(255)     NOT NULL,
    count_members int              NOT NULL DEAFULT 0
);

CREATE TABLE gm_schema.member
(
    id           uuid PRIMARY KEY NOT NULL,
    name         varchar(255)     NOT NULL,
    power        double           NOT NULL,
    level        int              NOT NULL,
    guild_id     uuid             NOT NULL,
    class_id     uuid             NOT NULL,
    hierarchy_id uuid             NOT NULL
);

ALTER TABLE gm_schema.member
    ADD CONSTRAINT fk_guild_id FOREIGN KEY (guild_id) REFERENCES gm_schema.guild (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE gm_schema.member
    ADD CONSTRAINT fk_class_id FOREIGN KEY (class_id) REFERENCES gm_schema.class (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE gm_schema.member
    ADD CONSTRAINT fk_hierarchy_id FOREIGN KEY (hierarchy_id) REFERENCES gm_schema.hierarchy (id) ON UPDATE NO ACTION ON DELETE NO ACTION;

INSERT INTO gm_schema.hierarchy
VALUES ('295b06da-c06e-4920-86d1-bfea72914eba', 'Leader'),
       ('8650afb6-7b90-4e17-a27e-095b184a872e', 'Elder'),
       ('f4674c45-f11a-4523-b63a-0fd300e12ba5', 'Member');

INSERT INTO gm_schema.class
VALUES ('37f9f16e-4128-47d4-9b45-f27b16bdff51', 'Arbalist'),
       ('4297b689-9e2f-4887-bd05-55b9be7eb40e', 'Lancer'),
       ('ebbb28d9-e656-4e2f-b053-99f8d5bfdbd0', 'Taoist'),
       ('a3a1bd54-4289-4be4-b791-6e2baed0c381', 'Warrior'),
        ('ce266987-f1a5-4e9c-a5e9-0a05b1c50618', 'Sorcerer');

INSERT INTO gm_schema.guild
VALUES ('fb489b09-bf0a-4c11-8a9a-da2d79402592', 'Ðragon Blood', 50);

INSERT INTO gm_schema.member
VALUES ('5135bf7e-c184-401c-b6d8-8378e79fa1e7', 'Thyrazgo', 100000, 81,
        'fb489b09-bf0a-4c11-8a9a-da2d79402592',
        'a3a1bd54-4289-4be4-b791-6e2baed0c381',
        '295b06da-c06e-4920-86d1-bfea72914eba');