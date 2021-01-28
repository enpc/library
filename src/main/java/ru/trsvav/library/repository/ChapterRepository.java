package ru.trsvav.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.trsvav.library.entity.Chapter;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}
