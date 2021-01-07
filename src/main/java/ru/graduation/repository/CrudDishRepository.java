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
    @Query(name = Dish.DELETE)
    int delete(int id, int restaurantId);

    @Query(name = Dish.GET_ALL)
    List<Dish> getAll(int restaurantId, LocalDate date);

    @Query(name = Dish.GET)
    Dish get(int id, int restaurantId);
}
