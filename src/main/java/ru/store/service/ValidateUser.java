package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.store.models.Role;
import ru.store.models.User;
import ru.store.repositories.UserRepository;
import java.util.List;
import java.util.Objects;

@Service
public class ValidateUser implements Validation <User> {

    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    public ValidateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        if (checkModel(user)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(new Role(""));
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public boolean replace(User user) {
        boolean rs = checkModel(user);
        user = userRepository.findById(user.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (rs) {
            userRepository.save(user);
        }
        return rs;
    }

    @Override
    public boolean delete(User user) {
        boolean rs = checkModel(user);
        if (rs) {
            userRepository.delete(user);
        }
        return rs;
    }

    @Override
    public List <User> findAll() {
        return (List <User>) userRepository.findAll();
    }

    @Override
    public User find(User user) {
        return userRepository.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
    }


    private boolean checkModel(User user) {
        return Objects.nonNull(user.getLogin()) && Objects.nonNull(user.getPassword());
    }
}
