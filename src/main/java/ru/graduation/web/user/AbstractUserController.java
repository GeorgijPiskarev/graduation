package ru.graduation.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.graduation.model.User;
import ru.graduation.repository.UserRepository;

import java.util.List;

import static ru.graduation.util.ValidationUtil.*;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    public User create(User user) {
        log.info("create {}", user);
        Assert.notNull(user, "user must not be null");
        checkNew(user);
        return repository.save(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    public void update(User user, int id) {
        log.info("update {} with id={}", user, id);
        Assert.notNull(user, "user must not be null");
        assureIdConsistent(user, id);
        repository.save(user);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }
}
