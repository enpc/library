package ru.trsvav.library.service.chapter.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.trsvav.library.common.batchColector.JpaBachInsertCollector;
import ru.trsvav.library.entity.Book;
import ru.trsvav.library.entity.Chapter;
import ru.trsvav.library.repository.BookRepository;
import ru.trsvav.library.service.book.BookStorageService;
import ru.trsvav.library.service.chapter.ChapterService;
import ru.trsvav.library.service.splitter.SplitterService;

import javax.persistence.EntityManagerFactory;

@Service
@RequiredArgsConstructor
@Log4j2
public class ChapterServiceImpl implements ChapterService {

    private final BookStorageService bookStorageService;

    private final BookRepository bookRepository;

    private final EntityManagerFactory entityManagerFactory;

    private final SplitterService splitterService;

    @Override
    public Long split(String name) {

        final Book book = bookRepository.findByName(name)
                .orElseGet(
                        () -> bookRepository.save(
                                Book.builder()
                                        .name(name)
                                        .build()
                        )
                );

        try (var collector = new JpaBachInsertCollector<Chapter>(entityManagerFactory)) {
            return splitterService
                    .splitBook(bookStorageService.findBook(name))
                    .map(chapter ->
                            Chapter.builder()
                                    .book(book.getId())
                                    .chapterNumber(chapter.getNumber())
                                    .text(chapter.getText())
                                    .build()
                    )
                    .collect(collector);
        }catch (Exception e){
            log.error(e);
            return 0L;
        }
    }
}
