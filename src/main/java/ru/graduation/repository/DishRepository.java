package ru.graduation.repository;

import ru.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    List<Dish> getAll(int restaurantId, LocalDate date);

    Dish get(int id, int restaurantId);

    Dish save(Dish dish, int restaurantId);

    boolean delete(int id, int restaurantId);
}
