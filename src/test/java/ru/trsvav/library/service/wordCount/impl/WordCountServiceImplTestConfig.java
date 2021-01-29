package ru.trsvav.library.service.wordCount.impl;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.trsvav.library.service.chapter.ChapterService;
import ru.trsvav.library.service.wordCount.WordCountService;

@Configuration
public class WordCountServiceImplTestConfig {

    @Bean
    public WordCountService wordCountService(){
        return new WordCountServiceImpl(chapterService());
    }

    @Bean
    public ChapterService chapterService(){
        return Mockito.mock(ChapterService.class);
    }
}
