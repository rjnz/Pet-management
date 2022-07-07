DELETE FROM pets;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, password)
VALUES ('User', 'password');

INSERT INTO pets (name, type, sex, user_id)
VALUES ('Шарик', 'DOG', 'MALE', 100000),
       ('Пушок', 'CAT', 'MALE', 100000),
       ('Карлуша', 'PARROT', 'FEMALE', 100000);