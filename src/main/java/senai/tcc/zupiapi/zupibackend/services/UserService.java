package senai.tcc.zupiapi.zupibackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import senai.tcc.zupiapi.zupibackend.dto.LoginDTO;
import senai.tcc.zupiapi.zupibackend.dto.UserDTO;
import senai.tcc.zupiapi.zupibackend.dto.UserResponseDTO;
import senai.tcc.zupiapi.zupibackend.model.User;
import senai.tcc.zupiapi.zupibackend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<UserDTO> findAll() {

        List<User> list = userRepository.findAll();

        return list.stream()
                .map(UserDTO::new)
                .toList();
    }

    public UserResponseDTO findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return new UserResponseDTO(user.orElse(null));
    }

    public UserResponseDTO findByEmail(String email) {
        return new UserResponseDTO(userRepository.findByEmail(email));
    }

    public UserResponseDTO save(UserDTO user) {
        User userEntity = new User();
        String encoderPassword = passwordEncoder.encode(user.password());

        userEntity.setEmail(user.email());
        userEntity.setPassword(encoderPassword);
        userEntity.setName(user.name());

        return new UserResponseDTO(userRepository.save(userEntity));
    }

    public Boolean validationPassword(LoginDTO user) {
       User usuario = userRepository.findByEmail(user.email());

       return passwordEncoder.matches(user.password(), usuario.getPassword());
    }
}
