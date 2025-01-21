package hiber.service;

import hiber.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);
    Optional<User> findUserByCar(String model, int series);
    List<User> listUsers();
}
