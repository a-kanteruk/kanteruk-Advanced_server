package net.dunice.newsFeed.repository;

import net.dunice.newsFeed.models.NewsEntity;

import net.dunice.newsFeed.models.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Long> {
    Page<NewsEntity> findAllByUser(UserEntity userEntity, Pageable pageable);
}
