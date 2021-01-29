package ru.trsvav.library.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.trsvav.library.entity.Chapter;

import java.util.Optional;
import java.util.stream.Stream;



@Repository
public interface ChapterRepository extends PagingAndSortingRepository<Chapter, Long> {

    Optional<Chapter> findByBookAndChapterNumber(String book, Long chapterNumber);

    Stream<Chapter> findByBookOrderByChapterNumber(String book, Pageable pageable);
}
