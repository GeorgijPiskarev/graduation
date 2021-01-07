package ru.graduation;

import ru.graduation.model.Dish;

import java.util.List;

import static ru.graduation.RestaurantTestData.restaurant1;
import static ru.graduation.RestaurantTestData.restaurant2;
import static ru.graduation.model.AbstractBaseEntity.START_SEQ;

public class DishTestData {
    public static final TestMatcher<Dish> DISH_MATCHER = TestMatcher.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final int DISH1_ID = START_SEQ + 4;

    public static final Dish dish1 = new Dish(DISH1_ID, "Soup", 500, restaurant1);
    public static final Dish dish2 = new Dish(DISH1_ID + 1, "Pasta", 1200, restaurant1);
    public static final Dish dish3 = new Dish(DISH1_ID + 2, "Meatballs", 520, restaurant1);
    public static final Dish dish4 = new Dish(DISH1_ID + 3, "Fruits", 100, restaurant2);
    public static final Dish dish5 = new Dish(DISH1_ID + 4, "Vegetables", 600, restaurant2);

    public static final List<Dish> dishes = List.of(dish5, dish4, dish3, dish2, dish1);
    public static final List<Dish> scarlettGreenDishes = List.of(dish1, dish2, dish3);
    public static final List<Dish> firebrandPizza = List.of(dish4, dish5);

    public static Dish getNew() {
        return new Dish(null, "newDish", 200, restaurant1);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, "updatedDish", 1000, restaurant1);
    }
}
