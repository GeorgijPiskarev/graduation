package ru.graduation.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;

@Repository
public class DataJpaDishRepository implements DishRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudDishRepository crudDishRepository;

    public DataJpaDishRepository(CrudRestaurantRepository crudRestaurantRepository, CrudDishRepository crudDishRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudDishRepository = crudDishRepository;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudDishRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudDishRepository.save(dish);
    }

    @Transactional
    public void update(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        save(dish, restaurantId);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        return crudDishRepository.delete(id, restaurantId) != 0;
    }
}
