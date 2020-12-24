package ru.graduation.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.graduation.model.Restaurant;
import ru.graduation.repository.RestaurantRepository;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository implements RestaurantRepository {

    private final CrudRestaurantRepository crudRestaurantRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaRestaurantRepository(CrudRestaurantRepository crudRestaurantRepository, CrudUserRepository crudUserRepository) {
        this.crudRestaurantRepository = crudRestaurantRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant, int userId) {
        if (restaurant.isNew() || get(restaurant.id()) != null) {
            return crudRestaurantRepository.save(restaurant);
        }
        return restaurant;
    }

    @Override
    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRestaurantRepository.get(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRestaurantRepository.getAll();
    }
}
