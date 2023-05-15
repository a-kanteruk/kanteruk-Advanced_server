package net.dunice.newsFeed.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.dunice.newsFeed.dto.PublicUserView;
import net.dunice.newsFeed.models.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM UserEntity", nativeQuery = true)
    List<PublicUserView> findAllUsers();
}
