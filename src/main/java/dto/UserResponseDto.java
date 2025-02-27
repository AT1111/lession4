package dto;

public record UserResponseDto(Long id, String email, String phoneNumber, String password) {
    @Override
    public Long id() {
        return id;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String phoneNumber() {
        return phoneNumber;
    }

    @Override
    public String password() {
        return password;
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
