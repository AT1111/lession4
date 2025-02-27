package dto;

import entity.User;
import lombok.Getter;
import lombok.ToString;

import java.util.Optional;

@Getter
@ToString
public class UserRegistrationDto {
    private final String email;
    private final Optional<String> phoneNumber;
    private final String password;
    private final String repeatPassword;

    public UserRegistrationDto(String email, String phoneNumber, String password, String repeatPassword) {
        this.email = email;
        this.phoneNumber = Optional.of(phoneNumber);
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public Optional<String> getPhoneNumber() {
        Optional<String> result = phoneNumber;
        if (phoneNumber.isPresent()) {
            result = Optional.of(phoneNumber.orElse("").replaceAll(" ", ""));
        }
        return result;
    }

    public UserRegistrationDto(String email, String password, String repeatPassword) {
        this.email = email;
        this.phoneNumber = Optional.empty();
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getPhoneNumberString() {
        return phoneNumber.orElse("").replaceAll(" ", "");
    }

    public User toUser() {
        return new User(email, getPhoneNumberString(), password);
    }
}
