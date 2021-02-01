package ru.trsvav.library.service.error.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.trsvav.library.entity.Chapter;
import ru.trsvav.library.repository.ChapterRepository;
import ru.trsvav.library.service.error.ErrorService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ErrorServiceImplTestConfig.class)
class ErrorServiceImplTest {

    @Autowired
    private ErrorService errorService;

    @Autowired
    private ChapterRepository chapterRepository;

    @Test
    void scanErrors() {
        Mockito.when(chapterRepository.save(any()))
                .then(
                        args -> args.getArgument(0)
                );

        var chapter = Chapter.builder()
                .id(1L)
                .book("book")
                .chapterNumber(1L)
                .text("1Текст гал1вы 2 \n 22")
                .build();

        var result = errorService.scanErrors(chapter);
        assertEquals(2,
                result.getErrors().stream()
                        .filter(el -> el.getCharacter().equals("1"))
                        .findFirst().get().getCount()
        );
        assertEquals(3,
                result.getErrors().stream()
                        .filter(el -> el.getCharacter().equals("2"))
                        .findFirst().get().getCount()
        );
    }

}