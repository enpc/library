package ru.trsvav.library.service.wordCount.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trsvav.library.entity.Chapter;
import ru.trsvav.library.service.chapter.ChapterService;
import ru.trsvav.library.service.wordCount.WordCountService;
import ru.trsvav.library.service.wordCount.responce.WordCount;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordCountServiceImpl implements WordCountService {

    private final ChapterService chapterService;

    @Override
    public WordCount getChapterWordCount(String book, Long chapter, String word) {
        return calcWordCount(word, chapterService.getChapter(book, chapter));
    }

    @Override
    @Transactional(readOnly = true)
    public List<WordCount> getBookWordCount(String book, String word, int page, int size) {
        return chapterService.getAllChapters(book, page, size)
                .map(chapter -> calcWordCount(word, chapter))
                .collect(Collectors.toList());
    }

    private WordCount calcWordCount(String word, Chapter chapter) {
        var wordCount = new Scanner(chapter.getText())
                .useDelimiter("[\\s,.;:\\?!\\(\\)]")
                .tokens()
                .filter(word::equalsIgnoreCase)
                .count();

        return WordCount.builder()
                .chapter(chapter.getChapterNumber())
                .word(word)
                .count(wordCount)
                .build();
    }

}
