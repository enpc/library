package ru.trsvav.library.service.splitter.impl;

import org.springframework.stereotype.Service;
import ru.trsvav.library.service.splitter.SplitterService;
import ru.trsvav.library.service.splitter.exceptions.ChapterFormatException;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
public class SplitterServiceImpl implements SplitterService {

    private final String chapterText = "Глава\\s*№";

    private final Pattern chapterPattern = Pattern.compile("^\\s*(?<number>\\d+)\\s*(?<text>[\\s\\S]*)", Pattern.MULTILINE);

    @Override
    public Stream<ChapterDescription> splitBook(InputStream input) {
        return new Scanner(input,StandardCharsets.UTF_8)
                .useDelimiter(chapterText)
                .tokens()
                .map(s -> {
                            var matcher = chapterPattern.matcher(s);
                            if (!matcher.matches()){
                                throw new ChapterFormatException("Wrong chapter format\n"+s);
                            }
                            return ChapterDescription.builder()
                                    .number(Long.valueOf(matcher.group("number")))
                                    .text(matcher.group("text"))
                                    .build();
                        }
                );
    }
}
