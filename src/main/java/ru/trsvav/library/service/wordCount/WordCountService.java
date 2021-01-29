package ru.trsvav.library.service.wordCount;

import ru.trsvav.library.service.wordCount.responce.WordCount;

import java.util.List;

public interface WordCountService {

    WordCount getChapterWordCount(String book, Long chapter, String word);

    List<WordCount> getBookWordCount(String book, String word, int page, int size);
}
