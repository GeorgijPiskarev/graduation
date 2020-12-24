package ru.graduation.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.graduation.model.User;
import ru.graduation.repository.UserRepository;

public abstract class AbstractUserController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository repository;

    public User get(int id) {
        log.info("get {}", id);
        return repository.get(id);
    }

    public User save(User user) {
        log.info("create {}", user);
        return repository.save(user);
    }
}
