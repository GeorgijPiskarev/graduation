package ru.graduation.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.graduation.util.ValidationUtil.*;

public abstract class AbstractDishController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRepository dishRepository;

    public Dish get(int id, int restaurantId) {
        log.info("get dish {} from restaurant {}", id, restaurantId);
        return checkNotFoundWithId(dishRepository.get(id, restaurantId), id);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete dish {} from restaurant {}", id, restaurantId);
        checkNotFoundWithId(dishRepository.delete(id, restaurantId), id);
    }

    public void update(Dish dish, int id, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        checkIdConsistent(dish, id);
        log.info("update dish {} for restaurant {}", id, restaurantId);
        dishRepository.save(dish, restaurantId);
    }

    public Dish create(Dish dish, int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        log.info("create dish for restaurant {}", restaurantId);
        return dishRepository.save(dish, restaurantId);
    }

    public List<Dish> getAll(int restaurantId) {
        log.info("getAll today's dishes in restaurant {}", restaurantId);
        return dishRepository.getAll(restaurantId, LocalDate.now());
    }
}
