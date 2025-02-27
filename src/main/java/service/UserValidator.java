package service;

import dto.UserRegistrationDto;

public class UserValidator {
    public static void validate(UserRegistrationDto userDto) {
        checkEmail(userDto.getEmail());
        checkPhoneNumber(userDto.getPhoneNumber());
        if (userDto.getPassword().isBlank()) {
            throw new RuntimeException("Password not set.");
        }
        if (!userDto.getPassword().equals(userDto.getRepeatPassword())) {
            throw new RuntimeException("Password not confirmed.");
        }
    }

    private static void checkEmail(String email) {
        if (email.isBlank()) {
            throw new RuntimeException("Email not set.");
        }
        if (!email.matches("^[A-Za-z]\\w*@[A-Za-z]\\w*.[A-Za-z]\\S*$")) {
            throw new RuntimeException("Bad email: " + email);
        }
    }

    private static void checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return;
        }
        if (!phoneNumber.matches("^\\+[1-9][0-9]{10}$")) {
            throw new RuntimeException("Bad phone number: " + phoneNumber);
        }
    }
}
