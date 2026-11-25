package se.yrgo.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.userservice.domain.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

}