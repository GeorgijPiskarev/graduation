package ru.graduation.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;
import ru.graduation.web.SecurityUtil;

import java.net.URI;

import static ru.graduation.util.ValidationUtil.checkIdConsistent;
import static ru.graduation.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = DishRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishRestController {
    private static final Logger log = LoggerFactory.getLogger(DishRestController.class);

    static final String REST_URL = "rest/admin/restaurants/{restaurantId}/dishes";

    @Autowired
    private DishRepository dishRepository;

    @GetMapping("/{id}")
    public Dish getDish(@PathVariable int id, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("get dish {} from restaurant {} for user {}", id, restaurantId, userId);
        return dishRepository.get(id, restaurantId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteDish(@PathVariable int id, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("delete dish {} from restaurant {} by user {}", id, restaurantId, userId);
        dishRepository.delete(id, restaurantId);
    }

    @PutMapping(value = "/dishes/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDish(@RequestBody Dish dish, @PathVariable int id, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        checkIdConsistent(dish, id);
        log.info("update dish {} for restaurant {} by user {}", id, restaurantId, userId);
        dishRepository.update(dish, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createDishWithLocation(@RequestBody Dish dish, @PathVariable int restaurantId) {
        int userId = SecurityUtil.authUserId();
        checkNew(dish);
        log.info("create dish for restaurant {} by user {}", restaurantId, userId);
        Dish created = dishRepository.save(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
