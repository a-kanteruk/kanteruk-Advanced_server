package net.dunice.newsFeed.repository;

import net.dunice.newsFeed.models.TagEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagsRepository extends JpaRepository<TagEntity, Long> {
}
