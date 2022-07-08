DELETE FROM pets;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, password)
VALUES ('user1', 'pwd1'),
       ('user2', 'pwd2');

INSERT INTO pets (name, type, sex, user_id)
VALUES ('Sharik', 'DOG', 'MALE', 100000),
       ('Pushok', 'CAT', 'MALE', 100000),
       ('Karlusha', 'PARROT', 'FEMALE', 100000),
       ('Moska', 'DOG', 'FEMALE', 100001),
       ('Rizhik', 'CAT', 'MALE', 100001);