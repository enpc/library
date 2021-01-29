package ru.trsvav.library.api.word;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.trsvav.library.api.word.response.WordCountResponse;

import java.util.List;

@RequestMapping("/api/word")
public interface WordController {

    @GetMapping("/{book}")
    ResponseEntity<List<WordCountResponse>> bookWordCount(
            @PathVariable("book") String book,
            @RequestParam String word,
            @RequestParam int page,
            @RequestParam int size);

    @GetMapping("/{book}/{chapter}")
    ResponseEntity<WordCountResponse> chapterWordCount(
            @PathVariable("book") String book,
            @PathVariable("chapter") Long chapter,
            @RequestParam String word);

}
