package ru.graduation.web.json;

import org.junit.jupiter.api.Test;
import ru.graduation.DishTestData;
import ru.graduation.model.Dish;

import java.util.List;

import static ru.graduation.DishTestData.*;

public class JsonUtilTest {
    @Test
    void readWriteValue() throws Exception {
        String json = JsonUtil.writeValue(dish1);
        System.out.println(json);
        Dish dish = JsonUtil.readValue(json, Dish.class);
        DISH_MATCHER.assertMatch(dish, DishTestData.dish1);
    }

    @Test
    void readWriteValues() throws Exception {
        String json = JsonUtil.writeValue(dishes);
        System.out.println(json);
        List<Dish> dishes = JsonUtil.readValues(json, Dish.class);
        DISH_MATCHER.assertMatch(dishes, DishTestData.dishes);
    }
}
