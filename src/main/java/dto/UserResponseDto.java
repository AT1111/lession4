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
}
