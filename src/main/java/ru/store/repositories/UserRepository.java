package ru.store.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.store.models.User;

@Repository
public interface UserRepository extends CrudRepository <User, Integer> {

    User findUserByLoginAndPassword(String login, String password);

    User findUserByLogin(String login);

}
