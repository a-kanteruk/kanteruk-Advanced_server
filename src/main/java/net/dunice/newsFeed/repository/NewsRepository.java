package net.dunice.newsFeed.repository;

import net.dunice.newsFeed.models.NewsEntity;

import net.dunice.newsFeed.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    Page<NewsEntity> findAllByUserId(UUID userId, Pageable pageable);

}
