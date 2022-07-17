DELETE FROM pets;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, password)
VALUES ('user1', 'pwd1'),
       ('user2', 'pwd2');

INSERT INTO pets (name, birthdate, type, sex, user_id)
VALUES ('Sharik', '2020-01-30', 'DOG', 'MALE', 100000),
       ('Pushok', '2020-04-30', 'CAT', 'MALE', 100000),
       ('Karlusha', '2020-05-30', 'PARROT', 'FEMALE', 100000),
       ('Moska', '2021-01-10', 'DOG', 'FEMALE', 100001),
       ('Rizhik', '2022-03-22', 'CAT', 'MALE', 100001);