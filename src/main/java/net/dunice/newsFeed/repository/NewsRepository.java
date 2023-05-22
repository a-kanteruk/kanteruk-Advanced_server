package net.dunice.newsFeed.repository;

import java.util.UUID;

import net.dunice.newsFeed.models.NewsEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

    @Query(value = "SELECT * FROM news n WHERE n.user_id = :userId", nativeQuery = true)
    Page<NewsEntity> findAllByUserId(@Param("userId") UUID userId, Pageable pageable);

}
