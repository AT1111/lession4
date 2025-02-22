package service.impl;

import dto.UserRegistrationDto;
import dto.UserResponseDto;
import entity.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserRepository USER_REPOSITORY = new UserRepositoryImpl();

    @Override
    public UserResponseDto registerUser(UserRegistrationDto dto) {
        User user = USER_REPOSITORY.save(dto);
        return user.toUserResponseDto();
    }

    @Override
    public Optional<UserResponseDto> getUserById(Long id) {
        return USER_REPOSITORY.findById(id);
    }

    @Override
    public List<UserResponseDto> getUsers() {
        return USER_REPOSITORY.findAll();
    }

    @Override
    public void deleteUsers() {
        USER_REPOSITORY.findAll().forEach(user -> USER_REPOSITORY.deleteById(user.id()));
        System.out.println("Table user is already empty!");
    }

    @Override
    public void deleteUserById(Long id) {
        USER_REPOSITORY.deleteById(id);
        System.out.println("User record with id=" + id + " already deleted!");
    }
}
