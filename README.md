[![Codacy Badge](https://api.codacy.com/project/badge/Grade/7d800090508e4ce88d7933118e4eacf9)](https://app.codacy.com/gh/GeorgijPiskarev/graduation?utm_source=github.com&utm_medium=referral&utm_content=GeorgijPiskarev/graduation&utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.org/GeorgijPiskarev/graduation.svg?branch=master)](https://travis-ci.org/GeorgijPiskarev/graduation)

REST web service for voting the best restaurant
==============================

Statement of the problem
------------------------------

Design and implement a REST API using Hibernate/Spring/SpringMVC without frontend.

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
  * If it is before 11:00 we assume that he changed his mind. 
  * If it is after 11:00 then it is too late, vote can't be changed
    
Each restaurant provides a new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

Documentation
-----------------------------
### API

For all requests authorization is required. Basic Authorization (email and password).

#### User
* Profile 
  * Registration: Post /rest/profile/register 
  * User profile: Get /rest/profile 
  * Update profile: Put /rest/profile 
  * Delete profile: Delete /rest/profile
* Restaurants 
  * List of restaurants: Get /rest/profile/restaurants 
  * Restaurant information: Get /rest/profile/restaurants/{restaurantId} 
  * Vote for the restaurant: Post /rest/profile/restaurants/{restaurantId}
* Dishes 
  * List of restaurant dishes: Get /rest/profile/restaurants/{restaurantId}/dishes 
  * Dish information: Get /rest/profile/restaurants/{restaurantId}/dishes/{dishId}
  
#### Admin
* Profiles
  * Get user profile: Get /rest/admin/users/{userId}
  * List of users: Get /rest/admin/users
  * Create profile: Post /rest/admin/users
  * Delete user profile: Delete /rest/admin/users/{userId}
  * Update user profile: Put /rest/admin/users/{userId}
  * Get user by email: /rest/admin/users/by?email={email}
* Restaurants
  * List of restaurants: Get /rest/admin/restaurants
  * Restaurant information: Get /rest/admin/restaurants/{restaurantId}
  * Get all of today's restaurant votes: Get /rest/admin/restaurants/{restaurantId}/votes
  * Create: Post /rest/admin/restaurants
  * Update: Put /rest/admin/restaurants
  * Delete: Delete /rest/admin/restaurants/{restaurantId}
* Dishes
  * List of restaurant dishes: Get /rest/admin/restaurants/{restaurantId}/dishes
  * Dish information: Get /rest/admin/restaurants/{restaurantId}/dishes/{dishId}
  * Create dish: Post /rest/admin/restaurants/{restaurantId}/dishes
  * Update dish: Put /rest/admin/restaurants/{restaurantId}/dishes/{dishId}
  * Delete dish: Delete /rest/admin/restaurants/{restaurantId}/dishes/{dishId}
  
### Curl samples (application deployed at application context `/graduation`).

#### User
* Registration `curl -s -X POST 'http://localhost:8080/graduation/rest/profile/register' -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' --data-raw '{
  "name": "newUser",
  "email": "newUser@yandex.ru",
  "password": "password123"
  }'`
  

* Update profile `curl -s -X PUT 'http://localhost:8080/graduation/rest/profile' -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' --data-raw '{
  "name": "User",
  "email": "user@yandex.ru",
  "password": "password111"
  }'`
  

* List of restaurants `curl -s -X GET 'http://localhost:8080/graduation/rest/profile/restaurants' -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' --data-raw ''`


* Vote for the restaurant `curl -s -X POST 'http://localhost:8080/graduation/rest/profile/restaurants/100003' -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`


* List of restaurant dishes `curl -s -X GET 'http://localhost:8080/graduation/rest/profile/restaurants/100002/dishes' -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`


* Dish information `curl -s -X GET 'http://localhost:8080/graduation/rest/profile/restaurants/100002/dishes/100004' -H 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ='`



#### Admin
* List of users
`curl -s -X GET 'http://localhost:8080/graduation/rest/admin/users' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`
  

* Create profile
  `curl -s -X POST 'http://localhost:8080/graduation/rest/admin/users' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --data-raw '{
  "id": null,
  "name": "newUser",
  "email": "newUser@yandex.ru",
  "password": "password123",
  "roles": [
  "USER"
  ]
  }'`
  

* Update user profile `curl -s -X PUT 'http://localhost:8080/graduation/rest/admin/users/100000' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --data-raw '{
  "id": 100000,
  "name": "User123",
  "email": "user@yandex.ru",
  "password": "password",
  "roles": [
  "USER"
  ]
  }'`
  
  
* Get user by email `curl -s -X GET 'http://localhost:8080/graduation/rest/admin/users/by?email=user@yandex.ru' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --data-raw ''`


* Create restaurant `curl -s -X POST 'http://localhost:8080/graduation/rest/admin/restaurants' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --data-raw '{
  "id": null,
  "name": "Restaurant3",
  "dishes": null
  }'`
  

* Delete restaurant `curl -s -X DELETE 'http://localhost:8080/graduation/rest/admin/restaurants/100002' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`


* Update restaurant `curl -s -X PUT 'http://localhost:8080/graduation/rest/admin/restaurants/100003' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --data-raw '{"id": 100003,
  "name": "Restaurant2",
  "dishes": [
  {
  "id": 100007,
  "name": "Fruits",
  "price": 99
  },
  {
  "id": 100008,
  "name": "Vegetables",
  "price": 600
  }
  ]
  }'`
  

* Get all of today's restaurant votes `curl -s -X GET 'http://localhost:8080/graduation/rest/admin/restaurants/100003/votes' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`


* Create dish `curl -s -X POST 'http://localhost:8080/graduation/rest/admin/restaurants/100002/dishes' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --data-raw '{
  "id": null,
  "name": "Sausages",
  "price": 600
  }'`
  

* Update dish `curl -s -X PUT 'http://localhost:8080/graduation/rest/admin/restaurants/100002/dishes/100004' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' --data-raw '{
  "id": 100004,
  "name": "Soup",
  "price": 502,
  "date": "2020-12-30"
  }'`
  

* Delete dish `curl -s -X DELETE 'http://localhost:8080/graduation/rest/admin/restaurants/100002/dishes/100004' -H 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'`

Technologies and tools used
---------------------------
* Maven
* Spring (Data JPA, MVC, Security, Test)
* JUnit
* HSQLDB
* Hibernate
* SLF4J, Logback