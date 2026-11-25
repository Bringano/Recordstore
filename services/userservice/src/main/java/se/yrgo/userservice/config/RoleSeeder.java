package se.yrgo.userservice.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.yrgo.userservice.domain.Role;
import se.yrgo.userservice.repository.RoleRepository;

@Component
public class RoleSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) {
        createRoleIfMissing("ADMIN");
        createRoleIfMissing("CUSTOMER");
    }

    private void createRoleIfMissing(String name) {
        if (roleRepository.findByName(name) == null) {
            Role role = new Role();
            role.setName(name);
            roleRepository.save(role);
        }
    }
}