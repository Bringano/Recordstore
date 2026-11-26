package se.yrgo.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.yrgo.userservice.domain.User;
import se.yrgo.userservice.dto.UpdateUsernameDto;
import se.yrgo.userservice.dto.UserDto;
import se.yrgo.userservice.exception.UserAlreadyExistsException;
import se.yrgo.userservice.exception.UserNotFoundException;
import se.yrgo.userservice.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(UserDto.fromUser(user));
        }

        return userDtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(UserDto.fromUser(user));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsername(@PathVariable Long id, @RequestBody UpdateUsernameDto updateUsernameDto) {
        try {
            User user = userService.updateUsername(id, updateUsernameDto.username());
            return ResponseEntity.ok(UserDto.fromUser(user));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


}