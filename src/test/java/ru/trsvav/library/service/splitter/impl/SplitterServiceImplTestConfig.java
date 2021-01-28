package ru.trsvav.library.service.splitter.impl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.trsvav.library.service.splitter.SplitterService;

@Configuration
public class SplitterServiceImplTestConfig {

    @Bean
    public SplitterService splitterService(){
        return new SplitterServiceImpl();
    }
}
