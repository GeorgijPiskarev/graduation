package ru.graduation.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.graduation.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = RestaurantEditController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController extends AbstractRestaurantController{

    static final String REST_URL = "rest/profile/restaurants";

    @Override
    public Restaurant get(int id) {
        return super.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return super.getAll();
    }
}
