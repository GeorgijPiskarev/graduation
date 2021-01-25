package ru.graduation.repository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.graduation.model.Restaurant;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class RestaurantRepository {
    private static final Sort SORT_NAME = Sort.by(Sort.Direction.ASC, "name");

    private final CrudRestaurantRepository crudRestaurantRepository;

    public RestaurantRepository(CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    public Restaurant get(int id) {
        return crudRestaurantRepository.get(id);
    }

    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_NAME);
    }
}
