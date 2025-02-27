package service;

import dto.UserRegistrationDto;
import dto.UserResponseDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationDto userRegistrationDto);

    Optional<UserResponseDto> getUserById(Long id);

    List<UserResponseDto> getUsers();

    void deleteUsers();

    void deleteUserById(Long id);
}
