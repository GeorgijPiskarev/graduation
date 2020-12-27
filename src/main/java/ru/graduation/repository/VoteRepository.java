package ru.graduation.repository;

import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    void vote(int userId, int restaurantId);

    Vote getByUser(int userId, LocalDate date);

    List<Vote> getByUser(int userId);

    List<Vote> getByRestaurant(int restaurantId, LocalDate date);
}
