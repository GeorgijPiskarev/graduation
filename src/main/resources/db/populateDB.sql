DELETE
FROM user_roles;
DELETE
FROM VOTES;
DELETE
FROM dishes;
DELETE
FROM restaurants;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 100000),
       ('ADMIN', 100001);

INSERT INTO restaurants (name)
VALUES ('Scarlett Green'),
       ('Firebrand Pizza');

INSERT INTO dishes (name, restaurant_id, price)
VALUES ('Soup', 100002, 500),
       ('Pasta', 100002, 1200),
       ('Meatballs', 100002, 520),
       ('Fruits', 100003, 100),
       ('Vegetables', 100003, 600);