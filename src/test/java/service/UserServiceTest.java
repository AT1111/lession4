package service;

import dto.UserRegistrationDto;
import dto.UserResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.impl.UserServiceImpl;

class UserServiceTest {
    static UserRepository userRepository = new UserRepositoryImpl();
    static UserService userService = new UserServiceImpl(userRepository);

    @BeforeAll
    static void setUp() {
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
        UserRegistrationDto userDto = new UserRegistrationDto("a2@gmail.com", "+23 121 312 324","12345", "12345");
        UserResponseDto userResponseDto = userService.registerUser(userDto);
        Assertions.assertNotNull(userService.getUserById(userResponseDto.id()).orElse(null));
    }

    @Test
    void deleteUserByIdTest() {
        UserRegistrationDto userDto = new UserRegistrationDto("a3@gmail.com", "+23 121 312 324","12345", "12345");
        UserResponseDto userResponseDto = userService.registerUser(userDto);
        Assertions.assertDoesNotThrow(() -> userService.deleteUserById(userResponseDto.id()));
        Assertions.assertEquals(0, userService.getUsers().size());
    }
}