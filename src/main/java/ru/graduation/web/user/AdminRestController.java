package ru.graduation.web.user;

import org.springframework.web.bind.annotation.RestController;
import ru.graduation.model.User;

@RestController
public class AdminRestController extends AbstractUserController {

    @Override
    public User get(int id) {
        return super.get(id);
    }

    @Override
    public User save(User user) {
        return super.save(user);
    }
}
