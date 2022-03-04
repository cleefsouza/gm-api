SET ROLE "guild-manager";

-- login table

CREATE TABLE gm_schema.login
(
    id         UUID PRIMARY KEY         NOT NULL,
    username   VARCHAR(255) UNIQUE      NOT NULL,
    password   VARCHAR(255)             NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    deleted    BOOLEAN                  NOT NULL DEFAULT FALSE
);

-- hierarchy table

CREATE TABLE gm_schema.hierarchy
(
    id         UUID PRIMARY KEY         NOT NULL,
    name       VARCHAR(255)             NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

-- class table

CREATE TABLE gm_schema._class
(
    id         UUID PRIMARY KEY         NOT NULL,
    name       VARCHAR(255)             NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now()
);

COMMENT ON TABLE gm_schema._class IS 'Use of underscore due to the word "class" being reserved by the kotlin language';

-- guild table

CREATE TABLE gm_schema.guild
(
    id            UUID PRIMARY KEY         NOT NULL,
    name          VARCHAR(255)             NOT NULL,
    level         INT                      NOT NULL DEFAULT 1,
    count_members INT                      NOT NULL DEFAULT 0,
    server        VARCHAR(255)             NOT NULL,
    created_at    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at    TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    deleted       BOOLEAN                  NOT NULL DEFAULT FALSE
);

-- member table

CREATE TABLE gm_schema.member
(
    id           UUID PRIMARY KEY         NOT NULL,
    name         VARCHAR(255)             NOT NULL,
    power        DOUBLE PRECISION         NOT NULL,
    level        INT                      NOT NULL,
    guild_id     UUID                     NOT NULL,
    class_id     UUID                     NOT NULL,
    hierarchy_id UUID                     NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    deleted      BOOLEAN                  NOT NULL DEFAULT FALSE
);

ALTER TABLE gm_schema.member
    ADD CONSTRAINT fk_guild_id FOREIGN KEY (guild_id) REFERENCES gm_schema.guild (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE gm_schema.member
    ADD CONSTRAINT fk_class_id FOREIGN KEY (class_id) REFERENCES gm_schema._class (id) ON UPDATE NO ACTION ON DELETE NO ACTION;
ALTER TABLE gm_schema.member
    ADD CONSTRAINT fk_hierarchy_id FOREIGN KEY (hierarchy_id) REFERENCES gm_schema.hierarchy (id) ON UPDATE NO ACTION ON DELETE NO ACTION;