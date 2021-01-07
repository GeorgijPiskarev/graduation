package ru.graduation;

import ru.graduation.model.Restaurant;

import java.util.List;

import static ru.graduation.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Restaurant.class, "dishes");

    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int RESTAURANT2_ID = START_SEQ + 3;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Scarlett Green", DishTestData.scarlettGreenDishes);
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Firebrand Pizza", DishTestData.firebrandPizza);

    public static final List<Restaurant> restaurants = List.of(restaurant2, restaurant1);

    public static Restaurant getNew() {
        return new Restaurant(null, "newRestaurant");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT1_ID, "updatedRestaurant");
    }
}
