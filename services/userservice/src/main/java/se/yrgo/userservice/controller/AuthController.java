package se.yrgo.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.userservice.domain.User;
import se.yrgo.userservice.dto.RegisterUserDto;
import se.yrgo.userservice.dto.UserDto;
import se.yrgo.userservice.exception.UserAlreadyExistsException;
import se.yrgo.userservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterUserDto registerUserDto) {
        try {
            User user = userService.register(
                    registerUserDto.email(),
                    registerUserDto.password(),
                    registerUserDto.username()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(UserDto.fromUser(user));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}