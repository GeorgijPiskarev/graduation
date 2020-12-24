package ru.graduation.util;

import ru.graduation.model.Restaurant;
import ru.graduation.model.User;

import java.time.LocalTime;

public class RestaurantUtil {
    public static final LocalTime END = LocalTime.of(11, 0);
    public static final LocalTime START = LocalTime.of(0, 0);

    private RestaurantUtil() {
    }

    public static void vote(Restaurant restaurant, User user) {
        LocalTime time = LocalTime.now();
        if (!user.isVoted()) {
            restaurant.setVotes(restaurant.getVotes() + 1);
            user.setVoted(true);
        } else {
            if (!time.isAfter(END) && !time.isBefore(START)) {

            }
        }
    }
}
