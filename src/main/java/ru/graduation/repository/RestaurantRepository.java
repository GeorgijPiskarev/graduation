package ru.graduation.repository;

import ru.graduation.model.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    // null if it is not an admin
    Restaurant save(Restaurant restaurant, int userId);

    // false if it is not an admin
    boolean delete(int id);

    // null if the restaurant does not exist
    Restaurant get(int id);

    List<Restaurant> getAll();
}
