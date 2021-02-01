package ru.trsvav.library.service.error.impl;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.trsvav.library.repository.ChapterRepository;
import ru.trsvav.library.service.error.ErrorService;

@Configuration
public class ErrorServiceImplTestConfig {

    @Bean
    public ErrorService errorService(){
        return new ErrorServiceImpl(chapterRepository());
    }

    @Bean
    public ChapterRepository chapterRepository(){
        return Mockito.mock(ChapterRepository.class);
    }
}
