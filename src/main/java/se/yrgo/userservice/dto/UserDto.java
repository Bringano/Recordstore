package se.yrgo.userservice.dto;

public record UserDto(Long id, String email, String username, String roleName) {
}