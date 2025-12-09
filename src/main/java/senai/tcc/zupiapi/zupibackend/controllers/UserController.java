package senai.tcc.zupiapi.zupibackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import senai.tcc.zupiapi.zupibackend.dto.LoginDTO;
import senai.tcc.zupiapi.zupibackend.dto.UserDTO;
import senai.tcc.zupiapi.zupibackend.dto.UserResponseDTO;
import senai.tcc.zupiapi.zupibackend.services.UserService;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> users = userService.findAll();
        return ResponseEntity.ok().body(users);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.findById(id));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserDTO user) {
        UserResponseDTO usurious  = userService.save(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(usurious.id()).toUri();

        return ResponseEntity.created(uri).body(usurious);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginDTO user) {
        Boolean validPassword = userService.validationPassword(user);

        if (!validPassword) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().body(userService.findByEmail(user.email()));
    }

}
