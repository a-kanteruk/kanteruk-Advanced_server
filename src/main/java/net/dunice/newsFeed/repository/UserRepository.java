package net.dunice.newsFeed.repository;

import java.util.Optional;
import java.util.UUID;

import net.dunice.newsFeed.models.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);
}
