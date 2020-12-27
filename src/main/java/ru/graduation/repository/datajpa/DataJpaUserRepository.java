package ru.graduation.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ru.graduation.model.User;
import ru.graduation.repository.UserRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.checkNotFound;
import static ru.graduation.util.ValidationUtil.checkNotFoundWithId;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudUserRepository;

    public DataJpaUserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return crudUserRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return checkNotFoundWithId(crudUserRepository.delete(id), id) != 0;
    }

    @Override
    public User get(int id) {
        return checkNotFoundWithId(crudUserRepository.findById(id).orElse(null), id);
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(crudUserRepository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }
}
