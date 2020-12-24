package ru.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.Restaurant;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant,Integer>{
    @Transactional
    @Modifying
    @Query(name = Restaurant.DELETE)
    int delete(@Param("id") int id);

    @Query(name = Restaurant.ALL_SORTED)
    List<Restaurant> getAll();

    @Query("SELECT r FROM Restaurant r WHERE r.id=:id")
    Restaurant get(@Param("id") int id);
}
