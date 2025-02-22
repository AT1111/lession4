package service;

import dto.UserRegistrationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class UserValidatorTest {
    @Test
    void validateWhenEmailIsBad() {
        UserRegistrationDto userDto = new UserRegistrationDto("1a1@gmail.com", "12345", "12345");
        Assertions.assertThrows(RuntimeException.class, () -> UserValidator.validate(userDto),"Exception expected.");
    }

    @Test
    void validateWhenPhoneNumberIsBad() {
        UserRegistrationDto userDto1 = new UserRegistrationDto("1a1@gmail.com", "+21312324", "12345", "12345");
        Assertions.assertThrows(RuntimeException.class, () -> UserValidator.validate(userDto1),"Exception expected.");
        UserRegistrationDto userDto2 = new UserRegistrationDto("1a1@gmail.com", "123 121 312 324", "12345", "12345");
        Assertions.assertThrows(RuntimeException.class, () -> UserValidator.validate(userDto2),"Exception expected.");
    }

    @Test
    void validateWhenPasswordIsBad() {
        UserRegistrationDto userDto1 = new UserRegistrationDto("1a1@gmail.com", "+23121312324", "12345", "13425");
        Assertions.assertThrows(RuntimeException.class, () -> UserValidator.validate(userDto1),"Exception expected.");
    }

    @Test
    void validateWhenAllIsGood() {
        UserRegistrationDto userDto = new UserRegistrationDto("a1@gmail.com", "+23 121 312 324","12345", "12345");
        Assertions.assertDoesNotThrow(() -> UserValidator.validate(userDto),"Exception not expected.");
    }
}