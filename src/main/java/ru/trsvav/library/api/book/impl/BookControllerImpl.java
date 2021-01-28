package ru.trsvav.library.api.book.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.trsvav.library.api.book.BookController;
import ru.trsvav.library.api.book.response.SplitBookResponse;
import ru.trsvav.library.service.book.BookStorageService;
import ru.trsvav.library.service.chapter.ChapterService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Log4j2
public class BookControllerImpl implements BookController {

    private final BookStorageService bookStorageService;

    private final ChapterService chapterService;

    @Override
    public ResponseEntity<Void> uploadBook(String name, MultipartFile file) {
        try {
            bookStorageService.appendBook(name, file.getInputStream());
        } catch (IOException e) {
            log.error(e);
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<SplitBookResponse> splitBook(String name) {
        return ResponseEntity.ok(
                SplitBookResponse.builder()
                        .chaptersCount(chapterService.split(name))
                        .build()
        );
    }
}
