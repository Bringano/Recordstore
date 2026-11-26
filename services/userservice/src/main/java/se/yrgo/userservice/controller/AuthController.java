package se.yrgo.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.yrgo.userservice.domain.User;
import se.yrgo.userservice.dto.LoginDto;
import se.yrgo.userservice.dto.RegisterUserDto;
import se.yrgo.userservice.dto.TokenDto;
import se.yrgo.userservice.dto.UserDto;
import se.yrgo.userservice.exception.InvalidCredentialsException;
import se.yrgo.userservice.exception.UserAlreadyExistsException;
import se.yrgo.userservice.service.JwtService;
import se.yrgo.userservice.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto) {
        try {
            User user = userService.login(loginDto.email(), loginDto.password());
            String token = jwtService.createToken(user);

            return ResponseEntity.ok(new TokenDto(token));

        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

}