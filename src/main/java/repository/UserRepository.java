package repository;

import dto.UserRegistrationDto;
import dto.UserResponseDto;
import entity.User;

import java.util.List;

public interface UserRepository {
    User save(UserRegistrationDto dto);

    List<UserResponseDto> findAll();

    UserResponseDto findById(Long id);

    boolean deleteById(Long id);
}
