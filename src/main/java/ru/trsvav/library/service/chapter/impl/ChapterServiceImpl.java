package ru.trsvav.library.service.chapter.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.trsvav.library.common.batchColector.JpaBachInsertCollector;
import ru.trsvav.library.entity.Chapter;
import ru.trsvav.library.repository.ChapterRepository;
import ru.trsvav.library.service.book.BookStorageService;
import ru.trsvav.library.service.chapter.ChapterService;
import ru.trsvav.library.exceptions.ChapterNotFoundException;
import ru.trsvav.library.service.splitter.SplitterService;

import javax.persistence.EntityManagerFactory;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChapterServiceImpl implements ChapterService {

    private final BookStorageService bookStorageService;

    private final EntityManagerFactory entityManagerFactory;

    private final SplitterService splitterService;

    private final ChapterRepository chapterRepository;

    @Override
    public Long split(String name) {
        try (var collector = new JpaBachInsertCollector<Chapter>(entityManagerFactory)) {
            return splitterService
                    .splitBook(bookStorageService.findBook(name))
                    .map(chapter ->
                            Chapter.builder()
                                    .book(name)
                                    .chapterNumber(chapter.getNumber())
                                    .text(chapter.getText())
                                    .build()
                    )
                    .collect(collector);
        } catch (Exception e) {
            log.error(e);
            return 0L;
        }
    }

    @Override
    public Chapter getChapter(String book, Long chapterNumber) {
        return chapterRepository.findByBookAndChapterNumber(book, chapterNumber)
                .orElseThrow(() -> new ChapterNotFoundException("Chapter " + book + " " + chapterNumber + "not found"));
    }

    @Override
    public Stream<Chapter> getAllChapters(String book, int page, int size) {
        return chapterRepository.findByBookOrderByChapterNumber(book, PageRequest.of(page, size));
    }
}
