package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id = ?1 AND v.day = ?2")
    List<Vote> getByRestaurantIdAndDate(int restaurantId, LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.user.id = ?1 AND v.day = ?2")
    Vote getByUserIdAndDate(int userId, LocalDate date);
}
