package ru.trsvav.library.service.splitter.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.trsvav.library.service.splitter.SplitterService;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SplitterServiceImplTestConfig.class)
class SplitterServiceImplTest {

    @Autowired
    private SplitterService splitterService;

    @Test
    void parseChapter() {
        final String chapterText = "Текст галвы 1";

        final String testCase = "Глава №1 \n\n" +
                chapterText;

        var result = splitterService
                .splitBook(new ByteArrayInputStream(testCase.getBytes(StandardCharsets.UTF_8)))
                .collect(Collectors.toList());

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getNumber());
        assertEquals(chapterText, result.get(0).getText());
    }

    @Test
    void splitBook() {
        final String testCase = "Глава№1\n" +
                "Текст галвы 1\n" +
                " Глава №2 " +
                "Текст главы 2" +
                "   Глава      №3\n\n" +
                "Текст главы 3";

        var result = splitterService
                .splitBook(new ByteArrayInputStream(testCase.getBytes(StandardCharsets.UTF_8)))
                .collect(Collectors.toList());

        assertEquals(3, result.size());
    }
}