package ru.graduation.repository;

import ru.graduation.model.Dish;

public interface DishRepository {

    Dish get(int id, int restaurantId);

    Dish save(Dish dish, int restaurantId);

    void update(Dish dish, int restaurantId);

    boolean delete(int id, int restaurantId);
}
