package ru.graduation.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.graduation.model.User;
import ru.graduation.repository.UserRepository;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private final CrudUserRepository crudRepository;

    public DataJpaUserRepository(CrudUserRepository crudUserRepository) {
        this.crudRepository = crudUserRepository;
    }

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public User get(int id) {
        return crudRepository.findById(id).orElse(null);
    }
}
