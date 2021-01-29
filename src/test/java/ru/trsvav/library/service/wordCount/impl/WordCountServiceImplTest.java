package ru.trsvav.library.service.wordCount.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.trsvav.library.entity.Chapter;
import ru.trsvav.library.service.chapter.ChapterService;
import ru.trsvav.library.service.splitter.impl.SplitterServiceImplTestConfig;
import ru.trsvav.library.service.wordCount.WordCountService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WordCountServiceImplTestConfig.class)
class WordCountServiceImplTest {

    @Autowired
    private WordCountService wordCountService;

    @Autowired
    ChapterService chapterService;

    @Test
    void getChapterWordCount() {
        Mockito.when(chapterService.getChapter(any(), any()))
                .thenAnswer(args ->
                        Chapter.builder()
                                .id(1L)
                                .book(args.getArgument(0))
                                .chapterNumber(args.getArgument(1))
                                .text("Слово другоеслово    слОвО \n\n ещеоднослово      слово, слово. слово;")
                                .build());

        final String bookName = "Книга";
        final long chapterNumber = 5L;
        final String word = "слово";

        var result = wordCountService.getChapterWordCount(bookName, chapterNumber, word);
        assertEquals(5, result.getCount());
        assertEquals(word, result.getWord());
        assertEquals(chapterNumber, result.getChapter());
    }
}