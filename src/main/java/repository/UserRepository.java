package repository;

import dto.UserRegistrationDto;
import dto.UserResponseDto;
import entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(UserRegistrationDto dto);

    List<UserResponseDto> findAll();

    Optional<UserResponseDto> findById(Long id);

    boolean deleteById(Long id);
}
