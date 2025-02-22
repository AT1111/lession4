package service;

import dto.UserRegistrationDto;
import dto.UserResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.impl.UserServiceImpl;

class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @BeforeEach
    void setUp() {
        userService.deleteUsers();
    }

    @Test
    void registerUserAndGetUsersWhenGoodRegistration() {
        UserRegistrationDto userDto = new UserRegistrationDto("a1@gmail.com", "+23 121 312 324","12345", "12345");
        Assertions.assertDoesNotThrow(() -> userService.registerUser(userDto));
        Assertions.assertEquals(1, userService.getUsers().size());
    }

    @Test
    void getUserByIdTest() {
        UserRegistrationDto userDto = new UserRegistrationDto("a1@gmail.com", "+23 121 312 324","12345", "12345");
        UserResponseDto userResponseDto = userService.registerUser(userDto);
        Assertions.assertNotNull(userService.getUserById(userResponseDto.id()).orElse(null));
    }

    @Test
    void deleteUserByIdTest() {
        UserRegistrationDto userDto = new UserRegistrationDto("a1@gmail.com", "+23 121 312 324","12345", "12345");
        UserResponseDto userResponseDto = userService.registerUser(userDto);
        Assertions.assertDoesNotThrow(() -> userService.deleteUserById(userResponseDto.id()));
        Assertions.assertEquals(0, userService.getUsers().size());
    }
}