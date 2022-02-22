-- config

CREATE DATABASE guild_manager_db;

DO
$do$
    BEGIN
        IF NOT EXISTS(
            SELECT
            FROM pg_catalog.pg_roles
            WHERE rolname = 'guild-manager'
        ) THEN
            CREATE ROLE "guild-manager" LOGIN PASSWORD 'guild-manager';
            GRANT ALL PRIVILEGES ON DATABASE guild_manager_db TO "guild-manager";
        END IF;
    END
$do$;

ALTER ROLE "guild-manager" WITH LOGIN;

ALTER DATABASE "guild_manager_db" OWNER TO "guild-manager";

CREATE SCHEMA gm_schema;

ALTER SCHEMA gm_schema OWNER TO "guild-manager";
