package ru.graduation.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;
import ru.graduation.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;

import static ru.graduation.util.ValidationUtil.*;

public class AbstractDishController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRepository dishRepository;

    public Dish get(int id, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("get dish {} from restaurant {} for user {}", id, restaurantId, userId);
        return checkNotFoundWithId(dishRepository.get(id, restaurantId), id);
    }

    public void delete(int id, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("delete dish {} from restaurant {} by user {}", id, restaurantId, userId);
        checkNotFoundWithId(dishRepository.delete(id, restaurantId), id);
    }

    public void update(Dish dish, int id, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        Assert.notNull(dish, "dish must not be null");
        checkIdConsistent(dish, id);
        log.info("update dish {} for restaurant {} by user {}", id, restaurantId, userId);
        dishRepository.save(dish, restaurantId);
    }

    public Dish create(Dish dish, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        Assert.notNull(dish, "dish must not be null");
        checkNew(dish);
        log.info("create dish for restaurant {} by user {}", restaurantId, userId);
        return dishRepository.save(dish, restaurantId);
    }

    public List<Dish> getAll(int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("getAll today's dishes by user {} in restaurant {}", userId, restaurantId);
        return dishRepository.getAll(restaurantId, LocalDate.now());
    }
}
