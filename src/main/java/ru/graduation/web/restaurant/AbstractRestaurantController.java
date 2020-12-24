package ru.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.web.SecurityUtil;

import java.util.List;

public class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository repository;

    public Restaurant create(Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        log.info("create {} by admin{}", restaurant, userId);
        return repository.save(restaurant, SecurityUtil.authUserId());
    }

    public void update(Restaurant restaurant, int id) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} by admin {}", restaurant, userId);
        repository.save(restaurant, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {} by admin {}", id, userId);
        repository.delete(id);
    }

    public Restaurant get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get restaurant {} for user {}", id, userId);
        return repository.get(id);
    }

    public List<Restaurant> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return repository.getAll();
    }
}
