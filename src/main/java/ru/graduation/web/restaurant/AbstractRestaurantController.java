package ru.graduation.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;
import ru.graduation.repository.UserRepository;
import ru.graduation.repository.VoteRepository;
import ru.graduation.web.SecurityUtil;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkIdConsistent;
import static ru.graduation.util.ValidationUtil.checkNew;

public class AbstractRestaurantController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    public Restaurant create(Restaurant restaurant) {
        int userId = SecurityUtil.authUserId();
        checkNew(restaurant);
        log.info("create {} by user{}", restaurant, userId);
        return restaurantRepository.save(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        int userId = SecurityUtil.authUserId();
        checkIdConsistent(restaurant, id);
        log.info("update {} by user {}", restaurant, userId);
        restaurantRepository.save(restaurant);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete restaurant {} by user {}", id, userId);
        restaurantRepository.delete(id);
    }

    public Restaurant get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get restaurant {} for user {}", id, userId);
        return restaurantRepository.get(id);
    }

    public List<Restaurant> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return restaurantRepository.getAll();
    }


    public void vote(int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("vote for restaurant {} by user {}", restaurantId, userId);
        voteRepository.vote(userId, restaurantId);
    }
}
