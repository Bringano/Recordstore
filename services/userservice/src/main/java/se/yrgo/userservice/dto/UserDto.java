package se.yrgo.userservice.dto;

import se.yrgo.userservice.domain.User;

public record UserDto(Long id, String email, String username, String roleName) {

    public static UserDto fromUser(User user) {
        return new UserDto(user.getId(), user.getEmail(), user.getUsername(), user.getRole().getName());
    }
}