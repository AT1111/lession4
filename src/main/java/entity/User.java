package entity;

import dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String phoneNumber;
    private String password;

    public UserResponseDto toUserResponseDto() {
        return new UserResponseDto(id, email, phoneNumber, password);
    }
}
