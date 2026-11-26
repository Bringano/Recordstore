package se.yrgo.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.yrgo.userservice.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User findByEmail(String email);

}
