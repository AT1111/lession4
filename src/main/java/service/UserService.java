package service;

import dto.UserRegistrationDto;
import dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto registerUser(UserRegistrationDto userRegistrationDto);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getUsers();

    void deleteUsers();

    void deleteUserById(Long id);
}
