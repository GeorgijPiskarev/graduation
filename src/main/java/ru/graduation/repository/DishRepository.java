package ru.graduation.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Repository
public class DishRepository {
    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudDishRepository crudDishRepository;

    public DishRepository(CrudRestaurantRepository crudRestaurantRepository, CrudDishRepository crudDishRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudDishRepository = crudDishRepository;
    }

    public List<Dish> getAll(int restaurantId, LocalDate date) {
        return crudDishRepository.getAll(restaurantId, date);
    }

    public Dish get(int id, int restaurantId) {
        return checkNotFoundWithId(crudDishRepository.get(id, restaurantId), id);
    }

    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    public boolean delete(int id, int restaurantId) {
        return checkNotFoundWithId(crudDishRepository.delete(id, restaurantId), id) != 0;
    }
}
