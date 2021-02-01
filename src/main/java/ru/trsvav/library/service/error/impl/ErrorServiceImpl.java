package ru.trsvav.library.service.error.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.trsvav.library.entity.Chapter;
import ru.trsvav.library.entity.Error;
import ru.trsvav.library.exceptions.ChapterNotFoundException;
import ru.trsvav.library.repository.ChapterRepository;
import ru.trsvav.library.service.error.ErrorService;

import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ErrorServiceImpl implements ErrorService {

    private final ChapterRepository chapterRepository;

    @Override
    @Transactional
    public Chapter scanErrors(Chapter chapter) {

        var errors = new Scanner(chapter.getText())
                .findAll("[0-9]")
                .map(MatchResult::group)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .map(error -> Error.builder()
                        .character(error.getKey())
                        .count(error.getValue())
                        .build()
                )
                .collect(Collectors.toSet());

        chapter.setErrors(errors);
        chapter.setErrorChecked(true);

        return chapterRepository.save(chapter);
    }

    @Override
    public Set<Error> getErrors(String book, Long chapterNumber) {
        var chapter = chapterRepository.findByBookAndChapterNumber(book, chapterNumber)
                .orElseThrow(() -> new ChapterNotFoundException("Chapter " + book + ":" + chapterNumber + "not found"));

        if (!chapter.isErrorChecked()) {
            chapter = scanErrors(chapter);
        }

        return chapter.getErrors();
    }
}
