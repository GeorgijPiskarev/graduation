package ru.graduation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Dish;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=?1 AND d.restaurant.id=?2")
    int delete(int id, int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=?1 AND d.day=?2")
    List<Dish> getAll(int restaurantId, LocalDate date);

    @Query("SELECT d FROM Dish d WHERE d.id=?1 AND d.restaurant.id=?2")
    Dish get(int id, int restaurantId);
}
