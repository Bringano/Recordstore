package se.yrgo.userservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se.yrgo.userservice.domain.Role;
import se.yrgo.userservice.domain.User;
import se.yrgo.userservice.repository.RoleRepository;
import se.yrgo.userservice.repository.UserRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String adminEmail;

    @Value("${admin.password}")
    private String adminPassword;

    public DataSeeder(RoleRepository roleRepository,
                      UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        createRoleIfMissing("ADMIN");
        createRoleIfMissing("CUSTOMER");
        createAdminIfMissing();
    }

    private void createRoleIfMissing(String name) {
        if (roleRepository.findByName(name) == null) {
            Role role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
    }

    private void createAdminIfMissing() {
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = new User();
            admin.setEmail(adminEmail);
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(roleRepository.findByName("ADMIN"));
            userRepository.save(admin);
        }
    }
}