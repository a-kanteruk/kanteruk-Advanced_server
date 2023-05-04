package net.dunice.newsFeed.repository;

import net.dunice.newsFeed.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String name);
    Boolean existsByEmail(String email);
}
