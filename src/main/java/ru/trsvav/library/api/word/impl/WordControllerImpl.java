package ru.trsvav.library.api.word.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.trsvav.library.api.word.WordController;
import ru.trsvav.library.api.word.response.BookWordCountResponse;
import ru.trsvav.library.api.word.response.ChapterWordCountResponse;
import ru.trsvav.library.service.wordCount.WordCountService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class WordControllerImpl implements WordController {

    private final WordCountService wordCountService;

    private final ResponseMapper responseMapper;

    @Override
    public ResponseEntity<List<BookWordCountResponse>> bookWordCount(String book, String word, int page, int size) {
        return ResponseEntity.ok(
                wordCountService.getBookWordCount(book, word, page, size).stream()
                        .map(responseMapper::wordCountToBookResponse)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public ResponseEntity<ChapterWordCountResponse> chapterWordCount(String book, Long chapter, String word) {
        return ResponseEntity.ok(
                responseMapper.wordCountToChapterResponse(
                        wordCountService.getChapterWordCount(book, chapter, word)
                )
        );
    }
}
