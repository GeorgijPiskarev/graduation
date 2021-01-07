package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query(name = Vote.GET_BY_RESTAURANT_AND_DATE)
    List<Vote> getByRestaurantIdAndDate(int restaurantId, LocalDate date);

    @Query(name = Vote.GET_BY_USER_AND_DATE)
    Vote getByUserIdAndDate(int userId, LocalDate date);
}
