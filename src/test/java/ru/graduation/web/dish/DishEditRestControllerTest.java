package ru.graduation.web.dish;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.graduation.UserTestData;
import ru.graduation.model.Dish;
import ru.graduation.repository.DishRepository;
import ru.graduation.web.AbstractControllerTest;
import ru.graduation.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.graduation.DishTestData.*;
import static ru.graduation.RestaurantTestData.RESTAURANT1_ID;
import static ru.graduation.TestUtil.readFromJson;
import static ru.graduation.TestUtil.userHttpBasic;

public class DishEditRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = DishEditRestController.REST_URL.replace("{restaurantId}", String.valueOf(RESTAURANT1_ID)) + "/";

    @Autowired
    private DishRepository dishRepository;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(scarlettGreenDishes));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH1_ID)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + DISH1_ID)
                .with(userHttpBasic(UserTestData.admin)))
                .andExpect(status().isNoContent());
    }

    @Test
    void update() throws Exception {
        Dish updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + DISH1_ID)
                .with(userHttpBasic(UserTestData.admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(dishRepository.get(DISH1_ID, RESTAURANT1_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Dish newDish = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(UserTestData.admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));

        Dish created = readFromJson(action, Dish.class);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishRepository.get(newId, RESTAURANT1_ID), newDish);
    }
}
