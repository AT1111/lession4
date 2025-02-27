package dto;

import entity.User;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserRegistrationDto {
    private final String email;
    private final String phoneNumber;
    private final String password;
    private final String repeatPassword;

    public UserRegistrationDto(String email, String phoneNumber, String password, String repeatPassword) {
        this.email = email;
        this.phoneNumber = phoneNumber.replaceAll(" ", "");
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public UserRegistrationDto(String email, String password, String repeatPassword) {
        this.email = email;
        this.phoneNumber = "";
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public User toUser() {
        return new User(email, getPhoneNumber(), password);
    }
}
