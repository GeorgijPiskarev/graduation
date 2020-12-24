DELETE
FROM dishes;
DELETE
FROM restaurants;
DELETE
FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO restaurants (name, votes)
VALUES ('Restaurant1', 0),
       ('Restaurant2', 0);

INSERT INTO users (name, email, password,role)
VALUES ('User', 'user@yandex.ru', 'password','USER'),
       ('Admin', 'admin@gmail.com', 'admin','ADMIN');

INSERT INTO dishes (name, restaurant_id,price)
VALUES ('Суп', 100000, 500),
       ('Макароны', 100000, 1200),
       ('Котлета',100000, 520),
       ('Фрукты',100001, 100),
       ('Овощи', 100001, 600);