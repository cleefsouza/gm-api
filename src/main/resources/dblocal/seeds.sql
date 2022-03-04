-- login seeds

INSERT INTO gm_schema.login
VALUES ('d343d88b-b632-422e-84b4-d680dd867503', 'xpepeta',
        '$2a$10$irVIP3cqP2OnD0AlGKu4B.YQYgdo6sZ2l.5cZdGhyJi5RHSzFEf1m');

-- hierarchy seeds

INSERT INTO gm_schema.hierarchy
VALUES ('295b06da-c06e-4920-86d1-bfea72914eba', 'Leader'),
       ('8650afb6-7b90-4e17-a27e-095b184a872e', 'Elder'),
       ('f4674c45-f11a-4523-b63a-0fd300e12ba5', 'Member');

-- class seeds

INSERT INTO gm_schema._class
VALUES ('37f9f16e-4128-47d4-9b45-f27b16bdff51', 'Arbalist'),
       ('4297b689-9e2f-4887-bd05-55b9be7eb40e', 'Lancer'),
       ('ebbb28d9-e656-4e2f-b053-99f8d5bfdbd0', 'Taoist'),
       ('a3a1bd54-4289-4be4-b791-6e2baed0c381', 'Warrior'),
       ('ce266987-f1a5-4e9c-a5e9-0a05b1c50618', 'Sorcerer');

-- guild seeds

INSERT INTO gm_schema.guild
VALUES ('fb489b09-bf0a-4c11-8a9a-da2d79402592', 'Ðragon Blood', 48, 50, 'NA41');

-- member seeds

INSERT INTO gm_schema.member
VALUES ('5135bf7e-c184-401c-b6d8-8378e79fa1e7', 'Thyrazgo', 100000, 81,
        'fb489b09-bf0a-4c11-8a9a-da2d79402592',
        'a3a1bd54-4289-4be4-b791-6e2baed0c381',
        '295b06da-c06e-4920-86d1-bfea72914eba');