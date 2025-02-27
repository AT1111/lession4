package repository.impl;

import dto.UserResponseDto;
import entity.User;
import repository.UserRepository;
import util.ConnectionUtil;

import javax.persistence.EntityNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String PHONENUMBER = "phonenumber";
    private static final String PASSWORD = "password";

    @Override
    public User save(User user) {
        String sqlRequest = "INSERT INTO user (email, phoneNumber, password) VALUES (?, ?, ?)";

        try (
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest,
                Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getPassword());

            int affectedRows = preparedStatement.executeUpdate();
            checkAffectedRowsNumber(affectedRows, 1);
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't create instance of user:"
                + user
                + " and add it to DB", e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        String sqlRequest = "SELECT id, email, phoneNumber, password FROM user";
        List<User> users = new ArrayList<>();

        try (
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(toUser(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't to retrieve user data", e);
        }
        return users;
    }

    @Override
    public Optional<UserResponseDto> findById(Long id) {
        String sqlRequest = "SELECT id, email, phoneNumber, password FROM user WHERE id = ?";
        Optional<UserResponseDto> user = Optional.empty();

        try (
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = Optional.of(toUserResponseDto(resultSet));
            }
        } catch (SQLException e) {
            throw new EntityNotFoundException("Can't to retrieve user by id. ID=" + id);
        }
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        String sqlRequest = "DELETE FROM user WHERE id = ?";

        try (
            Connection connection = ConnectionUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
            preparedStatement.setLong(1, id);

            int affectedRows = preparedStatement.executeUpdate();
            checkAffectedRowsNumber(affectedRows, 1);
        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete user record:" + "id = " + id, e);
        }
        return true;
    }

    @Override
    public void deleteAll() {
        findAll().forEach(user -> deleteById(user.getId()));
    }

    private void checkAffectedRowsNumber(int recordCount, int expectedCount) {
        if (recordCount != expectedCount) {
            throw new RuntimeException("Excepted to change at least " + expectedCount + " row. "
                + "But changed " + recordCount + " rows.");
        }
    }

    private User toUser(ResultSet requestResult) {
        try {
            Long id = requestResult.getLong(ID);
            String email = requestResult.getString(EMAIL);
            String phoneNumber = requestResult.getString(PHONENUMBER);
            String password = requestResult.getString(PASSWORD);
            return new User(id, email, phoneNumber, password);
        } catch (SQLException e) {
            throw new RuntimeException("Can't parse user data from resultSet", e);
        }
    }

    private UserResponseDto toUserResponseDto(ResultSet requestResult) {
        try {
            Long id = requestResult.getLong(ID);
            String email = requestResult.getString(EMAIL);
            String phoneNumber = requestResult.getString(PHONENUMBER);
            String password = requestResult.getString(PASSWORD);
            return new UserResponseDto(id, email, phoneNumber, password);
        } catch (SQLException e) {
            throw new RuntimeException("Can't parse user data from resultSet", e);
        }
    }
}
