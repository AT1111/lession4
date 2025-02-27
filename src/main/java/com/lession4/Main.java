package com.lession4;

import dto.UserRegistrationDto;
import dto.UserResponseDto;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);

        userService.deleteUsers();

        userService.registerUser(new UserRegistrationDto("a1@a1.aa", "+1 232 132 11 56", "321", "321"));
        userService.registerUser(new UserRegistrationDto("a@a.gmail.com", "321", "321"));

        List<UserResponseDto> users = userService.getUsers();
        for (UserResponseDto user : users) {
            System.out.println(userService.getUserById(user.id()).orElse(null));
        }
    }
}
