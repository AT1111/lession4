package repository;

import entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    List<User> findAll();

    Optional<User> findById(Long id);

    boolean deleteById(Long id);

    void deleteAll();
}
