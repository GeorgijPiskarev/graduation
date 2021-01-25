package ru.graduation.repository;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.graduation.AuthorizedUser;
import ru.graduation.model.User;

import java.util.List;

@Repository("userRepository")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserRepository implements UserDetailsService {

    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudUserRepository;

    public UserRepository(CrudUserRepository crudUserRepository) {
        this.crudUserRepository = crudUserRepository;
    }

    public User save(User user) {
        return crudUserRepository.save(user);
    }

    public boolean delete(int id) {
        return crudUserRepository.delete(id) != 0;
    }

    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = crudUserRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
