package ru.graduation.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.graduation.model.User;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User,Integer> {
}
