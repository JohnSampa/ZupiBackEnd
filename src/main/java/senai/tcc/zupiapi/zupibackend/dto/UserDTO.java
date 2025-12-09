package senai.tcc.zupiapi.zupibackend.dto;

import senai.tcc.zupiapi.zupibackend.model.User;

public record UserDTO(
        String name,
        String email,
        String password
) {
    public UserDTO(User user) {
       this(
               user.getName(),
               user.getEmail(),
               user.getPassword());
    }
}
