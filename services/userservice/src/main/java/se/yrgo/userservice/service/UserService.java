package se.yrgo.userservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.yrgo.userservice.domain.Role;
import se.yrgo.userservice.domain.User;
import se.yrgo.userservice.exception.InvalidCredentialsException;
import se.yrgo.userservice.exception.UserAlreadyExistsException;
import se.yrgo.userservice.exception.UserNotFoundException;
import se.yrgo.userservice.repository.RoleRepository;
import se.yrgo.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(String email, String password, String username) throws UserAlreadyExistsException {
        if (userRepository.existsByEmail(email)) {
            throw new UserAlreadyExistsException("Email already in use");
        }
        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username already taken");
        }

        Role customerRole = roleRepository.findByName("CUSTOMER");

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        user.setRole(customerRole);

        return userRepository.save(user);
    }

    public User login(String email, String password) throws InvalidCredentialsException {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        return user.get();
    }

    public User updateUsername(Long id, String username) throws UserNotFoundException, UserAlreadyExistsException {
        User user = getUserById(id);

        if (userRepository.existsByUsername(username)) {
            throw new UserAlreadyExistsException("Username already taken");
        }

        user.setUsername(username);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws UserNotFoundException {
        User user = getUserById(id);

        userRepository.delete(user);
    }
}