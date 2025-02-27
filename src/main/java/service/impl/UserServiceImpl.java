package service.impl;

import dto.UserRegistrationDto;
import dto.UserResponseDto;
import entity.User;
import repository.UserRepository;
import service.UserService;
import service.UserValidator;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto registerUser(UserRegistrationDto dto) {
        UserValidator.validate(dto);

        User user = userRepository.save(dto.toUser());
        return user.toUserResponseDto();
    }

    @Override
    public Optional<UserResponseDto> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream().map(User::toUserResponseDto).toList();
    }

    @Override
    public void deleteUsers() {
        userRepository.deleteAll();
        System.out.println("Table user is already empty!");
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        System.out.println("User record with id=" + id + " already deleted!");
    }
}
