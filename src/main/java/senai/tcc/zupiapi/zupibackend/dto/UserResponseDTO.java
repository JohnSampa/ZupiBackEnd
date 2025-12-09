package senai.tcc.zupiapi.zupibackend.dto;

import senai.tcc.zupiapi.zupibackend.model.User;

public record UserResponseDTO(
        Long id,
        String name,
        String email
) {
    public UserResponseDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
